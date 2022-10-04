package com.sayali.springboot.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AuthenticationRequest {
	
	@NotNull
	@NotBlank
	private String username;
	@NotNull
	@NotBlank
	private String userpassword;
		
	public AuthenticationRequest() {
		super();
	}
	
	public AuthenticationRequest(String username, String userpassword) {
		super();
		this.username = username;
		this.userpassword = userpassword;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	
	

}
