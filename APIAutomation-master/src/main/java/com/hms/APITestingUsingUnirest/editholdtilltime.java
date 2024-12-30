package com.hms.APITestingUsingUnirest;

import java.time.LocalDate;

import org.json.JSONObject;

import com.hms.APITestingUsingUnirest.Generic.ApiUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class editholdtilltime {
	String discountamt;
	  String responseJSONString;
		String serverurl;
		String hotelid1;
		String keytype; 
		String accesskey;
	    String message;
	    String Bookingid;
	    String GroupId;
		
		String time="8";
		String typeoftime="hour";
		
		public void editholdtilltime_SingleResv_getbookingcall(String s1) throws Throwable {
			keytype = s1;
			 
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to editholdtilltime   block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in editholdtilltime  :"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in editholdtilltime  :"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
					System.out.println("hello try"+accesskey);
					
					 ApiUtils.GA().fn_GetCurrentDate();
					String dateandtime=ApiUtils.todate+" "+ApiUtils.Time;
					Savebooking Savebookingobj=new Savebooking();
					Savebookingobj.SavebookingcallforHoldTillReservation(keytype, "1", "false");
					String bookingcode=Savebookingobj.extractingbookingCode();
					Getbooking Getbookingobj=new Getbooking();
					Getbookingobj.getbookingcallforExistingbookingcode(keytype,bookingcode);
					Bookingid=Getbookingobj.extractingbookingid();
					HttpResponse<JsonNode> responseeditholdtilltime = Unirest.post(""+serverurl+"/ws/web/editholdtilltime")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
						        		+ "\"hotelogix\": {"
						        		+ "\"version\": \"1.0\","
						        		+ "\"datetime\": \"2012-01-16T10:10:15\","
						        		+ "\"request\": {"
						        		+ "\"method\": \"editholdtilltime\","
						        		+ "\"key\": \""+accesskey+"\","
						        		+ "\"data\": {"
						        		+ "\"isMain\": {"
						        		+ "\"value\": true"
						        		+ "},"
						        		+ "\"bookingId\": {"
						        		+ "\"value\": \""+Bookingid+"\""
						        		+ " },"
						        		+ "\"holdTill\": {"
						        		+ "\"holdTillDate\": {"
						        		+ "\"value\": \""+dateandtime+"\""
						        		+ " },"
						        		+ "\"releaseAfter\": {"
						        		+ "\"value\": \""+time+"\","
						        		+ "\"type\": \""+typeoftime+"\""
						        		+ "}}}}}}")
							  .asJson();
					JsonNode body = responseeditholdtilltime.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
		}
		public void editholdtilltimecall_SingleResv_Editbooking(String s1)throws Throwable {
			
			keytype = s1;
			 
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to editholdtilltime   block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in editholdtilltime  :"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in editholdtilltime  :"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
					System.out.println("hello try"+accesskey);
					
					ApiUtils.GA().fn_GetCurrentDate();
					String dateandtime=ApiUtils.todate+" "+ApiUtils.Time;
					Savebooking Savebookingobj=new Savebooking();
					Savebookingobj.SavebookingcallforHoldTillReservation(keytype, "1", "false");
					String bookingid=Savebookingobj.extractingbookingrid();
					String msg=Savebookingobj.extractingmessagesavebooking();
					Editbooking Editbookingobj=new Editbooking();
					Editbookingobj.fn_editbookingcallforexistingbookingid(keytype,bookingid,"S");
					Bookingid=Editbookingobj.extractingtempid();
					HttpResponse<JsonNode> responseeditholdtilltime = Unirest.post(""+serverurl+"/ws/web/editholdtilltime")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
						        		+ "\"hotelogix\": {"
						        		+ "\"version\": \"1.0\","
						        		+ "\"datetime\": \"2012-01-16T10:10:15\","
						        		+ "\"request\": {"
						        		+ "\"method\": \"editholdtilltime\","
						        		+ "\"key\": \""+accesskey+"\","
						        		+ "\"data\": {"
						        		+ "\"isMain\": {"
						        		+ "\"value\": false"
						        		+ "},"
						        		+ "\"bookingId\": {"
						        		+ "\"value\": \""+Bookingid+"\""
						        		+ " },"
						        		+ "\"holdTill\": {"
						        		+ "\"holdTillDate\": {"
						        		+ "\"value\": \""+dateandtime+"\""
						        		+ " },"
						        		+ "\"releaseAfter\": {"
						        		+ "\"value\": \""+time+"\","
						        		+ "\"type\": \""+typeoftime+"\""
						        		+ "}}}}}}")
							  .asJson();
					JsonNode body = responseeditholdtilltime.getBody();
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
		public void editholdtilltimecall_GrpSingleResv_Getgroup(String s1) throws Throwable {
			keytype = s1;
			 
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to editholdtilltime   block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in editholdtilltime  :"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in editholdtilltime  :"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
					System.out.println("hello try"+accesskey);
					
					ApiUtils.GA().fn_GetCurrentDate();
					String dateandtime=ApiUtils.todate+" "+ApiUtils.Time;
					Savebooking Savebookingobj=new Savebooking();
					Savebookingobj.SavebookingcallforHoldTillReservation(keytype, "1", "true");

					String msg=Savebookingobj.extractingmessagesavebooking();
					System.out.println(msg);
					String bookingcode=Savebookingobj.extractingGroupCode();
					Getgroup Getgroupobj=new Getgroup();
					Getgroupobj.Getgroupcall(keytype, bookingcode);
					Bookingid=Getgroupobj.extractinggetbookingid();
					HttpResponse<JsonNode> responseeditholdtilltime = Unirest.post(""+serverurl+"/ws/web/editholdtilltime")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
						        		+ "\"hotelogix\": {"
						        		+ "\"version\": \"1.0\","
						        		+ "\"datetime\": \"2012-01-16T10:10:15\","
						        		+ "\"request\": {"
						        		+ "\"method\": \"editholdtilltime\","
						        		+ "\"key\": \""+accesskey+"\","
						        		+ "\"data\": {"
						        		+ "\"isMain\": {"
						        		+ "\"value\": true"
						        		+ "},"
						        		+ "\"bookingId\": {"
						        		+ "\"value\": \""+Bookingid+"\""
						        		+ " },"
						        		+ "\"holdTill\": {"
						        		+ "\"holdTillDate\": {"
						        		+ "\"value\": \""+dateandtime+"\""
						        		+ " },"
						        		+ "\"releaseAfter\": {"
						        		+ "\"value\": \""+time+"\","
						        		+ "\"type\": \""+typeoftime+"\""
						        		+ "}}}}}}")
							  .asJson();
					JsonNode body = responseeditholdtilltime.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
			
		}
		public void editholdtilltimecall_GrpSingleResv_Editbooking(String s1) throws Throwable {
			keytype = s1;
			 
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to editholdtilltime   block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in editholdtilltime  :"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in editholdtilltime  :"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
					System.out.println("hello try"+accesskey);
					
					ApiUtils.GA().fn_GetCurrentDate();
					String dateandtime=ApiUtils.todate+" "+ApiUtils.Time;
					Savebooking Savebookingobj=new Savebooking();
					Savebookingobj.SavebookingcallforHoldTillReservation(keytype, "1", "true");

					String msg=Savebookingobj.extractingmessagesavebooking();
					System.out.println(msg);
					String bookingid=Savebookingobj.extracinggroupid();
					Editbooking Editbookingobj=new Editbooking();
					Editbookingobj.fn_editbookingcallforexistingbookingid(keytype,bookingid,"G");
					Bookingid=Editbookingobj.extractingtempid();
					HttpResponse<JsonNode> responseeditholdtilltime = Unirest.post(""+serverurl+"/ws/web/editholdtilltime")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
						        		+ "\"hotelogix\": {"
						        		+ "\"version\": \"1.0\","
						        		+ "\"datetime\": \"2012-01-16T10:10:15\","
						        		+ "\"request\": {"
						        		+ "\"method\": \"editholdtilltime\","
						        		+ "\"key\": \""+accesskey+"\","
						        		+ "\"data\": {"
						        		+ "\"isMain\": {"
						        		+ "\"value\": true"
						        		+ "},"
						        		+ "\"bookingId\": {"
						        		+ "\"value\": \""+Bookingid+"\""
						        		+ " },"
						        		+ "\"holdTill\": {"
						        		+ "\"holdTillDate\": {"
						        		+ "\"value\": \""+dateandtime+"\""
						        		+ " },"
						        		+ "\"releaseAfter\": {"
						        		+ "\"value\": \""+time+"\","
						        		+ "\"type\": \""+typeoftime+"\""
						        		+ "}}}}}}")
							  .asJson();
					JsonNode body = responseeditholdtilltime.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
		}

public String extractingmessageeditholdtilltime() {
			
			System.out.println("welcome to extractingmessageeditholdtilltime");
			String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("status msg of editholdtilltime:"+message);
			return message;
		}


}
