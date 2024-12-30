package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class PerformNightAudit {
	
	public static String InvoiceID;
	static String InvoiceTotalAmount;
	//static String responseJSONString;
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
	static String invoiceId="";
	String paymentID;
	String bookingcode;
	
	 JsonNode responseJSONString;
	
	public void performnightaudit(String s1) throws Throwable {
		
		keytype = s1;	
		//reservationtype = s2;
		System.out.println("keytype:"+s1);
		
	
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in GatewayRefund:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in GatewayRefund"+keyl);
			accesskey = keyl;
			
		}
			
		try{
			
			serverurl = CommonConfig.serverurl;
			HttpResponse<JsonNode> response = Unirest.post(""+serverurl+"/ws/web/performnightaudit")
	                .header("content-type", "application/json")
	                .header("x-ig-sg", "D_gg%fkl85_j")
	                .header("cache-control", "no-cache")
	                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
	                .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"performnightaudit\",\"key\":\""+accesskey+"\",\"data\":{\"ignoreAllOpenCounters\":true}}}}")
	                     .asJson();
			responseJSONString	=response.getBody();
			
			System.out.println(responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		 
	    }
	
	
	
	
   public String extractMessagePerformnightaudit()
   {
	   JSONObject jobj=responseJSONString.getObject();
		 JSONObject hotelogix= jobj.getJSONObject("hotelogix");
		 JSONObject respons=hotelogix.getJSONObject("response");
		 //JSONObject invoice=respons.getJSONArray("invoices").getJSONObject(0);
		// InvoiceTotalAmount=invoice.getString("amount");
		 //InvoiceID=invoice.get("invoiceId").toString();
		 JSONObject status= respons.getJSONObject("status");
		 msg=status.getString("message");
		 return msg;
		 
   }



}
