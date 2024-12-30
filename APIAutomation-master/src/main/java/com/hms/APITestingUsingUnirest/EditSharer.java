package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class EditSharer {
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	String message;
	String guestStaysid;
	String guestStaysid2;
	String editbookingid;

	public void EditSharercall(String s1) {
		keytype = s1;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in EditSharer:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in EditSharer:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			Confirmbooking Confirmbookingobj=new Confirmbooking();
			Confirmbookingobj.ConfirmbookingcallforFrontDeskforfutureDate(keytype,"1","false");
			String bookingid=Confirmbookingobj.extracingbookingid();
			System.out.println("booking id from confirmbooking: "+bookingid);
			Editbooking Editbookingobj=new Editbooking();
			Editbookingobj.fn_editbookingcallforexistingbookingid(keytype,bookingid,"S");
			guestStaysid=Editbookingobj.extractingguestStayId();
			guestStaysid2=Editbookingobj.extractingguestStayIdofSecondGuest();
			editbookingid=Editbookingobj.extractingtempid();
			AttachGuest AttachGuestobj=new AttachGuest();
			System.out.println("keytype:::"+keytype);
			System.out.println("editbookingid:::"+editbookingid);
			System.out.println("guestStaysid2"+guestStaysid2);
			
			AttachGuestobj.attachguestcallForExistingBookingid(keytype, editbookingid, guestStaysid2, "S");
			
			
			HttpResponse<JsonNode> responseeditsharer = Unirest.post(""+serverurl+"/ws/web/editsharer")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
				        		+ "\"hotelogix\": {"
				        		+ "\"version\": \"1.0\","
				        		+ "\"datetime\": \"2012-01-16T10:10:15\","
				        		+ "\"request\": {"
				        		+ "\"method\": \"editsharer\","
				        		+ "\"key\": \""+accesskey+"\","
				        		+ "\"languagecode\": \"en\","
				        		+ "\"data\": {"
				        		+ "\"id\": \""+editbookingid+"\","
				        		+ "\"guestStays\": ["
				        		+ "{"
				        		+ "\"id\": \""+guestStaysid+"\","
				        		+ "\"isPrimary\": true,"
				        		+ "\"isChargeSharer\": true"
				        		+ "},"
				        		+ "{"
				        		+ "\"id\": \""+guestStaysid2+"\","
				        		+ "\"isPrimary\": false,"
				        		+ "\"isChargeSharer\": true"
				        		+ "}]}}}}")
				        .asJson();	
			JsonNode body = responseeditsharer.getBody();
			responseJSONString = body.toString();
			System.out.println("editsharer Response:  "+responseJSONString);
	 }catch(UnirestException e)
		{
			e.printStackTrace();
		}

		finally{

			Commiteditbooking commitbookingobj = new Commiteditbooking();
			commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", editbookingid );
			}
	}
	public void EditSharercall_Grp_SingleResv(String s1) {
		keytype = s1;
		if(keytype == "wsauth")
		{
			
			String keyw = Wsauth.wsauthkeyfinalstring;
			accesskey = keyw;
			System.out.println("wsauthkey in EditSharer:"+keyw);
		}
		
		else if(keytype == "login")
		{
			
			
			String keyl = Login.finalloginaccesskey;
			System.out.println("login key in EditSharer:"+keyl);
			accesskey = keyl;	
		}
		try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			Confirmbooking Confirmbookingobj=new Confirmbooking();
			Confirmbookingobj.ConfirmbookingcallforFrontDeskforfutureDate(keytype,"2","true");
			String bookingid=Confirmbookingobj.extracinggroupid();
			System.out.println("booking id from confirmbooking: "+bookingid);
			Editbooking Editbookingobj=new Editbooking();
			Editbookingobj.fn_editbookingcallforexistingbookingid(keytype,bookingid,"G");
			guestStaysid=Editbookingobj.extractingguestStayId();
			guestStaysid2=Editbookingobj.extractingguestStayIdofSecondGuest();
			editbookingid=Editbookingobj.extractingtempid();
			AttachGuest AttachGuestobj=new AttachGuest();
			AttachGuestobj.attachguestcallForExistingBookingid(keytype, editbookingid, guestStaysid2, "G");
			HttpResponse<JsonNode> responseeditsharer = Unirest.post(""+serverurl+"/ws/web/editsharer")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "85498441-f6c3-a742-5504-1d129d3b161c")
					  .body("{"
				        		+ "\"hotelogix\": {"
				        		+ "\"version\": \"1.0\","
				        		+ "\"datetime\": \"2012-01-16T10:10:15\","
				        		+ "\"request\": {"
				        		+ "\"method\": \"editsharer\","
				        		+ "\"key\": \""+accesskey+"\","
				        		+ "\"languagecode\": \"en\","
				        		+ "\"data\": {"
				        		+ "\"id\": \""+editbookingid+"\","
				        		+ "\"guestStays\": ["
				        		+ "{"
				        		+ "\"id\": \""+guestStaysid+"\","
				        		+ "\"isPrimary\": true,"
				        		+ "\"isChargeSharer\": true"
				        		+ "},"
				        		+ "{"
				        		+ "\"id\": \""+guestStaysid2+"\","
				        		+ "\"isPrimary\": false,"
				        		+ "\"isChargeSharer\": true"
				        		+ "}]}}}}")
				        .asJson();	
			JsonNode body = responseeditsharer.getBody();
			responseJSONString = body.toString();
			System.out.println("editsharer Response:  "+responseJSONString);
	 }catch(UnirestException e)
		{
			e.printStackTrace();
		}

		finally{

			Commiteditbooking commitbookingobj = new Commiteditbooking();
			commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", editbookingid );
			}
		
	}
	public String extractingmessageeditsharer(){
		System.out.println("welcome to extractingmessageeditsharer");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("Status msg of editsharer :"+message);
		//return editbookingstring;
		return message;
	}
	
}
