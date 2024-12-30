package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.UUID;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.hms.APITestingUsingUnirest.CommonConfig;
import com.hms.APITestingUsingUnirest.Generic.ApiUtils;


public class Createagent
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	public static String AgentID;
	public static String AgentName;
	
	public void Createagentcall(String s)
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
			System.out.println("hello try createagent");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			//String uniqueID = UUID.randomUUID().toString();
			//String email = uniqueID+"hotelogix.com";
			//String str1 = uniqueID.substring(0,9);
			//System.out.println("substring"+str1);
			// Get the current date and time
		      LocalDateTime currentTime = LocalDateTime.now();
		      System.out.println("Current DateTime: " + currentTime);
		      int year = Year.now().getValue()+1;
		      System.out.println(year);
		      AgentName=ApiUtils.GA().generateRandomString();
			
			HttpResponse<JsonNode> responsecreateagent = Unirest.post(""+serverurl+"/ws/web/createagent")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebf56e64-e308-b8b7-bd77-65d49111c1e0")
					  .body("{"
				        		+ "\"hotelogix\": {"
				        		+ "\"version\": \"1.0\","
				        		+ "\"datetime\": \"2019-08-07T01:10:15\","
				        		+ "\"request\": {"
				        		+ "\"method\": \"createagent\","
				        		+ "\"key\": \""+accesskey+"\","
				        		+ "\"data\": {"	
				        		+ "\"iataCode\": \"A21\","
				        		+ "\"businessName\": \""+AgentName+"\","
				        		+ "\"addresses\": {"
				        		+ "\"office\": {"
				        		+ "\"address\": \"abcd\","
				        		+ "\"country\": \"India\","
				        		+ "\"state\": \"delhi\","
				        		+ "\"city\": \"delhi\","
				        		+ "\"zip\": \"123\""
				        		+ "},"
				        		+ "\"billing\": {"
				        		+ "\"companyName\": \"billingCompany\","
				        		+ "\"address\": \"billingAddress\","
				        		+ "\"country\": \"India\","
				        		+ "\"state\": \"Punjab\","
				        		+ "\"city\": \"delhi\","
				        		+ "\"zip\": \"123456\""
				        		+ " }"
				        		+ "},"
				        		+ "\"contacts\": {"
				        		+ "\"personal\": {"
				        		+ "\"salutation\": \"Mr\","
				        		+ "\"fName\": \""+AgentName+"\","
				        		+ "\"lName\": \"Mishra\","
				        		+ "\"designation\": \"Manager\","
				        		+ "\"phoneNo\": \"Manager\","
				        		+ "\"phoneExtension\": \"101\","
				        		+ "\"faxNo\": \"101\","
				        		+ "\"email\": \""+AgentName+"@stayezee.com\","
				        		+ "\"mobileNo\": \"123\","
				        		+ "\"gender\": \"M\","
				        		+ "\"dob\": \"2019-08-04\","
				        		+ "\"website\": \"www.hotelogix.com\""
				        		+ "},"
				        		+ "\"billing\": {"
				        		+ "\"salutation\": \"Mr\","
				        		+ "\"fName\": \"Mukul\","
				        		+ "\"lName\": \"billing\","
				        		+ "\"designation\": \"Manager\","
				        		+ "\"phoneNo\": \"Manager\","
				        		+ "\"phoneExtension\": \"101\","
				        		+ "\"faxNo\": \"101\","
				        		+ "\"email\": \""+AgentName+"@stayezee.com\","
				        		+ "\"mobileNo\": \"123\","
				        		+ "\"gender\": \"M\","
				        		+ "\"dob\": \"2016-03-01\","
				        		+ "\"website\": \"www.hotelogix.com\""
				        		+ "}"
				        		+ "},"
				        		+ "\"creditCardDetail\": {"
				        		+ "\"nameOnCard\": \"Mohan\","
				        		+ "\"cardNo\": \"4111111111111111\","
				        		+ "\"cardType\": \"Visa\","
				        		+ "\"expiryMonth\": \"05\","
				        		+ "\"expiryYear\": \"2029\","
				        		+ "\"cvc\": \"101\","
				        		+ "\"address\": \"creditCardAddress\","
				        		+ "\"country\": \"India\","
				        		+ "\"state\": \"Delhi\","
				        		+ "\"city\": \"Delhi\","
				        		+ "\"zip\": \"20160301\""
				        		+ "},"
				        		+ "\"hotels\": [{"
				        		+ "\"id\": \""+hotelid1+"\""
				        		+ "}]}}}}")
					  .asJson();
			JsonNode body = responsecreateagent.getBody();
			System.out.println("createagent response::"+body);	
			responseJSONString = body.toString();	
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// end of Createagentcall() method
	
	public String extractingmessagecreateagent()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String createagentsuccessstring;
		createagentsuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		JSONObject newresp = jsonResult.getJSONObject("hotelogix");
		JSONObject resp= newresp.getJSONObject("response");
		JSONObject hotels=resp.getJSONArray("hotels").getJSONObject(0);	
		AgentID=hotels.get("agentId").toString();
		System.out.println("The agent id is:" +AgentID);
		System.out.println("last createagent success"+createagentsuccessstring);
		return createagentsuccessstring;
	}
	
	public String extractingAgentID()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String createagentsuccessstring;
		createagentsuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		JSONObject newresp = jsonResult.getJSONObject("hotelogix");
		JSONObject resp= newresp.getJSONObject("response");
		JSONObject hotels=resp.getJSONArray("hotels").getJSONObject(0);	
		AgentID=hotels.get("agentId").toString();
		System.out.println("The agent id is:" +AgentID);
		System.out.println("last createagent success"+createagentsuccessstring);
		return AgentID;
		
	}
	
}
