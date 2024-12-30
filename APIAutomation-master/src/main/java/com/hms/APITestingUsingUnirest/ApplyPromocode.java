package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ApplyPromocode {
	 String responseJSONString;
		String serverurl;
		String hotelid1;
		String keytype; 
		String accesskey;
	    String message;
		String promocode;
	   static String editbookigid;
		//String reservationtype="S";
	    
	    	 

	public void ApplyPromocodecall(String s1) {
		      keytype = s1;
		
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to ApplyPromocode block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in ApplyPromocode:"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in ApplyPromocode:"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
					promocode=CommonConfig.promocode;
					System.out.println("hello try"+accesskey);
					Editbooking editbookingobj=new Editbooking();
					editbookingobj.Editbookingcallforsingleid("login","S","CHECKIN");
					editbookigid=editbookingobj.extractingtempid();
					HttpResponse<JsonNode> responseApplyPromocode = Unirest.post(""+serverurl+"/ws/web/applypromocode")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"applypromocode\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"bookingId\": \""+editbookigid+"\","
				                		+ "\"promoCode\": \""+promocode+"\","
				                		+ "\"type\": \"S\""
				                		+ "}}}}")
				                     .asJson();
					JsonNode body = responseApplyPromocode.getBody();
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
	public void ApplyPromocodecallForGroupReservation(String s1) {
		  keytype = s1;
			
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to ApplyPromocode block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in ApplyPromocode:"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in ApplyPromocode:"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
					promocode=CommonConfig.promocode;
					System.out.println("hello try"+accesskey);
					Confirmbooking Confirmbookingobj=new Confirmbooking();
					Confirmbookingobj.ConfirmbookingcallforFrontDeskforfutureDate(keytype, "1", "true");
					editbookigid=Confirmbookingobj.extracinggroupid();
					Editbooking editbookingobj=new Editbooking();
					editbookingobj.fn_editbookingcallforexistingbookingid(keytype, editbookigid, "G");
					String EditbookingId=editbookingobj.extracttinggroupid();
					
					System.out.println("editbooking id is : "+editbookigid);
					HttpResponse<JsonNode> responseApplyPromocode = Unirest.post(""+serverurl+"/ws/web/applypromocode")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"applypromocode\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"bookingId\": \""+EditbookingId+"\","
				                		+ "\"promoCode\": \""+promocode+"\","
				                		+ "\"type\": \"G\""
				                		+ "}}}}")
				                     .asJson();
					JsonNode body = responseApplyPromocode.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
			 finally{

			       Commiteditbooking commitbookingobj = new Commiteditbooking();
			       commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", editbookigid );

			} 
					
	}

	public String extractingmessageApplyPromocode() {
		System.out.println("welcome to extractingmessageApplyPromocode");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("status msg of ApplyPromocode:"+message);
		return message;
	}
	
}
