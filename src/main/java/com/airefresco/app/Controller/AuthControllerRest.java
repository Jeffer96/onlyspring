package com.airefresco.app.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airefresco.app.Components.TokenProvider;
//import com.airefresco.app.Exceptions.UnauthorizedExeption;
import com.airefresco.app.Model.LoginRequest;
import com.airefresco.app.Model.ResponseLogin;
import com.airefresco.app.Model.User;
import com.airefresco.app.service.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.airefresco.app.Security.Constants.HEADER_AUTHORIZACION_KEY;
import static com.airefresco.app.Security.Constants.TOKEN_BEARER_PREFIX;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

@RestController
@CrossOrigin(origins="http://localhost:8080")
public class AuthControllerRest {
		
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	protected final void authenticate(UsernamePasswordAuthenticationToken upat, ResponseLogin ans, User us) {
		try {
			Authentication authentication = authenticationManager.authenticate( upat );
			SecurityContextHolder.getContext().setAuthentication(authentication);
			ans.setToken(TokenProvider.generateToken(authentication));
			ans.setUserName(us.getName());
			ans.setMessage("Bienvenido: "+us.getName());
			ans.setRespCode("202");
			ans.successAuth(us.getRoleName());
		}catch (AuthenticationException  ex) {
			ans.setRespCode("401");
		}
	}
			
	@RequestMapping(value="/login/auth")
	@ResponseBody
	public ResponseLogin  authenticateUser(@RequestBody LoginRequest lr) {
		ResponseLogin ans = new ResponseLogin();
		try {
			User us;
			us = userRepository.findUserByNickName(lr.getUserName());
			if (us != null) {
				UsernamePasswordAuthenticationToken upat =  new UsernamePasswordAuthenticationToken(lr.getUserName(),lr.getUserPass());
				authenticate(upat, ans, us);
			}
		}catch (DataAccessException ex) {
			ans.setRespCode("404");
		}
		return ans;
	}
			
	private final boolean isAuthenticated(HttpServletRequest request, String role) {
		String bearerToken = request.getHeader(HEADER_AUTHORIZACION_KEY);
		boolean resp = false;
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_BEARER_PREFIX)) {
			String ans = bearerToken.substring(7,bearerToken.length());
			if (StringUtils.hasText(ans) && TokenProvider.validateToken(ans)) {
				int userId = TokenProvider.getUserId(ans);
				try {
					resp = userRepository.findUserById(userId).getRoleName().equals(role);
				}catch (DataAccessException ex) {}
			}
		}
		return resp;
	}
	
	
	@RequestMapping("/login/getUrl")
	public String getUrl(HttpServletRequest request) {
		if(isAuthenticated(request , "ADMIN")) {
			return "/administrador";
		}else if (isAuthenticated(request,"EMPLOYEE")) {
			return "/other";
		}else if (isAuthenticated(request,"USER")) {
			return "/home";
		}
		return "/login";
	}
	
}
