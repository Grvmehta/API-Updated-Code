package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetPayments {
	
	static String responseJSONString;
	
	String keytype;
	String accesskey;
	String serverurl;
	String hotelid1;
	
	public void fn_getpayments(String s1){
		
		keytype = s1;
		
		System.out.println("Welcome to Get Payments API");
		
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
			System.out.println("Hello Get Payments API");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;	
			
			  HttpResponse<JsonNode> responsegetpayments = Unirest.post(""+serverurl+"/ws/web/getpayments")	
		                .header("content-type", "application/json")
		                .header("x-ig-sg", "D_gg%fkl85_j")
		                .header("cache-control", "no-cache")
		                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
		                .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"getpayments\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"fromDate\": \"2015-08-01\","		
		                        + "\"toDate\": \"2015-09-01\","   
                    	        + "\"hotels\": ["
		                        + "{"
		                        + "\"id\": \""+hotelid1+"\""
		                		+ "}]}}}}")
		                     .asJson();
			    	JsonNode body = responsegetpayments.getBody();  	
			    	responseJSONString = body.toString();
			    	System.out.println("Get Payments:"+responseJSONString);
				}
		    	catch(UnirestException e)
		    	{
		    	e.printStackTrace();
		    
		}	
	}			
			
			public String extractingmessagegetpayments()
				{
				String localresponseJSONString = responseJSONString;
				JSONObject jsonResult = new JSONObject(localresponseJSONString);
				String getpayments;
				getpayments = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
				System.out.println("at last getbookings success:"+getpayments);
				return getpayments;
				}

		    

		}



