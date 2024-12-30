package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;
import org.testng.Assert;

import com.hms.APITestingUsingUnirest.Generic.Log4j;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GenerateCreditDebitProformaInvoice {
	
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
	
	 JsonNode responseJSONString;
	
	public void generateCreditDebitProformaInvoice(String s1, String s2) {
		
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
			System.out.println("wsauthkey in GenerateCreditDebitProformaInvoice:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in GenerateCreditDebitProformaInvoice"+keyl);
			accesskey = keyl;
			
		}
			
		try{
			
			serverurl = CommonConfig.serverurl;
			
			
			/*GeneratePerformaInvoice generatePerformaInvoiceObj=new GeneratePerformaInvoice();
			generatePerformaInvoiceObj.GeneratePerformaInvoiceAPI(keytype, "S");
			invoiceId=	generatePerformaInvoiceObj.extractingInvID();
			System.out.println("Credit debit invoice id after gen per inv::"+invoiceId);
			mainid=generatePerformaInvoiceObj.mainid;*/
			
			AddPaymenttoInvoice addPaymenttoInvoiceObj=new AddPaymenttoInvoice();
			addPaymenttoInvoiceObj.addpaymenttoinvoice(keytype);
			invoiceId=addPaymenttoInvoiceObj.invoiceid;
			mainid=addPaymenttoInvoiceObj.bookingid;
			System.out.println("Credit debit invoice id after gen per inv::"+invoiceId);
			System.out.println("Credit debit mainid after gen per inv::"+mainid);
			FinalizeInvoice finalizeInvoiceObj=new FinalizeInvoice();
			finalizeInvoiceObj.fn_finalizeinvoice(keytype, invoiceId);
			String finalINV=finalizeInvoiceObj.extractingInvID();
			Addothercharge addotherchargeObj= new Addothercharge();
			addotherchargeObj.AddotherchargecallForExistingBooking(keytype, mainid);
			
			
			Getaccountstatement getaccountstatementObj=new Getaccountstatement();
			getaccountstatementObj.GetaccountstatementcallForExistingBookingID(keytype, mainid);
		String itemId=	getaccountstatementObj.extractingTransactionsInGetAccountstatement();
			
			HttpResponse<JsonNode> response = Unirest.post(""+serverurl+"/ws/web/generatecreditdebitproformainvoice")
	                .header("content-type", "application/json")
	                .header("x-ig-sg", "D_gg%fkl85_j")
	                .header("cache-control", "no-cache")
	                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
	                .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"generatecreditdebitproformainvoice\",\"key\":\""+accesskey+"\",\"data\":{\"id\":\""+mainid+"\",\"type\":\"S\",\"items\":[\""+itemId+"\",],\"refInvoice\":\""+finalINV+"\",\"reason\":\"06-Finalization of Provisional assessment\"}}}}")
	                     .asJson();
			responseJSONString	=response.getBody();
			
			System.out.println(responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		 
	    }
	
	
	public String GetInvoiceTotalAmount(){
		return this.InvoiceTotalAmount;
	}
	
   public String extractMessagegenerateCreditDebitProformaInvoice()
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
