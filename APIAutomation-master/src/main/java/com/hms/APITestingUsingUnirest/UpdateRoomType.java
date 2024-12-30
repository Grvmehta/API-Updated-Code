package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;

import org.json.JSONObject;

import com.hms.APITestingUsingUnirest.Generic.ApiUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UpdateRoomType {
	
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	public static String roomTypeID;
	public static String amenityID;
	
	public void updateRoomTypecall(String s)
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
			System.out.println("hello try UpdateRoomType");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			//String uniqueID = UUID.randomUUID().toString();
			//String email = uniqueID+"hotelogix.com";
			//String str1 = uniqueID.substring(0,9);
			//System.out.println("substring"+str1);
			// Get the current date and time
		      LocalDateTime currentTime = LocalDateTime.now();
		      System.out.println("Current DateTime: " + currentTime);
		      int year = Year.now().getValue()+1;
		      System.out.println(year);
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
			
			HttpResponse<JsonNode> responseUpdateRoomType = Unirest.post(""+serverurl+"/ws/web/updateroomtype")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebf56e64-e308-b8b7-bd77-65d49111c1e0")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"updateroomtype\",\"key\":\""+accesskey+"\",\"data\":{\"roomTypes\":{\"id\":\""+roomTypeID+"\",\"code\":\"sd\",\"basePrice\":5000,\"upCharge\":300,\"extraBedPrice\":300,\"higherOccupancy\":6,\"extraBed\":1,\"baseOccupancy\":1,\"amenities\":[{\"id\":\""+amenityID+"\"}],\"name\":[{\"langCode\":\"en\",\"value\":\"APIRoomType\"}]}}}}}")
					  .asJson();
			JsonNode body = responseUpdateRoomType.getBody();
			System.out.println("UpdateRoomType response::"+body);	
			responseJSONString = body.toString();	
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// end of Createagentcall() method
	
	public String extractingmessageUpdateRoomType()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String updateRoomTypesuccessstring;
		updateRoomTypesuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		
		System.out.println("last CreateAmenity success"+updateRoomTypesuccessstring);
		return updateRoomTypesuccessstring;
	}
	


}
