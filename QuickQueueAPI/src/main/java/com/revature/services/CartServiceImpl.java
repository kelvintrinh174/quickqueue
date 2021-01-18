package com.revature.services;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.mapping.Constraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.enums.CartStatus;
import com.revature.exceptions.CartNotFoundException;
import com.revature.models.Cart;
import com.revature.models.CartItem;
import com.revature.models.CartItemId;
import com.revature.models.Item;
import com.revature.repositories.ICartDao;
import com.revature.repositories.ICartItemDao;
import com.revature.repositories.IItemDao;
import com.revature.repositories.IUserDao;

@Service
public class CartServiceImpl implements ICartService {

	ICartDao cartDao;
    IUserDao userDao;
	IItemDao itemDao;
	ICartItemDao cartItemDao;
	

	@Autowired
	public CartServiceImpl(ICartDao cartDao, IItemDao itemDao, ICartItemDao cartItemDao,IUserDao userDao) {
		this.cartDao = cartDao;
		this.itemDao = itemDao;
		this.cartItemDao = cartItemDao;
		this.userDao = userDao;
	}

	@Override
	@Transactional
	public Cart addItem(int itemId, int userId, int quantity) {
		//check if item exists if not make a new one
		Item i;
		if (!itemDao.existsById(itemId)) {
			i = itemDao.save(new Item(itemId));
		}else {
			i = itemDao.findById(itemId).get();
		}
		Cart c;
		try {
			System.out.println("userId: " + userId);
			c = cartDao.findActiveCart(userId);
			if(c==null) {
				System.out.println(c);
				//User u = 
				c = cartDao.save(new Cart(0,CartStatus.ACTIVE,userDao.findById(userId).get(),null,null));
             
			}
		} catch (SQLException e) {
			throw new CartNotFoundException();
		}
		//check if line exists if not a make new one
		if (!cartItemDao.existsById(new CartItemId(c.getCartId(), itemId))) {
			System.out.println("overwritting quantity");
			cartItemDao.save(new CartItem(quantity, c, i));
		}
		else {
			System.out.println("adding to item not overwritting");
			CartItem ci = cartItemDao.getOne(new CartItemId(c.getCartId(), itemId));
			ci.setQuantity(ci.getQuantity() + quantity);
			cartItemDao.save(ci);
		}
		return cartDao.findById(c.getCartId()).get();
	}

	@Override
	@Transactional
	public Cart findActiveCart(int userId) {
		try {
			return cartDao.findActiveCart(userId);
		} catch (SQLException e) {
			throw new CartNotFoundException();
		}
	}

	@Override
	public List<Cart> findCartByStatus(int userId, CartStatus status) {
		try {
			return cartDao.findCartsByStatus(userId, status.toString());
		} catch (SQLException e) {
			throw new CartNotFoundException();
		}
	}

}
