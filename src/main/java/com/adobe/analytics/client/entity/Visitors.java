package com.adobe.analytics.client.entity;

import java.math.BigDecimal;

public class Visitors {
		private Integer hour;
		private BigDecimal visitors;
		
		public Visitors() {
			super();
		}
		
		public Visitors(Integer hour, BigDecimal visitors) {
			this.hour = hour;
			this.visitors = visitors;
		}


		

		public Integer getHour() {
			return hour;
		}

		public void setHour(Integer hour) {
			this.hour = hour;
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
