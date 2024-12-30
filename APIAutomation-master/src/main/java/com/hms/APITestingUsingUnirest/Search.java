package com.hms.APITestingUsingUnirest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.hms.APITestingUsingUnirest.CommonConfig;


public class Search
{
	static String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 String nightauditdate1;
	 String nightauditdate3;
	 String nexttonextnightauditdate;
	public String Today;
	 public String Tomorrow;
	 String nightauditdate4;
	 String Noofroom;
	String Noofadult;
	 public void getCurrentDate(){
		 LocalDate today = LocalDate.now();
		  System.out.println(today);
		  String Updatetoday=today.toString();
		  Today=Updatetoday;
			//adding one day to the localdate
			LocalDate tomorrow = today.plusDays(1);
		 System.out.println(tomorrow);
		 String updateTomorrow=tomorrow.toString();
		 Tomorrow=updateTomorrow;
	 //System.out.println(java.time.LocalDate.now());  
	 }
	 public void Searchcall(String s1)
	 {
		 System.out.println("hello searchcall");
		 getCurrentDate();
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
				System.out.println("wsauthkey in search:"+keyw);
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
				System.out.println("login key in search:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 	serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				
			 HttpResponse<JsonNode> responsesearch = Unirest.post(""+serverurl+"/ws/web/search")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ce256b7f-52b2-0c1b-838f-8720520fd81e")
					  .body("{\r\n    \"hotelogix\": {\r\n        \"version\": \"1.0\",\r\n        \"datetime\": \"2012-01-16T10:10:15\",\r\n        \"request\": {\r\n         \"method\": \"search\",\r\n         \"key\": \""+accesskey+"\",\r\n         \"languagecode\": \"en\",\r\n         \"data\": {\r\n           \"stay\": {\r\n             \"checkindate\": \""+Today+"\",\r\n             \"checkoutdate\": \""+Tomorrow+"\"\r\n           },\r\n           \"pax\": {\r\n             \"adult\": \"1\",\r\n             \"child\": \"0\",\r\n             \"infant\": \"0\"\r\n           },\r\n           \"searchfor\": {\"value\": \"TA\"},\r\n           \"ignorelists\": {\"cancellationpolicy\":[]},\r\n           \r\n           \"hotels\": [\r\n             {\r\n               \"id\": "+hotelid1+"\r\n             }\r\n           ],\r\n           \r\n           \"roomrequire\": 1,\r\n           \"limit\": {\r\n             \"value\": 20,\r\n             \"offset\": 0\r\n           }\r\n         }\r\n       }\r\n    }\r\n}")
					  .asJson();
			 JsonNode body = responsesearch.getBody();
			 System.out.println(body);
			 responseJSONString = body.toString();
			 System.out.println(responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 } 
		 
		 /*catch (ParseException e)
		 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	 }// End of Searchcall() method
	 public void Searchcallforgroupresv(String s1, String s2){
		 System.out.println("hello searchcall for frontdesk for group reservation");
		 getCurrentDate();
		 keytype = s1;
		 Noofadult = s2;
		 if(keytype == "wsauth")
		 {

		 String keyw = Wsauth.wsauthkeyfinalstring;
		 accesskey = keyw;
		 System.out.println("wsauthkey in search:"+keyw);
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
		 System.out.println("login key in search:"+keyl);
		 accesskey = keyl;
		 }

		 try
		 {
		 serverurl = CommonConfig.serverurl;
		 hotelid1 = CommonConfig.hotelid1;
		 nightauditdate1= CommonConfig.currentsystemdate;
		 nightauditdate4= CommonConfig.immediatenexttonextdate;



		 HttpResponse<JsonNode> responsesearch = Unirest.post(""+serverurl+"/ws/web/search")
		  .header("content-type", "application/json")
		  .header("x-ig-sg", "D_gg%fkl85_j")
		  .header("cache-control", "no-cache")
		  .header("postman-token", "ce256b7f-52b2-0c1b-838f-8720520fd81e")
		  .body("{\"hotelogix\":{ \"version\":\"1.0\","
		                 + "\"datetime\":\"2012-01-16T10:10:15\","
		                 + "\"request\":{"
		                 + "\"method\": \"search\","
		                 + "\"key\": \""+accesskey+"\","
		                 + "\"languagecode\": \"en\",\"data\": {"
		                 + "\"stay\": {"
		                 + "\"checkindate\": \""+nightauditdate1+"\","
		                 + "\"checkoutdate\": \""+nightauditdate4+"\" "
		                 + "},"
		                 + "\"pax\": {"
		                 + "\"adult\":"+Noofadult+","
		                 + "\"child\": \"0\", "
		                 + "\"infant\": \"0\" "
		                 + " },"
		                 + " \"hotels\": [ {"
		                 + "\"id\": "+hotelid1+""
		                 + "}],"
		                 + "\"roomrequire\": 2,"
		                 + "\"limit\": {"
		                 + "\"value\": 20,"
		                 + "\"offset\": 0 }}}}}")
		                     .asJson();

		 JsonNode body = responsesearch.getBody();
		 System.out.println(body);
		 responseJSONString = body.toString();
		 System.out.println("search for FD RESERVATION:"+responseJSONString);
		 }

		 catch(UnirestException e)
		 {
		 e.printStackTrace();
		 }

		  
		 }

	 public void SearchcallforFrontDeskfor3DResv(String s1){
		 System.out.println("hello searchcall for frontdesk for three days Reservation");
		 getCurrentDate();
		 keytype = s1;
		 if(keytype == "wsauth")
		 {

		 String keyw = Wsauth.wsauthkeyfinalstring;
		 accesskey = keyw;
		 System.out.println("wsauthkey in search:"+keyw);
		 }

		 else if(keytype == "login")
		 {


		 String keyl = Login.finalloginaccesskey;
		 System.out.println("login key in search:"+keyl);
		 accesskey = keyl;
		 }

		 try
		 {
		 serverurl = CommonConfig.serverurl;
		 hotelid1 = CommonConfig.hotelid1;
		 nightauditdate1= CommonConfig.currentsystemdate;
		 nightauditdate4= CommonConfig.immediatenexttonextdate;



		 HttpResponse<JsonNode> responsesearch = Unirest.post(""+serverurl+"/ws/web/search")
		  .header("content-type", "application/json")
		  .header("x-ig-sg", "D_gg%fkl85_j")
		  .header("cache-control", "no-cache")
		  .header("postman-token", "ce256b7f-52b2-0c1b-838f-8720520fd81e")
		  .body("{\"hotelogix\":{ \"version\":\"1.0\","
		                 + "\"datetime\":\"2012-01-16T10:10:15\","
		                 + "\"request\":{\"method\": \"search\","
		                 + "\"key\": \""+accesskey+"\","
		                 + "\"languagecode\": \"en\",\"data\": {"
		                 + "\"stay\": {"
		                 + "\"checkindate\": \""+nightauditdate1+"\","
		                 + "\"checkoutdate\": \""+nightauditdate4+"\" "
		                 + "},"
		                 + "\"pax\": {"
		                 + "\"adult\": \"1\","
		                 + "\"child\": \"1\", "
		                 + "\"infant\": \"1\" "
		                 + " },"
		                 + " \"hotels\": [ {"
		                 + "\"id\": "+hotelid1+""
		                 + "}],"
		                 + "\"roomrequire\": 1,"
		                 + "\"limit\": {"
		                 + "\"value\": 20,"
		                 + "\"offset\": 0 }}}}}")
		                     .asJson();

		 JsonNode body = responsesearch.getBody();
		 System.out.println(body);
		 responseJSONString = body.toString();
		 System.out.println("search fof FD RESERVATION:"+responseJSONString);
		 }

		 catch(UnirestException e)
		 {
		 e.printStackTrace();
		 }

		  
		 }
	 public void SearchcallforFrontDeskfututuredate(String s1,String s2){
		 System.out.println("hello searchcall for futuredate:::::::::::::::::::::::::::::::::::::::");
		 getCurrentDate();
		 keytype = s1;
		 Noofroom=s2;
		 if(keytype == "wsauth")
			{
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in search:"+keyw);
			}
			
			else if(keytype == "login")
			{
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in search:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 	serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1; 
				HttpResponse<JsonNode> responsesearch = Unirest.post(""+serverurl+"/ws/web/search")
						  .header("content-type", "application/json")
						  .header("x-ig-sg", "D_gg%fkl85_j")
						  .header("cache-control", "no-cache")
						  .header("postman-token", "ce256b7f-52b2-0c1b-838f-8720520fd81e")
						  .body("{\"hotelogix\":{ \"version\":\"1.0\","
			                		+ "\"datetime\":\"2012-01-16T10:10:15\","
			                		+ "\"request\":{\"method\": \"search\","
			                		+ "\"key\": \""+accesskey+"\","
			                		+ "\"languagecode\": \"en\",\"data\": {"
			                		+ "\"stay\": {"
			                		+ "\"checkindate\": \""+Today+"\","
			                		+ "\"checkoutdate\": \""+Tomorrow+"\" "
			                		+ "},"
			                		+ "\"pax\": {"
			                		+ "\"adult\": \"2\","
			                		+ "\"child\": \"0\", "
			                		+ "\"infant\": \"0\" "
			                		+ " },"
			                		+ " \"hotels\": [ {"
			                		+ "\"id\": "+hotelid1+""
			                		+ "}],"
			                		+ "\"roomrequire\":"+Noofroom+","
			                		+ "\"limit\": {"
			                		+ "\"value\": 20,"
			                		+ "\"offset\": 0 }}}}}")
			                     .asJson();
						 
				 JsonNode body = responsesearch.getBody();
				 System.out.println(body);
				 responseJSONString = body.toString();
				 System.out.println("search fof FD RESERVATION:"+responseJSONString);
				 System.out.println("i am here and printing search call for furture dates::::::::::::::::::::::::::::::"+responseJSONString);
			 }
			 
			 catch(UnirestException e)
			 {
				 e.printStackTrace();
			 } 
	 }
	 public void SearchcallforFrontDesk(String s1){
		 System.out.println("hello searchcall for frontdesk");
		 getCurrentDate();
		 keytype = s1;
		 if(keytype == "wsauth")
			{
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in search:"+keyw);
			}
			
			else if(keytype == "login")
			{
				
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in search:"+keyl);
				accesskey = keyl;
			}
		 
		 try
		 {
			 	serverurl = CommonConfig.serverurl;
				hotelid1 = CommonConfig.hotelid1;
				nightauditdate1= CommonConfig.currentsystemdate;
				nightauditdate3= CommonConfig.immediatenextdate;
				
			
				
			 HttpResponse<JsonNode> responsesearch = Unirest.post(""+serverurl+"/ws/web/search")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ce256b7f-52b2-0c1b-838f-8720520fd81e")
					  .body("{\"hotelogix\":{ \"version\":\"1.0\","
		                		+ "\"datetime\":\"2012-01-16T10:10:15\","
		                		+ "\"request\":{\"method\": \"search\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"languagecode\": \"en\",\"data\": {"
		                		+ "\"stay\": {"
		                		+ "\"checkindate\": \""+nightauditdate1+"\","
		                		+ "\"checkoutdate\": \""+nightauditdate3+"\" "
		                		+ "},"
		                		+ "\"pax\": {"
		                		+ "\"adult\": \"2\","
		                		+ "\"child\": \"0\", "
		                		+ "\"infant\": \"0\" "
		                		+ " },"
		                		+ " \"hotels\": [ {"
		                		+ "\"id\": "+hotelid1+""
		                		+ "}],"
		                		+ "\"roomrequire\": 1,"
		                		+ "\"limit\": {"
		                		+ "\"value\": 20,"
		                		+ "\"offset\": 0 }}}}}")
		                     .asJson();
					 
			 JsonNode body = responsesearch.getBody();
			 System.out.println(body);
			 responseJSONString = body.toString();
			 System.out.println("search fof FD RESERVATION:"+responseJSONString);
		 }
		 
		 catch(UnirestException e)
		 {
			 e.printStackTrace();
		 } 
		 
		  
	 }
	 public void Searchcallforgroupresv(String s1){
		 System.out.println("hello searchcall for frontdesk for group reservation");
		 getCurrentDate();
		 keytype = s1;
		 if(keytype == "wsauth")
		 {

		 String keyw = Wsauth.wsauthkeyfinalstring;
		 accesskey = keyw;
		 System.out.println("wsauthkey in search:"+keyw);
		 }

		 else if(keytype == "login")
		 {


		 String keyl = Login.finalloginaccesskey;
		 System.out.println("login key in search:"+keyl);
		 accesskey = keyl;
		 }

		 try
		 {
		 serverurl = CommonConfig.serverurl;
		 hotelid1 = CommonConfig.hotelid1;
		 nightauditdate1= CommonConfig.currentsystemdate;
		 nightauditdate4= CommonConfig.immediatenexttonextdate;



		 HttpResponse<JsonNode> responsesearch = Unirest.post(""+serverurl+"/ws/web/search")
		  .header("content-type", "application/json")
		  .header("x-ig-sg", "D_gg%fkl85_j")
		  .header("cache-control", "no-cache")
		  .header("postman-token", "ce256b7f-52b2-0c1b-838f-8720520fd81e")
		  .body("{\"hotelogix\":{ \"version\":\"1.0\","
		                 + "\"datetime\":\"2012-01-16T10:10:15\","
		                 + "\"request\":{"
		                 + "\"method\": \"search\","
		                 + "\"key\": \""+accesskey+"\","
		                 + "\"languagecode\": \"en\",\"data\": {"
		                 + "\"stay\": {"
		                 + "\"checkindate\": \""+nightauditdate1+"\","
		                 + "\"checkoutdate\": \""+nightauditdate4+"\" "
		                 + "},"
		                 + "\"pax\": {"
		                 + "\"adult\": \"2\","
		                 + "\"child\": \"0\", "
		                 + "\"infant\": \"0\" "
		                 + " },"
		                 + " \"hotels\": [ {"
		                 + "\"id\": "+hotelid1+""
		                 + "}],"
		                 + "\"roomrequire\": 2,"
		                 + "\"limit\": {"
		                 + "\"value\": 20,"
		                 + "\"offset\": 0 }}}}}")
		                     .asJson();

		 JsonNode body = responsesearch.getBody();
		 System.out.println(body);
		 responseJSONString = body.toString();
		 System.out.println("search fof FD RESERVATION:"+responseJSONString);
		 }

		 catch(UnirestException e)
		 {
		 e.printStackTrace();
		 }

		  
		 }
	 public String extractingmessagesearch()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			String searchstring;
			searchstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			System.out.println("at last search success:"+searchstring);
			return searchstring;
	 }// End of extractingmessagesearch()
	 
	 public String extractingrateid()
	 {
		 String localresponseJSONString = responseJSONString;
		 System.out.println("I am hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		 System.out.println("Printingggggggggggggggggggggggggggggggggggggggg"+responseJSONString);
		 JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 String rateid="";
		 JSONArray hotelsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("hotels");
		 System.out.println("This is Hotel's array::::::::::"+hotelsArray);
		 
		 JSONArray roomtypesArray = hotelsArray.getJSONObject(0).getJSONArray("roomtypes");
		 System.out.println("This is room type array:::::"+roomtypesArray);
		  
		JSONArray ratesArray = roomtypesArray.getJSONObject(0).getJSONArray("rates");
		  System.out.println(ratesArray);
		  
		  JSONObject ratesobject = ratesArray.getJSONObject(0);
		  rateid = ratesobject.get("id").toString();
		  System.out.println(rateid);
		 
		 return rateid;
	 }
	 
	 
}// End of class

