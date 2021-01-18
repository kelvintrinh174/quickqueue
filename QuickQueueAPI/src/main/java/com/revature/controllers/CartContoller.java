package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.revature.enums.CartStatus;
import com.revature.exceptions.UnauthenticatedException;
import com.revature.models.Cart;
import com.revature.models.User;
import com.revature.services.ICartService;
import com.revature.services.IUserService;

@RestController
@RequestMapping("/cart")
public class CartContoller {
	private IUserService us;
	private ICartService cartService;
	
	@Autowired
	public CartContoller(IUserService us, ICartService cartService) {
		this.us = us;
		this.cartService = cartService;
	}
	
		
	/**
	 * when a user sends a get request for a cart it returns the active cart
	 * 
	 * @return the active cart
	 */
	@GetMapping("/active/{userId}")
	public ResponseEntity<Cart> getActiveCart(@PathVariable int userId){
		User u = us.getUser(userId);
		if(u == null) {
			throw new UnauthenticatedException();
		}
		Cart c = cartService.findActiveCart(u.getUserId());
		return new ResponseEntity<Cart>(cartService.findActiveCart(u.getUserId()), HttpStatus.OK);

	}
	
	/**
	 * when a user sends a get request for a cart it returns the active cart
	 * 
	 * @return the active cart
	 */
	@GetMapping("/submitted/{userId}")
	public ResponseEntity<List<Cart>> getSubmittedCarts(@PathVariable int userId){
		User u = us.getUser(userId);
		if(u == null) {
			throw new UnauthenticatedException();
		}
		return new ResponseEntity<List<Cart>>(cartService.findCartByStatus(u.getUserId(), CartStatus.SUBMITTED), HttpStatus.OK);

	}
	

}
