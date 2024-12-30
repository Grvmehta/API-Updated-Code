package com.hms.APITestingUsingUnirest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Year;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.hms.APITestingUsingUnirest.CommonConfig;

public class Getbooking
{
	static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;

	String Bookingcode;

	String EarlyCheckOutChargeAmount;

	static String GuestID;

	static String ReservationStatus;

	String RoomTypeID;

	static String roomId;
     
	String bookingcode;
	
	String transID;

	static String amount;
	public void getbookingcall(String s) throws IOException
	{
		keytype = s;
		System.out.println("keytype:"+s);
		
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
			System.out.println("Its here");
			System.out.println("login key in getbooking:"+keyl);
			accesskey = keyl;
		}
	
	
	try
	{
		serverurl = CommonConfig.serverurl;
		Getbookings getbookingsobj = new Getbookings();
		getbookingsobj.Getbookingscall("login");
		String code = getbookingsobj.extractingcode();
		System.out.println("code:"+code);
		
		HttpResponse<JsonNode> responsegetbooking = Unirest.post(""+serverurl+"/ws/web/getbooking")
				  .header("content-type", "application/json")
				  .header("x-ig-sg", "D_gg%fkl85_j")
				  .header("cache-control", "no-cache")
				  .header("postman-token", "1b04f315-1c8b-b5a2-3cea-faa65507afe7")
				  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"code\": \""+code+"\"\r\n      }\r\n    }\r\n  }\r\n }")
				  .asJson();
		JsonNode body = responsegetbooking.getBody();
		responseJSONString = body.toString();
		System.out.println(responseJSONString);
	}
	catch(UnirestException e)
	{
		e.printStackTrace();
	}

  }//End of getbookingcall() method
	public void getbookingcallforExistingbookingcode(String s,String s2)
	{
		keytype = s;
		Bookingcode=s2;
		System.out.println("keytype:"+s);
		
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in addtocart:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getbooking:"+keyl);
			accesskey = keyl;
		}
	
	
	try
	{
		serverurl = CommonConfig.serverurl;
		/*Getbookings getbookingsobj = new Getbookings();
		getbookingsobj.Getbookingscall("login");
		String code = getbookingsobj.extractingcode();
		System.out.println("code:"+code);*/
		
		HttpResponse<JsonNode> responsegetbooking = Unirest.post(""+serverurl+"/ws/web/getbooking")
				  .header("content-type", "application/json")
				  .header("x-ig-sg", "D_gg%fkl85_j")
				  .header("cache-control", "no-cache")
				  .header("postman-token", "1b04f315-1c8b-b5a2-3cea-faa65507afe7")
				  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"code\": \""+Bookingcode+"\"\r\n      }\r\n    }\r\n  }\r\n }")
				  .asJson();
		JsonNode body = responsegetbooking.getBody();
		responseJSONString = body.toString();
		System.out.println("getbooking::::"+responseJSONString);
	}
	catch(UnirestException e)
	{
		e.printStackTrace();
	}
	}
	public String extractingbookingid()
	{
		System.out.println("welcome to extractingtempid");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String bookingid;
		JSONObject hotelogix= jsonResult.getJSONObject("hotelogix");
		 JSONObject respons=hotelogix.getJSONObject("response");
		 JSONObject status= respons.getJSONObject("status");
		/*String msg=status.getString("message");
		 Assert.assertEquals(msg, "success");*/
		 
		 JSONObject bookingObj= respons.getJSONObject("booking");
		bookingid = respons.getJSONObject("booking").getString("id");
		System.out.println(":Editbookings mainid:"+bookingid);
		return bookingid;
	}
	public String extractingroomTypeId()
	{
		System.out.println("welcome to roomTypeId");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String bookingid;
		JSONObject hotelogix= jsonResult.getJSONObject("hotelogix");
		 JSONObject respons=hotelogix.getJSONObject("response");
		 JSONObject status= respons.getJSONObject("status");
		/*String msg=status.getString("message");
		 Assert.assertEquals(msg, "success");*/
		 
		 JSONObject bookingObj= respons.getJSONObject("booking");
		bookingid = respons.getJSONObject("booking").getString("id");
		System.out.println(":Editbookings mainid:"+bookingid);
		JSONArray gueststays=bookingObj.getJSONArray("guestStays");
		 GuestID=gueststays.getJSONObject(0).getJSONObject("guestDetails").getString("id");
		 ReservationStatus=bookingObj.getString("reservationStatus");
		 JSONObject roomstays=bookingObj.getJSONArray("roomStays").getJSONObject(0);
		 roomId=roomstays.getString("roomId");
		 System.out.println("Room id:"+roomId);
		 RoomTypeID=roomstays.getString("roomTypeId");
		return  RoomTypeID;
	}

	
	public String extractingTransactionId()
	{
		System.out.println("welcome to extractingTransactionId");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String bookingid;
		JSONObject hotelogix= jsonResult.getJSONObject("hotelogix");
		 JSONObject respons=hotelogix.getJSONObject("response");
		 JSONObject status= respons.getJSONObject("status");
		/*String msg=status.getString("message");
		 Assert.assertEquals(msg, "success");*/
		 
		 JSONObject bookingObj= respons.getJSONObject("booking");
		bookingid = respons.getJSONObject("booking").getString("id");
		System.out.println(":Editbookings mainid:"+bookingid);
		JSONArray payments=bookingObj.getJSONArray("payments");
		int len=payments.length();
		System.out.println("Array Len::"+len);
		int obj=len-1;
		 transID=payments.getJSONObject(obj).getString("transactionId");
		System.out.println("Transaction id in getbooking:::"+transID);
		return  transID;
	}
	
	public String extractingPaymentItemID()
	{
	System.out.println("welcome to extractingmessageeditbooking");
	String localresponseJSONString = responseJSONString;
	System.out.println("response json string in extracting payment id:::"+responseJSONString);
	JSONObject jsonResult = new JSONObject(localresponseJSONString);
	String editbookingstring;
	JSONObject getbookingsObj = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("booking");
	JSONArray getPaymentsArray=getbookingsObj.getJSONArray("payments");
	int len=getPaymentsArray.length();
	JSONObject getPaymentObj=getPaymentsArray.getJSONObject(len-1);
	editbookingstring=getPaymentObj.getString("id");
	System.out.println("PaymentItemID from Getbooking:::"+editbookingstring);
	return editbookingstring;
	}

	public String extractingmessagegetbooking()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getbookingstring;
		getbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getbooking success:"+getbookingstring);
		return getbookingstring;
	}//End of extractingmessagegetbooking() method
	


   public String extractingpaymentid()
   {
   System.out.println("welcome to extractingpaymentid");
   String localresponseJSONString = responseJSONString;
   JSONObject jsonResult = new JSONObject(localresponseJSONString);  
   JSONObject hotelogix= jsonResult.getJSONObject("hotelogix");
   JSONObject respons=hotelogix.getJSONObject("response");
   System.out.println(respons);
   JSONObject respons1 = respons.getJSONObject("booking");
   System.out.println(respons1); 
   JSONArray JSONArray1  =  respons1.getJSONArray("payments");
   JSONObject response3  = respons1.getJSONArray("payments").getJSONObject(0);
   amount = response3.getString("amount");
   String paymentid =response3.getString("id");
  

   return paymentid;
   }
}


