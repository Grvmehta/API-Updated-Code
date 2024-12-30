package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetPaymentMode {
	
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	HttpResponse<JsonNode> responseGetPaymentMode;
	
	public void getPaymentModeCall(String s)
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
			System.out.println("login key in GetPaymentMode:"+keyl);
			accesskey = keyl;
			
		}
		try
		{
			System.out.println("hello try GetPaymentMode");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			
			
			responseGetPaymentMode= Unirest.post(""+serverurl+"/ws/web/getpaymentmode")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebf56e64-e308-b8b7-bd77-65d49111c1e0")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"getpaymentmode\",\"key\":\""+accesskey+"\",\"languagecode\":\"en\"}}}")
					  .asJson();
			JsonNode body = responseGetPaymentMode.getBody();
			System.out.println("GetPaymentMode response::"+body);	
			responseJSONString = body.toString();	
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// end of Createagentcall() method
	
	public int extractingmessageGetPaymentMode()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getPaymentModesuccessstring="";
		//getGuestTitlesuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		
	int status=	responseGetPaymentMode.getStatus();
		
		
		/*JSONObject newresp = jsonResult.getJSONObject("hotelogix");
		JSONObject resp= newresp.getJSONObject("response");
		JSONObject hotels=resp.getJSONArray("hotels").getJSONObject(0);	
		JSONObject titles=hotels.getJSONArray("paymentmodes").getJSONObject(0);
		getPaymentModesuccessstring=titles.get("bycheque").toString();*/
		//AgentID=hotels.get("agentId").toString();
		//System.out.println("The agent id is:" +AgentID);*/
		System.out.println("last getPaymentMode success"+status);
		return status;
	}
	

}
