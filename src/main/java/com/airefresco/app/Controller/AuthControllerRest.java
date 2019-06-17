package com.airefresco.app.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.airefresco.app.Components.TokenProvider;
import com.airefresco.app.Model.LoginRequest;
import com.airefresco.app.Model.ResponseLogin;
import com.airefresco.app.Model.User;
import com.airefresco.app.service.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@CrossOrigin(origins="http://localhost:8080")
public class AuthControllerRest {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	protected void authenticate(UsernamePasswordAuthenticationToken upat, ResponseLogin ans, User us) {
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
			
}
