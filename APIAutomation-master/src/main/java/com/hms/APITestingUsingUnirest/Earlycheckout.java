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

public class Earlycheckout
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
	
	
	public void Earlycheckoutcall(String s1, String s2)
	{
		keytype = s1;
		bookingtype = s2;
		System.out.println("keytype:"+s1);
		System.out.println("bookingtype:"+s2);
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("wsauth key in getallpaytypes"+ keyw);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in earlycheckout:"+keyw);
			
			
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in earlycheckout:"+accesskey);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			
			if(bookingtype == "S")
			{
				Getbookings getbookingsobj = new Getbookings();
				getbookingsobj.Getbookingscall("login", "CHECKIN");
				mainbookingid = getbookingsobj.extractingmainid();
				
				/*Editbooking editbookingobj = new Editbooking();
				editbookingobj.fn_editbookingcallforexistingbookingid("login", mainbookingid,"S");
				mainbookingid = editbookingobj.extractingtempid();*/
			}
			else
			{
				Getbookings getbookingsobj = new Getbookings();
				getbookingsobj.Getbookingscall(keytype);
				mainbookingid = getbookingsobj.extractinggroupmainid();
			}
			
			
			HttpResponse<JsonNode> responseearlycheckout = Unirest.post(""+serverurl+"/ws/web/earlycheckout")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "f700e0f8-bdec-ac26-6107-183471bc06ce")
					  .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"earlycheckout\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"bookingId\": \""+mainbookingid+"\","
		                		+ "\"doCheckout\": true,"
		                		+ "\"earlyCheckOutDesc\": \"Test automated by harish\""
		                		+ "}}}}")
		                     .asJson();
		
			
			JsonNode body = responseearlycheckout.getBody();
			responseJSONString = body.toString();
			System.out.println("Response from earlycheckout API came as:::"+responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
	}// End of Earlycheckoutcall() method
	
	public String extractingmessageearlycheckout()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String earlycheckoutstring;
		earlycheckoutstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last earlycheckout success:"+earlycheckoutstring);
		return earlycheckoutstring;
	}// End of extractingmessageearlycheckout() method
}// End of class
