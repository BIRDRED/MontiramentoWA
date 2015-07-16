package com.adobe.analytics.client.entity;

import java.math.BigDecimal;

public class AbandonCart {
	String hour;
	BigDecimal abandoncart;
	Double cart;
	Double orders;
	
	public AbandonCart() {
		super();
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public BigDecimal getAbandoncart() {
		return abandoncart;
	}

	public void setAbandoncart(BigDecimal abandoncart) {
		this.abandoncart = abandoncart;
	}

	public Double getCart() {
		return cart;
	}

	public void setCart(Double cart) {
		this.cart = cart;
	}

	public Double getOrders() {
		return orders;
	}

	public void setOrders(Double orders) {
		this.orders = orders;
	}

	public AbandonCart(String hour, BigDecimal abandoncart, Double cart,
			Double orders) {
		super();
		this.hour = hour;
		this.abandoncart = abandoncart;
		this.cart = cart;
		this.orders = orders;
	}
	
	@Override
	public String toString() {
		return "AbandonCart [hour=" + hour + ", abandoncart=" + abandoncart
				+ ", cart=" + cart + ", orders=" + orders + "]";
	}
	
	
}
