package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ApplySpecialDiscount {
	 String responseJSONString;
		String serverurl;
		String hotelid1;
		String keytype; 
		String accesskey;
	    String message;
	    String editbookigid;
		String discountid;
		String requirmentid;

	public void ApplySpecialDiscountcall( String s1) {
		
		  keytype = s1;
			
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to ApplySpecialDiscount block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in ApplySpecialDiscount:"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in ApplySpecialDiscount:"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
					System.out.println("hello try"+accesskey);
					Editbooking editbookingobj=new Editbooking();
					editbookingobj.Editbookingcallforsingleid("login","S","RESERVE");
					editbookigid=editbookingobj.extractingtempid();
					GetAvailableDisounts GetAvailableDisountsobj=new GetAvailableDisounts();
					GetAvailableDisountsobj.GetAvailableDisountscall(keytype);
					discountid=GetAvailableDisountsobj.extractingDiscountid();
					requirmentid=GetAvailableDisountsobj.extractingRequirementsid();
					HttpResponse<JsonNode> responseApplySpecialDiscount = Unirest.post(""+serverurl+"/ws/web/applyspecialdiscount")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"applyspecialdiscount\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"bookingId\": \""+editbookigid+"\","
				                		+ "\"discountId\": \""+discountid+"\","
				                		+ "\"reqId\": \""+requirmentid+"\","
				                		+ "\"reqVal\": \"Discount by harish\""
				                		+ "}}}}")
				                     .asJson();
					JsonNode body = responseApplySpecialDiscount.getBody();
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
	public String extractingmessageApplySpecialDiscount() {
		System.out.println("welcome to extractingmessageApplySpecialDiscount");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("status msg of ApplySpecialDiscount:"+message);
		return message;
	}

}
