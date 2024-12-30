package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetHotelSettings {
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String message;
	public void GetHotelSettingscall(String s1) {
	
		keytype = s1;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in GetHotelSettings:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in GetHotelSettings:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			HttpResponse<JsonNode> responseGetHotelSettings = Unirest.post(""+serverurl+"/ws/web/gethotelsettings")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
				        		+ "\"hotelogix\": {"
				        		+ "\"version\": \"1.0\","
				        		+ "\"datetime\": \"2012-01-16T10:10:15\","
				        		+ "\"request\": {"
				        		+ "\"method\": \"gethotelsettings\","
				        		+ "\"key\": \""+accesskey+"\""
				        		+ "}}}")
					  .asJson();

			JsonNode body = responseGetHotelSettings.getBody();
			responseJSONString = body.toString();
			System.out.println("GetHotelSettings Response:  "+responseJSONString);
	 }catch(UnirestException e)
	{
			e.printStackTrace();
		}
	}
	public String extractingmessageGetHotelSettings(){
		System.out.println("welcome to extractingmessageGetHotelSettings");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("Status msg of GetHotelSettings :"+message);
		//return editbookingstring;
		return message;
	}

}
