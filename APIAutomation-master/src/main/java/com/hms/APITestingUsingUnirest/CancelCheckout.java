package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CancelCheckout {
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	static String editbookigid;
	String message;
	static String Gueststayid;
	public void CancelCheckoutcall(String s1){
		keytype = s1;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in CancelCheckout:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in CancelCheckout:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			Getbookings Getbookingsobj=new Getbookings();
			Getbookingsobj.GetbookingscallforCancelchkout(keytype);
			editbookigid=Getbookingsobj.extractingmainid();
			Gueststayid=Getbookingsobj.extractingguestStayId();
			System.out.println("booking id within CancelChkout from getbookings::"+editbookigid);
			HttpResponse<JsonNode> responseCancelCheckout = Unirest.post(""+serverurl+"/ws/web/cancelcheckout")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
				        		+ "\"hotelogix\": {"
				        		+ "\"version\": \"1.0\","
				        		+ "\"datetime\": \"2012-01-16T10:10:15\","
				        		+ "\"request\": {"
				        		+ "\"method\": \"cancelcheckout\","
				        		+ "\"key\": \""+accesskey+"\","
				        		+ "\"data\": {"
				        		+ "\"bookingId\": \""+editbookigid+"\","
				        		+ "\"isMain\": true"
				        		+ "}}}}")
				        .asJson();
			JsonNode body = responseCancelCheckout.getBody();
			responseJSONString = body.toString();
			System.out.println("Response of CancelCheckout:");
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}	
		
	public String extractingmessageCancelCheckout(){
		System.out.println("welcome to extractingmessageCancelCheckout");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("Status msg of CancelCheckout :"+message);
		//return editbookingstring;
		return message;
		
	}

}
