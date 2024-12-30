package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ChangeGroupStay {
 
	static String responseJSONString;
	static String bookingid;
	String keytype;
	String accesskey;
	String serverurl;
	String hotelid1;
	String nightauditdate3;
	String nightauditdate4;
	String groupid;
	String editgroupid;
	
	public void fn_changegroupstay(String s1){
		
		keytype = s1;
		
		System.out.println("Welcome to Change Group Stay API");
		
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
			
			Confirmbooking confirmbookingobj = new Confirmbooking();
			confirmbookingobj.confirmbookingforgroupresv("login","1");
			System.out.println("new");
			//String groupcode = confirmbookingobj.extracingbookingcode();
			String groupid = confirmbookingobj.extracinggroupid();
			
			
			/*Getgroup getgroupobj = new Getgroup();
			getgroupobj.Getgroupcall("login", groupcode);
			bookingid = getgroupobj.extractinggetgroupid();*/
			
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.fn_editbookingcallforexistingbookingid("login", groupid, "G");
			
			editgroupid =editbookingobj.extracttinggroupid();
			         
          

		HttpResponse<JsonNode> responseassignrom = Unirest.post(""+serverurl+"/ws/web/changegroupstay")
					 .header("content-type", "application/json")
					 .header("x-ig-sg", "D_gg%fkl85_j")
					 .header("cache-control", "no-cache")
					 .header("postman-token", "c52633bd-8abb-e05d-ec86-0641bf085b8f")
					 .body("{"
					               	+ "\"hotelogix\": {"
					               	+ "\"version\": \"1.0\","
					               	+ "\"datetime\": \"2012-01-16T10:10:15\","
					               	+ "\"request\": {"
					               	+ "\"method\": \"changegroupstay\","
					               	+ "\"key\": \""+accesskey+"\","
					               	+ "\"data\": {"
					               	+ "\"id\": \""+editgroupid+"\","					        
					               	+ "\"checkInDate\": \""+nightauditdate3+"\","
	               	                + "\"checkOutDate\": \""+nightauditdate4+"\""
					               	+ "}}}}")
					                    .asJson();
					JsonNode body = responseassignrom.getBody();
					responseJSONString = body.toString();
					System.out.println("Response of Change Group Stay");
					System.out.println("Change Group:"+responseJSONString);
		}
    	catch(UnirestException e)
    	{
    	e.printStackTrace();
    	}
		finally{
			System.out.println("new3");
			Commiteditbooking commitbookingobj = new Commiteditbooking();
			commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", editgroupid );
			
		}
}	
		public String extractingmessagechangegroupstay()
		{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String changegroupstay;
		changegroupstay = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getbookings success:"+changegroupstay);
		return changegroupstay;
		}

    }

	

