package com.hms.APITestingUsingUnirest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.hms.APITestingUsingUnirest.CommonConfig;


public class Getroomstoassign
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String searchstring;
	String nightauditdate;
	String nightauditdate1;
	String nightauditdate2;
	 static String bookingtempid;
	 String editbookigid;
	static String roomid;
	 String gueststayid;
	String roomtypeid;
	private String bookingid;
	private String resvtype;
	String Isgroupbookingvalue;
	static String groupid;
	
	String paymentID="";
	public void getroomstoassigncall(String s1){
		keytype = s1;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in getroomstoassign:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getroomstoassign:"+keyl);
			accesskey = keyl;	
		}
		 try{
			    serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				nightauditdate1 = CommonConfig.currentsystemdate;
				nightauditdate2=CommonConfig.immediatenextdate;
				System.out.println("hello try"+accesskey);
				Editbooking editbookingobj=new Editbooking();
				editbookingobj.EditbookingcallforReserveGuest(keytype,"S");
				editbookigid=editbookingobj.extractingtempid();
				
				System.out.println("edit booking id from editbooking is: "+editbookigid);
				HttpResponse<JsonNode> responsegetroomstoassign = Unirest.post(""+serverurl+"/ws/web/getroomstoassign")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "ebd5b695-484f-a7ab-e2f7-688168c9a59f")
						  .body("{"
			                		+ "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2012-01-16T10:10:15\","
			                		+ "\"request\": {"
			                		+ "\"method\": \"getroomstoassign\","
			                		+ "\"key\": \""+accesskey+"\","
			                		+ "\"data\": {"
			                		+ "\"bookings\": ["
			                		+ "{"
			                		+ "\"id\": \""+editbookigid+"\","
			                		+ "\"fromDate\": \""+nightauditdate1+"\","
			                		+ "\"toDate\": \""+nightauditdate2+"\""
			                		+ "}]}}}}")
			                     .asJson();
				JsonNode body = responsegetroomstoassign.getBody();
				responseJSONString = body.toString();
				System.out.println("Response of getroomstoassign");
				System.out.println(responseJSONString);
			}
			
			catch(UnirestException e)
			{
				e.printStackTrace();
			}	
		 finally{

		       Commiteditbooking commitbookingobj = new Commiteditbooking();
		       commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", editbookigid );

		} 
	}
	public void getroomstoassigncallForexistingid(String s1,String s2,String s3){
		keytype = s1;
		bookingid=s2;
		resvtype=s3;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in getroomstoassign:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getroomstoassign:"+keyl);
			accesskey = keyl;	
		}
		 try{
			    serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				nightauditdate1 = CommonConfig.currentsystemdate;
				nightauditdate2=CommonConfig.immediatenexttonextdate;
				System.out.println("hello try"+accesskey);
				Editbooking editbookingobj=new Editbooking();
				editbookingobj.fn_editbookingcallforexistingbookingid("login", bookingid,resvtype);
				editbookigid=editbookingobj.extractingtempid();
				
				System.out.println("edit booking id from editbooking is: "+editbookigid);
				HttpResponse<JsonNode> responsegetroomstoassign = Unirest.post(""+serverurl+"/ws/web/getroomstoassign")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "ebd5b695-484f-a7ab-e2f7-688168c9a59f")
						  .body("{"
			                		+ "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2012-01-16T10:10:15\","
			                		+ "\"request\": {"
			                		+ "\"method\": \"getroomstoassign\","
			                		+ "\"key\": \""+accesskey+"\","
			                		+ "\"data\": {"
			                		+ "\"bookings\": ["
			                		+ "{"
			                		+ "\"id\": \""+editbookigid+"\","
			                		+ "\"fromDate\": \""+nightauditdate1+"\","
			                		+ "\"toDate\": \""+nightauditdate2+"\""
			                		+ "}]}}}}")
			                     .asJson();
				JsonNode body = responsegetroomstoassign.getBody();
				responseJSONString = body.toString();
				System.out.println("Response of getroomstoassign");
				System.out.println(responseJSONString);
			}
			
			catch(UnirestException e)
			{
				e.printStackTrace();
			}	
		 finally{

		       Commiteditbooking commitbookingobj = new Commiteditbooking();
		       commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", editbookigid );

		} 
	}
	public void getroomstoassigncallfromconfirmbooking(String s1, String s2){
		
		keytype = s1;
		Isgroupbookingvalue = s2;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in getroomstoassign:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getroomstoassign:"+keyl);
			accesskey = keyl;	
		}
		 try{
			    serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				nightauditdate1 = CommonConfig.currentsystemdate;
				nightauditdate2=CommonConfig.immediatenextdate;
				System.out.println("hello try"+accesskey);
				
				if(Isgroupbookingvalue == "false"){
				Editbooking editbookingobj=new Editbooking();
				editbookingobj.EditbookingcallforConfirmBookingid(keytype, Isgroupbookingvalue, "S");
				paymentID=	editbookingobj.extractingPaymentItemID();
				System.out.println("Payment Id in assign rooms::::"+paymentID);
				editbookigid=editbookingobj.extractingtempid();
				gueststayid=editbookingobj.extractingguestStayId();
				}
				
				if(Isgroupbookingvalue == "true"){
					Editbooking editbookingobj=new Editbooking();
					editbookingobj.EditbookingcallforConfirmBookingid(keytype, Isgroupbookingvalue, "G");
					editbookigid=editbookingobj.extractingtempid();
					gueststayid=editbookingobj.extractingguestStayId();
					groupid = editbookingobj.extracttinggroupid();
				}
				HttpResponse<JsonNode> responsegetroomstoassign = Unirest.post(""+serverurl+"/ws/web/getroomstoassign")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "ebd5b695-484f-a7ab-e2f7-688168c9a59f")
						  .body("{"
			                		+ "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2012-01-16T10:10:15\","
			                		+ "\"request\": {"
			                		+ "\"method\": \"getroomstoassign\","
			                		+ "\"key\": \""+accesskey+"\","
			                		+ "\"data\": {"
			                		+ "\"bookings\": ["
			                		+ "{"
			                		+ "\"id\": \""+editbookigid+"\","
			                		+ "\"fromDate\": \""+nightauditdate1+"\","
			                		+ "\"toDate\": \""+nightauditdate2+"\""
			                		+ "}]}}}}")
			                     .asJson();
				JsonNode body = responsegetroomstoassign.getBody();
				responseJSONString = body.toString();
				System.out.println("Response of getroomstoassign");
				System.out.println(responseJSONString);
			}
			
			catch(UnirestException e)
			{
				e.printStackTrace();
			}	
		
	}
	public void getroomstoassigncall(String s1, String s2)
	{
		keytype = s1;
		bookingtempid = s2;
		
		System.out.println("keytype:"+keytype);
		System.out.println("bookingtempid:"+bookingtempid);
		
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);*/
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in getroomstoassign:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in getroomstoassign:"+keyl);
			accesskey = keyl;	
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			nightauditdate1  = CommonConfig.currentsystemdate;
			nightauditdate2 = CommonConfig.immediatenextdate;
			
			HttpResponse<JsonNode> responsegetroomstoassign = Unirest.post(""+serverurl+"/ws/web/getroomstoassign")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebd5b695-484f-a7ab-e2f7-688168c9a59f")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getroomstoassign\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n        \"bookings\": [\r\n          {\r\n            \"id\": \""+bookingtempid+"\",\r\n            \"fromDate\": \""+nightauditdate1+"\",\r\n            \"toDate\": \""+nightauditdate2+"\"\r\n          }\r\n        ]\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			
			JsonNode body = responsegetroomstoassign.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}	
	}
	//End of getroomstoassigncall() method
	public String extractingmessagegetroomstoassign()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getroomstoassign;
		getroomstoassign = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last getbookings success:"+getroomstoassign);
		return getroomstoassign;
	}
	
	public String extractingroomid()
	{
		System.out.println("welcome to getroomstoassign for roomid");
		
		
		String localresponseJSONString = responseJSONString;	
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray availableroomArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("availableRooms");
		JSONObject rooms = availableroomArray.getJSONObject(0);
		JSONArray roomsArray = rooms.getJSONArray("rooms");
		int room=roomsArray.length()-1;
		roomid = roomsArray.getJSONObject(room).getString("id");
		System.out.println("roomid:"+roomid);
		return roomid;
	} 
	
	public String extractingunoccupiedroomid()
	{
		System.out.println("welcome to getroomstoassign for roomid");
		
		
		String localresponseJSONString = responseJSONString;	
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		JSONArray availableroomArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("availableRooms");
		JSONObject rooms = availableroomArray.getJSONObject(0);
		JSONArray roomsArray = rooms.getJSONArray("rooms");
		for (int i = 0; i <  roomsArray.length(); i++) {                                       
	        boolean value= roomsArray .getJSONObject(i).getBoolean("occupied");       		
	        if(value == false){
	        	roomid = roomsArray.getJSONObject(i).getString("id");
	        break;
	        }
	        }
		return roomid;
	}
	
	//End of extractingroomid() method
	public String extractingroomtypeid()
	{
	System.out.println("welcome to getroomstoassign for roomid");
	String roomid;
	String localresponseJSONString = responseJSONString;
	JSONObject jsonResult = new JSONObject(localresponseJSONString);
	JSONArray availableroomArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("availableRooms");
	roomtypeid = availableroomArray.getJSONObject(0).getString("roomTypeId");
	System.out.println("roomid:"+roomtypeid);
	return roomtypeid;
	} 
}// End of the class
