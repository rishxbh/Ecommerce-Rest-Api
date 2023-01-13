package com.rishabh.website.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishabh.website.dto.ResponseDto;
import com.rishabh.website.dto.SigninDto;
import com.rishabh.website.dto.SignupDto;
import com.rishabh.website.exceptions.AuthenticationFailException;
import com.rishabh.website.exceptions.CustomException;
import com.rishabh.website.model.AuthenticationToken;
import com.rishabh.website.model.User;
import com.rishabh.website.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AuthService authService;
	
	@Transactional
	public ResponseDto signUp(SignupDto signupDto) {
		if(userRepo.findByEmail(signupDto.getEmail()) != null) {
			throw new CustomException("User already exists");
		}
		
		String encryptedPassword = signupDto.getPassword();
		
		try {
			encryptedPassword = hashPassword(signupDto.getPassword());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user = new User();
		user.setEmail(signupDto.getEmail());
		user.setFirstName(signupDto.getFirstName());
		user.setLastName(signupDto.getLastName());
		user.setPassword(encryptedPassword);
		userRepo.save(user);
		
		AuthenticationToken token = new AuthenticationToken(user);
		authService.saveConfirmationToken(token);
		
		return new ResponseDto("success", "user created successfully");
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}

	@Override
	public ResponseDto signin(SigninDto signinDto) {
		// TODO Auto-generated method stub
		User user = userRepo.findByEmail(signinDto.getEmail());
		if(user == null) {
			throw new AuthenticationFailException("User doesn't exist");
		}
		try {
			if(!user.getPassword().equals(hashPassword(signinDto.getPassword()))) {
				throw new AuthenticationFailException("Wrong Password");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		AuthenticationToken token = authService.getToken(user);
		if(token == null) {
			throw new CustomException("token is not present");
		}
		return new ResponseDto("success", token.getToken());
	}

}
