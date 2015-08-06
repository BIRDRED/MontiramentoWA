package com.adobe.analytics.client.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.adobe.analytics.client.AnalyticsClient;
import com.adobe.analytics.client.ApiException;
import com.adobe.analytics.client.domain.ReportDescription;
import com.adobe.analytics.client.domain.ReportDescriptionDateGranularity;
import com.adobe.analytics.client.domain.ReportDescriptionElement;
import com.adobe.analytics.client.domain.ReportResponse;
import com.adobe.analytics.client.method.ReportMethods;
import com.adobe.analytics.client.method.ReportSuiteMethods;



public class EparReport {
	
	public ReportResponse getEparReport(String reportsuite , String datapublicacao ) throws IOException, InterruptedException {
		AnalyticsClient client = AnalyticsClient.authenticateWithSecret("api3.omniture.com");
		ReportSuiteMethods suiteMethods = new ReportSuiteMethods(client);
		List<ReportDescriptionElement> elements = new ArrayList<ReportDescriptionElement>();
		ReportDescriptionElement element = new ReportDescriptionElement();
								element.setId("trackingcode");
								element.setClassification("Midia");
								element.setTop(15);
		elements.add(0, element);
								 
		ReportDescription desc = new ReportDescription();
						  desc.setReportSuiteID(reportsuite);
						  desc.setDate(datapublicacao);
						  desc.setDateGranularity(ReportDescriptionDateGranularity.HOUR);
						  desc.setMetricIds("visits","orders");
						  desc.setElements(elements);
	    ReportMethods reportMethods = new ReportMethods(client);
		int reportId = reportMethods.queue(desc);
		ReportResponse response = null;
		while (response == null) {
		    try {
		        response = reportMethods.get(reportId);
		    } catch (ApiException e) {
		        if ("report_not_ready".equals(e.getError())) {
		            Thread.sleep(3000);
		            continue;
		        }
		        throw e;
		    }
		}
		return response;
	
	}
	public static void main(String[] args) throws IOException, InterruptedException {
		EparReport epar = new EparReport();
		for(int j=0; j< 24; j++){
			for(int i=0; i < 10 ; i++){
				System.out.println(epar.getEparReport("b2w-shop", "2014-08-04").getReport().getData().get(j).getBreakdown().get(i).getName());
			}
		}
	}
	
}