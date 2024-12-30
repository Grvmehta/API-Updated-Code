package com.hms.APITestingUsingUnirest;

import java.io.File;

import org.json.JSONObject;

import com.hms.APITestingUsingUnirest.Generic.Log4j;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SaveGuestSignature {
	
	String posid;
	static String bookingid;
	String discountamt;
	String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype;
	String accesskey;
	String message;
	static String groupcode;
	String GroupId;
	static String bookingcode;
	static String productid;
	String restype;
	String GuestDetailsId;
	static String guestid;

	public void saveguestsignature(String s1){
	keytype = s1;

	if(keytype == "wsauth")

	{
	Log4j.info("hello to saveguestsignature block:");

	String keyw = Wsauth.wsauthkeyfinalstring;
	accesskey = keyw;
	Log4j.info("wsauthkey in saveguestsignature:"+keyw);
	}

	else if(keytype == "login")
	{
	String keyl = Login.finalloginaccesskey;
	Log4j.info("login key in saveguestsignature:"+keyl);
	accesskey = keyl;
	}
	try{
	   serverurl = CommonConfig.serverurl;
	hotelid1 = CommonConfig.hotelid1;
	Log4j.info("Here is the access key" +accesskey);

	Getbookings getbookingsobj = new Getbookings();
	getbookingsobj.Getbookingscall("login","CHECKIN");
	bookingid= getbookingsobj.extractingmainid();
	guestid=getbookingsobj.extractingguestStayId();
	Editbooking editbookingObj=new Editbooking();
	editbookingObj.fn_editbookingcallforexistingbookingid(keytype, bookingid, "S");
	bookingcode = getbookingsobj.extractingcode();
	Log4j.info("Booking Id for getbookings:" +bookingid);
	String url="file:///E://API-March%202020//APIAutomation//Report//Signature.png";
	String url1="https://drive.google.com/file/d/0B5WEExopJUOdc1NFN1NMX2F5VXhaYkRpYlp6Tmk4ckR6SDRB/view?usp=sharing";

	HttpResponse<JsonNode> responseaddothercharge = Unirest.post(""+serverurl+"/ws/web/saveguestsignature")
	 .header("content-type", "application/json")
	 .header("x-ig-sg", "D_gg%fkl85_j")
	 .header("cache-control", "no-cache")
	 .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
	 .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"saveguestsignature\",\"key\":\""+accesskey+"\",\"data\":{\"bookingId\":\""+bookingid+"\",\"isMain\":true,\"guestStayId\":\""+guestid+"\",\"signatureUrl\":\""+url+"\"}}}}")
	 .asJson();
	JsonNode body = responseaddothercharge.getBody();
	responseJSONString = body.toString();
	Log4j.info("saveguestsignature:" +responseJSONString);
	}catch(UnirestException e)
	{
	e.printStackTrace();
	}
		finally
		{
		Commiteditbooking commitedbookingobj = new Commiteditbooking();
		commitedbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", bookingid);
		}

	}
	
	

	
	public String extractingmessageSaveguestsignature() {

	Log4j.info("welcome to extractingmessageSaveguestsignature");
	String localresponseJSONString = responseJSONString;
	JSONObject jsonResult = new JSONObject(localresponseJSONString);
	message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
	Log4j.info("status msg of saveguestsignature:"+message);
	return message;
	}

}
