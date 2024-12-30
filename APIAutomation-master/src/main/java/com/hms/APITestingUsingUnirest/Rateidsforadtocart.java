package com.hms.APITestingUsingUnirest;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Rateidsforadtocart {
	 String responseJSONString;
	String serverurl;
	 
	 String keytype;
	 String accesskey;
	 String msg;
	 String fromdate;
      String todate;
      String RateID=null;
      String hotelid=CommonConfig.hotelid1;
	 public void Rateidsforadtocartcall(String s1) throws JDOMException, IOException{
         keytype = s1;
         
		 
		 if(keytype == "wsauth")
			{
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in Rateidsforadtocart:"+keyw);
			}
			
			else if(keytype == "login")
			{
		
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in Rateidsforadtocart:"+keyl);
				accesskey = keyl;
			}
		 try{
			 System.out.println("welcome to Rateidsforadtocart try block:");
			 serverurl = CommonConfig.serverurl;	 
			 System.out.println("hello accesskey:"+accesskey);
			 Search searchobj=new Search();
			 searchobj.getCurrentDate();
			 fromdate= searchobj.Today;
			 System.out.println(fromdate);
			 todate=searchobj.Tomorrow;
			 System.out.println(todate);System.out.println("hi");
			 HttpResponse<String> rateidsforadtocart = Unirest.post(""+serverurl+"/ws/web/rateidsforadtocart")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/xml")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "9c5b1361-4e6d-ce04-992a-8fa30ca26ed6")
					  .body("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		                		+ "<hotelogix version=\"1.0\" datetime=\"2015-10-27T11:23:54\">"
		                		+ "<request method=\"rateidsforadtocart\" key=\""+accesskey+"\" languagecode=\"en\">"
		                	    + "<stay checkindate=\""+fromdate+"\" checkoutdate=\""+todate+"\"/>"
		                	    + "<hotels>"
		                	    + "<hotel id=\""+hotelid+"\">"
		                	    + "</hotel>"
		                	    + "</hotels>"
		                		+ "</request>"
		                		+ "</hotelogix>")
		                     .asString();
			 responseJSONString=rateidsforadtocart.getBody();
			 System.out.println("Hey:"  +responseJSONString);
			 SAXBuilder saxBuilder = new SAXBuilder();
			 Document doc = saxBuilder.build(new StringReader(responseJSONString));
			 Element hotelogix = doc.detachRootElement();
			 Element respons =hotelogix.getChild("response");
			
			 Element hotels =respons.getChild("hotels");
			 System.out.println(hotels);
			 Element hotel =hotels.getChild("hotel");
			 System.out.println(hotel);
			 Element title =hotel.getChild("title");
			 Element status =hotel.getChild("status");
			 System.out.println(status);
			 msg = status.getAttributeValue("message");
			 System.out.println(msg);
			 
			/* Element roomtypes =hotel.getChild("roomtypes");
			 System.out.println(roomtypes);
			 Element roomtype =roomtypes.getChild("roomtype");
			 System.out.println(roomtype);
			 Element rates =roomtype.getChild("rates");
			 System.out.println(rates);
			 Element rate =rates.getChild("rate");
			 System.out.println(rate);
			 Element paxes =rate.getChild("paxes");
			 System.out.println(paxes);
			 Iterator<Element> iete=paxes.getChildren().iterator();
				 while(iete.hasNext()==true){
					 Element ele=iete.next();
					 String adult=ele.getAttributeValue("adult");
					if(adult.equals("1")){
						RateID=ele.getAttributeValue("id");
						break;
					}
				    }
				 System.out.println("hi");*/
			
			 System.out.println(responseJSONString);
		 }catch(UnirestException e)
	       {
          e.printStackTrace();
         }
		 
	 }
	 public String extractingmessageRateidsforadtocart()throws JDOMException, IOException{ 

			try{
			 String localresponseJSONString = responseJSONString;
				SAXBuilder saxBuilder = new SAXBuilder();
				 Document doc = saxBuilder.build(new StringReader(responseJSONString));
				 Element hotelogix = doc.detachRootElement();
				 Element respons =hotelogix.getChild("response");
				 Element hotels =respons.getChild("hotels");
				 Element hotel =hotels.getChild("hotel");
				 Element status =hotel.getChild("status");
				 msg=status.getAttributeValue("message");
				 
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			return msg;
		 }
}
