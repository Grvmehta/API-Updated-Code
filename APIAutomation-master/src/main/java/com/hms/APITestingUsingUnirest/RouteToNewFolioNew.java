package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RouteToNewFolioNew {
	
	static String responseJSONString;

	String serverurl;
	String hotelid1;
	String keytype;
	String accesskey;
	static String guestID="";
	String mainId="";


	public void routetonewfolio(String s)
	{
	keytype = s;
	System.out.println("keytype:"+s);

	if(keytype == "wsauth")
	{
	/*Wsauth objwsauth = new Wsauth();
	objwsauth.Wsauthcall();
	String keyw = objwsauth.extractingWsauthKey();
	accesskey = keyw;
	System.out.println("hello if"+ accesskey);*/
	String keyw = Wsauth.wsauthkeyfinalstring;
	accesskey = keyw;
	System.out.println("wsauthkey in routetonewfolio:"+keyw);
	}

	else if(keytype == "login")
	{
	/*Login objlogin = new Login();
	objlogin.Logincall();
	String keyl = objlogin.extractingLoginKey();
	System.out.println("login key in gethousestatus:"+keyl);
	accesskey = keyl;*/

	String keyl = Login.finalloginaccesskey;
	System.out.println("login key in routetonewfolio:"+keyl);
	accesskey = keyl;
	}

	try
	{
	serverurl = CommonConfig.serverurl;
	Getinvoices getinvoicesobj = new Getinvoices();
	       getinvoicesobj.Getinvoicescall("login", "S");
	String invoiceid = getinvoicesobj.extractinginvoiceid();
	mainId=getinvoicesobj.mainid;
	guestID=Getinvoices.guestID;
	System.out.println("invoice id in getinvoice"+invoiceid);

	HttpResponse<JsonNode> responseroutetonewfolio = Unirest.post(""+serverurl+"/ws/web/routetonewfolio")
	 .header("content-type", "application/json")
	 .header("x-ig-sg", "D_gg%fkl85_j")
	 .header("cache-control", "no-cache")
	 .header("postman-token", "2ddab0a3-b56d-bb9d-ffe8-4322aa23d2f1")
	 .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"routetonewfolio\",\"key\":\""+accesskey+"\",\"data\":{\"from\":\""+invoiceid+"\",\"guestStayId\":\""+guestID+"\",\"markSharer\":true}}}}")
	 .asJson();

	JsonNode body = responseroutetonewfolio.getBody();
	responseJSONString = body.toString();
	System.out.println("routetonewfolio response"+responseJSONString);
	}

	catch(UnirestException e)
	{
	e.printStackTrace();
	}

	}// End of Getinvoicescall() method

	public String extractingmessageroutetonewfolio()
	{
	String localresponseJSONString = responseJSONString;
	JSONObject jsonResult = new JSONObject(localresponseJSONString);
	String routetonewfoliostring;
	routetonewfoliostring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
	System.out.println("at last routetonewfolio success :"+routetonewfoliostring);
	return routetonewfoliostring;
	}

}
