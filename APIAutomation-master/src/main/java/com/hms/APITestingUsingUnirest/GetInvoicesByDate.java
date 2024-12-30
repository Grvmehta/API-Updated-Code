package com.hms.APITestingUsingUnirest;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetInvoicesByDate {

static String responseJSONString;
	
	String keytype;
	String accesskey;
	String serverurl;
	String hotelid1;
	String editbookingid;
	String gueststayid;
	String fname;
	String lname;
	String restype;
    String groupid;
    String invoiceid;
    static String mainid;
    String itemid;
    String amount;
    String fromdate;
    String todate;

public void fn_getinvoicesbydate(String s1){
		
		keytype = s1;
	
		
		System.out.println("Welcome to GetInvoicesByDate API");
		
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
			System.out.println("Hello GetInvoicesByDate Group Stay API");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			fromdate = CommonConfig.currentsystemdate;
			todate = CommonConfig.immediatenextdate;
			
			HttpResponse<JsonNode> responseattachguest = Unirest.post(""+serverurl+"/ws/web/getinvoicesbydate")
                .header("content-type", "application/json")
                .header("x-ig-sg", "D_gg%fkl85_j")
                .header("cache-control", "no-cache")
                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
                .body("{"
                		+ "\"hotelogix\": {"
                		+ "\"version\": \"1.0\","
                		+ "\"datetime\": \"2012-01-16T10:10:15\","
                		+ "\"request\": {"
                		+ "\"method\": \"getinvoicesbydate\","
                		+ "\"key\": \""+accesskey+"\","
                		+ "\"data\": {"
                		+ "\"fromDate\": \""+fromdate+"\","
                		+ "\"toDate\": \""+todate+"\","
                		+ "\"status\": ["
                		+ "\"OPEN\","
                		+ "\"CLOSE\""
                		+ "],"          				
                		+ "\"type\": ["
                		+ "\"CN\","
                		+ "\"DN\""
                		+ "]," 
                		+ "\"hotels\": ["
                		+ "{"
                		+  "\"id\": \""+hotelid1+"\""
                		+ "}]"
                		+ "}}}}")
                     .asJson();
	    	JsonNode body = responseattachguest.getBody();
	    	responseJSONString = body.toString();
	    	System.out.println("Get Invoices By Date Response:" +responseJSONString);
		}
    	catch(UnirestException e)
    	{
    	e.printStackTrace();
    	}
		
}	
		public String extractingmessageforgetinvoicesbydate()
		{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String routetonewfolio;
		routetonewfolio = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("GetInvoicesBy Date Response"+routetonewfolio);
		return routetonewfolio;
		}
		
		
			
		
	
	
}
