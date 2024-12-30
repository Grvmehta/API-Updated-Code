package com.hms.APITestingUsingUnirest;

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

public class Addoccupancies
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String hotelid2;
	String keytype; 
	String accesskey;
	String tempbookingid;
	String tempbookingid1;
	String restype;
	String bookingtype;
	static String groupid;

	public void Addoccupanciescall(String s1,String s2,String s3)
	{
		keytype = s1;
		bookingtype=s2;
		restype=s3;
		
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("wsauth key in getallpaytypes"+ keyw);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in addoccupancies:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
	
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in addoccupancies:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.Editbookingcallforsingleid(keytype,bookingtype,restype);
			
			String tempbookingid1 = editbookingobj.extractingtempid();
			tempbookingid = tempbookingid1;
			System.out.println("tempbooking id in addoccupancies:"+tempbookingid);
			
			int adults=editbookingobj.extractingNoOfAdults();
			adults=adults+1;
			System.out.println("No of adults are:"+adults);
			
			HttpResponse<JsonNode> responseaddoccupancies = Unirest.post(""+serverurl+"/ws/web/addoccupancies")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "13d3aa0a-0d55-0ad8-a9d6-e6b7eaf93853")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"addoccupancies\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n        \"bookings\": [\r\n          {\r\n            \"id\": \""+tempbookingid+"\",\r\n            \"noOfadult\":\""+adults+"\",\r\n            \"noOfchild\": 0\r\n          }\r\n        ]\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			
			JsonNode body = responseaddoccupancies.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			Commiteditbooking commiteditbookingobj = new Commiteditbooking();
			commiteditbookingobj.CommiteditBookingCallForExistingBookingid(keytype,bookingtype,tempbookingid);
		}
	}// End of Addoccupanciescall() method
	
	public void Addoccupanciescallforgroup(String s1)
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
			System.out.println("wsauthkey in addoccupancies:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
	
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in addoccupancies:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			
			 Confirmbooking confirmbookingobj = new Confirmbooking();
			 confirmbookingobj.confirmbookingforgroupresv("login","1");
			 groupid = confirmbookingobj.extracinggroupid();
			
			 
			 Editbooking editbookingobj = new Editbooking();
			 editbookingobj.fn_editbookingcallforexistingbookingid("login", groupid, "G");
			 tempbookingid1 = editbookingobj.extractingtempid();
		     tempbookingid = tempbookingid1;
		     //For commitbooking
		     groupid = editbookingobj.extracttinggroupid();
			 
		/*	 editbookingobj.EditbookingcallforReserveGuest("login", "G");
			tempbookingid1 = editbookingobj.extractingtempid();
			tempbookingid = tempbookingid1;
			//gueststayid = editbookingobj.extractingguestStayId();
			System.out.println("tempbooking id in addoccupancies:"+tempbookingid);*/
			
			HttpResponse<JsonNode> responseaddoccupancies = Unirest.post(""+serverurl+"/ws/web/addoccupancies")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "13d3aa0a-0d55-0ad8-a9d6-e6b7eaf93853")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"addoccupancies\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n        \"bookings\": [\r\n          {\r\n            \"id\": \""+tempbookingid+"\",\r\n            \"noOfadult\": 3,\r\n            \"noOfchild\": 0\r\n          }\r\n        ]\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			
			JsonNode body = responseaddoccupancies.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);		
			
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			Commiteditbooking commiteditbookingobj = new Commiteditbooking();
			commiteditbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", groupid);
		}
	}
	public String extractingmessageaddoccupancies()
	{
		String localresponseJSONString = responseJSONString;
		System.out.println("Add occupancies message extract response:::::"+responseJSONString);
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String addoccupanciessuccessstring;
		addoccupanciessuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("last keepalive addoccupancies"+addoccupanciessuccessstring);
		return addoccupanciessuccessstring;
	}// extractingmessageaddoccupancies() method
	
	
	
}// End of class
