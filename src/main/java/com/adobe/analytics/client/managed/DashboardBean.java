package com.adobe.analytics.client.managed;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.adobe.analytics.client.domain.ReportData;
import com.adobe.analytics.client.entity.AbandonCart;
import com.adobe.analytics.client.entity.BounceRate;
import com.adobe.analytics.client.entity.Transaction;
import com.adobe.analytics.client.entity.Visitors;
import com.adobe.analytics.client.report.AbandonCartReport;
import com.adobe.analytics.client.report.DashboardReport;

@ManagedBean
@SessionScoped
public class DashboardBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//Variaveis de Listas 
	List<BounceRate> bouncerate;
	List<Visitors> visitors;
	List<AbandonCart> abandoncart;
	List<Transaction> transactions;
	
	//Variaveis de Totais
	BigDecimal totalAbandonCart;
	BigDecimal totalTransaction;
	BigDecimal totalBounce;
	
	//Variaveis de Somas dos Totais
	BigDecimal totalVisitorsACOM;
	BigDecimal totalSumAbandonCart;
	BigDecimal totalSumTransaction;
	BigDecimal totalSumBounce;
	
	//Variaveis de Gráficos
	String chartabandoncartACOM;
	String chartBouncerateACOM;
	String chartTransactionACOM;
	String chartVisitorsACOM;
	
	//Total de Horas
	Integer totalHours;
	
	
	//Definição de Data
	GeneralBean gb = new GeneralBean();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String date = sdf.format(new Date()).toString();
	
	
	public DashboardBean() {
		 DashboardReport dbr = new DashboardReport();
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
			 		
			 				
			 		 //Inicialização de Hora
		 				totalHours = 0;
		 				
			 try {
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
						 	totalHours = totalHours + 1;
					 }
					 String hour = rd.getHour().toString();
					 
					 //Instancia dos dados
					 AbandonCart abandon =  new AbandonCart(hour, abandonCart.setScale(2, BigDecimal.ROUND_UP), rd.getCounts().get(2), rd.getCounts().get(1));
					 BounceRate bounce = new BounceRate(hour, bounceratepct.setScale(2, BigDecimal.ROUND_UP));
					 Visitors vis = new Visitors(hour, visitorsACOM);
					 Transaction trans = new Transaction(hour,visitorsACOM.doubleValue(), rd.getCounts().get(1), transactionpct.setScale(2, BigDecimal.ROUND_UP));
					 
					 //Soma dos Totais 
					 	totalSumAbandonCart = totalSumAbandonCart.add(abandon.getAbandoncart());
					 	totalVisitorsACOM = totalVisitorsACOM.add(vis.getVisitors());
					 	totalSumTransaction = totalSumTransaction.add(trans.getTransactionpct());;
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

	public BigDecimal getTotalAbandonCart() {
		return totalAbandonCart;
	}

	public void setTotalAbandonCart(BigDecimal totalAbandonCart) {
		this.totalAbandonCart = totalAbandonCart;
	}

	public BigDecimal getTotalTransaction() {
		return totalTransaction;
	}

	public void setTotalTransaction(BigDecimal totalTransaction) {
		this.totalTransaction = totalTransaction;
	}

	public BigDecimal getTotalBounce() {
		return totalBounce;
	}

	public void setTotalBounce(BigDecimal totalBounce) {
		this.totalBounce = totalBounce;
	}

	public BigDecimal getTotalVisitorsACOM() {
		return totalVisitorsACOM;
	}

	public void setTotalVisitorsACOM(BigDecimal totalVisitorsACOM) {
		this.totalVisitorsACOM = totalVisitorsACOM;
	}

	public String getChartabandoncartACOM() {
		if (chartabandoncartACOM == null || chartabandoncartACOM.trim().length() <= 0) {
			populateData();
		}
		return chartabandoncartACOM;
	}

	public void setChartabandoncartACOM(String chartabandoncartACOM) {
		this.chartabandoncartACOM = chartabandoncartACOM;
	}

	public String getChartBouncerateACOM() {
		if (chartBouncerateACOM == null || chartBouncerateACOM.trim().length() <= 0) {
			populateData();
		}
		return chartBouncerateACOM;
	}

	public void setChartBouncerateACOM(String chartBouncerateACOM) {
		this.chartBouncerateACOM = chartBouncerateACOM;
	}

	public String getChartTransactionACOM() {
		if (chartTransactionACOM == null || chartTransactionACOM.trim().length() <= 0) {
			populateData();
		}
		return chartTransactionACOM;
	}

	public void setChartTransactionACOM(String chartTransactionACOM) {
		this.chartTransactionACOM = chartTransactionACOM;
	}

	public String getChartVisitorsACOM() {
		if (chartVisitorsACOM == null || chartVisitorsACOM.trim().length() <= 0) {
			populateData();
		}
		return chartVisitorsACOM;
	}

	public void setChartVisitorsACOM(String chartVisitorsACOM) {
		this.chartVisitorsACOM = chartVisitorsACOM;
	}

	private void populateData() {
		DashboardBean dbb = new DashboardBean();
		DashboardLYBean dbly = new DashboardLYBean();
		StringBuilder sbVisitors = new StringBuilder();
		StringBuilder sbBounce = new StringBuilder();
		StringBuilder sbAbandon = new StringBuilder();
		StringBuilder sbTrans =  new StringBuilder();
		for (int i=0; i < dbb.getVisitors().size(); i++) {
						sbVisitors.append("[{ v: [");
						sbVisitors.append(dbb.getVisitors().get(i).getHour());
						sbVisitors.append(", 0, 0], f: '");
						sbVisitors.append(dbb.getVisitors().get(i).getHour());
						sbVisitors.append(" am'},");
					if(dbb.getVisitors().get(i).getVisitors().doubleValue() == 0 || dbly.getVisitors().get(i).getVisitors().doubleValue() == 0 ){
						sbVisitors.append("0,0]");
						sbVisitors.append(",");
					}else{
						sbVisitors.append(dbb.getVisitors().get(i).getVisitors().doubleValue()/1000);
						sbVisitors.append(",");
						sbVisitors.append(dbb.getVisitors().get(i).getVisitors().doubleValue()/1000);
						sbVisitors.append("]");
						sbVisitors.append(",");
					}
				}
		chartVisitorsACOM = sbVisitors.toString();
			sbBounce.append("['Hora' ,");
			sbBounce.append(gb.getDateAtual());
			sbBounce.append(",");
			sbBounce.append(gb.getLastYear());
			sbBounce.append("],");
				for (int i=0; i < dbb.getBouncerate().size(); i++) {
					sbBounce.append("['");
					sbBounce.append(dbb.getBouncerate().get(i).getHour());
					sbBounce.append("h',");
					if(dbb.getBouncerate().get(i).getBouncerate().doubleValue() == 0 || dbly.getBouncerate().get(i).getBouncerate().doubleValue() == 0 ){
						sbBounce.append("0,0]");
						sbBounce.append(",");
					}else{
						sbBounce.append(dbb.getBouncerate().get(i).getBouncerate());
						sbBounce.append(",");
						sbBounce.append(dbly.getBouncerate().get(i).getBouncerate());
						sbBounce.append("]");
						sbBounce.append(",");
					}
				}
		chartBouncerateACOM = sbBounce.toString();		
						sbTrans.append("['Hora' ,");
						sbTrans.append(gb.getDateAtual());
						sbTrans.append(",");
						sbTrans.append(gb.getLastYear());
						sbTrans.append("],");
				for (int i=0; i < dbb.getTransactions().size(); i++) {
						sbTrans.append("['");
						sbTrans.append(dbb.getTransactions().get(i).getHour());
						sbTrans.append("h',");
						if(dbb.getTransactions().get(i).getTransactionpct().doubleValue() == 0 || dbly.getTransactions().get(i).getTransactionpct().doubleValue() == 0 ){
							sbTrans.append("0,0]");
							sbTrans.append(",");
						}else{
							sbTrans.append(dbb.getTransactions().get(i).getTransactionpct());
							sbTrans.append(",");
							sbTrans.append(dbly.getTransactions().get(i).getTransactionpct());
							sbTrans.append("]");
							sbTrans.append(",");
						}
				}
        chartTransactionACOM = sbTrans.toString();
        StringBuilder stringBuilder = new StringBuilder();
					  stringBuilder.append("['Hora' ,");
					  stringBuilder.append(gb.getDateAtual());
					  stringBuilder.append(",");
					  stringBuilder.append(gb.getLastYear());
					  stringBuilder.append("],");
		for (int i=0; i < dbb.getAbandoncart().size(); i++) {
				stringBuilder.append("['");
				stringBuilder.append(dbb.getAbandoncart().get(i).getHour());
				stringBuilder.append("h',");
				if(dbb.getAbandoncart().get(i).getAbandoncart().doubleValue() == 0 || dbly.getAbandoncart().get(i).getAbandoncart().doubleValue() == 0 ){
					stringBuilder.append("0,0]");
					stringBuilder.append(",");
				}else{
					stringBuilder.append(dbb.getAbandoncart().get(i).getAbandoncart());
					stringBuilder.append(",");
					stringBuilder.append(dbly.getAbandoncart().get(i).getAbandoncart());
					stringBuilder.append("]");
					stringBuilder.append(",");
				}
			}
		chartabandoncartACOM = stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		DashboardBean dbb = new DashboardBean();
		System.out.println(dbb.getChartVisitorsACOM());
		System.out.println(dbb.getChartBouncerateACOM());
		System.out.println(dbb.getChartTransactionACOM());
		System.out.println(dbb.getChartabandoncartACOM());
	}

}

