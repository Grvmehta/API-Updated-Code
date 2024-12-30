package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UpdateDefaultSetting {
	
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	
	public void updateDefaultSettingcall(String s)
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
			System.out.println("hello try UpdateDefaultSetting");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
		    System.out.println("Access key is::"+accesskey);
			
			HttpResponse<JsonNode> responseUpdateDefaultSetting = Unirest.post(""+serverurl+"/ws/web/updatedefaultsetting")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebf56e64-e308-b8b7-bd77-65d49111c1e0")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2020-11-17T08:20:06\",\"request\":{\"method\":\"updatedefaultsetting\",\"key\":\""+accesskey+"\",\"data\":{\"defaultSetting\":{\"currency\":\"USD\",\"language\":\"en\"},\"hotels\":[{\"id\":\""+hotelid1+"\"}]}}}}")
					  .asJson();
			JsonNode body = responseUpdateDefaultSetting.getBody();
			System.out.println("UpdateDefaultSetting response::"+body);	
			responseJSONString = body.toString();	
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// end of Createagentcall() method
	
	public String extractingmessageUpdateDefaultSetting()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String updateDefaultSettingSuccessString;
		updateDefaultSettingSuccessString = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		
		System.out.println("last UpdateHotelInfo success"+updateDefaultSettingSuccessString);
		return updateDefaultSettingSuccessString;
	}
	




}
