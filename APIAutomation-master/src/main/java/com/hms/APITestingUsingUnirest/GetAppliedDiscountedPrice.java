package com.hms.APITestingUsingUnirest;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class GetAppliedDiscountedPrice {
	
	 String responseJSONString;
		String serverurl;
		 //String hotelid1;
		 String keytype;
		 String accesskey;
		 String gueststayid;
		 String firstname;
		 String msg;
		 HttpResponse<JsonNode> responseGetAppliedDiscountedPrice;
		 
		 public void getAppliedDiscountedPriceCall(String s1) throws JDOMException, IOException{
	         keytype = s1;
			 
			 if(keytype == "wsauth")
				{
					String keyw = Wsauth.wsauthkeyfinalstring;
					accesskey = keyw;
					System.out.println("wsauthkey in GetAppliedDiscountedPrice:"+keyw);
				}
				
				else if(keytype == "login")
				{
			
					String keyl = Login.finalloginaccesskey;
					System.out.println("login key in GetAppliedDiscountedPrice:"+keyl);
					accesskey = keyl;
				}
			 try{
			 System.out.println("welcome to GetAppliedDiscountedPrice try block:");
			 serverurl = CommonConfig.serverurl;	 
			 System.out.println("hello accesskey:"+accesskey);
			 System.out.println("hi");
			 
			 Getspdiscount getspdiscountObj=new Getspdiscount();
			 getspdiscountObj.getspdiscount("login");
				String spID=	 getspdiscountObj.extractingSpdiscountId();
				String reqID=	 getspdiscountObj.extractingRequirementID();
			 
		responseGetAppliedDiscountedPrice= Unirest.post(""+serverurl+"/ws/web/getpaymentmode")
				  .header("content-type", "application/json")
				  .header("x-ig-sg", "D_gg%fkl85_j")
				  .header("cache-control", "no-cache")
				  .header("postman-token", "ebf56e64-e308-b8b7-bd77-65d49111c1e0")
				  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2012-01-16T10:10:15\",\"request\":{\"method\":\"getpaymentmode\",\"key\":\""+accesskey+"\",\"languagecode\":\"en\"}}}")
				  .asJson();
		JsonNode body = responseGetAppliedDiscountedPrice.getBody();
		System.out.println("GetAppliedDiscountedPrice response::"+body);	
		responseJSONString = body.toString();	
		
		
			 /*Confirmbooking confirmbookingobj=new Confirmbooking();
			confirmbookingobj.Confirmbookingcall(keytype);
			 String bookingid=confirmbookingobj.extracingbookingid();
			 System.out.println("Bookingid in GetAppliedDiscountedPrice ::"+bookingid);*/
			
			/* HttpResponse<String> responseGetAppliedDiscountedPrice= Unirest.post(""+serverurl+"/ws/web/getapplieddiscountedprice")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/xml")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "9c5b1361-4e6d-ce04-992a-8fa30ca26ed6")
					  .body("<?xml version=\"1.0\"?>\n<hotelogix version=\"1.0\" datetime=\"2016-07-21T12:08:02\">\n\t<request method=\"getapplieddiscountedprice\" key=\""+accesskey+"\" languagecode=\"en\">\n\t\t<specialDiscount id=\""+spID+"\">\n\t\t\t<requirement id=\""+reqID+"\" value=\"Driver License\" />\n\t\t</specialDiscount>\n\t</request>\n</hotelogix>")
		                     .asString();
			 responseJSONString=responseGetAppliedDiscountedPrice.getBody();
			 System.out.println(":::::::::::"+responseJSONString);
			 SAXBuilder saxBuilder = new SAXBuilder();
			 Document doc = saxBuilder.build(new StringReader(responseJSONString));
			 Element hotelogix = doc.detachRootElement();
			 Element respons =hotelogix.getChild("response");
			// Element l1 =respons.getChild("hotel id");
			// l1.g
			 
		//	String hotelID= status.getAttribute("hotel id").toString();
			// System.out.println("HotelID is:::"+status);
			// msg=status.getAttributeValue("message");
			
		    //JsonNode body = responseupdatewebbooking.getBody();
			// String body = responseupdatewebbooking.getBody();
			 //responseJSONString = body.toString();
*/			 System.out.println(responseJSONString);
			 }
			 catch(UnirestException e)
			 {
				 e.printStackTrace();
			 }
	}
		 public int extractingmessageGetAppliedDiscountedPrice()
			{
				String localresponseJSONString = responseJSONString;
				JSONObject jsonResult = new JSONObject(localresponseJSONString);
				String getPaymentModesuccessstring="";
				//getGuestTitlesuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
				
			int status=	responseGetAppliedDiscountedPrice.getStatus();
				
				
				/*JSONObject newresp = jsonResult.getJSONObject("hotelogix");
				JSONObject resp= newresp.getJSONObject("response");
				JSONObject hotels=resp.getJSONArray("hotels").getJSONObject(0);	
				JSONObject titles=hotels.getJSONArray("paymentmodes").getJSONObject(0);
				getPaymentModesuccessstring=titles.get("bycheque").toString();*/
				//AgentID=hotels.get("agentId").toString();
				//System.out.println("The agent id is:" +AgentID);*/
				System.out.println("last getPaymentMode success"+status);
				return status;
			}
			

}
