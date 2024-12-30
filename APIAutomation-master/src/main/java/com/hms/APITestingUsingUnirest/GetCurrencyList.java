package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetCurrencyList {
	String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	
	public void getcurrencylist(String key)
	{
		keytype = key;
		 
		 if(keytype == "wsauth")
			{
				System.out.println("hello if getcurrencylist:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in getcurrencylist:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in getcurrencylist:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 serverurl = CommonConfig.serverurl;
			 
			 System.out.println("hello:: accesskey in getcurrencylist:"+accesskey);
			 
			 HttpResponse<JsonNode> responsegetcountrylist = Unirest.post(""+serverurl+"/ws/web/getcurrencylist")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebd1bb38-292b-54ad-c70f-f226cab49295")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"getcurrencylist\",\"key\":\""+accesskey+"\"}}}")
					  .asJson();
			 
			 JsonNode body = responsegetcountrylist.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		 
	}
	
	 public String extractingmessgaegetcurrencylist()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String getcurrencylistString;
			getcurrencylistString = jsonResult.getJSONObject("hotelogix").getJSONArray("response").getJSONObject(0).getString("currencycode");
					//getJSONObject("currencycode").toString();
			System.out.println("at last getcountrylist:"+getcurrencylistString);
			return getcurrencylistString;
	 }

}
