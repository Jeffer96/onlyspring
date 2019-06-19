package com.airefresco.app.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/portal")
public class UserController {
	
	@GetMapping("/customers")
	@PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
	public String getAllCustomers() {
		return "list customers";
	}
	
	@GetMapping("/customer")
	@PreAuthorize("hasAnyAuthority('ADMIN','EMPLOYEE')")
	public String getCustomerById(@RequestParam String nit) {
		return "this is a customer";
	}
	
	@PostMapping("/set/userNick")
	@PreAuthorize("hasAnyAuthority('EMPLOYEE','USER')")
	public String setUserNickName(@RequestParam String newNick) {
		return "this is a response that you have EMPOYEE or USER rol";
	}
	
}
