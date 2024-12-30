package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Removeguest {
 String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String editbookigid;
	String gueststayid;
	String message;
	public void Removeguestcall(String s1)
	{
		keytype = s1;
		 
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello Remove guest block:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in Removeguest:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in Removeguest:"+keyl);
				accesskey = keyl;
			}
		 try
		 {
			 	serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);
				Editbooking editbookingobj=new Editbooking();
				editbookingobj.EditbookingcallforReserveGuest(keytype,"S");
				editbookigid=editbookingobj.extractingtempid();
				gueststayid=editbookingobj.extractingguestStayId();
				HttpResponse<JsonNode> responseRemoveguest = Unirest.post(""+serverurl+"/ws/web/removeguest")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
						  .body("{"
					        		+ "\"hotelogix\": {"
					        		+ "\"version\": \"1.0\","
					        		+ "\"datetime\": \"2012-01-16T10:10:15\","
					        		+ "\"request\": {"
					        		+ "\"method\": \"removeguest\","
					        		+ "\"key\": \""+accesskey+"\","
					        		+ "\"data\": {"
					        		+ "\"bookings\": ["
					        		+ "{"
					        		+ "\"id\": \""+editbookigid+"\","
					        		+ "\"guests\": ["
					        		+ "{"
					        		+ "\"guestStayId\": \""+gueststayid+"\""
					        		+ "}]}]}}}}")
						  .asJson();
				JsonNode body = responseRemoveguest.getBody();
				responseJSONString = body.toString();
				System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		 finally{

		       Commiteditbooking commitbookingobj = new Commiteditbooking();
		       commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", editbookigid );

		} 
		 
		 
	}
	public void RemoveguestinGroupcall(String s1){
		keytype = s1;
		 
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello Remove guest block:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in Removeguest:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in Removeguest:"+keyl);
				accesskey = keyl;
			}
		 try
		 {
			 	serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);
				System.out.println("HLO");
				Editbooking editbookingobj=new Editbooking();
				editbookingobj.Editbookingcall("login","G","RESERVE");
				editbookigid=editbookingobj.extractingtempid();
				gueststayid=editbookingobj.extractingguestStayId();
				HttpResponse<JsonNode> responseRemoveguest = Unirest.post(""+serverurl+"/ws/web/removeguest")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
						  .body("{"
					        		+ "\"hotelogix\": {"
					        		+ "\"version\": \"1.0\","
					        		+ "\"datetime\": \"2012-01-16T10:10:15\","
					        		+ "\"request\": {"
					        		+ "\"method\": \"removeguest\","
					        		+ "\"key\": \""+accesskey+"\","
					        		+ "\"data\": {"
					        		+ "\"bookings\": ["
					        		+ "{"
					        		+ "\"id\": \""+editbookigid+"\","
					        		+ "\"guests\": ["
					        		+ "{"
					        		+ "\"guestStayId\": \""+gueststayid+"\""
					        		+ "}]}]}}}}")
						  .asJson();
				JsonNode body = responseRemoveguest.getBody();
				responseJSONString = body.toString();
				System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		 finally{

		       Commiteditbooking commitbookingobj = new Commiteditbooking();
		       commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", editbookigid );

		} 
		 
		 
	}
	public String extractingmessageRemoveguest(){
		System.out.println("welcome to extractingmessageRemoveguest");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("Booking id from editbooking:"+message);
		//return editbookingstring;
		return message;
		
	}
}
