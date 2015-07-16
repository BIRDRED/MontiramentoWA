package com.adobe.analytics.client.managed;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.adobe.analytics.client.report.VisitsReport;

public class TotalVisitorsBean {
	private Integer shareVisitorsACOM;
	private BigDecimal totalB2W;
	private BigDecimal totalACOM;
	private String chartshareVisitors;
	VisitsReport vr = new VisitsReport();
	

	public TotalVisitorsBean() throws IOException, InterruptedException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date()).toString();
			   totalB2W = new BigDecimal(vr.getvisits("b2w-global", date).getReport().getTotals().get(0));
			   totalACOM = new BigDecimal(vr.getvisits("b2w-acom", date).getReport().getTotals().get(0));
	}
	
	

	public Integer getShareVisitorsACOM() {
		BigDecimal shareACOM = new BigDecimal((totalACOM.doubleValue()/totalB2W.doubleValue()) * 100);
		return shareACOM.setScale(2, BigDecimal.ROUND_UP).intValue();
	}

	

	public BigDecimal getTotalB2W() {
		return totalB2W;
	}

	public void setTotalB2W(BigDecimal totalB2W) {
		this.totalB2W = totalB2W;
	}
	
	public BigDecimal getTotalACOM() {
		return totalACOM;
	}

	public void setTotalACOM(BigDecimal totalACOM) {
		this.totalACOM = totalACOM;
	}
	
	

	public String getChartshareVisitors() throws Exception, InterruptedException {
		if (chartshareVisitors == null || chartshareVisitors.trim().length() <= 0) {
				populateData();
			
		}
		return chartshareVisitors;
	}

	public void setChartshareVisitors(String chartshareVisitors) {
		this.chartshareVisitors = chartshareVisitors;
	}

	private void populateData() throws Exception, InterruptedException {
		TotalVisitorsBean tvb = new TotalVisitorsBean();
		StringBuilder stringBuilder = new StringBuilder();
					  stringBuilder.append("['Marca' ,  'Share Visitas'],");
					  stringBuilder.append("['ACOM',");
					  stringBuilder.append(tvb.getShareVisitorsACOM().intValue());
					  stringBuilder.append("],");
					  stringBuilder.append("['Outros',");
					  stringBuilder.append(100 - (tvb.getShareVisitorsACOM().intValue()));
					  stringBuilder.append("]");
					  chartshareVisitors = stringBuilder.toString();
				
	}
	
	
	public static void main(String[] args) throws Exception {
		TotalVisitorsBean tvb = new TotalVisitorsBean();
		System.out.println(tvb.getChartshareVisitors());
	}
}
