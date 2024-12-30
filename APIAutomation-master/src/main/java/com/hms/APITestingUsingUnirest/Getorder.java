package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Getorder {
	String serverurl;
	 //String hotelid1;
	 String keytype;
	 String accesskey;
	 
      String responseJSONString;
     String msg;
     JsonNode body;
	 public void Getordercall(String s1){
         keytype = s1;
		 
		 if(keytype == "wsauth")
			{
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in Getorder:"+keyw);
			}
			
			else if(keytype == "login")
			{
		
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in Getorder:"+keyl);
				accesskey = keyl;
			}
		 try
		 {
			 System.out.println("welcome to Modifyamount try block:");
			 serverurl = CommonConfig.serverurl;
			 System.out.println("hello accesskey:"+accesskey);
			 Savebooking savebookingobj=new Savebooking();
			 savebookingobj.Savebookingcall(keytype);
			 String orderid=savebookingobj.extractingorderid();
			 HttpResponse<JsonNode> responsegetorder = Unirest.post(""+serverurl+"/ws/web/getorder")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "9c5b1361-4e6d-ce04-992a-8fa30ca26ed6")
					  .body("{ \"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2015-09-16T11:01:29\","
		                		+ " \"request\": {"
		                		+ " \"method\": \"getorder\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"languagecode\": \"en\","
		                		+ "\"data\": {"
		                		+ "\"orderId\": {"
		                		+ "\"value\": \""+orderid+"\""
		                		+"}}}}}")
		                     .asJson();
			  body = responsegetorder.getBody();
			 responseJSONString = body.toString();
			 System.out.println("hi...");
			 System.out.println(responseJSONString);
		 } catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
	 }
	 public String extractingmessageGetorder()
		{
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String getorder;
			getorder = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last search modifyamount:"+getorder);
			return getorder;
		}
}
