package com.rishabh.website.service;

import com.rishabh.website.model.AuthenticationToken;
import com.rishabh.website.model.User;

public interface AuthService {

	void saveConfirmationToken(AuthenticationToken token);

	AuthenticationToken getToken(User user);
	
	void authenticateToken(String token);
	
	User getUser(String token);

}
