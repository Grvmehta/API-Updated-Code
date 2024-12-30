package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetConsoleBookings {
	
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	public void getConsoleBookingscall(String s)
	{
		keytype = s;
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in GetConsoleBookings"+keyw);
			
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in GetConsoleBookings"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			
			Getagents getagentsObj=new Getagents();
			getagentsObj.Getagentscall(keytype);
		String agentId=	getagentsObj.extractingAgentId();
			
			HttpResponse<JsonNode> responseGetConsoleBookings  = Unirest.post(""+serverurl+"/ws/web/getconsolebookings")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "c483df3c-fc07-c930-99df-400e6b8eaf32")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2020-11-17T09:27:34\",\"request\":{\"method\":\"getconsolebookings\",\"key\":\""+accesskey+"\",\"data\":{\"hotels\":[{\"id\":\""+hotelid1+"\"}],\"refId\":\""+agentId+"\",\"type\":\"AGENT\",\"languageId\":1},\"languagecode\":\"en\"}}}")
					  .asJson();
			JsonNode body = responseGetConsoleBookings.getBody();
			System.out.println("GetConsoleBookings response::"+body);
			responseJSONString = body.toString(); 
			
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}	
	}// End of Getagentscall method
	
	public String extractingmessageGetConsoleBookings()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getConsoleBookingssuccessstring;
		getConsoleBookingssuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("last getConsoleBookingssuccessstring :::"+getConsoleBookingssuccessstring);
		return getConsoleBookingssuccessstring; 
	}// End of extractingmessagegetagents method
	
	

}
