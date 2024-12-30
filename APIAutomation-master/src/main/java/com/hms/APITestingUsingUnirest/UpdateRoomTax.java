package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UpdateRoomTax {
	
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	public static String roomTypeID;
	public static String amenityID;
	
	public void updateRoomTaxcall(String s)
	{
		/*keytype = s;
		
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
*/		try
		{
			System.out.println("hello try UpdateRoomTax");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			
		      hotelid1=CommonConfig.hotelid1;
		    //  amenityName=ApiUtils.GA().generateRandomString();
		      GetAmenity getAmenityObj=new GetAmenity();
		      getAmenityObj.getAmenitycall(s);
		      amenityID= getAmenityObj.extractingAmenityId();
		      GetRoomType getRoomTypeObj=new GetRoomType();
		      getRoomTypeObj.getRoomTypecall(s);
		      roomTypeID= getRoomTypeObj.extractingRoomTypeId();
		      accesskey=getRoomTypeObj.accesskey;
		   //  String hotelID= getAmenityObj.extractingHotelId();
		     System.out.println("Access key is::"+accesskey);
			
			HttpResponse<JsonNode> responseUpdateRoomTax = Unirest.post(""+serverurl+"/ws/web/updateroomtax")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebf56e64-e308-b8b7-bd77-65d49111c1e0")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"updateroomtax\",\"key\":\""+accesskey+"\",\"data\":{\"taxes\":{\"id\":29925,\"code\":\"abC\",\"amount\":500,\"tariffLessThan\":1000,\"chargeType\":\"PV\",\"type\":\"RR\",\"name\":[{\"langCode\":\"en\",\"value\":\"APITax\"}]}}}}}")
					  .asJson();
			JsonNode body = responseUpdateRoomTax.getBody();
			System.out.println("UpdateRoomTax response::"+body);	
			responseJSONString = body.toString();	
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// end of Createagentcall() method
	
	public String extractingmessageUpdateRoomTax()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String updateRoomTaxsuccessstring;
		updateRoomTaxsuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		
		System.out.println("last CreateAmenity success"+updateRoomTaxsuccessstring);
		return updateRoomTaxsuccessstring;
	}
	



}
