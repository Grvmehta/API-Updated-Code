package com.hms.APITestingUsingUnirest;

import java.util.ArrayList;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;



public class ChangeRoomTypes {
	
	static String keytype;
	static String accesskey; 
	static String serverurl;
	static String msg;
	static String responseJSONString;
	static String Bookingid;
    String editbookingid;
    String roomtypeid;
    String editbookingid2;
  
    ArrayList<String> arr=new ArrayList<String>();
	
	
	public void ChangeRoomTypesAPIgroup(String s1){
		
		keytype = s1;
		System.out.println("keytype:"+s1);
		
		if(keytype == "wsauth")
		{
			/*Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
			System.out.println("hello if"+ accesskey);*/
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey for changeroomtype:"+keyw);
			
			
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("Login Acccess Key for Change Room Type"+keyl);
			accesskey = keyl;
		}
		
		try
		{
		
			//Creating group reservation 
			Confirmbooking confirmbookingobj = new Confirmbooking();
			confirmbookingobj.confirmbookingforgroupresv("login" , "2");
			String groupcode = confirmbookingobj.extractinggroupcode();
			String Bookingid2= confirmbookingobj.extracinggroupid();
			System.out.println(Bookingid2);
			//Extracting group code
			System.out.println("Welcome GetGroup API");
			Getgroup getgroupobj = new Getgroup();
			getgroupobj.Getgroupcall("login", groupcode);
			
			//Extracting Booking Id 
		   Bookingid=  getgroupobj.extractinggetgroupidmain();
		    //Extracting RoomType Id 
			roomtypeid = Getgroup.roomtypeid;
			
			
			System.out.println("Welcome EditBooking API");
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.fn_editbookingcallforexistingbookingid("login",Bookingid , "G");
			
			editbookingid = editbookingobj.extractingtempid();
			editbookingid2 = editbookingobj.extractingSecondtempid();
			
			System.out.println("Welcome GetRoomTypesToChange API");
			Getroomtypestochange getroomtypestochangeobj = new Getroomtypestochange();
			getroomtypestochangeobj.Getroomtypestochangecall("login");
		    arr = getroomtypestochangeobj.extractingroomtypes();
			arr.remove(roomtypeid);
			String changeroomtypeid=arr.get(0);
			String changeroomtypeid1=arr.get(1);
					
			serverurl = CommonConfig.serverurl;

			HttpResponse<JsonNode> responseeditbooking = Unirest.post(""+serverurl+"/ws/web/changeroomtypes")
                .header("content-type", "application/json")
                .header("x-ig-sg", "D_gg%fkl85_j")
                .header("cache-control", "no-cache")
                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
                .body("{"
                		+ "\"hotelogix\": {"
                		+ "\"version\": \"1.0\","
                		+ "\"datetime\": \"2012-01-16T10:10:15\","
                		+ "\"request\": {"
                		+ "\"method\": \"changeroomtypes\","
                		+ "\"key\": \""+accesskey+"\","
                		+ "\"data\": {"
                		+ "\"bookings\":  ["
                		+ "{"
                		+ "\"id\": \""+editbookingid+"\","
                		+ "\"roomTypeId\": \""+changeroomtypeid+"\""
                		+ "},"
                		+ "{"
                		+ "\"id\": \""+editbookingid2+"\","
                		+ "\"roomTypeId\": \""+changeroomtypeid1+"\""
                		+ "}"
                		+ "]"
                		+ "}}}}")
                     .asJson();
		 JsonNode body=responseeditbooking.getBody();
		 System.out.println(body);
		 //Assert.assertEquals(response.getCode(), 200);
		 responseJSONString = body.toString();
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
		finally{
			  System.out.println("Call ChangeRoomType finally block ::");
				Commiteditbooking commiteditbookingobj = new Commiteditbooking();
				commiteditbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", Bookingid);
			}
		
}
	public String extractingmessagegetchaneroomtypemsg()
	{
		
		
			String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String getchangeroomtypestring;
			getchangeroomtypestring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last changeroomtype success :"+getchangeroomtypestring);
			return getchangeroomtypestring;
		
		
	
	}
}
		

	
	
	
