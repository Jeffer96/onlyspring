package com.airefresco.app.Controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.airefresco.app.Model.User;
import com.airefresco.app.service.UserRepository;

@RestController
@RequestMapping("/administrador")
public class AdministratorController{
	
	@Autowired
	UserRepository ur;
	
		
	@PostMapping(value = "/addUser")
	public String addUser(@RequestBody User user) {
		try {
			ur.save(user);
			return "202";
		}catch (DataAccessException ex){
			return "401";
		}
	}
		
	@RequestMapping("/getAllUsers")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseBody
	public Collection<User> getAllUsers(){
		try {
			return ur.getAllUser();
		}catch(DataAccessException ex) {
			return new ArrayList<User>();
		}
	}
}