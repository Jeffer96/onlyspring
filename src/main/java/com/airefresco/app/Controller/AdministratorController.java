package com.airefresco.app.Controller;

import static com.airefresco.app.Security.Constants.HEADER_AUTHORIZACION_KEY;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.airefresco.app.Components.TokenProvider;
import com.airefresco.app.Model.Customer;
import com.airefresco.app.Model.User;
import com.airefresco.app.service.CustomerRepository;
import com.airefresco.app.service.UserRepository;

@RestController
@RequestMapping("/administrador")
public class AdministratorController{
	
	@Autowired
	UserRepository ur;
	
	@Autowired
	CustomerRepository cr;
	
		
	@PostMapping(value = "/addUser")
	public String addUser(@RequestBody User user) {
		try {
			ur.save(user);
			return "202";
		}catch (DataAccessException ex){
			return "401";
		}
	}
		
	@RequestMapping("/getAllUsers")
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseBody
	public Collection<User> getAllUsers(){
		try {
			return ur.getAllUser();
		}catch(DataAccessException ex) {
			return new ArrayList<User>();
		}
	}
	
	@PostMapping("/updateName")
	public String updatename(@RequestParam int id, @RequestParam String userName) {
		try {
			ur.updateUserName(userName,id);
			return "202";
		}catch(DataAccessException ex) {
			return ex+"";
		}
	}
	
	@PostMapping("/updateNick")
	public String updatenick(@RequestParam int id, @RequestParam String nickName, HttpServletRequest rq) {
		try {
			if (isNotCurrentUser(rq,id)) {
				ur.updateUserNick(nickName, id);
				return "202";
			}else {
				return "300";
			}
		}catch(DataAccessException ex) {
			return ex+"";
		}
	}
	
	@PostMapping("/updateId")
	public String updateId(@RequestParam int id, @RequestParam int newId, HttpServletRequest rq) {
		try {		
			if (isNotCurrentUser(rq,id)) {
				ur.updateUserId(newId, id);
				return "202";
			}else {
				return "300";
			}
		}catch(DataAccessException ex) {
			return ex+"";
		}
	}
	
	@PostMapping("/updateRol")
	public String updaterol(@RequestParam int id, @RequestParam String roleName, HttpServletRequest rq) {
		try {
			if (isNotCurrentUser(rq,id)) {
				ur.updateAuthority(roleName, id);
				return "202";
			}else {
				return "300";
			}
		}catch(DataAccessException ex) {
			return ex+"";
		}
	}
	
	@PostMapping("/updateEmail")
	public String updateemail(@RequestParam int id, @RequestParam String email) {
		try {
			ur.updateUserEmail(email, id);
			return "202";
		}catch(DataAccessException ex) {
			return ex+"";
		}
	}
	
	private final boolean isNotCurrentUser(HttpServletRequest request, int id) {
		String bearer = request.getHeader(HEADER_AUTHORIZACION_KEY);
		return TokenProvider.getUserId(bearer.substring(7, bearer.length()))!=id;
	}
	
	@GetMapping("/getCustomers")
	public ArrayList<Customer> getAllCustomers(){
		try {
			return cr.getAllCustomers();
		}catch(DataAccessException ex) {
			return new ArrayList<Customer>();
		}
	}
}