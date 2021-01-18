package com.revature.exceptions;

public class CartNotFoundException extends AbstractHttpException {

	public CartNotFoundException() {
 	   super("Cart not found", 404);
	}

}
