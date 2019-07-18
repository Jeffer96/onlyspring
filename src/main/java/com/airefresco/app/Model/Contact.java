package com.airefresco.app.Model;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="contacts")
@Data
public class Contact {
	
	@Id
	private int id;
	@NotBlank
	private String contactName;
	@NotBlank
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="customer_nit")
	@JsonIgnore
	private Customer customerNit;
	//@OneToMany(mappedBy="owner")
	//private ArrayList<Tel> tels;
	private String email;
	
	public Contact() {
		
	}
	
	public Contact(int id, String contactName, Customer customerNit, String email) {
		this.id = id;
		this.contactName = contactName;
		this.customerNit = customerNit;
		this.email = email;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId( int id) {
		this.id = id;
	}
	
	public String getContactName() {
		return this.contactName;
	}
	
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	public void setCustomerNit(Customer customerNit) {
		this.customerNit = customerNit;
	}
	
	public Customer getCustomerNit() {
		return this.customerNit;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
