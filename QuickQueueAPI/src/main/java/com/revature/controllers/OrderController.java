package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.revature.enums.OrderStatus;
import com.revature.exceptions.OrderNotFoundException;
import com.revature.exceptions.UnauthenticatedException;
import com.revature.exceptions.UrlMismatchException;
import com.revature.models.Order;
import com.revature.models.User;
import com.revature.services.IOrderService;
import com.revature.services.IUserService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private IOrderService os;
	private IUserService us;
	
	@Autowired
	public OrderController(IOrderService os, IUserService us) {
		this.os = os;
		this.us = us;
		
	}
	
//	@PostMapping("/submit/{userId}")
//	public ResponseEntity<Order> submitNewOrder(@RequestBody Order o, @PathVariable int userId) {
//		
//		User u = us.getUser(userId);
//		if(u == null) {
//			throw new UnauthenticatedException();
//		}
//		if (!o.getOrderCustomer().equals(u)) {
//				
//			throw new UrlMismatchException();
//			
//		}
//		return new ResponseEntity<Order>(os.addOrder(o), HttpStatus.OK);
//		
//	}
	
	@GetMapping("/history/pending/{userId}")
	@Transactional
	public ResponseEntity<List<Order>> getPendingOrders(@PathVariable int userId) {
		
		User u = us.getUser(userId);
		if(u == null) {
			throw new UnauthenticatedException();
		}
		return new ResponseEntity<List<Order>>(os.findOrderByStatus(u, OrderStatus.PENDING), HttpStatus.OK);
		
	}
	
	@GetMapping("/history/active/{userId}")
	@Transactional
	public ResponseEntity<List<Order>> getActiveOrders(@PathVariable int userId) {
		
		User u = us.getUser(userId);

		if(u == null) {
			throw new UnauthenticatedException();
		}
		return new ResponseEntity<List<Order>>(os.findOrderByStatus(u, OrderStatus.ACTIVE), HttpStatus.OK);
		
	}
	
	@GetMapping("/history/closed/{userId}")
	@Transactional
	public ResponseEntity<List<Order>> getClosedOrders(@PathVariable int userId) {
		
		User u = us.getUser(userId);
		if(u == null) {
			throw new UnauthenticatedException();
		}
		return new ResponseEntity<List<Order>>(os.findOrderByStatus(u, OrderStatus.CLOSED), HttpStatus.OK);
		
	}
	
	@PostMapping("/{orderId}/{userId}")
	public ResponseEntity<Order> updateOrderStatus(@RequestBody Order o, @PathVariable int orderId, @PathVariable int userId) {
		
		User u = us.getUser(userId);
		if(u == null) {
			throw new UnauthenticatedException();
		}else if (o.getOrderId() != orderId) {
			throw new UrlMismatchException();
		} 
		
		return new ResponseEntity<Order>(os.updateOrderStatus(o), HttpStatus.OK);
		
	}
	
	@PostMapping("/submit/{userId}")
	public ResponseEntity<Order> submitOrder(@RequestBody Order o, @PathVariable int userId) {
		
		User u = us.getUser(userId);
		if(u == null) {
			throw new UnauthenticatedException();
		} else if (o.getOrderCustomer().getUserId() != u.getUserId()) {
			throw new UrlMismatchException();
		}
		
		o = os.submitOrder(o, u);
		o.getOrderCustomer().setPassword(null);
		return new ResponseEntity<Order>(o, HttpStatus.OK);
		
	}
	
}
