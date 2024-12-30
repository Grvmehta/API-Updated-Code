package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ChangeGuestStay {

	static String responseJSONString;
	
	String keytype;
	String accesskey;
	String serverurl;
	String hotelid1;
	String nightauditdate3;
	String nightauditdate4;	
	String editbookingid;
	String gueststayid;
	
	
	public void fn_changegueststay(String s1){
		
		keytype = s1;
		
		System.out.println("Welcome to Change Guest Stay API");
		
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
			System.out.println("Hello Change Group Stay API");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			nightauditdate3 = CommonConfig.immediatenextdate;
			nightauditdate4 = CommonConfig.immediatenexttonextdate;			
			
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.EditbookingcallforReserveGuest("login" ,"S");
			
			editbookingid =editbookingobj.extractingtempid();
			gueststayid = editbookingobj.extractingguestStayId();
			         
          

		HttpResponse<JsonNode> responseassignrom = Unirest.post(""+serverurl+"/ws/web/changegueststay")
					 .header("content-type", "application/json")
					 .header("x-ig-sg", "D_gg%fkl85_j")
					 .header("cache-control", "no-cache")
					 .header("postman-token", "c52633bd-8abb-e05d-ec86-0641bf085b8f")
					 .body("{"
					               	+ "\"hotelogix\": {"
					               	+ "\"version\": \"1.0\","
					               	+ "\"datetime\": \"2012-01-16T10:10:15\","
					               	+ "\"request\": {"
					               	+ "\"method\": \"changegueststay\","
					               	+ "\"key\": \""+accesskey+"\","
					               	+ "\"data\": {"
					            	+ "\"bookings\": ["
					               	+ "{"
					            	+ "\"id\": \""+editbookingid+"\","
					               	+ "\"guest\": ["	
					            	+ "{"
					               	+ "\"guestStayId\": \""+gueststayid+"\","
					               	+ "\"checkInDate\": \""+nightauditdate3+"\","
	               	                + "\"checkOutDate\": \"2015-08-27\""
					               	+ "}]}]}}}}")
					                    .asJson();
					JsonNode body = responseassignrom.getBody();
					responseJSONString = body.toString();
					System.out.println("Response of Change Guest Stay");
					System.out.println("Change Group:"+responseJSONString);
		}
    	catch(UnirestException e)
    	{
    	e.printStackTrace();
    	}
		finally{
			System.out.println("new3");
			Commiteditbooking commitbookingobj = new Commiteditbooking();
			commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", editbookingid );
			
		}
}	
		public String extractingmessagechangegueststay()
		{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String changegueststay;
		changegueststay = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getbookings success:"+changegueststay);
		return changegueststay;
		}

    }

	

