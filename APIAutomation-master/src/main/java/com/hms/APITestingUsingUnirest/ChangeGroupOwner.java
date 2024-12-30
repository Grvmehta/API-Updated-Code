package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.hms.APITestingUsingUnirest.Generic.ApiUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ChangeGroupOwner {
	
static String responseJSONString;
	
	String keytype;
	String accesskey;
	String serverurl;
	String hotelid1;
	String bookingid;
	String editbookingid;	
	String Ownerid;
	String groupcode;
	String groupid;
	String OwnerType;
	String fname;
	
	
	public void fn_changegroupowner(String s1){
		
		keytype = s1;
		
		System.out.println("Welcome to Change group Owner API");
		
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
			System.out.println("Hello Change group Owner API");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;	
			
			System.out.println("Welcome EditBooking API");
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.Editbookingcall("login", "G", "RESERVE");
		    editbookingid = editbookingobj.extracttinggroupid();
		    Ownerid = editbookingobj.extracttingowneridfromgroup();	 
		    System.out.println("Owner id for encryption:"+Ownerid);
			String fname = ApiUtils.GA().generateRandomString();
			System.out.println("Value of fname:"+fname);
			
			 HttpResponse<JsonNode> responseaddbookingtogroup = Unirest.post(""+serverurl+"/ws/web/changegroupowner")
	                .header("content-type", "application/json")
	                .header("x-ig-sg", "D_gg%fkl85_j")
	                .header("cache-control", "no-cache")
	                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
	                .body("{"
	                		+ "\"hotelogix\": {"
	                		+ "\"version\": \"1.0\","
	                		+ "\"datetime\": \"2012-01-16T10:10:15\","
	                		+ "\"request\": {"
	                		+ "\"method\": \"changegroupowner\","
	                		+ "\"key\": \""+accesskey+"\","
	                		+ "\"languagecode\": \"en\","
	                		+ "\"data\": {"
	                		+ "\"id\": \""+editbookingid+"\","
	                		+ "\"ownerType\": \"guest\","
	                		+ "\"owner\": {"
	                		+ "\"id\": \""+Ownerid+"\","
	                		+ "\"contacts\": {"
	                		+ "\"personal\": {"
	                		+ "\"salutation\": \"Dr\","
	                		+ "\"fName\": \""+fname+"\","
	                		+ "\"lName\": \"NewLastName\","
	                		+ "\"gender\": \"F\","
	                		+ "\"designation\": \"Manager\","
	                		+ "\"phoneNo\": \"94372934\","
	                		+ "\"faxNo\": \"0202\","
	                 		+ "\"email\": \""+fname+"+new.com\","
	                		+ "\"mobileNo\": \"08340348034\""             	
	                		+ "},"
	                		+ "\"addresses\": ["
	                		+ "{"
	                		+ "\"type\": \"home\"," 
	                		+ "\"address\": \"patn1331a44\"," 
	                		+ "\"country\": \"IN\","
	                		+ "\"state\": \"AP\","
	                		+ "\"city\": \"mycity134431\","
	                		+ "\"zip\": \"33333133441\","
	                		+ "\"fax\": \"55667777711\""
	                		+ "},"
	                		+ "{"
	                		+ "\"type\": \"billing\"," 
	                		+ "\"address\": \"patn1331a44\"," 
	                		+ "\"country\": \"IN\"," 
	                		+ "\"state\": \"CH\"" 
	                		+ "}"
	                		+ "],"
	                		+ "\"otherDetails\": {"
	                		+ "\"spouseSalutation\": \"Dr.\"," 
	                		+ "\"spouseLName\": \"Ranii918\""          	          	
	                		+ "}}}}}}}")
	                     .asJson();
			    	JsonNode body = responseaddbookingtogroup.getBody();  		    	
			    	responseJSONString = body.toString();
			    	System.out.println("Add Bookings to Group:"+responseJSONString);
				}
		    	catch(UnirestException e)
		    	{
		    	e.printStackTrace();
		    
		}	
		
		finally{
			Commiteditbooking commiteditbookingobj = new Commiteditbooking();
			commiteditbookingobj.CommiteditBookingCallForExistingBookingid("login","G", editbookingid);
		}
	}
	
    public void fn_changegroupownerforTACorp(String s1, String s2){
		
		keytype = s1;
		OwnerType = s2;
		
		System.out.println("Welcome to Change group Owner API for Travel Agent");
		
		if(keytype == "wsauthforTA")
		{
			Wsauth objwsauth = new Wsauth();
			objwsauth.WsauthcallforTA();
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
			System.out.println("Hello Change group Owner API");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;	
			
			if(OwnerType == "agent")
			{
			Confirmbooking confirmbookingobj = new Confirmbooking();
			confirmbookingobj.confirmbookingforgroupresv("wsauthforTA", "1");
			System.out.println("Created");
			
			groupcode = confirmbookingobj.extractinggroupcode();
			
			Getgroup getgroupobj = new Getgroup();
			getgroupobj.Getgroupcall("login", groupcode);
			groupid = getgroupobj.extractinggetgroupidmain();
			
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.fn_editbookingcallforexistingbookingid("login", groupid, "G");
		    editbookingid = editbookingobj.extracttinggroupid();
		    Ownerid = editbookingobj.extracttingowneridfromgroup();   
			fname = ApiUtils.GA().generateRandomString();
			
			}
			
			
			else if (OwnerType == "corporate")
				{
					Confirmbooking confirmbookingobj = new Confirmbooking();
					confirmbookingobj.confirmbookingforgroupresv("wsauthforCorp", "1");
					System.out.println("Created");
					
					groupcode = confirmbookingobj.extractinggroupcode();
					
					Getgroup getgroupobj = new Getgroup();
					getgroupobj.Getgroupcall("login", groupcode);
					groupid = getgroupobj.extractinggetgroupidmain();
					
					Editbooking editbookingobj = new Editbooking();
					editbookingobj.fn_editbookingcallforexistingbookingid("login", groupid, "G");
				    editbookingid = editbookingobj.extracttinggroupid();
				    Ownerid = editbookingobj.extracttingowneridfromgroup();   
					fname = ApiUtils.GA().generateRandomString();
					
					}
				
				HttpResponse<JsonNode> responseaddbookingtogroup = Unirest.post(""+serverurl+"/ws/web/changegroupowner")
	                .header("content-type", "application/json")
	                .header("x-ig-sg", "D_gg%fkl85_j")
	                .header("cache-control", "no-cache")
	                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
	                .body("{"
	                		+ "\"hotelogix\": {"
	                		+ "\"version\": \"1.0\","
	                		+ "\"datetime\": \"2012-01-16T10:10:15\","
	                		+ "\"request\": {"
	                		+ "\"method\": \"changegroupowner\","
	                		+ "\"key\": \""+accesskey+"\","
	                		+ "\"languagecode\": \"en\","
	                		+ "\"data\": {"
	                		+ "\"id\": \""+editbookingid+"\","
	                		+ "\"ownerType\": \""+OwnerType+"\","
	                		+ "\"owner\": {"
	                		+ "\"id\": \""+Ownerid+"\","
	                		+ "\"contacts\": {"
	                		+ "\"personal\": {"
	                		+ "\"salutation\": \"Dr\","
	                		+ "\"fName\": \""+fname+"\","
	                		+ "\"lName\": \"NewLastName\","
	                		+ "\"gender\": \"F\","
	                		+ "\"designation\": \"Manager\","
	                		+ "\"phoneNo\": \"94372934\","
	                		+ "\"faxNo\": \"0202\","
	                 		+ "\"email\": \""+fname+"+new.com\","
	                		+ "\"mobileNo\": \"08340348034\""             	
	                		+ "},"
	                		+ "\"addresses\": ["
	                		+ "{"
	                		+ "\"type\": \"home\"," 
	                		+ "\"address\": \"patn1331a44\"," 
	                		+ "\"country\": \"IN\","
	                		+ "\"state\": \"AP\","
	                		+ "\"city\": \"mycity134431\","
	                		+ "\"zip\": \"33333133441\","
	                		+ "\"fax\": \"55667777711\""
	                		+ "},"
	                		+ "{"
	                		+ "\"type\": \"billing\"," 
	                		+ "\"address\": \"patn1331a44\"," 
	                		+ "\"country\": \"IN\"," 
	                		+ "\"state\": \"CH\"" 
	                		+ "}"
	                		+ "],"
	                		+ "\"otherDetails\": {"
	                		+ "\"spouseSalutation\": \"Dr.\"," 
	                		+ "\"spouseLName\": \"Ranii918\""          	          	
	                		+ "}}}}}}}")
	                     .asJson();
			    	JsonNode body = responseaddbookingtogroup.getBody();  		    	
			    	responseJSONString = body.toString();
			    	System.out.println("Add Bookings to Group:"+responseJSONString);
				
    
		}
		    	catch(UnirestException e)
		    	{
		    	e.printStackTrace();
		    
		}	
		
		finally{
		Commiteditbooking commiteditbookingobj = new Commiteditbooking();
		commiteditbookingobj.CommiteditBookingCallForExistingBookingid("login","G", editbookingid);
	}
		
	}
	
	
			
	 public String extractingmessagechangegroupowner()
			
			{
				String localresponseJSONString = responseJSONString;
				JSONObject jsonResult = new JSONObject(localresponseJSONString);
				String changegroupowner;
				changegroupowner = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
				System.out.println("at last getbookings success:"+changegroupowner);
				return changegroupowner;
				
			
	    

		
			
			}
}

	


