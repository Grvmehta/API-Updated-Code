package com.hms.APITestingUsingUnirest;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ConfirmHoldtillBookings {
	String discountamt;
	  String responseJSONString;
		String serverurl;
		String hotelid1;
		String keytype; 
		String accesskey;
	    String message;
	    String Bookingid;
	    String GroupId;
	    public void confirmholdtillbookingscall_singleRsv_editbooking(String s1){
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
					Savebooking Savebookingobj=new Savebooking();
					Savebookingobj.SavebookingcallforFrontDeskforfutureDate(keytype,"1","false");
					String bookingid=Savebookingobj.extractingbookingrid();
					String msg=Savebookingobj.extractingmessagesavebooking();
					Editbooking Editbookingobj=new Editbooking();
					Editbookingobj.fn_editbookingcallforexistingbookingid(keytype,bookingid,"S");
					Bookingid=Editbookingobj.extractingtempid();
					HttpResponse<JsonNode> responseconfirmholdtillbookings = Unirest.post(""+serverurl+"/ws/web/confirmholdtillbookings")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"confirmholdtillbookings\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"isMain\": false,"
				                		+ "\"type\":  \"S\","
				                		+ "\"bookings\": ["
				                		+ "{"
				                		+ "\"id\": \""+Bookingid+"\""
				                		+ "}]}}}}")
				                     .asJson();
					JsonNode body = responseconfirmholdtillbookings.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
	    }
	    public void confirmholdtillbookingscall_singleRsv_getbooking(String s1){
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
					Savebooking Savebookingobj=new Savebooking();
					Savebookingobj.SavebookingcallforFrontDeskforfutureDate(keytype,"1","false");
					String bookingcode=Savebookingobj.extractingbookingCode();
					Getbooking Getbookingobj=new Getbooking();
					Getbookingobj.getbookingcallforExistingbookingcode(keytype, bookingcode);
					Bookingid=Getbookingobj.extractingbookingid();
					HttpResponse<JsonNode> responseconfirmholdtillbookings = Unirest.post(""+serverurl+"/ws/web/confirmholdtillbookings")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"confirmholdtillbookings\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"isMain\": true,"
				                		+ "\"type\":  \"S\","
				                		+ "\"bookings\": ["
				                		+ "{"
				                		+ "\"id\": \""+Bookingid+"|\""
				                		+ "}]}}}}")
				                     .asJson();
					JsonNode body = responseconfirmholdtillbookings.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
	    }
	    public void confirmholdtillbookingscall_GrpResv_Getgroup(String s1){
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
					Savebooking Savebookingobj=new Savebooking();
					Savebookingobj.SavebookingcallforFrontDeskforfutureDate(keytype,"1","true");
					String bookingcode=Savebookingobj.extractingGroupCode();
					Getgroup Getgroupobj=new Getgroup();
					Getgroupobj.Getgroupcall(keytype, bookingcode);
					Bookingid=Getgroupobj.extractingGOUPPID();
					HttpResponse<JsonNode> responseconfirmholdtillbookings = Unirest.post(""+serverurl+"/ws/web/confirmholdtillbookings")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"confirmholdtillbookings\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"isMain\": true,"
				                		+ "\"type\":  \"G\","
				                		+ "\"bookings\": ["
				                		+ "{"
				                		+ "\"id\": \""+Bookingid+"|\""
				                		+ "}]}}}}")
				                     .asJson();
					JsonNode body = responseconfirmholdtillbookings.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
	    
	    }
	    
	    public void confirmholdtillbookingscall_GrpResv_EditBooking(String s1){
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
					Savebooking Savebookingobj=new Savebooking();
					Savebookingobj.SavebookingcallforFrontDeskforfutureDate(keytype,"1","true");
					String bookingid=Savebookingobj.extracinggroupid();
					Editbooking Editbookingobj=new Editbooking();
					Editbookingobj.fn_editbookingcallforexistingbookingid(keytype,bookingid,"G");
					Bookingid=Editbookingobj.extracttinggroupid();
					HttpResponse<JsonNode> responseconfirmholdtillbookings = Unirest.post(""+serverurl+"/ws/web/confirmholdtillbookings")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"confirmholdtillbookings\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"isMain\": false,"
				                		+ "\"type\":  \"G\","
				                		+ "\"bookings\": ["
				                		+ "{"
				                		+ "\"id\": \""+Bookingid+"|\""
				                		+ "}]}}}}")
				                     .asJson();
					JsonNode body = responseconfirmholdtillbookings.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
	    }
	    public void confirmholdtillbookingscall_GrpSingleResv_Getgroup(String s1) {
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
					Savebooking Savebookingobj=new Savebooking();
					Savebookingobj.SavebookingcallforFrontDeskforfutureDate(keytype,"1","true");
					String msg=Savebookingobj.extractingmessagesavebooking();
					System.out.println(msg);
					String bookingcode=Savebookingobj.extractingGroupCode();
					Getgroup Getgroupobj=new Getgroup();
					Getgroupobj.Getgroupcall(keytype, bookingcode);
					Bookingid=Getgroupobj.extractinggetbookingid();
					HttpResponse<JsonNode> responseconfirmholdtillbookings = Unirest.post(""+serverurl+"/ws/web/confirmholdtillbookings")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"confirmholdtillbookings\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"isMain\": true,"
				                		+ "\"type\":  \"S\","
				                		+ "\"bookings\": ["
				                		+ "{"
				                		+ "\"id\": \""+Bookingid+"|\""
				                		+ "}]}}}}")
				                     .asJson();
					JsonNode body = responseconfirmholdtillbookings.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
	    }
	    public void confirmholdtillbookings_GrpSingleResv_Editbooking(String s1) {
	    	
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
					Savebooking Savebookingobj=new Savebooking();
					Savebookingobj.SavebookingcallforFrontDeskforfutureDate(keytype,"1","true");
					String msg=Savebookingobj.extractingmessagesavebooking();
					System.out.println(msg);
					String bookingid=Savebookingobj.extracinggroupid();
					Editbooking Editbookingobj=new Editbooking();
					Editbookingobj.fn_editbookingcallforexistingbookingid(keytype,bookingid,"G");
					Bookingid=Editbookingobj.extractingtempid();
					HttpResponse<JsonNode> responseconfirmholdtillbookings = Unirest.post(""+serverurl+"/ws/web/confirmholdtillbookings")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"confirmholdtillbookings\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"isMain\": false,"
				                		+ "\"type\":  \"S\","
				                		+ "\"bookings\": ["
				                		+ "{"
				                		+ "\"id\": \""+Bookingid+"|\""
				                		+ "}]}}}}")
				                     .asJson();
					JsonNode body = responseconfirmholdtillbookings.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
	    }
					
public String extractingmessageconfirmholdtillbookings() {
			
			System.out.println("welcome to extractingmessageconfirmholdtillbookings");
			String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("status msg of confirmholdtillbookings:"+message);
			return message;
		}
public String extracingBookingid(){
	
	String localresponseJSONString = responseJSONString;
	 JSONObject jsonResult = new JSONObject(localresponseJSONString);
	 String confirmbookingstring;
	 confirmbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
	 JSONObject newresp = jsonResult.getJSONObject("hotelogix");
	 System.out.println("The new" +newresp);
	 JSONObject resp= newresp.getJSONObject("response");
	 JSONArray newarray = resp.getJSONArray("bookings");
	 JSONObject book2 = newarray.getJSONObject(0);
	 Bookingid=book2.getString("id");
	 
	 return Bookingid;
	
}

}
