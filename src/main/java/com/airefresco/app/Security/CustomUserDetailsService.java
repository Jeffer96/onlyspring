package com.airefresco.app.Security;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.airefresco.app.Model.User;
import com.airefresco.app.service.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    UserRepository userRepository;
	
	public CustomUserDetailsService() {
		//System.out.println(">>>>>>>>>>>>>>>>creando el cuds");
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByNickName( username );
		/**
		if (user == null) {
			throw  new UsernameNotFoundException(">>>>>>>>>>>>>>>>>>>>>>>>User not found : " + username);
		}**/
		
        return UserPrincipal.create(user);
	}
	
    public UserDetails loadUserById(int id) {
		 //System.out.println(">>>>>>>>>>>>>>>>>>>>>> vamos a revisar el usuario");
		 User user = userRepository.findUserById(id);
        return UserPrincipal.create(user);
    }
	 
	 public User getUserAppById(int id) {
		 return userRepository.findUserById(id);
	 }

}
