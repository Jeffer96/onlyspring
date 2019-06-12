
package com.airefresco.app.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;


@Entity
@Table(name="users")
@ToString(exclude = "pass")
@Data
public class User {
	
	@Id
	private Long id;
	@NotBlank
	private String name;
	@NotBlank
	@JsonIgnore 
	private String pass;
	private String role;
	
	public User() {
		
	}
	
	protected String secure(String param) {
		return "some"+param;
	}
	
	public User(String name, String pass, String role) {
		this.name= name;
		this.pass = secure(pass);
		this.role = role;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return this.role;
	}

	public String getPass(){
		return this.pass.substring(3);
	}
	
	public void setPass(String pass) {
		this.pass = secure(pass);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}