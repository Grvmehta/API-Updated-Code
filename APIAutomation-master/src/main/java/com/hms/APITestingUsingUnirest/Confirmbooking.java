package com.hms.APITestingUsingUnirest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.hms.APITestingUsingUnirest.CommonConfig;

public class Confirmbooking
{
static String responseJSONString;
	 String serverurl;
String hotelid1;
	 String keytype;
	 String accesskey;
	 public static String bookingCode;
	 String Noofroom;
	 String Isgroupbookingvalue;
     String Noofadult;
     static String orderid;


	 public void Confirmbookingcall(String s1)
	 {
		 
		 keytype = s1;
		 
		 if(keytype == "wsauth")
			{
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in confirmbooking:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in confirmbooking:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 System.out.println("welcome to confirmbooking try block:");
			 serverurl = CommonConfig.serverurl;
			 
			 System.out.println("hello accesskey in confirmbooking:"+accesskey);
			 Savebooking savebookingobj = new Savebooking();
			 savebookingobj.Savebookingcall(keytype);
			 String orderid = savebookingobj.extractingorderid();
			 System.out.println("orderid in confirmbooking"+orderid);
			 String deposittotal = savebookingobj.extractingdeposittotal();
			 System.out.println("deposit total in confirmbooking"+deposittotal);
			 
			 HttpResponse<JsonNode> responseconfirmbooking = Unirest.post(""+serverurl+"/ws/web/confirmbooking")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "58e24a66-d02d-4398-5490-23f8af38a960")
					  .body("{\r\n    \"hotelogix\": {\r\n         \"version\": \"1.0\",\r\n         \"datetime\": \"2012-01-16T10:10:15\",\r\n         \"request\": {\r\n             \"method\": \"confirmbooking\",\r\n             \"key\": \""+accesskey+"\",\r\n             \"languagecode\": \"en\",\r\n             \"data\": {\r\n                \"payment\": {\r\n                  \"amount\": \""+deposittotal+"\",\r\n                  \"isguestcc\": \"0\",\r\n                  \"type\": \"CC\",\r\n                  \"description\": \"Test Payment\"\r\n                },\r\n                \"creditcard\": {\r\n                  \"nameoncard\": \"Test Dev\",\r\n                  \"cardno\": \"4111111111111111\",\r\n                  \"cardtype\": \"visa\",\r\n                  \"cvc\": \"156\",\r\n                  \"expirymonth\": \"12\",\r\n                  \"expiryyear\": \"2019\"\r\n                },\r\n                \"orderId\": {\r\n                  \"value\": \""+orderid+"\"\r\n                }\r\n             }\r\n\r\n         }\r\n    }\r\n }")
					  .asJson();
			 
			 JsonNode body = responseconfirmbooking.getBody();
			 System.out.println(body);
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
	 }// End of Confirmbookingcall() method
	
	 
	 public void confirmbookingforFrontDeskfor3DResv(String s1,String s2){
         keytype = s1;
         Isgroupbookingvalue=s2;
if(keytype == "wsauth")
{

String keyw = Wsauth.wsauthkeyfinalstring;
accesskey = keyw;
System.out.println("wsauthkey in confirmbooking:"+keyw);
}

else if(keytype == "login")
{

String keyl = Login.finalloginaccesskey;
System.out.println("login key in confirmbooking:"+keyl);
accesskey = keyl;
}

try
{
System.out.println("welcome to confirmbooking try block:");
serverurl = CommonConfig.serverurl;

System.out.println("hello accesskey in confirmbooking:"+accesskey);
Savebooking savebookingobj = new Savebooking();
savebookingobj.SavebookingforFrontDeskfor3DResv(keytype,Isgroupbookingvalue);
String orderid = savebookingobj.extractingorderid();
System.out.println("orderid in confirmbooking"+orderid);
String deposittotal = savebookingobj.extractingdeposittotal();
System.out.println("deposit total in confirmbooking"+deposittotal);

HttpResponse<JsonNode> responseconfirmbooking = Unirest.post(""+serverurl+"/ws/web/confirmbooking")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("content-type", "application/json")
 .header("cache-control", "no-cache")
 .header("postman-token", "58e24a66-d02d-4398-5490-23f8af38a960")
 .body("{\r\n    \"hotelogix\": {\r\n         \"version\": \"1.0\",\r\n         \"datetime\": \"2012-01-16T10:10:15\",\r\n         \"request\": {\r\n             \"method\": \"confirmbooking\",\r\n             \"key\": \""+accesskey+"\",\r\n             \"languagecode\": \"en\",\r\n             \"data\": {\r\n                \"payment\": {\r\n                  \"amount\": \""+deposittotal+"\",\r\n                  \"isguestcc\": \"0\",\r\n                  \"type\": \"CC\",\r\n                  \"description\": \"Test Payment\"\r\n                },\r\n                \"creditcard\": {\r\n                  \"nameoncard\": \"Test Dev\",\r\n                  \"cardno\": \"4111111111111111\",\r\n                  \"cardtype\": \"visa\",\r\n                  \"cvc\": \"156\",\r\n                  \"expirymonth\": \"12\",\r\n                  \"expiryyear\": \"2023\"\r\n                },\r\n                \"orderId\": {\r\n                  \"value\": \""+orderid+"\"\r\n                }\r\n             }\r\n\r\n         }\r\n    }\r\n }")
 .asJson();

JsonNode body = responseconfirmbooking.getBody();
responseJSONString = body.toString();
System.out.println(responseJSONString);
}

catch(UnirestException e)
{
e.printStackTrace();
}
}  
	 public void ConfirmbookingcallforFrontDeskforfutureDate(String s1,String s2,String s3){
 keytype = s1;
 Noofroom=s2;
 Isgroupbookingvalue=s3;	 
		 if(keytype == "wsauth")
			{
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in confirmbooking:"+keyw);
			}
			
			else if(keytype == "login")
			{
				
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in confirmbooking:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 System.out.println("welcome to confirmbooking try block:");
			 serverurl = CommonConfig.serverurl;
			 
			 System.out.println("hello accesskey in confirmbooking:"+accesskey);
			 Savebooking savebookingobj = new Savebooking();
			 savebookingobj.SavebookingcallforFrontDeskforfutureDate(keytype,Noofroom,Isgroupbookingvalue);
			 String orderid = savebookingobj.extractingorderid();
			 System.out.println("orderid in confirmbooking"+orderid);
			 String deposittotal = savebookingobj.extractingdeposittotal();
			 System.out.println("deposit total in confirmbooking"+deposittotal);
			 
			 HttpResponse<JsonNode> responseconfirmbooking = Unirest.post(""+serverurl+"/ws/web/confirmbooking")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "58e24a66-d02d-4398-5490-23f8af38a960")
					  .body("{\r\n    \"hotelogix\": {\r\n         \"version\": \"1.0\",\r\n         \"datetime\": \"2012-01-16T10:10:15\",\r\n         \"request\": {\r\n             \"method\": \"confirmbooking\",\r\n             \"key\": \""+accesskey+"\",\r\n             \"languagecode\": \"en\",\r\n             \"data\": {\r\n                \"payment\": {\r\n                  \"amount\": \""+deposittotal+"\",\r\n                  \"isguestcc\": \"0\",\r\n                  \"type\": \"CC\",\r\n                  \"description\": \"Test Payment\"\r\n                },\r\n                \"creditcard\": {\r\n                  \"nameoncard\": \"Test Dev\",\r\n                  \"cardno\": \"4111111111111111\",\r\n                  \"cardtype\": \"visa\",\r\n                  \"cvc\": \"156\",\r\n                  \"expirymonth\": \"12\",\r\n                  \"expiryyear\": \"2019\"\r\n                },\r\n                \"orderId\": {\r\n                  \"value\": \""+orderid+"\"\r\n                }\r\n             }\r\n\r\n         }\r\n    }\r\n }")
					  .asJson();
			 
			 JsonNode body = responseconfirmbooking.getBody();
			 System.out.println(body);
			 responseJSONString = body.toString();
			 System.out.println("confirm booking respons::"+responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 } 
	 }
	 
	 public void ConfirmbookingcallforFrontDesk(String s1, String s2){
         keytype = s1;
         Isgroupbookingvalue = s2;
		 if(keytype == "wsauth")
			{
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in confirmbooking:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in confirmbooking:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 System.out.println("welcome to confirmbooking try block:");
			 serverurl = CommonConfig.serverurl;
			 
			 System.out.println("hello accesskey in confirmbooking:"+accesskey);
			 Savebooking savebookingobj = new Savebooking();
			 savebookingobj.SavebookingcallforFrontDesk(keytype, Isgroupbookingvalue);
			 String orderid = savebookingobj.extractingorderid();
			 System.out.println("orderid in confirmbooking"+orderid);
			 String deposittotal = savebookingobj.extractingdeposittotal();
			 System.out.println("deposit total in confirmbooking"+deposittotal);
			 
			 HttpResponse<JsonNode> responseconfirmbooking = Unirest.post(""+serverurl+"/ws/web/confirmbooking")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "58e24a66-d02d-4398-5490-23f8af38a960")
					  .body("{\r\n    \"hotelogix\": {\r\n         \"version\": \"1.0\",\r\n         \"datetime\": \"2012-01-16T10:10:15\",\r\n         \"request\": {\r\n             \"method\": \"confirmbooking\",\r\n             \"key\": \""+accesskey+"\",\r\n             \"languagecode\": \"en\",\r\n             \"data\": {\r\n                \"payment\": {\r\n                  \"amount\": \""+deposittotal+"\",\r\n                  \"isguestcc\": \"0\",\r\n                  \"type\": \"CC\",\r\n                  \"description\": \"Test Payment\"\r\n                },\r\n                \"creditcard\": {\r\n                  \"nameoncard\": \"Test Dev\",\r\n                  \"cardno\": \"4111111111111111\",\r\n                  \"cardtype\": \"visa\",\r\n                  \"cvc\": \"156\",\r\n                  \"expirymonth\": \"12\",\r\n                  \"expiryyear\": \"2027\"\r\n                },\r\n                \"orderId\": {\r\n                  \"value\": \""+orderid+"\"\r\n                }\r\n             }\r\n\r\n         }\r\n    }\r\n }")
					  .asJson();
			 
			 JsonNode body = responseconfirmbooking.getBody();
			 System.out.println(body);
			 responseJSONString = body.toString();
			 System.out.println("confirm booking respons::"+responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
	 }
	 public void confirmbookingforgroupresv(String s1){
         keytype = s1;

         if(keytype == "wsauth")
         {

			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in confirmbooking:"+keyw);
			}
			
			else if(keytype == "login")
			{
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in confirmbooking:"+keyl);
			accesskey = keyl;
			}
			
			try
			{
			System.out.println("welcome to confirmbooking try block:");
			serverurl = CommonConfig.serverurl;
			
			System.out.println("hello accesskey in confirmbooking:"+accesskey);
			Savebooking savebookingobj = new Savebooking();
			savebookingobj.Savebookingforgroupresv(keytype);
		    orderid = savebookingobj.extractingorderid();
			System.out.println("orderid in confirmbooking"+orderid);
			String deposittotal = savebookingobj.extractingdeposittotal();
			System.out.println("deposit total in confirmbooking"+deposittotal);
			
			HttpResponse<JsonNode> responseconfirmbooking = Unirest.post(""+serverurl+"/ws/web/confirmbooking")
			 .header("x-ig-sg", "D_gg%fkl85_j")
			 .header("content-type", "application/json")
			 .header("cache-control", "no-cache")
			 .header("postman-token", "58e24a66-d02d-4398-5490-23f8af38a960")
			 .body("{\r\n    \"hotelogix\": {\r\n         \"version\": \"1.0\",\r\n         \"datetime\": \"2012-01-16T10:10:15\",\r\n         \"request\": {\r\n             \"method\": \"confirmbooking\",\r\n             \"key\": \""+accesskey+"\",\r\n             \"languagecode\": \"en\",\r\n             \"data\": {\r\n                \"payment\": {\r\n                  \"amount\": \""+deposittotal+"\",\r\n                  \"isguestcc\": \"0\",\r\n                  \"type\": \"CC\",\r\n                  \"description\": \"Test Payment\"\r\n                },\r\n                \"creditcard\": {\r\n                  \"nameoncard\": \"Test Dev\",\r\n                  \"cardno\": \"4111111111111111\",\r\n                  \"cardtype\": \"visa\",\r\n                  \"cvc\": \"156\",\r\n                  \"expirymonth\": \"12\",\r\n                  \"expiryyear\": \"2019\"\r\n                },\r\n                \"orderId\": {\r\n                  \"value\": \""+orderid+"\"\r\n                }\r\n             }\r\n\r\n         }\r\n    }\r\n }")
			 .asJson();
			
			JsonNode body = responseconfirmbooking.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
			}
			
			catch(UnirestException e)
			{
			e.printStackTrace();
			}
			}
	 public void confirmbookingforgroupresv(String s1, String s2){
         keytype = s1;
         Noofadult = s2;
			if(keytype == "wsauth")
			{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in confirmbooking:"+keyw);
			}
			
			else if(keytype == "wsauthforTA")
			{
			Wsauth objwsauth = new Wsauth();
			objwsauth.WsauthcallforTA();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			}
			
			else if(keytype == "wsauthforCorp")
			{
			Wsauth objwsauth = new Wsauth();
			objwsauth.WsauthcallforCorp();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			}
			else if(keytype == "login")
			{
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in confirmbooking:"+keyl);
			accesskey = keyl;
			}
			
			try
			{
			System.out.println("welcome to confirmbooking try block:");
			serverurl = CommonConfig.serverurl;
			
			System.out.println("hello accesskey in confirmbooking:"+accesskey);
			Savebooking savebookingobj = new Savebooking();
			savebookingobj.Savebookingforgroupresv(keytype, Noofadult);
			accesskey = savebookingobj.accesskey;
			String orderid = savebookingobj.extractingorderid();
			System.out.println("orderid in confirmbooking"+orderid);
			String deposittotal = savebookingobj.extractingdeposittotal();
			System.out.println("deposit total in confirmbooking"+deposittotal);
			
			HttpResponse<JsonNode> responseconfirmbooking = Unirest.post(""+serverurl+"/ws/web/confirmbooking")
			 .header("x-ig-sg", "D_gg%fkl85_j")
			 .header("content-type", "application/json")
			 .header("cache-control", "no-cache")
			 .header("postman-token", "58e24a66-d02d-4398-5490-23f8af38a960")
			 .body("{\r\n    \"hotelogix\": {\r\n         \"version\": \"1.0\",\r\n         \"datetime\": \"2012-01-16T10:10:15\",\r\n         \"request\": {\r\n             \"method\": \"confirmbooking\",\r\n             \"key\": \""+accesskey+"\",\r\n             \"languagecode\": \"en\",\r\n             \"data\": {\r\n                \"payment\": {\r\n                  \"amount\": \""+deposittotal+"\",\r\n                  \"isguestcc\": \"0\",\r\n                  \"type\": \"CC\",\r\n                  \"description\": \"Test Payment\"\r\n                },\r\n                \"creditcard\": {\r\n                  \"nameoncard\": \"Test Dev\",\r\n                  \"cardno\": \"4111111111111111\",\r\n                  \"cardtype\": \"visa\",\r\n                  \"cvc\": \"156\",\r\n                  \"expirymonth\": \"12\",\r\n                  \"expiryyear\": \"2019\"\r\n                },\r\n                \"orderId\": {\r\n                  \"value\": \""+orderid+"\"\r\n                }\r\n             }\r\n\r\n         }\r\n    }\r\n }")
			 .asJson();
			
			JsonNode body = responseconfirmbooking.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
			}
			
			catch(UnirestException e)
			{
			e.printStackTrace();
			}
}
   
	 public String extractingmessageconfirmbooking()
	 {
		 	String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String confirmbookingstring;
			confirmbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last confirmbooking::"+confirmbookingstring);
			return confirmbookingstring;
	 }// End of extractingmessageconfirmbooking() method
	 public String extracingbookingid(){
		 String bookingid;
		 String localresponseJSONString = responseJSONString;
		 System.out.println("New::::"+localresponseJSONString);
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 String confirmbookingstring;
		 confirmbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		 JSONObject newresp = jsonResult.getJSONObject("hotelogix");
		 System.out.println(newresp);
		 JSONObject resp= newresp.getJSONObject("response");
		 System.out.println(resp);
		 JSONObject order=resp.getJSONObject("order");
		 String booking=order.get("bookings").toString();
		 Confirmbooking confirmbookingobj=new Confirmbooking();
		 JSONObject bookingjobj=confirmbookingobj.GetJsonObject(booking);
		 //JSONObject hotels=resp.getJSONArray("bookings").getJSONObject(0);
		 //JSONObject hotels=resp.getJSONObject("bookings");
		 //System.out.println(hotels);
		 bookingid=bookingjobj.get("id").toString();
		 System.out.println("The booking id is:" +bookingid);
		 //System.out.println("last createagent success"+confirmbookingstring);
		// return confirmbookingstring;
        
		 return bookingid;
		 
	 }
	 public String extracingbookingCode(){
		 String bookingid;
		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 String confirmbookingstring;
		 confirmbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		 JSONObject newresp = jsonResult.getJSONObject("hotelogix");
		 System.out.println(newresp);
		 JSONObject resp= newresp.getJSONObject("response");
		 System.out.println(resp);
		 JSONObject order=resp.getJSONObject("order");
		 String booking=order.get("bookings").toString();
		 Confirmbooking confirmbookingobj=new Confirmbooking();
		 JSONObject bookingjobj=confirmbookingobj.GetJsonObject(booking);
		 //JSONObject hotels=resp.getJSONArray("bookings").getJSONObject(0);
		 //JSONObject hotels=resp.getJSONObject("bookings");
		 //System.out.println(hotels);
		 bookingid=bookingjobj.get("id").toString();
		 System.out.println("The booking id is:" +bookingid);
		 bookingCode=bookingjobj.get("code").toString();
		 System.out.println("The booking code is:" +bookingCode);
		 return bookingCode;
	 }
	 public String extractinggroupcode(){


		// code to extract booking code
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		System.out.println("DEBUG RESPONSE JSON STRING:"+responseJSONString);
		String confirmbookingstring;
		confirmbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		JSONObject newresp = jsonResult.getJSONObject("hotelogix");
		System.out.println("The new" +newresp);
		JSONObject resp= newresp.getJSONObject("response");

		JSONObject newstatus = resp.getJSONObject("order");

		    JSONArray newarray = newstatus.getJSONArray("bookings");
		JSONObject book1 = newarray.getJSONObject(0);
		      String bookingcode = book1.getString("groupcode");
		System.out.println("The booking id is:" +bookingcode);
		return bookingcode;
		}
	 public String extracinggroupid(){

		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 String confirmbookingstring;
		 confirmbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		 JSONObject newresp = jsonResult.getJSONObject("hotelogix");
		 System.out.println("The new" +newresp);
		 JSONObject resp= newresp.getJSONObject("response");

		 JSONObject newstatus = resp.getJSONObject("order");

		 //JSONObject book1 = newstatus.getJSONObject("id");
		 JSONArray newarray = newstatus.getJSONArray("bookings");
		 JSONObject book2 = newarray.getJSONObject(0);
		 JSONObject newgrp = book2.getJSONObject("group");
		 String groupid = newgrp.getString("id");
		 System.out.println("The group id is" +groupid);
		 return groupid;



		 }
	 
	 public JSONObject GetJsonObject(String value) throws JSONException{
			String str=value.substring(1, value.length()-1);
			JSONObject bookingjobj=new JSONObject(str);
			return bookingjobj;
		}
	
}// End of class
