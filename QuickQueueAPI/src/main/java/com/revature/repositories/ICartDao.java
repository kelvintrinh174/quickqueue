package com.revature.repositories;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.enums.CartStatus;
import com.revature.models.Cart;

public interface ICartDao extends JpaRepository<Cart, Integer> {

	 @Query("select c FROM Cart c where (c.cartOwner.userId = ?1 and c.cartStatus = 'ACTIVE')")	
	 public Cart findActiveCart(int userId) throws SQLException;

	 //com.revature.enums.CartStatus
	 @Query("select c FROM Cart c where (c.cartOwner.userId = ?1 and cast( c.cartStatus as string) = ?2)")	
	 public List<Cart> findCartsByStatus(int userId, String status) throws SQLException;
	 
	 

}
