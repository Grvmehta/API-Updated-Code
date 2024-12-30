package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CaptureOrRefundTransaction {

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
	
	public void captureOrRefundTransaction(String s1, String s2) throws Throwable {
		
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
			System.out.println("wsauthkey in CaptureOrRefundTransaction:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in CaptureOrRefundTransaction"+keyl);
			accesskey = keyl;
			
		}
			
		try{
			
			serverurl = CommonConfig.serverurl;
			
			
			AddPaymentToBooking addPaymentToBookingObj=new AddPaymentToBooking();
			addPaymentToBookingObj.fn_addpaymenttobookingPaymentGateway("login");
			mainid=	addPaymentToBookingObj.bookingid;
			bookingcode=	addPaymentToBookingObj.bookingcode;
			String price=addPaymentToBookingObj.price;
		//	String transId=addPaymentToBookingObj.transactionId;
			/*Editbooking editbookingObj=new Editbooking();
			
			editbookingObj.fn_editbookingcallforexistingbookingid(keytype, mainid, "S");
			paymentID=editbookingObj.extractingPaymentItemID();
			System.out.println("gatewayrefund payment ID:::"+paymentID);
			Commiteditbooking commiteditbookingobj = new Commiteditbooking();
			commiteditbookingobj.CommiteditBookingCallForExistingBookingid(keytype, "S", mainid);*/
			
			Getbooking getbookingObj=new Getbooking();
			getbookingObj.getbookingcallforExistingbookingcode(keytype, bookingcode);
	String transId=	getbookingObj.extractingTransactionId();
	paymentID=getbookingObj.extractingPaymentItemID();
			HttpResponse<JsonNode> response = Unirest.post(""+serverurl+"/ws/web/captureorrefundtransaction")
	                .header("content-type", "application/json")
	                .header("x-ig-sg", "D_gg%fkl85_j")
	                .header("cache-control", "no-cache")
	                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
	                .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"captureorrefundtransaction\",\"key\":\""+accesskey+"\",\"data\":{\"transactionId\":\""+transId+"\",\"remainDaysToExpire\":\"30\",\"amount\":\""+price+"\",\"paymentId\":\""+paymentID+"\",\"remarks\":\"\",\"isvoid\":\"0\"}}}}")
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
	
   public String extractMessagecaptureorrefundtransaction()
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
