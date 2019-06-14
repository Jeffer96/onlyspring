
package com.airefresco.app.Model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;


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
	@JsonIgnore 
	private String userPass;
	@NotBlank
	private String roleName;
	
	
	public User() {
		
	}
	
	public User(String name, String nickName, String pass, int id, String roleName) {
		this.id = id;
		this.userPass = secure(pass);
		this.nickName = nickName;
		this.userName = name;
		this.roleName = roleName;
	}
	
	
	protected String secure(String param) {
		return new BCryptPasswordEncoder().encode(param);
	}
	
	public User(String name, String pass) {
		this.userName= name;
		this.userPass = secure(pass);
	}
	
	public void setName(String name) {
		this.userName = name;
	}
	
	public String getName() {
		return this.userName;
	}
	

	public String getPass(){
		return this.userPass;
	}
	
	public void setPass(String pass) {
		this.userPass = secure(pass);
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
	
}