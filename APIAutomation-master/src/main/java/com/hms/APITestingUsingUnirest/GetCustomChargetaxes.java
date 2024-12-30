package com.hms.APITestingUsingUnirest;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetCustomChargetaxes {
	  String responseJSONString;
	  String accesskey;
	    String message;
	    String serverurl;
	    String keytype;
		private String TaxId; 
	public void GetCustomChargetaxescall(String s1){
		keytype = s1;
		 
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello to GetCustomChargetaxes block:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in GetCustomChargetaxes:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in GetCustomChargetaxes:"+keyl);
				accesskey = keyl;
			}
		 try{
			    serverurl = CommonConfig.serverurl;
			    System.out.println("hello try"+accesskey);
			    HttpResponse<JsonNode> responseGetCustomChargetaxes = Unirest.post(""+serverurl+"/ws/web/getcustomchargetaxes")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
						  .body("{"
			                		+ "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2012-01-16T10:10:15\","
			                		+ "\"request\": {"
			                		+ "\"method\": \"getcustomchargetaxes\","
			                		+ "\"key\": \""+accesskey+"\""
			                		+ "}}}")
			                     .asJson();
				 
					JsonNode body = responseGetCustomChargetaxes.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
				
		
	}
	public String extractingTaxId(){
		System.out.println("welcome to extractingTaxId");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray getcustomchargetaxes = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("customchargetaxes");
		JSONObject customcharge=getcustomchargetaxes.getJSONObject(0);
		 JSONObject taxes=customcharge.getJSONArray("taxes").getJSONObject(0);
		String TaxAmtInValue=taxes.getString("amount");
		 TaxId=taxes.getString("id");

		return TaxId;	
	}
	public String extractingmessageGetCustomChargetaxes() {
		
		System.out.println("welcome to extractingmessageGetCustomChargetaxes");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("status msg of getcustomchargetaxes:"+message);
		return message;
	}

}
