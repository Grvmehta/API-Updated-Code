package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class EditBookingPretax {
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String editbookigid;
	String message;
	String amount="1500";
	String groupId;
	public void EditBookingPretaxcall(String s1) {
		 keytype = s1;
			
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello to EditBookingPretax block:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in EditBookingPretax:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in editbookingpretax:"+keyl);
				accesskey = keyl;
			}
		 try{
			    serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);
				Editbooking editbookingobj=new Editbooking();
				editbookingobj.Editbookingcallforsingleid("login","S","RESERVE");
				editbookigid=editbookingobj.extractingtempid();
				HttpResponse<JsonNode> responseeditbookingpretax = Unirest.post(""+serverurl+"/ws/web/editbookingpretax")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
						  .body("{"
			                		+ "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2012-01-16T10:10:15\","
			                		+ "\"request\": {"
			                		+ "\"method\": \"editbookingpretax\","
			                		+ "\"key\": \""+accesskey+"\","
			                		+ "\"data\": {"
			                		+ "\"id\": \""+editbookigid+"\","
			                		+ "\"amount\": \""+amount+"\""
			                		+ "}}}}")
			                     .asJson();
				JsonNode body = responseeditbookingpretax.getBody();
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
	public void EditBookingPretaxcallForGroup(String s1) {
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
			 groupId=	editbookingobj.extracttinggroupid();
				editbookigid=editbookingobj.extractingtempid();
				HttpResponse<JsonNode> responseeditgrouppretax = Unirest.post(""+serverurl+"/ws/web/editbookingpretax")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
						  .body("{"
			                		+ "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2012-01-16T10:10:15\","
			                		+ "\"request\": {"
			                		+ "\"method\": \"editbookingpretax\","
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
		       commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", groupId );

		} 
	}
	public String extractingmessageeditbookingpretax() {
		System.out.println("welcome to extractingmessageeditbookingpretax");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("status msg of editbookingpretax:"+message);
		return message;
	}
	
}
