package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Addpaycard {

	 String responseJSONString;
		String serverurl;
		String hotelid1;
		String keytype; 
		String accesskey;
	    String message;
	    String paycardurl;
		
	   static String editbookigid;
		//String reservationtype="S";
	    
	    	 

	public void addpaycardcall(String s1) {
		      keytype = s1;
		
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to Addpaycard block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in Addpaycard"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in Addpaycard"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
				    paycardurl=CommonConfig.paycardurl;
					hotelid1 = CommonConfig.hotelid1;
					System.out.println("hello try"+accesskey);
					
					HttpResponse<JsonNode> responseAddpaycard = Unirest.post(""+paycardurl+"/ws/web/addpaycard")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"addpaycard\",\"key\":\""+accesskey+"\",\"data\":{\"creditCardDetails\":{\"cardType\":\"Visa\",\"nameOnCard\":\"Test R\",\"cardNo\":\"4111111111111111\",\"expiryMonth\":11,\"expiryYear\":2023,\"cvc\":\"123\"}}}}}")
				                     .asJson();
					JsonNode body = responseAddpaycard.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
			 		}

					
	

	public String extractingmessageAddpaycard() {
		System.out.println("welcome to extractingmessageAddpaycard");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("status msg of Addpaycard"+message);
		return message;
	}

	
}
