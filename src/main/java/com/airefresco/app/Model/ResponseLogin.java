package com.airefresco.app.Model;

public class ResponseLogin {
	private String message;
	private String userName;
	private String respCode;
	private String token;
	private String url;

	public ResponseLogin() {
		
	}
	
	public ResponseLogin(String token, String userName, String respCode, String message, String url) {
		this.userName = userName;
		this.respCode = respCode;
		this.message = message;
		this.token = token;
		this.url = url;
	}
	
	public void successAuth(String auth) {
		setUrlFromAuth(auth);
	}
	
	protected void setUrlFromAuth(String rol) {
		
		if (rol.equals("ADMIN")) {
			this.url = "/administrador";
		} else if (rol.equals("EMPLOYEE")) {
			this.url = "/portal";
		} else if (rol.equals("USER")) {
			this.url = "/inicio";
		} else {
			this.url = "/login";
		}
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
