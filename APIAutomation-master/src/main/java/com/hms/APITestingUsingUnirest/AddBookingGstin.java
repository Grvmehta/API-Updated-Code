package com.hms.APITestingUsingUnirest;

import java.io.InputStream;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class AddBookingGstin {
	 String responseJSONString;
	  String accesskey;
	    String message;
	    String serverurl;
	    String keytype;
	    String hotelid1;
	    String bookingid;
	    String Groupid;
	    String gstinnumber="09AXFPTY6787I98rah1234";
	    public void AddBookingGstincall(String s1){
			keytype = s1;
			 
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to AddBookingGstin block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in AddBookingGstin:"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in AddBookingGstin:"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
					System.out.println("hello try"+accesskey);
					Confirmbooking Confirmbookingobj=new Confirmbooking();
					Confirmbookingobj.confirmbookingforgroupresv("wsauthforTA","1");
					String Grpcode=Confirmbookingobj.extractinggroupcode();
					Getgroup Getgroupobj=new Getgroup();
					Getgroupobj.Getgroupcall("login", Grpcode);
					bookingid=Getgroupobj.extractingGOUPPID();
					Editbooking Editbookingobj=new Editbooking();
					Editbookingobj.fn_editbookingcallforexistingbookingid(s1, bookingid, "G");
					Groupid=Editbookingobj.extracttinggroupid();
													
					System.out.println("Group id from editBookins:"+Groupid);
					HttpResponse<JsonNode> responseAddBookingGstin = Unirest.post(""+serverurl+"/ws/web/addbookinggstin")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"addbookinggstin\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"bookingId\": \""+Groupid+"\","
				                		+ "\"type\": \"G\","
				                		+ "\"customgstin\": {"
				                		+ "\"number\": \""+gstinnumber+"\","
				                		+ "\"name\": \"HMS Infotech123\","
				                		+ "\"address1\": \"E-66\","
				                		+ "\"address2\": \"Sector 1009\","
				                		+ "\"country\": \"India\","
				                		+ "\"state\": \"Uttar Pradesh\","
				                		+ "\"city\": \"Noida\","
				                		+ "\"zip\": \"201301\""
				                		+ "}}}}}")
				                     .asJson();
					JsonNode body = responseAddBookingGstin.getBody();
					System.out.println("addbookinggstin status:::"+responseAddBookingGstin.getStatus());
					System.out.println(responseAddBookingGstin.getStatusText());
				InputStream body1=	responseAddBookingGstin.getRawBody();
			String s5=	body1.toString();
			System.out.println("raw body:::::"+s5);
					responseJSONString = body.toString();
					System.out.println("response body AddBookingGstin:::: "+responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
			 finally{

			       Commiteditbooking commitbookingobj = new Commiteditbooking();
			       commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", Groupid );

			} 
	    }
		public String extractingmessageAddBookingGstin() {
			System.out.println("welcome to extractingmessageAddBookingGstin");
			String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("status msg of AddBookingGstin:"+message);
			return message;
		}
}
