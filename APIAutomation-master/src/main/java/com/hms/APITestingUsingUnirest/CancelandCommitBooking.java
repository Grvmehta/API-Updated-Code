package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CancelandCommitBooking {
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	 static String editbookigid;
	String message;
	String cancellationcharge;
	String isNoShowType;
	String bookingid;
	public void CancelandCommitBookingcallforcancelcharge(String s1,String s2){
		keytype = s1;
		isNoShowType=s2;
		
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in CancelandCommitBooking:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in CancelandCommitBooking:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			
			Editbooking editbookingobj=new Editbooking();
			String Noofroom="1";
			String isGroupBookingValue="false";
			editbookingobj.EditbookingcallforConfirmBookingidforFutureDate(keytype,Noofroom,isGroupBookingValue);
			editbookigid=editbookingobj.extractingtempid();
			/*GetbookingCancelCharge GetbookingCancelChargeOBJ=new GetbookingCancelCharge();
			editbookigid=GetbookingCancelChargeOBJ.editbookigid;*/
			System.out.println("Booking id from guest chk in within CancelandCommitBooking :"+editbookigid);

			cancellationcharge="50";
			System.out.println("booking id from GetbookingCancelCharge:"+editbookigid);
			HttpResponse<JsonNode> responseCancelandCommitBooking = Unirest.post(""+serverurl+"/ws/web/cancelandcommitbooking")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"cancelandcommitbooking\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"bookings\": ["
		                		+ "{"
		                		+ "\"id\": \""+editbookigid+"\","
		                		+ "\"charge\": \""+cancellationcharge+"\","
		                		+ "\"isNoShow\":\""+isNoShowType+"\","
		                		+ "\"sendMail\": false"
		                		+ "}"
		                		+ "]}}}}")
		                     .asJson();
			JsonNode body = responseCancelandCommitBooking.getBody();
			responseJSONString = body.toString();
			System.out.println("Response of CancelandCommitBooking:");
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}	
	}
	
	public void CancelandCommitBookingcall(String s1,String s2){
		keytype = s1;
		isNoShowType=s2;
		
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in CancelandCommitBooking:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in CancelandCommitBooking:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			
			Getbookings getbookingobj = new Getbookings();
			getbookingobj.Getbookingscallforreserveguest("login");
			bookingid = getbookingobj.extractingmainid();
			
			Editbooking editbookingobj=new Editbooking();
			editbookingobj.fn_editbookingcallforexistingbookingid("login", bookingid, "S");
			
			editbookigid=editbookingobj.extractingtempid();
	
			System.out.println("Booking id from guest chk in within CancelandCommitBooking :"+editbookigid);

			cancellationcharge="50";
			System.out.println("booking id from GetbookingCancelCharge:"+editbookigid);
			HttpResponse<JsonNode> responseCancelandCommitBooking = Unirest.post(""+serverurl+"/ws/web/cancelandcommitbooking")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"cancelandcommitbooking\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"bookings\": ["
		                		+ "{"
		                		+ "\"id\": \""+editbookigid+"\","
		                		+ "\"charge\": \""+cancellationcharge+"\","
		                		+ "\"isNoShow\":\""+isNoShowType+"\","
		                		+ "\"sendMail\": false"
		                		+ "}"
		                		+ "]}}}}")
		                     .asJson();
			JsonNode body = responseCancelandCommitBooking.getBody();
			responseJSONString = body.toString();
			System.out.println("Response of CancelandCommitBooking:");
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}	
	}
	
	public String extractingmessageCancelandCommitBooking(){
		System.out.println("welcome to extractingmessageCancelandCommitBooking");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("Status msg of CancelandCommitBooking :"+message);
		//return editbookingstring;
		return message;
		
	}
					  
}
