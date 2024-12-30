package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class changerateamountgroup {
	String discountamt;
	  String responseJSONString;
		String serverurl;
		String hotelid1;
		String keytype; 
		String accesskey;
	    String message;
	    String Bookingid;
	    String GroupId;
	    
	    public void changerateamountgroupcall(String s1){
			keytype = s1;
			 
			 if(keytype == "wsauth")
				 
				{
					System.out.println("hello to changerateamountgroup block:");
					
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in changerateamountgroup:"+keyw);
				}
				
				else if(keytype == "login")
				{	
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in changerateamountgroup:"+keyl);
					accesskey = keyl;
				}
			 try{
				    serverurl = CommonConfig.serverurl;
					hotelid1 = CommonConfig.hotelid1;
					System.out.println("hello try"+accesskey);
					Getbookings Getbookingsobj=new Getbookings();
					Getbookingsobj.Getbookingscall(keytype, "CHECKIN");
					String groupcode = Getbookingsobj.extractinggroupcode();
					Getgroup Getgroupobj=new Getgroup();
					Getgroupobj.Getgroupcall(keytype,groupcode);
					Bookingid=Getgroupobj.extractingGOUPPID();
					Editbooking Editbookingobj=new Editbooking();
					Editbookingobj.fn_editbookingcallforexistingbookingid(keytype,Bookingid,"G");
					GroupId=Editbookingobj.extracttinggroupid();
					String chkinDate=Editbookingobj.extracttingCheckinDate();
					String chkoutDate=Editbookingobj.extracttingCheckoutDate();
							String amt="3000";
					HttpResponse<JsonNode> responsechangerateamountgroup = Unirest.post(""+serverurl+"/ws/web/changerateamountgroup")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
							  .body("{ \"hotelogix\": {"
				                		+ "\"version\": \"1.0\","
				                		+ "\"datetime\": \"2015-09-16T11:01:29\","
				                		+ " \"request\": {"
				                		+ " \"method\": \"changerateamountgroup\","
				                		+ "\"key\": \""+accesskey+"\","
				                		+ "\"data\": {"
				                		+ "\"fromDate\": \""+chkinDate+"\","
				                		+ "\"toDate\": \""+chkoutDate+"\","
				                		+ "\"id\": \""+GroupId+"\","
				                		+ "\"amount\": \""+amt+"\","
				                		+ "\"pkgId\": \"0\""
				                		+ "}}}}")
							  .asJson();
					JsonNode body = responsechangerateamountgroup.getBody();
					responseJSONString = body.toString();
					System.out.println(responseJSONString);
			 }catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
}
public String extractingmessagechangerateamountgroup() {
			
			System.out.println("welcome to extractingmessagechangerateamountgroup");
			String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("status msg of changerateamountgroup:"+message);
			return message;
		}
}
