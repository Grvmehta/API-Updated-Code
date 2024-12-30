package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ChangeRateAmount {
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String message;
	String nightauditdate1;
	String nightauditdate3;
	String editbookingid;
	String changeamount="751";
	public void ChangeRateAmountCall(String s1) {
		keytype = s1;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in ChangeRateAmount:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in ChangeRateAmount:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			nightauditdate1= CommonConfig.currentsystemdate;
			nightauditdate3= CommonConfig.immediatenextdate;
			Editbooking Editbookingobj =new Editbooking();
			Editbookingobj.EditbookingcallforConfirmBookingid(keytype, "false" , "S");
			editbookingid=Editbookingobj.extractingtempid();
			HttpResponse<JsonNode> responsechangerateamount = Unirest.post(""+serverurl+"/ws/web/changerateamount")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"changerateamount\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"bookings\": ["
		                		+ "{"
		                		+ "\"fromDate\": \""+nightauditdate1+"\","
		                		+ "\"toDate\": \""+nightauditdate3+"\","
		                		+ "\"id\": \""+editbookingid+"\","
		                		+ "\"amount\": \""+changeamount+"\""
		                		+ "}]}}}}")
					  .asJson();
			JsonNode body = responsechangerateamount.getBody();
			responseJSONString = body.toString();
			System.out.println("changerateamount Response:  "+responseJSONString);
	 }catch(UnirestException e)
	{
			e.printStackTrace();
		}

 finally{

   Commiteditbooking commitbookingobj = new Commiteditbooking();
   commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", editbookingid );
	}
	}
	public void changerateamountcall_GrpResv(String s1) {
		keytype = s1;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in ChangeRateAmount:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in ChangeRateAmount:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			nightauditdate1= CommonConfig.currentsystemdate;
			nightauditdate3= CommonConfig.immediatenexttonextdate;
			Confirmbooking Confirmbookingobj=new Confirmbooking();
			Confirmbookingobj.confirmbookingforgroupresv("login","1");
			String Grpcode=Confirmbookingobj.extractinggroupcode();
			Getgroup Getgroupobj=new Getgroup();
			Getgroupobj.Getgroupcall("login", Grpcode);
			String bookingid=Getgroupobj.extractingGOUPPID();
			Editbooking Editbookingobj=new Editbooking();
			Editbookingobj.fn_editbookingcallforexistingbookingid(s1, bookingid, "G");
			editbookingid=Editbookingobj.extractingtempid();
			HttpResponse<JsonNode> responsechangerateamount = Unirest.post(""+serverurl+"/ws/web/changerateamount")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"changerateamount\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"bookings\": ["
		                		+ "{"
		                		+ "\"fromDate\": \""+nightauditdate1+"\","
		                		+ "\"toDate\": \""+nightauditdate3+"\","
		                		+ "\"id\": \""+editbookingid+"\","
		                		+ "\"amount\": \""+changeamount+"\""
		                		+ "}]}}}}")
					  .asJson();
			JsonNode body = responsechangerateamount.getBody();
			responseJSONString = body.toString();
			System.out.println("changerateamount Response:  "+responseJSONString);
	 }catch(UnirestException e)
	{
			e.printStackTrace();
		}

finally{

 Commiteditbooking commitbookingobj = new Commiteditbooking();
 commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", editbookingid );
	}
	
		
	}

public String extractingmessagechangerateamount(){
	System.out.println("welcome to extractingmessagechangerateamount");
	String localresponseJSONString = responseJSONString;
	JSONObject jsonResult = new JSONObject(localresponseJSONString);
	message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
	System.out.println("Status msg of changerateamount :"+message);
	//return editbookingstring;
	return message;
}


}
