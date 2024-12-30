package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ConfirmTempBookings {
	
	String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 HttpResponse<JsonNode> responseconfirmtempbookings ;
	 String nightauditdate3;
	 String nightauditdate4;
	 String mainId="";
	 
	
	public void confirmtempbookings(String key)
	{
		keytype = key;
		 
		 if(keytype == "wsauth")
			{
				System.out.println("hello if confirmtempbookings:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in confirmtempbookings:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in confirmtempbookings:"+keyl);
				accesskey = keyl;
			}
		 
		// Getbookings getbookingsObj=new Getbookings();
			//getbookingsObj.Getbookingscall(keytype, "BLOCKED");
			
		//String mainId=	getbookingsObj.extractingmainid();
		Editbooking editbookingObj=new Editbooking();
		editbookingObj.Editbookingcallforsingleid(keytype, "S", "BLOCKED");
		mainId	=editbookingObj.extractingtempid();
		System.out.println(mainId);
		
		 serverurl = CommonConfig.serverurl;
		 
		 System.out.println("hello:: accesskey in confirmtempbookings:"+accesskey);
		 
 try {
	responseconfirmtempbookings= Unirest.post(""+serverurl+"/ws/web/confirmtempbookings")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebd1bb38-292b-54ad-c70f-f226cab49295")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"confirmtempbookings\",\"key\":\""+accesskey+"\",\"languagecode\":\"en\",\"data\":{\"type\":\"S\",\"bookings\":[{\"id\":\""+mainId+"\"}]}}}}")
					  .asJson();

		 
		 JsonNode body = responseconfirmtempbookings.getBody();

		 responseJSONString = body.toString();
		 System.out.println(responseJSONString);
 }
		 catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
 finally
 {
	 Commiteditbooking  commiteditbookingObj=new Commiteditbooking();
	 commiteditbookingObj.CommiteditBookingCallForExistingBookingid(keytype, "S", mainId);
 }
	}
	
	 public String extractingmessgaeconfirmtempbookings()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			
			String confirmtempbookingsString;
			int res=responseconfirmtempbookings.getStatus();
	
			confirmtempbookingsString = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			//getJSONObject("currencycode").toString();
			System.out.println("at last confirmtempbookings"+res);
			return confirmtempbookingsString;
	 }


}
