package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.UUID;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.hms.APITestingUsingUnirest.CommonConfig;

public class Createcorporate
{
	static String responseJSONString;
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	public void Createcorporatecall(String s)
	{
		keytype = s;
		
		if(keytype == "wsauth")
		{
			Wsauth objwsauth = new Wsauth();
			objwsauth.Wsauthcall();
			String keyw = objwsauth.extractingWsauthKey();
			accesskey = keyw;
		}
		
		else if(keytype == "login")
		{
			Login objlogin = new Login();
			objlogin.Logincall();
			String keyl = objlogin.extractingLoginKey();
			accesskey = keyl;
		}
		
		try
		{
			System.out.println("hello try createcorporate");
			serverurl = CommonConfig.serverurl;
			hotelid1 = CommonConfig.hotelid1;
			
			String uniqueID = UUID.randomUUID().toString();
			//String email = uniqueID+"hotelogix.com";
			String str1 = uniqueID.substring(0,9);
			System.out.println("substring"+str1);
			
			// Get the current date and time
			LocalDateTime currentTime = LocalDateTime.now();
			System.out.println("Current DateTime: " + currentTime);
			int year = Year.now().getValue()+1;
			System.out.println(year);
			
			
			HttpResponse<JsonNode> responsecreatecorporate = Unirest.post(""+serverurl+"/ws/web/createcorporate")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebd95f0a-de03-4216-8ded-2dcf5edddf95")
					  .body("{\r\n   \"hotelogix\": {\r\n       \"version\": \"1.0\",\r\n       \"datetime\": \"2012-01-16T10:11:15\",\r\n       \"request\": {\r\n           \"method\": \"createcorporate\",\r\n           \"key\": \""+accesskey+"\",\r\n           \"data\": {\r\n               \r\n               \"businessName\": \""+uniqueID+"\",\r\n               \"addresses\": {\r\n                       \"office\": {\r\n                               \"address\": \"Noida\",\r\n                               \"country\": \"India\",\r\n                               \"state\": \"delhi\",\r\n                               \"city\": \"delhi\",\r\n                               \"zip\": \"123\"\r\n                       },\r\n                       \"billing\": {\r\n                               \"companyName\": \"billingCompany\",\r\n                               \"address\": \"abcd\",\r\n                               \"country\": \"India\",\r\n                               \"state\": \"Punjab\",\r\n                               \"city\": \"delhi\",\r\n                               \"zip\": \"123\"\r\n                       }\r\n               },\r\n               \"contacts\": {\r\n                       \"personal\": {\r\n                               \"salutation\": \"Mr\",\r\n                               \"fName\": \"Mohan\",\r\n                               \"lName\": \"Mishra\",\r\n                               \"designation\": \"Manager\",\r\n                               \"phoneNo\": \"Manager\",\r\n                               \"phoneExtension\": \"101\",\r\n                               \"faxNo\": \"101\",\r\n                               \"email\": \""+uniqueID+"@hotelogix.com"+"\",\r\n                               \"mobileNo\": \"123\",\r\n                               \"gender\": \"M\",\r\n                               \"dob\": \"2016-09-27\",\r\n                               \"website\": \"www.hotelogix.com\"\r\n                       },\r\n                       \"billing\": {\r\n                               \"salutation\": \"Mr\",\r\n                               \"fName\": \"Mohan\",\r\n                               \"lName\": \"Mishra\",\r\n                               \"designation\": \"Manager\",\r\n                               \"phoneNo\": \"Manager\",\r\n                               \"phoneExtension\": \"101\",\r\n                               \"faxNo\": \"101\",\r\n                               \"email\": \""+uniqueID+"@hotelogix.com"+"\",\r\n                               \"mobileNo\": \"8802640811\",\r\n                               \"gender\": \"M\",\r\n                               \"dob\": \"2016-09-27\",\r\n                               \"website\": \"www.hotelogix.com\"\r\n                       }\r\n               },\r\n               \"creditCardDetail\": {\r\n                       \"nameOnCard\": \"Mukesh\",\r\n                       \"cardNo\": \"4111111111111111\",\r\n                       \"cardType\": \"Visa\",\r\n                       \"expiryMonth\": \"09\",\r\n                       \"expiryYear\": \""+year+"\",\r\n                       \"cvc\": \"101\",\r\n                       \"address\": \"creditCardAddress\",\r\n                       \"country\": \"India\",\r\n                       \"state\": \"Delhi\",\r\n                       \"city\": \"Delhi\",\r\n                       \"zip\": \"20160301\"\r\n               },\r\n               \"creditLimit\": \"100\",\r\n               \"paymentTerms\": \"5\"\r\n              \r\n           }\r\n       }\r\n   }\r\n}")
					  .asJson();
			JsonNode body = responsecreatecorporate.getBody();
			System.out.println("createcorporate response::"+body);
			responseJSONString = body.toString();
		}
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}// end of createcorporatecall() method
	
	public String extractingmessagecreatecorporate()
	{
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		String createagentsuccessstring;
		createagentsuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
		System.out.println("last createagent success"+createagentsuccessstring);
		return createagentsuccessstring;
	}
	
}
