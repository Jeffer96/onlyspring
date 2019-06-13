
package com.airefresco.app.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;


@Entity
@Table(name="Users")
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
	
	public User() {
		
	}
	
	public User(String name, String nickName, String pass, int id) {
		this.id = id;
		this.userPass = secure(pass);
		this.nickName = nickName;
		this.userName = name;
	}
	
	
	protected String secure(String param) {
		return "some"+param;
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
		return this.userPass.substring(3);
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
	
}