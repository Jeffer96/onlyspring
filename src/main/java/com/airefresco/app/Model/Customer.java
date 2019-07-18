package com.airefresco.app.Model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name="customers")
@Data
public class Customer {
	
	@Id 
	private String nit;
	@NotBlank
	private String customerName;
	@NotBlank
	private boolean statusActive;
	@NotBlank
	private Date startContract;
	private Date endContract;
	@OneToMany(mappedBy="customerNit")
	private ArrayList<Contact> contacts;
	
	public Customer() {
		
	}
	
	public Customer(String nit, String customerName, boolean active, Date startContract) {
		this.nit = nit;
		this.customerName = customerName;
		this.statusActive = active;
		this.startContract = startContract;
	}
	
	public void setNit(String nit) {
		this.nit = nit;
	}
	
	public String getNit() {
		return this.nit;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getCustomerName() {
		return this.customerName;
	}
	
	public void setStatusActive(boolean active) {
		this.statusActive = active;
	}
	
	public boolean getStatusActive() {
		return this.statusActive;
	}
	
	public void setStartContract(Date startContract) {
		this.startContract = startContract;
	}
	
	public Date getStartContract() {
		return this.startContract;
	}

	public Date getEndContract() {
		return endContract;
	}

	public void setEndContract(Date endContract) {
		this.endContract = endContract;
	}
	
	public ArrayList<Contact> getContacts(){
		return this.contacts;
	}
	
	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
}
