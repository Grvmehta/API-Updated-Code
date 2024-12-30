package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class PaymentFailedCaptures {
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String Date;
	String message;
	public void PaymentFailedCapturescall(String s1) {
		
			 keytype = s1;
				
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to PaymentFailedCaptures block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in PaymentFailedCaptures:"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in PaymentFailedCaptures:"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
					System.out.println("hello try"+accesskey);
					Date=CommonConfig.currentsystemdate;
					HttpResponse<JsonNode> responsepaymentfailedcaptures = Unirest.post(""+serverurl+"/ws/web/paymentfailedcaptures")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"paymentfailedcaptures\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"fromDate\": \""+Date+"\","
				                		+ "\"toDate\": \""+Date+"\""
				                		+ "}}}}")
				                     .asJson();
					JsonNode body = responsepaymentfailedcaptures.getBody();
					responseJSONString = body.toString();
					System.out.println("paymentfailedcaptures Response:  "+responseJSONString);
			 }catch(UnirestException e)
			{
					e.printStackTrace();
				}
		
	}
	public String extractingmessagepaymentfailedcaptures(){
		System.out.println("welcome to extractingmessagepaymentfailedcaptures");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("Status msg of paymentfailedcaptures :"+message);
		//return editbookingstring;
		return message;
	}
}
