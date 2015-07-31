package com.adobe.analytics.client.managed;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.adobe.analytics.client.report.VisitsReport;

public class TotalVisitorsBean {
	private BigDecimal totalB2W;
	private BigDecimal totalACOM;
	private BigDecimal totalSUBA;
	private BigDecimal totalSHOP;
	private BigDecimal totalSOUB;
	private String chartshareVisitorsACOM;
	private String chartshareVisitorsSUBA;
	private String chartshareVisitorsSHOP;
	private String chartshareVisitorsSOUB;
	VisitsReport vr = new VisitsReport();
	

	public TotalVisitorsBean() throws IOException, InterruptedException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date()).toString();
			   totalB2W = new BigDecimal(vr.getvisits("b2w-global", date).getReport().getTotals().get(0));
			   totalACOM = new BigDecimal(vr.getvisits("b2w-acom", date).getReport().getTotals().get(0));
			   totalSUBA = new BigDecimal(vr.getvisits("b2w-suba", date).getReport().getTotals().get(0));
			   totalSHOP = new BigDecimal(vr.getvisits("b2w-shop", date).getReport().getTotals().get(0));
			   totalSOUB = new BigDecimal(vr.getvisits("b2w-soub", date).getReport().getTotals().get(0));
	}
	
	//SHARE VISITORS
	
	public Integer getShareVisitorsSUBA() {
		BigDecimal shareSUBA = new BigDecimal((totalSUBA.doubleValue()/totalB2W.doubleValue()) * 100);
		return shareSUBA.setScale(2, BigDecimal.ROUND_UP).intValue();
	}



	public Integer getShareVisitorsACOM() {
		BigDecimal shareACOM = new BigDecimal((totalACOM.doubleValue()/totalB2W.doubleValue()) * 100);
		return shareACOM.setScale(2, BigDecimal.ROUND_UP).intValue();
	}
	
	public Integer getShareVisitorsSHOP() {
		BigDecimal shareSHOP = new BigDecimal((totalSHOP.doubleValue()/totalB2W.doubleValue()) * 100);
		return shareSHOP.setScale(2, BigDecimal.ROUND_UP).intValue();
	}


	public Integer getShareVisitorsSOUB() {
		BigDecimal shareSOUB = new BigDecimal((totalSOUB.doubleValue()/totalB2W.doubleValue()) * 100);
		return shareSOUB.setScale(2, BigDecimal.ROUND_UP).intValue();
	}


	//GRÁFICO DOS DADOS	
	
	
	public String getChartshareVisitorsSHOP() throws InterruptedException, Exception {
		if (chartshareVisitorsSHOP == null || chartshareVisitorsSHOP.trim().length() <= 0) {
			populateData();
		
		}
			return chartshareVisitorsSHOP;
	}

	public void setChartshareVisitorsSHOP(String chartshareVisitorsSHOP) {
		this.chartshareVisitorsSHOP = chartshareVisitorsSHOP;
	}

	public String getChartshareVisitorsSOUB() throws InterruptedException, Exception {
		if (chartshareVisitorsSOUB == null || chartshareVisitorsSOUB.trim().length() <= 0) {
			populateData();
		
		}
			return chartshareVisitorsSOUB;
	}

	public void setChartshareVisitorsSOUB(String chartshareVisitorsSOUB) {
		this.chartshareVisitorsSOUB = chartshareVisitorsSOUB;
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
	
	public String getChartshareVisitorsACOM() throws Exception, InterruptedException {
		if (chartshareVisitorsACOM == null || chartshareVisitorsACOM.trim().length() <= 0) {
				populateData();
			
		}
		return chartshareVisitorsACOM;
	}

	public void setChartshareVisitorsACOM(String chartshareVisitors) {
		this.chartshareVisitorsACOM = chartshareVisitors;
	}

	public String getChartshareVisitorsSUBA() throws InterruptedException, Exception {
		if (chartshareVisitorsSUBA == null || chartshareVisitorsSUBA.trim().length() <= 0) {
			populateData();
		
		}
			return chartshareVisitorsSUBA;
	}


	public void setChartshareVisitorsSUBA(String chartshareVisitorsSUBA) {
		this.chartshareVisitorsSUBA = chartshareVisitorsSUBA;
	}

	

	private void populateData() throws Exception, InterruptedException {
		TotalVisitorsBean tvb = new TotalVisitorsBean();
		StringBuilder stringBuilder = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		StringBuilder sbSHOP = new StringBuilder();
		StringBuilder sbSOUB = new StringBuilder();
					  stringBuilder.append("['Marca' ,  'Share Visitas'],");
					  stringBuilder.append("['ACOM',");
					  stringBuilder.append(tvb.getShareVisitorsACOM().intValue());
					  stringBuilder.append("],");
					  stringBuilder.append("['Outros',");
					  stringBuilder.append(100 - (tvb.getShareVisitorsACOM().intValue()));
					  stringBuilder.append("]");
					  chartshareVisitorsACOM = stringBuilder.toString();
					  sb.append("['Marca' ,  'Share Visitas'],");
					  sb.append("['SUBA',");
					  sb.append(tvb.getShareVisitorsSUBA().intValue());
					  sb.append("],");
					  sb.append("['Outros',");
					  sb.append(100 - (tvb.getShareVisitorsSUBA().intValue()));
					  sb.append("]");
					  chartshareVisitorsSUBA = sb.toString();
					  sbSHOP.append("['Marca' ,  'Share Visitas'],");
					  sbSHOP.append("['SHOP',");
					  sbSHOP.append(tvb.getShareVisitorsSHOP().intValue());
					  sbSHOP.append("],");
					  sbSHOP.append("['Outros',");
					  sbSHOP.append(100 - (tvb.getShareVisitorsSHOP().intValue()));
					  sbSHOP.append("]");
					  chartshareVisitorsSHOP = sbSHOP.toString();
					  sbSOUB.append("['Marca' ,  'Share Visitas'],");
					  sbSOUB.append("['SOUB',");
					  sbSOUB.append(tvb.getShareVisitorsSOUB().intValue());
					  sbSOUB.append("],");
					  sbSOUB.append("['Outros',");
					  sbSOUB.append(100 - (tvb.getShareVisitorsSOUB().intValue()));
					  sbSOUB.append("]");
					  chartshareVisitorsSOUB = sbSOUB.toString();
	}
	
	
	public static void main(String[] args) throws Exception {
		TotalVisitorsBean tvb = new TotalVisitorsBean();
		System.out.println(tvb.getChartshareVisitorsACOM());
		System.out.println(tvb.getChartshareVisitorsSUBA());
		System.out.println(tvb.getChartshareVisitorsSHOP());
		System.out.println(tvb.getChartshareVisitorsSOUB());
	}
}
