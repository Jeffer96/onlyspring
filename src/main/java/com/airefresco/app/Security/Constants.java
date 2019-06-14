package com.airefresco.app.Security;

public class Constants {

	// Spring Security
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT
	public static final String AUTHORITIES_KEY = "CLAIM_TOKEN";
	public static final String SUPER_SECRET_KEY = "@Airefresco@Application@secret_key#2019#";
	public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day

}