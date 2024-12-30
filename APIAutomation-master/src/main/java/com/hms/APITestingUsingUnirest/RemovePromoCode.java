package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RemovePromoCode {
	String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
    String message;
    String editbookigid;
	String reservationtype="S";
    String promocode="promo22";
	public void RemovePromoCodecall(String s1,String s2) {
		 keytype = s1;
		 editbookigid=s2;	
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello to RemovePromoCode block:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in RemovePromoCode:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in RemovePromoCode:"+keyl);
				accesskey = keyl;
			}
		 try{
			    serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);
				Editbooking Editbookingobj=new Editbooking();
				Editbookingobj.fn_editbookingcallforexistingbookingid(keytype, editbookigid, "S");
               String EditbookingId=Editbookingobj.extractingtempid();
				/*ApplyPromocode ApplyPromocodeobj=new ApplyPromocode();
				editbookigid=ApplyPromocodeobj.editbookigid;*/
				System.out.println("booking id from applypromocode::"+EditbookingId);
				HttpResponse<JsonNode> responseremovepromocode = Unirest.post(""+serverurl+"/ws/web/removepromocode")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
						  .body("{"
			                		+ "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2012-01-16T10:10:15\","
			                		+ "\"request\": {"
			                		+ "\"method\": \"removepromocode\","
			                		+ "\"key\": \""+accesskey+"\","
			                		+ "\"data\": {"
			                		+ "\"bookingId\": \""+EditbookingId+"\","
			                		+ "\"promoCode\": \""+promocode+"\","
			                		+ "\"type\": \""+reservationtype+"\""
			                		+ "}}}}")
			                     .asJson();
				JsonNode body = responseremovepromocode.getBody();
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
	 public void RemovePromoCodecallForGroupReservation(String s1, String s2) {
		 keytype = s1;
		 editbookigid=s2;	
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello to RemovePromoCode block:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in RemovePromoCode:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in RemovePromoCode:"+keyl);
				accesskey = keyl;
			}
		 try{
			    serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);
				Editbooking Editbookingobj=new Editbooking();
				Editbookingobj.fn_editbookingcallforexistingbookingid(keytype, editbookigid, "G");
				String EditbookingId=Editbookingobj.extracttinggroupid();
				System.out.println("booking id from applypromocode::"+EditbookingId);
					HttpResponse<JsonNode> responseremovepromocode = Unirest.post(""+serverurl+"/ws/web/removepromocode")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"removepromocode\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"bookingId\": \""+EditbookingId+"\","
				                		+ "\"promoCode\": \""+promocode+"\","
				                		+ "\"type\": \"G\""
				                		+ "}}}}")
				                     .asJson();
					JsonNode body = responseremovepromocode.getBody();
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
	public String extractingmessageremovepromocode() {
		System.out.println("welcome to extractingmessageremovepromocode");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("status msg of removepromocode:"+message);
		return message;
	}
	
}
