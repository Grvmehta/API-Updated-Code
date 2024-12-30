package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hms.APITestingUsingUnirest.Generic.ApiUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetGuestTitle {
	
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	HttpResponse<JsonNode> responseGetGuestTitle;
	
	public void getGuestTitleCall(String s)
	{
		keytype = s;
		
		if(keytype == "wsauth")
		{
			Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in GetGuestTitle:"+keyl);
			accesskey = keyl;
			
		}
		try
		{
			System.out.println("hello try GetGuestTitle");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			
			
			responseGetGuestTitle = Unirest.post(""+serverurl+"/ws/web/getguesttitle")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebf56e64-e308-b8b7-bd77-65d49111c1e0")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"getguesttitle\",\"key\":\""+accesskey+"\",\"languagecode\":\"en\"}}}")
					  .asJson();
			JsonNode body = responseGetGuestTitle.getBody();
			System.out.println("GetGuestTitle response::"+body);	
			responseJSONString = body.toString();	
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// end of Createagentcall() method
	
	public int extractingmessageGetGuestTitle()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getGuestTitlesuccessstring="";
		//getGuestTitlesuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		
	int status=	responseGetGuestTitle.getStatus();
		/*JSONObject newresp = jsonResult.getJSONObject("hotelogix");
		JSONObject resp= newresp.getJSONObject("response");
		JSONObject hotels=resp.getJSONArray("hotels").getJSONObject(0);	
		JSONObject titles=hotels.getJSONArray("guesttitles").getJSONObject(0);
		getGuestTitlesuccessstring=titles.get("value").toString();*/
		//AgentID=hotels.get("agentId").toString();
		//System.out.println("The agent id is:" +AgentID);*/
		System.out.println("last CreateAmenity success"+status);
		return status;
	}
	
	

}
