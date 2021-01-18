package com.revature.exceptions;

public class UrlMismatchException extends AbstractHttpException {

	public UrlMismatchException() {
	 	   super("Something went terribly wrong", 400);
		}
	
}
