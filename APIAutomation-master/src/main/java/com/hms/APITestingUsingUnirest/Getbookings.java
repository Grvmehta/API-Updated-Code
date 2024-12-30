package com.hms.APITestingUsingUnirest;

import java.io.FileWriter;
import java.io.IOException;

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
import com.hms.APITestingUsingUnirest.Generic.Log4j;

public class Getbookings
{
	static String responseJSONString;
	static String getGroupCode;
	static String getGroupTempID;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String nightauditdate1;
	String restype;
	String resstatus;
   //  String nightauditdate11;
	 String RESERVE;
    String nightauditdate2;
   
	String Gueststayid;
	 String GuestID;
	 String ReservationStatus;
	 String RoomTypeID;
	 String TotalBookigAmount;
	 String DiscountedAmount;
	 String RateID;
	 String groupid;
	 static String guestdetailsid;
	
	public void Getbookingscall(String s) 
	{
		keytype = s;
		Log4j.info("keytype:"+s);
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			Log4j.info("hello if"+ accesskey);*/
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			Log4j.info("wsauthkey in addtocart:"+keyw);
			
			
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			Log4j.info("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			Log4j.info("login key in getbookings:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			nightauditdate1 = CommonConfig.currentsystemdate;
		//	nightauditdate11=CommonConfig.nightauditdate11;
			
			HttpResponse<JsonNode> responsegetbookings = Unirest.post(""+serverurl+"/ws/web/getbookings")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "442ccdd8-438b-68ed-d7c1-0aaed145ef9b")
					  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getbookings\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"fromDate\": \""+nightauditdate1+"\",\r\n         \"toDate\": \""+nightauditdate1+"\",\r\n         \"searchBy\": \"STAYDATE\",\r\n         \"reservationStatus\":[\"RESERVE\",\"CHECKIN\",\"CHECKOUT\",\"BLOCKED\",\"CANCEL\"]\r\n      }\r\n    }\r\n  }\r\n }")
					  .asJson();
			JsonNode body = responsegetbookings.getBody();
			responseJSONString = body.toString();
			Log4j.info("Hiii");
			try{
				 FileWriter jsonFileWriter = new FileWriter("E://API-March 2020//APIAutomation//NewFile");
		            jsonFileWriter.write(responseJSONString);
		            //jsonFileWriter.flush();
		            jsonFileWriter.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			Log4j.info(responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
	}//End of Getbookingscall() method for single parameter
	public void GetbookingscallforCancelchkout(String s){
		keytype = s;
		Log4j.info("keytype:"+s);
		
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			Log4j.info("wsauthkey in addtocart:"+keyw);
			
			
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			Log4j.info("login key in getbookings:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			nightauditdate2 = CommonConfig.immediateprevioustocurrentdate;
			nightauditdate1=CommonConfig.currentsystemdate;
			//nightauditdate11=CommonConfig.nightauditdate11;
			
			HttpResponse<JsonNode> responsegetbookings = Unirest.post(""+serverurl+"/ws/web/getbookings")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "442ccdd8-438b-68ed-d7c1-0aaed145ef9b")
					  .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"getbookings\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"fromDate\": \""+nightauditdate2+"\","
		                		+ "\"toDate\": \""+nightauditdate1+"\","
		                		+ "\"searchBy\": \"STAYDATE\","
		                		+ "\"reservationStatus\":\"CHECKOUT\""
		                		+ "}}}}")
		                     .asJson();
					 
			JsonNode body = responsegetbookings.getBody();
			responseJSONString = body.toString();
			Log4j.info(responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}
	
	public void Getbookingscallforreserveguest(String s){
		keytype = s;
		Log4j.info("keytype:"+s);
		
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			Log4j.info("wsauthkey in addtocart:"+keyw);
			
			
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			Log4j.info("login key in getbookings:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			nightauditdate1 = CommonConfig.currentsystemdate;
			//nightauditdate11=CommonConfig.nightauditdate11;
			
			HttpResponse<JsonNode> responsegetbookings = Unirest.post(""+serverurl+"/ws/web/getbookings")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "442ccdd8-438b-68ed-d7c1-0aaed145ef9b")
					  .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"getbookings\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"fromDate\": \""+nightauditdate1+"\","
		                		+ "\"toDate\": \""+nightauditdate1+"\","
		                		+ "\"searchBy\": \"STAYDATE\","
		                		+ "\"reservationStatus\":\"RESERVE\""
		                		+ "}}}}")
		                     .asJson();
					 
			JsonNode body = responsegetbookings.getBody();
			responseJSONString = body.toString();
			Log4j.info(responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	
	public void Getbookingscall(String s1,String s2)
	{
		keytype = s1;
		
		Log4j.info("keytype:"+s1);
		
		resstatus = s2;
		Log4j.info("resstatus:"+s2);
		
		if(keytype == "wsauth")
		{
			Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			Log4j.info("hello if"+ accesskey);
			
			
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			Log4j.info("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			//Log4j.info("we are here in get bookings");
			Log4j.info("login key in getbookings:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			nightauditdate1 = CommonConfig.currentsystemdate;
			
			HttpResponse<JsonNode> responsegetbookings = Unirest.post(""+serverurl+"/ws/web/getbookings")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "442ccdd8-438b-68ed-d7c1-0aaed145ef9b")
					  .header("page", "1")
					  .header("offset", "50")
					  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getbookings\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"fromDate\": \""+nightauditdate1+"\",\r\n         \"toDate\": \""+nightauditdate1+"\",\r\n         \"searchBy\": \"STAYDATE\",\r\n         \"reservationStatus\":[\""+resstatus+"\"]\r\n      }\r\n    }\r\n  }\r\n }")
					  .asJson();
			JsonNode body = responsegetbookings.getBody();
			responseJSONString = body.toString();
			Log4j.info(responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
	}// End of Getbookingscall() method for two parameter
	
	
	
	public String extractingmessagegetbookings()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getbookingsstring;
		getbookingsstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		Log4j.info("at last getbookings success:"+getbookingsstring);
		return getbookingsstring;
	}// End of extractingmessagegetbookings() method
	
	public String extractingcode()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getbookingscode;
		JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
		JSONObject bookingscode = getbookingsArray.getJSONObject(0);
		getbookingscode = bookingscode.getString("code");
		Log4j.info(":getbookings code:"+getbookingscode);
		return getbookingscode;
	}//End of extractingcode() method
	
	public String extractinggroupcode()
	{

		Log4j.info("welcome to extractinggroupcode");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		Log4j.info(responseJSONString);
		String getbookingsgroupcode;
		
		JSONArray getbookingsarray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
		for (int i = 0; i <  getbookingsarray.length(); i++) {                                       
	       
			
			boolean value= getbookingsarray .getJSONObject(i).getBoolean("isGroup");       		
	        if(value == true){
	        	String getgroupcode = getbookingsarray.getJSONObject(i).getJSONObject("group").getString("code");
	        	getGroupCode = getgroupcode;
	        break;
	        }
	        }
		return getGroupCode;
	}// End of extractinggroupcode() method
	
	
	public String extractinggroupmainid()
	{

		Log4j.info("welcome to extractinggrouptempid");
		String localresponseJSONString = responseJSONString;
	
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getbookingsgroupcode;
		JSONArray getbookingsarray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");

        for (int i = 0; i <  getbookingsarray.length(); i++) {                                       
        boolean value= getbookingsarray .getJSONObject(i).getBoolean("isGroup");       		
        if(value == true){
        groupid= getbookingsarray.getJSONObject(i).getJSONObject("group").getString("id");
        getGroupTempID = groupid;
        break;
        }
        }
		return groupid;
	}// End of extractinggrouptempid() method
	
	
	public String extractingmainid()
	{
		Log4j.info("welcome to extractingmainid for single reservation");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getbookingsmainid;
		JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
		JSONObject bookingsmainid = getbookingsArray.getJSONObject(0);
		getbookingsmainid = bookingsmainid.getString("mainId");
		Log4j.info(":getbookings mainid:"+getbookingsmainid);
		return getbookingsmainid;
	}//End of extractingmainid() method
	public String extractingguestStayId(){
		Log4j.info("welcome to extractingguestid");
		String localresponseJSONString = responseJSONString;
		Log4j.info(localresponseJSONString);
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
		System.out.println(getbookingsArray.length());
		JSONObject bkgObj=getbookingsArray.getJSONObject(0);
		String BookingID=bkgObj.getString("id");
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
	
	public String extractingrateid()
	{
	Log4j.info("welcome Extract Rate Id in getBookings ");
	String localresponseJSONString = responseJSONString;
	JSONObject jsonResult = new JSONObject(localresponseJSONString);
	String getbookingsmainid;
	JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
	JSONObject bookingsmainid = getbookingsArray.getJSONObject(0);
	getbookingsmainid = bookingsmainid.getString("mainId");
	JSONArray gueststays=bookingsmainid.getJSONArray("guestStays");
	GuestID=gueststays.getJSONObject(0).getJSONObject("guestDetails").getString("id");
	ReservationStatus=bookingsmainid.getString("reservationStatus");
	JSONObject roomstays=bookingsmainid.getJSONArray("roomStays").getJSONObject(0);
	RoomTypeID=roomstays.getString("roomTypeId");
	TotalBookigAmount=roomstays.getString("amount");
	DiscountedAmount=roomstays.getString("discountAmount");
	RateID=roomstays.getString("rateId");

	return RateID;
	}
	
	
	
	
	
	
}
