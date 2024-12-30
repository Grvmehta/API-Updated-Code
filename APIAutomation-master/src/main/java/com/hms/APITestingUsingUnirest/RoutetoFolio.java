package com.hms.APITestingUsingUnirest;

import java.util.ArrayList;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RoutetoFolio {
	 
static String responseJSONString;
	
	String keytype;
	String accesskey;
	String serverurl;
	String hotelid1;
	String editbookingid;
	String gueststayid;
	String fname;
	String lname;
	String restype;
    String groupid;
    String invoiceid;
    String mainid;
    String itemid;
    String amount;
    String invoiceidnew;
    String bookingid;
    String bookingcode;
    String invoiceidfrom;
    String invoiceidto;
    String invoiceidtoroutenewfolio;
    String invoiceto;
    
public void fn_routetofolio(String s1, String s2)throws Throwable{
			
	 
	    keytype = s1;
	    restype = s2;	
		
		System.out.println("Welcome to Change Guest Stay API");
		
		if(keytype == "wsauth")
		{

		String keyw = Wsauth.wsauthkeyfinalstring;
		accesskey = keyw;
		System.out.println("wsauthkey in addtocart:"+keyw);
		}

		else if(keytype == "login")
		{

		String keyl = Login.finalloginaccesskey;
		System.out.println("login key in editbooking:"+keyl);
		accesskey = keyl;
		}
		try
		{
			System.out.println("Hello Change Group Stay API");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			
			if(restype == "S")
			{
				GuestCheckin guestcheckinobj=new GuestCheckin();
				guestcheckinobj.GuestCheckincall("login" ,"false", "S");
				guestcheckinobj.extractingguestStayId();
				bookingid = GuestCheckin.BookingID;
				bookingcode = GuestCheckin.Bookingcode;
				
				Addothercharge addotherchargeboj = new Addothercharge();
				addotherchargeboj.Addotherchargecallwithnewreservation("login", "S");
									
			/*	AddPaymentToBooking addpaymentbookingobj = new AddPaymentToBooking();
			    addpaymentbookingobj.fn_addpaymenttobookingforexisting("login", bookingid);*/
		
				
			    GeneratePerformaInvoice generateperformainvoiceobj = new GeneratePerformaInvoice();
				generateperformainvoiceobj.GeneratePerformaInvoiceAPI("login", "S", bookingid);
				invoiceidtoroutenewfolio = GeneratePerformaInvoice.InvoiceID;
			     amount=     GeneratePerformaInvoice.InvoiceTotalAmount;
			     mainid = GeneratePerformaInvoice.mainid;
			     
			     
			     Addothercharge addotherchargeboj1 = new Addothercharge();
				 addotherchargeboj1.Addotherchargecallwithnewreservation("login", "S");
				 
				 GeneratePerformaInvoice generateperformainvoiceobj1 = new GeneratePerformaInvoice();
				 generateperformainvoiceobj1.GeneratePerformaInvoiceAPI("login", "S", bookingid);
				 invoiceidto = GeneratePerformaInvoice.InvoiceID;
				 
				    Editbooking editbookingobj = new Editbooking();
					editbookingobj.fn_editbookingcallforexistingbookingid("login", mainid, "S");
					gueststayid = editbookingobj.extractingguestStayId();
				     
				     RoutetoNewFolio routtonrefoliobj = new RoutetoNewFolio();
				     routtonrefoliobj.fn_routetonewfolio("login","S",invoiceidtoroutenewfolio, gueststayid, amount);
				     //itemid =routtonrefoliobj.itemid;
				     invoiceidfrom = routtonrefoliobj.extractinginvoiceidforroutetonewfolio();
				     
				     
				     Getinvoices getinvoicesobj = new Getinvoices();
				     getinvoicesobj.GetinvoiceswithExistingbookingid("login", bookingid, "S");
				     invoiceto = getinvoicesobj.extractinginvoiceidsecond();
				     
				     
		
			}
			
			if(restype == "G"){
				
				GuestCheckin guestcheckinobj=new GuestCheckin();
				guestcheckinobj.GuestCheckincall("login" ,"true" , "G");
				guestcheckinobj.extractingguestStayIdforgroup();
				bookingid = GuestCheckin.BookingID;
				bookingcode = GuestCheckin.groupcode;
				
				Addothercharge addotherchargeboj = new Addothercharge();
				addotherchargeboj.Addotherchargecallwithnewreservation("login", "G");			
				
				Getgroup getgroupobj = new Getgroup();
				getgroupobj.Getgroupcall("login", bookingcode);
				getgroupobj.extractingrateidfromgetgroup();
	
				groupid = getgroupobj.extractingGOUPPID();
				GeneratePerformaInvoice generateperformainvoiceobj = new GeneratePerformaInvoice();
				generateperformainvoiceobj.GeneratePerformaInvoiceAPI("login", "G", groupid);
				invoiceidtoroutenewfolio = GeneratePerformaInvoice.InvoiceID;		
			    amount = Getposproduct.price;
			    mainid = GeneratePerformaInvoice.mainid;
			    
			    Addothercharge addotherchargeboj1 = new Addothercharge();
				addotherchargeboj1.Addotherchargecallwithnewreservation("login", "G");	
				
				GeneratePerformaInvoice generateperformainvoiceobj1 = new GeneratePerformaInvoice();
				generateperformainvoiceobj1.GeneratePerformaInvoiceAPI("login", "G", groupid);
			
				  Editbooking editbookingobj = new Editbooking();
				  editbookingobj.fn_editbookingcallforexistingbookingid("login", groupid, "G");
			      gueststayid = editbookingobj.extractingguestStayId();
				     
				   RoutetoNewFolio routtonrefoliobj = new RoutetoNewFolio();
				   routtonrefoliobj.fn_routetonewfolio("login","G",invoiceidtoroutenewfolio, gueststayid, amount);
				     //itemid =routtonrefoliobj.itemid;
				   invoiceidfrom = routtonrefoliobj.extractinginvoiceidforroutetonewfolio();
				     
				     
				 Getinvoices getinvoicesobj = new Getinvoices();
				 getinvoicesobj.GetinvoiceswithExistingbookingid("login", groupid, "G");
				 invoiceto = getinvoicesobj.extractinginvoiceidsecond();
				     
				
			}
			
			HttpResponse<JsonNode> responseattachguest = Unirest.post(""+serverurl+"/ws/web/routetofolios")
                .header("content-type", "application/json")
                .header("x-ig-sg", "D_gg%fkl85_j")
                .header("cache-control", "no-cache")
                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
                .body("{"
                		+ "\"hotelogix\": {"
                		+ "\"version\": \"1.0\","
                		+ "\"datetime\": \"2012-01-16T10:10:15\","
                		+ "\"request\": {"
                		+ "\"method\": \"routetofolios\","
                		+ "\"key\": \""+accesskey+"\","
                		+ "\"data\": {"
                		+ "\"from\": \""+invoiceidfrom+"\","
                		+ "\"to\": ["
                		+ "\""+invoiceto+"\""
                		+ "],"
                		+ "\"routeAmount\": \""+amount+"\""
                		+ "}}}}")
                     .asJson();
			
	    	JsonNode body = responseattachguest.getBody();
	    	responseJSONString = body.toString();
	    	System.out.println("Route To Folio Response:" +responseJSONString);
		}
    	catch(UnirestException e)
    	{
    	e.printStackTrace();
    	}
		finally{
						
			Commiteditbooking commitbookingobj = new Commiteditbooking();
			commitbookingobj.CommiteditBookingCallForExistingBookingid("login", restype, mainid );
		
			
		}
}	
		public String extractingmessageforroutetofolio()
		{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String routetofolio;
		routetofolio = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getbookings success:"+routetofolio);
		return routetofolio;
		}

	
	

}
