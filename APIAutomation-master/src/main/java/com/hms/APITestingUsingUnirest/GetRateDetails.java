package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetRateDetails {
	String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 String roomTypeCode="";
	 String rateCode="";
	 String checkin="";
	 String checkout="";
	public  HttpResponse<JsonNode> responsegetratedetail;
	
	public void getratedetails(String key)
	{
		keytype = key;
		 
		 if(keytype == "wsauth")
			{
				System.out.println("hello if getratedetails:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in getratedetails:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in getratedetails:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 serverurl = CommonConfig.serverurl;
			 hotelid1= CommonConfig.hotelid1;
			 roomTypeCode=CommonConfig.roomtypecode1hotelid1;
			 rateCode=CommonConfig.ratecode1hotelid1;
			 checkin=CommonConfig.currentsystemdate;
			 checkout=CommonConfig.immediatenextdate;
			 System.out.println("hello:: accesskey in getratedetails:"+accesskey);
			 
			 responsegetratedetail = Unirest.post(""+serverurl+"/ws/web/getratedetails")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebd1bb38-292b-54ad-c70f-f226cab49295")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"getratedetails\",\"key\":\""+accesskey+"\",\"languagecode\":\"en\",\"data\":{\"stay\":{\"checkindate\":\""+checkin+"\",\"checkoutdate\":\""+checkout+"\"},\"searchrate\":{\"roomtypecode\":\""+roomTypeCode+"\",\"ratecode\":\""+rateCode+"\",\"hotel\":"+hotelid1+"},\"pax\":{\"adult\":\"1\",\"child\":\"0\",\"infant\":\"0\"},\"hotels\":[{\"id\":"+hotelid1+"}],\"roomrequire\":1,\"limit\":{\"value\":20,\"offset\":0}}}}}")
					  .asJson();
			 
			 JsonNode body = responsegetratedetail.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		 
	}
	
	 public int extractingmessgaegetratedetails()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			
			String getratedetailsString;
			int res=responsegetratedetail.getStatus();
			
			//getJSONObject("currencycode").toString();
			System.out.println("at last getallowednights:"+res);
			return res;
	 }

	

}
