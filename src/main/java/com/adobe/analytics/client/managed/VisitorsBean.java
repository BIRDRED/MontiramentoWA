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
import com.adobe.analytics.client.entity.Visitors;
import com.adobe.analytics.client.report.VisitsReport;

@ManagedBean
@SessionScoped
public class VisitorsBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	List<Visitors> visitors;
	List<Visitors> visitorsly;
	BigDecimal totalVisitorsACOM;
	Integer totalVisitors;
	public String chartVisitorsACOM;
	
	public VisitorsBean() {
		 VisitsReport v  =  new VisitsReport();
		 visitors = new ArrayList<Visitors>();
		 totalVisitorsACOM = BigDecimal.ZERO;
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 String date = sdf.format(new Date()).toString();
			 try {
				 
				for(ReportData r : v.getvisits("b2w-acom" , date).getReport().getData()){
					BigDecimal visitorsACOM = BigDecimal.ZERO;
					if(r.getCounts().get(0) != 0){
							visitorsACOM = new BigDecimal(r.getCounts().get(0));
					}
					 Visitors vis = new Visitors(r.getHour() , visitorsACOM);
					 totalVisitorsACOM = totalVisitorsACOM.add(vis.getVisitors());
					 visitors.add(vis);
				 }
			} catch (IOException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	public List<Visitors> getVisitors() {
		return visitors;
	}
	public void setVisitors(List<Visitors> visitors) {
		this.visitors = visitors;
	}
	public Integer getTotalVisitors() {
		BigDecimal tv = BigDecimal.ZERO;
		if(totalVisitorsACOM.toString().length() > 3){
			tv = new BigDecimal((totalVisitorsACOM.doubleValue()/1000));
		}
		return tv.intValue();
	}
	public void setTotalVisitors(Integer totalVisitors) {
		this.totalVisitors = totalVisitors;
	}


	private void populateData() {
		VisitorsBean mb = new VisitorsBean();
		VisitorsLYBean vly = new VisitorsLYBean();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i=0; i < mb.getVisitors().size(); i++) {
						stringBuilder.append("[{ v: [");
						stringBuilder.append(mb.getVisitors().get(i).getHour());
						stringBuilder.append(", 0, 0], f: '");
						stringBuilder.append(mb.getVisitors().get(i).getHour());
						stringBuilder.append(" am'},");
					if(mb.getVisitors().get(i).getVisitors().doubleValue() == 0 || vly.getVisitors().get(i).getVisitors().doubleValue() == 0 ){
						stringBuilder.append("0,0]");
						stringBuilder.append(",");
					}else{
						stringBuilder.append(mb.getVisitors().get(i).getVisitors().doubleValue()/1000);
						stringBuilder.append(",");
						//BigDecimal vlypct = new BigDecimal(vly.getVisitors().get(i).getVisitors().doubleValue() / mb.getVisitors().get(i).getVisitors().doubleValue());
						//stringBuilder.append(vlypct.setScale(2, BigDecimal.ROUND_UP));
						stringBuilder.append(vly.getVisitors().get(i).getVisitors().doubleValue()/1000);
						stringBuilder.append("]");
						stringBuilder.append(",");
					}
				}
				chartVisitorsACOM = stringBuilder.toString();
	}

	
	public String getChartVisitorsACOM() {
		if (chartVisitorsACOM == null || chartVisitorsACOM.trim().length() <= 0) {
				populateData();
			}
			return chartVisitorsACOM;

	}
	public void setChartVisitorsACOM(String chartVisitorsACOM) {
		this.chartVisitorsACOM = chartVisitorsACOM;
	}
	
	public static void main(String[] args) {
		VisitorsBean mb = new VisitorsBean();
		System.out.println(mb.getTotalVisitors());
	}
}
