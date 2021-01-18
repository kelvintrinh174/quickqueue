package com.revature.exceptions;

public class OrderNotFoundException extends AbstractHttpException {

	public OrderNotFoundException() {
	 	   super("Order not found", 404);
		}
	
}
