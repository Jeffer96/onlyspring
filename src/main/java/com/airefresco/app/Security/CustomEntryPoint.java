package com.airefresco.app.Security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class CustomEntryPoint extends LoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint{

	public CustomEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	@Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,AuthenticationException e) throws IOException, ServletException {
        //httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }

}
