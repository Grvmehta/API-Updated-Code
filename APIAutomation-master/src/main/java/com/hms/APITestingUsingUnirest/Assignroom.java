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

public class Assignroom
{
static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String nightauditdate;
	String restype = "RESERVE";
	String bookingtype = "G";
	String searchkey;
	String editbookigid;
	String Roomid;
    String gueststayid;
    String saveorderId;
	String BookingCode;
	String GroupId;
    String Isgroupbookingvalue;
    String paymentID;
	
	public void assignroomcall(String s1){
		keytype = s1;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in Assignroom:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in Assignroom:"+keyl);
			accesskey = keyl;	
		}
		 try{
			    serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);
				/*Editbooking editbookingobj=new Editbooking();
				editbookingobj.EditbookingcallforReserveGuest(keytype,"S");
				editbookigid=editbookingobj.extractingtempid();*/
				Getroomstoassign Getroomstoassignobj=new Getroomstoassign();
				Getroomstoassignobj.getroomstoassigncall(keytype);
				editbookigid=Getroomstoassignobj.editbookigid;
				System.out.println("edit booking id from get room to assign is: "+editbookigid);
				Editbooking editbookingobj=new Editbooking();
				 editbookingobj.fn_editbookingcallforexistingbookingid("login", editbookigid, "S");
				
				 Roomid=Getroomstoassignobj.extractingroomid();
				System.out.println("Roomid is:"+Roomid);
				HttpResponse<JsonNode> responseassignrom = Unirest.post(""+serverurl+"/ws/web/assignroom")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "c52633bd-8abb-e05d-ec86-0641bf085b8f")
						  .body("{"
			                		+ "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2012-01-16T10:10:15\","
			                		+ "\"request\": {"
			                		+ "\"method\": \"assignroom\","
			                		+ "\"key\": \""+accesskey+"\","
			                		+ "\"data\": {"
			                		+ "\"bookings\": ["
			                		+ "{"
			                		+ "\"id\": \""+editbookigid+"\","
			                		+ "\"roomId\":\""+Roomid+"\""
			                		+ "}]}}}}")
			                     .asJson();
				JsonNode body = responseassignrom.getBody();
				responseJSONString = body.toString();
				System.out.println("Response of AssignRoom");
				System.out.println("assignroom:"+responseJSONString);
			}
			
			catch(UnirestException e)
			{
				e.printStackTrace();
			}
			
	}
	
	public void assignroomcallforExistngData(String s1,String s2,String s3,String s4){
		keytype = s1;
		editbookigid=s2;
		Roomid=s3;
		gueststayid=s4;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in Assignroom:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in Assignroom:"+keyl);
			accesskey = keyl;	
		}
		 try{
			    serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);
				
				
				HttpResponse<JsonNode> responseassignrom = Unirest.post(""+serverurl+"/ws/web/assignroom")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "c52633bd-8abb-e05d-ec86-0641bf085b8f")
						  .body("{"
			                		+ "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2012-01-16T10:10:15\","
			                		+ "\"request\": {"
			                		+ "\"method\": \"assignroom\","
			                		+ "\"key\": \""+accesskey+"\","
			                		+ "\"data\": {"
			                		+ "\"bookings\": ["
			                		+ "{"
			                		+ "\"id\": \""+editbookigid+"\","
			                		+ "\"roomId\":\""+Roomid+"\""
			                		+ "}]}}}}")
			                     .asJson();
				JsonNode body = responseassignrom.getBody();
				responseJSONString = body.toString();
				System.out.println("Response of AssignRoom");
				System.out.println("assignroom:"+responseJSONString);
			}
			
			catch(UnirestException e)
			{
				e.printStackTrace();
			}
		
	}
	public void assignroomcallfromconfirmbooking(String s1, String s2){
		keytype = s1;
		Isgroupbookingvalue = s2;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in Assignroom:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in Assignroom:"+keyl);
			accesskey = keyl;	
		}
		 try{
			    serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);
				if(Isgroupbookingvalue == "false"){
				 Getroomstoassign Getroomstoassignobj=new Getroomstoassign();
				 Getroomstoassignobj.getroomstoassigncallfromconfirmbooking(keytype, Isgroupbookingvalue);
		paymentID=		 Getroomstoassignobj.paymentID;
				 editbookigid= Getroomstoassignobj.editbookigid;
				 System.out.println("asn rom extract booking id:"+editbookigid);
				
				//Roomid=Getroomstoassignobj.extractingroomid();
				 Roomid=Getroomstoassignobj.extractingunoccupiedroomid();
				System.out.println("ASSIGN ROOM EXTRACT ROOM ID FROM GET ROM TO ASN:"+Roomid);
				gueststayid=Getroomstoassignobj.gueststayid;
				
				}
				if(Isgroupbookingvalue == "true"){
					Getroomstoassign Getroomstoassignobj=new Getroomstoassign();
					 Getroomstoassignobj.getroomstoassigncallfromconfirmbooking(keytype, Isgroupbookingvalue);
					 editbookigid= Getroomstoassignobj.editbookigid;
					 System.out.println("asn rom extract booking id:"+editbookigid);
					
					Roomid=Getroomstoassignobj.extractingunoccupiedroomid();
					System.out.println("ASSIGN ROOM EXTRACT ROOM ID FROM GET ROM TO ASN:"+Roomid);
					gueststayid=Getroomstoassignobj.gueststayid;
				}
				
				HttpResponse<JsonNode> responseassignrom = Unirest.post(""+serverurl+"/ws/web/assignroom")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "c52633bd-8abb-e05d-ec86-0641bf085b8f")
						  .body("{"
			                		+ "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2012-01-16T10:10:15\","
			                		+ "\"request\": {"
			                		+ "\"method\": \"assignroom\","
			                		+ "\"key\": \""+accesskey+"\","
			                		+ "\"data\": {"
			                		+ "\"bookings\": ["
			                		+ "{"
			                		+ "\"id\": \""+editbookigid+"\","
			                		+ "\"roomId\":\""+Roomid+"\""
			                		+ "}]}}}}")
			                     .asJson();
				JsonNode body = responseassignrom.getBody();
				responseJSONString = body.toString();
				System.out.println("Response of AssignRoom");
				System.out.println("assignroom:"+responseJSONString);
			}
			
			catch(UnirestException e)
			{
				e.printStackTrace();
			}
		
	}
	
	public void assignroomcall(String s1, String s2)
	{
		keytype = s1;
		searchkey = s2;
		System.out.println("keytype:"+s1);
		System.out.println("searchkey:"+s2);
		
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in addtocart:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in addtocart:"+keyl);
			accesskey = keyl;
		}
		
		try
		{
			serverurl = CommonConfig.serverurl;
			Editbooking editbookingobj = new Editbooking();
			editbookingobj.Editbookingcall(keytype, searchkey);
			String tempid = editbookingobj.extractingtempid();
			
			// we need roomid and thats why we need to call "getroomstoassign" API and get roomid
			Getroomstoassign getroomstoassignobj = new Getroomstoassign();
			getroomstoassignobj.getroomstoassigncall(keytype, tempid);
			String roomid = getroomstoassignobj.extractingroomid();
			System.out.println("roomid in assignroom:"+roomid);
			
			HttpResponse<JsonNode> responseassignrom = Unirest.post(""+serverurl+"/ws/web/assignroom")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "c52633bd-8abb-e05d-ec86-0641bf085b8f")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"assignroom\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n        \"bookings\": [\r\n          {\r\n            \"id\": \""+tempid+"\",\r\n            \"roomId\":\""+roomid+"\"\r\n          }\r\n        ]\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			
			JsonNode body = responseassignrom.getBody();
			responseJSONString = body.toString();
			System.out.println("assignroom:"+responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			Commiteditbooking commiteditbookingobj = new Commiteditbooking();
			commiteditbookingobj.Commiteditbookingcall(keytype, "S", "RESERVE");
		}
	}// End of assignroomcall() method
	public void assignroomcallForGrpReservation(String s1) {
		keytype = s1;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in Assignroom:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in Assignroom:"+keyl);
			accesskey = keyl;	
		}
		 try{
			    serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);	
				Confirmbooking Confirmbookingobj=new Confirmbooking();
				Confirmbookingobj.confirmbookingforgroupresv(keytype);
				saveorderId=Confirmbookingobj.orderid;
				BookingCode=Confirmbookingobj.extractinggroupcode();
				Getgroup Getgroupobj=new Getgroup();
				Getgroupobj.Getgroupcall(keytype, BookingCode);
				GroupId=Getgroupobj.extractinggetgroupidmain();
				Editbooking Editbookingobj=new Editbooking();
				Editbookingobj.fn_editbookingcallforexistingbookingid("login", GroupId, "G");
				editbookigid=Editbookingobj.extractingtempid();
				Getroomstoassign Getroomstoassignobj=new Getroomstoassign();
				Getroomstoassignobj.getroomstoassigncall(keytype, editbookigid);
				Roomid=Getroomstoassignobj.extractingroomid();
				System.out.println("Roomid is:"+Roomid);
				HttpResponse<JsonNode> responseassignrom = Unirest.post(""+serverurl+"/ws/web/assignroom")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "c52633bd-8abb-e05d-ec86-0641bf085b8f")
						  .body("{"
			                		+ "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2012-01-16T10:10:15\","
			                		+ "\"request\": {"
			                		+ "\"method\": \"assignroom\","
			                		+ "\"key\": \""+accesskey+"\","
			                		+ "\"data\": {"
			                		+ "\"bookings\": ["
			                		+ "{"
			                		+ "\"id\": \""+editbookigid+"\","
			                		+ "\"roomId\":\""+Roomid+"\""
			                		+ "}]}}}}")
			                     .asJson();
				JsonNode body = responseassignrom.getBody();
				responseJSONString = body.toString();
				System.out.println("Response of AssignRoom");
				System.out.println("assignroom:"+responseJSONString);
			}
			
			catch(UnirestException e)
			{
				e.printStackTrace();
			}
				
		
	}
	
	public String extractingmessageassignroom()
	{
		System.out.println("welcome to extractingmessagegeassignroom");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String assignroomstring;
		assignroomstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("at last assignroom success:"+assignroomstring);
		return assignroomstring;
	}// End of extractingmessageassignroom
	
	
}// End of class
