package com.airefresco.app.Model;

public class JwtResponse {
	
	private String token;
	private String type;
	
	public JwtResponse(String token) {
		this.token = token;
		this.type="Bearer";
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

}
