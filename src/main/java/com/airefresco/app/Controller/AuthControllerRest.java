package com.airefresco.app.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.airefresco.app.Components.TokenProvider;
import com.airefresco.app.Model.LoginRequest;
import com.airefresco.app.Model.User;
import com.airefresco.app.service.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.airefresco.app.Security.JwtProvider;**/

@RestController
@CrossOrigin(origins="http://localhost:8080")
public class AuthControllerRest {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
		
	@PostMapping(value="/registro/add")
	public String addUserTest(@RequestBody User user) {
		String response;
		User us = userRepository.findUserById(user.getId());
		try {
			if (us != null) {
				response = "el usuario con identificacion: "+us.getId()+" ya se encuentra registrado";
			}else {
				//System.out.println("name: "+user.getName()+" \n nick: "+user.getNickName()+" \n id: "+user.getId()+" \n rol: "+user.getRoleName());
				userRepository.save(user);
				response = "Usuario "+user.getName()+" ha sido registrado con exito"; 
			}
		}catch ( DataAccessException sqle) {
			response = "Usuario "+user.getName()+" no se ha podido registrar: \n"+sqle;
		}
		return response;
	}
	
	@RequestMapping(value="/login/auth")
	public String  isUserRegistered(@RequestBody LoginRequest lr) {
		User us = userRepository.findUserByNickName(lr.getUserName());
		String jwt = "";
		if (us != null) {
			UsernamePasswordAuthenticationToken upat =  new UsernamePasswordAuthenticationToken(lr.getUserName(),lr.getUserPass());
			try {
				Authentication authentication = authenticationManager.authenticate( upat );
				SecurityContextHolder.getContext().setAuthentication(authentication);
				jwt = TokenProvider.generateToken(authentication)+"<<>>"+us.getName() ;
			}catch (AuthenticationException  ex) {
				System.out.println(" >>>>>>>>>>> E R R O R <<<<<<<<<<<<<<<<<<<<< "+ex);
			}
			
			
		}
		return jwt;
	}
		
}
