package com.airefresco.app.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
	public UserDetails loadUserByUsername(String nickName) throws UsernameNotFoundException {
		User user = userRepository.findUserByNickName( nickName );
		UserPrincipal ans;
		if (user != null) {
			ans = UserPrincipal.create(user);
		}else {
			throw  new UsernameNotFoundException(">>>>>>>>>>>>>>>>>>>>>>>>User not found : " + nickName);

		}
		
        return ans;
	}
	
	 @Transactional
    public UserDetails loadUserById(int id) {
		 User user;
		 if (userRepository.existsById(id)) {
			 user = userRepository.findUserById(id);
		 }else {
        		throw new ResourceNotFoundException(">>>>>>>>>>>>>>>>>>>>>>>>User not found<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		 }

        return UserPrincipal.create(user);
    }

}
