package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ChangeBookingStay {

	static String responseJSONString;
	static String bookingid;
	String keytype;
	String accesskey;
	String serverurl;
	String hotelid1;
	String nightauditdate3;
	String nightauditdate4;
	String restype;
	String Bookingidtype;
	String groupcode;
	String groupid;
	String commitbookingid;
	
	public void fn_changebooking(String s1, String s2, String s3){
		
		keytype = s1;
		restype = s2;
		Bookingidtype = s3;
		
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
			//nightauditdate3 = CommonConfig.immediatenextdate;
			nightauditdate3 = CommonConfig.immediatenextdate;
			nightauditdate4 = CommonConfig.immediatenexttonextdate;
	    
			if(Bookingidtype == "Singlebookinid"){
            Editbooking editbookingobj = new Editbooking();
            editbookingobj.EditbookingcallforReserveGuest("login" ,"S");
            bookingid = editbookingobj.extractingtempid();
            commitbookingid = bookingid;
			}
			else if(Bookingidtype =="GroupSinglebookinid"){
			
				Getbookings getbookingsobj = new Getbookings();
				getbookingsobj.Getbookingscallforreserveguest("login");
				groupcode = getbookingsobj.extractinggroupcode();
				
				Getgroup getgroupobj = new Getgroup();
			    getgroupobj.Getgroupcall("login", groupcode);
			    groupid = getgroupobj.extractingGOUPPID();
				Editbooking editbookingidobj = new Editbooking();
				editbookingidobj.fn_editbookingcallforexistingbookingid("login", groupid, "G");
			
		        bookingid = editbookingidobj.extractingtempid();
		        commitbookingid = groupid;
			}

		HttpResponse<JsonNode> responseassignrom = Unirest.post(""+serverurl+"/ws/web/changebookingstay")
					 .header("content-type", "application/json")
					 .header("x-ig-sg", "D_gg%fkl85_j")
					 .header("cache-control", "no-cache")
					 .header("postman-token", "c52633bd-8abb-e05d-ec86-0641bf085b8f")
					 .body("{"
					               	+ "\"hotelogix\": {"
					               	+ "\"version\": \"1.0\","
					               	+ "\"datetime\": \"2012-01-16T10:10:15\","
					               	+ "\"request\": {"
					               	+ "\"method\": \"changebookingstay\","
					               	+ "\"key\": \""+accesskey+"\","
					               	+ "\"data\": {"
					               	+ "\"bookings\": ["
					               	+ "{"
					               	+ "\"id\": \""+bookingid+"\","					        
					               	+ "\"checkInDate\": \""+nightauditdate3+"\","
	               	                + "\"checkOutDate\": \""+nightauditdate4+"\""
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
		finally{
			
			Commiteditbooking commitbookingobj = new Commiteditbooking();
			commitbookingobj.CommiteditBookingCallForExistingBookingid("login", restype, commitbookingid );
			
		}
}	
		public String extractingmessagechangebooking()
		{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getchangebooking;
		getchangebooking = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getbookings success:"+getchangebooking);
		return getchangebooking;
		}

    }



	

