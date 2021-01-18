package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CartItemId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "cart_id")
	private int cartId;

	@Column(name = "item_id")
    private int itemId;

    public CartItemId() {
		super();
	}

	public CartItemId(int cartId, int itemId) {
        this.cartId = cartId;
        this.itemId = itemId;
    }



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public int getCartId() {
		return cartId;
	}



	public int getItemId() {
		return itemId;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cartId;
		result = prime * result + itemId;
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
		CartItemId other = (CartItemId) obj;
		if (cartId != other.cartId)
			return false;
		if (itemId != other.itemId)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "CartItemId [cartId=" + cartId + ", itemId=" + itemId + "]";
	}


}
