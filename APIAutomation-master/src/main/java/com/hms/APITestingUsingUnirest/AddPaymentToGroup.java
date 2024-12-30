package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class AddPaymentToGroup {
	
	String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 HttpResponse<JsonNode> responseaddpaymenttogroup ;
	 String nightauditdate3;
	 String nightauditdate4;
	 String mainId="";
	 String guestId="";
	
	public void addpaymenttogroup(String key)
	{
		keytype = key;
		 
		 if(keytype == "wsauth")
			{
				System.out.println("hello if addpaymenttogroup:");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in addpaymenttogroup:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in addpaymenttogroup:"+keyl);
				accesskey = keyl;
			}
		 
		// Getbookings getbookingsObj=new Getbookings();
			//getbookingsObj.Getbookingscall(keytype, "BLOCKED");
			
		//String mainId=	getbookingsObj.extractingmainid();
		Editbooking editbookingObj=new Editbooking();
		editbookingObj.EditbookingcallforConfirmBookingid(keytype, "true", "G");
		mainId=editbookingObj.extracttinggroupid();
		System.out.println("GroupID::"+mainId);
		guestId=editbookingObj.extractingdetailsId();
		System.out.println("guestId::"+guestId);
		
		 serverurl = CommonConfig.serverurl;
		 
		 System.out.println("hello:: accesskey in addpaymenttogroup:"+accesskey);
		 
try {
	responseaddpaymenttogroup= Unirest.post(""+serverurl+"/ws/web/addpaymenttogroup")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebd1bb38-292b-54ad-c70f-f226cab49295")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"addpaymenttogroup\",\"key\":\""+accesskey+"\",\"data\":{\"id\":\""+mainId+"\",\"guestId\":\""+guestId+"\",\"paymentMode\":\"cc\",\"paymentType\":\"Visa\",\"amount\":150,\"creditCardDetails\":{\"nameOnCard\":\"Susuil. R\",\"cardNo\":\"4111111111111111\",\"expiryMonth\":12,\"expiryYear\":2027,\"cvc\":\"100\"}}}}}")
					  .asJson();
		 JsonNode body = responseaddpaymenttogroup.getBody();

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
	
	 public String extractingmessgaeaddpaymenttogroup()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			
			String addpaymenttogroupString;
			int res=responseaddpaymenttogroup.getStatus();
	
			addpaymenttogroupString = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			//getJSONObject("currencycode").toString();
			System.out.println("at last addpaymenttogroup"+res);
			return addpaymenttogroupString;
	 }



}
