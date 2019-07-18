
package com.airefresco.app.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;
import lombok.ToString;


@Entity
@Table(name="users")
@ToString(exclude = "pass")
@Data
public class User {
	
	@Id
	private int id;
	@NotBlank
	private String userName;
	@NotBlank
	private String nickName;
	@NotBlank 
	private String userPass;
	@NotBlank
	private String roleName;
	private String email;
	
	public User() {
		
	}
	
	public User(String userName, String nickName, String userPass, int id, String roleName, String email) {
		this.id = id;
		this.userPass = secure(userPass);
		this.nickName = nickName;
		this.userName = userName;
		this.roleName = roleName;
		this.email = email;
	}
	
	
	protected String secure(String param) {
		return new BCryptPasswordEncoder().encode(param);
	}
	
	public User(String name, String userPass) {
		this.userName= name;
		this.userPass = secure(userPass);
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return this.userName;
	}
	

	public String getUserPass(){
		return this.userPass;
	}
	
	public void setUserPass(String userPass) {
		this.userPass = secure(userPass);
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getNickName() {
		return this.nickName;
	}
	
	public String getRoleName() {
		return this.roleName;
	}
	
	public void setRoleName(String rol) {
		this.roleName = rol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}