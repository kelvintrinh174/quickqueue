package com.revature.services;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.enums.CartStatus;
import com.revature.enums.OrderStatus;
import com.revature.exceptions.OrderNotFoundException;
import com.revature.models.Cart;
import com.revature.models.Order;
import com.revature.models.User;
import com.revature.repositories.ICartDao;
import com.revature.repositories.IOrderDao;
import com.revature.repositories.IUserDao;

@Service
public class OrderServiceImpl implements IOrderService{

	IOrderDao orderDao;
	ICartDao cartDao;
	IUserDao userDao;
	
	@Autowired
	public OrderServiceImpl(IOrderDao orderDao, ICartDao cartDao, IUserDao userDao) {

		this.orderDao = orderDao;
		this.cartDao = cartDao;
		this.userDao = userDao;
		
	}

	@Override
	@Transactional
	public Order addOrder(Order o) {
		
		return orderDao.save(o);
		
	}

	@Override
	public List<Order> findOrderByStatus(User orderCustomer, OrderStatus status) {		
		
		return orderDao.findOrderByStatus(orderCustomer.getUserId(), status.toString());

	}

	@Override
	public Order updateOrderStatus(Order o) {
		return orderDao.save(o);
		
	}
	
	@Override
	@Transactional
	public Order submitOrder(Order o, User u) {
		o.setOrderId(0);
		o.setOrderStatus(OrderStatus.ACTIVE);
		o.getOrderCart().setCartStatus(CartStatus.SUBMITTED);
		
		o.getOrderCart().setCartOwner(u);
		//System.out.println()
		cartDao.save(o.getOrderCart());
		cartDao.save(new Cart(0, CartStatus.ACTIVE, o.getOrderCustomer(), null, null));
		
		return orderDao.save(o);
		
	}

}
