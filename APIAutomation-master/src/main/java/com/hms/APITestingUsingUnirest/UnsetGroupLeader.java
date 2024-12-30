package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnsetGroupLeader {
	String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 HttpResponse<JsonNode> responseunsetgroupleader ;
	 String nightauditdate3;
	 String nightauditdate4;
	 String mainId="";
	 String singleId="";
	
	public void unsetgroupleader(String key)
	{
		keytype = key;
		 
		 if(keytype == "wsauth")
			{
				System.out.println("hello if unsetgroupleader:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in unsetgroupleader:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in unsetgroupleader:"+keyl);
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
		Setgroupleader setgrpldrObj=new Setgroupleader();
		setgrpldrObj.setgroupleader(keytype);
		System.out.println("unsetgroup leader mainid:::::"+setgrpldrObj.mainId);
		System.out.println("unsetgroup leader singleid:::::"+setgrpldrObj.singleId);
		mainId=setgrpldrObj.mainId;
		singleId=setgrpldrObj.singleId;
		serverurl = CommonConfig.serverurl;
		 Editbooking editBookingObj=new Editbooking();
		 editBookingObj.fn_editbookingcallforexistingbookingid(keytype, mainId, "G");
		 System.out.println("hello:: accesskey in unsetgroupleader:"+accesskey);
		 
try {
	responseunsetgroupleader= Unirest.post(""+serverurl+"/ws/web/unsetgroupleader")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebd1bb38-292b-54ad-c70f-f226cab49295")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"unsetgroupleader\",\"key\":\""+accesskey+"\",\"data\":{\"id\":\""+mainId+"\",\"bookingId\":\""+singleId+"\"}}}}")
					  .asJson();

		 
		 JsonNode body = responseunsetgroupleader.getBody();

		 responseJSONString = body.toString();
		 System.out.println(responseJSONString);
}
		 catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

finally
{
	 Commiteditbooking  commiteditbookingObj=new Commiteditbooking();
	 commiteditbookingObj.CommiteditBookingCallForExistingBookingid(keytype, "G", mainId);
}
	}
	
	 public String extractingmessgaeunsetgroupleader()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			
			String unsetgroupleaderString;
			int res=responseunsetgroupleader.getStatus();
	
			unsetgroupleaderString = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			//getJSONObject("currencycode").toString();
			System.out.println("at last confirmtempbookings"+res);
			return unsetgroupleaderString;
	 }

	

}
