package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetMinimumBookingRule {
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String message;
	public void GetMinimumBookingRulecall(String s1) {
		keytype = s1;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in GetMinimumBookingRule:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in GetMinimumBookingRule:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			HttpResponse<JsonNode> responseGetMinimumBookingRule = Unirest.post(""+serverurl+"/ws/web/getminimumbookingrule")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
				        		+ "\"hotelogix\": {"
				        		+ "\"version\": \"1.0\","
				        		+ "\"datetime\": \"2012-01-16T10:10:15\","
				        		+ "\"request\": {"
				        		+ "\"method\": \"getminimumbookingrule\","
				        		+ "\"key\": \""+accesskey+"\""
				        		+ "}}}")
					  .asJson();

			JsonNode body = responseGetMinimumBookingRule.getBody();
			responseJSONString = body.toString();
			System.out.println("GetMinimumBookingRule Response:  "+responseJSONString);
	 }catch(UnirestException e)
	{
			e.printStackTrace();
		}
		
	}
	public String extractingmessageGetMinimumBookingRule(){
		System.out.println("welcome to extractingmessageGetMinimumBookingRule");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("Status msg of GetMinimumBookingRule :"+message);
		//return editbookingstring;
		return message;
	}

}
