package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UpdateCurrencies {
	
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	
	public void updateCurrenciescall(String s)
	{
		keytype = s;
		
		if(keytype == "wsauth")
		{
			Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
		}
		
		else if(keytype == "login")
		{
			Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			accesskey = keyl;
		}
		try
		{
			System.out.println("hello try UpdateCurrencies");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
		    System.out.println("Access key is::"+accesskey);
			
			HttpResponse<JsonNode> responseUpdateCurrencies = Unirest.post(""+serverurl+"/ws/web/updatecurrencies")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebf56e64-e308-b8b7-bd77-65d49111c1e0")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2020-11-17T09:10:40\",\"request\":{\"method\":\"updatecurrencies\",\"key\":\""+accesskey+"\",\"data\":{\"currencies\":[[\"BRL\",\"PEN\",\"DKK\",\"CDF\",\"USD\",\"INR\",\"AFN\"]],\"hotels\":[{\"id\":\""+hotelid1+"\"}],\"defaultSetting\":null}}}}")
					  .asJson();
			JsonNode body = responseUpdateCurrencies.getBody();
			System.out.println("UpdateDefaultSetting response::"+body);	
			responseJSONString = body.toString();	
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// end of Createagentcall() method
	
	public String extractingmessageUpdateCurrencies()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String updateCurrenciesSuccessString;
		updateCurrenciesSuccessString = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		
		System.out.println("last UpdateHotelInfo success"+updateCurrenciesSuccessString);
		return updateCurrenciesSuccessString;
	}
	





}
