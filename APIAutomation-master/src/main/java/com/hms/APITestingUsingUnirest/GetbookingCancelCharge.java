package com.hms.APITestingUsingUnirest;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetbookingCancelCharge {
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	static String editbookigid;
	String message;
	String grpID;
	 String confirmbookingcode;
    String confirmId;
    String Noofroom;
     String isGroupBookingValue;
	public void GetbookingCancelChargecall(String s1){
		keytype = s1;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in GetbookingCancelCharge:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in GetbookingCancelCharge:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			
			Editbooking editbookingobj=new Editbooking();
			editbookingobj.EditbookingcallforReserveGuest(keytype,"S");
			editbookigid=editbookingobj.extractingtempid();
			HttpResponse<JsonNode> responseGetbookingCancelCharge = Unirest.post(""+serverurl+"/ws/web/getbookingcancelcharge")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"getbookingcancelcharge\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"bookings\": ["
		                		+ "{"
		                		+ "\"id\": \""+editbookigid+"\""
		                		+ "}]}}}}")
		                     .asJson();
			JsonNode body = responseGetbookingCancelCharge.getBody();
			responseJSONString = body.toString();
			System.out.println("Response of GetbookingCancelCharge:");
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}	
	}
	public void GetbookingCancelChargecallForFDReservation(String s1,String s2,String s3){
		keytype = s1;
		Noofroom=s2;
		isGroupBookingValue=s3;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in GetbookingCancelCharge:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in GetbookingCancelCharge:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			Editbooking editbookingobj=new Editbooking();
			editbookingobj.EditbookingcallforConfirmBookingidforFutureDate(keytype,Noofroom,isGroupBookingValue);
			editbookigid=editbookingobj.extractingtempid();
			System.out.println("Booking id from confirmbooking within GetbookingCancelCharge  :"+editbookigid);
			HttpResponse<JsonNode> responseGetbookingCancelCharge = Unirest.post(""+serverurl+"/ws/web/getbookingcancelcharge")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"getbookingcancelcharge\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"bookings\": ["
		                		+ "{"
		                		+ "\"id\": \""+editbookigid+"\""
		                		+ "}]}}}}")
		                     .asJson();
			JsonNode body = responseGetbookingCancelCharge.getBody();
			responseJSONString = body.toString();
			System.out.println("Response of GetbookingCancelCharge:");
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}	
		
	}
	public void GetbookingCancelChargecallForGroupReservation(String s1,String s2,String s3){
		keytype = s1;
		Noofroom=s2;
		isGroupBookingValue=s3;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in GetbookingCancelCharge:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in GetbookingCancelCharge:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			Confirmbooking Confirmbookingobj=new Confirmbooking();
			Confirmbookingobj.ConfirmbookingcallforFrontDeskforfutureDate(keytype,Noofroom,isGroupBookingValue);
			//Confirmbookingobj.confirmbookingforgroupresv("login");
			confirmbookingcode=Confirmbookingobj.extractinggroupcode();
			confirmId=Confirmbookingobj.extracingbookingid();
			System.out.println("confirmbooking bookingCode is :"+confirmbookingcode);
			Getgroup getgroupobj = new Getgroup();
			getgroupobj.Getgroupcall("login", confirmbookingcode);
			
			
			
			/*Getgroup Getgroupobj=new Getgroup();
			Getgroupobj.GetgroupcallforFDGroupReservation("login");*/
			//grpID=Getgroupobj.confirmId;
			grpID=getgroupobj.extractinggetgroupidmain();
			Editbooking editbookingobj=new Editbooking();
			editbookingobj.fn_editbookingcallforexistingbookingid("login", grpID,"G");
			editbookigid=editbookingobj.extractingtempid();
			HttpResponse<JsonNode> responseGetbookingCancelCharge = Unirest.post(""+serverurl+"/ws/web/getbookingcancelcharge")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"getbookingcancelcharge\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"bookings\": ["
		                		+ "{"
		                		+ "\"id\": \""+editbookigid+"\""
		                		+ "}]"
		                		+ "}}}}")
		                     .asJson();
			JsonNode body = responseGetbookingCancelCharge.getBody();
			responseJSONString = body.toString();
			System.out.println("Response of GetbookingCancelCharge:");
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}	
		 finally{

		       Commiteditbooking commitbookingobj = new Commiteditbooking();
		       commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", editbookigid );

		} 
	}
	public String extractingmessageGetbookingCancelCharge(){
		System.out.println("welcome to extractingmessageGetbookingCancelCharge");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("Status msg of GetbookingCancelCharge :"+message);
		//return editbookingstring;
		return message;
		
	}
	public String extractingChargeamount(){
		System.out.println("welcome to extractingGetbookingCancelCharge");
		String localresponseJSONString = responseJSONString;
		String Charges;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray chargesArray=jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("charges");
		System.out.println("charge array::"+chargesArray);
		Charges=chargesArray.getJSONObject(1).getString("charge");
		
		System.out.println("charge amount in GetbookingCancelCharge:"+Charges);
		
		return Charges;
		
	}
	public String extractingBookingid(){
		System.out.println("welcome to extractingBookingid");
		String localresponseJSONString = responseJSONString;
		String Bookingid;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray chargesArray=jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("charges");
		System.out.println("charge array::"+chargesArray);
		Bookingid=chargesArray.getJSONObject(0).getString("bookingId");
		
		System.out.println("booking id in GetbookingCancelCharge:"+Bookingid);
		
		return Bookingid;
		
	}
	
}
