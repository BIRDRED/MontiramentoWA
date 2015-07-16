package com.adobe.analytics.client.managed;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.adobe.analytics.client.domain.ReportData;
import com.adobe.analytics.client.entity.Transaction;
import com.adobe.analytics.client.report.TransactionReport;

@ManagedBean
@SessionScoped
public class TransactionLYBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	List<Transaction> transactions;
	
	public TransactionLYBean() {
		 TransactionReport tr  =  new TransactionReport();
		 transactions = new ArrayList<Transaction>();
			 try {
				 Calendar c = Calendar.getInstance();
	 				Integer ano = c.get(Calendar.YEAR) - 1 ;
	 						 c.set(Calendar.YEAR, ano );
	 				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 				String date = sdf.format(c.getTime()).toString();
				for(ReportData rd : tr.getTransaction("b2w-acom", date).getReport().getData()){
					BigDecimal transactionpct = BigDecimal.ZERO;
					 if(rd.getCounts().get(1) != 0 && rd.getCounts().get(0) != 0 ){
						 transactionpct = new BigDecimal((rd.getCounts().get(1) /rd.getCounts().get(0)) * 100);
					 }
					 Transaction trans = new Transaction(rd.getHour().toString(), rd.getCounts().get(0), rd.getCounts().get(1),transactionpct.setScale(2, BigDecimal.ROUND_UP));
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
	
}

