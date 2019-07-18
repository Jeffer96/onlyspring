package com.airefresco.app.service;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.airefresco.app.Model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	@Query(value = "SELECT * FROM customers WHERE nit=?1", nativeQuery = true)
	Customer getCustomerByNit(String nit);
	
	@Query(value = "SELECT * FROM customers", nativeQuery = true)
	ArrayList<Customer> getAllCustomers();

}
