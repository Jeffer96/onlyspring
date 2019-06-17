package com.airefresco.app.Security;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airefresco.app.Model.User;
import com.airefresco.app.service.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByNickName( username );
		/**
		if (user == null) {
			throw  new UsernameNotFoundException(">>>>>>>>>>>>>>>>>>>>>>>>User not found : " + username);
		}**/
		
        return UserPrincipal.create(user);
	}
	
	 @Transactional
    public UserDetails loadUserById(int id) {
		 System.out.println(" vamos a revisar el usuario");
		 User user = userRepository.findUserById(id);
		 
		 if (user == null) {
			 System.out.println(" 3.5 no encontró el usuario");
		 }else {
			 System.out.println(" 3.5 el usuario es"+user.getName());
		 }
        return UserPrincipal.create(user);
    }
	 
	 @Transactional
	 public User getUserAppById(int id) {
		 return userRepository.findUserById(id);
	 }

}
