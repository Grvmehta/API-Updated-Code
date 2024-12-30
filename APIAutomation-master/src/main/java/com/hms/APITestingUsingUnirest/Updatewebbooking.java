package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jdom.input.SAXBuilder;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Updatewebbooking {
	 String responseJSONString;
	String serverurl;
	 //String hotelid1;
	 String keytype;
	 String accesskey;
	 String gueststayid;
	 String firstname;
	 String msg;
	 
	 public void Updatewebbookingcall(String s1) throws JDOMException, IOException{
         keytype = s1;
		 
		 if(keytype == "wsauth")
			{
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in Updatewebbooking:"+keyw);
			}
			
			else if(keytype == "login")
			{
		
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in Updatewebbooking:"+keyl);
				accesskey = keyl;
			}
		 try{
		 System.out.println("welcome to Updatewebbooking try block:");
		 serverurl = CommonConfig.serverurl;	 
		 System.out.println("hello accesskey:"+accesskey);
		 System.out.println("hi");
		 Confirmbooking confirmbookingobj=new Confirmbooking();
		confirmbookingobj.Confirmbookingcall(keytype);
		 String bookingid=confirmbookingobj.extracingbookingid();
		 System.out.println("Bookingid in updatewebbooking ::"+bookingid);
		
		 HttpResponse<String> responseupdatewebbooking = Unirest.post(""+serverurl+"/ws/web/updatewebbooking")
				  .header("x-ig-sg", "D_gg%fkl85_j")
				  .header("content-type", "application/xml")
				  .header("cache-control", "no-cache")
				  .header("postman-token", "9c5b1361-4e6d-ce04-992a-8fa30ca26ed6")
				  .body("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
	                		+ "<hotelogix version=\"1.0\" datetime=\"2015-10-27T11:23:54\">"
	                		+ "<request method=\"updatewebbooking\" key=\""+accesskey+"\" languagecode=\"en\">"
	                		+ " <bookings>"
	                		+ "<booking id=\""+bookingid+"\">"            		
	                		+ "<preference>"
	                		+ "<![CDATA[Preference for group]]>"
	                		+ "</preference>"
	                		+ "</booking>"
	                		+ "</bookings>"
	                		+ "</request>"
	                		+ "</hotelogix>")
	                     .asString();
		 responseJSONString=responseupdatewebbooking.getBody();
		 SAXBuilder saxBuilder = new SAXBuilder();
		 Document doc = saxBuilder.build(new StringReader(responseJSONString));
		 Element hotelogix = doc.detachRootElement();
		 Element respons =hotelogix.getChild("response");
		 Element status =respons.getChild("status");
		 msg=status.getAttributeValue("message");
		
	    //JsonNode body = responseupdatewebbooking.getBody();
		// String body = responseupdatewebbooking.getBody();
		 //responseJSONString = body.toString();
		 System.out.println(responseJSONString);
		 }
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
}
	 public String extractingmessageUpdatewebbooking()throws JDOMException, IOException{ 
		 try{
		 String localresponseJSONString = responseJSONString;
		
			SAXBuilder saxBuilder = new SAXBuilder();
			 Document doc = saxBuilder.build(new StringReader(responseJSONString));
			 Element hotelogix = doc.detachRootElement();
			 Element respons =hotelogix.getChild("response");
			 Element status =respons.getChild("status");
			 msg=status.getAttributeValue("message");
			 //System.out.println(hotelogix);
			//String updatewebbookingstring;
			//updatewebbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			//System.out.println("at last search updatewebbbooking:"+updatewebbookingstring);
			//return updatewebbookingstring;
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		return msg;
	 }
}
