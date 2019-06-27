package com.airefresco.app.service;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.airefresco.app.Model.User;

@Service
public interface UserRepository extends CrudRepository<User,Integer>{
	
	@Query(value = "SELECT * FROM users WHERE nick_name = ?1", nativeQuery = true)
	User findUserByNickName(String nickName);	
	
	@Query(value = "SELECT * FROM users WHERE id = ?1", nativeQuery = true)
	User findUserById(int id);
	
	@Query(value = "SELECT * FROM users", nativeQuery = true)
	Collection<User> getAllUser();
	
}
