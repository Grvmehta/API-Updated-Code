package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UpdateHotelInfo {
	
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	public static String roomTypeID;
	public static String amenityID;
	
	public void updateHotelInfocall(String s)
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
			System.out.println("hello try UpdateHotelInfo");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
		    System.out.println("Access key is::"+accesskey);
			
			HttpResponse<JsonNode> responseUpdateHotelInfo = Unirest.post(""+serverurl+"/ws/web/updatehotelinfo")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebf56e64-e308-b8b7-bd77-65d49111c1e0")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2020-11-17T08:20:06\",\"request\":{\"method\":\"updatehotelinfo\",\"key\":\""+accesskey+"\",\"data\":{\"hotelInfo\":{\"name\":\"API Automation Hotel\",\"website\":\"www.hotelogix.com\",\"contact\":{\"name\":\"Mail1 Testq\",\"designation\":\"QA\",\"email\":\"gaurav.mehta@hotelogix.com\",\"phone\":\"9788548795\",\"fax\":\"0111234567F\"},\"address\":{\"address\":\"Gervinusstr. 24dd\",\"country\":\"AF\",\"state\":\"BDS\",\"city\":\"Berlin\",\"zip\":\"10629\",\"phone\":\"9711905402\"},\"billingContact\":{\"name\":\"Mail1 Testq\",\"designation\":\"Developer\",\"email\":\"soug_25@yahoo.com\",\"phone\":\"9711905402\",\"fax\":\"0111234567F\"}},\"hotels\":[{\"id\":\""+hotelid1+"\"}]}}}}")
					  .asJson();
			JsonNode body = responseUpdateHotelInfo.getBody();
			System.out.println("UpdateRoomType response::"+body);	
			responseJSONString = body.toString();	
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// end of Createagentcall() method
	
	public String extractingmessageUpdateHotelInfo()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String updateHotelInfoSuccessString;
		updateHotelInfoSuccessString = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		
		System.out.println("last UpdateHotelInfo success"+updateHotelInfoSuccessString);
		return updateHotelInfoSuccessString;
	}
	



}
