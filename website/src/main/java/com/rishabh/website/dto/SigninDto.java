package com.rishabh.website.dto;

public class SigninDto {
	private String email;
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public SigninDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public SigninDto() {
		super();
	}
}
