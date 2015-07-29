package com.adobe.analytics.client.report;

import java.io.IOException;

import com.adobe.analytics.client.AnalyticsClient;
import com.adobe.analytics.client.ApiException;
import com.adobe.analytics.client.domain.ReportDescription;
import com.adobe.analytics.client.domain.ReportDescriptionDateGranularity;
import com.adobe.analytics.client.domain.ReportResponse;
import com.adobe.analytics.client.method.ReportMethods;
import com.adobe.analytics.client.method.ReportSuiteMethods;



public class DashboardReport {
	
	public ReportResponse getDashboardACOM(String reportsuite , String datapublicacao ) throws IOException, InterruptedException {
		AnalyticsClient client = AnalyticsClient.authenticateWithSecret(,"api3.omniture.com");
		ReportSuiteMethods suiteMethods = new ReportSuiteMethods(client);
		ReportDescription desc = new ReportDescription();
						  desc.setReportSuiteID(reportsuite);
						  desc.setDate(datapublicacao);
						  desc.setDateGranularity(ReportDescriptionDateGranularity.HOUR);
						  desc.setMetricIds("visits","orders","carts","bounces","entries");
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
	
	public static void main(String[] args) {
		DashboardReport drr = new DashboardReport();
		try {
			System.out.println(drr.getDashboardACOM("b2w-acom","2015-07-28").getReport().getMetrics().get(0).getLatency() /60);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}