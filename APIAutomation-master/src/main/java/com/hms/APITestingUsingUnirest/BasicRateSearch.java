package com.hms.APITestingUsingUnirest;

import java.io.IOException;
import java.io.StringReader;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class BasicRateSearch {
	static String responseJSONString;
	String serverurl;
	 
	 String keytype;
	 String accesskey;
	 String msg;
	 String fromdate;
     String todate;
     String hotelid=CommonConfig.hotelid1;
     public void BasicRateSearchcall(String s1) throws JDOMException, IOException{
         keytype = s1;
         
		 
		 if(keytype == "wsauth")
			{
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in BasicRateSearch:"+keyw);
			}
			
			else if(keytype == "login")
			{
		
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in BasicRateSearch:"+keyl);
				accesskey = keyl;
			}
		 try{
			 System.out.println("welcome to BasicRateSearch try block:");
			 serverurl = CommonConfig.serverurl;	 
			 System.out.println("hello accesskey:"+accesskey);
			 Search searchobj=new Search();
			 searchobj.getCurrentDate();
			 fromdate= searchobj.Today;
			 System.out.println(fromdate);
			 todate=searchobj.Tomorrow;
			 System.out.println(todate);
			 HttpResponse<String> BasicRateSearch = Unirest.post(""+serverurl+"/ws/web/basicratesearch")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/xml")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "9c5b1361-4e6d-ce04-992a-8fa30ca26ed6")
					  .body("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		                		+ "<hotelogix version=\"1.0\" datetime=\"2015-10-27T11:23:54\">"
		                		+ "<request method=\"basicratesearch\" key=\""+accesskey+"\" languagecode=\"en\">"
		                	    + "<stay checkindate=\""+fromdate+"\" checkoutdate=\""+todate+"\"/>"
		                	    + "<pax adult=\"1\" child=\"0\" infant=\"0\"/>"
		                	    + "<minvaluetype value=\"1\"/>"
		                	    + "<hotels>"
		                	    + "<hotel id=\""+hotelid+"\"/>"
		                	    + "</hotels>"
		                		+ "</request>"
		                		+ "</hotelogix>")
		                     .asString();
			 String responseJSONString=BasicRateSearch.getBody();
			 System.out.println("Hey:"  +responseJSONString);
			 SAXBuilder saxBuilder = new SAXBuilder();
			 Document doc = saxBuilder.build(new StringReader(responseJSONString));
			 Element hotelogix = doc.detachRootElement();
			 Element respons =hotelogix.getChild("response");
		 }catch(UnirestException e)
	       {
        e.printStackTrace();
       }
     }
}
