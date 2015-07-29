package com.adobe.analytics.client.managed;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.adobe.analytics.client.domain.ReportData;
import com.adobe.analytics.client.entity.AbandonCart;
import com.adobe.analytics.client.entity.BounceRate;
import com.adobe.analytics.client.entity.Transaction;
import com.adobe.analytics.client.entity.Visitors;
import com.adobe.analytics.client.report.DashboardReport;

@ManagedBean
@SessionScoped
public class DashboardACOMLYBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//Variaveis de Listas 
	List<BounceRate> bouncerate;
	List<Visitors> visitors;
	List<AbandonCart> abandoncart;
	List<Transaction> transactions;

	//Variaveis de Somas dos Totais
		BigDecimal totalVisitorsACOM;
		BigDecimal totalSumAbandonCart;
		BigDecimal totalSumTransaction;
		BigDecimal totalSumBounce;
	
	//Definição de Data
	 
	public DashboardACOMLYBean() {
		 DashboardReport dbr = new DashboardReport();
		 GeneralBean gb = new GeneralBean();
		 				//Inicialização de Variaveis
			 				abandoncart = new ArrayList<AbandonCart>();
			 				bouncerate = new ArrayList<BounceRate>();
			 				visitors =  new ArrayList<Visitors>();
			 				transactions =  new ArrayList<Transaction>();
			 				
			 			//Inicialização de Soma 
				 			totalVisitorsACOM = BigDecimal.ZERO;
			 				totalSumAbandonCart = BigDecimal.ZERO;
			 				totalSumTransaction = BigDecimal.ZERO;
			 				totalSumBounce = BigDecimal.ZERO;
		 				
			 try {
				 Calendar calendar = Calendar.getInstance();
				 Integer ano = calendar.get(Calendar.YEAR) - 1;
				 			    calendar.set(Calendar.YEAR, ano);
				 			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				 String date = sdf.format(calendar.getTime()).toString();
				for(ReportData rd : dbr.getDashboardACOM("b2w-acom", date).getReport().getData()){
					//Taxas de Relatório
						BigDecimal abandonCart = BigDecimal.ZERO;
						BigDecimal visitorsACOM = BigDecimal.ZERO;
						BigDecimal transactionpct = BigDecimal.ZERO;
						BigDecimal bounceratepct = BigDecimal.ZERO;
						
					//Faz a soma dos Variaveis
					 if(rd.getCounts().get(0)!=0 && rd.getCounts().get(1)!=0 && rd.getCounts().get(2)!=0 && rd.getCounts().get(3)!=0 &&  rd.getCounts().get(4)!=0){
						 	abandonCart = new BigDecimal((1 - (rd.getCounts().get(1) /rd.getCounts().get(2))) * 100);
						 	visitorsACOM = new BigDecimal(rd.getCounts().get(0));
						 	transactionpct = new BigDecimal((rd.getCounts().get(1) /rd.getCounts().get(0)) * 100);
						 	bounceratepct = new BigDecimal((rd.getCounts().get(3) /rd.getCounts().get(4)) * 100);
						 	
					 }
					 String hour = rd.getHour().toString();
					 
					 //Instancia dos dados
					 AbandonCart abandon =  new AbandonCart(hour, abandonCart.setScale(2, BigDecimal.ROUND_UP), rd.getCounts().get(2), rd.getCounts().get(1));
					 BounceRate bounce = new BounceRate(hour, bounceratepct.setScale(2, BigDecimal.ROUND_UP));
					 Visitors vis = new Visitors(rd.getHour(), visitorsACOM);
					 Transaction trans = new Transaction(hour,visitorsACOM.doubleValue(), rd.getCounts().get(1), transactionpct.setScale(2, BigDecimal.ROUND_UP));
					
					//Soma dos Totais 
					 	totalSumAbandonCart = totalSumAbandonCart.add(abandon.getAbandoncart());
					 	if(rd.getHour() < gb.getCompareHour()){
					 		totalVisitorsACOM = totalVisitorsACOM.add(vis.getVisitors());
					 	}
					 	totalSumTransaction = totalSumTransaction.add(trans.getTransactionpct());
					 	totalSumBounce = totalSumBounce.add(bounce.getBouncerate());
		 				
		 			//Adiciona na Lista
					 	abandoncart.add(abandon);
					 	bouncerate.add(bounce);
					 	visitors.add(vis);
					 	transactions.add(trans);
					 	
				 }
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public List<BounceRate> getBouncerate() {
		return bouncerate;
	}

	public void setBouncerate(List<BounceRate> bouncerate) {
		this.bouncerate = bouncerate;
	}

	public List<Visitors> getVisitors() {
		return visitors;
	}

	public void setVisitors(List<Visitors> visitors) {
		this.visitors = visitors;
	}

	public List<AbandonCart> getAbandoncart() {
		return abandoncart;
	}

	public void setAbandoncart(List<AbandonCart> abandoncart) {
		this.abandoncart = abandoncart;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Integer getTotalVisitorsACOM() {
		BigDecimal tv = BigDecimal.ZERO;
		if(totalVisitorsACOM.toString().length() > 3){
			tv = new BigDecimal((totalVisitorsACOM.doubleValue()/1000));
		}
		return tv.intValue();
	}

	public void setTotalVisitorsACOM(BigDecimal totalVisitorsACOM) {
		this.totalVisitorsACOM = totalVisitorsACOM;
	}

	public BigDecimal getTotalSumAbandonCart() {
		return new BigDecimal(totalSumAbandonCart.doubleValue()/23).setScale(2, BigDecimal.ROUND_UP);
	}

	public void setTotalSumAbandonCart(BigDecimal totalSumAbandonCart) {
		this.totalSumAbandonCart = totalSumAbandonCart;
	}

	public BigDecimal getTotalSumTransaction() {
		return new BigDecimal(totalSumTransaction.doubleValue()/23).setScale(2, BigDecimal.ROUND_UP);
	}

	public void setTotalSumTransaction(BigDecimal totalSumTransaction) {
		this.totalSumTransaction = totalSumTransaction;
	}

	public BigDecimal getTotalSumBounce() {
		return new BigDecimal(totalSumBounce.doubleValue()/23).setScale(2, BigDecimal.ROUND_UP);
	}

	public void setTotalSumBounce(BigDecimal totalSumBounce) {
		this.totalSumBounce = totalSumBounce;
	}
	public static void main(String[] args) {
		DashboardACOMLYBean daly =  new DashboardACOMLYBean();
		System.out.println(daly.getTotalSumAbandonCart());
		System.out.println(daly.getTotalSumBounce());
		System.out.println(daly.getTotalSumTransaction());
		System.out.println(daly.getTotalVisitorsACOM());
	}
	
}

