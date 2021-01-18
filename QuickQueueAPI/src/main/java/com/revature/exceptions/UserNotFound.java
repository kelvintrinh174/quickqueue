package com.revature.exceptions;

public class UserNotFound extends AbstractHttpException {
       public UserNotFound() {
    	   super("Wrong user or password", 404);
       }
}
