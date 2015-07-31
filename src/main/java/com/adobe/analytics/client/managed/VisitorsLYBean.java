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
import com.adobe.analytics.client.entity.Visitors;
import com.adobe.analytics.client.report.VisitsReport;

@ManagedBean
@SessionScoped
public class VisitorsLYBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	List<Visitors> visitors;
	List<Visitors> visitorsly;
	   					
	public VisitorsLYBean() {
		 VisitsReport v  =  new VisitsReport();
		 visitors = new ArrayList<Visitors>();
		 Calendar c = Calendar.getInstance();
			Integer ano = c.get(Calendar.YEAR) - 1 ;
					 c.set(Calendar.YEAR, ano );
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(c.getTime()).toString();
			 try {
				for(ReportData r : v.getvisits("b2w-acom" , date).getReport().getData()){
					 BigDecimal visitorsACOM = new BigDecimal(r.getCounts().get(0));
					 Visitors vis = new Visitors(r.getHour(), visitorsACOM);
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
	
}
