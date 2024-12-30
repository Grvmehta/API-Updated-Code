package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class AddBookingsToGroup {

static String responseJSONString;
	
	String keytype;
	String accesskey;
	String serverurl;
	String hotelid1;
	String bookingid;
	String editbookingid;
	String checkindate;
	String checkoutdate;
	
	
	public void fn_addbookingstogroup(String s1){
		
		keytype = s1;
		
		System.out.println("Welcome to Add Bookings to Group API");
		
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
			System.out.println("Hello Add Bookings To Group API");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;	
			checkindate = CommonConfig.currentsystemdate;
			checkoutdate = CommonConfig.immediatenextdate;
			
		
			System.out.println("Welcome CreateBooking");
			Confirmbooking confirmbookingobj = new Confirmbooking();
			confirmbookingobj.confirmbookingforgroupresv("login","1");
			String groupcode = confirmbookingobj.extractinggroupcode();
			
			System.out.println("Welcome GetGroup API");
			Getgroup getgroupobj = new Getgroup();
			getgroupobj.Getgroupcall("login", groupcode);
			
			bookingid =getgroupobj.extractinggetgroupidmain();
			
			//Extracting RoomType Id 
			String roomtypeid = Getgroup.roomtypeid;
			
			System.out.println("Welcome EditBooking API");
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.fn_editbookingcallforexistingbookingid("login",bookingid , "G");
			
			editbookingid = editbookingobj.extracttinggroupid();
			

		/*	System.out.println("Welcome GetRoomTypesToChange API");
=======
			/*System.out.println("Welcome GetRoomTypesToChange API");
>>>>>>> 9ef1ae1e2254e33aa3a0eca87d3388555b3287eb
			Getroomtypestochange getroomtypestochangeobj = new Getroomtypestochange();
			getroomtypestochangeobj.Getroomtypestochangecall("login");
			String roomtypeid = getroomtypestochangeobj.extractingroomtypes();*/
			
			 HttpResponse<JsonNode> responseaddbookingtogroup = Unirest.post(""+serverurl+"/ws/web/addbookingstogroup")
	                .header("content-type", "application/json")
	                .header("x-ig-sg", "D_gg%fkl85_j")
	                .header("cache-control", "no-cache")
	                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
	                .body("{"
	                		+ "\"hotelogix\": {"
	                		+ "\"version\": \"1.0\","
	                		+ "\"datetime\": \"2012-01-16T10:10:15\","
	                		+ "\"request\": {"
	                		+ "\"method\": \"addbookingstogroup\","
	                		+ "\"key\": \""+accesskey+"\","
	                		+ "\"data\": {"
	                		+ "\"id\": \""+editbookingid+"\","
	                		+ "\"bookings\": ["
	                		+ "{"
	                		+ "\"checkInDate\": \""+checkindate+"\","
	                		+ "\"checkOutDate\": \""+checkoutdate+"\","
	                		+ "\"roomTypeId\": \""+roomtypeid+"\","
	                		+ "\"adults\": \"1\","
	                		+ "\"children\": \"0\""
	                		+ "}]}}}}")
	                     .asJson();
			    	JsonNode body = responseaddbookingtogroup.getBody();  	
			    	responseJSONString = body.toString();
			    	System.out.println("Add Bookings to Group:"+responseJSONString);
				}
		    	catch(UnirestException e)
		    	{
		    	e.printStackTrace();
		    
		}	
	}			
			
	 public String extractingmessageaddbookingstogroup()
			{
			try
			{
				String localresponseJSONString = responseJSONString;
				JSONObject jsonResult = new JSONObject(localresponseJSONString);
				String addbookingstogroup;
				addbookingstogroup = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
				System.out.println("at last getbookings success:"+addbookingstogroup);
				return addbookingstogroup;
				}
			
			finally{
		Commiteditbooking commiteditbookingobj = new Commiteditbooking();
		commiteditbookingobj.CommiteditBookingCallForExistingBookingid("login","G", editbookingid);
	}
		    

		
			
			}
}

	

