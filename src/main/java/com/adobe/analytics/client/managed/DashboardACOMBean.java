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
public class DashboardACOMBean implements Serializable {
	
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
	
	//Latencia
	BigDecimal latenciaVisitors;
	
	//Definição de Data
	GeneralBean gb = new GeneralBean();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String date = sdf.format(new Date()).toString();
	
	
	public DashboardACOMBean() throws IOException, InterruptedException {
		 DashboardReport dbr = new DashboardReport();
		 ReportResponse report =  dbr.getDashboard("b2w-acom", date);
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
		 				
		 			//Latencia dos Dados
		 				latenciaVisitors = new BigDecimal(report.getReport().getMetrics().get(0).getLatency());
				for(ReportData rd : report.getReport().getData()){
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
					 Visitors vis = new Visitors(rd.getHour(), visitorsACOM);
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

	public String getChartabandoncartACOM() throws IOException, InterruptedException{
		if (chartabandoncartACOM == null || chartabandoncartACOM.trim().length() <= 0) {
			populateData();
		}
		return chartabandoncartACOM;
	}

	public void setChartabandoncartACOM(String chartabandoncartACOM) {
		this.chartabandoncartACOM = chartabandoncartACOM;
	}

	public String getChartBouncerateACOM() throws IOException, InterruptedException{
		if (chartBouncerateACOM == null || chartBouncerateACOM.trim().length() <= 0) {
			populateData();
		}
		return chartBouncerateACOM;
	}

	public void setChartBouncerateACOM(String chartBouncerateACOM) {
		this.chartBouncerateACOM = chartBouncerateACOM;
	}

	public String getChartTransactionACOM() throws IOException, InterruptedException{
		if (chartTransactionACOM == null || chartTransactionACOM.trim().length() <= 0) {
			populateData();
		}
		return chartTransactionACOM;
	}

	public void setChartTransactionACOM(String chartTransactionACOM) {
		this.chartTransactionACOM = chartTransactionACOM;
	}

	public String getChartVisitorsACOM() throws IOException, InterruptedException {
		if (chartVisitorsACOM == null || chartVisitorsACOM.trim().length() <= 0) {
			populateData();
		}
		return chartVisitorsACOM;
	}

	public void setChartVisitorsACOM(String chartVisitorsACOM) {
		this.chartVisitorsACOM = chartVisitorsACOM;
	}
	
	

	public BigDecimal getLatenciaVisitors() {
		return new BigDecimal(latenciaVisitors.intValue()/60);
	}

	public void setLatenciaVisitors(BigDecimal latenciaVisitors) {
		this.latenciaVisitors = latenciaVisitors;
	}

	private void populateData() throws IOException, InterruptedException {
		DashboardACOMBean dbb = new DashboardACOMBean();
		DashboardACOMLYBean dbly = new DashboardACOMLYBean();
		StringBuilder sbVisitors = new StringBuilder();
		StringBuilder sbBounce = new StringBuilder();
		StringBuilder sbAbandon = new StringBuilder();
		StringBuilder sbTrans =  new StringBuilder();
		Integer countVisitors = dbb.getVisitors().size();	
		Integer countBounce = dbb.getBouncerate().size();
		Integer countAbandon = dbb.getAbandoncart().size();
		Integer countTrans = dbb.getTransactions().size();
		if( countVisitors == 0){
			countVisitors = countVisitors + 1;
		}
		for (int i=0; i < countVisitors; i++) {
			if(dbb.getVisitors().get(i).getVisitors().doubleValue() != 0 && dbly.getVisitors().get(i).getVisitors().doubleValue() != 0 ){
						sbVisitors.append("[{ v: [");
						sbVisitors.append(dbb.getVisitors().get(i).getHour());
						sbVisitors.append(", 0, 0], f: '");
						sbVisitors.append(dbb.getVisitors().get(i).getHour());
						sbVisitors.append(" am'},");
						if(dbb.getVisitors().get(i).getHour() == gb.getCompareHour()){
							sbVisitors.append(dbb.getVisitors().get(i).getVisitors().doubleValue() * 2/1000);
							sbVisitors.append(",");
							sbVisitors.append(dbb.getVisitors().get(i).getVisitors().doubleValue()/1000);
						}else if(dbb.getVisitors().get(i).getHour() == gb.getCompareHour() - 1){
							if((dbb.getLatenciaVisitors().intValue()  + 30) - 60 >= gb.getCompareMinutes()
								&& (latenciaVisitors.intValue() + 30) - 60 > 0 ){
								sbVisitors.append(dbb.getVisitors().get(i).getVisitors().doubleValue() * 2/1000);
								sbVisitors.append(",");
								sbVisitors.append(dbb.getVisitors().get(i).getVisitors().doubleValue()/1000);
							}else{
								sbVisitors.append("0,");
								sbVisitors.append(dbb.getVisitors().get(i).getVisitors().doubleValue()/1000);
							}
						}else{
							sbVisitors.append("0,");
							sbVisitors.append(dbb.getVisitors().get(i).getVisitors().doubleValue()/1000);
						}
	
							sbVisitors.append(",");
							sbVisitors.append(dbly.getVisitors().get(i).getVisitors().doubleValue()/1000);
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
		chartBouncerateACOM = sbBounce.toString();		
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
        chartTransactionACOM = sbTrans.toString();
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
		chartabandoncartACOM = sbAbandon.toString();
	}
	public static void main(String[] args) throws IOException, InterruptedException {
		DashboardACOMBean dbb = new DashboardACOMBean();
		System.out.println(dbb.getChartabandoncartACOM());
	}

}

