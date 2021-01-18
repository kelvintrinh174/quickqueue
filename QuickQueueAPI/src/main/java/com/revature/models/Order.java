package com.revature.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.revature.enums.OrderStatus;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;
	
	@Column(name = "order_net_amount")
	private BigDecimal orderNetAmount;

	@Column(name = "order_gross_amount")
	private BigDecimal order_gross_amount;
	
	@Column(name = "order_tax_amount")
	private BigDecimal order_tax_amount;

	@Column(name = "order_submitted")
	private Timestamp orderSubmitted;

	@Column(name = "order_resolved")
	private Timestamp orderResolved;

	@Column(name = "order_additional_instructions")
	private String orderAdditionalInstructions;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_customer")
	private User orderCustomer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_shopper")
	private User orderShopper;

    @Enumerated(EnumType.STRING)
	@Column(name = "order_status")
	@Type(type = "com.revature.enums.PosgreSQLEnumtype")
	private OrderStatus orderStatus;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "cart_id")
	private Cart orderCart;

	public Order() {
		super();
	}

	public Order(int orderId, BigDecimal orderNetAmount, BigDecimal order_gross_amount, BigDecimal order_tax_amount,
			Timestamp orderSubmitted, Timestamp orderResolved, String orderAdditionalInstructions, User orderCustomer,
			User orderShopper, OrderStatus orderStatus, Cart orderCart) {
		super();
		this.orderId = orderId;
		this.orderNetAmount = orderNetAmount;
		this.order_gross_amount = order_gross_amount;
		this.order_tax_amount = order_tax_amount;
		this.orderSubmitted = orderSubmitted;
		this.orderResolved = orderResolved;
		this.orderAdditionalInstructions = orderAdditionalInstructions;
		this.orderCustomer = orderCustomer;
		this.orderShopper = orderShopper;
		this.orderStatus = orderStatus;
		this.orderCart = orderCart;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getOrderNetAmount() {
		return orderNetAmount;
	}

	public void setOrderNetAmount(BigDecimal orderNetAmount) {
		this.orderNetAmount = orderNetAmount;
	}

	public BigDecimal getOrder_gross_amount() {
		return order_gross_amount;
	}

	public void setOrder_gross_amount(BigDecimal order_gross_amount) {
		this.order_gross_amount = order_gross_amount;
	}

	public BigDecimal getOrder_tax_amount() {
		return order_tax_amount;
	}

	public void setOrder_tax_amount(BigDecimal order_tax_amount) {
		this.order_tax_amount = order_tax_amount;
	}

	public Timestamp getOrderSubmitted() {
		return orderSubmitted;
	}

	public void setOrderSubmitted(Timestamp orderSubmitted) {
		this.orderSubmitted = orderSubmitted;
	}

	public Timestamp getOrderResolved() {
		return orderResolved;
	}

	public void setOrderResolved(Timestamp orderResolved) {
		this.orderResolved = orderResolved;
	}

	public String getOrderAdditionalInstructions() {
		return orderAdditionalInstructions;
	}

	public void setOrderAdditionalInstructions(String orderAdditionalInstructions) {
		this.orderAdditionalInstructions = orderAdditionalInstructions;
	}

	public User getOrderCustomer() {
		return orderCustomer;
	}

	public void setOrderCustomer(User orderCustomer) {
		this.orderCustomer = orderCustomer;
	}

	public User getOrderShopper() {
		return orderShopper;
	}

	public void setOrderShopper(User orderShopper) {
		this.orderShopper = orderShopper;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Cart getOrderCart() {
		return orderCart;
	}

	public void setOrderCart(Cart orderCart) {
		this.orderCart = orderCart;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderAdditionalInstructions == null) ? 0 : orderAdditionalInstructions.hashCode());
		result = prime * result + ((orderCart == null) ? 0 : orderCart.hashCode());
		result = prime * result + ((orderCustomer == null) ? 0 : orderCustomer.hashCode());
		result = prime * result + ((orderNetAmount == null) ? 0 : orderNetAmount.hashCode());
		result = prime * result + ((orderResolved == null) ? 0 : orderResolved.hashCode());
		result = prime * result + ((orderShopper == null) ? 0 : orderShopper.hashCode());
		result = prime * result + ((orderStatus == null) ? 0 : orderStatus.hashCode());
		result = prime * result + ((orderSubmitted == null) ? 0 : orderSubmitted.hashCode());
		result = prime * result + ((order_gross_amount == null) ? 0 : order_gross_amount.hashCode());
		result = prime * result + ((order_tax_amount == null) ? 0 : order_tax_amount.hashCode());
		result = prime * result + orderId;
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
		Order other = (Order) obj;
		if (orderAdditionalInstructions == null) {
			if (other.orderAdditionalInstructions != null)
				return false;
		} else if (!orderAdditionalInstructions.equals(other.orderAdditionalInstructions))
			return false;
		if (orderCart == null) {
			if (other.orderCart != null)
				return false;
		} else if (!orderCart.equals(other.orderCart))
			return false;
		if (orderCustomer == null) {
			if (other.orderCustomer != null)
				return false;
		} else if (!orderCustomer.equals(other.orderCustomer))
			return false;
		if (orderNetAmount == null) {
			if (other.orderNetAmount != null)
				return false;
		} else if (!orderNetAmount.equals(other.orderNetAmount))
			return false;
		if (orderResolved == null) {
			if (other.orderResolved != null)
				return false;
		} else if (!orderResolved.equals(other.orderResolved))
			return false;
		if (orderShopper == null) {
			if (other.orderShopper != null)
				return false;
		} else if (!orderShopper.equals(other.orderShopper))
			return false;
		if (orderStatus != other.orderStatus)
			return false;
		if (orderSubmitted == null) {
			if (other.orderSubmitted != null)
				return false;
		} else if (!orderSubmitted.equals(other.orderSubmitted))
			return false;
		if (order_gross_amount == null) {
			if (other.order_gross_amount != null)
				return false;
		} else if (!order_gross_amount.equals(other.order_gross_amount))
			return false;
		if (order_tax_amount == null) {
			if (other.order_tax_amount != null)
				return false;
		} else if (!order_tax_amount.equals(other.order_tax_amount))
			return false;
		if (orderId != other.orderId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderNetAmount=" + orderNetAmount + ", order_gross_amount="
				+ order_gross_amount + ", order_tax_amount=" + order_tax_amount + ", orderSubmitted=" + orderSubmitted
				+ ", orderResolved=" + orderResolved + ", orderAdditionalInstructions=" + orderAdditionalInstructions
				+ ", orderCustomer=" + orderCustomer + ", orderShopper=" + orderShopper + ", orderStatus=" + orderStatus
				+ ", orderCart=" + orderCart + "]";
	}
	
	
	
}
