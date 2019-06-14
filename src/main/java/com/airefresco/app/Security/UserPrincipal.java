package com.airefresco.app.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.airefresco.app.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrincipal implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

    private String userName;
    
    private String nickName;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String userPass;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(int id, String name, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userName = username;
        this.userPass = password;
        this.authorities = authorities;
    }
    
    public static UserPrincipal create(User user) {
    	if ( user != null ) {
	    	SimpleGrantedAuthority  role =  new SimpleGrantedAuthority (user.getRoleName());
	       List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
	       authorities.add(role);
	
	        return new UserPrincipal(
	                user.getId(),
	                user.getName(),
	                user.getNickName(),
	                user.getPass(),
	                authorities
	        );
    	}else {
    		return null;
    	}
    }
    
    public int getId() {
    	return this.id;
    }
    
    public String getNickName() {
    	return this.nickName;
    }
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.userPass;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
