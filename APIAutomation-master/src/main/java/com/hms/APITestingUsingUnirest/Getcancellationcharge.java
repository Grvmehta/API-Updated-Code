package com.hms.APITestingUsingUnirest;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Getcancellationcharge {
	 String responseJSONString;
	String serverurl;
	 String orderid;
	 String bookingid;
	 String keytype;
	 String accesskey;
	 String msg;
	 public void Getcancellationchargecall(String s1) throws JDOMException, IOException{
         keytype = s1;
		 
		 if(keytype == "wsauth")
			{
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in Getcancellationcharge:"+keyw);
			}
			
			else if(keytype == "login")
			{
		
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in Getcancellationcharge:"+keyl);
				accesskey = keyl;
			}
		 try{
			 System.out.println("welcome to Getcancellationcharge try block:");
			 serverurl = CommonConfig.serverurl;	 
			 System.out.println("hello accesskey:"+accesskey);
			 Savebooking savebookingobj=new Savebooking();
			 savebookingobj.Savebookingcall(keytype);
             orderid=savebookingobj.extractingorderid();
			 bookingid=savebookingobj.extractingbookingrid();
			 HttpResponse<String> responsegetcancellationcharge = Unirest.post(""+serverurl+"/ws/web/getcancellationcharge")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/xml")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "9c5b1361-4e6d-ce04-992a-8fa30ca26ed6")
					  .body("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		                		+ "<hotelogix version=\"1.0\" datetime=\"2015-10-27T11:23:54\">"
		                		+ "<request method=\"getcancellationcharge\" key=\""+accesskey+"\" languagecode=\"en\">"
		                		+ "<orderId value=\""+orderid+"\"/>"
		                		+ "<reservationId value=\""+bookingid+"\"/>"
		                		+ "</request>"
		                		+ "</hotelogix>")
		                     .asString();
			 responseJSONString=responsegetcancellationcharge.getBody();
			 SAXBuilder saxBuilder = new SAXBuilder();
			 Document doc = saxBuilder.build(new StringReader(responseJSONString));
			 Element hotelogix = doc.detachRootElement();
			 Element respons =hotelogix.getChild("response");
			 Element status =respons.getChild("status");
			 msg = status.getAttributeValue("message");
			 System.out.println(responseJSONString);
		 }catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
	 }
	 public String extractingmessageGetcancellationcharge()throws JDOMException, IOException{ 

		try{
		 String localresponseJSONString = responseJSONString;
			
			SAXBuilder saxBuilder = new SAXBuilder();
			 Document doc = saxBuilder.build(new StringReader(responseJSONString));
			 Element hotelogix = doc.detachRootElement();
			 Element respons =hotelogix.getChild("response");
			 Element status =respons.getChild("status");
			 msg=status.getAttributeValue("message");
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		return msg;
	 }

}
