package com.hms.APITestingUsingUnirest;


import org.json.JSONArray;
import org.json.JSONObject;

import com.hms.APITestingUsingUnirest.Generic.Log4j;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GuestCheckin {
	
	String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
    String message;
   static String editbookigid;
   static String gueststayid;
	 String Roomid;
	 static String Gueststayid;
	 static String guestdetailsid;
	 static String BookingID;
	 static String Bookingcode;
	 String Isgroupbookingvalue;
     static String groupcode;
     String restype;
     String editgroupidforeditbooking;
     String paymentID;
     
	public void GuestCheckincall(String s1, String s2, String s3) {
		keytype = s1;
		Isgroupbookingvalue = s2;
		restype = s3;
		
		 if(keytype == "wsauth")
			 
			{
				Log4j.info("hello to GuestCheckin block:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				Log4j.info("wsauthkey in GuestCheckin:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				Log4j.info("login key in GuestCheckin:"+keyl);
				accesskey = keyl;
			}
		 try{
			    serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				Log4j.info("hello try"+accesskey);			
						
				Assignroom Assignroomobj=new Assignroom();
				Assignroomobj.assignroomcallfromconfirmbooking(keytype, Isgroupbookingvalue);
				editbookigid=Assignroomobj.editbookigid;
				gueststayid=Assignroomobj.gueststayid;
				paymentID=	Assignroomobj.paymentID;
				editgroupidforeditbooking = Editbooking.mainid;
				
				HttpResponse<JsonNode> responseGuestCheckin = Unirest.post(""+serverurl+"/ws/web/guestcheckin")
						.header("content-type", "application/json")
		                .header("x-ig-sg", "D_gg%fkl85_j")
		                .header("cache-control", "no-cache")
		                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
						  .body("{"  
								    + "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2015-09-16T11:01:29\","
			                		+ " \"request\": {"
			                		+ " \"method\": \"guestcheckin\","
			                		+ "\"key\": \""+accesskey+"\","
			                		+ "\"data\": {"
			                		+ "\"bookings\": ["
			                		+ " {"
			                		+ "\"id\": \""+editbookigid+"\","
			                		+ "\"isEarlyCheckIn\": false,"
			                		+ "\"guests\": ["
			                		+ "{"
			                		+ "\"guestStayId\": \""+gueststayid+"\""
			                		+ "}]}]}}}}")
			                     .asJson();
               
					JsonNode body = responseGuestCheckin.getBody();
					responseJSONString = body.toString();
					Log4j.info("Guest checkin Response:  "+responseJSONString);
			 }catch(UnirestException e)
			{
					e.printStackTrace();
				}
		 finally{
			 
			 if(restype == "S"){

		       Commiteditbooking commitbookingobj = new Commiteditbooking();
		       commitbookingobj.CommiteditBookingCallForExistingBookingid("login", restype, editbookigid );

		}
			 if(restype == "G") {
				 Commiteditbooking commitbookingobj = new Commiteditbooking();
			     commitbookingobj.CommiteditBookingCallForExistingBookingid("login", restype, editgroupidforeditbooking );
			 }
		 }
		
	}
	public String extractingmessageGuestCheckin(){
		Log4j.info("welcome to extractingmessageGuestCheckin");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		Log4j.info("Status msg of Guestchkin :"+message);
		
		//return editbookingstring;
		return message;
		
	}
	
	public String extractingguestStayId(){
		Log4j.info("welcome to extractingguestid");
		String localresponseJSONString = responseJSONString;
		Log4j.info(localresponseJSONString);
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
		System.out.println(getbookingsArray.length());
		JSONObject bkgObj=getbookingsArray.getJSONObject(0);
		 Bookingcode = bkgObj.getString("code");
		 BookingID=bkgObj.getString("id");
		 Log4j.info(BookingID);
		 JSONArray gueststaysarr=bkgObj.getJSONArray("guestStays");
		 System.out.println(gueststaysarr.length());
		 JSONObject gueststay=gueststaysarr.getJSONObject(0);
		 Gueststayid= gueststay.getString("id");
		 Log4j.info(Gueststayid);
		 Log4j.info("Guest Stays Id from getbookins:"+Gueststayid);
		 JSONObject new1 = gueststay.getJSONObject("guestDetails");
		 guestdetailsid = new1.getString("id");	
		 return Gueststayid;
	
	
	} 
	
	public String extractingguestStayIdforgroup(){
		Log4j.info("welcome to extractingguestid");
		String localresponseJSONString = responseJSONString;
		Log4j.info(localresponseJSONString);
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
		groupcode = getbookingsArray.getJSONObject(0).getJSONObject("group").getString("code");
		System.out.println(getbookingsArray.length());
		JSONObject bkgObj=getbookingsArray.getJSONObject(0);
		 Bookingcode = bkgObj.getString("code");
		 BookingID=bkgObj.getString("id");
		 Log4j.info(BookingID);
		 JSONArray gueststaysarr=bkgObj.getJSONArray("guestStays");
		 System.out.println(gueststaysarr.length());
		 JSONObject gueststay=gueststaysarr.getJSONObject(0);
		 Gueststayid= gueststay.getString("id");
		 Log4j.info(Gueststayid);
		 Log4j.info("Guest Stays Id from getbookins:"+Gueststayid);
		 JSONObject new1 = gueststay.getJSONObject("guestDetails");
		 guestdetailsid = new1.getString("id");	
		 return Gueststayid;
	
	
	} 

}
