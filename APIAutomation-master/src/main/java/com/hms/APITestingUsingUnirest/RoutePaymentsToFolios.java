package com.hms.APITestingUsingUnirest;

import java.io.IOException;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RoutePaymentsToFolios {

	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String bookingid;
	String businesssourceid;
	String restype;
	String Bookinsourcetype;
	String groupid;
	String groupcode;
	String bookingcode;
	String paymentid;
	String invoiceid;
	String amount;
	public void fn_routepaymenttofolios(String s1, String s2) throws Throwable
	
	{
		keytype = s1;
		restype = s2;
		
		System.out.println("keytype:"+s1);
		if(keytype == "wsauth")
		{
	
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in gethoteldata:"+keyw);
		}
		
		else if(keytype == "login")
		{
		
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in gethoteldata:"+keyl);
			accesskey = keyl;
		}
		
		try{
			serverurl = CommonConfig.serverurl;
		 
			if(restype == "S"){
			
			GuestCheckin guestcheckinobj=new GuestCheckin();
			guestcheckinobj.GuestCheckincall("login" ,"false", "S");
			guestcheckinobj.extractingguestStayId();
			bookingid = GuestCheckin.BookingID;
			bookingcode = GuestCheckin.Bookingcode;
			Addothercharge addotherchargeboj = new Addothercharge();
			addotherchargeboj.Addotherchargecallwithnewreservation("login", "S");
			
					
			AddPaymentToBooking addpaymentbookingobj = new AddPaymentToBooking();
		    addpaymentbookingobj.fn_addpaymenttobookingforexisting("login", bookingid);
	
			
			Getbooking getbookingobj = new Getbooking();
			getbookingobj.getbookingcallforExistingbookingcode("login", bookingcode);
			paymentid = getbookingobj.extractingpaymentid();
			amount = Getbooking.amount;
				
			
			GeneratePerformaInvoice generateperformainvoiceobj = new GeneratePerformaInvoice();
			generateperformainvoiceobj.GeneratePerformaInvoiceAPI("login", "S", bookingid);
			invoiceid = GeneratePerformaInvoice.InvoiceID;
			HttpResponse<JsonNode> routepaymenttofolios = Unirest.post(""+serverurl+"/ws/web/routepaymentstofolios")
					   .header("content-type", "application/json")
					               .header("x-ig-sg", "D_gg%fkl85_j")
					               .header("cache-control", "no-cache")
					               .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
					               .body("{"
					                + "\"hotelogix\": {"
					                + "\"version\": \"1.0\","
					                + "\"datetime\": \"2012-01-16T10:10:15\","
					                + "\"request\": {"
					                + "\"method\": \"routepaymentstofolios\","
					                + "\"key\": \""+accesskey+"\","
					                + "\"data\": {"
					                       + "\"id\": \""+bookingid+"\","
					                + "\"type\": \""+restype+"\","
					                + "\"routeFrom\": \"ACST\","
					                + "\"fromPayments\": ["
					                +  "\""+paymentid+"\""
					                + "],"
					                + "\"toInvoices\": ["
					                + "\""+invoiceid+"\""
					                + "],"
					                + "\"routeAmount\": \""+amount+"\""
					                      + "}}}}")
			                .asJson();
			                       
				JsonNode body = routepaymenttofolios.getBody();
				responseJSONString = body.toString();
				System.out.println("RoutePaymenttofoolios Response" +responseJSONString);
		}
		
		if(restype == "G"){
			
			GuestCheckin guestcheckinobj=new GuestCheckin();
			guestcheckinobj.GuestCheckincall("login" ,"true" , "G");
			guestcheckinobj.extractingguestStayIdforgroup();
			bookingid = GuestCheckin.BookingID;
			bookingcode = GuestCheckin.groupcode;
			
			Addothercharge addotherchargeboj = new Addothercharge();
			addotherchargeboj.Addotherchargecallwithnewreservation("login", "G");
			
			AddPaymentToBooking addpaymentbookingobj = new AddPaymentToBooking();
		    addpaymentbookingobj.fn_addpaymenttobookingforexisting("login", bookingid);
		
			Getgroup getgroupobj = new Getgroup();
			getgroupobj.Getgroupcall("login", bookingcode);
			getgroupobj.extractingrateidfromgetgroup();
			paymentid = getgroupobj.paymentid;
			groupid = getgroupobj.extractingGOUPPID();
			GeneratePerformaInvoice generateperformainvoiceobj = new GeneratePerformaInvoice();
			generateperformainvoiceobj.GeneratePerformaInvoiceAPI("login", "G", groupid);
			invoiceid = GeneratePerformaInvoice.InvoiceID;
		
		     amount = Getposproduct.price;
		
		
	
		   HttpResponse<JsonNode> routepaymenttofolios = Unirest.post(""+serverurl+"/ws/web/routepaymentstofolios")
				   .header("content-type", "application/json")
				               .header("x-ig-sg", "D_gg%fkl85_j")
				               .header("cache-control", "no-cache")
				               .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
				               .body("{"
				                + "\"hotelogix\": {"
				                + "\"version\": \"1.0\","
				                + "\"datetime\": \"2012-01-16T10:10:15\","
				                + "\"request\": {"
				                + "\"method\": \"routepaymentstofolios\","
				                + "\"key\": \""+accesskey+"\","
				                + "\"data\": {"
				                       + "\"id\": \""+groupid+"\","
				                + "\"type\": \""+restype+"\","
				                + "\"routeFrom\": \"ACST\","
				                + "\"fromPayments\": ["
				                +  "\""+paymentid+"\""
				                + "],"
				                + "\"toInvoices\": ["
				                + "\""+invoiceid+"\""
				                + "],"
				                + "\"routeAmount\": \""+amount+"\""
				                      + "}}}}")
		                .asJson();
		                       
			JsonNode body = routepaymenttofolios.getBody();
			responseJSONString = body.toString();
			System.out.println("RoutePaymenttofoolios Response" +responseJSONString);
		}
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
		finally{
		if(restype == "S"){
			 Commiteditbooking  commiteditbookingobj = new Commiteditbooking();
			 commiteditbookingobj.CommiteditBookingCallForExistingBookingid("login", restype, bookingid);
		}
		if(restype == "G"){
			Commiteditbooking  commiteditbookingobj = new Commiteditbooking();
			 commiteditbookingobj.CommiteditBookingCallForExistingBookingid("login", restype, groupid);
		}
		}
	
	}

	//End of RoutePaymenttofolios method
	
	public String extractingmessageroutepaymenttofolios()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String routepaymenttofolios;
		routepaymenttofolios = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("Update Booking Source success:"+routepaymenttofolios);
		return routepaymenttofolios;
	}

}


