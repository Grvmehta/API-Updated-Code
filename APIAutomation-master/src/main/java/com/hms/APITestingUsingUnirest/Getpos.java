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
import com.hms.APITestingUsingUnirest.Generic.Log4j;

public class Getpos
{
	 String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 
	 public void Getposcall(String s1)
	 {
		 keytype = s1;
		 
		 if(keytype == "wsauth")
			 
			{
				Log4j.info("hello if getpos:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				Log4j.info("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				Log4j.info("wsauthkey in getpos:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				Log4j.info("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				Log4j.info("login key in getpos:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 serverurl = CommonConfig.serverurl;
			 hotelid1 = CommonConfig.hotelid1;
			 
			 HttpResponse<JsonNode> responsegetpos = Unirest.post(""+serverurl+"/ws/web/getpos")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "96a89c08-93ac-0f68-8408-555ff5c95d60")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:11:15\",\r\n    \"request\": {\r\n      \"method\": \"getpos\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n          \"hotels\":[\r\n            {\r\n                \"id\" : \""+hotelid1+"\"\r\n            }\r\n          ]\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson(); 
			 
			 JsonNode body = responsegetpos.getBody();
			 responseJSONString = body.toString();
			 Log4j.info(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		 
	 }// End of Getposcall() method

	 public String extractingmessagegetpos()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String getposstring;
			getposstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			Log4j.info("at last bestsuggestedrate:"+getposstring);
			return getposstring;
	 }// End of extractingmessagegetpos() method
	 
	 public String extractingposid()
	 {
		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 String posid ="testposid";
		 JSONObject hotelsjsonobject = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("hotels").getJSONObject(0);
		 //posid = hotelsjsonobject.getJSONObject("0").getJSONObject("pos").getJSONObject("0").get("id").toString();
		 posid = hotelsjsonobject.getJSONArray("pos").getJSONObject(1).get("id").toString();
		 
		 
		 Log4j.info("posid  in getpos:"+posid);
		 return posid;
		 
	 }// End of extractingposid() method 
}// End of class
