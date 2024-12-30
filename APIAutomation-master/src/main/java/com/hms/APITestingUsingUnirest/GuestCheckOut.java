package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GuestCheckOut {
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String editbookigid;
	String message;
	String Gueststayid;
	String restype;
	public void GuestCheckOutCall(String s1) {
		keytype = s1;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in GuestCheckOut:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in GuestCheckOut:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			CancelCheckout CancelCheckoutobj=new CancelCheckout();
			editbookigid=CancelCheckoutobj.editbookigid;
			System.out.println("booking id cfron cancelchkout::"+editbookigid);
			Gueststayid=CancelCheckoutobj.Gueststayid;
			System.out.println("guest id from cancelchkout::"+Gueststayid);
			HttpResponse<JsonNode> responseGuestCheckOut = Unirest.post(""+serverurl+"/ws/web/guestcheckout")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"  
							    + "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2015-09-16T11:01:29\","
		                		+ " \"request\": {"
		                		+ " \"method\": \"guestcheckout\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"bookingId\": \""+editbookigid+"\","
		                		+ "\"guests\": ["
		                		+ "{"
		                		+ "\"guestStayId\": \""+Gueststayid+"\""
		                		+ "}]}}}}")
		                     .asJson();
         
				JsonNode body = responseGuestCheckOut.getBody();
				responseJSONString = body.toString();
				System.out.println("GuestCheckOut Response:  "+responseJSONString);
		 }catch(UnirestException e)
		{
				e.printStackTrace();
			}

     finally{

       Commiteditbooking commitbookingobj = new Commiteditbooking();
       commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", editbookigid );

}
	
	}
		
	public String extractingmessageGuestCheckOut(){
		System.out.println("welcome to extractingmessageGuestCheckOut");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("Status msg of GuestCheckOut :"+message);
		//return editbookingstring;
		return message;
	}
}
