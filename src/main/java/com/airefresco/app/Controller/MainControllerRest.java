package com.airefresco.app.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.airefresco.app.Model.User;
import com.airefresco.app.service.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/**
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.airefresco.app.Security.JwtProvider;**/

@RestController
@CrossOrigin(origins="http://localhost:8080")
public class MainControllerRest {
	
	@Autowired
	UserRepository userRepository;
	
	/**
	@GetMapping(value="/login")
	public Iterable<User> getAll(){
		return userRepository.findAll();
	}**/
	
	private int toInt(String numberText) {
		String compare = "0123456789";
		String[] var = numberText.split("");
		boolean seguir=true;
		int res=0;
		for (int i=0; i<var.length && seguir;i++) {
			if(compare.contains(var[i])) {
				res = (res*10) + Integer.parseInt(var[i]);
			}else {
				seguir = false;
				res = 0;
			}
		}
		return res;
	}
	
	@PostMapping(value="/registro/add")
	public String addUserTest(@RequestBody User user) {
		String response;
		try {
			userRepository.save(user);
			response = "User "+user.getName()+" saved Succesfully"; 
		}catch (Exception e) {
			response = "User "+user.getName()+" couldn´t be saved, please try again!";
		}
		return response;
	}
	
	/**@Autowired
	UserDetailsService uds;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtProvider tokenProvider;
		
	@PostMapping(value="/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestParam("un") String userName, @RequestParam("pwd") String pass ){
		
		Authentication authentication = this.authenticate(userName, pass);
        SecurityContextHolder.getContext().setAuthentication(authentication);
		String generatedToken = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok("{token:"+generatedToken+"}");
		//return ResponseEntity.ok("{user:+"+userName+",pass:"+pass+"}");
	}
	
	private Authentication authenticate(String un, String pwd) {
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(un, pwd));
	}**/
	
}
