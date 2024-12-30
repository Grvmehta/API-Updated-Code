package com.hms.APITestingUsingUnirest;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CancelCommitGroup {

	static String responseJSONString;

	String keytype;
	String accesskey;
	String serverurl;
	String hotelid1;
	 static String groupbookingid;
	String gueststayid;
	static String edibookingid;

	public void fn_cancelcommitgroup(String s1){

	keytype = s1;

	System.out.println("Welcome to Cancelcommitgroup API");

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
	System.out.println("Hello CancelCommitGroup API");
	serverurl = CommonConfig.serverurl;
	hotelid1 = CommonConfig.hotelid1;
	/*Editbooking editbookingobj=new Editbooking();
	String Noofroom="2";
	String isGroupBookingValue="true";
	editbookingobj.EditbookingcallforConfirmBookingidforFutureDate(keytype,Noofroom,isGroupBookingValue);
	edibookingid=editbookingobj.extracttinggroupid();
	System.out.println("edibookingid");*/
	Confirmbooking confirmbookingobj = new Confirmbooking();
	confirmbookingobj.ConfirmbookingcallforFrontDeskforfutureDate("login", "2" , "true");
	groupbookingid = confirmbookingobj.extracinggroupid();

	Editbooking editbookingobj = new Editbooking();
	editbookingobj.fn_editbookingcallforexistingbookingid("login", groupbookingid, "G");
	edibookingid = editbookingobj.extracttinggroupid();


	HttpResponse<JsonNode> responsremoveoccupancies = Unirest.post(""+serverurl+"/ws/web/cancelandcommitgroup")
	               .header("content-type", "application/json")
	               .header("x-ig-sg", "D_gg%fkl85_j")
	               .header("cache-control", "no-cache")
	               .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
	               .body("{"
	                + "\"hotelogix\": {"
	                + "\"version\": \"1.0\","
	                + "\"datetime\": \"2012-01-16T10:10:15\","
	                + "\"request\": {"
	                + "\"method\": \"cancelandcommitgroup\","
	                + "\"key\": \""+accesskey+"\","  
	                + "\"data\": {"    
	                + "\"id\": \""+edibookingid+"\","
	                + "\"charge\": \"125.55\","
	                + "\"sendMail\": true"                        
	                + "}}}}")
	     
	                    .asJson();
	    JsonNode body = responsremoveoccupancies.getBody();  
	    responseJSONString = body.toString();
	    System.out.println("CancelCommitGroup Response:"+responseJSONString);
	}
	    catch(UnirestException e)
	    {
	    e.printStackTrace();
	   
	}
	}
	
	public void fn_cancelcommitgrouptoexistingids(String s1){

		keytype = s1;

		System.out.println("Welcome to Cancelcommitgroup API");

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
		System.out.println("Hello CancelCommitGroup API");
		serverurl = CommonConfig.serverurl;
		hotelid1 = CommonConfig.hotelid1;
	
		
		Getgroup getgroupobj = new Getgroup();
		getgroupobj.Getgroupcall("login");
		groupbookingid = getgroupobj.extractingGOUPPID();
		
		Editbooking editbookingobj=new Editbooking();
		editbookingobj.fn_editbookingcallforexistingbookingid("login", groupbookingid, "G");
		
		edibookingid=editbookingobj.extracttinggroupid();


		HttpResponse<JsonNode> responsremoveoccupancies = Unirest.post(""+serverurl+"/ws/web/cancelandcommitgroup")
		               .header("content-type", "application/json")
		               .header("x-ig-sg", "D_gg%fkl85_j")
		               .header("cache-control", "no-cache")
		               .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
		               .body("{"
		                + "\"hotelogix\": {"
		                + "\"version\": \"1.0\","
		                + "\"datetime\": \"2012-01-16T10:10:15\","
		                + "\"request\": {"
		                + "\"method\": \"cancelandcommitgroup\","
		                + "\"key\": \""+accesskey+"\","  
		                + "\"data\": {"    
		                + "\"id\": \""+edibookingid+"\","
		                + "\"charge\": \"125.55\","
		                + "\"sendMail\": true"                        
		                + "}}}}")
		     
		                    .asJson();
		    JsonNode body = responsremoveoccupancies.getBody();  
		    responseJSONString = body.toString();
		    System.out.println("CancelCommitGroup Response:"+responseJSONString);
		}
		    catch(UnirestException e)
		    {
		    e.printStackTrace();
		   
		}
		}

	public String extractingmessagecancelcommitgroup()
	{

	String localresponseJSONString = responseJSONString;
	JSONObject jsonResult = new JSONObject(localresponseJSONString);
	String cancelcommitgroup;
	cancelcommitgroup = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
	System.out.println("at last getbookings success:"+cancelcommitgroup);
	return cancelcommitgroup;


	}


	}

