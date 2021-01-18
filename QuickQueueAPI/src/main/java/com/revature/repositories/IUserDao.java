package com.revature.repositories;

import java.sql.SQLException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.exceptions.UserNotFound;
import com.revature.models.User;

public interface IUserDao extends JpaRepository<User, Integer> {
	
	 @Query("select u FROM User u where u.username = ?1 and u.password = ?2")	
	 public User findUserbyUsernameAndPassword(String username, String password) throws SQLException;
}
