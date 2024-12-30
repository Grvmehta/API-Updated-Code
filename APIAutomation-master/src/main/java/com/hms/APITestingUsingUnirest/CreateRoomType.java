package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;

import org.json.JSONObject;

import com.hms.APITestingUsingUnirest.Generic.ApiUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CreateRoomType {
	
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	public static String AgentID;
	public static String roomTypeName;
	
	public void CreateRoomTypecall(String s)
	{
		keytype = s;
	/*	
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
		}*/
		try
		{
			System.out.println("hello try CreateRoomType");
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
		      
		      GetAmenity getAmenityObj=new GetAmenity();
		      getAmenityObj.getAmenitycall(s);
		      String amenityId=getAmenityObj.extractingAmenityId();
		      accesskey=getAmenityObj.accesskey;
		      roomTypeName=ApiUtils.GA().generateRandomString();
		      //roomTypeName="Api Room: "+roomTypeName;
		      
			HttpResponse<JsonNode> responseCreateRoomType = Unirest.post(""+serverurl+"/ws/web/createroomtype")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebf56e64-e308-b8b7-bd77-65d49111c1e0")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"createroomtype\",\"key\":\""+accesskey+"\",\"data\":{\"roomTypes\":{\"code\":\""+roomTypeName+"\",\"basePrice\":500,\"upCharge\":1000,\"extraBedPrice\":100,\"higherOccupancy\":5,\"extraBed\":2,\"baseOccupancy\":3,\"amenities\":[{\"id\":\""+amenityId+"\"}],\"name\":[{\"langCode\":\"en\",\"value\":\""+roomTypeName+"\"}]}}}}}")
					  .asJson();
			System.out.println(responseCreateRoomType.getStatus());
			JsonNode body = responseCreateRoomType.getBody();
			System.out.println("CreateRoom response::"+body);	
			responseJSONString = body.toString();	
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// end of Createagentcall() method
	
	public String extractingmessageCreateRoomType()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String createRoomTypesuccessstring;
		createRoomTypesuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		/*JSONObject newresp = jsonResult.getJSONObject("hotelogix");
		JSONObject resp= newresp.getJSONObject("response");
		JSONObject hotels=resp.getJSONArray("hotels").getJSONObject(0);	
		AgentID=hotels.get("agentId").toString();
		System.out.println("The agent id is:" +AgentID);*/
		System.out.println("last CreateAmenity success"+createRoomTypesuccessstring);
		return createRoomTypesuccessstring;
	}
	
	
	

	


}
