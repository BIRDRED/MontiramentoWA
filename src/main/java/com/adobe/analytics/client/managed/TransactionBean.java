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
import com.adobe.analytics.client.entity.Transaction;
import com.adobe.analytics.client.report.TransactionReport;

@ManagedBean
@SessionScoped
public class TransactionBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	List<Transaction> transactions;
	BigDecimal totalTransaction;
	BigDecimal totalSumTransaction;
	Integer totalHours;
	public String chartTransactionACOM;
	GeneralBean gb =  new GeneralBean();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String date = sdf.format(new Date()).toString();
	public TransactionBean() {
		 TransactionReport tr  =  new TransactionReport();
		 				totalHours = 0;
		 				totalSumTransaction = BigDecimal.ZERO;
		 transactions = new ArrayList<Transaction>();
			 try {
				for(ReportData rd : tr.getTransaction("b2w-acom", date).getReport().getData()){
					BigDecimal transactionpct = BigDecimal.ZERO;	   
					 if(rd.getCounts().get(1) != 0 && rd.getCounts().get(0) != 0 ){
						 transactionpct = new BigDecimal((rd.getCounts().get(1) /rd.getCounts().get(0)) * 100);
						 totalHours = totalHours + 1;
					 }
					 Transaction trans = new Transaction(rd.getHour().toString(), rd.getCounts().get(0), rd.getCounts().get(1),transactionpct.setScale(2, BigDecimal.ROUND_UP));
					 			totalSumTransaction = totalSumTransaction.add(trans.getTransactionpct());
					 transactions.add(trans);
				
				 }
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
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
	
	
	public BigDecimal getTotalTransaction() {
		BigDecimal totalTransaction = new BigDecimal((totalSumTransaction.doubleValue()/totalHours.doubleValue()));
		return totalTransaction.setScale(2, BigDecimal.ROUND_UP);
	}

	public void setTotalTransaction(BigDecimal totalTransaction) {
		this.totalTransaction = totalTransaction;
	}


	private void populateData() {
		TransactionBean tb = new TransactionBean();
		TransactionLYBean tbly = new TransactionLYBean();
		StringBuilder stringBuilder = new StringBuilder();
					  stringBuilder.append("['Hora' ,");
					  stringBuilder.append(gb.getDateAtual());
					  stringBuilder.append(",");
					  stringBuilder.append(gb.getLastYear());
					  stringBuilder.append("],");
		for (int i=0; i < tb.getTransactions().size(); i++) {
					stringBuilder.append("['");
					stringBuilder.append(tb.getTransactions().get(i).getHour());
					stringBuilder.append("h',");
					if(tb.getTransactions().get(i).getTransactionpct().doubleValue() == 0 || tbly.getTransactions().get(i).getTransactionpct().doubleValue() == 0 ){
						stringBuilder.append("0,0]");
						stringBuilder.append(",");
					}else{
						stringBuilder.append(tb.getTransactions().get(i).getTransactionpct());
						stringBuilder.append(",");
						stringBuilder.append(tbly.getTransactions().get(i).getTransactionpct());
						stringBuilder.append("]");
						stringBuilder.append(",");
					}
				}
			chartTransactionACOM = stringBuilder.toString();
	}
	
	
	
	public static void main(String[] args) {
		TransactionBean tbb = new TransactionBean();
		System.out.println(tbb.getTotalTransaction());
	}
}

