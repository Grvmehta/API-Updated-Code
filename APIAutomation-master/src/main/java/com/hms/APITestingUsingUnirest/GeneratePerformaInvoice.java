package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;
import org.testng.Assert;

import com.hms.APITestingUsingUnirest.Generic.Log4j;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class GeneratePerformaInvoice {
	public static String InvoiceID;
	static String InvoiceTotalAmount;
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String reservationtype;
	String accesskey;
	String searchstring;
	String nightauditdate;
	String nightauditdate1;
	static String mainid;
	String msg=null;
	static String guestID="";
	
	public void GeneratePerformaInvoiceAPI(String s1, String s2) {
		
		keytype = s1;	
		reservationtype = s2;
		System.out.println("keytype:"+s1);
		System.out.println("reservationtype:"+s2);
	
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in addtocart:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in generate performa invoice:"+keyl);
			accesskey = keyl;
			
		}
			
		try{
			
			serverurl = CommonConfig.serverurl;
			
			
			//AddOtherChargeAPI called to add charges so that PI can be generated.
		
			if(reservationtype == "S"){
					
			Addothercharge addotherchargeobj = new Addothercharge();
		    addotherchargeobj.Addotherchargecall("login");
		    mainid = Addothercharge.bookingid;
		    guestID=Addothercharge.guestid;
		    //GetBookings called to get booking id
		
/*			Getbookings getbookingsobj = new Getbookings();
			getbookingsobj.Getbookingscall("login", "CHECKIN");
		    mainid = getbookingsobj.extractingmainid();*/
		    
		  
			System.out.println("Booking Id for getbookings:" +mainid);
			
			}
			
			else if(reservationtype =="G"){
				
				Addothercharge addotherchargeobj = new Addothercharge();
				Log4j.info("We are here adding other charges");
			    addotherchargeobj.AddotherchargecallWithGroupChargetype("login");
			    mainid = addotherchargeobj.GroupId;
			    
				
			}
			
			HttpResponse<JsonNode> response = Unirest.post(""+serverurl+"/ws/web/generateproformainvoice")
	                .header("content-type", "application/json")
	                .header("x-ig-sg", "D_gg%fkl85_j")
	                .header("cache-control", "no-cache")
	                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
	                .body("{"
	                		+ "\"hotelogix\": {"
	                		+ "\"version\": \"1.0\","
	                		+ "\"datetime\": \"2012-01-16T10:10:15\","
	                		+ "\"request\": {"
	                		+ "\"method\": \"generateproformainvoice\","
	                		+ "\"key\": \""+accesskey+"\","
	                		+ "\"data\": {"
	                		+ "\"id\": \""+mainid+"\","
	                		+ "\"type\": \""+reservationtype+"\""
	                		+ "}}}}")
	                     .asJson();
			
			JsonNode body = response.getBody();
			
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
			
			// JsonNode responseJSONString=response.getBody();
			// responseJSONString = body.toString();
			 /*JSONObject jobj=responseJSONString.getObject();
			 JSONObject hotelogix= jobj.getJSONObject("hotelogix");
			 JSONObject respons=hotelogix.getJSONObject("response");
			 JSONObject invoice=respons.getJSONArray("invoices").getJSONObject(0);
			 InvoiceTotalAmount=invoice.getString("amount");
			 InvoiceID=invoice.get("invoiceId").toString();
			 JSONObject status= respons.getJSONObject("status");
			 msg=status.getString("message");
			 Assert.assertEquals(msg, "success");
			 System.out.println();
			 System.out.println(responseJSONString);*/
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		 
	    }
	
	
	public String GetInvoiceTotalAmount(){
		return this.InvoiceTotalAmount;
	}
	
    public void GeneratePerformaInvoiceAPI(String s1, String s2, String s3) {
		
		keytype = s1;	
		reservationtype = s2;
		mainid = s3;
		
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in addtocart:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getinvoices:"+keyl);
			accesskey = keyl;
			
		}
			
		try{
			
			serverurl = CommonConfig.serverurl;
			
			HttpResponse<JsonNode> response = Unirest.post(""+serverurl+"/ws/web/generateproformainvoice")
	                .header("content-type", "application/json")
	                .header("x-ig-sg", "D_gg%fkl85_j")
	                .header("cache-control", "no-cache")
	                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
	                .body("{"
	                		+ "\"hotelogix\": {"
	                		+ "\"version\": \"1.0\","
	                		+ "\"datetime\": \"2012-01-16T10:10:15\","
	                		+ "\"request\": {"
	                		+ "\"method\": \"generateproformainvoice\","
	                		+ "\"key\": \""+accesskey+"\","
	                		+ "\"data\": {"
	                		+ "\"id\": \""+mainid+"\","
	                		+ "\"type\": \""+reservationtype+"\""
	                		+ "}}}}")
	                     .asJson();
			 JsonNode responseJSONString=response.getBody();
			System.out.println(responseJSONString);
			 JSONObject jobj=responseJSONString.getObject();
			 JSONObject hotelogix= jobj.getJSONObject("hotelogix");
			 JSONObject respons=hotelogix.getJSONObject("response");
			 JSONObject invoice=respons.getJSONArray("invoices").getJSONObject(0);
			 InvoiceTotalAmount=invoice.getString("amount");
			 InvoiceID=invoice.get("invoiceId").toString();
			 JSONObject status= respons.getJSONObject("status");
			 msg=status.getString("message");
			 Assert.assertEquals(msg, "success");
			 System.out.println();
			 System.out.println(responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		 
	    }


			
	
	public String extractingGeneratePerformaInvoice() {

		System.out.println("welcome to  extractingGeneratePerformaInvoice");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 JSONObject hotelogix= jsonResult.getJSONObject("hotelogix");
		 JSONObject respons=hotelogix.getJSONObject("response");
		 JSONObject invoice=respons.getJSONArray("invoices").getJSONObject(0);
		 InvoiceTotalAmount=invoice.getString("amount");
		 InvoiceID=invoice.get("invoiceId").toString();
		 JSONObject status= respons.getJSONObject("status");
		 msg=status.getString("message");
		System.out.println("status msg of GeneratePerformaInvoice:"+msg);
		return msg;
		}
	
	public String extractingInvID() {

		System.out.println("welcome to  extractingGeneratePerformaInvoice");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 JSONObject hotelogix= jsonResult.getJSONObject("hotelogix");
		 JSONObject respons=hotelogix.getJSONObject("response");
		 JSONObject invoice=respons.getJSONArray("invoices").getJSONObject(0);
		 InvoiceTotalAmount=invoice.getString("amount");
		 InvoiceID=invoice.get("invoiceId").toString();
		return InvoiceID;
		}
	
	
}


