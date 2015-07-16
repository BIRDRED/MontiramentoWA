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
import com.adobe.analytics.client.entity.BounceRate;
import com.adobe.analytics.client.report.BounceRateReport;

@ManagedBean
@SessionScoped
public class BounceRateBean implements Serializable {
	 
	 
	private static final long serialVersionUID = 1L;
	List<BounceRate> bouncerate;
	public String chartBouncerateACOM;
	BigDecimal totalBounce;
	BigDecimal totalSumBounce;
	Integer totalHours;
	GeneralBean gb =  new GeneralBean();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String date = sdf.format(new Date()).toString();
	public BounceRateBean() {
		 BounceRateReport br  =  new BounceRateReport();
		 				  bouncerate = new ArrayList<BounceRate>();
		 				  totalSumBounce = BigDecimal.ZERO;
		 				  totalHours = 0;
			 try {
				for(ReportData rd : br.getBounceRate("b2w-acom",date).getReport().getData()){
					BigDecimal bounceratepct = BigDecimal.ZERO;
					 if(rd.getCounts().get(0)!=0 && rd.getCounts().get(1) != 0 ){
						 		bounceratepct = new BigDecimal((rd.getCounts().get(0) /rd.getCounts().get(1)) * 100);
						 		totalHours = totalHours + 1;
					 }
					 BounceRate brate = new BounceRate(rd.getHour().toString(),bounceratepct.setScale(2, BigDecimal.ROUND_UP));
					 			totalSumBounce = totalSumBounce.add(brate.getBouncerate());
					 			bouncerate.add(brate);
					 
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

	public String getChartBouncerateACOM() {
		if (chartBouncerateACOM == null || chartBouncerateACOM.trim().length() <= 0) {
			populateData();
		}
		return chartBouncerateACOM;
	}

	public void setChartBouncerateACOM(String chartBouncerateACOM) {
		this.chartBouncerateACOM = chartBouncerateACOM;
	}

	public BigDecimal getTotalBounce() {
		BigDecimal totalBounceRate = new BigDecimal((totalSumBounce.doubleValue()/totalHours.doubleValue()));
		return totalBounceRate.setScale(2, BigDecimal.ROUND_UP);
	}

	public void setTotalBounce(BigDecimal totalBounce) {
		this.totalBounce = totalBounce;
	}
	
	private void populateData() {
		BounceRateBean br = new BounceRateBean();
		BounceRateLYBean brly = new BounceRateLYBean();
		StringBuilder stringBuilder = new StringBuilder();
					  stringBuilder.append("['Hora' ,");
					  stringBuilder.append(gb.getDateAtual());
					  stringBuilder.append(",");
					  stringBuilder.append(gb.getLastYear());
					  stringBuilder.append("],");
		for (int i=0; i < br.getBouncerate().size(); i++) {
					stringBuilder.append("['");
					stringBuilder.append(br.getBouncerate().get(i).getHour());
					stringBuilder.append("h',");
					if(br.getBouncerate().get(i).getBouncerate().doubleValue() == 0 || brly.getBouncerate().get(i).getBouncerate().doubleValue() == 0 ){
						stringBuilder.append("0,0]");
						stringBuilder.append(",");
					}else{
						stringBuilder.append(br.getBouncerate().get(i).getBouncerate());
						stringBuilder.append(",");
						stringBuilder.append(brly.getBouncerate().get(i).getBouncerate());
						stringBuilder.append("]");
						stringBuilder.append(",");
					}
				}
		chartBouncerateACOM = stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		BounceRateBean brb =  new BounceRateBean();
		System.out.println(brb.getTotalBounce());
	}
	

	
}

