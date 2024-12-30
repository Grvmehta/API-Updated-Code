package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetGuestDetail {
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String guestDetailsId;
	String message;

	public void GetGuestDetail(String s1) {
		keytype = s1;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in GetGuestDetail:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in GetGuestDetail:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			Editbooking Editbookingobj=new Editbooking();
			Editbookingobj.Editbookingcall(keytype);
			guestDetailsId=Editbookingobj.extractingdetailsId();
			System.out.println("guestDetailsId from editbookings"+guestDetailsId);
			HttpResponse<JsonNode> responseGetGuestDetail = Unirest.post(""+serverurl+"/ws/web/getguestdetail")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
				        		+ "\"hotelogix\": {"
				        		+ "\"version\": \"1.0\","
				        		+ "\"datetime\": \"2012-01-16T10:10:15\","
				        		+ "\"request\": {"
				        		+ "\"method\": \"getguestdetail\","
				        		+ "\"key\": \""+accesskey+"\","
				        		+ "\"data\": {"
				        		+ "\"id\": \""+guestDetailsId+"\""
				        		+ "}}}}")
				        .asJson();

			JsonNode body = responseGetGuestDetail.getBody();
			responseJSONString = body.toString();
			System.out.println("GetGuestDetail Response:  "+responseJSONString);
	 }catch(UnirestException e)
	{
			e.printStackTrace();
		}
		
	}
	public void getguestdetailcall_GrpResv(String s1) {
		keytype = s1;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in GetGuestDetail:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in GetGuestDetail:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			Confirmbooking Confirmbookingobj=new Confirmbooking();
			Confirmbookingobj.ConfirmbookingcallforFrontDeskforfutureDate(keytype,"2","true");
			String GrpCode = Confirmbookingobj.extractinggroupcode();
			Getgroup Getgroupobj=new Getgroup();
			Getgroupobj.Getgroupcall(keytype, GrpCode);
			String gueststayid=Getgroupobj.extractingGuestStayID();
			String GuestDetailsId=Getgroupobj.guestdetailsid;
			System.out.println("guestDetailsId from editbookings"+GuestDetailsId);
			HttpResponse<JsonNode> responseGetGuestDetail = Unirest.post(""+serverurl+"/ws/web/getguestdetail")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
				        		+ "\"hotelogix\": {"
				        		+ "\"version\": \"1.0\","
				        		+ "\"datetime\": \"2012-01-16T10:10:15\","
				        		+ "\"request\": {"
				        		+ "\"method\": \"getguestdetail\","
				        		+ "\"key\": \""+accesskey+"\","
				        		+ "\"data\": {"
				        		+ "\"id\": \""+GuestDetailsId+"\""
				        		+ "}}}}")
				        .asJson();

			JsonNode body = responseGetGuestDetail.getBody();
			responseJSONString = body.toString();
			System.out.println("GetGuestDetail Response:  "+responseJSONString);
	 }catch(UnirestException e)
	{
			e.printStackTrace();
		}
		
	}
		
	
	public String extractingmessageGetGuestDetail(){
		System.out.println("welcome to extractingmessageGetGuestDetail");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("Status msg of GetGuestDetail :"+message);
		//return editbookingstring;
		return message;
	}
	

}
