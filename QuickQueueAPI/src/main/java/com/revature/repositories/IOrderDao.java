package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.models.Order;

public interface IOrderDao extends JpaRepository<Order, Integer>{

	@Query("select o FROM Order o where (o.orderCustomer.userId = ?1 and cast (o.orderStatus as string) = ?2)")
	public List<Order> findOrderByStatus(int customerId, String status);
	
}
