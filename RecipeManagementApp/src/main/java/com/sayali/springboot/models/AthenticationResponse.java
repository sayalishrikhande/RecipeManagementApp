package com.sayali.springboot.models;

public class AthenticationResponse {
	
	private final String jwtToken;
	
	public AthenticationResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

}
