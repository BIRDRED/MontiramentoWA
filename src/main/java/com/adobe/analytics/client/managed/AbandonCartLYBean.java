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
import com.adobe.analytics.client.entity.AbandonCart;
import com.adobe.analytics.client.report.AbandonCartReport;

@ManagedBean
@SessionScoped
public class AbandonCartLYBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	List<AbandonCart> abandoncart;
	public AbandonCartLYBean() {
		 AbandonCartReport ac  =  new AbandonCartReport();
		 				abandoncart = new ArrayList<AbandonCart>();
		 				Calendar c = Calendar.getInstance();
		 				Integer ano = c.get(Calendar.YEAR) - 1 ;
		 						 c.set(Calendar.YEAR, ano );
		 				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 				String date = sdf.format(c.getTime()).toString();
			 try {
				for(ReportData rd : ac.getAbandonCart("b2w-acom", date).getReport().getData()){
					BigDecimal abandonCart = BigDecimal.ZERO;
					 if(rd.getCounts().get(0)!=0 && rd.getCounts().get(1)!=0){
						 	abandonCart = new BigDecimal((1 - (rd.getCounts().get(0) /rd.getCounts().get(1))) * 100);
					 }
					 AbandonCart abandon = new AbandonCart(rd.getHour().toString(),abandonCart.setScale(2, BigDecimal.ROUND_UP), rd.getCounts().get(0), rd.getCounts().get(1));
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






}

