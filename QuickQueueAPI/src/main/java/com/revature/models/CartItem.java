package com.revature.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "cart_item")
public class CartItem {

	@EmbeddedId
	private CartItemId cartItemId;

	private int quantity;

	@ManyToOne
	@MapsId("cartId")
	@JoinColumn(name = "cart_id")
	@JsonBackReference(value="cartItems")
	Cart cart;

	@ManyToOne
	@MapsId("itemId")
	@JoinColumn(name = "item_id")
	Item item;

	public CartItem() {
		super();
	}

	public CartItem(int quantity, Cart cart, Item item) {
		super();
//		this.cartItemId = cartItemId;
		this.quantity = quantity;
		this.cart = cart;
		this.item = item;
        this.cartItemId = new CartItemId(cart.getCartId(), item.getItemId());
	}
	
	

	public CartItemId getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(CartItemId cartItemId) {
		this.cartItemId = cartItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cart == null) ? 0 : cart.hashCode());
		result = prime * result + ((cartItemId == null) ? 0 : cartItemId.hashCode());
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (cart == null) {
			if (other.cart != null)
				return false;
		} else if (!cart.equals(other.cart))
			return false;
		if (cartItemId == null) {
			if (other.cartItemId != null)
				return false;
		} else if (!cartItemId.equals(other.cartItemId))
			return false;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CartItem [cartItemId=" + cartItemId + ", quantity=" + quantity + ", cart=" + cart + ", item=" + item
				+ "]";
	}

	

}
