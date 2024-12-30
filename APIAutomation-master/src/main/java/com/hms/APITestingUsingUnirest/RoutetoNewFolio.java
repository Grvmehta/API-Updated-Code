package com.hms.APITestingUsingUnirest;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hms.APITestingUsingUnirest.Generic.ApiUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RoutetoNewFolio {
	
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
    static String mainid;
    String itemid;
    String amount;
    String bookingid;
    String bookingcode;

 public void fn_routetonewfolio(String s1, String s2, String s3, String s4, String s5)  throws Throwable{
		
		keytype = s1;
		restype = s2;
		invoiceid = s3;
		gueststayid = s4;
		amount = s5;
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
			
			/*if(restype == "S"){
				
				GuestCheckin guestcheckinobj=new GuestCheckin();
				guestcheckinobj.GuestCheckincall("login" ,"false", "S");
				guestcheckinobj.extractingguestStayId();
				bookingid = GuestCheckin.BookingID;
				bookingcode = GuestCheckin.Bookingcode;
				Addothercharge addotherchargeboj = new Addothercharge();
				addotherchargeboj.Addotherchargecallwithnewreservation("login", "S");
									
				AddPaymentToBooking addpaymentbookingobj = new AddPaymentToBooking();
			    addpaymentbookingobj.fn_addpaymenttobookingforexisting("login", bookingid);
		
				
			    GeneratePerformaInvoice generateperformainvoiceobj = new GeneratePerformaInvoice();
				generateperformainvoiceobj.GeneratePerformaInvoiceAPI("login", "S", bookingid);
				invoiceid = GeneratePerformaInvoice.InvoiceID;
			     amount=     GeneratePerformaInvoice.InvoiceTotalAmount;
			     mainid = GeneratePerformaInvoice.mainid;
			
			 
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.fn_editbookingcallforexistingbookingid("login", mainid, "S");
			gueststayid = editbookingobj.extractingguestStayId();
			
			}
			else if(restype == "G"){
				
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
				groupid = getgroupobj.extractingGOUPPID();
				GeneratePerformaInvoice generateperformainvoiceobj = new GeneratePerformaInvoice();
				generateperformainvoiceobj.GeneratePerformaInvoiceAPI("login", "G", groupid);
				invoiceid = GeneratePerformaInvoice.InvoiceID;
			
					   amount=     GeneratePerformaInvoice.InvoiceTotalAmount;
					   mainid = GeneratePerformaInvoice.mainid;
					
				Editbooking editbookingobj = new Editbooking();
				editbookingobj.fn_editbookingcallforexistingbookingid("login", groupid, "G");
				gueststayid = editbookingobj.extractingguestStayId();
				
				
			}
			*/
			
			HttpResponse<JsonNode> responseattachguest = Unirest.post(""+serverurl+"/ws/web/routetonewfolio")
                .header("content-type", "application/json")
                .header("x-ig-sg", "D_gg%fkl85_j")
                .header("cache-control", "no-cache")
                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
                .body("{"
                		+ "\"hotelogix\": {"
                		+ "\"version\": \"1.0\","
                		+ "\"datetime\": \"2012-01-16T10:10:15\","
                		+ "\"request\": {"
                		+ "\"method\": \"routetonewfolio\","
                		+ "\"key\": \""+accesskey+"\","
                		+ "\"data\": {"
                		+ "\"from\": \""+invoiceid+"\","
                		+ "\"guestStayId\": \""+gueststayid+"\","
                		+ "\"routeAmount\": \""+amount+"\""
                		+ "}}}}")
                     .asJson();
			
	    	JsonNode body = responseattachguest.getBody();
	    	responseJSONString = body.toString();
	    	System.out.println("Route To New Folio Response:" +responseJSONString);
		}
    	catch(UnirestException e)
    	{
    	e.printStackTrace();
    	}
		
}	
		public String extractingmessageforroutetonewfolio()
		{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String routetonewfolio;
		routetonewfolio = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getbookings success:"+routetonewfolio);
		return routetonewfolio;
		}
		
		public String extractinginvoiceidforroutetonewfolio()
		{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray  invoicearr =jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("invoices");
		JSONObject invoiceobj = invoicearr.getJSONObject(0);
		JSONArray itemidarr = invoiceobj.getJSONArray("lineitems");
		JSONObject  itemidobj = itemidarr.getJSONObject(0);
		//Extracting amount
		amount = itemidobj.getString("amount");
		//Extracting itemid
		itemid = itemidobj.getString("itemId");
		String invoiceid =invoiceobj.getString("invoiceId");
		System.out.println("This is Invoice id From RouteToNewFolio Resposne:"+invoiceid);
		return invoiceid;
		
		
		}
		public ArrayList<String> extractinginvoiceid()
		{
			
			    ArrayList<String> arr=new ArrayList<String>();
				String localresponseJSONString = responseJSONString;
				JSONObject jsonResult = new JSONObject(localresponseJSONString);
				JSONObject jsonResult1 = jsonResult.getJSONObject("hotelogix").getJSONObject("response");		
				
				 JSONArray rates=jsonResult1.getJSONArray("invoices");
				 for(int i=0;i<=rates.length()-1;i++){
					 String rateid=rates.getJSONObject(i).getString("invoiceId");
					 arr.add(rateid);
				 }
				
				return arr;
				
				
			
		}
}
