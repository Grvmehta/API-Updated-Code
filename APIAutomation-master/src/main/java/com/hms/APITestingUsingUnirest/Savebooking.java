package com.hms.APITestingUsingUnirest;

import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.hms.APITestingUsingUnirest.CommonConfig;
import com.hms.APITestingUsingUnirest.Generic.ApiUtils;

public class Savebooking
{
	static String responseJSONString;
	 String serverurl;
	 //String hotelid1;
	 String keytype;
	 String accesskey;
	 String Noofroom;
	 String Isgroupbookingvalue;
	 String Noofadult;	
	 static String orderid;
	 

	 public void Savebookingcall(String s1)
	 {
		 keytype = s1;
		 
		 if(keytype == "wsauth")
			{
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in savebooking:"+keyw);
			}
			
			else if(keytype == "login")
			{
				
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in savebooking:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 System.out.println("welcome to savebooking try block:");
			 serverurl = CommonConfig.serverurl;
			 
			 System.out.println("hello accesskey in savebooking:"+accesskey);
			 
			 Loadcart loadcartobj = new Loadcart();
			 loadcartobj.Loadcartcall(keytype);
			 String hotelid = loadcartobj.extractinghotelid();
			 String bookingid = loadcartobj.extracingbookingid();
			 String gueststayid = loadcartobj.extractinggueststayid();
			 
			 HttpResponse<JsonNode> responsesavebooking = Unirest.post(""+serverurl+"/ws/web/savebooking")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "4bb5413d-2fd5-bbde-7d66-11c6df5ee394")
					  .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"savebooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"languagecode\": \"en\",\r\n      \"data\": {\r\n        \"guest\": {\r\n          \"fname\": {\r\n            \"value\": \"Mukesh\"\r\n          },\r\n          \"lname\": {\r\n            \"value\": \"Jha\"\r\n          },\r\n          \"email\": {\r\n            \"value\": \"mukesh.kumar@hotelogix.com\"\r\n          },\r\n          \"phone\": {\r\n            \"value\": \"8802640811\"\r\n          },\r\n          \"mobile\": {\r\n            \"value\": \"8802640811\"\r\n          },\r\n          \"country\": {\r\n            \"code\": \"IN\",\r\n            \"value\": \"India\"\r\n          },\r\n          \"state\": {\r\n            \"code\": \"UP\",\r\n            \"value\": \"noida\"\r\n          },\r\n          \"address\": {\r\n            \"value\": \"test address\"\r\n          },\r\n          \"city\": {\r\n            \"value\": \"noida\"\r\n          },\r\n          \"zip\": {\r\n            \"value\": \"201301\"\r\n          }\r\n        },\r\n        \"hotels\": [\r\n          {\r\n            \"id\": \""+hotelid+"\",\r\n            \"preference\": {\r\n              \"value\": \"nopreference\"\r\n            },\r\n            \"bookings\": {\r\n              \"booking\": {\r\n                \"id\": \""+bookingid+"\",\r\n                \"gueststays\": {\r\n                  \"gueststay\": {\r\n                    \"id\": \""+gueststayid+"\",\r\n                    \"guest\": {\r\n                      \"fname\": {\r\n                        \"value\": \"akriti\"\r\n                      },\r\n                      \"lname\": {\r\n                        \"value\": \"mishra\"\r\n                      },\r\n                      \"email\": {\r\n                        \"value\": \"mukesh.kumar@hotelogix.com\"\r\n                      },\r\n                      \"phone\": {\r\n                        \"value\": \"9968040558\"\r\n                      },\r\n                      \"mobile\": {\r\n                        \"value\": \"9968040558\"\r\n                      },\r\n                      \"country\": {\r\n                        \"code\": \"IN\",\r\n                        \"value\": \"India\"\r\n                      },\r\n                      \"state\": {\r\n                        \"code\": \"br\",\r\n                        \"value\": \"Bihar\"\r\n                      },\r\n                      \"address\": {\r\n                        \"value\": \"mayur vihar\"\r\n                      },\r\n                      \"city\": {\r\n                        \"value\": \"madhubani\"\r\n                      },\r\n                      \"zip\": {\r\n                        \"value\": \"110091\"\r\n                      }\r\n                    }\r\n                  }\r\n                }\r\n              }\r\n            }\r\n          }\r\n        ],\r\n        \"holdTill\": {\r\n          \"releaseAfter\": {\r\n            \"value\": \"20\",\r\n            \"type\": \"hour\"\r\n          }\r\n        }\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			 JsonNode body = responsesavebooking.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
	 }// End of Savebookingcall() method
	
	 public void SavebookingforFrontDeskfor3DResv(String s1,String s2){
		    keytype = s1;
		    Isgroupbookingvalue=s2;
		if(keytype == "wsauth")
		{

		String keyw = Wsauth.wsauthkeyfinalstring;
		accesskey = keyw;
		System.out.println("wsauthkey in savebooking:"+keyw);
		}

		else if(keytype == "login")
		{


		String keyl = Login.finalloginaccesskey;
		System.out.println("login key in savebooking:"+keyl);
		accesskey = keyl;
		}

		try
		{

		  String firstname=ApiUtils.GA().generateRandomString();
		//firstname="sneha";
		System.out.println("welcome to savebooking try block:");
		serverurl = CommonConfig.serverurl;

		System.out.println("hello accesskey in savebooking:"+accesskey);

		Loadcart loadcartobj = new Loadcart();
		loadcartobj.loadcartforFrontDeskfor3DResv(keytype);
		String hotelid = loadcartobj.extractinghotelid();
		String bookingid = loadcartobj.extracingbookingid();
		String gueststayid = loadcartobj.extractinggueststayid();

		HttpResponse<JsonNode> responsesavebooking = Unirest.post(""+serverurl+"/ws/web/savebooking")
		.header("content-type", "application/json")
		.header("x-ig-sg", "D_gg%fkl85_j")
		.header("cache-control", "no-cache")
		.header("postman-token", "4bb5413d-2fd5-bbde-7d66-11c6df5ee394")
		.body("{ \"hotelogix\": {"
		              + "\"version\": \"1.0\","
		              + "\"datetime\": \"2015-09-16T11:01:29\","
		              + " \"request\": {"
		              + " \"method\": \"savebooking\","
		              + "\"key\": \""+accesskey+"\","
		              + "\"languagecode\" :\"en\","
		              + "\"data\": {"
		              + "\"guest\": {"
		              + "\"fname\": {"
		              + "\"value\": \""+firstname+"\""
		              + "},"
		              + "\"lname\": {"
		              + "\"value\": \"Jha\""
		              + " },"
		              + "\"email\": {"
		              + "\"value\": \"mukesh.kumar@hotelogix.com\""
		              + " },"
		              + "\"phone\": {"
		              + "\"value\": \"2121221212121\""
		              + " },"
		              + "\"mobile\": {"
		              + "\"value\": \"988888989\""
		              + "},"
		              + "\"country\": {"
		              + "\"code\": \"IN\","
		              + "\"value\": \"India\""
		              + " },"
		              + "\"state\": {"
		              + "\"code\": \"UP\","
		              + "\"value\": \"noida\""
		              + " },"
		              + "\"address\": {"
		              + " \"value\": \"test address\""
		              + " },"
		              + "\"city\": {"
		              + "\"value\": \"noida\""
		              + " },"
		              + "\"zip\": {"
		              + "\"value\": \"12345A\""
		              + " },"
		              + "\"taxCode\": {"
		              + "\"value\": \"07AQSPJ0999EABC\""
		              + "},"
		              + "\"workDetail\": {"
		              + "\"address\": {"
		              + "\"value\": \"work address\""
		              + " },"
		              + "\"city\": {"
		              + "\"value\": \"work city\""
		              + "},"
		              + "\"state\": {"
		              + "\"code\": \"UP\","
		              + "\"value\": \"noida\""
		              + " },"
		              + "\"country\": {"
		              + "\"code\": \"IN\","
		              + "\"value\": \"India\""
		              + "},"
		              + "\"zip\": {"
		              + "\"value\": \"110096\""
		              + "},"
		              + "\"phone\": {"
		              + "\"value\": \"123456789\""
		              + " },"
		              + "\"mobile\": {"
		              + "\"value\": \"123456789\""
		              + "},"
		              + "\"fax\": {"
		              + "\"value\": \"123456789\""
		              + "}"
		              + "}"
		              + "},"
		              + "\"isGroupBooking\": {"
		              + "\"value\":"+Isgroupbookingvalue+""
		              + "},"
		              + "\"hotels\": "
		              + "["
		              + "{"
		              + "\"id\": \""+hotelid+"\","
		              + "\"preference\": {"
		              + "\"value\": \"nopreference\""
		              + "},"
		              + "\"bookings\": {"
		              + "\"booking\": {"
		              + "\"id\": \""+bookingid+"\","
		              + "\"gueststays\": "
		              + "["
		              + "{"
		              + "\"id\": \""+gueststayid+"\","
		              + "\"guest\": {"
		              + "\"fname\": {"
		              + "\"value\": \""+firstname+"\""
		              + " },"
		              + "\"lname\": {"
		              + "\"value\": \"upadhaya\""
		              + "},"
		              + "\"email\": {"
		              + "\"value\": \"mukesh.kumar@hotelogix.com\""
		              + "},"
		              + "\"phone\": {"
		              + "\"value\": \"9968040558\""
		              + "},"
		              + "\"mobile\": {"
		              + "\"value\": \"9968040558\""
		              + "},"
		              + "\"country\": {"
		              + "\"code\": \"IN\","
		              + "\"value\": \"India\""
		              + "},"
		              + "\"state\": {"
		              + "\"code\": \"br\","
		              + "\"value\": \"Bihar\""
		              + "},"
		              + "\"address\": {"
		              + "\"value\": \"mayur vihar\""
		              + "},"
		              + "\"city\": {"
		              + "\"value\": \"madhubani\""
		              + "},"
		              + "\"zip\": {"
		              + "\"value\": \"110091\""
		              + "},"
		              + "\"taxCode\": {"
		              + "\"value\": \"07AQSPJ0999EABC\""
		              + "},"
		              + "\"workDetail\": {"
		              + "\"address\": {"
		              + "\"value\": \"work address\""
		              + "},"
		              + "\"city\": {"
		              + "\"value\": \"work city\""
		              + "},"
		              + "\"state\": {"
		              + "\"code\": \"UP\","
		              + "\"value\": \"noida\""
		              + "},"
		              + "\"country\": {"
		              + "\"code\": \"IN\","
		              + "\"value\": \"India\""
		              + "},"
		              + "\"zip\": {"
		              + "\"value\": \"110096\""
		              + "},"
		              + "\"phone\": {"
		              + "\"value\": \"123456789\""
		              + "},"
		              + "\"mobile\": {"
		              + "\"value\": \"123456789\""
		              + "},"
		              + "\"fax\": {"
		              + "\"value\": \"123456789\""
		              + "}}}}]"
		             
		              + "}}}]}}}}")
		//.body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"savebooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"languagecode\": \"en\",\r\n      \"data\": {\r\n        \"guest\": {\r\n          \"fname\": {\r\n            \"value\": \"Mukesh\"\r\n          },\r\n          \"lname\": {\r\n            \"value\": \"Jha\"\r\n          },\r\n          \"email\": {\r\n            \"value\": \"mukesh.kumar@hotelogix.com\"\r\n          },\r\n          \"phone\": {\r\n            \"value\": \"8802640811\"\r\n          },\r\n          \"mobile\": {\r\n            \"value\": \"8802640811\"\r\n          },\r\n          \"country\": {\r\n            \"code\": \"IN\",\r\n            \"value\": \"India\"\r\n          },\r\n          \"state\": {\r\n            \"code\": \"UP\",\r\n            \"value\": \"noida\"\r\n          },\r\n          \"address\": {\r\n            \"value\": \"test address\"\r\n          },\r\n          \"city\": {\r\n            \"value\": \"noida\"\r\n          },\r\n          \"zip\": {\r\n            \"value\": \"201301\"\r\n          }\r\n        },\r\n        \"hotels\": [\r\n          {\r\n            \"id\": \""+hotelid+"\",\r\n            \"preference\": {\r\n              \"value\": \"nopreference\"\r\n            },\r\n            \"bookings\": {\r\n              \"booking\": {\r\n                \"id\": \""+bookingid+"\",\r\n                \"gueststays\": {\r\n                  \"gueststay\": {\r\n                    \"id\": \""+gueststayid+"\",\r\n                    \"guest\": {\r\n                      \"fname\": {\r\n                        \"value\": \"akriti\"\r\n                      },\r\n                      \"lname\": {\r\n                        \"value\": \"mishra\"\r\n                      },\r\n                      \"email\": {\r\n                        \"value\": \"mukesh.kumar@hotelogix.com\"\r\n                      },\r\n                      \"phone\": {\r\n                        \"value\": \"9968040558\"\r\n                      },\r\n                      \"mobile\": {\r\n                        \"value\": \"9968040558\"\r\n                      },\r\n                      \"country\": {\r\n                        \"code\": \"IN\",\r\n                        \"value\": \"India\"\r\n                      },\r\n                      \"state\": {\r\n                        \"code\": \"br\",\r\n                        \"value\": \"Bihar\"\r\n                      },\r\n                      \"address\": {\r\n                        \"value\": \"mayur vihar\"\r\n                      },\r\n                      \"city\": {\r\n                        \"value\": \"madhubani\"\r\n                      },\r\n                      \"zip\": {\r\n                        \"value\": \"110091\"\r\n                      }\r\n                    }\r\n                  }\r\n                }\r\n              }\r\n            }\r\n          }\r\n        ],\r\n        \"holdTill\": {\r\n          \"releaseAfter\": {\r\n            \"value\": \"20\",\r\n            \"type\": \"hour\"\r\n          }\r\n        }\r\n      }\r\n    }\r\n  }\r\n}")
		.asJson();
		JsonNode body = responsesavebooking.getBody();
		responseJSONString = body.toString();
		System.out.println(responseJSONString);
		}

		catch(UnirestException e)
		{
		e.printStackTrace();
		}
		}
	 public void Savebookingforgroupresv(String s1, String s2){
		    keytype = s1;
		    Noofadult = s2;
		if(keytype == "wsauth")
		{

		String keyw = Wsauth.wsauthkeyfinalstring;
		accesskey = keyw;
		System.out.println("wsauthkey in savebooking:"+keyw);
		}

		else if(keytype == "wsauthforCorp")
		{
		Wsauth objwsauth = new Wsauth();
		objwsauth.WsauthcallforCorp();
		String keyw = objwsauth.extractingWsauthKey();
		accesskey = keyw;
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


		String keyl = Login.finalloginaccesskey;
		System.out.println("login key in savebooking:"+keyl);
		accesskey = keyl;
		}

		try
		{

		  String firstname=ApiUtils.GA().generateRandomString();
		//firstname="sneha";
		System.out.println("welcome to savebooking try block:");
		serverurl = CommonConfig.serverurl;

		System.out.println("hello accesskey in savebooking:"+accesskey);

		Loadcart loadcartobj = new Loadcart();
		loadcartobj.loadcartforgroupresv(keytype,Noofadult);
		accesskey = loadcartobj.accesskey;
		String hotelid = loadcartobj.extractinghotelid();
		String bookingid = loadcartobj.extracingbookingid();
		String gueststayid = loadcartobj.extractinggueststayid();

		HttpResponse<JsonNode> responsesavebooking = Unirest.post(""+serverurl+"/ws/web/savebooking")
		.header("content-type", "application/json")
		.header("x-ig-sg", "D_gg%fkl85_j")
		.header("cache-control", "no-cache")
		.header("postman-token", "4bb5413d-2fd5-bbde-7d66-11c6df5ee394")
		.body("{ \"hotelogix\": {"
		              + "\"version\": \"1.0\","
		              + "\"datetime\": \"2015-09-16T11:01:29\","
		              + " \"request\": {"
		              + " \"method\": \"savebooking\","
		              + "\"key\": \""+accesskey+"\","
		              + "\"languagecode\" :\"en\","
		              + "\"data\": {"
		              + "\"guest\": {"
		              + "\"fname\": {"
		              + "\"value\": \""+firstname+"\""
		              + "},"
		              + "\"lname\": {"
		              + "\"value\": \"Jha\""
		              + " },"
		              + "\"email\": {"
		              + "\"value\": \"mukesh.kumar@hotelogix.com\""
		              + " },"
		              + "\"phone\": {"
		              + "\"value\": \"2121221212121\""
		              + " },"
		              + "\"mobile\": {"
		              + "\"value\": \"988888989\""
		              + "},"
		              + "\"country\": {"
		              + "\"code\": \"IN\","
		              + "\"value\": \"India\""
		              + " },"
		              + "\"state\": {"
		              + "\"code\": \"UP\","
		              + "\"value\": \"noida\""
		              + " },"
		              + "\"address\": {"
		              + " \"value\": \"test address\""
		              + " },"
		              + "\"city\": {"
		              + "\"value\": \"noida\""
		              + " },"
		              + "\"zip\": {"
		              + "\"value\": \"12345A\""
		              + " },"
		              + "\"taxCode\": {"
		              + "\"value\": \"07AQSPJ0999EABC\""
		              + "},"
		              + "\"workDetail\": {"
		              + "\"address\": {"
		              + "\"value\": \"work address\""
		              + " },"
		              + "\"city\": {"
		              + "\"value\": \"work city\""
		              + "},"
		              + "\"state\": {"
		              + "\"code\": \"UP\","
		              + "\"value\": \"noida\""
		              + " },"
		              + "\"country\": {"
		              + "\"code\": \"IN\","
		              + "\"value\": \"India\""
		              + "},"
		              + "\"zip\": {"
		              + "\"value\": \"110096\""
		              + "},"
		              + "\"phone\": {"
		              + "\"value\": \"123456789\""
		              + " },"
		              + "\"mobile\": {"
		              + "\"value\": \"123456789\""
		              + "},"
		              + "\"fax\": {"
		              + "\"value\": \"123456789\""
		              + "}"
		              + "}"
		              + "},"
		              + "\"isGroupBooking\": {"
		              + "\"value\":true"
		              + "},"
		              + "\"hotels\": "
		              + "["
		              + "{"
		              + "\"id\": \""+hotelid+"\","
		              + "\"preference\": {"
		              + "\"value\": \"nopreference\""
		              + "},"
		              + "\"bookings\": {"
		              + "\"booking\": {"
		              + "\"id\": \""+bookingid+"\","
		              + "\"gueststays\": "
		              + "["
		              + "{"
		              + "\"id\": \""+gueststayid+"\","
		              + "\"guest\": {"
		              + "\"fname\": {"
		              + "\"value\": \""+firstname+"\""
		              + " },"
		              + "\"lname\": {"
		              + "\"value\": \"upadhaya\""
		              + "},"
		              + "\"email\": {"
		              + "\"value\": \"mukesh.kumar@hotelogix.com\""
		              + "},"
		              + "\"phone\": {"
		              + "\"value\": \"9968040558\""
		              + "},"
		              + "\"mobile\": {"
		              + "\"value\": \"9968040558\""
		              + "},"
		              + "\"country\": {"
		              + "\"code\": \"IN\","
		              + "\"value\": \"India\""
		              + "},"
		              + "\"state\": {"
		              + "\"code\": \"br\","
		              + "\"value\": \"Bihar\""
		              + "},"
		              + "\"address\": {"
		              + "\"value\": \"mayur vihar\""
		              + "},"
		              + "\"city\": {"
		              + "\"value\": \"madhubani\""
		              + "},"
		              + "\"zip\": {"
		              + "\"value\": \"110091\""
		              + "},"
		              + "\"taxCode\": {"
		              + "\"value\": \"07AQSPJ0999EABC\""
		              + "},"
		              + "\"workDetail\": {"
		              + "\"address\": {"
		              + "\"value\": \"work address\""
		              + "},"
		              + "\"city\": {"
		              + "\"value\": \"work city\""
		              + "},"
		              + "\"state\": {"
		              + "\"code\": \"DE\","
		              + "\"value\": \"delhi\""
		              + "},"
		              + "\"country\": {"
		              + "\"code\": \"IN\","
		              + "\"value\": \"India\""
		              + "},"
		              + "\"zip\": {"
		              + "\"value\": \"110096\""
		              + "},"
		              + "\"phone\": {"
		              + "\"value\": \"123456789\""
		              + "},"
		              + "\"mobile\": {"
		              + "\"value\": \"123456789\""
		              + "},"
		              + "\"fax\": {"
		              + "\"value\": \"123456789\""
		              + "}}}}]"
		             
		              + "}}}]}}}}")
		//.body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"savebooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"languagecode\": \"en\",\r\n      \"data\": {\r\n        \"guest\": {\r\n          \"fname\": {\r\n            \"value\": \"Mukesh\"\r\n          },\r\n          \"lname\": {\r\n            \"value\": \"Jha\"\r\n          },\r\n          \"email\": {\r\n            \"value\": \"mukesh.kumar@hotelogix.com\"\r\n          },\r\n          \"phone\": {\r\n            \"value\": \"8802640811\"\r\n          },\r\n          \"mobile\": {\r\n            \"value\": \"8802640811\"\r\n          },\r\n          \"country\": {\r\n            \"code\": \"IN\",\r\n            \"value\": \"India\"\r\n          },\r\n          \"state\": {\r\n            \"code\": \"UP\",\r\n            \"value\": \"noida\"\r\n          },\r\n          \"address\": {\r\n            \"value\": \"test address\"\r\n          },\r\n          \"city\": {\r\n            \"value\": \"noida\"\r\n          },\r\n          \"zip\": {\r\n            \"value\": \"201301\"\r\n          }\r\n        },\r\n        \"hotels\": [\r\n          {\r\n            \"id\": \""+hotelid+"\",\r\n            \"preference\": {\r\n              \"value\": \"nopreference\"\r\n            },\r\n            \"bookings\": {\r\n              \"booking\": {\r\n                \"id\": \""+bookingid+"\",\r\n                \"gueststays\": {\r\n                  \"gueststay\": {\r\n                    \"id\": \""+gueststayid+"\",\r\n                    \"guest\": {\r\n                      \"fname\": {\r\n                        \"value\": \"akriti\"\r\n                      },\r\n                      \"lname\": {\r\n                        \"value\": \"mishra\"\r\n                      },\r\n                      \"email\": {\r\n                        \"value\": \"mukesh.kumar@hotelogix.com\"\r\n                      },\r\n                      \"phone\": {\r\n                        \"value\": \"9968040558\"\r\n                      },\r\n                      \"mobile\": {\r\n                        \"value\": \"9968040558\"\r\n                      },\r\n                      \"country\": {\r\n                        \"code\": \"IN\",\r\n                        \"value\": \"India\"\r\n                      },\r\n                      \"state\": {\r\n                        \"code\": \"br\",\r\n                        \"value\": \"Bihar\"\r\n                      },\r\n                      \"address\": {\r\n                        \"value\": \"mayur vihar\"\r\n                      },\r\n                      \"city\": {\r\n                        \"value\": \"madhubani\"\r\n                      },\r\n                      \"zip\": {\r\n                        \"value\": \"110091\"\r\n                      }\r\n                    }\r\n                  }\r\n                }\r\n              }\r\n            }\r\n          }\r\n        ],\r\n        \"holdTill\": {\r\n          \"releaseAfter\": {\r\n            \"value\": \"20\",\r\n            \"type\": \"hour\"\r\n          }\r\n        }\r\n      }\r\n    }\r\n  }\r\n}")
		.asJson();
		JsonNode body = responsesavebooking.getBody();
		responseJSONString = body.toString();
		System.out.println(responseJSONString);
		}

		catch(UnirestException e)
		{
		e.printStackTrace();
		}
		}
	 public void SavebookingcallforFrontDeskforfutureDate(String s1,String s2,String s3){
 keytype = s1;
 Noofroom=s2;
 Isgroupbookingvalue=s3;
		 if(keytype == "wsauth")
			{
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in savebooking:"+keyw);
			}
			
			else if(keytype == "login")
			{
				
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in savebooking:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {   
			 
			 String firstname=ApiUtils.GA().generateRandomString();
			 //String firstname="Disha";
			 System.out.println("welcome to savebooking try block:");
			 serverurl = CommonConfig.serverurl;
			 
			 System.out.println("hello accesskey in savebooking:"+accesskey);
			 
			 Loadcart loadcartobj = new Loadcart();
			 loadcartobj.LoadcartcallforfrontdeskforFutureDate(keytype,Noofroom);
			 String hotelid = loadcartobj.extractinghotelid();
			 String bookingid = loadcartobj.extracingbookingid();
			 String gueststayid = loadcartobj.extractinggueststayid();
			 
			 HttpResponse<JsonNode> responsesavebooking = Unirest.post(""+serverurl+"/ws/web/savebooking")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "4bb5413d-2fd5-bbde-7d66-11c6df5ee394")
					  .body("{ \"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2015-09-16T11:01:29\","
		                		+ " \"request\": {"
		                		+ " \"method\": \"savebooking\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"languagecode\" :\"en\","
		                		+ "\"data\": {"
		                		+ "\"guest\": {"
		                		+ "\"fname\": {"
		                		+ "\"value\": \""+firstname+"\""
		                		+ "},"
		                		+ "\"lname\": {"
		                		+ "\"value\": \"Jha\""
		                		+ " },"
		                		+ "\"email\": {"
		                		+ "\"value\": \"mukesh.kumar@hotelogix.com\""
		                		+ " },"
		                		+ "\"phone\": {"
		                		+ "\"value\": \"2121221212121\""
		                		+ " },"
		                		+ "\"mobile\": {"
		                		+ "\"value\": \"988888989\""
		                		+ "},"
		                		+ "\"country\": {"
		                		+ "\"code\": \"IN\","
		                		+ "\"value\": \"India\""
		                		+ " },"
		                		+ "\"state\": {"
		                		+ "\"code\": \"UP\","
		                		+ "\"value\": \"noida\""
		                		+ " },"
		                		+ "\"address\": {"
		                		+ " \"value\": \"test address\""
		                		+ " },"
		                		+ "\"city\": {"
		                		+ "\"value\": \"noida\""
		                		+ " },"
		                		+ "\"zip\": {"
		                		+ "\"value\": \"12345A\""
		                		+ " },"
		                		+ "\"taxCode\": {"
		                		+ "\"value\": \"07AQSPJ0999EABC\""
		                		+ "},"
		                		+ "\"workDetail\": {"
		                		+ "\"address\": {"
		                		+ "\"value\": \"work address\""
		                		+ " },"
		                		+ "\"city\": {"
		                		+ "\"value\": \"work city\""
		                		+ "},"
		                		+ "\"state\": {"
		                		+ "\"code\": \"UP\","
		                		+ "\"value\": \"noida\""
		                		+ " },"
		                		+ "\"country\": {"
		                		+ "\"code\": \"IN\","
		                		+ "\"value\": \"India\""
		                		+ "},"
		                		+ "\"zip\": {"
		                		+ "\"value\": \"110096\""
		                		+ "},"
		                		+ "\"phone\": {"
		                		+ "\"value\": \"123456789\""
		                		+ " },"
		                		+ "\"mobile\": {"
		                		+ "\"value\": \"123456789\""
		                		+ "},"
		                		+ "\"fax\": {"
		                		+ "\"value\": \"123456789\""
		                		+ "}"
		                		+ "}"
		                		+ "},"
		                		+ "\"isGroupBooking\": {"
		                		+ "\"value\":"+Isgroupbookingvalue+""
		                		+ "},"
		                		+ "\"hotels\": "
		                		+ "["
		                		+ "{"
		                		+ "\"id\": \""+hotelid+"\","
		                		+ "\"preference\": {"
		                		+ "\"value\": \"nopreference\""
		                		+ "},"
		                		+ "\"bookings\": {"
		                		+ "\"booking\": {"
		                		+ "\"id\": \""+bookingid+"\","
		                		+ "\"gueststays\": "
		                		+ "["
		                		+ "{"
		                		+ "\"id\": \""+gueststayid+"\","
		                		+ "\"guest\": {"
		                		+ "\"fname\": {"
		                		+ "\"value\": \""+firstname+"\""
		                		+ " },"
		                		+ "\"lname\": {"
		                		+ "\"value\": \"upadhaya\""
		                		+ "},"
		                		+ "\"email\": {"
		                		+ "\"value\": \"mukesh.kumar@hotelogix.com\""
		                		+ "},"
		                		+ "\"phone\": {"
		                		+ "\"value\": \"9968040558\""
		                		+ "},"
		                		+ "\"mobile\": {"
		                		+ "\"value\": \"9968040558\""
		                		+ "},"
		                		+ "\"country\": {"
		                		+ "\"code\": \"IN\","
		                		+ "\"value\": \"India\""
		                		+ "},"
		                		+ "\"state\": {"
		                		+ "\"code\": \"br\","
		                		+ "\"value\": \"Bihar\""
		                		+ "},"
		                		+ "\"address\": {"
		                		+ "\"value\": \"mayur vihar\""
		                		+ "},"
		                		+ "\"city\": {"
		                		+ "\"value\": \"madhubani\""
		                		+ "},"
		                		+ "\"zip\": {"
		                		+ "\"value\": \"110091\""
		                		+ "},"
		                		+ "\"taxCode\": {"
		                		+ "\"value\": \"07AQSPJ0999EABC\""
		                		+ "},"
		                		+ "\"workDetail\": {"
		                		+ "\"address\": {"
		                		+ "\"value\": \"work address\""
		                		+ "},"
		                		+ "\"city\": {"
		                		+ "\"value\": \"work city\""
		                		+ "},"
		                		+ "\"state\": {"
		                		+ "\"code\": \"UP\","
		                		+ "\"value\": \"noida\""
		                		+ "},"
		                		+ "\"country\": {"
		                		+ "\"code\": \"IN\","
		                		+ "\"value\": \"India\""
		                		+ "},"
		                		+ "\"zip\": {"
		                		+ "\"value\": \"110096\""
		                		+ "},"
		                		+ "\"phone\": {"
		                		+ "\"value\": \"123456789\""
		                		+ "},"
		                		+ "\"mobile\": {"
		                		+ "\"value\": \"123456789\""
		                		+ "},"
		                		+ "\"fax\": {"
		                		+ "\"value\": \"123456789\""
		                		+ "}}}}]"
		                	
		                		+ "}}}]}}}}")
					  //.body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"savebooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"languagecode\": \"en\",\r\n      \"data\": {\r\n        \"guest\": {\r\n          \"fname\": {\r\n            \"value\": \"Mukesh\"\r\n          },\r\n          \"lname\": {\r\n            \"value\": \"Jha\"\r\n          },\r\n          \"email\": {\r\n            \"value\": \"mukesh.kumar@hotelogix.com\"\r\n          },\r\n          \"phone\": {\r\n            \"value\": \"8802640811\"\r\n          },\r\n          \"mobile\": {\r\n            \"value\": \"8802640811\"\r\n          },\r\n          \"country\": {\r\n            \"code\": \"IN\",\r\n            \"value\": \"India\"\r\n          },\r\n          \"state\": {\r\n            \"code\": \"UP\",\r\n            \"value\": \"noida\"\r\n          },\r\n          \"address\": {\r\n            \"value\": \"test address\"\r\n          },\r\n          \"city\": {\r\n            \"value\": \"noida\"\r\n          },\r\n          \"zip\": {\r\n            \"value\": \"201301\"\r\n          }\r\n        },\r\n        \"hotels\": [\r\n          {\r\n            \"id\": \""+hotelid+"\",\r\n            \"preference\": {\r\n              \"value\": \"nopreference\"\r\n            },\r\n            \"bookings\": {\r\n              \"booking\": {\r\n                \"id\": \""+bookingid+"\",\r\n                \"gueststays\": {\r\n                  \"gueststay\": {\r\n                    \"id\": \""+gueststayid+"\",\r\n                    \"guest\": {\r\n                      \"fname\": {\r\n                        \"value\": \"akriti\"\r\n                      },\r\n                      \"lname\": {\r\n                        \"value\": \"mishra\"\r\n                      },\r\n                      \"email\": {\r\n                        \"value\": \"mukesh.kumar@hotelogix.com\"\r\n                      },\r\n                      \"phone\": {\r\n                        \"value\": \"9968040558\"\r\n                      },\r\n                      \"mobile\": {\r\n                        \"value\": \"9968040558\"\r\n                      },\r\n                      \"country\": {\r\n                        \"code\": \"IN\",\r\n                        \"value\": \"India\"\r\n                      },\r\n                      \"state\": {\r\n                        \"code\": \"br\",\r\n                        \"value\": \"Bihar\"\r\n                      },\r\n                      \"address\": {\r\n                        \"value\": \"mayur vihar\"\r\n                      },\r\n                      \"city\": {\r\n                        \"value\": \"madhubani\"\r\n                      },\r\n                      \"zip\": {\r\n                        \"value\": \"110091\"\r\n                      }\r\n                    }\r\n                  }\r\n                }\r\n              }\r\n            }\r\n          }\r\n        ],\r\n        \"holdTill\": {\r\n          \"releaseAfter\": {\r\n            \"value\": \"20\",\r\n            \"type\": \"hour\"\r\n          }\r\n        }\r\n      }\r\n    }\r\n  }\r\n}")
					  .asJson();
			 JsonNode body = responsesavebooking.getBody();
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 }
	 }
	 public void SavebookingcallforHoldTillReservation(String s1,String s2,String s3)throws Throwable{{
		 keytype = s1;
		 Noofroom=s2;
		 Isgroupbookingvalue=s3;
				 if(keytype == "wsauth")
					{
						
						String keyw = Wsauth.wsauthkeyfinalstring;
						accesskey = keyw;
						System.out.println("wsauthkey in savebooking:"+keyw);
					}
					
					else if(keytype == "login")
					{
						
						
						String keyl = Login.finalloginaccesskey;
						System.out.println("login key in savebooking:"+keyl);
						accesskey = keyl;
					}
				 
				 try
				 {   
					 
					 
					 ApiUtils.GA().fn_GetCurrentDate();
					String dateandtime=ApiUtils.fromdate+" "+ApiUtils.Time;
					
					 
					 String firstname=ApiUtils.GA().generateRandomString();
					 //String firstname="Disha";
					 System.out.println("welcome to savebooking try block:");
					 serverurl = CommonConfig.serverurl;
					 
					 System.out.println("hello accesskey in savebooking:"+accesskey);
					 
					 Loadcart loadcartobj = new Loadcart();
					 loadcartobj.LoadcartcallforfrontdeskforFutureDate(keytype,Noofroom);
					 String hotelid = loadcartobj.extractinghotelid();
					 String bookingid = loadcartobj.extracingbookingid();
					 String gueststayid = loadcartobj.extractinggueststayid();
					 
					 HttpResponse<JsonNode> responsesavebooking = Unirest.post(""+serverurl+"/ws/web/savebooking")
							  .header("content-type", "application/json")
							  .header("x-ig-sg", "D_gg%fkl85_j")
							  .header("cache-control", "no-cache")
							  .header("postman-token", "4bb5413d-2fd5-bbde-7d66-11c6df5ee394")
							  .body("{ \"hotelogix\": {"
		                                + "\"version\": \"1.0\","
		                                + "\"datetime\": \"2015-09-16T11:01:29\","
		                                + " \"request\": {"
		                                + " \"method\": \"savebooking\","
		                                + "\"key\": \""+accesskey+"\","
		                                + "\"languagecode\" :\"en\","
		                                + "\"data\": {"
		                                + "\"guest\": {"
		                                + "\"fname\": {"
		                                + "\"value\": \""+firstname+"\""
		                                + "},"
		                                + "\"lname\": {"
		                                + "\"value\": \"Jha\""
		                                + " },"
		                                + "\"email\": {"
		                                + "\"value\": \"mukesh.kumar@hotelogix.com\""
		                                + " },"
		                                + "\"phone\": {"
		                                + "\"value\": \"2121221212121\""
		                                + " },"
		                                + "\"mobile\": {"
		                                + "\"value\": \"988888989\""
		                                + "},"
		                                + "\"country\": {"
		                                + "\"code\": \"IN\","
		                                + "\"value\": \"India\""
		                                + " },"
		                                + "\"state\": {"
		                                + "\"code\": \"UP\","
		                                + "\"value\": \"noida\""
		                                + " },"
		                                + "\"address\": {"
		                                + " \"value\": \"test address\""
		                                + " },"
		                                + "\"city\": {"
		                                + "\"value\": \"noida\""
		                                + " },"
		                                + "\"zip\": {"
		                                + "\"value\": \"12345A\""
		                                + " },"
		                                + "\"taxCode\": {"
		                                + "\"value\": \"07AQSPJ0999EABC\""
		                                + "},"
		                                + "\"workDetail\": {"
		                                + "\"address\": {"
		                                + "\"value\": \"work address\""
		                                + " },"
		                                + "\"city\": {"
		                                + "\"value\": \"work city\""
		                                + "},"
		                                + "\"state\": {"
		                                + "\"code\": \"UP\","
		                                + "\"value\": \"noida\""
		                                + " },"
		                                + "\"country\": {"
		                                + "\"code\": \"IN\","
		                                + "\"value\": \"India\""
		                                + "},"
		                                + "\"zip\": {"
		                                + "\"value\": \"110096\""
		                                + "},"
		                                + "\"phone\": {"
		                                + "\"value\": \"123456789\""
		                                + " },"
		                                + "\"mobile\": {"
		                                + "\"value\": \"123456789\""
		                                + "},"
		                                + "\"fax\": {"
		                                + "\"value\": \"123456789\""
		                                + "}"
		                                + "}"
		                                + "},"
		                                + "\"isGroupBooking\": {"
		                                + "\"value\":"+Isgroupbookingvalue+""
		                                + "},"
		                                + "\"hotels\": "
		                                + "["
		                                + "{"
		                                + "\"id\": \""+hotelid+"\","
		                                + "\"preference\": {"
		                                + "\"value\": \"nopreference\""
		                                + "},"
		                                + "\"bookings\": {"
		                                + "\"booking\": {"
		                                + "\"id\": \""+bookingid+"\","
		                                + "\"gueststays\": "
		                                + "["
		                                + "{"
		                                + "\"id\": \""+gueststayid+"\","
		                                + "\"guest\": {"
		                                + "\"fname\": {"
		                                + "\"value\": \""+firstname+"\""
		                                + " },"
		                                + "\"lname\": {"
		                                + "\"value\": \"upadhaya\""
		                                + "},"
		                                + "\"email\": {"
		                                + "\"value\": \"mukesh.kumar@hotelogix.com\""
		                                + "},"
		                                + "\"phone\": {"
		                                + "\"value\": \"9968040558\""
		                                + "},"
		                                + "\"mobile\": {"
		                                + "\"value\": \"9968040558\""
		                                + "},"
		                                + "\"country\": {"
		                                + "\"code\": \"IN\","
		                                + "\"value\": \"India\""
		                                + "},"
		                                + "\"state\": {"
		                                + "\"code\": \"br\","
		                                + "\"value\": \"Bihar\""
		                                + "},"
		                                + "\"address\": {"
		                                + "\"value\": \"mayur vihar\""
		                                + "},"
		                                + "\"city\": {"
		                                + "\"value\": \"madhubani\""
		                                + "},"
		                                + "\"zip\": {"
		                                + "\"value\": \"110091\""
		                                + "},"
		                                + "\"taxCode\": {"
		                                + "\"value\": \"07AQSPJ0999EABC\""
		                                + "},"
		                                + "\"workDetail\": {"
		                                + "\"address\": {"
		                                + "\"value\": \"work address\""
		                                + "},"
		                                + "\"city\": {"
		                                + "\"value\": \"work city\""
		                                + "},"
		                                + "\"state\": {"
		                                + "\"code\": \"DE\","
		                                + "\"value\": \"delhi\""
		                                + "},"
		                                + "\"country\": {"
		                                + "\"code\": \"IN\","
		                                + "\"value\": \"India\""
		                                + "},"
		                                + "\"zip\": {"
		                                + "\"value\": \"110096\""
		                                + "},"
		                                + "\"phone\": {"
		                                + "\"value\": \"123456789\""
		                                + "},"
		                                + "\"mobile\": {"
		                                + "\"value\": \"123456789\""
		                                + "},"
		                                + "\"fax\": {"
		                                + "\"value\": \"123456789\""
				                		+ "}}}}]"
				                		+ "}}}],"
				                		+ "\"holdTill\":{"
				                		+ "\"holdTillDate\":{"
				                		+ "\"value\":\""+dateandtime+"\""
				                		+ "}}}}}}")
				                     .asJson();
					 JsonNode body = responsesavebooking.getBody();
					 responseJSONString = body.toString();
					 System.out.println(responseJSONString);
				 }
				 
				 catch(UnirestException e)
				 {
					 e.printStackTrace();
				 }
	 }
			 }
	 public void SavebookingcallforFrontDesk(String s1, String s2){
		 keytype = s1;
		 Isgroupbookingvalue = s2;
		 if(keytype == "wsauth")
		 {

		 String keyw = Wsauth.wsauthkeyfinalstring;
		 accesskey = keyw;
		 System.out.println("wsauthkey in savebooking:"+keyw);
		 }

		 else if(keytype == "login")
		 {


		 String keyl = Login.finalloginaccesskey;
		 System.out.println("login key in savebooking:"+keyl);
		 accesskey = keyl;
		 }

		 try
		 {
		 String firstname= ApiUtils.GA().generateRandomString();
		 System.out.println("welcome to savebooking try block:");
		 serverurl = CommonConfig.serverurl;

		 System.out.println("hello accesskey in savebooking:"+accesskey);

		 Loadcart loadcartobj = new Loadcart();
		 loadcartobj.Loadcartcallforfrontdesk(keytype);
		 String hotelid = loadcartobj.extractinghotelid();
		 String bookingid = loadcartobj.extracingbookingid();
		 String gueststayid = loadcartobj.extractinggueststayid();
		 
		 System.out.println(hotelid);
		 System.out.println(bookingid);
		 System.out.println(gueststayid);
		 
		 HttpResponse<JsonNode> responsesavebooking = Unirest.post(""+serverurl+"/ws/web/savebooking")
		 .header("content-type", "application/json")
		 .header("x-ig-sg", "D_gg%fkl85_j")
		 .header("cache-control", "no-cache")
		 .header("postman-token", "4bb5413d-2fd5-bbde-7d66-11c6df5ee394")
		 .body("{ \"hotelogix\": {"
		               + "\"version\": \"1.0\","
		               + "\"datetime\": \"2015-09-16T11:01:29\","
		               + " \"request\": {"
		               + " \"method\": \"savebooking\","
		               + "\"key\": \""+accesskey+"\","
		               + "\"languagecode\" :\"en\","
		               + "\"data\": {"
		               + "\"guest\": {"
		               + "\"fname\": {"
		               + "\"value\": \""+firstname+"\""
		               + "},"
		               + "\"lname\": {"
		               + "\"value\": \"Jha\""
		               + " },"
		               + "\"email\": {"
		               + "\"value\": \"mukesh.kumar@hotelogix.com\""
		               + " },"
		               + "\"phone\": {"
		               + "\"value\": \"2121221212121\""
		               + " },"
		               + "\"mobile\": {"
		               + "\"value\": \"988888989\""
		               + "},"
		               + "\"country\": {"
		               + "\"code\": \"IN\","
		               + "\"value\": \"India\""
		               + " },"
		               + "\"state\": {"
		               + "\"code\": \"UP\","
		               + "\"value\": \"noida\""
		               + " },"
		               + "\"address\": {"
		               + " \"value\": \"test address\""
		               + " },"
		               + "\"city\": {"
		               + "\"value\": \"noida\""
		               + " },"
		               + "\"zip\": {"
		               + "\"value\": \"12345A\""
		               + " },"
		               + "\"taxCode\": {"
		               + "\"value\": \"07AQSPJ0999EABC\""
		               + "},"
		               + "\"workDetail\": {"
		               + "\"address\": {"
		               + "\"value\": \"work address\""
		               + " },"
		               + "\"city\": {"
		               + "\"value\": \"work city\""
		               + "},"
		               + "\"state\": {"
		               + "\"code\": \"UP\","
		               + "\"value\": \"noida\""
		               + " },"
		               + "\"country\": {"
		               + "\"code\": \"IN\","
		               + "\"value\": \"India\""
		               + "},"
		               + "\"zip\": {"
		               + "\"value\": \"110096\""
		               + "},"
		               + "\"phone\": {"
		               + "\"value\": \"123456789\""
		               + " },"
		               + "\"mobile\": {"
		               + "\"value\": \"123456789\""
		               + "},"
		               + "\"fax\": {"
		               + "\"value\": \"123456789\""
		               + "}"
		               + "}"
		               + "},"
		               + "\"isGroupBooking\": {"
		               + "\"value\":"+Isgroupbookingvalue+""
		               + "},"
		               + "\"hotels\": "
		               + "["
		               + "{"
		               + "\"id\": \""+hotelid+"\","
		               + "\"preference\": {"
		               + "\"value\": \"nopreference\""
		               + "},"
		               + "\"bookings\": {"
		               + "\"booking\": {"
		               + "\"id\": \""+bookingid+"\","
		               + "\"gueststays\": "
		               + "["
		               + "{"
		               + "\"id\": \""+gueststayid+"\","
		               + "\"guest\": {"
		               + "\"fname\": {"
		               + "\"value\": \""+firstname+"\""
		               + " },"
		               + "\"lname\": {"
		               + "\"value\": \"upadhaya\""
		               + "},"
		               + "\"email\": {"
		               + "\"value\": \"mukesh.kumar@hotelogix.com\""
		               + "},"
		               + "\"phone\": {"
		               + "\"value\": \"9968040558\""
		               + "},"
		               + "\"mobile\": {"
		               + "\"value\": \"9968040558\""
		               + "},"
		               + "\"country\": {"
		               + "\"code\": \"IN\","
		               + "\"value\": \"India\""
		               + "},"
		               + "\"state\": {"
		               + "\"code\": \"br\","
		               + "\"value\": \"Bihar\""
		               + "},"
		               + "\"address\": {"
		               + "\"value\": \"mayur vihar\""
		               + "},"
		               + "\"city\": {"
		               + "\"value\": \"madhubani\""
		               + "},"
		               + "\"zip\": {"
		               + "\"value\": \"110091\""
		               + "},"
		               + "\"taxCode\": {"
		               + "\"value\": \"07AQSPJ0999EABC\""
		               + "},"
		               + "\"workDetail\": {"
		               + "\"address\": {"
		               + "\"value\": \"work address\""
		               + "},"
		               + "\"city\": {"
		               + "\"value\": \"work city\""
		               + "},"
		               + "\"state\": {"
		               + "\"code\": \"DE\","
		               + "\"value\": \"delhi\""
		               + "},"
		               + "\"country\": {"
		               + "\"code\": \"IN\","
		               + "\"value\": \"India\""
		               + "},"
		               + "\"zip\": {"
		               + "\"value\": \"110096\""
		               + "},"
		               + "\"phone\": {"
		               + "\"value\": \"123456789\""
		               + "},"
		               + "\"mobile\": {"
		               + "\"value\": \"123456789\""
		               + "},"
		               + "\"fax\": {"
		               + "\"value\": \"123456789\""
		               + "}}}}]"
		              
		               + "}}}]}}}}")
		  .asJson();
		 JsonNode body = responsesavebooking.getBody();
		 responseJSONString = body.toString();
		 System.out.println(responseJSONString);
		 }

		 catch(UnirestException e)
		 {
		 e.printStackTrace();
		 }
		 }
	 public void Savebookingforgroupresv(String s1){
		    keytype = s1;

		if(keytype == "wsauth")
		{

		String keyw = Wsauth.wsauthkeyfinalstring;
		accesskey = keyw;
		System.out.println("wsauthkey in savebooking:"+keyw);
		}

		else if(keytype == "login")
		{


		String keyl = Login.finalloginaccesskey;
		System.out.println("login key in savebooking:"+keyl);
		accesskey = keyl;
		}

		try
		{

		  String firstname=ApiUtils.GA().generateRandomString();
		//firstname="sneha";
		System.out.println("welcome to savebooking try block:");
		serverurl = CommonConfig.serverurl;

		System.out.println("hello accesskey in savebooking:"+accesskey);

		Loadcart loadcartobj = new Loadcart();
		loadcartobj.loadcartforgroupresv(keytype);
		String hotelid = loadcartobj.extractinghotelid();
		String bookingid = loadcartobj.extracingbookingid();
		String gueststayid = loadcartobj.extractinggueststayid();

		HttpResponse<JsonNode> responsesavebooking = Unirest.post(""+serverurl+"/ws/web/savebooking")
		.header("content-type", "application/json")
		.header("x-ig-sg", "D_gg%fkl85_j")
		.header("cache-control", "no-cache")
		.header("postman-token", "4bb5413d-2fd5-bbde-7d66-11c6df5ee394")
		.body("{ \"hotelogix\": {"
		              + "\"version\": \"1.0\","
		              + "\"datetime\": \"2015-09-16T11:01:29\","
		              + " \"request\": {"
		              + " \"method\": \"savebooking\","
		              + "\"key\": \""+accesskey+"\","
		              + "\"languagecode\" :\"en\","
		              + "\"data\": {"
		              + "\"guest\": {"
		              + "\"fname\": {"
		              + "\"value\": \""+firstname+"\""
		              + "},"
		              + "\"lname\": {"
		              + "\"value\": \"Jha\""
		              + " },"
		              + "\"email\": {"
		              + "\"value\": \"mukesh.kumar@hotelogix.com\""
		              + " },"
		              + "\"phone\": {"
		              + "\"value\": \"2121221212121\""
		              + " },"
		              + "\"mobile\": {"
		              + "\"value\": \"988888989\""
		              + "},"
		              + "\"country\": {"
		              + "\"code\": \"IN\","
		              + "\"value\": \"India\""
		              + " },"
		              + "\"state\": {"
		              + "\"code\": \"UP\","
		              + "\"value\": \"noida\""
		              + " },"
		              + "\"address\": {"
		              + " \"value\": \"test address\""
		              + " },"
		              + "\"city\": {"
		              + "\"value\": \"noida\""
		              + " },"
		              + "\"zip\": {"
		              + "\"value\": \"12345A\""
		              + " },"
		              + "\"taxCode\": {"
		              + "\"value\": \"07AQSPJ0999EABC\""
		              + "},"
		              + "\"workDetail\": {"
		              + "\"address\": {"
		              + "\"value\": \"work address\""
		              + " },"
		              + "\"city\": {"
		              + "\"value\": \"work city\""
		              + "},"
		              + "\"state\": {"
		              + "\"code\": \"UP\","
		              + "\"value\": \"noida\""
		              + " },"
		              + "\"country\": {"
		              + "\"code\": \"IN\","
		              + "\"value\": \"India\""
		              + "},"
		              + "\"zip\": {"
		              + "\"value\": \"110096\""
		              + "},"
		              + "\"phone\": {"
		              + "\"value\": \"123456789\""
		              + " },"
		              + "\"mobile\": {"
		              + "\"value\": \"123456789\""
		              + "},"
		              + "\"fax\": {"
		              + "\"value\": \"123456789\""
		              + "}"
		              + "}"
		              + "},"
		              + "\"isGroupBooking\": {"
		              + "\"value\":true"
		              + "},"
		              + "\"hotels\": "
		              + "["
		              + "{"
		              + "\"id\": \""+hotelid+"\","
		              + "\"preference\": {"
		              + "\"value\": \"nopreference\""
		              + "},"
		              + "\"bookings\": {"
		              + "\"booking\": {"
		              + "\"id\": \""+bookingid+"\","
		              + "\"gueststays\": "
		              + "["
		              + "{"
		              + "\"id\": \""+gueststayid+"\","
		              + "\"guest\": {"
		              + "\"fname\": {"
		              + "\"value\": \""+firstname+"\""
		              + " },"
		              + "\"lname\": {"
		              + "\"value\": \"upadhaya\""
		              + "},"
		              + "\"email\": {"
		              + "\"value\": \"mukesh.kumar@hotelogix.com\""
		              + "},"
		              + "\"phone\": {"
		              + "\"value\": \"9968040558\""
		              + "},"
		              + "\"mobile\": {"
		              + "\"value\": \"9968040558\""
		              + "},"
		              + "\"country\": {"
		              + "\"code\": \"IN\","
		              + "\"value\": \"India\""
		              + "},"
		              + "\"state\": {"
		              + "\"code\": \"br\","
		              + "\"value\": \"Bihar\""
		              + "},"
		              + "\"address\": {"
		              + "\"value\": \"mayur vihar\""
		              + "},"
		              + "\"city\": {"
		              + "\"value\": \"madhubani\""
		              + "},"
		              + "\"zip\": {"
		              + "\"value\": \"110091\""
		              + "},"
		              + "\"taxCode\": {"
		              + "\"value\": \"07AQSPJ0999EABC\""
		              + "},"
		              + "\"workDetail\": {"
		              + "\"address\": {"
		              + "\"value\": \"work address\""
		              + "},"
		              + "\"city\": {"
		              + "\"value\": \"work city\""
		              + "},"
		              + "\"state\": {"
		              + "\"code\": \"DE\","
		              + "\"value\": \"delhi\""
		              + "},"
		              + "\"country\": {"
		              + "\"code\": \"IN\","
		              + "\"value\": \"India\""
		              + "},"
		              + "\"zip\": {"
		              + "\"value\": \"110096\""
		              + "},"
		              + "\"phone\": {"
		              + "\"value\": \"123456789\""
		              + "},"
		              + "\"mobile\": {"
		              + "\"value\": \"123456789\""
		              + "},"
		              + "\"fax\": {"
		              + "\"value\": \"123456789\""
		              + "}}}}]"
		             
		              + "}}}]}}}}")
		//.body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"savebooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"languagecode\": \"en\",\r\n      \"data\": {\r\n        \"guest\": {\r\n          \"fname\": {\r\n            \"value\": \"Mukesh\"\r\n          },\r\n          \"lname\": {\r\n            \"value\": \"Jha\"\r\n          },\r\n          \"email\": {\r\n            \"value\": \"mukesh.kumar@hotelogix.com\"\r\n          },\r\n          \"phone\": {\r\n            \"value\": \"8802640811\"\r\n          },\r\n          \"mobile\": {\r\n            \"value\": \"8802640811\"\r\n          },\r\n          \"country\": {\r\n            \"code\": \"IN\",\r\n            \"value\": \"India\"\r\n          },\r\n          \"state\": {\r\n            \"code\": \"UP\",\r\n            \"value\": \"noida\"\r\n          },\r\n          \"address\": {\r\n            \"value\": \"test address\"\r\n          },\r\n          \"city\": {\r\n            \"value\": \"noida\"\r\n          },\r\n          \"zip\": {\r\n            \"value\": \"201301\"\r\n          }\r\n        },\r\n        \"hotels\": [\r\n          {\r\n            \"id\": \""+hotelid+"\",\r\n            \"preference\": {\r\n              \"value\": \"nopreference\"\r\n            },\r\n            \"bookings\": {\r\n              \"booking\": {\r\n                \"id\": \""+bookingid+"\",\r\n                \"gueststays\": {\r\n                  \"gueststay\": {\r\n                    \"id\": \""+gueststayid+"\",\r\n                    \"guest\": {\r\n                      \"fname\": {\r\n                        \"value\": \"akriti\"\r\n                      },\r\n                      \"lname\": {\r\n                        \"value\": \"mishra\"\r\n                      },\r\n                      \"email\": {\r\n                        \"value\": \"akriti@hotelogix.com\"\r\n                      },\r\n                      \"phone\": {\r\n                        \"value\": \"9968040558\"\r\n                      },\r\n                      \"mobile\": {\r\n                        \"value\": \"9968040558\"\r\n                      },\r\n                      \"country\": {\r\n                        \"code\": \"IN\",\r\n                        \"value\": \"India\"\r\n                      },\r\n                      \"state\": {\r\n                        \"code\": \"br\",\r\n                        \"value\": \"Bihar\"\r\n                      },\r\n                      \"address\": {\r\n                        \"value\": \"mayur vihar\"\r\n                      },\r\n                      \"city\": {\r\n                        \"value\": \"madhubani\"\r\n                      },\r\n                      \"zip\": {\r\n                        \"value\": \"110091\"\r\n                      }\r\n                    }\r\n                  }\r\n                }\r\n              }\r\n            }\r\n          }\r\n        ],\r\n        \"holdTill\": {\r\n          \"releaseAfter\": {\r\n            \"value\": \"20\",\r\n            \"type\": \"hour\"\r\n          }\r\n        }\r\n      }\r\n    }\r\n  }\r\n}")
		.asJson();
		JsonNode body = responsesavebooking.getBody();
		responseJSONString = body.toString();
		System.out.println(responseJSONString);
		}

		catch(UnirestException e)
		{
		e.printStackTrace();
		}
		}
	 public String extractingmessagesavebooking()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String savebookingstring;
			savebookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last savebooking msg is ::"+savebookingstring);
			return savebookingstring;
	 }// End of extractingmessagesavebooking() method
	 
	 public String extractingorderid()
	 {
		 System.out.println("welcome to extractingorderid:");
		  orderid = "testorderid"; 
		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 JSONObject orderobj = jsonResult.getJSONObject("hotelogix").getJSONObject("response");
		 orderid = orderobj.getJSONObject("order").getString("id").toString();
		 
		 System.out.println("order id in savebooking"+orderid);
		 return orderid;
	 }
	 
	 public String extractingbookingrid(){
		 String bookingid;
		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 String confirmbookingstring;
		 confirmbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		 JSONObject newresp = jsonResult.getJSONObject("hotelogix");
		 System.out.println(newresp);
		 JSONObject resp= newresp.getJSONObject("response");
		 System.out.println(resp);
		 JSONObject order=resp.getJSONObject("order");
		 String booking=order.get("bookings").toString();
		 Savebooking savebookingobj=new Savebooking();
		 JSONObject bookingjobj=savebookingobj.GetJsonObject(booking);
		 //JSONObject hotels=resp.getJSONArray("bookings").getJSONObject(0);
		 //JSONObject hotels=resp.getJSONObject("bookings");
		 //System.out.println(hotels);
		 bookingid=bookingjobj.get("id").toString();
		 System.out.println("The booking id is:" +bookingid);
		 //System.out.println("last createagent success"+confirmbookingstring);
		// return confirmbookingstring;
        
		 return bookingid;
		 
	 }
	 public String extracinggroupid(){

		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 String confirmbookingstring;
		 confirmbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		 JSONObject newresp = jsonResult.getJSONObject("hotelogix");
		 System.out.println("The new" +newresp);
		 JSONObject resp= newresp.getJSONObject("response");

		 JSONObject newstatus = resp.getJSONObject("order");

		 //JSONObject book1 = newstatus.getJSONObject("id");
		 JSONArray newarray = newstatus.getJSONArray("bookings");
		 JSONObject book2 = newarray.getJSONObject(0);
		 JSONObject newgrp = book2.getJSONObject("group");
		 String groupid = newgrp.getString("id");
		 System.out.println("The group id is" +groupid);
		 return groupid;



		 }
	 public String extractingbookingCode(){
		 String bookingcode;
		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 String confirmbookingstring;
		 confirmbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		 JSONObject newresp = jsonResult.getJSONObject("hotelogix");
		 System.out.println(newresp);
		 JSONObject resp= newresp.getJSONObject("response");
		 System.out.println(resp);
		 JSONObject order=resp.getJSONObject("order");
		 String booking=order.get("bookings").toString();
		 Savebooking savebookingobj=new Savebooking();
		 JSONObject bookingjobj=savebookingobj.GetJsonObject(booking);
		 //JSONObject hotels=resp.getJSONArray("bookings").getJSONObject(0);
		 //JSONObject hotels=resp.getJSONObject("bookings");
		 //System.out.println(hotels);
		 bookingcode=bookingjobj.get("code").toString();
		 System.out.println("The booking code is:" +bookingcode);
		 //System.out.println("last createagent success"+confirmbookingstring);
		// return confirmbookingstring;
        
		 return bookingcode;
		 
	 }
	 public String extractingGroupCode(){
		 String bookingcode;
		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 String confirmbookingstring;
		 confirmbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		 JSONObject newresp = jsonResult.getJSONObject("hotelogix");
		 System.out.println(newresp);
		 JSONObject resp= newresp.getJSONObject("response");
		 System.out.println(resp);
		 JSONObject order=resp.getJSONObject("order");
		 String booking=order.get("bookings").toString();
		 Savebooking savebookingobj=new Savebooking();
		 JSONObject bookingjobj=savebookingobj.GetJsonObject(booking);
		 //JSONObject hotels=resp.getJSONArray("bookings").getJSONObject(0);
		 //JSONObject hotels=resp.getJSONObject("bookings");
		 //System.out.println(hotels);
		 bookingcode=bookingjobj.get("groupcode").toString();
		 System.out.println("The Group code is:" +bookingcode);
		 //System.out.println("last createagent success"+confirmbookingstring);
		// return confirmbookingstring;
        
		 return bookingcode;
		 
	 }
	 public JSONObject GetJsonObject(String value) throws JSONException{
			String str=value.substring(1, value.length()-1);
			JSONObject bookingjobj=new JSONObject(str);
			return bookingjobj;
		}
	 public String extractingdeposittotal()
	 {
		 System.out.println("welcome to extractingdeposittotal:");
		 String deposittotal = "testdeposittotal";
		 String localresponseJSONString = responseJSONString;
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 deposittotal =jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("order").getJSONObject("deposittotal").get("amount").toString();
		 System.out.println("deposittotal in savebooking:");
		 return deposittotal;
	 }// End of extractingdeposittotal() method
}// End of class
