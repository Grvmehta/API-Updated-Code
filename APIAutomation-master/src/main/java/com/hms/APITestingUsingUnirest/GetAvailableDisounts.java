package com.hms.APITestingUsingUnirest;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetAvailableDisounts {
	String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
    String message;
    String editbookigid;
	String discountid;
	String requirementsid;

	public void GetAvailableDisountscall(String s1) {

		  keytype = s1;
			
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to GetAvailableDisounts block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in GetAvailableDisounts:"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in GetAvailableDisounts:"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
					System.out.println("hello try"+accesskey);
					HttpResponse<JsonNode> responseGetAvailableDisounts = Unirest.post(""+serverurl+"/ws/web/getavailabledisounts")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{"
				                		+ "\"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2012-01-16T10:10:15\","
				                		+ "\"request\": {"
				                		+ "\"method\": \"getavailabledisounts\","
				                		+ "\"key\": \""+accesskey+"\""
				                		+ "}}}")
				                     .asJson();
					JsonNode body = responseGetAvailableDisounts.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
			 
	}
	public String extractingmessageGetAvailableDisounts() {
		System.out.println("welcome to extractingmessageGetAvailableDisounts");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("status msg of GetAvailableDisounts:"+message);
		return message;
	}
	public String extractingDiscountid() {
		System.out.println("welcome to extractingDiscountid");
		String localresponseJSONString = responseJSONString;
		System.out.println(localresponseJSONString);
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray getDiscountArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("discounts");
		JSONObject Discountid = getDiscountArray.getJSONObject(0);
		discountid = Discountid.getString("id");
		System.out.println(":AvailableDisounts id:"+discountid);
		return discountid;
		
	}
	public String extractingRequirementsid(){
		System.out.println("welcome to extractingDiscountid");
		String localresponseJSONString = responseJSONString;
		System.out.println(localresponseJSONString);
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray getDiscountArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("discounts");
		System.out.println(getDiscountArray.length());
		JSONObject Discountid = getDiscountArray.getJSONObject(0);
		discountid = Discountid.getString("id");
		System.out.println(":AvailableDisounts id:"+discountid);
		 JSONArray requirements=Discountid.getJSONArray("requirements");
		 System.out.println(requirements.length());
		 JSONObject gueststay=requirements.getJSONObject(0);
		 requirementsid= gueststay.getString("id");
		 System.out.println(requirementsid);
		 System.out.println("requirements Id from GetAvailableDisounts:"+requirementsid);
		return requirementsid;
		
	}

}
