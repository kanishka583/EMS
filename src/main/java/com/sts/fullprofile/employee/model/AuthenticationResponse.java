package com.sts.fullprofile.employee.model;

public class AuthenticationResponse {
	
	private String jwt;

	public String getJwt() {
		return jwt;
	}

	public AuthenticationResponse(String jwt) {
		super();
		this.jwt = jwt;
	}
	
}
