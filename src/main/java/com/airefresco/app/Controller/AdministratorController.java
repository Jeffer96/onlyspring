package com.airefresco.app.Controller;

import static com.airefresco.app.Security.Constants.HEADER_AUTHORIZACION_KEY;
import static com.airefresco.app.Security.Constants.TOKEN_BEARER_PREFIX;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airefresco.app.Components.TokenProvider;
import com.airefresco.app.Model.User;
import com.airefresco.app.service.UserRepository;

@RestController
@RequestMapping("/administrador")
public class AdministratorController {
			
	@Autowired
	UserRepository ur;
	
	@RequestMapping(value = "/users")
	@PreAuthorize("hasAuthority('ADMIN')")
	public Collection<User> getAllUsers(){
		return null;
	}
	
	@PostMapping(value = "/addUser")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveNewUser(@RequestBody User user){
		return "ha guardado";
	}
	
	@RequestMapping("/test")
	//@PreAuthorize("hasAuthority('ADMIN')")
	public void testAuthentication(HttpServletRequest request){		
		String ans="";
		String bearerToken = request.getHeader(HEADER_AUTHORIZACION_KEY);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_BEARER_PREFIX)) {
			ans = bearerToken.substring(7,bearerToken.length());
		}
		if (StringUtils.hasText(ans) && TokenProvider.validateToken(ans)) {
			int userId = TokenProvider.getUserId(ans);
			String role = ur.findUserById(userId).getRoleName();
			System.out.println(">>>>>>>>>>> el rol del usuario es: "+role);
		}
		System.out.println(">>>>>>>>>>>>entro este es el token: "+ans);
	}
}
