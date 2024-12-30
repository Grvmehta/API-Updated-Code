package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.hms.APITestingUsingUnirest.Generic.ApiUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class AddPaymentToBooking {
	
	static String responseJSONString;
	String keytype;
	String accesskey;
	String serverurl;
	String hotelid1;
	String bookingid;
	String roomid;
	String currentDate;
	String frmDate1;
	String paymentmode;
	String paytype;
	String message;
	String  bookingcode;
    String price;
    String transactionId;

	public void fn_addpaymenttobooking(String s1) throws Throwable 
	{
		
			keytype = s1;
			
			if(keytype == "wsauth")
			{
				Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				accesskey = keyl;*/

				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in getbooking:"+keyl);
				accesskey = keyl;
			}
			try
			{
				System.out.println("hello try createagent");
				serverurl = CommonConfig.serverurl;
 			    hotelid1 = CommonConfig.hotelid1;	
 			    
 			   Addothercharge addotherchargeobj=new Addothercharge();
 			   addotherchargeobj.Addotherchargecall("login");
 			   bookingid = Addothercharge.bookingid;
 			   bookingcode = Addothercharge.bookingcode;
				
 	           Getallpaytypes getallpaytypesobj = new Getallpaytypes();
 			   getallpaytypesobj.Getallpaytypescall("login");
 			   getallpaytypesobj.extractingmessageGetallpaytypes();
 			   paymentmode = getallpaytypesobj.paymentmode;
 			   paytype= getallpaytypesobj.paytype;
 			   price = Getposproduct.price;
				
			     
		
				HttpResponse<JsonNode> responseaddpaymenttobooking = Unirest.post(""+serverurl+"/ws/web/addpaymenttobooking")
						 .header("content-type", "application/json")
						 .header("x-ig-sg", "D_gg%fkl85_j")
						 .header("cache-control", "no-cache")
						 .header("postman-token", "c52633bd-8abb-e05d-ec86-0641bf085b8f")
						 .body("{"
						               	+ "\"hotelogix\": {"
						               	+ "\"version\": \"1.0\","
						               	+ "\"datetime\": \"2012-01-16T10:10:15\","
						               	+ "\"request\": {"
						               	+ "\"method\": \"addpaymenttobooking\","
						               	+ "\"key\": \""+accesskey+"\","
						               	+ "\"data\": {"
						               	+ "\"id\": \""+bookingid+"\","
						               	+ "\"paymentMode\": \""+paymentmode+"\","
						               	+ "\"paymentType\": \""+paytype+"\","
						               	+ "\"amount\": \""+price+"\","
						            	+ "\"creditCardDetails\": {"
						            	+ "\"nameOnCard\": \"Susuil. R\","
					                    + "\"cardNo\": \"4111111111111111\","
					                    + "\"expiryMonth\": \"12\","
					                    + "\"expiryYear\": \"2027\","
					                    + "\"cvc\": \"100\""
					                    + "}"
						               	+ "}}}}")
						                .asJson();
						JsonNode body = responseaddpaymenttobooking.getBody();
						responseJSONString = body.toString();
						System.out.println("addpaymenttobooking Response :"+responseJSONString);
			}
	    	catch(UnirestException e)
	    	{
	    	e.printStackTrace();
	    	}
	}
	
	public void fn_addpaymenttobookingforexisting(String s1, String s2) throws Throwable 
	{
		
			keytype = s1;
			bookingid = s2;
			
			if(keytype == "wsauth")
			{
				Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
			}
			
			else if(keytype == "login")
			{
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in getbooking:"+keyl);
				accesskey = keyl;
			}
			try
			{
				System.out.println("hello try createagent");
				serverurl = CommonConfig.serverurl;
 			    hotelid1 = CommonConfig.hotelid1;	
 			    
 			   Getallpaytypes getallpaytypesobj = new Getallpaytypes();
 			   getallpaytypesobj.Getallpaytypescall("wsauth");
 			   getallpaytypesobj.extractingmessageGetallpaytypes();
 			   paymentmode = getallpaytypesobj.paymentmode;
 			   paytype= getallpaytypesobj.paytype;
 			   price = Getposproduct.price;
				
			     
		
				HttpResponse<JsonNode> responseaddpaymenttobooking = Unirest.post(""+serverurl+"/ws/web/addpaymenttobooking")
						 .header("content-type", "application/json")
						 .header("x-ig-sg", "D_gg%fkl85_j")
						 .header("cache-control", "no-cache")
						 .header("postman-token", "c52633bd-8abb-e05d-ec86-0641bf085b8f")
						 .body("{"
						               	+ "\"hotelogix\": {"
						               	+ "\"version\": \"1.0\","
						               	+ "\"datetime\": \"2012-01-16T10:10:15\","
						               	+ "\"request\": {"
						               	+ "\"method\": \"addpaymenttobooking\","
						               	+ "\"key\": \""+accesskey+"\","
						               	+ "\"data\": {"
						               	+ "\"id\": \""+bookingid+"\","
						               	+ "\"paymentMode\": \""+paymentmode+"\","
						               	+ "\"paymentType\": \""+paytype+"\","
						               	+ "\"amount\": \""+price+"\","
						            	+ "\"creditCardDetails\": {"
						            	+ "\"nameOnCard\": \"Susuil. R\","
					                    + "\"cardNo\": \"4111111111111111\","
					                    + "\"expiryMonth\": \"12\","
					                    + "\"expiryYear\": \"2027\","
					                    + "\"cvc\": \"100\""
					                    + "}"
						               	+ "}}}}")
						                .asJson();
						JsonNode body = responseaddpaymenttobooking.getBody();
						responseJSONString = body.toString();
						System.out.println("Response of addpaymenttobooking ");
						System.out.println("addpaymenttobooking :"+responseJSONString);
			}
	    	catch(UnirestException e)
	    	{
	    	e.printStackTrace();
	    	}
	}
	
	public void fn_addpaymenttobookinggroup(String s1) throws Throwable 
	{
		
			keytype = s1;
			
			if(keytype == "wsauth")
			{
				Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
			}
			
			else if(keytype == "login")
			{
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in getbooking:"+keyl);
				accesskey = keyl;
			}
			try
			{
				System.out.println("hello try createagent");
				serverurl = CommonConfig.serverurl;
 			    hotelid1 = CommonConfig.hotelid1;	
 			    
 			    
 			   Addothercharge addotherchargeobj=new Addothercharge();
 			   addotherchargeobj.AddotherchargecallWithGroupChargetype("login");
 			   bookingid = Addothercharge.bookingid;
 			   bookingcode = Addothercharge.groupcode;
				System.out.println("booking id is ::"+bookingid);
 	           Getallpaytypes getallpaytypesobj = new Getallpaytypes();
 			   getallpaytypesobj.Getallpaytypescall("wsauth");
 			   getallpaytypesobj.extractingmessageGetallpaytypes();
 			   paymentmode = getallpaytypesobj.paymentmode;
 			   System.out.println("Paymode"+paymentmode);
 			   paytype= getallpaytypesobj.paytype;
 			  System.out.println("paytype"+paytype);
 			   price = Getposproduct.price;
 			  System.out.println("price"+price);
			     
		
				HttpResponse<JsonNode> responseaddpaymenttobooking = Unirest.post(""+serverurl+"/ws/web/addpaymenttogroup")
						 .header("content-type", "application/json")
						 .header("x-ig-sg", "D_gg%fkl85_j")
						 .header("cache-control", "no-cache")
						 .header("postman-token", "c52633bd-8abb-e05d-ec86-0641bf085b8f")
						 .body("{"
						               	+ "\"hotelogix\": {"
						               	+ "\"version\": \"1.0\","
						               	+ "\"datetime\": \"2012-01-16T10:10:15\","
						               	+ "\"request\": {"
						               	+ "\"method\": \"addpaymenttogroup\","
						               	+ "\"key\": \""+accesskey+"\","
						               	+ "\"data\": {"
						               	+ "\"id\": \""+bookingid+"\","
						               	+ "\"paymentMode\": \""+paymentmode+"\","
						               	+ "\"paymentType\": \""+paytype+"\","
						            	+ "\"amount\": \""+price+"\","
						            	+ "\"creditCardDetails\": {"
						            	+ "\"nameOnCard\": \"Susuil. R\","
					                    + "\"cardNo\": \"4111111111111111\","
					                    + "\"expiryMonth\": \"12\","
					                    + "\"expiryYear\": \"2027\","
					                    + "\"cvc\": \"100\""
					                    + "}"
						               	+ "}}}}")
						                .asJson();
						JsonNode body = responseaddpaymenttobooking.getBody();
						responseJSONString = body.toString();
						System.out.println("Response of addpaymenttobooking ");
						System.out.println("addpaymenttobooking :"+responseJSONString);
			}
	    	catch(UnirestException e)
	    	{
	    	e.printStackTrace();
	    	}
	}
		
	
	public void fn_addpaymenttobookingPaymentGateway(String s1) throws Throwable 
	{
		
			keytype = s1;
			
			if(keytype == "wsauth")
			{
				Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				accesskey = keyl;*/

				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in getbooking:"+keyl);
				accesskey = keyl;
			}
			try
			{
				System.out.println("hello try createagent");
				serverurl = CommonConfig.serverurl;
 			    hotelid1 = CommonConfig.hotelid1;	
 			    
 			   Addothercharge addotherchargeobj=new Addothercharge();
 			   addotherchargeobj.Addotherchargecall("login");
 			   bookingid = Addothercharge.bookingid;
 			   bookingcode = Addothercharge.bookingcode;
				
 	           Getallpaytypes getallpaytypesobj = new Getallpaytypes();
 			   getallpaytypesobj.Getallpaytypescall("login");
 			   getallpaytypesobj.extractingmessageGetallpaytypes();
 			   paymentmode = getallpaytypesobj.paymentmode;
 			   paytype= getallpaytypesobj.paytype;
 			   price = Getposproduct.price;
		
				/*
				
				System.out.println("hello try createagent");
				serverurl = CommonConfig.serverurl;
 			    hotelid1 = CommonConfig.hotelid1;	
 			    
 			   Addothercharge addotherchargeobj=new Addothercharge();
 			   addotherchargeobj.Addotherchargecall("login");
 			   bookingid = Addothercharge.bookingid;
 			   bookingcode = Addothercharge.bookingcode;
				
 	           Getallpaytypes getallpaytypesobj = new Getallpaytypes();
 			   getallpaytypesobj.Getallpaytypescall("login");
 			   getallpaytypesobj.extractingmessageGetallpaytypes();
 			   paymentmode = getallpaytypesobj.paymentmode;
 			   paytype= getallpaytypesobj.paytype;
 			   price = Getposproduct.price;*/
 			  transactionId="TF6767612311";
 			  Gethoteldata gethoteldataObj=new Gethoteldata();
 			 gethoteldataObj.gethoteldatacallForPaymentGateway("login");
 			String paymentGatewayID=gethoteldataObj.extractingPaymentGatewayId();
			     
		
				HttpResponse<JsonNode> responseaddpaymenttobooking = Unirest.post(""+serverurl+"/ws/web/addpaymenttobooking")
						 .header("content-type", "application/json")
						 .header("x-ig-sg", "D_gg%fkl85_j")
						 .header("cache-control", "no-cache")
						 .header("postman-token", "c52633bd-8abb-e05d-ec86-0641bf085b8f")
						 .body( "{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"addpaymenttobooking\",\"key\":\""+accesskey+"\",\"data\":{\"id\":\""+bookingid+"\",\"paymentMode\":\""+paymentmode+"\",\"paymentType\":\""+paytype+"\",\"amount\":"+price+",\"creditCardDetails\":{\"nameOnCard\":\"Susuil. R\",\"cardNo\":\"4111111111111111\",\"expiryMonth\":12,\"expiryYear\":2027,\"cvc\":\"100\"},\"transactionId\":\""+transactionId+"\",\"receiptNo\":\"R-111544\",\"chequeNo\":\"C-123456\",\"description\":\"Test Description\",\"isPreAuth\":true,\"paymentGatewayId\":\""+paymentGatewayID+"\"}}}}")
						 .asJson();
						JsonNode body = responseaddpaymenttobooking.getBody();
						responseJSONString = body.toString();
						System.out.println("addpaymenttobooking Response :"+responseJSONString);
			}
	    	catch(UnirestException e)
	    	{
	    	e.printStackTrace();
	    	}
	}
	
	
	
			public String extractingmessageAddPaymenttobooking() {

				System.out.println("welcome to extractingmessageAddothercharge");
				String localresponseJSONString = responseJSONString;
				JSONObject jsonResult = new JSONObject(localresponseJSONString);
				message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
				System.out.println("status msg of Add Payment To booking:"+message);
				return message;
				}
			
			
			public String extractingTransId() {

				System.out.println("welcome to extractingmessageAddothercharge");
				String localresponseJSONString = responseJSONString;
				JSONObject jsonResult = new JSONObject(localresponseJSONString);
				message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
				System.out.println("status msg of Add Payment To booking:"+message);
				return message;
				}
	}



