package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ReinstateBooking {
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String editbookigid;
	String message;
	 String Mainid;
	public void ReinstateBookingcall(String s1,String s2) {
		keytype = s1;
		editbookigid=s2;
		
		
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in ReinstateBooking:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in ReinstateBooking:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			
		
			System.out.println("booking id from GetbookingCancelCharge:"+editbookigid);
			HttpResponse<JsonNode> responseReinstateBooking = Unirest.post(""+serverurl+"/ws/web/reinstatebooking")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
				        		+ "\"hotelogix\": {"
				        		+ "\"version\": \"1.0\","
				        		+ "\"datetime\": \"2012-01-16T10:10:15\","
				        		+ "\"request\": {"
				        		+ "\"method\": \"reinstatebooking\","
				        		+ "\"key\": \""+accesskey+"\","
				        		+ "\"data\": {"
				        		+ "\"bookingId\": \""+editbookigid+"\""
				        		+ "}}}}")
				        .asJson();

			JsonNode body = responseReinstateBooking.getBody();
			responseJSONString = body.toString();
			System.out.println("ReinstateBooking Response:  "+responseJSONString);
	 }catch(UnirestException e)
	{
			e.printStackTrace();
		}
		 finally{

		       Commiteditbooking commitbookingobj = new Commiteditbooking();
		       commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", editbookigid );

		}
	}
	public void ReinstateBookingcallinGrpResrvation(String s1,String s2) {
		keytype = s1;
		editbookigid=s2;
		
		
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in ReinstateBooking:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in ReinstateBooking:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			
		   Editbooking editbookingobj = new Editbooking();
		   editbookingobj.fn_editbookingcallforexistingbookingid("login", editbookigid, "G");
		  Mainid= editbookingobj.extractingtempid();
			System.out.println("booking id from GetbookingCancelCharge:"+editbookigid);
			HttpResponse<JsonNode> responseReinstateBooking = Unirest.post(""+serverurl+"/ws/web/reinstatebooking")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
				        		+ "\"hotelogix\": {"
				        		+ "\"version\": \"1.0\","
				        		+ "\"datetime\": \"2012-01-16T10:10:15\","
				        		+ "\"request\": {"
				        		+ "\"method\": \"reinstatebooking\","
				        		+ "\"key\": \""+accesskey+"\","
				        		+ "\"data\": {"
				        		+ "\"bookingId\": \""+Mainid+"\""
				        		+ "}}}}")
				        .asJson();

			JsonNode body = responseReinstateBooking.getBody();
			responseJSONString = body.toString();
			System.out.println("ReinstateBooking Response:  "+responseJSONString);
	 }catch(UnirestException e)
	{
			e.printStackTrace();
		}
		 finally{

		       Commiteditbooking commitbookingobj = new Commiteditbooking();
		       commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", editbookigid );

		}
	}
	
	public String extractingmessageReinstateBooking(){
		System.out.println("welcome to extractingmessageReinstateBooking");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("Status msg of ReinstateBooking :"+message);
		//return editbookingstring;
		return message;
		
	}

}
