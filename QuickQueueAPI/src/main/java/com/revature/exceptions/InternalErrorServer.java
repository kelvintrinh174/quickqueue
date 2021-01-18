package com.revature.exceptions;

public class InternalErrorServer extends AbstractHttpException {

	public InternalErrorServer() {
		super("Internal Server Error", 500);
		
	}

}
