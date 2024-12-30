package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Releasebooking {
	String discountamt;
	  String responseJSONString;
		String serverurl;
		String hotelid1;
		String keytype; 
		String accesskey;
	    String message;
	    String Bookingid;
	    String GroupId;
		public void releasebookingcall_SingleResv(String s1) {
			keytype = s1;
			 
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to Releasebooking   block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in Releasebooking  :"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in Releasebooking  :"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
					System.out.println("hello try"+accesskey);
					Savebooking Savebookingobj=new Savebooking();
					Savebookingobj.SavebookingcallforFrontDeskforfutureDate(keytype,"1","false");
					String bookingid=Savebookingobj.extractingbookingrid();
					System.out.println("booking id from save_for_HoldTillBookings : "+bookingid);
					Editbooking editbookingobj = new Editbooking();
					editbookingobj.fn_editbookingcallforexistingbookingid("login", bookingid, "S");
					System.out.println("Booking Edited");
					Bookingid = editbookingobj.extractingtempid();
					HttpResponse<JsonNode> responsereleasebooking = Unirest.post(""+serverurl+"/ws/web/releasebooking")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("content-type", "application/json")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"releasebooking\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"bookings\": ["
				                		+ "{"
				                		+ "\"id\": \""+Bookingid+"\""
				                		+ "}]}}}}")
				                     .asJson();
					JsonNode body = responsereleasebooking.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
			
		}
		public void releasebooking_Grp_SingleResv(String s1) {
			keytype = s1;
			 
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to Releasebooking   block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in Releasebooking  :"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in Releasebooking  :"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
					System.out.println("hello try"+accesskey);
					Savebooking Savebookingobj=new Savebooking();
					Savebookingobj.SavebookingcallforFrontDeskforfutureDate(keytype,"1","true");
					String bookingid=Savebookingobj.extracinggroupid();
					System.out.println("booking id from save_for_HoldTillBookings : "+bookingid);
					Editbooking editbookingobj = new Editbooking();
					editbookingobj.fn_editbookingcallforexistingbookingid("login", bookingid, "G");
					System.out.println("Booking Edited");
					Bookingid = editbookingobj.extractingtempid();
					HttpResponse<JsonNode> responsereleasebooking = Unirest.post(""+serverurl+"/ws/web/releasebooking")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("content-type", "application/json")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"releasebooking\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"bookings\": ["
				                		+ "{"
				                		+ "\"id\": \""+Bookingid+"\""
				                		+ "}]}}}}")
				                     .asJson();
					JsonNode body = responsereleasebooking.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
			
		}
		public String extractingmessagereleasebooking(){
			System.out.println("welcome to extractingmessagereleasebooking");
			String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("Status msg of releasebooking :"+message);
			//return editbookingstring;
			return message;
		}
		

}
