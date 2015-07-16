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
import com.adobe.analytics.client.entity.AbandonCart;
import com.adobe.analytics.client.report.AbandonCartReport;

@ManagedBean
@SessionScoped
public class AbandonCartBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	List<AbandonCart> abandoncart;
	public String chartabandoncartACOM;
	BigDecimal totalAbandonCart;
	BigDecimal totalSumAbandonCart;
	Integer totalHours;
	GeneralBean gb = new GeneralBean();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String date = sdf.format(new Date()).toString();
	public AbandonCartBean() {
		 AbandonCartReport ac  =  new AbandonCartReport();
		 				abandoncart = new ArrayList<AbandonCart>();
		 				totalSumAbandonCart = BigDecimal.ZERO;
		 				totalHours = 0;
			 try {
				for(ReportData rd : ac.getAbandonCart("b2w-acom", date).getReport().getData()){
					BigDecimal abandonCart = BigDecimal.ZERO;
					 if(rd.getCounts().get(0)!=0 && rd.getCounts().get(1)!=0){
						 	abandonCart = new BigDecimal((1 - (rd.getCounts().get(0) /rd.getCounts().get(1))) * 100);
						 	totalHours = totalHours + 1;
					 }
					 AbandonCart abandon = new AbandonCart(rd.getHour().toString(),abandonCart.setScale(2, BigDecimal.ROUND_UP), rd.getCounts().get(0), rd.getCounts().get(1));
					 totalSumAbandonCart = totalSumAbandonCart.add(abandon.getAbandoncart());
					 abandoncart.add(abandon);
				 }
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	
	public List<AbandonCart> getAbandoncart() {
		return abandoncart;
	}



	public void setAbandoncart(List<AbandonCart> abandoncart) {
		this.abandoncart = abandoncart;
	}



	public String getChartabandoncartACOM() {
		if (chartabandoncartACOM == null || chartabandoncartACOM.trim().length() <= 0) {
			populateData();
		}
		return chartabandoncartACOM;
	}



	public void setChartabandoncartACOM(String chartabandoncartACOM) {
		this.chartabandoncartACOM = chartabandoncartACOM;
	}
	
	

	public BigDecimal getTotalAbandonCart() {
		BigDecimal totalAbandonCart = new BigDecimal((totalSumAbandonCart.doubleValue()/totalHours.doubleValue()));
		return totalAbandonCart.setScale(2, BigDecimal.ROUND_UP);
	}

	public void setTotalAbandonCart(BigDecimal totalAbandonCart) {
		this.totalAbandonCart = totalAbandonCart;
	}


	private void populateData() {
		AbandonCartBean ac = new AbandonCartBean();
		AbandonCartLYBean aly = new AbandonCartLYBean();
		StringBuilder stringBuilder = new StringBuilder();
					  stringBuilder.append("['Hora' ,");
					  stringBuilder.append(gb.getDateAtual());
					  stringBuilder.append(",");
					  stringBuilder.append(gb.getLastYear());
					  stringBuilder.append("],");
		for (int i=0; i < ac.getAbandoncart().size(); i++) {
					stringBuilder.append("['");
					stringBuilder.append(ac.getAbandoncart().get(i).getHour());
					stringBuilder.append("h',");
					if(ac.getAbandoncart().get(i).getAbandoncart().doubleValue() == 0 || aly.getAbandoncart().get(i).getAbandoncart().doubleValue() == 0 ){
						stringBuilder.append("0,0]");
						stringBuilder.append(",");
					}else{
						stringBuilder.append(ac.getAbandoncart().get(i).getAbandoncart());
						stringBuilder.append(",");
						stringBuilder.append(aly.getAbandoncart().get(i).getAbandoncart());
						stringBuilder.append("]");
						stringBuilder.append(",");
					}
				}
		chartabandoncartACOM = stringBuilder.toString();
	}
	
	
	public static void main(String[] args) {
		AbandonCartBean abb =  new AbandonCartBean();
		System.out.println(abb.getTotalAbandonCart());
	}
}

