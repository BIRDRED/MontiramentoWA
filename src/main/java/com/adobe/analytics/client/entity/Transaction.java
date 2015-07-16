package com.adobe.analytics.client.entity;

import java.math.BigDecimal;

public class Transaction {
		private String hour;
		private Double visitors;
		private Double transaction;
		private BigDecimal transactionpct;
		
		public Transaction() {
			super();
		}
		
		

		public Transaction(String hour, Double visitors, Double transaction,
				BigDecimal transactionpct) {
			super();
			this.hour = hour;
			this.visitors = visitors;
			this.transaction = transaction;
			this.transactionpct = transactionpct;
		}



		public String getHour() {
			return hour;
		}

		public void setHour(String hour) {
			this.hour = hour;
		}

		public Double getVisitors() {
			return visitors;
		}

		public void setVisitors(Double visitors) {
			this.visitors = visitors;
		}

		public Double getTransaction() {
			return transaction;
		}

		public void setTransaction(Double transaction) {
			this.transaction = transaction;
		}

		

		public BigDecimal getTransactionpct() {
			return transactionpct;
		}



		public void setTransactionpct(BigDecimal transactionpct) {
			this.transactionpct = transactionpct;
		}



		@Override
		public String toString() {
			return "Transaction [hour=" + hour + ", visitors=" + visitors
					+ ", transaction=" + transaction + ", transactionpct="
					+ transactionpct + "]";
		}
}
