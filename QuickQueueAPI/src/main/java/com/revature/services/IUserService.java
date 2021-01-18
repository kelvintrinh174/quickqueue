package com.revature.services;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.revature.exceptions.UserNotFound;
import com.revature.models.User;

public interface IUserService {

	public User login(String username, String password) throws NoSuchAlgorithmException;
	public User register(User u) throws NoSuchAlgorithmException;
	public User getUser(int userId);
}
