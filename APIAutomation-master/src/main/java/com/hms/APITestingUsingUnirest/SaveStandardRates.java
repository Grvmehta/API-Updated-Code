package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SaveStandardRates {

	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String nightauditdate1;
    String roomtypecode;
	String ratetypecode;
	
	public void saveStandardRates(String s)
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
			System.out.println("hello try GetStandardRate");
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
		      
		      GetStandardRates getstandardratesobj = new GetStandardRates();
			  getstandardratesobj.getstandardratescall("wsauth");
			  roomtypecode = getstandardratesobj.extractingroomtypecode();
			  System.out.println("Roomtypecode Fetched" +roomtypecode);
			  ratetypecode = getstandardratesobj.extractingrateID();
			  System.out.println("RateTypecode feteched" +ratetypecode);
	
		      
			
			HttpResponse<JsonNode> responsegetweeklyrates = Unirest.post(""+serverurl+"/ws/web/savestandardrates")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebf56e64-e308-b8b7-bd77-65d49111c1e0")
				  
					  .body("{"
				        		+ "\"hotelogix\": {"
				        		+ "\"version\": \"1.0\","
				        		+ "\"datetime\": \""+currentTime+"\","
				        		+ "\"request\": {"
				        		+ "\"method\": \"savestandardrates\","		        		
				        		+ "\"key\": \""+accesskey+"\","
				        		+ "\"data\": {"			        		
				        		+ "\"hotels\": ["
				        		+ "{"
				        		+ "\"id\": \""+hotelid1+"\""
				        		+  "}"
				        		+  "],"	
				        		+ "\"rates\": ["
				        		+ "{"  		
				        		+ "\"roomTypeCode\": \""+roomtypecode+"\","
				        		+ "\"weekdays\":"
				        		+ "{"
				        		+ "\"High\":"
				        		+ "{"
				        		+ "\"baseprice\": \"5000\","
				        		+ "\"higherprice\": \"300\","
				        		+ "\"extrabedprice\": \"200\""
				        		+ "},"
				        		+ "\"Low\":"
				        		+ "{"
				        		+ "\"baseprice\": \"5000\","
				        		+ "\"higherprice\": \"300\","
				        		+ "\"extrabedprice\": \"200\""
      			        		+ "}"	
				        		+ "}}],"
				        		+ "\"id\":\""+ratetypecode+"\","
				        		+ "\"modifiedBy\": \"1053\""				        		
				        		+ "}}}}")				        				        		
					  .asJson();			
			
			
			JsonNode body = responsegetweeklyrates.getBody();
			System.out.println("Save Standard Rate Response::"+body);	
			responseJSONString = body.toString();
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// end of Createagentcall() method
	
	public String extractingmsgsavestandardrates()
	{
		String localresponseJSONString = responseJSONString;
		System.out.println("This is new:" +localresponseJSONString);
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		System.out.println("This is jsonresult:" +jsonResult );	
		String savestandardratesuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("Save Daily Rate success Message:"  +savestandardratesuccessstring);
		return savestandardratesuccessstring;
	}


	
}
