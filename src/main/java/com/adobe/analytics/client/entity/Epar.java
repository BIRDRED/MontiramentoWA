package com.adobe.analytics.client.entity;

import java.math.BigDecimal;

public class Epar {
	private String sigla;
	private String name;
	private BigDecimal visitsEpar;
	private BigDecimal ordersEpar;
	
	

	public Epar(String sigla, String name, BigDecimal visitsEpar,
			BigDecimal ordersEpar) {
		super();
		this.sigla = sigla;
		this.name = name;
		this.visitsEpar = visitsEpar;
		this.ordersEpar = ordersEpar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getVisitsEpar() {
		return visitsEpar;
	}

	public void setVisitsEpar(BigDecimal visitsEpar) {
		this.visitsEpar = visitsEpar;
	}

	public BigDecimal getOrdersEpar() {
		return ordersEpar;
	}

	public void setOrdersEpar(BigDecimal ordersEpar) {
		this.ordersEpar = ordersEpar;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public String toString() {
		return "Epar [sigla=" + sigla + ", name=" + name + ", visitsEpar="
				+ visitsEpar + ", ordersEpar=" + ordersEpar + "]";
	}
	
	
	
	
}
