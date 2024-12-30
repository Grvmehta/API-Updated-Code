package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class EditGroupPreTax {
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String editbookigid;
	String message;
	String amount="1500";
	public void EditGroupPreTaxcall(String s1) {
		 keytype = s1;
			
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello to EditGroupPreTax block:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in EditGroupPreTax:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in EditGroupPreTax:"+keyl);
				accesskey = keyl;
			}
		 try{
			    serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);
				Editbooking editbookingobj=new Editbooking();
				editbookingobj.Editbookingcall("login","G","RESERVE");
				editbookigid=editbookingobj.extracttinggroupid();
				HttpResponse<JsonNode> responseeditgrouppretax = Unirest.post(""+serverurl+"/ws/web/editgrouppretax")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
						  .body("{"
			                		+ "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2012-01-16T10:10:15\","
			                		+ "\"request\": {"
			                		+ "\"method\": \"editgrouppretax\","
			                		+ "\"key\": \""+accesskey+"\","
			                		+ "\"data\": {"
			                		+ "\"id\": \""+editbookigid+"\","
			                		+ "\"amount\": \""+amount+"\""
			                		+ "}}}}")
			                     .asJson();
				JsonNode body = responseeditgrouppretax.getBody();
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
	public String extractingmessageeditgrouppretax() {
		System.out.println("welcome to extractingmessageeditgrouppretax");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("status msg of editgrouppretax:"+message);
		return message;
	}
}
