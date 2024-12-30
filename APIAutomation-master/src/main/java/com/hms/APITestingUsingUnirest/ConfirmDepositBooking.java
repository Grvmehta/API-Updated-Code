package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ConfirmDepositBooking {
	
	String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 HttpResponse<JsonNode> responseConfirmDepositBooking ;
	 String nightauditdate3;
	 String nightauditdate4;
	 String mainId="";
	 String code;
	
	public void confirmDepositBookingCall(String key)
	{
		keytype = key;
		 
		 if(keytype == "wsauth")
			{
				System.out.println("hello if ConfirmDepositBooking:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in ConfirmDepositBooking"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in ConfirmDepositBooking:"+keyl);
				accesskey = keyl;
			}
		 
		// Getbookings getbookingsObj=new Getbookings();
			//getbookingsObj.Getbookingscall(keytype, "BLOCKED");
			
		 //mainId=	getbookingsObj.extractingmainid();
		/*Editbooking editbookingObj=new Editbooking();
		editbookingObj.Editbookingcallforsingleid(keytype, "S", "RESERVE");
		code=editbookingObj.extractingCode();
		mainId=	editbookingObj.extractingtempid();
		System.out.println(code);*/
		
		serverurl = CommonConfig.serverurl;
		 hotelid1=CommonConfig.hotelid1;
		 System.out.println("hello:: accesskey in confirmdepositbooking:"+accesskey);
		 
		 Confirmbooking confirmbookingObj=new Confirmbooking();
		 confirmbookingObj.Confirmbookingcall(keytype);
		 code=confirmbookingObj.extracingbookingCode();
		 Getpaytype getpaytypeObj=new Getpaytype();
		 getpaytypeObj.Getpaytypecall(keytype);
		 //getpaytypeObj.extractingmessagegetpaytype();
		 System.out.println("Values getting passed in request::::"+"value of code is:::"+code+"   value of accesskey is::::::"+accesskey+"     hotel id is:::"+hotelid1);
	
try {
	responseConfirmDepositBooking= Unirest.post(""+serverurl+"/ws/web/confirmdepositbooking")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebd1bb38-292b-54ad-c70f-f226cab49295")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2020-11-17T09:32:45\",\"request\":{\"method\":\"confirmdepositbooking\",\"key\":\""+accesskey+"\",\"data\":{\"code\":\""+code+"\",\"type\":\"S\",\"paymentMode\":\"cc\",\"paymentType\":\"Visa\",\"amount\":\"31.800000\",\"creditCardDetails\":{\"nameOnCard\":\"SS\",\"cardNo\":\"4111111111111111\",\"cardtype\":\"Visa\",\"expiryMonth\":\"12\",\"expiryYear\":\"2029\",\"cvc\":\"123\",\"pciToken\":\"\"},\"hotels\":[{\"id\":\""+hotelid1+"\"}]},\"languagecode\":\"en\"}}}")
					  .asJson();

		 
		 JsonNode body = responseConfirmDepositBooking.getBody();
		 System.out.println(responseConfirmDepositBooking.getStatus());
		 System.out.println(responseConfirmDepositBooking.getStatusText());
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
	 commiteditbookingObj.CommiteditBookingCallForExistingBookingid(keytype, "S", mainId);
}
	}
	
	 public String extractingMessageConfirmDepositBooking()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			
			String confirmDepositBookingString;
			int res=responseConfirmDepositBooking.getStatus();
	
			confirmDepositBookingString = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			//getJSONObject("currencycode").toString();
			System.out.println("at last confirmtempbookings"+res);
			return confirmDepositBookingString;
	 }



}
