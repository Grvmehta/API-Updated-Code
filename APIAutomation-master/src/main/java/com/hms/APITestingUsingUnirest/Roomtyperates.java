package com.hms.APITestingUsingUnirest;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.JDOMException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Roomtyperates {
String responseJSONString;
	String serverurl;
	 
	 String keytype;
	 String accesskey;
	 String msg;
	 String fromdate;
     String todate;
	 public void Roomtyperatescall(String s1) throws JDOMException, IOException{
         keytype = s1;
         
		 
		 if(keytype == "wsauth")
			{
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in Roomtyperates:"+keyw);
			}
			
			else if(keytype == "login")
			{
		
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in Roomtyperates:"+keyl);
				accesskey = keyl;
			}
		 try{
			 System.out.println("welcome to Roomtyperates try block:");
			 serverurl = CommonConfig.serverurl;	 
			 System.out.println("hello accesskey:"+accesskey);
			 Search searchobj=new Search();
			 searchobj.getCurrentDate();
			 fromdate= searchobj.Today;
			 System.out.println(fromdate);
			 todate=searchobj.Tomorrow;
			 System.out.println(todate);
			 HttpResponse<String> roomtyperates = Unirest.post(""+serverurl+"/ws/web/roomtyperates")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/xml")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "9c5b1361-4e6d-ce04-992a-8fa30ca26ed6")
					  .body("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		                		+ "<hotelogix version=\"1.0\" datetime=\"2015-10-27T11:23:54\">"
		                		+ "<request method=\"roomtyperates\" key=\""+accesskey+"\" languagecode=\"en\">"
		                	    + "<stay checkindate=\""+fromdate+"\" checkoutdate=\""+todate+"\"/>"
		                	    + "<pax adult=\"1\" child=\"0\" infant=\"0\"/>"
		                	    + "<limit value=\"200\" offset=\"0\" />"
		                		+ "</request>"
		                		+ "</hotelogix>")
		                     .asString();
			responseJSONString=roomtyperates.getBody();
			 System.out.println(responseJSONString);
			
		     SAXBuilder saxBuilder = new SAXBuilder();
			 Document doc = saxBuilder.build(new StringReader(responseJSONString));
			 Element hotelogix = doc.detachRootElement();
			 Element respons =hotelogix.getChild("response");
			 Element hotels =respons.getChild("hotels");
			 Element hotel =hotels.getChild("hotel");
			 Element status =hotel.getChild("status");
			 String message=status.getAttributeValue("message");
			 
			 System.out.println(message);
			 System.out.println(responseJSONString);
		 }catch(UnirestException e)
	       {
        e.printStackTrace();
       }
		 		
		 
}
	 public String extractingmessageRoomtyperates()throws JDOMException, IOException{ 
		 try{
			
			 String localresponseJSONString = responseJSONString;
			//	JSONObject jsonResult = new JSONObject(localresponseJSONString);
				SAXBuilder saxBuilder = new SAXBuilder();
				 Document doc = saxBuilder.build(new StringReader(responseJSONString));
				 Element hotelogix = doc.detachRootElement();
				 Element respons =hotelogix.getChild("response");
				 Element hotels =respons.getChild("hotels");
				 Element hotel =hotels.getChild("hotel");
				 Element status =hotel.getChild("status");
				 msg=status.getAttributeValue("message");
				 System.out.println(responseJSONString);
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			return msg;
		 }
	 }


