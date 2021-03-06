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
import com.adobe.analytics.client.entity.BounceRate;
import com.adobe.analytics.client.report.BounceRateReport;

@ManagedBean
@SessionScoped
public class BounceRateLYBean implements Serializable {
	 
	 
	private static final long serialVersionUID = 1L;
	List<BounceRate> bouncerate;
	public BounceRateLYBean() {
		 BounceRateReport br  =  new BounceRateReport();
		 				 bouncerate = new ArrayList<BounceRate>();
			 				Calendar c = Calendar.getInstance();
			 				Integer ano = c.get(Calendar.YEAR) - 1 ;
			 						 c.set(Calendar.YEAR, ano );
			 				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 				String date = sdf.format(c.getTime()).toString();
			 try {
				for(ReportData rd : br.getBounceRate("b2w-acom",date).getReport().getData()){
					BigDecimal bounceratepct = BigDecimal.ZERO;
					 if(rd.getCounts().get(0)!=0 && rd.getCounts().get(1) != 0 ){
						 		bounceratepct = new BigDecimal((rd.getCounts().get(0) /rd.getCounts().get(1)) * 100);
					 }
					 BounceRate brate = new BounceRate(rd.getHour().toString(),bounceratepct.setScale(2, BigDecimal.ROUND_UP));
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
	

	
}

