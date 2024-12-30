package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CancelCheckin {
	String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
    String message;
    String bookingid;
	String editbookigid;
	public void CancelCheckincall(String s1) {
	
		keytype = s1;
		
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello to CancelCheckin block:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in CancelCheckin:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in CancelCheckin:"+keyl);
				accesskey = keyl;
			}
		 try{
			    serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);
				GuestCheckin guestcheckinobj=new GuestCheckin();
				editbookigid=guestcheckinobj.editbookigid;
				System.out.println("Booking id from guest chk in:"+editbookigid);
				Commiteditbooking Commiteditbookingobj=new Commiteditbooking();
				Commiteditbookingobj.CommiteditbookingcallforFDBooking(keytype);
				
				
				
				//System.out.println("Booking id from getbookings:"+bookingid);
				HttpResponse<JsonNode> responseCancelCheckin = Unirest.post(""+serverurl+"/ws/web/cancelcheckin")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
						  .body("{"
			                		+ "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2012-01-16T10:10:15\","
			                		+ "\"request\": {"
			                		+ "\"method\": \"cancelcheckin\","
			                		+ "\"key\": \""+accesskey+"\","
			                		+ "\"data\": {"
			                		+ "\"bookingId\": \""+editbookigid+"\","
			                		+ "\"isMain\": true"
			                		+ "}}}}")
			                     .asJson();
				JsonNode body = responseCancelCheckin.getBody();
				responseJSONString = body.toString();
				System.out.println(responseJSONString);
		 }catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		 finally{

		       Commiteditbooking commitbookingobj = new Commiteditbooking();
		       commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", editbookigid );

		}
	}
	public String extractingmessageCancelCheckin(){
		System.out.println("welcome to extractingmessageCancelCheckin");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("Status msg of CancelCheckin :"+message);
		//return editbookingstring;
		return message;
		
	}

}
