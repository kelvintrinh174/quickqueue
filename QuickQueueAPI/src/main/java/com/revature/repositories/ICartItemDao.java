package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.CartItem;
import com.revature.models.CartItemId;

public interface ICartItemDao extends JpaRepository<CartItem, CartItemId> {

}
