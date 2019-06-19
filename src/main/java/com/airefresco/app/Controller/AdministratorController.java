package com.airefresco.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airefresco.app.Model.User;
import com.airefresco.app.service.UserRepository;

@RestController
public class AdministratorController{
	
	@Autowired
	UserRepository ur;
		
	@PostMapping(value = "/administrador/addUser")
	public void addUser(@RequestBody User user) {
				
	}
	
	@RequestMapping(value = "/administrador/logout")
	public void logOut() {
		
	}
}