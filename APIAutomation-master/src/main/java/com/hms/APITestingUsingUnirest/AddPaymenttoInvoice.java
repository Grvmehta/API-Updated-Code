package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class AddPaymenttoInvoice {
	
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String editguestdetailid;
	String mainid;
	String bookingid;
	String bookingcode;
	String invoiceid;
	String paymentID;
	
	public void addpaymenttoinvoice(String s1)
	{
		keytype = s1;
		 
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello Add Payment To Invoice:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;

				System.out.println("wsauthkey in addpaymenttoinvoice:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in addpaymenttoinvoice:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 	serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);						
				
		/*		Getinvoices getinvoicesobj = new Getinvoices();
				getinvoicesobj.Getinvoicescall("login","S");
				String invoiceid = getinvoicesobj.extractinginvoiceid();
				mainid = getinvoicesobj.mainid;*/
				
				GuestCheckin guestcheckinobj=new GuestCheckin();
				guestcheckinobj.GuestCheckincall("login" ,"false", "S");
				guestcheckinobj.extractingguestStayId();
				paymentID=	guestcheckinobj.paymentID;
				System.out.println("Add paymet to inv::::"+paymentID);
				bookingid = GuestCheckin.BookingID;
				bookingcode = GuestCheckin.Bookingcode;
				Addothercharge addotherchargeboj = new Addothercharge();
				addotherchargeboj.Addotherchargecallwithnewreservation("login", "S");
				
				GeneratePerformaInvoice generateperformainvoiceobj = new GeneratePerformaInvoice();
				generateperformainvoiceobj.GeneratePerformaInvoiceAPI("login", "S", bookingid);
				invoiceid = GeneratePerformaInvoice.InvoiceID;
				
				
				Editbooking editbookingcallobj = new Editbooking();
				editbookingcallobj.fn_editbookingcallforexistingbookingid("login", bookingid, "S");
				editbookingcallobj.extractingtempid();
				editguestdetailid = editbookingcallobj.extractingdetailsId();
				
				
				
			 HttpResponse<JsonNode> responsesendaddpaymenttoinvoice = Unirest.post(""+serverurl+"/ws/web/addpaymenttoinvoice")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "0190e7db-adae-6d84-065a-ef776b855f23")
					  .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"addpaymenttoinvoice\","            		
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"    		
		                		+ "\"invoiceId\": \""+invoiceid+"\","
		                		+ "\"guestId\": \""+editguestdetailid+"\"," 
		                		+ "\"paymentMode\": \"cc\","
		                		+ "\"paymentType\": \"Visa\","
		                		+ "\"amount\": 100,"  
		                		+ "\"creditCardDetails\": {"
		                		+ "\"nameOnCard\": \"Susuil. R\","     
		                		+ "\"cardNo\": \"4111111111111111\","      
		                		+ "\"expiryMonth\": \"12\","  
		                		+ "\"expiryYear\": \"2027\","
		                		+ "\"cvc\": \"100\""
		                        +  "}"
		                		+ "}}}}")
					  .asJson();
			 JsonNode body = responsesendaddpaymenttoinvoice.getBody();
			 responseJSONString = body.toString();	 
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 
			 e.printStackTrace();
		 }
		 
		 finally{
				
				Commiteditbooking commiteditbookingobj = new Commiteditbooking();
				commiteditbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", bookingid);
			}
		}
		 
	public void addpaymenttoinvoiceforgroup(String s1)
	{
		keytype = s1;
		 
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello Add Payment To Invoice:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;

				System.out.println("wsauthkey in addpaymenttoinvoice:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in addpaymenttoinvoice:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 	serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);						
				
				Getinvoices getinvoicesobj = new Getinvoices();
				getinvoicesobj.Getinvoicescall("login","G");
				String invoiceid = getinvoicesobj.extractinginvoiceid();
				
				mainid = getinvoicesobj.mainid;
				
				Editbooking editbookingcallobj = new Editbooking();
				editbookingcallobj.fn_editbookingcallforexistingbookingid("login", mainid, "G");
				editbookingcallobj.extractingtempid();
				editguestdetailid = editbookingcallobj.extractingdetailsId();
				
				
				
			 HttpResponse<JsonNode> responsesendaddpaymenttoinvoice = Unirest.post(""+serverurl+"/ws/web/addpaymenttoinvoice")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "0190e7db-adae-6d84-065a-ef776b855f23")
					  .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"addpaymenttoinvoice\","            		
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"    		
		                		+ "\"invoiceId\": \""+invoiceid+"\","
		                		+ "\"guestId\": \""+editguestdetailid+"\"," 
		                		+ "\"paymentMode\": \"cc\","
		                		+ "\"paymentType\": \"Visa\","
		                		+ "\"amount\": 150,"  
		                		+ "\"creditCardDetails\": {"
		                		+ "\"nameOnCard\": \"Susuil. R\","     
		                		+ "\"cardNo\": \"4111111111111111\","      
		                		+ "\"expiryMonth\": \"12\","  
		                		+ "\"expiryYear\": \"2027\","
		                		+ "\"cvc\": \"100\""
		                        +  "}"
		                		+ "}}}}")
					  .asJson();
			 JsonNode body = responsesendaddpaymenttoinvoice.getBody();
			 responseJSONString = body.toString();	 
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 
			 e.printStackTrace();
		 }
		 
		 finally{
				
				Commiteditbooking commiteditbookingobj = new Commiteditbooking();
				commiteditbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", mainid);
			}
		}
		 
	
	

	
	public String extractingmessageaddpaymenttoinvoice(){
	
	
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String addpaymenttoinvoicemsg;
		addpaymenttoinvoicemsg = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last sendinvoiceemail success::"+addpaymenttoinvoicemsg);
		return addpaymenttoinvoicemsg;
	
	// End of extractingmessageaddpaymenttoinvoice() method 
	
}
}



