package com.hms.APITestingUsingUnirest;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Getspdiscount {
	String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 HttpResponse<JsonNode> responsegetspdiscount ;
	 String nightauditdate3;
	 String nightauditdate4;
	 
	
	public void getspdiscount(String key)
	{
		keytype = key;
		 
		 if(keytype == "wsauth")
			{
				System.out.println("hello if getspdiscount:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in getspdiscount:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in getspdiscount:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 nightauditdate3 = CommonConfig.immediatenextdate;
				nightauditdate4 = CommonConfig.immediatenexttonextdate;
				System.out.println(nightauditdate3);
				System.out.println(nightauditdate4);
			 serverurl = CommonConfig.serverurl;
			 
			 System.out.println("hello:: accesskey in getspdiscount:"+accesskey);
			 
		 responsegetspdiscount = Unirest.post(""+serverurl+"/ws/web/getspdiscount")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebd1bb38-292b-54ad-c70f-f226cab49295")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"getspdiscount\",\"key\":\""+accesskey+"\"}}}")
					  .asJson();
			 
			 JsonNode body = responsegetspdiscount.getBody();
		
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		 
	}
	
	 public String extractingmessgaegetspdiscount()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			
			String getspdiscountString;
			getspdiscountString = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last getcheckins success:"+getspdiscountString);
			return getspdiscountString;
	 }

	 public String extractingSpdiscountId()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String getRoomTaxsuccessstring;
			getRoomTaxsuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			JSONObject newresp = jsonResult.getJSONObject("hotelogix");
			JSONObject resp= newresp.getJSONObject("response");
			JSONObject taxes=resp.getJSONArray("hotels").getJSONObject(0);	
			String id=taxes.getJSONArray("specialdiscounts").getJSONObject(0).get("id").toString();
			
			//String id=taxes.get("id").toString();
			System.out.println("The id is:" +id);
			System.out.println("last success"+getRoomTaxsuccessstring);
			return id;
	 }
	 
	 public String extractingRequirementID()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String getRoomTaxsuccessstring;
			getRoomTaxsuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			JSONObject newresp = jsonResult.getJSONObject("hotelogix");
			JSONObject resp= newresp.getJSONObject("response");
			JSONObject taxes=resp.getJSONArray("hotels").getJSONObject(0);	
			String id=taxes.getJSONArray("specialdiscounts").getJSONObject(0).getJSONArray("requirements").getJSONObject(0).get("id").toString();
			
			//String id=taxes.get("id").toString();
			System.out.println("The requirement id is:" +id);
			System.out.println("last success"+getRoomTaxsuccessstring);
			return id;
	 }
	 
}
