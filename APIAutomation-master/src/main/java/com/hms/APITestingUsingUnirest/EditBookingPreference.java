package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.hms.APITestingUsingUnirest.Generic.ApiUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class EditBookingPreference {

static String responseJSONString;
	
	String keytype;
	String accesskey;
	String serverurl;
	String hotelid1;
	
	public void fn_editbookingpreference(String s1){
		
		keytype = s1;
		
		System.out.println("Welcome to Edit Booking Preference API");
		
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
			System.out.println("Hello Edit Booking Preference API");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;	
			
			Confirmbooking confirmbookingobj = new Confirmbooking();
			confirmbookingobj.ConfirmbookingcallforFrontDesk("login","false");
			
			String bookingid = confirmbookingobj.extracingbookingid();
			
			//For Checkin reservations
			/*Getbookings getbookingsobj = new Getbookings();
			getbookingsobj.Getbookingscall("login", "CHECKIN");
			String bookingid = getbookingsobj.extractingmainid();*/		
			
		
			String preference = ApiUtils.GA().generateRandomString();
			
			
			  HttpResponse<JsonNode> responseeditbookingpreference = Unirest.post(""+serverurl+"/ws/web/editbookingpreference")	
		                .header("content-type", "application/json")
		                .header("x-ig-sg", "D_gg%fkl85_j")
		                .header("cache-control", "no-cache")
		                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
		                .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"editbookingpreference\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"type\": \"S\","		
		                        + "\"isMain\": true,"
                    	        + "\"bookings\": ["
		                        + "{"
		                        + "\"id\": \""+bookingid+"\","
		                        + "\"preference\": \""+preference+"\""
		                             
		                		+ "}]}}}}")
		                     .asJson();
			    	JsonNode body = responseeditbookingpreference.getBody();  	
			    	responseJSONString = body.toString();
			    	System.out.println("Edit Booking Preference:"+responseJSONString);
				}
		    	catch(UnirestException e)
		    	{
		    	e.printStackTrace();
		    
		}	
	}	
	
 public void fn_editbookingpreferenceforgroup(String s1){
		
		keytype = s1;
		
		System.out.println("Welcome to Edit Booking Preference API");
		
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
			System.out.println("Hello Edit Booking Preference API");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;	
			
			Confirmbooking confirmbookingobj = new Confirmbooking();
			confirmbookingobj.confirmbookingforgroupresv("login","1");
			
			String groupid = confirmbookingobj.extracinggroupid();
			
			//For Checkin reservations
			/*Getbookings getbookingsobj = new Getbookings();
			getbookingsobj.Getbookingscall("login", "CHECKIN");
			String bookingid = getbookingsobj.extractingmainid();*/		
			
		
			String preference = ApiUtils.GA().generateRandomString();
			
			
			  HttpResponse<JsonNode> responseeditbookingpreference = Unirest.post(""+serverurl+"/ws/web/editbookingpreference")	
		                .header("content-type", "application/json")
		                .header("x-ig-sg", "D_gg%fkl85_j")
		                .header("cache-control", "no-cache")
		                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
		                .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"editbookingpreference\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"type\": \"G\","		
		                        + "\"isMain\": true,"
                    	        + "\"bookings\": ["
		                        + "{"
		                        + "\"id\": \""+groupid+"\","
		                        + "\"preference\": \""+preference+"\""
		                             
		                		+ "}]}}}}")
		                     .asJson();
			    	JsonNode body = responseeditbookingpreference.getBody();  	
			    	responseJSONString = body.toString();
			    	System.out.println("Edit Booking Preference:"+responseJSONString);
				}
		    	catch(UnirestException e)
		    	{
		    	e.printStackTrace();
		    
		}	
	}			
			
			public String extractingmessageeditbookingpreference()
				{
				String localresponseJSONString = responseJSONString;
				JSONObject jsonResult = new JSONObject(localresponseJSONString);
				String editbookingpreference;
				editbookingpreference = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
				System.out.println("at last getbookings success:"+editbookingpreference);
				return editbookingpreference;
				}

		    

		}


	
