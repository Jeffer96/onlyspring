package com.airefresco.app.service;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airefresco.app.Model.User;

@Service
public interface UserRepository extends CrudRepository<User,Integer>{
	
	@Query(value = "SELECT * FROM users WHERE nick_name = ?1", nativeQuery = true)
	User findUserByNickName(String nickName);	
	
	@Query(value = "SELECT * FROM users WHERE id = ?1", nativeQuery = true)
	User findUserById(int id);
	
	@Query(value = "SELECT * FROM users", nativeQuery = true)
	Collection<User> getAllUser();
	
	@Query(value = "SELECT * FROM users ORDER BY id", nativeQuery = true)
	Collection<User> getAllUsersOrderedById();
	
	@Query(value = "SELECT * FROM users ORDER BY user_name", nativeQuery = true)
	Collection<User> getAllUsersOrderedByName();
	
	@Query(value = "SELECT * FROM users ORDER BY user_nick", nativeQuery = true)
	Collection<User> getAllUsersOrderedByNick();
	
	@Query(value = "SELECT * FROM users WHERE role_name =?1", nativeQuery = true)
	Collection<User> getUserByRol(String roleName);
		
	@Modifying
	@Transactional
	@Query(value = "UPDATE users u SET user_name = ?1 WHERE u.id = ?2", nativeQuery = true)
	void updateUserName(String newName, int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users u SET nick_name = ?1 WHERE u.id = ?2", nativeQuery = true)
	void updateUserNick(String nickName, int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users u SET id = ?1 WHERE u.id = ?2", nativeQuery = true)
	void updateUserId(int newid, int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users u SET email = ?1 WHERE u.id = ?2", nativeQuery = true)
	void updateUserEmail(String email, int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users u SET role_name = ?1 WHERE u.id = ?2", nativeQuery = true)
	void updateAuthority(String rol, int id);
}
