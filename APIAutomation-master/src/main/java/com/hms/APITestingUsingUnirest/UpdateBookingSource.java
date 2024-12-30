package com.hms.APITestingUsingUnirest;

import java.io.IOException;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UpdateBookingSource {

	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String bookingid;
	String businesssourceid;
	String restype;
	String Bookinsourcetype;
	String groupid;
	String groupcode;
	public void fn_UpdateBookingSource(String s1, String s2, String s3) throws IOException
	{
		keytype = s1;
		restype = s2;
		Bookinsourcetype = s3;
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
			System.out.println("wsauthkey in gethoteldata:"+keyw);
		}
		
		else if(keytype == "login")
		{
			/*Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			System.out.println("login key in gethousestatus:"+keyl);
			accesskey = keyl;*/
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in gethoteldata:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			
			if(Bookinsourcetype == "GetBooking"){		
			
			Getbooking getbookingobj = new Getbooking();
			getbookingobj.getbookingcall("login");
			bookingid = getbookingobj.extractingbookingid();
			Editbooking editbookingidobj = new Editbooking();
			editbookingidobj.fn_editbookingcallforexistingbookingid("login", bookingid, "S");
			bookingid = editbookingidobj.extractingtempid();
			} 
			
			
			else if(Bookinsourcetype == "Single"){
			Editbooking editbookingidobj = new Editbooking();
			editbookingidobj.EditbookingcallforReserveGuest("login", "S");
			bookingid =  editbookingidobj.extractingtempid();
			}
			

			else if(Bookinsourcetype == "Group"){
			Editbooking editbookingidobj = new Editbooking();
			editbookingidobj.EditbookingcallforReserveGuest("login", "G");
			bookingid =  editbookingidobj.extracttinggroupid();
			}
			
			else if(Bookinsourcetype == "GroupCode"){
				
			Getbookings getbookingsobj = new Getbookings();
			getbookingsobj.Getbookingscallforreserveguest("login");
			groupcode = getbookingsobj.extractinggroupcode();
			
			Getgroup getgroupobj = new Getgroup();
		    getgroupobj.Getgroupcall("login", groupcode);
		    groupid = getgroupobj.extractingGOUPPID();
			Editbooking editbookingidobj = new Editbooking();
			editbookingidobj.fn_editbookingcallforexistingbookingid("login", groupid, "G");
			bookingid =  editbookingidobj.extracttinggroupid();
				
			}
			else if(Bookinsourcetype == "GroupSingleresevation"){
				Editbooking editbookingidobj = new Editbooking();
				editbookingidobj.EditbookingcallforReserveGuest("login", "G");
			 groupid=editbookingidobj.extracttinggroupid();
				bookingid =  editbookingidobj.extractingtempid();
			}
			
			else if(Bookinsourcetype == "GroupSingleresevationwithmainbookingid"){
				
				Getbookings getbookingsobj = new Getbookings();
				getbookingsobj.Getbookingscallforreserveguest("login");
				groupcode = getbookingsobj.extractinggroupcode();
				
				Getgroup getgroupobj = new Getgroup();
			    getgroupobj.Getgroupcall("login", groupcode);
			    bookingid = getgroupobj.extractinggetbookingid();
				
			}
			
			Gethoteldata gethoteldataobj = new Gethoteldata();
			gethoteldataobj.Gethoteldatacall("login");
			businesssourceid =  gethoteldataobj.extractingbusinesssourceid();
			 
			
			
			HttpResponse<JsonNode> updatebookingsource = Unirest.post(""+serverurl+"/ws/web/updatebookingsource")
					    .header("content-type", "application/json")
		                .header("x-ig-sg", "D_gg%fkl85_j")
		                .header("cache-control", "no-cache")
		                .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
		                .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"updatebookingsource\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"type\": \""+restype+"\","
		                		+ "\"isMain\": false,"
		                		+ "\"bookings\": ["
		                		+ "{"
		                		+ "\"id\": \""+bookingid+"\","
		                		+ "\"businessSourcesId\": \""+businesssourceid+"\""		
		                		+ "}"
	                      		+ "]}}}}")
		                .asJson();
		                       
			JsonNode body = updatebookingsource.getBody();
			responseJSONString = body.toString();
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
		finally{
			 Commiteditbooking  commiteditbookingobj = new Commiteditbooking();
			 commiteditbookingobj.CommiteditBookingCallForExistingBookingid("login", restype, groupid);
		}
	}//End of Gethoteldatacall() method
	
	public String extractingmessagegeupdatebookingsource()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String getupdatebookingsource;
		getupdatebookingsource = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("Update Booking Source success:"+getupdatebookingsource);
		return getupdatebookingsource;
	}//End of extractingmessagegethoteldata() method


}
