package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.hms.APITestingUsingUnirest.Generic.ApiUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RemoveOccupancies {
 
static String responseJSONString;
	
	String keytype;
	String accesskey;
	String serverurl;
	String hotelid1;
	String bookingid;
	String gueststayid;
	String newbookingid;
	
	public void fn_removeoccupancies(String s1){
		
		keytype = s1;
		
		System.out.println("Welcome to Remove Occupancies API");
		
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
			System.out.println("Hello Remove Occupancies API");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;	
			
			
			Addoccupancies addoccupanciesobj = new Addoccupancies();
			addoccupanciesobj.Addoccupanciescall("login","S","RESERVE");	
			Editbooking editbookingobj = new Editbooking();
			System.out.println("The Booking id for remove occupancies is:" +bookingid);
			bookingid = editbookingobj.extractingtempid();
			editbookingobj.fn_editbookingcallforexistingbookingid("login", bookingid, "S");	
			System.out.println("The GuestStay id for remove occupancies is:" +gueststayid);
			gueststayid = editbookingobj.extractingguestStayIdofSecondGuest();
			
			 HttpResponse<JsonNode> responsremoveoccupancies = Unirest.post(""+serverurl+"/ws/web/removeoccupancies")
	                .header("content-type", "application/json")
	                .header("x-ig-sg", "D_gg%fkl85_j")
	                .header("cache-control", "no-cache")
	                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
	                .body("{"
	                		+ "\"hotelogix\": {"
	                		+ "\"version\": \"1.0\","
	                		+ "\"datetime\": \"2012-01-16T10:10:15\","
	                		+ "\"request\": {"
	                		+ "\"method\": \"removeoccupancies\","
	                		+ "\"key\": \""+accesskey+"\","  
	                		+ "\"data\": {"    		
	                		+ "\"bookings\": ["
	                		+ "{"
	                		+ "\"id\": \""+bookingid+"\","
	                	    + "\"guests\": ["
	                		+ "{"
	                		+ "\"guestStayId\": \""+gueststayid+"\""
	                		+ "}"
	                		+ "]"       	          	
	                		+ "}]}}}}")
	                     .asJson();
			    	JsonNode body = responsremoveoccupancies.getBody();  
			    	responseJSONString = body.toString();
			    	System.out.println("Remove Occupancies Response:"+responseJSONString);
				}
		    	catch(UnirestException e)
		    	{
		    	e.printStackTrace();
		    
		}	
		finally{
	Commiteditbooking commiteditbookingobj = new Commiteditbooking();
	commiteditbookingobj.CommiteditBookingCallForExistingBookingid("login","S", bookingid);
}
	}	
	
    public void fn_removeoccupanciesforgroup(String s1){
		
		keytype = s1;
		
		System.out.println("Welcome to Remove Occupancies API");
		
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
			System.out.println("Hello Remove Occupancies API");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;	
					
			
			
			Addoccupancies addoccupanciesobj = new Addoccupancies();
			addoccupanciesobj.Addoccupanciescallforgroup("login");
			bookingid = Addoccupancies.groupid;
			Editbooking editbookingobj = new Editbooking();
			System.out.println("The Booking id for remove occupancies is:" +bookingid);
		    editbookingobj.fn_editbookingcallforexistingbookingid("login", bookingid, "G");	
			System.out.println("The GuestStay id for remove occupancies is:" +gueststayid);
			gueststayid = editbookingobj.extractingguestStayIdofSecondGuest();
			newbookingid = editbookingobj.extractingtempid();
			System.out.println("Hey");
			
			 HttpResponse<JsonNode> responsremoveoccupancies = Unirest.post(""+serverurl+"/ws/web/removeoccupancies")
	                .header("content-type", "application/json")
	                .header("x-ig-sg", "D_gg%fkl85_j")
	                .header("cache-control", "no-cache")
	                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
	                .body("{"
	                		+ "\"hotelogix\": {"
	                		+ "\"version\": \"1.0\","
	                		+ "\"datetime\": \"2012-01-16T10:10:15\","
	                		+ "\"request\": {"
	                		+ "\"method\": \"removeoccupancies\","
	                		+ "\"key\": \""+accesskey+"\","  
	                		+ "\"data\": {"    		
	                		+ "\"bookings\": ["
	                		+ "{"
	                		+ "\"id\": \""+newbookingid+"\","
	                	    + "\"guests\": ["
	                		+ "{"
	                		+ "\"guestStayId\": \""+gueststayid+"\""
	                		+ "}"
	                		+ "]"       	          	
	                		+ "}]}}}}")
	                     .asJson();
			    	JsonNode body = responsremoveoccupancies.getBody();  
			    	responseJSONString = body.toString();
			    	System.out.println("Remove Occupancies Response:"+responseJSONString);
				}
		    	catch(UnirestException e)
		    	{
		    	e.printStackTrace();
		    
		}	
		
		finally {
			Commiteditbooking commiteditbookingobj = new Commiteditbooking();
			commiteditbookingobj.CommiteditBookingCallForExistingBookingid("login","G", bookingid);
			
			
		}
	}			
		
	 public String extractingmessageremoveoccupancies()
		{
		
			String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String removeoccupancies;
			removeoccupancies = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last getbookings success:"+removeoccupancies);
			return removeoccupancies;
			}
		
	    
}
