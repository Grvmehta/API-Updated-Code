package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.hms.APITestingUsingUnirest.CommonConfig;

public class Addtocart
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 String Noofroom;
	 String rateId;
	private String Noofadult;

	 
	public void Addtocartcall(String s1)
	{
		keytype = s1;
		 
		 if(keytype == "wsauth")
			{
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in addtocart:"+keyw);
			}
		 
			
		 else if(keytype == "wsauthforTA")
			{
				Wsauth objwsauth = new Wsauth();
				objwsauth.WsauthcallforTA();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in addtocart:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 System.out.println("welcome to addtocart try block:");
			 serverurl = CommonConfig.serverurl;
			 System.out.println("hello accesskey:"+accesskey);
			
			 Search searchobj = new Search();
			 searchobj.Searchcall(keytype);
			 System.out.println("hi");
			 rateId = searchobj.extractingrateid();
			 System.out.println("rateid in addtocart ::"+rateId);
			 
			 
			 HttpResponse<JsonNode> responseaddtocart = Unirest.post(""+serverurl+"/ws/web/addtocart")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "9c5b1361-4e6d-ce04-992a-8fa30ca26ed6")
					  .body("{\r\n    \"hotelogix\": {\r\n        \"version\": \"1.0\",\r\n        \"datetime\": \"2012-01-16T10:10:15\",\r\n        \"request\": {\r\n                \"method\": \"addtocart\",\r\n                \"key\": \""+accesskey+"\",\r\n                \"languagecode\": \"en\",\r\n                \"data\": {\r\n                        \"itemid\": {\r\n                                \"value\": \""+rateId+"\"\r\n                        }\r\n                }\r\n\r\n        }\r\n    }\r\n}")
					  .asJson();
			 JsonNode body = responseaddtocart.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		 
	}// End of Addtocartcall() method

	 public void Addtocartforgroupresv(String s1, String s2){
		    keytype = s1;
		        Noofadult = s2;
		    if(keytype == "wsauth")
		    {

		    String keyw = Wsauth.wsauthkeyfinalstring;
		    accesskey = keyw;
		    System.out.println("wsauthkey in addtocart:"+keyw);
		    }
		   
		    else if(keytype == "wsauthforTA")
		{
		Wsauth objwsauth = new Wsauth();
		objwsauth.WsauthcallforTA();
		String keyw = objwsauth.extractingWsauthKey();
		accesskey = keyw;
		}
		   
		    else if(keytype == "wsauthforCorp")
		{
		Wsauth objwsauth = new Wsauth();
		objwsauth.WsauthcallforCorp();
		String keyw = objwsauth.extractingWsauthKey();
		accesskey = keyw;
		}

		    else if(keytype == "login")
		    {
		      String keyl = Login.finalloginaccesskey;
		    System.out.println("login key in addtocart:"+keyl);
		    accesskey = keyl;
		    }

		    try
		    {
		    System.out.println("welcome to addtocart try block:");
		    serverurl = CommonConfig.serverurl;
		    System.out.println("hello accesskey:"+accesskey);

		    Search searchobj = new Search();
		    searchobj.Searchcallforgroupresv(keytype, Noofadult );
		    accesskey = searchobj.accesskey;
		    System.out.println("hi");
		    String rateid = searchobj.extractingrateid();
		    System.out.println("rateid in addtocart ::"+rateid);

		        for(int i=0;i<=1;i++){
		    HttpResponse<JsonNode> responseaddtocart = Unirest.post(""+serverurl+"/ws/web/addtocart")
		    .header("x-ig-sg", "D_gg%fkl85_j")
		    .header("content-type", "application/json")
		    .header("cache-control", "no-cache")
		    .header("postman-token", "9c5b1361-4e6d-ce04-992a-8fa30ca26ed6")
		    .body("{\r\n    \"hotelogix\": {\r\n        \"version\": \"1.0\",\r\n        \"datetime\": \"2012-01-16T10:10:15\",\r\n        \"request\": {\r\n                \"method\": \"addtocart\",\r\n                \"key\": \""+accesskey+"\",\r\n                \"languagecode\": \"en\",\r\n                \"data\": {\r\n                        \"itemid\": {\r\n                                \"value\": \""+rateid+"\"\r\n                        }\r\n                }\r\n\r\n        }\r\n    }\r\n}")
		    .asJson();
		       
		    JsonNode body = responseaddtocart.getBody();
		    responseJSONString = body.toString();
		        }
		    System.out.println("Add to cart repns for fd:"+responseJSONString);
		    }
		    catch(UnirestException e)
		    {
		    e.printStackTrace();
		    }

		    }
	public void AddtocartforFrontDeskfor3DResv(String s1){
	    keytype = s1;

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
	    System.out.println("welcome to addtocart try block:");
	    serverurl = CommonConfig.serverurl;
	    System.out.println("hello accesskey:"+accesskey);

	    Search searchobj = new Search();
	    searchobj.SearchcallforFrontDeskfor3DResv(keytype);
	    System.out.println("hi");
	    String rateid = searchobj.extractingrateid();
	    System.out.println("rateid in addtocart ::"+rateid);


	    HttpResponse<JsonNode> responseaddtocart = Unirest.post(""+serverurl+"/ws/web/addtocart")
	    .header("x-ig-sg", "D_gg%fkl85_j")
	    .header("content-type", "application/json")
	    .header("cache-control", "no-cache")
	    .header("postman-token", "9c5b1361-4e6d-ce04-992a-8fa30ca26ed6")
	    .body("{\r\n    \"hotelogix\": {\r\n        \"version\": \"1.0\",\r\n        \"datetime\": \"2012-01-16T10:10:15\",\r\n        \"request\": {\r\n                \"method\": \"addtocart\",\r\n                \"key\": \""+accesskey+"\",\r\n                \"languagecode\": \"en\",\r\n                \"data\": {\r\n                        \"itemid\": {\r\n                                \"value\": \""+rateid+"\"\r\n                        }\r\n                }\r\n\r\n        }\r\n    }\r\n}")
	    .asJson();
	    JsonNode body = responseaddtocart.getBody();
	    responseJSONString = body.toString();
	    System.out.println("Add to cart repns for fd:"+responseJSONString);
	    }
	    catch(UnirestException e)
	    {
	    e.printStackTrace();
	    }

	    }
	public void AddtocartcallforFrontDeskforfutureDate(String s1,String s2){
		keytype = s1;
		Noofroom=s2;
		 if(keytype == "wsauth")
			{
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in addtocart:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in addtocart:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 System.out.println("welcome to addtocart try block:");
			 serverurl = CommonConfig.serverurl;
			 System.out.println("hello accesskey:"+accesskey);
			
			 Search searchobj = new Search();
			 searchobj.SearchcallforFrontDeskfututuredate(keytype,Noofroom);
			 System.out.println("hi");
			 String rateid = searchobj.extractingrateid();
			 System.out.println("rateid in addtocart ::"+rateid);
			 
			 
			 HttpResponse<JsonNode> responseaddtocart = Unirest.post(""+serverurl+"/ws/web/addtocart")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "9c5b1361-4e6d-ce04-992a-8fa30ca26ed6")
					  .body("{\r\n    \"hotelogix\": {\r\n        \"version\": \"1.0\",\r\n        \"datetime\": \"2012-01-16T10:10:15\",\r\n        \"request\": {\r\n                \"method\": \"addtocart\",\r\n                \"key\": \""+accesskey+"\",\r\n                \"languagecode\": \"en\",\r\n                \"data\": {\r\n                        \"itemid\": {\r\n                                \"value\": \""+rateid+"\"\r\n                        }\r\n                }\r\n\r\n        }\r\n    }\r\n}")
					  .asJson();
			 JsonNode body = responseaddtocart.getBody();
			 responseJSONString = body.toString();
			 System.out.println("Add to cart repns for fd:"+responseJSONString);
		 }
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		
	}
	
	public void AddtocartcallforFrontDesk(String s1){
		keytype = s1;
		 
		 if(keytype == "wsauth")
			{
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in addtocart:"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in addtocart:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 System.out.println("welcome to addtocart try block:");
			 serverurl = CommonConfig.serverurl;
			 System.out.println("hello accesskey:"+accesskey);
			
			 Search searchobj = new Search();
			 searchobj.SearchcallforFrontDesk(keytype);
			 System.out.println("hi");
			 String rateid = searchobj.extractingrateid();
			 System.out.println("rateid in addtocart ::"+rateid);
			 
			 
			 HttpResponse<JsonNode> responseaddtocart = Unirest.post(""+serverurl+"/ws/web/addtocart")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("content-type", "application/json")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "9c5b1361-4e6d-ce04-992a-8fa30ca26ed6")
					  .body("{\r\n    \"hotelogix\": {\r\n        \"version\": \"1.0\",\r\n        \"datetime\": \"2012-01-16T10:10:15\",\r\n        \"request\": {\r\n                \"method\": \"addtocart\",\r\n                \"key\": \""+accesskey+"\",\r\n                \"languagecode\": \"en\",\r\n                \"data\": {\r\n                        \"itemid\": {\r\n                                \"value\": \""+rateid+"\"\r\n                        }\r\n                }\r\n\r\n        }\r\n    }\r\n}")
					  .asJson();
			 JsonNode body = responseaddtocart.getBody();
			 responseJSONString = body.toString();
			 System.out.println("Add to cart repns for fd:"+responseJSONString);
		 }
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
		
	}
	public void Addtocartforgroupresv(String s1){
	    keytype = s1;

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
	    System.out.println("welcome to addtocart try block:");
	    serverurl = CommonConfig.serverurl;
	    System.out.println("hello accesskey:"+accesskey);

	    Search searchobj = new Search();
	    searchobj.Searchcallforgroupresv(keytype);
	    System.out.println("hi");
	    String rateid = searchobj.extractingrateid();
	    System.out.println("rateid in addtocart ::"+rateid);


	    HttpResponse<JsonNode> responseaddtocart = Unirest.post(""+serverurl+"/ws/web/addtocart")
	    .header("x-ig-sg", "D_gg%fkl85_j")
	    .header("content-type", "application/json")
	    .header("cache-control", "no-cache")
	    .header("postman-token", "9c5b1361-4e6d-ce04-992a-8fa30ca26ed6")
	    .body("{\r\n    \"hotelogix\": {\r\n        \"version\": \"1.0\",\r\n        \"datetime\": \"2012-01-16T10:10:15\",\r\n        \"request\": {\r\n                \"method\": \"addtocart\",\r\n                \"key\": \""+accesskey+"\",\r\n                \"languagecode\": \"en\",\r\n                \"data\": {\r\n                        \"itemid\": {\r\n                                \"value\": \""+rateid+"\"\r\n                        }\r\n                }\r\n\r\n        }\r\n    }\r\n}")
	    .asJson();
	    JsonNode body = responseaddtocart.getBody();
	    responseJSONString = body.toString();
	    System.out.println("Add to cart repns for fd:"+responseJSONString);
	    }
	    catch(UnirestException e)
	    {
	    e.printStackTrace();
	    }

	    }


	public String extractingmessageaddtocart()
 {
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
String addtocartstring;
addtocartstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
//addtocartstring = jsonResult.getJSONObject("roomtypes").getJSONObject("response").getJSONObject("status").getString("message");
System.out.println("at last search addtocart:"+addtocartstring);
return addtocartstring;
}
}// End of class
