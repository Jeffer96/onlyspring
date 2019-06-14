package com.airefresco.app.Model;

public class LoginRequest {
	
	private String userName;
	private String userPass;
		
	public LoginRequest() {
		
	}
	
	public LoginRequest(String userName, String userPass) {
		this.setUserName(userName);
		this.setUserPass(userPass);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	
}
