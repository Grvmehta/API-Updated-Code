package com.hms.APITestingUsingUnirest;

import com.hms.APITestingUsingUnirest.Generic.ApiUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ChangeRoom {
	static String responseJSONString;
	String keytype;
	String accesskey;
	String serverurl;
	String hotelid1;
	String bookingid;
	String roomid;
	String currentDate;
	String frmDate1;


	public void fn_changeRoom(String s1) throws Throwable 
	{
		
			keytype = s1;
			
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
 			    
 			    Checknightauditstatus Checknightauditstatusobj = new Checknightauditstatus();
 			    Checknightauditstatusobj.Checknightauditstatuscall("login");
 			    Checknightauditstatusobj.extractingmessagecheckingnightauditstatus();
 			    currentDate =  Checknightauditstatusobj.currentDate;
 			    String toDate1= ApiUtils.GA().GA().fn_setFrmToDate(currentDate, 1);
 			    
 			    
 			    
 			    
 			    
 				SplitBooking splitbookingobj = new SplitBooking();
 				splitbookingobj.fn_splitbooking("login");
										
				Assignroom assignroomobj = new Assignroom();
				assignroomobj.assignroomcall("login");
				String  editbookingid = assignroomobj.editbookigid;
				
				Getbookings obj = new Getbookings();
				obj.Getbookingscallforreserveguest("login");
				//String  editbookingid = obj.extractingmainid();
				
				Editbooking editbookingobj = new Editbooking();
				editbookingobj.fn_editbookingcallforexistingbookingid("login", editbookingid, "S");
				
				
				Getroomstoassign Getroomstoassignobj=new Getroomstoassign();
				Getroomstoassignobj.getroomstoassigncall("login", editbookingid);
				roomid = Getroomstoassignobj.extractingroomid();
				
			     
				
				HttpResponse<JsonNode> responseassignrom = Unirest.post(""+serverurl+"/ws/web/changeroom")
						 .header("content-type", "application/json")
						 .header("x-ig-sg", "D_gg%fkl85_j")
						 .header("cache-control", "no-cache")
						 .header("postman-token", "c52633bd-8abb-e05d-ec86-0641bf085b8f")
						 .body("{"
						               	+ "\"hotelogix\": {"
						               	+ "\"version\": \"1.0\","
						               	+ "\"datetime\": \"2012-01-16T10:10:15\","
						               	+ "\"request\": {"
						               	+ "\"method\": \"changeroom\","
						               	+ "\"key\": \""+accesskey+"\","
						               	+ "\"data\": {"
						               	+ "\"bookings\": ["
						               	+ "{"
						               	+ "\"id\": \""+editbookingid+"\","
						               	+ "\"roomId\":\""+roomid+"\""
						               	+ "}]}}}}")
						                    .asJson();
						JsonNode body = responseassignrom.getBody();
						responseJSONString = body.toString();
						System.out.println("Response of AssignRoom");
						System.out.println("assignroom:"+responseJSONString);
			}
	    	catch(UnirestException e)
	    	{
	    	e.printStackTrace();
	    	}

	}
	
}
