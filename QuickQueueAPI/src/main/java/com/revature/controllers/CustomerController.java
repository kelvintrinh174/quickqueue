package com.revature.controllers;

import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.revature.enums.UserRole;
import com.revature.exceptions.UnauthenticatedException;
import com.revature.models.Cart;
import com.revature.models.User;
import com.revature.services.ICartService;
import com.revature.services.IUserService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	private IUserService us;
	private ICartService cartService;
	
	@Autowired
	public CustomerController(IUserService us, ICartService cartService) {
		this.us = us;
		this.cartService = cartService;
	}
	
	
	@PostMapping("/register") 
	public ResponseEntity<User> register(@RequestBody User u) throws NoSuchAlgorithmException{
		u.setUserRole(UserRole.CUSTOMER);
		return new ResponseEntity<User>(us.register(u), HttpStatus.OK);
	}
		
	@PostMapping("/login") // curly braces denote it as a path variable -> when can extract the data
	public ResponseEntity<User> logIn(@RequestBody User u) throws NoSuchAlgorithmException{
		u = us.login(u.getUsername(), u.getPassword());
		return new ResponseEntity<User>((u), HttpStatus.OK);

	}
	
	@PostMapping("/addItem/{itemid}/{quantity}/{userId}")
	public ResponseEntity<Cart> addItem(@PathVariable int itemid, @PathVariable int quantity, @PathVariable int userId){
		//TODO check that user is logged in
		User u = us.getUser(userId);
		if(u == null) {
			throw new UnauthenticatedException();
		}
		int userid = u.getUserId();
		return new ResponseEntity<Cart>(cartService.addItem(itemid, userid, quantity), HttpStatus.OK);

	}

}
