package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SaveWeeklyRates {

	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String nightauditdate1;
    String roomtypecode;
	String ratetypecode;
	String Validityid;
	
	public void saveWeeklyRate(String s)
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
		      
		      GetWeeklyRates getweeklyratesobj = new GetWeeklyRates();
			  getweeklyratesobj.getweeklyratescall("login");
			  
			  roomtypecode = getweeklyratesobj.extractingroomtypecode();
			  System.out.println("roomtypecode::"+roomtypecode);
			  ratetypecode = getweeklyratesobj.extractingrateID();
			  System.out.println("ratetypecode::"+ratetypecode);
			  Validityid = getweeklyratesobj.extractingvalidityID();
			  System.out.println("Validityid::"+Validityid);
	
		      System.out.println("New");
			
			HttpResponse<JsonNode> responsegetweeklyrates = Unirest.post(""+serverurl+"/ws/web/saveweeklyrates")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebf56e64-e308-b8b7-bd77-65d49111c1e0")
				  
					  .body("{"
				        		+ "\"hotelogix\": {"
				        		+ "\"version\": \"1.0\","
				        		+ "\"datetime\": \""+currentTime+"\","
				        		+ "\"request\": {"
				        		+ "\"method\": \"saveweeklyrates\","		        		
				        		+ "\"key\": \""+accesskey+"\","
				        		+ "\"data\": {"			        		
				        		+ "\"hotels\": ["
				        		+ "{"
				        		+ "\"id\": \""+hotelid1+"\""
				        		+  "}"
				        		+  "],"	
				        		+ "\"rates\": ["
				        		+ "{"  	
				        		+ "\"validityId\": \""+Validityid+"\","
				        		+ "\"roomTypeCode\": \""+roomtypecode+"\","
				        		+ "\"weekdays\":"
				        		+ "["
				        		+ "\"Mon\""
				        		+ "],"
				        		+ "\"occupancy\": ["
				        		+ "{"	
				        		+ "\"type\": \"Adult\","
				        		+ "\"number\": \"1\","
				        		+ "\"amount\": \"666.00\""
				        		+ "}"
      			        		+ "]"	
				        		+ "}],"
				        		+ "\"id\":\""+ratetypecode+"\","
				        		+ "\"modifiedBy\": \"1053\""				        		
				        		+ "}}}}")				        				        		
					  .asJson();			
			
			
			JsonNode body = responsegetweeklyrates.getBody();
     		System.out.println("Save Weekly Rate Response::"+body);			    
			responseJSONString = body.toString();
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		/*finally {
			
			Commiteditbooking commiteditbookingobj = new Commiteditbooking();
			commiteditbookingobj.Commiteditbookingcall("login");
			System.out.println("Weekly Rates Saved Successfully");
		}*/
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


	


