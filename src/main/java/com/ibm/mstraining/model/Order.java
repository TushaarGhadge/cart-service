package com.ibm.mstraining.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Order {
	@Id
	@GeneratedValue
	private long id;
	private Date orderDate;
	private Date expectedDeliveyDate;
	private String clientId;
	private ShoppingCart cart;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getExpectedDeliveyDate() {
		return expectedDeliveyDate;
	}
	public void setExpectedDeliveyDate(Date expectedDeliveyDate) {
		this.expectedDeliveyDate = expectedDeliveyDate;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public ShoppingCart getCart() {
		return cart;
	}
	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}
	
}
