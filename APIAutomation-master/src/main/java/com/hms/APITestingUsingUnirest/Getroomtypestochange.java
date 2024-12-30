package com.hms.APITestingUsingUnirest;

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

public class Getroomtypestochange
{
	static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	ArrayList<String> arr=new ArrayList<String>();

	public void Getroomtypestochangecall(String s)
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
			System.out.println("wsauthkey in getroomtypestochange:"+keyw);

		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getroomtypestochange:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			HttpResponse<JsonNode> responsegetroomtypestochange = Unirest.post(""+serverurl+"/ws/web/getroomtypestochange")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "dd3ff36c-b131-c5cd-c07d-e4885e2de155")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getroomtypestochange\",\r\n      \"key\": \""+accesskey+"\"\r\n    }\r\n  }\r\n}")
					  .asJson();
			JsonNode body = responsegetroomtypestochange.getBody();
			responseJSONString = body.toString();
			System.out.println("getroomtypestochange response"+responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}//End of Getroomtypestochangecall() method
	
	public String extractingmessagegetroomtypestochange()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getroomtypestochangestring;
		getroomtypestochangestring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getroomtypestochange success :"+getroomtypestochangestring);
		return getroomtypestochangestring;
	}// extractingmessagegetroomtypestochange() method
	
	public ArrayList<String> extractingroomtypes()
	{

	System.out.println("welcome to extractingroomtypes");
	String localresponseJSONString = responseJSONString;
	    JSONObject jsonResult = new JSONObject(localresponseJSONString);
	JSONObject hotelogix= jsonResult.getJSONObject("hotelogix");
	JSONObject response=hotelogix.getJSONObject("response");
	JSONArray availableRoomTypes=response.getJSONArray("availableRoomTypes");
	for(int i=0;i<=availableRoomTypes.length()-1;i++){
	  JSONObject roomtypes=availableRoomTypes.getJSONObject(i);
	  arr.add(roomtypes.getString("id"));
	}

	return arr;

	}
	
}//End of the class
