package com.revature.controllers;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.enums.UserRole;
import com.revature.models.User;
import com.revature.services.ICartService;
import com.revature.services.IUserService;

@RestController
@RequestMapping("/shoppers")
public class ShopperController {
	
	private IUserService us;
	
	@Autowired
	public ShopperController(IUserService us) {
		this.us = us;
	}
	
	@PostMapping("/login") 
	public ResponseEntity<User> logIn(@RequestBody User u) throws NoSuchAlgorithmException{
		return new ResponseEntity<User>(us.login(u.getUsername(), u.getPassword()), HttpStatus.OK);
	}
	
	@PostMapping("/register") 
	public ResponseEntity<User> register(@RequestBody User u) throws NoSuchAlgorithmException{
		u.setUserRole(UserRole.SHOPPER);
		return new ResponseEntity<User>(us.register(u), HttpStatus.OK);
	}
	
}
