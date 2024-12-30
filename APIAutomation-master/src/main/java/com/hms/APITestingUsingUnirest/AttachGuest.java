package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.hms.APITestingUsingUnirest.Generic.ApiUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class AttachGuest {
	
	static String responseJSONString;

	String keytype;
	String accesskey;
	String serverurl;
	String hotelid1;
	String nightauditdate3;
	String nightauditdate4;
	String editbookingid;
	String gueststayid;
	String fname;
	String lname;
	String restype;
	  String groupid;

	public void fn_attachguest(String s1, String s2){

	keytype = s1;
	restype = s2;

	System.out.println("Welcome to Change Guest Stay API");

	if(keytype == "wsauth")
	{
	Wsauth objwsauth = new Wsauth();
	objwsauth.Wsauthcall();
	String keyw = objwsauth.extractingWsauthKey();
	accesskey = keyw;
	}

	else if(keytype == "login")
	{
	Login objlogin = new Login();
	objlogin.Logincall();
	String keyl = objlogin.extractingLoginKey();
	accesskey = keyl;
	}
	try
	{
	System.out.println("Hello Change Group Stay API");
	serverurl = CommonConfig.serverurl;
	hotelid1 = CommonConfig.hotelid1;

	fname=ApiUtils.GA().generateRandomString();
	lname = ApiUtils.GA().generateRandomString();
	nightauditdate3 = CommonConfig.immediatenextdate;
	nightauditdate4 = CommonConfig.immediatenexttonextdate;

	if (restype == "S"){

	Editbooking editbookingobj = new Editbooking();
	editbookingobj.EditbookingcallforReserveGuest("login", "S");

	editbookingid =editbookingobj.extractingtempid();
	gueststayid = editbookingobj.extractingguestStayId();

	}

	else if(restype == "G"){

	Editbooking editbookingobj = new Editbooking();
	editbookingobj.EditbookingcallforReserveGuest("login", "G");

	editbookingid = editbookingobj.extractingtempid();
	groupid = editbookingobj.extracttinggroupid();
	gueststayid = editbookingobj.extractingguestStayId();

	}

	HttpResponse<JsonNode> responseattachguest = Unirest.post(""+serverurl+"/ws/web/attachguest")
	                .header("content-type", "application/json")
	                .header("x-ig-sg", "D_gg%fkl85_j")
	                .header("cache-control", "no-cache")
	                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
	                .body("{"
	                + "\"hotelogix\": {"
	                + "\"version\": \"1.0\","
	                + "\"datetime\": \"2012-01-16T10:10:15\","
	                + "\"request\": {"
	                + "\"method\": \"attachguest\","
	                + "\"key\": \""+accesskey+"\","
	                + "\"data\": {"
	                + "\"bookings\": ["
	                + "{"
	                + "\"id\": \""+editbookingid+"\","
	                + "\"guests\": ["
	                + "{"
	                + "\"guestStayId\": \""+gueststayid+"\","
	                + "\"replaceGuest\": true,"
	                + "\"taxCode\": \"AQSPJ0000\","
	                + "\"fName\": \""+fname+"\","
	                + "\"lName\": \""+lname+"\","
	                + "\"email\": \""+fname+"@stayezee.com\","
	                + "\"phoneNo\": \"011598888\","
	                + "\"mobileNo\": \"9968040558\","
	                + "\"gender\": \"M\","
	                + "\"nationality\": \"US\","
	                + "\"identityTypeId\": \"\","
	                + "\"identityNo\": null,"
	                + "\"organization\": \"\","
	                + "\"otherDetails\": {"
	                + "\"spouseSalutation\": \"Mrs.\","
	                + "\"spouseFName\": \""+ApiUtils.GA().generateRandomString()+"\","
	                + "\"spouseLName\": \"ji\","
	                + "\"spouseDob\": \"1985-02-05\","
	                + "\"anniversary\": \"2015-12-05\""
	                + "},"
	                + "\"addresses\": {"
	                + "\"home\": {"
	                + "\"address\": \"D-996, Cross Road\","
	                + "\"country\": \"US\","
	                + "\"state\": \"CA\","
	                + "\"city\": \"Los Angeles\","
	                + "\"zip\": \"325215\""
	                + "},"
	                + "\"work\": {}"
	                + "}}]}]}}}}")
	                     .asJson();
	    JsonNode body = responseattachguest.getBody();
	    responseJSONString = body.toString();
	    System.out.println("Editbooking rspns from confirm booking:"+responseJSONString);
	}
	    catch(UnirestException e)
	    {
	    e.printStackTrace();
	    }
	finally{
	System.out.println("new3");

	if(restype == "S"){

	Commiteditbooking commitbookingobj = new Commiteditbooking();
	commitbookingobj.CommiteditBookingCallForExistingBookingid("login", restype, editbookingid );
	}
	else{
	Commiteditbooking commitbookingobj = new Commiteditbooking();
	commitbookingobj.CommiteditBookingCallForExistingBookingid("login", restype, groupid );
	}


	}
	}
	
	
	
	
	
	public void attachguestcallForExistingBookingid(String s1, String s2,String s3,String s4){
		keytype = s1;
		editbookingid = s2;
		gueststayid=s3;
		restype = s4;
		System.out.println("Welcome to Change Guest Stay API");

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
		try
		{
		System.out.println("Hello Change Group Stay API");
		serverurl = CommonConfig.serverurl;
		hotelid1 = CommonConfig.hotelid1;

		fname=ApiUtils.GA().generateRandomString();
		lname = ApiUtils.GA().generateRandomString();
		HttpResponse<JsonNode> responseattachguest = Unirest.post(""+serverurl+"/ws/web/attachguest")
                .header("content-type", "application/json")
                .header("x-ig-sg", "D_gg%fkl85_j")
                .header("cache-control", "no-cache")
                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
                .body("{"
                + "\"hotelogix\": {"
                + "\"version\": \"1.0\","
                + "\"datetime\": \"2012-01-16T10:10:15\","
                + "\"request\": {"
                + "\"method\": \"attachguest\","
                + "\"key\": \""+accesskey+"\","
                + "\"data\": {"
                + "\"bookings\": ["
                + "{"
                + "\"id\": \""+editbookingid+"\","
                + "\"guests\": ["
                + "{"
                + "\"guestStayId\": \""+gueststayid+"\","
                + "\"replaceGuest\": true,"
                + "\"taxCode\": \"AQSPJ0000\","
                + "\"fName\": \""+fname+"\","
                + "\"lName\": \""+lname+"\","
                + "\"email\": \""+fname+"@stayezee.com\","
                + "\"phoneNo\": \"011598888\","
                + "\"mobileNo\": \"9968040558\","
                + "\"gender\": \"M\","
                + "\"nationality\": \"US\","
                + "\"identityTypeId\": \"\","
                + "\"identityNo\": null,"
                + "\"organization\": \"\","
                + "\"otherDetails\": {"
                + "\"spouseSalutation\": \"Mrs.\","
                + "\"spouseFName\": \""+ApiUtils.GA().generateRandomString()+"\","
                + "\"spouseLName\": \"ji\","
                + "\"spouseDob\": \"1985-02-05\","
                + "\"anniversary\": \"2015-12-05\""
                + "},"
                + "\"addresses\": {"
                + "\"home\": {"
                + "\"address\": \"D-996, Cross Road\","
                + "\"country\": \"US\","
                + "\"state\": \"CA\","
                + "\"city\": \"Los Angeles\","
                + "\"zip\": \"325215\""
                + "},"
                + "\"work\": {}"
                + "}}]}]}}}}")
                     .asJson();
    JsonNode body = responseattachguest.getBody();
    responseJSONString = body.toString();
    System.out.println("Editbooking rspns from confirm booking:"+responseJSONString);
		}
       catch(UnirestException e)
		{
    	   e.printStackTrace();
		}
		finally{

			   Commiteditbooking commitbookingobj = new Commiteditbooking();
			   commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "restype", editbookingid );
				}
		
	}
	
	
	
	
	
	public String extractingmessageattachguest()
	{
	String localresponseJSONString = responseJSONString;
	JSONObject jsonResult = new JSONObject(localresponseJSONString);
	String attachguest;
	attachguest = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
	System.out.println("at last getbookings success:"+attachguest);
	return attachguest;
	}

    }
