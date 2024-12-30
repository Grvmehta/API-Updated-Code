package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class AbandonNightAudit {
	
	String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 HttpResponse<JsonNode> responseabandonnightaudit ;
	 String nightauditdate3;
	 String nightauditdate4;
	 
	
	public void abandonnightaudit(String key)
	{
		keytype = key;
		 
		 if(keytype == "wsauth")
			{
				System.out.println("hello if abandonnightaudit:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in abandonnightaudit:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in abandonnightaudit:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 nightauditdate3 = CommonConfig.immediatenextdate;
				nightauditdate4 = CommonConfig.immediatenexttonextdate;
				System.out.println(nightauditdate3);
				System.out.println(nightauditdate4);
			 serverurl = CommonConfig.serverurl;
			 
			 System.out.println("hello:: accesskey in abandonnightaudit:"+accesskey);
			 
		 responseabandonnightaudit = Unirest.post(""+serverurl+"/ws/web/abandonnightaudit")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebd1bb38-292b-54ad-c70f-f226cab49295")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"abandonnightaudit\",\"key\":\""+accesskey+"\"}}}")
					  .asJson();
			 
			 JsonNode body = responseabandonnightaudit.getBody();
		
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		 
	}
	
	 public int extractingmessgaeabandonnightaudit()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			
			String abandonnightauditString;
			int res=responseabandonnightaudit.getStatus();
			
			//getJSONObject("currencycode").toString();
			System.out.println("at last abandonnightaudit:"+res);
			return res;
	 }

}
