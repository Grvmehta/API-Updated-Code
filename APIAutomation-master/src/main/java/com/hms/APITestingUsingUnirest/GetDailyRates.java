package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetDailyRates {
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String nightauditdate1;
	static String roomtypecode1;
	static String getrateid;
	
	public void getdailyratescall(String s)
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
			System.out.println("hello try editagent");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			nightauditdate1 = CommonConfig.currentsystemdate;
			//String uniqueID = UUID.randomUUID().toString();
			//String email = uniqueID+"hotelogix.com";
			//String str1 = uniqueID.substring(0,9);
			//System.out.println("substring"+str1);
			// Get the current date and time
		      LocalDateTime currentTime = LocalDateTime.now();
		      System.out.println("Current DateTime: " + currentTime);
		      int year = Year.now().getValue()+1;
		      System.out.println(year);
		      
			
			HttpResponse<JsonNode> responsedailyrates = Unirest.post(""+serverurl+"/ws/web/getdailyrates")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebf56e64-e308-b8b7-bd77-65d49111c1e0")
				  
					  .body("{"
				        		+ "\"hotelogix\": {"
				        		+ "\"version\": \"1.0\","
				        		+ "\"datetime\": \"2012-01-16T10:10:15\","
				        		+ "\"request\": {"
				        		+ "\"method\": \"getdailyrates\","
				        		+ "\"key\": \""+accesskey+"\","
				        		+ "\"data\": {"			        		
				        		+ "\"hotels\": ["
				        		+ "{"
				        		+ "\"id\": \""+hotelid1+"\""
				        		+  "}"
				        		+  "],"
				        		+ "\"fromDate\": \""+nightauditdate1+"\","
				        		+ "\"toDate\":  \""+nightauditdate1+"\""				        		
				        		+ "}}}}")	
				        				        		
					  .asJson();
			
			JsonNode body = responsedailyrates.getBody();
			System.out.println("Edit agent response::"+body);	
			responseJSONString = body.toString();
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// end of Createagentcall() method
	
	public String extractingmsggetdailyrates()
	{
		String localresponseJSONString = responseJSONString;
		System.out.println("This is new:" +localresponseJSONString);
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		System.out.println("This is jsonresult:" +jsonResult );	
		String editagentsuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("last createagent success:"  +editagentsuccessstring);
		return editagentsuccessstring;
	}
	
	public String extractingroomtypecode()
	{
		String localresponseJSONString = responseJSONString;
		System.out.println("This is new:" +localresponseJSONString);
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		System.out.println("This is jsonresult:" +jsonResult );	
		JSONArray gethotelsarray  = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("hotels");
		
		JSONObject  hotelname =  gethotelsarray.getJSONObject(0);
		JSONArray gethotelsarray1  = hotelname.getJSONArray("roomType");
		JSONObject roomtypecode = gethotelsarray1.getJSONObject(1);
		roomtypecode1 = roomtypecode.getString("code");
		System.out.println(roomtypecode);
		return roomtypecode1;
		
		
	}
	
	public String extractingrateID()
	{
		String localresponseJSONString = responseJSONString;
		System.out.println("This is new:" +localresponseJSONString);
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		System.out.println("This is jsonresult:" +jsonResult );	
		JSONArray gethotelsarray  = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("hotels");
		
		JSONObject  hotelname =  gethotelsarray.getJSONObject(0);
		JSONArray getroomtypearray  = hotelname.getJSONArray("roomType");
		JSONObject roomtypecode = getroomtypearray.getJSONObject(0);
		JSONArray getratearray = roomtypecode.getJSONArray("rates");
		JSONObject rateid = getratearray.getJSONObject(0);
		getrateid = rateid.getString("id");
		System.out.println(getrateid);
		return getrateid;
	}
}

	
