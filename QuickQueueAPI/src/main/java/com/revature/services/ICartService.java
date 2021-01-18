package com.revature.services;

import java.util.List;

import com.revature.enums.CartStatus;
import com.revature.models.Cart;
import com.revature.models.CartItem;
import com.revature.models.CartItemId;

public interface ICartService {

	Cart addItem(int itemId, int userId, int quantity);

	Cart findActiveCart(int userId);

	List<Cart> findCartByStatus(int userId, CartStatus status);
	
}
