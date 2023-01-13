package com.rishabh.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rishabh.website.dto.ResponseDto;
import com.rishabh.website.dto.SigninDto;
import com.rishabh.website.dto.SignupDto;
import com.rishabh.website.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseDto signup(@RequestBody SignupDto signupDto) {
		return userService.signUp(signupDto);
	}
	
	@PostMapping("/signin")
	public ResponseDto signin(@RequestBody SigninDto signinDto) {
		System.out.println("YOOOOOO");
		return userService.signin(signinDto);
	}
	
}
