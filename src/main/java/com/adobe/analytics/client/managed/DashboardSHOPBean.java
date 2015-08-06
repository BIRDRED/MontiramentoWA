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
import com.adobe.analytics.client.domain.ReportResponse;
import com.adobe.analytics.client.entity.AbandonCart;
import com.adobe.analytics.client.entity.BounceRate;
import com.adobe.analytics.client.entity.Transaction;
import com.adobe.analytics.client.entity.Visitors;
import com.adobe.analytics.client.report.DashboardReport;

@ManagedBean
@SessionScoped
public class DashboardSHOPBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//Variaveis de Listas 
	List<BounceRate> bouncerate;
	List<Visitors> visitorslist;
	List<AbandonCart> abandoncart;
	List<Transaction> transactions;
	
	//Variaveis de Totais
	BigDecimal totalAbandonCart;
	BigDecimal totalTransaction;
	BigDecimal totalBounce;
	
	//Variaveis de Somas dos Totais
	BigDecimal totalVisitors;
	BigDecimal totalSumAbandonCart;
	BigDecimal totalSumTransaction;
	BigDecimal totalSumBounce;
	
	//Variaveis de Gráficos
	String chartabandoncart;
	String chartBouncerate;
	String chartTransaction;
	String chartVisitors;
	
	//Total de Horas
	Integer totalHours;
	
	//Latencia
	BigDecimal latenciaVisitors;
	
	//Definição de Data
	GeneralBean gb = new GeneralBean();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String date = sdf.format(new Date()).toString();
	
	
	public DashboardSHOPBean() throws IOException, InterruptedException {
		 DashboardReport dbr = new DashboardReport();
		 ReportResponse report =  dbr.getDashboard("b2w-shop", date);
		 				//Inicialização de Variaveis
			 				abandoncart = new ArrayList<AbandonCart>();
			 				bouncerate = new ArrayList<BounceRate>();
			 				visitorslist =  new ArrayList<Visitors>();
			 				transactions =  new ArrayList<Transaction>();
			 				
		 			  //Inicialização de Soma 
				 			totalVisitors = BigDecimal.ZERO;
			 				totalSumAbandonCart = BigDecimal.ZERO;
			 				totalSumTransaction = BigDecimal.ZERO;
			 				totalSumBounce = BigDecimal.ZERO;
			 		
			 				
			 		 //Inicialização de Hora
		 				totalHours = 0;
		 				
		 			//Latencia dos Dados
		 				latenciaVisitors = new BigDecimal(report.getReport().getMetrics().get(0).getLatency());
		 if(report.getReport().getTotals().get(0) > 0  && report.getReport().getTotals().get(0) > 0){
				for(ReportData rd : report.getReport().getData()){
					//Taxas de Relatório
						BigDecimal abandonCart = BigDecimal.ZERO;
						BigDecimal visitors = BigDecimal.ZERO;
						BigDecimal transactionpct = BigDecimal.ZERO;
						BigDecimal bounceratepct = BigDecimal.ZERO;
						
					//Faz a soma dos Variaveis
					 if(rd.getCounts().get(0)!=0 && rd.getCounts().get(1)!=0 && rd.getCounts().get(2)!=0 && rd.getCounts().get(3)!=0 &&  rd.getCounts().get(4)!=0){
						 	abandonCart = new BigDecimal((1 - (rd.getCounts().get(1) /rd.getCounts().get(2))) * 100);
						 	visitors = new BigDecimal(rd.getCounts().get(0));
						 	transactionpct = new BigDecimal((rd.getCounts().get(1) /rd.getCounts().get(0)) * 100);
						 	bounceratepct = new BigDecimal((rd.getCounts().get(3) /rd.getCounts().get(4)) * 100);
						 	totalHours = totalHours + 1;
					 }
					 String hour = rd.getHour().toString();
					 
					 //Instancia dos dados
					 AbandonCart abandon =  new AbandonCart(hour, abandonCart.setScale(2, BigDecimal.ROUND_UP), rd.getCounts().get(2), rd.getCounts().get(1));
					 BounceRate bounce = new BounceRate(hour, bounceratepct.setScale(2, BigDecimal.ROUND_UP));
					 Visitors vis = new Visitors(rd.getHour(), visitors);
					 Transaction trans = new Transaction(hour,visitors.doubleValue(), rd.getCounts().get(1), transactionpct.setScale(2, BigDecimal.ROUND_UP));
					 
					 //Soma dos Totais 
					 	totalSumAbandonCart = totalSumAbandonCart.add(abandon.getAbandoncart());
					 	totalVisitors = totalVisitors.add(vis.getVisitors());
					 	totalSumTransaction = totalSumTransaction.add(trans.getTransactionpct());;
					 	totalSumBounce = totalSumBounce.add(bounce.getBouncerate());
		 				
		 			//Adiciona na Lista
					 	abandoncart.add(abandon);
					 	bouncerate.add(bounce);
					 	visitorslist.add(vis);
					 	transactions.add(trans);
					 	
				 }
		 }
	}
	
	public List<BounceRate> getBouncerate() {
		return bouncerate;
	}

	public void setBouncerate(List<BounceRate> bouncerate) {
		this.bouncerate = bouncerate;
	}


	public List<Visitors> getVisitorslist() {
		return visitorslist;
	}

	public void setVisitorslist(List<Visitors> visitorslist) {
		this.visitorslist = visitorslist;
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
		BigDecimal totalAbandonCart = new BigDecimal((totalSumAbandonCart.doubleValue()/totalHours.doubleValue()));
		return totalAbandonCart.setScale(2, BigDecimal.ROUND_UP);
	}

	public void setTotalAbandonCart(BigDecimal totalAbandonCart) {
		this.totalAbandonCart = totalAbandonCart;
	}


	public BigDecimal getTotalTransaction() {
		BigDecimal totalTransaction = new BigDecimal((totalSumTransaction.doubleValue()/totalHours.doubleValue()));
		return totalTransaction.setScale(2, BigDecimal.ROUND_UP);
	}

	public void setTotalTransaction(BigDecimal totalTransaction) {
		this.totalTransaction = totalTransaction;
	}

	public BigDecimal getTotalBounce() {
		BigDecimal totalBounceRate = new BigDecimal((totalSumBounce.doubleValue()/totalHours.doubleValue()));
		return totalBounceRate.setScale(2, BigDecimal.ROUND_UP);
	}

	public void setTotalBounce(BigDecimal totalBounce) {
		this.totalBounce = totalBounce;
	}

	public Integer getTotalVisitors() {
		BigDecimal tv = BigDecimal.ZERO;
		if(totalVisitors.toString().length() > 3){
			tv = new BigDecimal((totalVisitors.doubleValue()/1000));
		}
		return tv.intValue();
	}

	public void setTotalVisitors(BigDecimal totalVisitors) {
		this.totalVisitors = totalVisitors;
	}

	public String getChartabandoncart() throws IOException, InterruptedException{
		if (chartabandoncart == null || chartabandoncart.trim().length() <= 0) {
			populateData();
		}
		return chartabandoncart;
	}

	public void setChartabandoncart(String chartabandoncart) {
		this.chartabandoncart = chartabandoncart;
	}

	public String getChartBouncerate() throws IOException, InterruptedException{
		if (chartBouncerate == null || chartBouncerate.trim().length() <= 0) {
			populateData();
		}
		return chartBouncerate;
	}

	public void setChartBouncerate(String chartBouncerate) {
		this.chartBouncerate = chartBouncerate;
	}

	public String getChartTransaction() throws IOException, InterruptedException{
		if (chartTransaction == null || chartTransaction.trim().length() <= 0) {
			populateData();
		}
		return chartTransaction;
	}

	public void setChartTransaction(String chartTransaction) {
		this.chartTransaction = chartTransaction;
	}

	public String getChartVisitors() throws IOException, InterruptedException {
		if (chartVisitors == null || chartVisitors.trim().length() <= 0) {
			populateData();
		}
		return chartVisitors;
	}

	public void setChartVisitors(String chartVisitors) {
		this.chartVisitors = chartVisitors;
	}
	
	

	public BigDecimal getLatenciaVisitors() {
		return new BigDecimal(latenciaVisitors.intValue()/60);
	}

	public void setLatenciaVisitors(BigDecimal latenciaVisitors) {
		this.latenciaVisitors = latenciaVisitors;
	}

	private void populateData() throws IOException, InterruptedException {
		DashboardSHOPBean dbb = new DashboardSHOPBean();
		DashboardSHOPLYBean dbly = new DashboardSHOPLYBean();
		StringBuilder sbVisitors = new StringBuilder();
		StringBuilder sbBounce = new StringBuilder();
		StringBuilder sbAbandon = new StringBuilder();
		StringBuilder sbTrans =  new StringBuilder();
		Integer countVisitors = dbb.getVisitorslist().size();	
		Integer countBounce = dbb.getBouncerate().size();
		Integer countAbandon = dbb.getAbandoncart().size();
		Integer countTrans = dbb.getTransactions().size();
		if( countVisitors == 0){
			countVisitors = countVisitors + 1;
		}
		for (int i=0; i < countVisitors; i++) {
			if(dbb.getVisitorslist().get(i).getVisitors().doubleValue() != 0 && dbly.getVisitorsList().get(i).getVisitors().doubleValue() != 0 ){
						sbVisitors.append("[{ v: [");
						sbVisitors.append(dbb.getVisitorslist().get(i).getHour());
						sbVisitors.append(", 0, 0], f: '");
						sbVisitors.append(dbb.getVisitorslist().get(i).getHour());
						sbVisitors.append(" am'},");
						if(dbb.getVisitorslist().get(i).getHour() == gb.getCompareHour()){
							sbVisitors.append(dbb.getVisitorslist().get(i).getVisitors().doubleValue() * 2/1000);
							sbVisitors.append(",");
							sbVisitors.append(dbb.getVisitorslist().get(i).getVisitors().doubleValue()/1000);
						/*}else if(dbb.getVisitors().get(i).getHour() == gb.getCompareHour() - 1){
							if((dbb.getLatenciaVisitors().intValue()  + 30) - 60 >= gb.getCompareMinutes()
								&& (latenciaVisitors.intValue() + 30) - 60 > 0 ){
								sbVisitors.append(dbb.getVisitors().get(i).getVisitors().doubleValue() * 2/1000);
								sbVisitors.append(",");
								sbVisitors.append(dbb.getVisitors().get(i).getVisitors().doubleValue()/1000);
							}else{
								sbVisitors.append("0,");
								sbVisitors.append(dbb.getVisitors().get(i).getVisitors().doubleValue()/1000);
							}*/
						}else{
							sbVisitors.append("0,");
							sbVisitors.append(dbb.getVisitorslist().get(i).getVisitors().doubleValue()/1000);
						}
	
							sbVisitors.append(",");
							sbVisitors.append(dbly.getVisitorsList().get(i).getVisitors().doubleValue()/1000);
							sbVisitors.append("]");
							sbVisitors.append(",");
				}
			}
		chartVisitors = sbVisitors.toString();
			sbBounce.append("['Hora' ,");
			sbBounce.append(gb.getDateAtual());
			sbBounce.append(",");
			sbBounce.append(gb.getLastYear());
			sbBounce.append("],");
			if( countBounce == 0){
				countBounce = countBounce + 1;
			}
				for (int i=0; i < countBounce; i++) {
					if(dbb.getBouncerate().get(i).getBouncerate().doubleValue() != 0 && dbly.getBouncerate().get(i).getBouncerate().doubleValue() != 0 ){
						sbBounce.append("['");
						sbBounce.append(dbb.getBouncerate().get(i).getHour());
						sbBounce.append("h',");
						sbBounce.append(dbb.getBouncerate().get(i).getBouncerate());
						sbBounce.append(",");
						sbBounce.append(dbly.getBouncerate().get(i).getBouncerate());
						sbBounce.append("]");
						sbBounce.append(",");
					}
				}
		chartBouncerate = sbBounce.toString();		
						sbTrans.append("['Hora' ,");
						sbTrans.append(gb.getDateAtual());
						sbTrans.append(",");
						sbTrans.append(gb.getLastYear());
						sbTrans.append("],");
						if( countTrans == 0){
							countTrans = countTrans + 1;
						}
				for (int i=0; i < countTrans; i++) {
					if(dbb.getTransactions().get(i).getTransactionpct().doubleValue() != 0 && dbly.getTransactions().get(i).getTransactionpct().doubleValue() != 0 ){		
							sbTrans.append("['");
							sbTrans.append(dbb.getTransactions().get(i).getHour());
							sbTrans.append("h',");
							sbTrans.append(dbb.getTransactions().get(i).getTransactionpct());
							sbTrans.append(",");
							sbTrans.append(dbly.getTransactions().get(i).getTransactionpct());
							sbTrans.append("]");
							sbTrans.append(",");
						}
				}
        chartTransaction = sbTrans.toString();
        			  sbAbandon.append("['Hora' ,");
        			  sbAbandon.append(gb.getDateAtual());
        			  sbAbandon.append(",");
        			  sbAbandon.append(gb.getLastYear());
        			  sbAbandon.append("],");
        			  if( countAbandon == 0){
        				  countAbandon = countAbandon + 1;
        			  }
		for (int i=0; i < countAbandon; i++) {
			if(dbb.getAbandoncart().get(i).getAbandoncart().doubleValue() != 0 && dbly.getAbandoncart().get(i).getAbandoncart().doubleValue() != 0 ){
					sbAbandon.append("['");
					sbAbandon.append(dbb.getAbandoncart().get(i).getHour());
					sbAbandon.append("h',");
					sbAbandon.append(dbb.getAbandoncart().get(i).getAbandoncart());
					sbAbandon.append(",");
					sbAbandon.append(dbly.getAbandoncart().get(i).getAbandoncart());
					sbAbandon.append("]");
					sbAbandon.append(",");
				}
			}
		chartabandoncart = sbAbandon.toString();
	}

}

