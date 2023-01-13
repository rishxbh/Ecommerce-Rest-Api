package com.rishabh.website.exceptions;

public class AuthenticationFailException extends IllegalArgumentException{
	public AuthenticationFailException(String message) {
		super(message);
	}
}
