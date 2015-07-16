package com.adobe.analytics.client.managed;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GeneralBean {
	public String dateAtual;
	public String lastYear;
	public String refreshDate;
	public String refreshHour;
	
	public String getDateAtual() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(new Date()).toString();
		date =  "'" + date +"'"; 
		return date;
	}



	public void setDateAtual(String dateAtual) {
		this.dateAtual = dateAtual;
	}



	public String getLastYear() {
		Calendar c = Calendar.getInstance();
			Integer ano = c.get(Calendar.YEAR) - 1 ;
					 c.set(Calendar.YEAR, ano );
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String date = sdf.format(c.getTime()).toString();
			date =  "'" + date +"'"; 
		return date;
	}



	public void setLastYear(String lastYear) {
		this.lastYear = lastYear;
	}

	public static void main(String[] args) {
		GeneralBean gb = new GeneralBean();
		System.out.println(gb.getDateAtual());
		
	}



	public String getRefreshDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(new Date()).toString();
		return date;
	}



	public void setRefreshDate(String refreshDate) {
		this.refreshDate = refreshDate;
	}



	public String getRefreshHour() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String date = sdf.format(new Date()).toString();
		return date;
	}



	public void setRefreshHour(String refreshHour) {
		this.refreshHour = refreshHour;
	}
	
	
}
