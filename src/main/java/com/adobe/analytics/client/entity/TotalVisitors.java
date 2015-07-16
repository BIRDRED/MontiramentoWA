package com.adobe.analytics.client.entity;

import java.math.BigDecimal;

public class TotalVisitors {
	
	private BigDecimal totalB2W;
	private BigDecimal totalACOM;
	
	public TotalVisitors() {
		super();
	}

	public BigDecimal getTotalB2W() {
		return totalB2W;
	}

	public void setTotalB2W(BigDecimal totalB2W) {
		this.totalB2W = totalB2W;
	}

	public BigDecimal getTotalACOM() {
		return totalACOM;
	}

	public void setTotalACOM(BigDecimal totalACOM) {
		this.totalACOM = totalACOM;
	}

	public TotalVisitors(BigDecimal totalB2W, BigDecimal totalACOM) {
		super();
		this.totalB2W = totalB2W;
		this.totalACOM = totalACOM;
	}
	
}
