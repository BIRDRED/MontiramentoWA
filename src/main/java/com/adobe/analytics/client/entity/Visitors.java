package com.adobe.analytics.client.entity;

import java.math.BigDecimal;

public class Visitors {
		private String hour;
		private BigDecimal visitors;
		
		public Visitors() {
			super();
		}
		
		public Visitors(String hour, BigDecimal visitors) {
			this.hour = hour;
			this.visitors = visitors;
		}


		public String getHour() {
			return hour;
		}


		public String setHour(String hour) {
			return this.hour = hour;
		}


		public BigDecimal getVisitors() {
			return visitors;
		}

		public void setVisitors(BigDecimal visitors) {
			this.visitors = visitors;
		}

		@Override
		public String toString() {
			return "[hour=" + hour + ", visitors=" + visitors + "]";
		}

		
}
