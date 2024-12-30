package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Startlogoutusers {
	
	String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 HttpResponse<JsonNode> responsestartlogoutusers ;
	 
	
	public void startlogoutusers(String key)
	{
		keytype = key;
		 
		 if(keytype == "wsauth")
			{
				System.out.println("hello if startlogoutusers:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in startlogoutusers:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in startlogoutusers:"+keyl);
				accesskey = keyl;
			}
		 
	/*	// Getbookings getbookingsObj=new Getbookings();
			//getbookingsObj.Getbookingscall(keytype, "BLOCKED");
			
		//String mainId=	getbookingsObj.extractingmainid();
		Editbooking editbookingObj=new Editbooking();
		
		mainId=editbookingObj.extracttinggroupid();
		System.out.println("GroupID::"+mainId);
		String singleId=editbookingObj.extractingtempid();
		System.out.println("SingleID::"+singleId);*/
		 serverurl = CommonConfig.serverurl;
		 System.out.println("hello:: accesskey in startlogoutusers:"+accesskey);
		 
try {
	responsestartlogoutusers= Unirest.post(""+serverurl+"/ws/web/startlogoutusers")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebd1bb38-292b-54ad-c70f-f226cab49295")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"startlogoutusers\",\"key\":\""+accesskey+"\"}}}")
					  .asJson();

		 
		 JsonNode body = responsestartlogoutusers.getBody();

		 responseJSONString = body.toString();
		 System.out.println(responseJSONString);
}
		 catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}
	
	 public String extractingmessgaestartlogoutusers()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			
			String startlogoutusersString;
			int res=responsestartlogoutusers.getStatus();
	
			startlogoutusersString = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			//getJSONObject("currencycode").toString();
			System.out.println("at last startlogoutusers"+res);
			return startlogoutusersString;
	 }


}
