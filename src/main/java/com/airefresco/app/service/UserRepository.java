package com.airefresco.app.service;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.airefresco.app.Model.User;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User,String>{
	
	//User findByName(String name);
	
	//Collection<User> getAll();
	
	//Collection<User> getByRoleName(String role);
	

}
