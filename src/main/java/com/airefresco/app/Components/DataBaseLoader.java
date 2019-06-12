package com.airefresco.app.Components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.airefresco.app.Model.User;
import com.airefresco.app.service.UserRepository;

public class DataBaseLoader implements CommandLineRunner{
	
	private final UserRepository customerRepository;
	
	@Autowired
	public DataBaseLoader(UserRepository cr) {
		customerRepository = cr;
	}

	@Override
	public void run(String... args) throws Exception {
		customerRepository.save(new User("airefresco","830.071.876-1","ADMIN"));
	}

}
