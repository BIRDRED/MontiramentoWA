package com.adobe.analytics.client.entity;

import java.math.BigDecimal;

public class BounceRate {
	String hour;
	BigDecimal bouncerate;
	
	public BounceRate() {
		super();
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}
	public BigDecimal getBouncerate() {
		return bouncerate;
	}

	public void setBouncerate(BigDecimal bouncerate) {
		this.bouncerate = bouncerate;
	}

	public BounceRate(String hour, BigDecimal bouncerate) {
		super();
		this.hour = hour;
		this.bouncerate = bouncerate;
	}

	@Override
	public String toString() {
		return "BounceRate [hour=" + hour + ", bouncerate=" + bouncerate + "]";
	}
	
	
	
}
