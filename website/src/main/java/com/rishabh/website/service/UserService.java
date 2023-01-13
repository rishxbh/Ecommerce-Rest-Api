package com.rishabh.website.service;

import com.rishabh.website.dto.ResponseDto;
import com.rishabh.website.dto.SigninDto;
import com.rishabh.website.dto.SignupDto;

public interface UserService {

	ResponseDto signUp(SignupDto signupDto);

	ResponseDto signin(SigninDto signinDto);

}
