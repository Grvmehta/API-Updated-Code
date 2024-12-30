package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.hms.APITestingUsingUnirest.CommonConfig;

public class Consolidateaccount
{
	static String responseJSONString;
	static String finalloginaccesskey;
	String serverurl;
	String hotelid1;
	String key;
	String emailid;
	String password;
	String keytype;
	String accesskey;
	String bookingtype;
	String mainbookingid;
	String groupcode;
	
	public void Consolidateaccountcall(String s1,String s2)
	{
		keytype = s1;
		bookingtype = s2;
		System.out.println("keytype:"+s1);
		System.out.println("keytype:"+s2);
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("wsauth key in getallpaytypes"+ keyw);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in consolidateaccount:"+keyw);
			
			
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in consolidateaccount:"+accesskey);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			if(bookingtype == "S")
			{
				Getbookings getbookingsobj = new Getbookings();
				getbookingsobj.Getbookingscall(keytype);
				mainbookingid = getbookingsobj.extractingmainid();
			}
			else if(bookingtype == "G")
			{
				Getbookings getbookingsobj = new Getbookings();
				getbookingsobj.Getbookingscall("login","CHECKIN");
				groupcode=getbookingsobj.extractinggroupcode();
				Getgroup Getgroupobj=new Getgroup();
				Getgroupobj.Getgroupcall("login", groupcode);
				mainbookingid=Getgroupobj.extractinggetbookingid();
			}
			
			HttpResponse<JsonNode> responseconsolidateaccount = Unirest.post(""+serverurl+"/ws/web/consolidateaccount")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "1fa321be-5d1b-c662-3e0c-eb1f4691e860")
					  .body("{ \"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2015-09-16T11:01:29\","
		                		+ " \"request\": {"
		                		+ " \"method\": \"consolidateaccount\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"id\": \""+mainbookingid+"\","
		                		+ "\"type\": \"S\""
		                		+ "}}}}")
		                     .asJson();

			
			JsonNode body = responseconsolidateaccount.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
	}// End of Consolidateaccountcall() method
	public void Consolidateaccountcall_Grp_Resv(String s1, String s2) {
		keytype = s1;
		bookingtype = s2;
		System.out.println("keytype:"+s1);
		System.out.println("keytype:"+s2);
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("wsauth key in getallpaytypes"+ keyw);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in consolidateaccount:"+keyw);
			
			
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in consolidateaccount:"+accesskey);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			if(bookingtype == "S")
			{
				Getbookings getbookingsobj = new Getbookings();
				getbookingsobj.Getbookingscall(keytype);
				mainbookingid = getbookingsobj.extractingmainid();
			}
			else if(bookingtype == "G")
			{
				Getbookings getbookingsobj = new Getbookings();
				getbookingsobj.Getbookingscall("login","CHECKIN");
				groupcode=getbookingsobj.extractinggroupcode();
				Getgroup Getgroupobj=new Getgroup();
				Getgroupobj.Getgroupcall("login", groupcode);
				mainbookingid=Getgroupobj.extractingGOUPPID();
			}
			HttpResponse<JsonNode> responseconsolidateaccount = Unirest.post(""+serverurl+"/ws/web/consolidateaccount")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "1fa321be-5d1b-c662-3e0c-eb1f4691e860")
					  .body("{ \"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2015-09-16T11:01:29\","
		                		+ " \"request\": {"
		                		+ " \"method\": \"consolidateaccount\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"id\": \""+mainbookingid+"\","
		                		+ "\"type\": \""+bookingtype+"\""
		                		+ "}}}}")
		                     .asJson();

			
			JsonNode body = responseconsolidateaccount.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public String extractingmessageconsolidteaccount()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String consolidateaccountstring;
		consolidateaccountstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last consolidateaccount success:"+consolidateaccountstring);
		return consolidateaccountstring;
	}// End of extractingmessageconsolidteaccount() method

	
}// End of class
