package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Splitrate {
	String discountamt;
	  String responseJSONString;
		String serverurl;
		String hotelid1;
		String keytype; 
		String accesskey;
	    String message;
	    String Bookingid;
	    String GroupId;
	    String nightauditdate3;
	    String nightauditdate4;
	     String roomTypeId;
		String roomId;
		public void splitrate_SingleResv(String s1) {
			keytype = s1;
			 
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to confirmholdtillbookings   block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in confirmholdtillbookings  :"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in confirmholdtillbookings  :"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
					System.out.println("hello try"+accesskey);
					nightauditdate3 = CommonConfig.currentsystemdate;
					nightauditdate4 = CommonConfig.immediatenextdate;
					Confirmbooking confirmbookingobj = new Confirmbooking();
					confirmbookingobj.confirmbookingforFrontDeskfor3DResv("login","false");
					System.out.println("hlo");
					String newbookingid = confirmbookingobj.extracingbookingid();
					String Bookingcode=confirmbookingobj.extracingbookingCode();
					Getbooking Getbookingobj=new Getbooking();
					Getbookingobj.getbookingcallforExistingbookingcode("login", Bookingcode);
					roomTypeId=Getbookingobj.extractingroomTypeId();
					 String Roomid=Getbooking.roomId;
				
					Editbooking editbookingobj = new Editbooking();
					editbookingobj.fn_editbookingcallforexistingbookingid("login", newbookingid, "S");
					System.out.println("Booking Edited");
					Bookingid = editbookingobj.extractingtempid();
					
					/*HttpResponse<JsonNode> responsesplitrate = Unirest.post(""+serverurl+"/ws/web/splitrate")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"splitrate\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"bookings\": ["
				                		+ "{"
				                		+ "\"id\": \""+Bookingid+"\","
				                		+ "\"roomTypeId\": \""+roomTypeId+"\","
				                		+ "\"roomId\": \""+Roomid+"\","
				                		+ "\"fromDate\": \""+nightauditdate3+"\","
				                		+ "\"toDate\": \""+nightauditdate4+"\""               		
				                		+ "}]}}}}")
				                     .asJson();*/
					
					
					HttpResponse<JsonNode> responsesplitrate = Unirest.post(""+serverurl+"/ws/web/splitrate")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{\n    \"hotelogix\": {\n        \"version\": \"1.0\",\n        \"datetime\": \"2012-01-16T10:10:15\",\n        \"request\": {\n            \"method\": \"splitrate\",\n            \"key\": \""+accesskey+"\",\n            \"data\": {\n                \"bookingId\": \""+Bookingid+"\",\n                \"fromDate\": \""+nightauditdate3+"\",\n                \"toDate\": \""+nightauditdate4+"\"\n            }\n        }\n    }\n}")
				                     .asJson();
					JsonNode body = responsesplitrate.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
			 finally{

				 Commiteditbooking commitbookingobj = new Commiteditbooking();
				 commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", Bookingid );

				 }
			
		}
		public void splitrate_GRP_SingleResv(String s1) {
			keytype = s1;
			 
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to confirmholdtillbookings   block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in confirmholdtillbookings  :"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in confirmholdtillbookings  :"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
					System.out.println("hello try"+accesskey);
					nightauditdate3 = CommonConfig.currentsystemdate;
					nightauditdate4 = CommonConfig.immediatenextdate;
					Confirmbooking confirmbookingobj = new Confirmbooking();
					confirmbookingobj.confirmbookingforFrontDeskfor3DResv("login","true");
					System.out.println("hlo");
					String newbookingid = confirmbookingobj.extracinggroupid();
					//String grpid=confirmbookingobj.extracinggroupid();
					String Bookingcode=confirmbookingobj.extracingbookingCode();
					Getbooking Getbookingobj=new Getbooking();
					Getbookingobj.getbookingcallforExistingbookingcode("login", Bookingcode);
					roomTypeId=Getbookingobj.extractingroomTypeId();
					//Getbookingobj.
				//	Getroomstoassign getRoomsToAssignObj=new Getroomstoassign();
					//getRoomsToAssignObj.getroomstoassigncall("login");
					 String Roomid=Getbookingobj.roomId;
					 System.out.println("Room ID:"+Roomid);
					 Editbooking editbookingobj = new Editbooking();
						editbookingobj.fn_editbookingcallforexistingbookingid("login", newbookingid, "G");
						System.out.println("Booking Edited");
						Bookingid = editbookingobj.extractingtempid();
						HttpResponse<JsonNode> responsesplitrate = Unirest.post(""+serverurl+"/ws/web/splitrate")
								  .header("content-type", "application/json")
								  .header("x-ig-sg", "D_gg%fkl85_j")
								  .header("cache-control", "no-cache")
								  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
								  .body("{"
					                		+ "\"hotelogix\": {"
					                		+ "\"version\": \"1.0\","
					                		+ "\"datetime\": \"2012-01-16T10:10:15\","
					                		+ "\"request\": {"
					                		+ "\"method\": \"splitrate\","
					                		+ "\"key\": \""+accesskey+"\","
					                		+ "\"data\": {"
					                		+ "\"bookings\": ["
					                		+ "{"
					                		+ "\"id\": \""+Bookingid+"\","
					                		+ "\"roomTypeId\": \""+roomTypeId+"\","
					                		+ "\"roomId\": \""+Roomid+"\","
					                		+ "\"fromDate\": \""+nightauditdate3+"\","
					                		+ "\"toDate\": \""+nightauditdate4+"\""               		
					                		+ "}]}}}}")
					                     .asJson();
						JsonNode body = responsesplitrate.getBody();
						responseJSONString = body.toString();
						System.out.println(responseJSONString);
				 }catch(UnirestException e)
				 {
					 e.printStackTrace();
				 }
				 finally{

					 Commiteditbooking commitbookingobj = new Commiteditbooking();
					 commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", Bookingid );

					 }
		}
public String extractingmessagessplitrate() {
			
			System.out.println("welcome to extractingmessagesplitrate");
			String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("status msg of splitrate:"+message);
			return message;
		}


}
