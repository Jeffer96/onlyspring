package com.airefresco.app.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import com.airefresco.app.Security.JwtProvider;

@RestController
@CrossOrigin(origins="http://localhost:8080")
public class MainControllerRest {
	
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
