package com.hms.APITestingUsingUnirest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;

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


public class Getgroup
{

	static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	private ArrayList<String> Arr;
    String confirmbookingcode;
    static String roomtypeid;
    String bookingcode;
    String guestid;
    String rateid;

	String guestdetailsid;
	String paymentid;

static String confirmId;

static String savebkingorderid;
	
	public void Getgroupcall(String s)
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
			Log4j.info("wsauthkey in Getgroup:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			Log4j.info("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			Log4j.info("login key in getbooking:"+keyl);
			accesskey = keyl;
		}
		try
		{
			serverurl = CommonConfig.serverurl;
			
			Getbookings getbookingsobj = new Getbookings();
			getbookingsobj.Getbookingscallforreserveguest("login");
			String groupcode = getbookingsobj.extractinggroupcode();
			Log4j.info("group code:"+groupcode);
			
			HttpResponse<JsonNode> responsegetgroup = Unirest.post(""+serverurl+"/ws/web/getgroup")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "fa348cb1-ea2e-18b8-00d6-5f94048c6adb")
					  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getgroup\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"code\": \""+groupcode+"\"\r\n      }\r\n    }\r\n  }\r\n }")
					  .asJson();
			JsonNode body = responsegetgroup.getBody();
			responseJSONString = body.toString();
			Log4j.info(responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}//End of Getgroupcall() method

public void Getgroupcall(String s1, String s2)
{
		keytype = s1;
		bookingcode = s2;
		Log4j.info("keytype:"+s1);
		
		if(keytype == "wsauth")
		{
		/*Wsauth objwsauth = new Wsauth();
		objwsauth.Wsauthcall();
		String keyw = objwsauth.extractingWsauthKey();
		accesskey = keyw;
		Log4j.info("hello if"+ accesskey);*/
		String keyw = Wsauth.wsauthkeyfinalstring;
		accesskey = keyw;
		Log4j.info("wsauthkey in Getgroup:"+keyw);
		}
		
		else if(keytype == "login")
		{
		/*Login objlogin = new Login();
		objlogin.Logincall();
		String keyl = objlogin.extractingLoginKey();
		Log4j.info("login key in gethousestatus:"+keyl);
		accesskey = keyl;*/
		
		String keyl = Login.finalloginaccesskey;
		Log4j.info("login key in getbooking:"+keyl);
		accesskey = keyl;
		}
		try
		{
		serverurl = CommonConfig.serverurl;
		
		HttpResponse<JsonNode> responsegetgroup = Unirest.post(""+serverurl+"/ws/web/getgroup")
		 .header("content-type", "application/json")
		 .header("x-ig-sg", "D_gg%fkl85_j")
		 .header("cache-control", "no-cache")
		 .header("postman-token", "fa348cb1-ea2e-18b8-00d6-5f94048c6adb")
		 .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getgroup\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"code\": \""+bookingcode+"\"\r\n      }\r\n    }\r\n  }\r\n }")
		 .asJson();
		JsonNode body = responsegetgroup.getBody();
		responseJSONString = body.toString();
		Log4j.info(responseJSONString);
		}
		catch(UnirestException e)
		{
		e.printStackTrace();
		}
}
	/*public void GetgroupcallforFDGroupReservation(String s){
		keytype = s;
		Log4j.info("keytype:"+s);
		
		if(keytype == "wsauth")
		{
			Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			Log4j.info("hello if"+ accesskey);
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			Log4j.info("wsauthkey in Getgroup:"+keyw);
		}
		
		else if(keytype == "login")
		{
			Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			Log4j.info("login key in gethousestatus:"+keyl);
			accesskey = keyl;
			
			String keyl = Login.finalloginaccesskey;
			Log4j.info("login key in getbooking:"+keyl);
			accesskey = keyl;
		}
		try
		{
			serverurl = CommonConfig.serverurl;
			Confirmbooking Confirmbookingobj=new Confirmbooking();
			Confirmbookingobj.confirmbookingforgroupresv("login");
			confirmbookingcode=Confirmbookingobj.extracingbookingCode();
			confirmId=Confirmbookingobj.extracingbookingid();
			Log4j.info("confirmbooking bookingCode is :"+confirmbookingcode);
			Savebooking Savebookingobj=new Savebooking();
			savebkingorderid=Savebookingobj.orderid;
			Log4j.info("save booking orderid is : "+savebkingorderid);
			HttpResponse<JsonNode> responsegetgroup = Unirest.post(""+serverurl+"/ws/web/getgroup")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "fa348cb1-ea2e-18b8-00d6-5f94048c6adb")
					  .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getgroup\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"code\": \""+confirmbookingcode+"\"\r\n      }\r\n    }\r\n  }\r\n }")
					  .asJson();
			JsonNode body = responsegetgroup.getBody();
			responseJSONString = body.toString();
			Log4j.info("getgroup Response is: "+responseJSONString);
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
			
	}*/
	
	public String extractingGOUPPID(){
		Log4j.info("welcome to groupid FROM GET GROUP");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
		JSONObject bookingstempid = getbookingsArray.getJSONObject(0);
		JSONObject grp = bookingstempid.getJSONObject("group");
		String groupid = grp.getString("id");
		Log4j.info(":getGruop id From GetGrp :"+groupid);

		return groupid;
		
		
	}
	public String extractinggetbookingid()
	{
	String localresponseJSONString = responseJSONString;
	JSONObject jsonResult = new JSONObject(localresponseJSONString);
	String getgroupid;
	getgroupid = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
	Log4j.info("at last getbooking success:"+getgroupid);

	JSONObject newresp = jsonResult.getJSONObject("hotelogix");
	System.out.println(newresp);
	JSONObject resp= newresp.getJSONObject("response");
	JSONArray newarray = resp.getJSONArray("bookings");
	JSONObject book1 = newarray.getJSONObject(0);
	//JSONObject book  = book1.getJSONObject("group");
	    String bookingid1 = book1.getString("id");
	Log4j.info("The booking id is:" +bookingid1);
	return bookingid1;

	}
	
	
	public String extractinggetgroupidmain()
	{
	String localresponseJSONString = responseJSONString;
	JSONObject jsonResult = new JSONObject(localresponseJSONString);
	String getgroupid;
	getgroupid = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
	Log4j.info("at last getbooking success:"+getgroupid);
	Log4j.info("extractinggetgroupidmain():::Response string"+responseJSONString);
	JSONObject newresp = jsonResult.getJSONObject("hotelogix");
	System.out.println(newresp);
	JSONObject resp= newresp.getJSONObject("response");
	JSONArray newarray = resp.getJSONArray("bookings");
	JSONObject book1 = newarray.getJSONObject(0);
	JSONObject book  = book1.getJSONObject("group");
	 String groupid = book.getString("mainId");
	Log4j.info("The booking id is:" +groupid);
	JSONObject roomStays=book1.getJSONArray("roomStays").getJSONObject(0);
	roomtypeid=roomStays.getString("roomTypeId");
	Log4j.info("Group id is: "+groupid);
	return groupid;

	}
	public String extractingGuestStayID(){
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getgroupid;
		getgroupid = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		Log4j.info("at last getbooking success:"+getgroupid);

		JSONObject newresp = jsonResult.getJSONObject("hotelogix");
		System.out.println(newresp);
		JSONObject resp= newresp.getJSONObject("response");
		JSONArray newarray = resp.getJSONArray("bookings");
		JSONObject book1 = newarray.getJSONObject(0);
		JSONObject book  = book1.getJSONObject("group");
		 String groupid = book.getString("mainId");
		Log4j.info("The booking id is:" +groupid);
		JSONObject roomStays=book1.getJSONArray("roomStays").getJSONObject(0);
		roomtypeid=roomStays.getString("roomTypeId");
		JSONObject GuestStays=book1.getJSONArray("guestStays").getJSONObject(0);
		guestid=GuestStays.getString("id");
		JSONObject guestDetails = GuestStays.getJSONObject("guestDetails");
		guestdetailsid = guestDetails.getString("id");

		return guestid;
	}
	public String extractingmessagegetgroup()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getbookingstring;
		getbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		Log4j.info("at last getbooking success:"+getbookingstring);
		return getbookingstring;
	}//End of extractingmessagegetgroup() method  

 public String extractingrateidfromgetgroup()
{
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
String getgroupid;
getgroupid = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
Log4j.info("at last getbooking success:"+getgroupid);

JSONObject newresp = jsonResult.getJSONObject("hotelogix");
System.out.println(newresp);
JSONObject resp= newresp.getJSONObject("response");
JSONArray newarray = resp.getJSONArray("bookings");
JSONObject book1 = newarray.getJSONObject(0);
JSONObject book  = book1.getJSONObject("group");
String groupid = book.getString("mainId");
JSONArray paymentarr = book.getJSONArray("payments");
JSONObject paymentob =  paymentarr.getJSONObject(0);
paymentid = paymentob.getString("id");

Log4j.info("The booking id is:" +groupid);
JSONObject roomStays=book1.getJSONArray("roomStays").getJSONObject(0);
rateid=roomStays.getString("rateId");
return rateid;

}
}
