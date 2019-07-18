package com.airefresco.app.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(name="tels")
public class Tel {
	@NotBlank
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_owner")
	private Contact contactOwner;
	
	@Id
	private int tel;
	
	public Tel() {
		
	}
	
	public Tel(Contact owner, String numTel) {
		this.owner = owner;
		this.numTel = numTel;
	}
	
	public void setOwner(Contact owner) {
		this.owner = owner;
	}
	
	public Contact getOwner() {
		return this.owner;
	}
	
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	
	public String getNumTel() {
		return this.numTel;
	}

}
