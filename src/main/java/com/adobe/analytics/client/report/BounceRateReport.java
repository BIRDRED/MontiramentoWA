package com.adobe.analytics.client.report;

import java.io.IOException;

import com.adobe.analytics.client.AnalyticsClient;
import com.adobe.analytics.client.ApiException;
import com.adobe.analytics.client.domain.ReportDescription;
import com.adobe.analytics.client.domain.ReportDescriptionDateGranularity;
import com.adobe.analytics.client.domain.ReportResponse;
import com.adobe.analytics.client.method.ReportMethods;
import com.adobe.analytics.client.method.ReportSuiteMethods;



public class BounceRateReport {
	
	public ReportResponse getBounceRate(String reportsuite,String datapublicacao) throws IOException, InterruptedException {
		AnalyticsClient client = AnalyticsClient.authenticateWithSecret(,"api3.omniture.com");
		ReportSuiteMethods suiteMethods = new ReportSuiteMethods(client);
		ReportDescription desc = new ReportDescription();
						  desc.setReportSuiteID(reportsuite);
						  desc.setDate(datapublicacao);
						  desc.setDateGranularity(ReportDescriptionDateGranularity.HOUR);
						  desc.setMetricIds("bounces","entries");
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
		BounceRateReport brr = new BounceRateReport();
		try {
			System.out.println(brr.getBounceRate("b2w-acom", "2015-07-13"));
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

