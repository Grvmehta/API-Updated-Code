package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Modifyamount {
	
     String responseJSONString;
	 String serverurl;
	 //String hotelid1;
	 String keytype;
	 String accesskey;
	 String changeblebookingprice;
	 public void Modifyamountcall(String s1){
         keytype = s1;
		 
		 if(keytype == "wsauth")
			{
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in Modifyamount:"+keyw);
			}
			
			else if(keytype == "login")
			{
		
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in Modifyamount:"+keyl);
				accesskey = keyl;
			}
		 try
		 {
			 System.out.println("welcome to Modifyamount try block:");
			 serverurl = CommonConfig.serverurl;
			 System.out.println("hello accesskey:"+accesskey);
			 Loadcart loadcartobj=new Loadcart();
			 loadcartobj.Loadcartcall(keytype);
			 String hotelid=loadcartobj.extractinghotelid();
			 System.out.println("Hotelid in Modifyamount ::"+hotelid);
			 String bookingid=loadcartobj.extracingbookingid();
			 System.out.println("Bookingid in Modifyamount ::"+bookingid);
			// Search searchobj = new Search();
			 //searchobj.Searchcall(keytype);
			 //System.out.println("hi");
			// String rateid = searchobj.extractingrateid();
			  // System.out.println("rateid in addtocart ::"+rateid);
			 
			 
			 HttpResponse<JsonNode> responsemodifyamount = Unirest.post(""+serverurl+"/ws/web/modifyamount")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "9c5b1361-4e6d-ce04-992a-8fa30ca26ed6")
					  .body("{ \"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ " \"request\": {"
		                		+ " \"method\": \"modifyamount\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"languagecode\": \"en\","
		                		+ "\"data\": {"
		                		+ "\"hotels\": ["
		                		+ "{"
		                		+ "\"id\": \""+hotelid+"\","
		                		+ "\"reservations\": ["
		                		+ "{"
		                		+ "\"id\": \""+bookingid+"\","
		                		+ "\"base\": {"
		                		+ "\"amount\": \""+changeblebookingprice+"\","
		                		+ "\"inclusiveoftax\": \"0\""
		                		+ "}}]}]}}}}")
					  .asJson();
			 JsonNode body = responsemodifyamount.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		 
	}
	 public String extractingmessageModifyamount()
		{
			 String localresponseJSONString = responseJSONString;
				JSONObject jsonResult = new JSONObject(localresponseJSONString);
				String modifyamountstring;
				modifyamountstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
				System.out.println("at last search modifyamount:"+modifyamountstring);
				return modifyamountstring;
		}
		 
		 
	 }
	
	


