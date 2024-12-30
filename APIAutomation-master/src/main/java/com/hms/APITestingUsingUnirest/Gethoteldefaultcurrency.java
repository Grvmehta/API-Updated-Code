package com.hms.APITestingUsingUnirest;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Gethoteldefaultcurrency {
	
	String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 HttpResponse<JsonNode> responsegetcountrylist ;
	
	public void gethoteldefaultcurrency(String key)
	{
		keytype = key;
		 
		 if(keytype == "wsauth")
			{
				System.out.println("hello if Gethoteldefaultcurrency:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in Gethoteldefaultcurrency:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in Gethoteldefaultcurrency:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 serverurl = CommonConfig.serverurl;
			 
			 System.out.println("hello:: accesskey in Gethoteldefaultcurrency:"+accesskey);
			 
		 responsegetcountrylist = Unirest.post(""+serverurl+"/ws/web/gethoteldefaultcurrency")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebd1bb38-292b-54ad-c70f-f226cab49295")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"gethoteldefaultcurrency\",\"key\":\""+accesskey+"\"}}}")
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
	
	 public int extractingmessgaegetHoteldefaultcurrency()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			
			String gethoteldefaultcurrencyString;
			int res=responsegetcountrylist.getStatus();
					
			//getJSONObject("currencycode").toString();
			System.out.println("at last gethoteldefaultcurrency:"+res);
			return res;
	 }

}
