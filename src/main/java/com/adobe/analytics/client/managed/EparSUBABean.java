package com.adobe.analytics.client.managed;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.adobe.analytics.client.domain.ReportData;
import com.adobe.analytics.client.domain.ReportResponse;
import com.adobe.analytics.client.entity.Epar;
import com.adobe.analytics.client.report.EparReport;

@ManagedBean
@SessionScoped
public class EparSUBABean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//Variaveis de Listas 
	List<Epar> eparlist;
	List<Epar> eparlistLY;
	
	
	//Variaveis de Somas dos Totais
	BigDecimal totalTrafegoDiretoVisits;
	BigDecimal totalTrafegoDiretoOrders;
	BigDecimal totalPPCVisits;
	BigDecimal totalPPCOrders;
	BigDecimal totalEmailMarketingVisits;
	BigDecimal totalEmailMarketingOrders;
	BigDecimal totalBuscaOrganicaVisits;
	BigDecimal totalBuscaOrganicaOrders;
	BigDecimal totalDisplayVisits;
	BigDecimal totalDisplayOrders;
	BigDecimal totalAfiliadosVisits;
	BigDecimal totalAfiliadosOrders;
	BigDecimal totalComparadoresVisits;
	BigDecimal totalComparadoresOrders;
	BigDecimal totalSocialAdsVisits;
	BigDecimal totalSocialAdsOrders;
	BigDecimal totalPlanoMidiaVisits;
	BigDecimal totalPlanoMidiaOrders;
	BigDecimal totalMktDiretoVisits;
	BigDecimal totalMktDiretoOrders;
	BigDecimal totalOutrosVisits;
	BigDecimal totalOutrosOrders;
	
	//Variaveis de Somas dos Totais - ULTIMO ANO
	BigDecimal totalTrafegoDiretoVisitsLY;
	BigDecimal totalTrafegoDiretoOrdersLY;
	BigDecimal totalPPCVisitsLY;
	BigDecimal totalPPCOrdersLY;
	BigDecimal totalEmailMarketingVisitsLY;
	BigDecimal totalEmailMarketingOrdersLY;
	BigDecimal totalBuscaOrganicaVisitsLY;
	BigDecimal totalBuscaOrganicaOrdersLY;
	BigDecimal totalDisplayVisitsLY;
	BigDecimal totalDisplayOrdersLY;
	BigDecimal totalAfiliadosVisitsLY;
	BigDecimal totalAfiliadosOrdersLY;
	BigDecimal totalComparadoresVisitsLY;
	BigDecimal totalComparadoresOrdersLY;
	BigDecimal totalSocialAdsVisitsLY;
	BigDecimal totalSocialAdsOrdersLY;
	BigDecimal totalPlanoMidiaVisitsLY;
	BigDecimal totalPlanoMidiaOrdersLY;
	BigDecimal totalMktDiretoVisitsLY;
	BigDecimal totalMktDiretoOrdersLY;
	BigDecimal totalOutrosVisitsLY;
	BigDecimal totalOutrosOrdersLY;
	
	//Variaveis de Gráficos
	String chartEpar;
	//Total de Horas
	Integer totalHours;
	
	//Latencia
	BigDecimal latenciaVisitors;
	
	//Definição de Data - Esse Ano
	GeneralBean gb = new GeneralBean();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String date = sdf.format(new Date()).toString();
	
	
	
	public EparSUBABean() throws IOException, InterruptedException {
		 EparReport ert = new EparReport();
		 ReportResponse report =  ert.getEparReport("b2w-suba", date);
		 			//Inicialização de Variaveis
			 		eparlist = new ArrayList<Epar>();
			 		eparlistLY = new ArrayList<Epar>();
			 				
		 			//Inicialização de Soma 
							totalTrafegoDiretoVisits = BigDecimal.ZERO;
							totalTrafegoDiretoOrders = BigDecimal.ZERO;
							totalPPCVisits = BigDecimal.ZERO;
							totalPPCOrders = BigDecimal.ZERO;
							totalEmailMarketingVisits = BigDecimal.ZERO;
							totalEmailMarketingOrders = BigDecimal.ZERO;
							totalBuscaOrganicaVisits = BigDecimal.ZERO;
							totalBuscaOrganicaOrders = BigDecimal.ZERO;
							totalDisplayVisits = BigDecimal.ZERO;
							totalDisplayOrders = BigDecimal.ZERO;
							totalAfiliadosVisits = BigDecimal.ZERO;
							totalAfiliadosOrders = BigDecimal.ZERO;
							totalComparadoresVisits = BigDecimal.ZERO;
							totalComparadoresOrders = BigDecimal.ZERO;
							totalSocialAdsVisits = BigDecimal.ZERO;
							totalSocialAdsOrders = BigDecimal.ZERO;
							totalPlanoMidiaVisits = BigDecimal.ZERO;
							totalPlanoMidiaOrders = BigDecimal.ZERO;
							totalMktDiretoVisits = BigDecimal.ZERO;
							totalMktDiretoOrders = BigDecimal.ZERO;
							totalOutrosVisits = BigDecimal.ZERO;
							totalOutrosOrders = BigDecimal.ZERO;
							
					//Inicialização de Soma - Ultimo Ano
							totalTrafegoDiretoVisitsLY = BigDecimal.ZERO;
							totalTrafegoDiretoOrdersLY = BigDecimal.ZERO;
							totalPPCVisitsLY = BigDecimal.ZERO;
							totalPPCOrdersLY = BigDecimal.ZERO;
							totalEmailMarketingVisitsLY = BigDecimal.ZERO;
							totalEmailMarketingOrdersLY = BigDecimal.ZERO;
							totalBuscaOrganicaVisitsLY = BigDecimal.ZERO;
							totalBuscaOrganicaOrdersLY = BigDecimal.ZERO;
							totalDisplayVisitsLY = BigDecimal.ZERO;
							totalDisplayOrdersLY = BigDecimal.ZERO;
							totalAfiliadosVisitsLY = BigDecimal.ZERO;
							totalAfiliadosOrdersLY = BigDecimal.ZERO;
							totalComparadoresVisitsLY = BigDecimal.ZERO;
							totalComparadoresOrdersLY = BigDecimal.ZERO;
							totalSocialAdsVisitsLY = BigDecimal.ZERO;
							totalSocialAdsOrdersLY = BigDecimal.ZERO;
							totalPlanoMidiaVisitsLY = BigDecimal.ZERO;
							totalPlanoMidiaOrdersLY = BigDecimal.ZERO;
							totalMktDiretoVisitsLY = BigDecimal.ZERO;
							totalMktDiretoOrdersLY = BigDecimal.ZERO;
							totalOutrosVisitsLY = BigDecimal.ZERO;
							totalOutrosOrdersLY = BigDecimal.ZERO;
			 		 //Inicialização de Hora
		 				totalHours = 0;
		 				
		 			//Latencia dos Dados
		 				latenciaVisitors = new BigDecimal(report.getReport().getMetrics().get(0).getLatency());
			if(report.getReport().getTotals().get(0) > 0  && report.getReport().getTotals().get(0) > 0){
			 	for(ReportData rdData : report.getReport().getData()){
						//Faz a soma dos Variaveis
						 for(ReportData rd : rdData.getBreakdown()){
							 if(rd.getCounts().get(0) > 0 && rd.getCounts().get(1) >0){
							 	if(rd.getName().equals("BUSCA ORGANICA")){
							 		totalBuscaOrganicaVisits = new BigDecimal(totalBuscaOrganicaVisits.doubleValue() + rd.getCounts().get(0)); 
							 		totalBuscaOrganicaOrders = new BigDecimal(totalBuscaOrganicaOrders.doubleValue() + rd.getCounts().get(1));
							 	}else if(rd.getName().equals("TRAFEGO DIRETO")){
							 		totalTrafegoDiretoVisits = new BigDecimal(totalTrafegoDiretoVisits.doubleValue() + rd.getCounts().get(0)); 
							 		totalTrafegoDiretoOrders = new BigDecimal(totalTrafegoDiretoOrders.doubleValue() + rd.getCounts().get(1));
							 	}else if(rd.getName().equals("EMAIL MARKETING")){
							 		totalEmailMarketingVisits = new BigDecimal(totalEmailMarketingVisits.doubleValue() + rd.getCounts().get(0)); 
							 		totalEmailMarketingOrders = new BigDecimal(totalEmailMarketingOrders.doubleValue() + rd.getCounts().get(1));
							 	}else if(rd.getName().equals("COMPARADORES")){
							 		totalComparadoresVisits = new BigDecimal(totalComparadoresVisits.doubleValue() + rd.getCounts().get(0)); 
							 		totalComparadoresOrders = new BigDecimal(totalComparadoresOrders.doubleValue() + rd.getCounts().get(1));
							 	}else if(rd.getName().equals("AFILIADOS")){
							 		totalAfiliadosVisits = new BigDecimal(totalAfiliadosVisits.doubleValue() + rd.getCounts().get(0)); 
							 		totalAfiliadosOrders = new BigDecimal(totalAfiliadosOrders.doubleValue() + rd.getCounts().get(1));
							 	}else if(rd.getName().equals("BUSCA PPC")){
							 		totalPPCVisits = new BigDecimal(totalPPCVisits.doubleValue() + rd.getCounts().get(0)); 
							 		totalPPCOrders = new BigDecimal(totalPPCOrders.doubleValue() + rd.getCounts().get(1));
							 	}else if(rd.getName().equals("DISPLAY")){
							 		totalDisplayVisits = new BigDecimal(totalDisplayVisits.doubleValue() + rd.getCounts().get(0)); 
							 		totalDisplayOrders = new BigDecimal(totalDisplayOrders.doubleValue() + rd.getCounts().get(1));
							 	}else if(rd.getName().equals("MARKETING DIRETO")){
							 		totalMktDiretoVisits = new BigDecimal(totalMktDiretoVisits.doubleValue() + rd.getCounts().get(0)); 
							 		totalMktDiretoOrders = new BigDecimal(totalMktDiretoOrders.doubleValue() + rd.getCounts().get(1));
							 	}else if(rd.getName().equals("SOCIAL ADS")){
							 		totalSocialAdsVisits = new BigDecimal(totalSocialAdsVisits.doubleValue() + rd.getCounts().get(0)); 
							 		totalSocialAdsOrders = new BigDecimal(totalSocialAdsOrders.doubleValue() + rd.getCounts().get(1));
							 	}else if(rd.getName().equals("PLANO DE MIDIA")){
							 		totalPlanoMidiaVisits = new BigDecimal(totalPlanoMidiaVisits.doubleValue() + rd.getCounts().get(0)); 
							 		totalPlanoMidiaOrders = new BigDecimal(totalPlanoMidiaOrders.doubleValue() + rd.getCounts().get(1));
							 	}
							 }
						 }
							 if(rdData.getBreakdownTotal().get(0) > 0 && rdData.getBreakdownTotal().get(1) > 0){
							 	totalHours = totalHours + 1;
							 }
			 		}
			}
		 	
			//Instancia dos dados e Adicionar na Lista
					if(totalAfiliadosOrders.doubleValue() > 0 && totalAfiliadosVisits.doubleValue() > 0){
						 	Epar eparAfiliados = new Epar("AFILIADOS ","AFILIADOS", totalAfiliadosVisits, totalAfiliadosOrders);
						 	eparlist.add(eparAfiliados);
				 		}
					 if(totalBuscaOrganicaOrders.doubleValue() > 0 && totalBuscaOrganicaVisits.doubleValue() > 0){
						 	Epar eparBuscaOrganica = new Epar("BUSCA ORGANICA","BUSCA ORGANICA", totalBuscaOrganicaVisits, totalBuscaOrganicaOrders);
						 	eparlist.add(eparBuscaOrganica);
				 		}
					 if(totalComparadoresOrders.doubleValue() > 0 && totalComparadoresVisits.doubleValue() > 0){
						 	Epar eparComparadores = new Epar("COMPARADORES","COMPARADORES", totalComparadoresVisits, totalComparadoresOrders);
						 	eparlist.add(eparComparadores);
					 }
					 if(totalDisplayOrders.doubleValue() > 0 && totalDisplayVisits.doubleValue() > 0){
						 	Epar eparDisplay = new Epar("DISPLAY","DISPLAY", totalDisplayOrders, totalDisplayVisits);
						 	eparlist.add(eparDisplay);
					 }					 	
					 if(totalEmailMarketingOrders.doubleValue() > 0 && totalEmailMarketingVisits.doubleValue() > 0){
						 	Epar eparDisplay = new Epar("E-MAIL","E-MAIL MARKETING", totalEmailMarketingOrders, totalEmailMarketingVisits);
						 	eparlist.add(eparDisplay);
					 }
					 if(totalMktDiretoOrders.doubleValue() > 0 && totalMktDiretoVisits.doubleValue() > 0){
						 	Epar eparMktDireto = new Epar("MARKETING DIRETO","MARKETING DIRETO", totalMktDiretoVisits, totalMktDiretoOrders);
						 	eparlist.add(eparMktDireto);
					 }
					 if(totalPlanoMidiaOrders.doubleValue() > 0 && totalPlanoMidiaVisits.doubleValue() > 0){
						 	Epar eparPlanoMidia = new Epar("PLANO DE MIDIA", "PLANO DE MIDIA", totalPlanoMidiaVisits, totalPlanoMidiaOrders);
						 	eparlist.add(eparPlanoMidia);
					 }
					 if(totalOutrosOrders.doubleValue() > 0 && totalOutrosVisits.doubleValue() > 0){
						 	Epar eparOutros = new Epar("OUTROS", "OUTROS", totalOutrosVisits, totalOutrosOrders);
						 	eparlist.add(eparOutros);
					 }
					 if(totalPPCOrders.doubleValue() > 0 && totalPPCVisits.doubleValue() > 0){
						 	Epar eparPPC = new Epar("PPC", "BUSCA PPC", totalPPCVisits, totalPPCOrders);
						 	eparlist.add(eparPPC);
					 }
					 if(totalSocialAdsOrders.doubleValue() > 0 && totalSocialAdsVisits.doubleValue() > 0){
						 	Epar eparSocialAds = new Epar("SOCIAL ADS", "SOCIAL ADS", totalSocialAdsVisits, totalSocialAdsOrders);
						 	eparlist.add(eparSocialAds);
					 }
					 if(totalTrafegoDiretoOrders.doubleValue() > 0 && totalTrafegoDiretoVisits.doubleValue() > 0){
						 	Epar eparTrafegoDireto = new Epar("TRAFEGO DIRETO", "TRAFEGO DIRETO", totalTrafegoDiretoVisits, totalTrafegoDiretoOrders);
						 	eparlist.add(eparTrafegoDireto);
					 }
					 
					 Calendar calendar = Calendar.getInstance();
					 Integer ano = calendar.get(Calendar.YEAR) - 1;
					 			   calendar.set(Calendar.YEAR, ano);
					 Integer mes = calendar.get(Calendar.MONTH) -1;
					 			   calendar.set(Calendar.MONTH, mes);
					 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					 String dately= sdf.format(calendar.getTime()).toString();
					 
					 EparReport ertly = new EparReport();
					 ReportResponse reportly =  ertly.getEparReport("b2w-suba", dately);
					 if(reportly.getReport().getTotals().get(0) > 0  || reportly.getReport().getTotals().get(0) > 0){
						 	for(ReportData rdDataLY : reportly.getReport().getData()){
						 		if(rdDataLY.getHour() <= totalHours ){
									//Faz a soma dos Variaveis
									 for(ReportData rdly : rdDataLY.getBreakdown()){
										 if(rdly.getCounts().get(0) > 0 || rdly.getCounts().get(1) >0){
										 	if(rdly.getName().equals("BUSCA ORGANICA")){
										 		totalBuscaOrganicaVisitsLY = new BigDecimal(totalBuscaOrganicaVisitsLY.doubleValue() + rdly.getCounts().get(0)); 
										 		totalBuscaOrganicaOrdersLY = new BigDecimal(totalBuscaOrganicaOrdersLY.doubleValue() + rdly.getCounts().get(1));
										 	}else if(rdly.getName().equals("TRAFEGO DIRETO")){
										 		totalTrafegoDiretoVisitsLY = new BigDecimal(totalTrafegoDiretoVisitsLY.doubleValue() + rdly.getCounts().get(0)); 
										 		totalTrafegoDiretoOrdersLY = new BigDecimal(totalTrafegoDiretoOrdersLY.doubleValue() + rdly.getCounts().get(1));
										 	}else if(rdly.getName().equals("EMAIL MARKETING")){
										 		totalEmailMarketingVisitsLY = new BigDecimal(totalEmailMarketingVisitsLY.doubleValue() + rdly.getCounts().get(0)); 
										 		totalEmailMarketingOrdersLY = new BigDecimal(totalEmailMarketingOrdersLY.doubleValue() + rdly.getCounts().get(1));
										 	}else if(rdly.getName().equals("COMPARADORES")){
										 		totalComparadoresVisitsLY = new BigDecimal(totalComparadoresVisitsLY.doubleValue() + rdly.getCounts().get(0)); 
										 		totalComparadoresOrdersLY = new BigDecimal(totalComparadoresOrdersLY.doubleValue() + rdly.getCounts().get(1));
										 	}else if(rdly.getName().equals("AFILIADOS")){
										 		totalAfiliadosVisitsLY = new BigDecimal(totalAfiliadosVisitsLY.doubleValue() + rdly.getCounts().get(0)); 
										 		totalAfiliadosOrdersLY = new BigDecimal(totalAfiliadosOrdersLY.doubleValue() + rdly.getCounts().get(1));
										 	}else if(rdly.getName().equals("BUSCA PPC")){
										 		totalPPCVisitsLY = new BigDecimal(totalPPCVisitsLY.doubleValue() + rdly.getCounts().get(0)); 
										 		totalPPCOrdersLY = new BigDecimal(totalPPCOrdersLY.doubleValue() + rdly.getCounts().get(1));
										 	}else if(rdly.getName().equals("DISPLAY")){
										 		totalDisplayVisitsLY = new BigDecimal(totalDisplayVisitsLY.doubleValue() + rdly.getCounts().get(0)); 
										 		totalDisplayOrdersLY = new BigDecimal(totalDisplayOrdersLY.doubleValue() + rdly.getCounts().get(1));
										 	}else if(rdly.getName().equals("MARKETING DIRETO")){
										 		totalMktDiretoVisitsLY = new BigDecimal(totalMktDiretoVisitsLY.doubleValue() + rdly.getCounts().get(0)); 
										 		totalMktDiretoOrdersLY = new BigDecimal(totalMktDiretoOrdersLY.doubleValue() + rdly.getCounts().get(1));
										 	}else if(rdly.getName().equals("SOCIAL ADS")){
										 		totalSocialAdsVisitsLY = new BigDecimal(totalSocialAdsVisitsLY.doubleValue() + rdly.getCounts().get(0)); 
										 		totalSocialAdsOrdersLY = new BigDecimal(totalSocialAdsOrdersLY.doubleValue() + rdly.getCounts().get(1));
										 	}else if(rdly.getName().equals("PLANO DE MIDIA")){
										 		totalPlanoMidiaVisitsLY = new BigDecimal(totalPlanoMidiaVisitsLY.doubleValue() + rdly.getCounts().get(0)); 
										 		totalPlanoMidiaOrdersLY = new BigDecimal(totalPlanoMidiaOrdersLY.doubleValue() + rdly.getCounts().get(1));
										 	}else{
										 		totalOutrosVisitsLY = new BigDecimal(totalOutrosVisitsLY.doubleValue() + rdly.getCounts().get(0)); 
										 		totalOutrosOrdersLY = new BigDecimal(totalOutrosOrdersLY.doubleValue() + rdly.getCounts().get(1));
										 	} 	
										 }
									 }
						 		}
						 	}
						}
					 
					//Instancia dos dados e Adicionar na Lista
						if(totalAfiliadosOrdersLY.doubleValue() > 0 && totalAfiliadosVisitsLY.doubleValue() > 0){
							 	Epar eparAfiliados = new Epar("AFI ","AFILIADOS", totalAfiliadosVisitsLY, totalAfiliadosOrdersLY);
							 	eparlistLY.add(eparAfiliados);
					 		}
						 if(totalBuscaOrganicaOrdersLY.doubleValue() > 0 && totalBuscaOrganicaVisitsLY.doubleValue() > 0){
							 	Epar eparBuscaOrganica = new Epar("BORG","BUSCA ORGANICA", totalBuscaOrganicaVisitsLY, totalBuscaOrganicaOrdersLY);
							 	eparlistLY.add(eparBuscaOrganica);
					 		}
						 if(totalComparadoresOrdersLY.doubleValue() > 0 && totalComparadoresVisitsLY.doubleValue() > 0){
							 	Epar eparComparadores = new Epar("COMP","COMPARADORES", totalComparadoresVisitsLY, totalComparadoresOrdersLY);
							 	eparlistLY.add(eparComparadores);
						 }
						 if(totalDisplayOrdersLY.doubleValue() > 0 && totalDisplayVisitsLY.doubleValue() > 0){
							 	Epar eparDisplay = new Epar("DISP","DISPLAY", totalDisplayOrdersLY, totalDisplayVisitsLY);
							 	eparlistLY.add(eparDisplay);
						 }					 	
						 if(totalEmailMarketingOrdersLY.doubleValue() > 0 && totalEmailMarketingVisitsLY.doubleValue() > 0){
							 	Epar eparDisplay = new Epar("EMAIL","E-MAIL MARKETING", totalEmailMarketingOrdersLY, totalEmailMarketingVisitsLY);
							 	eparlistLY.add(eparDisplay);
						 }
						 if(totalMktDiretoOrdersLY.doubleValue() > 0 && totalMktDiretoVisitsLY.doubleValue() > 0){
							 	Epar eparMktDireto = new Epar("MKTD","MARKETING DIRETO", totalMktDiretoVisitsLY, totalMktDiretoOrdersLY);
							 	eparlistLY.add(eparMktDireto);
						 }
						 if(totalPlanoMidiaOrdersLY.doubleValue() > 0 && totalPlanoMidiaVisitsLY.doubleValue() > 0){
							 	Epar eparPlanoMidia = new Epar("PMID", "PLANO DE MIDIA", totalPlanoMidiaVisitsLY, totalPlanoMidiaOrdersLY);
							 	eparlistLY.add(eparPlanoMidia);
						 }
						 if(totalOutrosOrdersLY.doubleValue() > 0 && totalOutrosVisitsLY.doubleValue() > 0){
							 	Epar eparOutros = new Epar("OUT", "OUTROS", totalOutrosVisitsLY, totalOutrosOrdersLY);
							 	eparlistLY.add(eparOutros);
						 }
						 if(totalPPCOrdersLY.doubleValue() > 0 && totalPPCVisitsLY.doubleValue() > 0){
							 	Epar eparPPC = new Epar("PPC", "BUSCA PPC", totalPPCVisitsLY, totalPPCOrdersLY);
							 	eparlistLY.add(eparPPC);
						 }
						 if(totalSocialAdsOrdersLY.doubleValue() > 0 && totalSocialAdsVisitsLY.doubleValue() > 0){
							 	Epar eparSocialAds = new Epar("SADS", "SOCIAL ADS", totalSocialAdsVisitsLY, totalSocialAdsOrdersLY);
							 	eparlistLY.add(eparSocialAds);
						 }
						 if(totalTrafegoDiretoOrdersLY.doubleValue() > 0 && totalTrafegoDiretoVisitsLY.doubleValue() > 0){
							 	Epar eparTrafegoDireto = new Epar("TRDIR", "TRAFEGO DIRETO", totalTrafegoDiretoVisitsLY, totalTrafegoDiretoOrdersLY);
							 	eparlistLY.add(eparTrafegoDireto);
						 }
		 	
	}
	
	public BigDecimal getCrescimentoPCT(Double CurrentYear , Double LastYear){
		BigDecimal diffPCT =  new BigDecimal(CurrentYear - LastYear);
		BigDecimal crescPCT = new BigDecimal((diffPCT.doubleValue()/LastYear) * 100);
		return crescPCT.setScale(2, BigDecimal.ROUND_UP);
	}
	
	public BigDecimal getTotalTrafegoDiretoVisits() {
		return totalTrafegoDiretoVisits;
	}


	public void setTotalTrafegoDiretoVisits(BigDecimal totalTrafegoDiretoVisits) {
		this.totalTrafegoDiretoVisits = totalTrafegoDiretoVisits;
	}


	public BigDecimal getTotalTrafegoDiretoOrders() {
		return totalTrafegoDiretoOrders;
	}


	public void setTotalTrafegoDiretoOrders(BigDecimal totalTrafegoDiretoOrders) {
		this.totalTrafegoDiretoOrders = totalTrafegoDiretoOrders;
	}


	public BigDecimal getTotalPPCVisits() {
		return totalPPCVisits;
	}


	public void setTotalPPCVisits(BigDecimal totalPPCVisits) {
		this.totalPPCVisits = totalPPCVisits;
	}


	public BigDecimal getTotalPPCOrders() {
		return totalPPCOrders;
	}


	public void setTotalPPCOrders(BigDecimal totalPPCOrders) {
		this.totalPPCOrders = totalPPCOrders;
	}


	public BigDecimal getTotalEmailMarketingVisits() {
		return totalEmailMarketingVisits;
	}


	public void setTotalEmailMarketingVisits(BigDecimal totalEmailMarketingVisits) {
		this.totalEmailMarketingVisits = totalEmailMarketingVisits;
	}


	public BigDecimal getTotalEmailMarketingOrders() {
		return totalEmailMarketingOrders;
	}


	public void setTotalEmailMarketingOrders(BigDecimal totalEmailMarketingOrders) {
		this.totalEmailMarketingOrders = totalEmailMarketingOrders;
	}


	public BigDecimal getTotalBuscaOrganicaVisits() {
		return totalBuscaOrganicaVisits;
	}


	public void setTotalBuscaOrganicaVisits(BigDecimal totalBuscaOrganicaVisits) {
		this.totalBuscaOrganicaVisits = totalBuscaOrganicaVisits;
	}


	public BigDecimal getTotalBuscaOrganicaOrders() {
		return totalBuscaOrganicaOrders;
	}


	public void setTotalBuscaOrganicaOrders(BigDecimal totalBuscaOrganicaOrders) {
		this.totalBuscaOrganicaOrders = totalBuscaOrganicaOrders;
	}


	public BigDecimal getTotalDisplayVisits() {
		return totalDisplayVisits;
	}


	public void setTotalDisplayVisits(BigDecimal totalDisplayVisits) {
		this.totalDisplayVisits = totalDisplayVisits;
	}


	public BigDecimal getTotalDisplayOrders() {
		return totalDisplayOrders;
	}


	public void setTotalDisplayOrders(BigDecimal totalDisplayOrders) {
		this.totalDisplayOrders = totalDisplayOrders;
	}


	public BigDecimal getTotalAfiliadosVisits() {
		return totalAfiliadosVisits;
	}


	public void setTotalAfiliadosVisits(BigDecimal totalAfiliadosVisits) {
		this.totalAfiliadosVisits = totalAfiliadosVisits;
	}


	public BigDecimal getTotalAfiliadosOrders() {
		return totalAfiliadosOrders;
	}


	public void setTotalAfiliadosOrders(BigDecimal totalAfiliadosOrders) {
		this.totalAfiliadosOrders = totalAfiliadosOrders;
	}


	public BigDecimal getTotalComparadoresVisits() {
		return totalComparadoresVisits;
	}


	public void setTotalComparadoresVisits(BigDecimal totalComparadoresVisits) {
		this.totalComparadoresVisits = totalComparadoresVisits;
	}


	public BigDecimal getTotalComparadoresOrders() {
		return totalComparadoresOrders;
	}


	public void setTotalComparadoresOrders(BigDecimal totalComparadoresOrders) {
		this.totalComparadoresOrders = totalComparadoresOrders;
	}


	public BigDecimal getTotalSocialAdsVisits() {
		return totalSocialAdsVisits;
	}


	public void setTotalSocialAdsVisits(BigDecimal totalSocialAdsVisits) {
		this.totalSocialAdsVisits = totalSocialAdsVisits;
	}


	public BigDecimal getTotalSocialAdsOrders() {
		return totalSocialAdsOrders;
	}


	public void setTotalSocialAdsOrders(BigDecimal totalSocialAdsOrders) {
		this.totalSocialAdsOrders = totalSocialAdsOrders;
	}


	public BigDecimal getTotalPlanoMidiaVisits() {
		return totalPlanoMidiaVisits;
	}


	public void setTotalPlanoMidiaVisits(BigDecimal totalPlanoMidiaVisits) {
		this.totalPlanoMidiaVisits = totalPlanoMidiaVisits;
	}


	public BigDecimal getTotalPlanoMidiaOrders() {
		return totalPlanoMidiaOrders;
	}


	public void setTotalPlanoMidiaOrders(BigDecimal totalPlanoMidiaOrders) {
		this.totalPlanoMidiaOrders = totalPlanoMidiaOrders;
	}


	public BigDecimal getTotalMktDiretoVisits() {
		return totalMktDiretoVisits;
	}


	public void setTotalMktDiretoVisits(BigDecimal totalMktDiretoVisits) {
		this.totalMktDiretoVisits = totalMktDiretoVisits;
	}


	public BigDecimal getTotalMktDiretoOrders() {
		return totalMktDiretoOrders;
	}


	public void setTotalMktDiretoOrders(BigDecimal totalMktDiretoOrders) {
		this.totalMktDiretoOrders = totalMktDiretoOrders;
	}


	public BigDecimal getTotalOutrosVisits() {
		return totalOutrosVisits;
	}


	public void setTotalOutrosVisits(BigDecimal totalOutrosVisits) {
		this.totalOutrosVisits = totalOutrosVisits;
	}


	public BigDecimal getTotalOutrosOrders() {
		return totalOutrosOrders;
	}


	public void setTotalOutrosOrders(BigDecimal totalOutrosOrders) {
		this.totalOutrosOrders = totalOutrosOrders;
	}


	public BigDecimal getTotalTrafegoDiretoVisitsLY() {
		return totalTrafegoDiretoVisitsLY;
	}


	public void setTotalTrafegoDiretoVisitsLY(BigDecimal totalTrafegoDiretoVisitsLY) {
		this.totalTrafegoDiretoVisitsLY = totalTrafegoDiretoVisitsLY;
	}


	public BigDecimal getTotalTrafegoDiretoOrdersLY() {
		return totalTrafegoDiretoOrdersLY;
	}


	public void setTotalTrafegoDiretoOrdersLY(BigDecimal totalTrafegoDiretoOrdersLY) {
		this.totalTrafegoDiretoOrdersLY = totalTrafegoDiretoOrdersLY;
	}


	public BigDecimal getTotalPPCVisitsLY() {
		return totalPPCVisitsLY;
	}


	public void setTotalPPCVisitsLY(BigDecimal totalPPCVisitsLY) {
		this.totalPPCVisitsLY = totalPPCVisitsLY;
	}


	public BigDecimal getTotalPPCOrdersLY() {
		return totalPPCOrdersLY;
	}


	public void setTotalPPCOrdersLY(BigDecimal totalPPCOrdersLY) {
		this.totalPPCOrdersLY = totalPPCOrdersLY;
	}


	public BigDecimal getTotalEmailMarketingVisitsLY() {
		return totalEmailMarketingVisitsLY;
	}


	public void setTotalEmailMarketingVisitsLY(
			BigDecimal totalEmailMarketingVisitsLY) {
		this.totalEmailMarketingVisitsLY = totalEmailMarketingVisitsLY;
	}


	public BigDecimal getTotalEmailMarketingOrdersLY() {
		return totalEmailMarketingOrdersLY;
	}


	public void setTotalEmailMarketingOrdersLY(
			BigDecimal totalEmailMarketingOrdersLY) {
		this.totalEmailMarketingOrdersLY = totalEmailMarketingOrdersLY;
	}


	public BigDecimal getTotalBuscaOrganicaVisitsLY() {
		return totalBuscaOrganicaVisitsLY;
	}


	public void setTotalBuscaOrganicaVisitsLY(BigDecimal totalBuscaOrganicaVisitsLY) {
		this.totalBuscaOrganicaVisitsLY = totalBuscaOrganicaVisitsLY;
	}


	public BigDecimal getTotalBuscaOrganicaOrdersLY() {
		return totalBuscaOrganicaOrdersLY;
	}


	public void setTotalBuscaOrganicaOrdersLY(BigDecimal totalBuscaOrganicaOrdersLY) {
		this.totalBuscaOrganicaOrdersLY = totalBuscaOrganicaOrdersLY;
	}


	public BigDecimal getTotalDisplayVisitsLY() {
		return totalDisplayVisitsLY;
	}


	public void setTotalDisplayVisitsLY(BigDecimal totalDisplayVisitsLY) {
		this.totalDisplayVisitsLY = totalDisplayVisitsLY;
	}


	public BigDecimal getTotalDisplayOrdersLY() {
		return totalDisplayOrdersLY;
	}


	public void setTotalDisplayOrdersLY(BigDecimal totalDisplayOrdersLY) {
		this.totalDisplayOrdersLY = totalDisplayOrdersLY;
	}


	public BigDecimal getTotalAfiliadosVisitsLY() {
		return totalAfiliadosVisitsLY;
	}


	public void setTotalAfiliadosVisitsLY(BigDecimal totalAfiliadosVisitsLY) {
		this.totalAfiliadosVisitsLY = totalAfiliadosVisitsLY;
	}


	public BigDecimal getTotalAfiliadosOrdersLY() {
		return totalAfiliadosOrdersLY;
	}


	public void setTotalAfiliadosOrdersLY(BigDecimal totalAfiliadosOrdersLY) {
		this.totalAfiliadosOrdersLY = totalAfiliadosOrdersLY;
	}


	public BigDecimal getTotalComparadoresVisitsLY() {
		return totalComparadoresVisitsLY;
	}


	public void setTotalComparadoresVisitsLY(BigDecimal totalComparadoresVisitsLY) {
		this.totalComparadoresVisitsLY = totalComparadoresVisitsLY;
	}


	public BigDecimal getTotalComparadoresOrdersLY() {
		return totalComparadoresOrdersLY;
	}


	public void setTotalComparadoresOrdersLY(BigDecimal totalComparadoresOrdersLY) {
		this.totalComparadoresOrdersLY = totalComparadoresOrdersLY;
	}


	public BigDecimal getTotalSocialAdsVisitsLY() {
		return totalSocialAdsVisitsLY;
	}


	public void setTotalSocialAdsVisitsLY(BigDecimal totalSocialAdsVisitsLY) {
		this.totalSocialAdsVisitsLY = totalSocialAdsVisitsLY;
	}


	public BigDecimal getTotalSocialAdsOrdersLY() {
		return totalSocialAdsOrdersLY;
	}


	public void setTotalSocialAdsOrdersLY(BigDecimal totalSocialAdsOrdersLY) {
		this.totalSocialAdsOrdersLY = totalSocialAdsOrdersLY;
	}


	public BigDecimal getTotalPlanoMidiaVisitsLY() {
		return totalPlanoMidiaVisitsLY;
	}


	public void setTotalPlanoMidiaVisitsLY(BigDecimal totalPlanoMidiaVisitsLY) {
		this.totalPlanoMidiaVisitsLY = totalPlanoMidiaVisitsLY;
	}


	public BigDecimal getTotalPlanoMidiaOrdersLY() {
		return totalPlanoMidiaOrdersLY;
	}


	public void setTotalPlanoMidiaOrdersLY(BigDecimal totalPlanoMidiaOrdersLY) {
		this.totalPlanoMidiaOrdersLY = totalPlanoMidiaOrdersLY;
	}


	public BigDecimal getTotalMktDiretoVisitsLY() {
		return totalMktDiretoVisitsLY;
	}


	public void setTotalMktDiretoVisitsLY(BigDecimal totalMktDiretoVisitsLY) {
		this.totalMktDiretoVisitsLY = totalMktDiretoVisitsLY;
	}


	public BigDecimal getTotalMktDiretoOrdersLY() {
		return totalMktDiretoOrdersLY;
	}


	public void setTotalMktDiretoOrdersLY(BigDecimal totalMktDiretoOrdersLY) {
		this.totalMktDiretoOrdersLY = totalMktDiretoOrdersLY;
	}


	public BigDecimal getTotalOutrosVisitsLY() {
		return totalOutrosVisitsLY;
	}


	public void setTotalOutrosVisitsLY(BigDecimal totalOutrosVisitsLY) {
		this.totalOutrosVisitsLY = totalOutrosVisitsLY;
	}


	public BigDecimal getTotalOutrosOrdersLY() {
		return totalOutrosOrdersLY;
	}


	public void setTotalOutrosOrdersLY(BigDecimal totalOutrosOrdersLY) {
		this.totalOutrosOrdersLY = totalOutrosOrdersLY;
	}


	public Integer getTotalHours() {
		return totalHours;
	}


	public void setTotalHours(Integer totalHours) {
		this.totalHours = totalHours;
	}
	
	public List<Epar> getEparlist() {
		return eparlist;
	}

	public void setEparlist(List<Epar> eparlist) {
		this.eparlist = eparlist;
	}

	public List<Epar> getEparlistLY() {
		return eparlistLY;
	}

	public void setEparlistLY(List<Epar> eparlistLY) {
		this.eparlistLY = eparlistLY;
	}
	
	

	public String getChartEpar() throws IOException, InterruptedException {
		if (chartEpar == null || chartEpar.trim().length() <= 0) {
			populateData();
		}
		return chartEpar;
	}

	public void setChartEpar(String chartEpar) {
		this.chartEpar = chartEpar;
	}

	private void populateData() throws IOException, InterruptedException {
		EparSUBABean epar = new EparSUBABean();
		StringBuilder sb = new StringBuilder();
		sb.append("['ID', 'Crescimento Vendas', 'Crescimento Visita', 'Midia', 'Total Visita'],");
		for(int i=0; i < epar.getEparlist().size(); i++){
			for(int j=0; j < epar.getEparlistLY().size(); j++){
				if(epar.getEparlist().get(i).getName().equals(epar.getEparlistLY().get(j).getName())){
					sb.append("['");
					sb.append(epar.getEparlist().get(i).getSigla());
					sb.append("',");
					sb.append(epar.getCrescimentoPCT(epar.getEparlist().get(i).getOrdersEpar().doubleValue(), epar.getEparlistLY().get(j).getOrdersEpar().doubleValue()));
					sb.append(",");
					sb.append(epar.getCrescimentoPCT(epar.getEparlist().get(i).getVisitsEpar().doubleValue(), epar.getEparlistLY().get(j).getVisitsEpar().doubleValue()));
					sb.append(",'");
					sb.append(epar.getEparlist().get(i).getName());
					sb.append("',");
					sb.append(epar.getEparlist().get(i).getVisitsEpar());
					sb.append("],");
				}
			}
		}
		chartEpar = sb.toString();
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		EparSUBABean eparb = new EparSUBABean();
		System.out.println(eparb.getChartEpar());
		System.out.println(eparb.getTotalPlanoMidiaOrders());
		System.out.println(eparb.getTotalPlanoMidiaOrdersLY());
	}
}
