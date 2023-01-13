package com.rishabh.website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;
import com.rishabh.website.exceptions.AuthenticationFailException;
import com.rishabh.website.model.AuthenticationToken;
import com.rishabh.website.model.User;
import com.rishabh.website.repo.TokenRepo;

@Service
public class AuthServiceImpl implements AuthService {

	
	@Autowired
	private TokenRepo tokenRepo;
	
	@Override
	public void saveConfirmationToken(AuthenticationToken token) {
		tokenRepo.save(token);
	}

	@Override
	public AuthenticationToken getToken(User user) {
		return tokenRepo.findByUser(user);
	}

	@Override
	public void authenticateToken(String token) {
		if(token == null) {
			throw new AuthenticationFailException("token does not exist");
		}
		if(getUser(token) == null) {
			throw new AuthenticationFailException("token not valid");
		}
	}

	@Override
	public User getUser(String token) {
		final AuthenticationToken authenticationToken = tokenRepo.findByToken(token);
		if(authenticationToken == null) {
			return null;
		}
		return authenticationToken.getUser();
	}

}
