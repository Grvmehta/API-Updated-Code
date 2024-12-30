package com.hms.APITestingUsingUnirest;

import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ChangeRateforCart {
	
	String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 HttpResponse<JsonNode> responseChangeRateforCart ;
	 String nightauditdate3;
	 String nightauditdate4;
	 String mainId="";
	 String code;
	 String rateId;
	
	public void changeRateforCartCall(String key)
	{
		keytype = key;
		 
		 if(keytype == "wsauth")
			{
				System.out.println("hello if ChangeRateforCart:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in ChangeRateforCart"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in ChangeRateforCart:"+keyl);
				accesskey = keyl;
			}
		/* 
		 Getbookings getbookingsObj=new Getbookings();
		getbookingsObj.Getbookingscall(keytype, "RESERVE");
			
		 mainId=	getbookingsObj.extractingmainid();
		 
		 
		Editbooking editbookingObj=new Editbooking();
		editbookingObj.Editbookingcallforsingleid(keytype, "S", "RESERVE");
		//code=editbookingObj.extractingCode();
		mainId=	editbookingObj.extractingtempid();
		//System.out.println(code);
		 
*/		
		 Loadcart loadcartobj = new Loadcart();
		 loadcartobj.Loadcartcall(keytype);
		 //String s1 = loadcartobj.extractingmessageloadcart();
		 mainId = loadcartobj.extracingbookingid();
		 
		 System.out.println(mainId);
		 rateId=loadcartobj.extractingRateId();

		serverurl = CommonConfig.serverurl;
		 hotelid1=CommonConfig.hotelid1;
		 System.out.println("hello:: accesskey in ChangeRateforCart:"+accesskey);
		 nightauditdate3=CommonConfig.immediatenextdate;
		 nightauditdate4=CommonConfig.currentsystemdate;
		/* Getratestochange getratestochangeObj=new Getratestochange();
		 getratestochangeObj.Getratestochangecallforexistingbookingid(keytype,mainId);
		 List<String> listObj=getratestochangeObj.extractingrateid();
		 Iterator<String> iObj=listObj.iterator();
		String rateId=iObj.next();
		System.out.println("rateId:::"+rateId);
		System.out.println(iObj.next());*/
try {
	responseChangeRateforCart= Unirest.post(""+serverurl+"/ws/web/changerateforcart")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebd1bb38-292b-54ad-c70f-f226cab49295")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2020-11-17T09:36:40\",\"request\":{\"method\":\"changerateforcart\",\"key\":\""+accesskey+"\",\"data\":{\"bookings\":[{\"id\":\""+mainId+"\",\"rateId\":\""+rateId+"\",\"fromDate\":\""+nightauditdate3+"\",\"toDate\":\""+nightauditdate4+"\",\"searchForType\":\"FDESK\"}]}}}}")
					  .asJson();

		 
		 JsonNode body = responseChangeRateforCart.getBody();
		 System.out.println(responseChangeRateforCart.getStatus());
		 System.out.println(responseChangeRateforCart.getStatusText());
		 responseJSONString = body.toString();
		 System.out.println(responseJSONString);
}
		 catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}
	
	 public String extractingMessageChangeRateforCart()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			
			String changeRateforCartString;
			int res=responseChangeRateforCart.getStatus();
	
			changeRateforCartString = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			//getJSONObject("currencycode").toString();
			System.out.println("at last changeRateforCartString::"+changeRateforCartString);
			return changeRateforCartString;
	 }




}
