package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Addcustomcharge {
	String posid;
	String bookingid;
	String discountamt;
  String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
    String message;
	 String groupid=null;
	 String guestid=null;
	 String price="50";
	 String qtity="1";
	 String disamt="5";
	 String distype="FV";
	 String groupcode;
	private String TaxID;
	public void Addcustomchargecall(String s1){
		keytype = s1;
		 
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello to Addcustomcharge block:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in Addcustomcharge:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in Addcustomcharge:"+keyl);
				accesskey = keyl;
			}
		 try{
			    serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);
				Getbookings getbookingsobj = new Getbookings();
				getbookingsobj.Getbookingscall("login","CHECKIN");
				bookingid= getbookingsobj.extractingmainid();
				System.out.println("Booking id from getbookings:"+bookingid);
				Getpos getposobj=new Getpos();
				getposobj.Getposcall(keytype);
				posid=getposobj.extractingposid();
				HttpResponse<JsonNode> responseAddcustomcharge = Unirest.post(""+serverurl+"/ws/web/addcustomcharge")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
						  .body("{"
			                		+ "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2012-01-16T10:10:15\","
			                		+ "\"request\": {"
			                		+ "\"method\": \"addcustomcharge\","
			                		+ "\"key\": \""+accesskey+"\","
			                		+ "\"data\": {"
			                		+ "\"chargeType\": \"room\","
			                		+ "\"bookingId\": \""+bookingid+"\","
			                		+ "\"groupId\":\""+groupid+"\","
			                		+ "\"guestId\":\""+guestid+"\","
			                		+ "\"posId\": \""+posid+"\","
			                		+ "\"price\":\""+price+"\","
			                		+ "\"quantity\": \""+qtity+"\","
			                		+ "\"description\":\"custom charge\","
			                		+ "\"HSNCode\":\"85167990\","
			                		+ "\"discount\": \""+disamt+"\","
			                		+ "\"discountType\":\""+distype+"\""
			                		+ "}}}}")
			                     .asJson();
				JsonNode body = responseAddcustomcharge.getBody();
				responseJSONString = body.toString();
				System.out.println(responseJSONString);
		 }catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }

		
	}
	public void AddcustomchargecallWithGroupChargeType(String s1){
		keytype = s1;
		 
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello to Addothercharge block:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in Addothercharge:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in Addothercharge:"+keyl);
				accesskey = keyl;
			}
		 try{
		    serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			System.out.println("hello try"+accesskey);
			Getbookings getbookingsobj = new Getbookings();
			getbookingsobj.Getbookingscall("login","CHECKIN");
			groupcode=getbookingsobj.extractinggroupcode();
			Getpos getposobj=new Getpos();
			getposobj.Getposcall(keytype);
			posid=getposobj.extractingposid();
			System.out.println("pos id is : "+posid);
			Getgroup Getgroupobj=new Getgroup();
			Getgroupobj.Getgroupcall("login", groupcode);
			bookingid=Getgroupobj.extractingGOUPPID();
			String GroupId = Getgroupobj.extractinggetgroupidmain();
			/*GetCustomChargetaxes GetCustomChargetaxesobj=new GetCustomChargetaxes();
			GetCustomChargetaxesobj.GetCustomChargetaxescall(keytype);
		    TaxID=GetCustomChargetaxesobj.extractingTaxId();
		    System.out.println( "tax id is: "+TaxID);*/
		    HttpResponse<JsonNode> responseAddcustomcharge = Unirest.post(""+serverurl+"/ws/web/addcustomcharge")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
					  .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"addcustomcharge\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                		+ "\"chargeType\":  \"group\","
		                		+ "\"bookingId\": \""+bookingid+"\","
		                		+ "\"groupId\":\""+GroupId+"\","
		                		+ "\"guestId\":\""+guestid+"\","
		                		+ "\"posId\": \""+posid+"\","
		                		+ "\"price\":\""+price+"\","
		                		+ "\"quantity\": \""+qtity+"\","
		                		+ "\"description\":\"custom charge\","
		                		+ "\"HSNCode\":\"85167990\","
		                		+ "\"discount\": \""+disamt+"\","
		                		+ "\"discountType\":\""+distype+"\""
		                		+ "}}}}")
		                     .asJson();
			JsonNode body = responseAddcustomcharge.getBody();
			responseJSONString = body.toString();
			System.out.println(responseJSONString);
	 }catch(UnirestException e)
	 {
		 e.printStackTrace();
	 }

	}
	public void AddcustomchargecallWithGuestChargeType(String s1) {
		
		keytype = s1;
		 
		 if(keytype == "wsauth")
			 
			{
				System.out.println("hello to Addcustomcharge block:");
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in Addcustomcharge:"+keyw);
			}
			
			else if(keytype == "login")
			{	
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in Addcustomcharge:"+keyl);
				accesskey = keyl;
			}
		 try{
			    serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				System.out.println("hello try"+accesskey);
				Getbookings getbookingsobj = new Getbookings();
				getbookingsobj.Getbookingscall("login","CHECKIN");
				String gbookingid= getbookingsobj.extractingmainid();
				//String GuestID=getbookingsobj.extractingguestStayId();
				//System.out.println("Booking id from getbookings:"+bookingid);
				Getpos getposobj=new Getpos();
				getposobj.Getposcall(keytype);
				posid=getposobj.extractingposid();
				Editbooking editbookingobj=new Editbooking();
				editbookingobj.fn_editbookingcallforexistingbookingid(keytype, gbookingid, "S");
				bookingid=editbookingobj.extractingtempid();
				String GuestDetailsID=editbookingobj.extractingdetailsId();
				HttpResponse<JsonNode> responseAddcustomcharge = Unirest.post(""+serverurl+"/ws/web/addcustomcharge")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
						  .body("{"
			                		+ "\"hotelogix\": {"
			                		+ "\"version\": \"1.0\","
			                		+ "\"datetime\": \"2012-01-16T10:10:15\","
			                		+ "\"request\": {"
			                		+ "\"method\": \"addcustomcharge\","
			                		+ "\"key\": \""+accesskey+"\","
			                		+ "\"data\": {"
			                		+ "\"chargeType\": \"guest \","
			                		+ "\"bookingId\": \""+bookingid+"\","
			                		+ "\"groupId\":\""+groupid+"\","
			                		+ "\"guestId\":\""+GuestDetailsID+"\","
			                		+ "\"posId\": \""+posid+"\","
			                		+ "\"price\":\""+price+"\","
			                		+ "\"quantity\": \""+qtity+"\","
			                		+ "\"description\":\"custom charge\","
			                		+ "\"HSNCode\":\"85167990\","
			                		+ "\"discount\": \""+disamt+"\","
			                		+ "\"discountType\":\""+distype+"\""
			                		+ "}}}}")
			                     .asJson();
				JsonNode body = responseAddcustomcharge.getBody();
				responseJSONString = body.toString();
				System.out.println(responseJSONString);
		 }catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }

		
	}
	public String extractingmessageAddcustomcharge() {
		
		System.out.println("welcome to extractingmessageAddcustomcharge");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("status msg of Addcustomcharge:"+message);
		return message;
	}
	

}
