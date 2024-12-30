package com.hms.APITestingUsingUnirest;

import java.time.LocalDateTime;
import java.time.Year;

import org.json.JSONObject;

import com.hms.APITestingUsingUnirest.Generic.ApiUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class EditAgent {

static String responseJSONString;
String serverurl;
String hotelid1;
String keytype;
String accesskey;
public static String AgentName;

public void editagentcall(String s)
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
System.out.println("hello try editagent");
serverurl = CommonConfig.serverurl;
hotelid1 = CommonConfig.hotelid1;
//String uniqueID = UUID.randomUUID().toString();
//String email = uniqueID+"hotelogix.com";
//String str1 = uniqueID.substring(0,9);
//System.out.println("substring"+str1);
// Get the current date and time
     LocalDateTime currentTime = LocalDateTime.now();
     System.out.println("Current DateTime: " + currentTime);
     int year = Year.now().getValue()+1;
     System.out.println(year);
     
     Createagent createagentobj = new Createagent();
     createagentobj.Createagentcall("wsauth");
     String AgentID=createagentobj.extractingAgentID();
     
     
     
     AgentName=ApiUtils.GA().generateRandomString();
     
     

HttpResponse<JsonNode> responseeditagent = Unirest.post(""+serverurl+"/ws/web/editagent")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "ebf56e64-e308-b8b7-bd77-65d49111c1e0")
 
 .body("{"
        + "\"hotelogix\": {"
        + "\"version\": \"1.0\","
        + "\"datetime\": \"2012-01-16T10:10:15\","
        + "\"request\": {"
        + "\"method\": \"editagent\","
        + "\"key\": \""+accesskey+"\","
        + "\"data\": {"
        + "\"businessName\": \""+AgentName+"\","
        + "\"addresses\": {"
        + "\"office\": {"
        + "\"address\": \"abcd\","
        + "\"country\": \"India\","
        + "\"state\": \"delhi\","
        + "\"city\": \"delhi\","
        + "\"zip\": \"123\""
        + "},"
        + "\"billing\": {"
        + "\"companyName\": \"billingCompany\","
        + "\"address\": \"billingAddress\","
        + "\"country\": \"India\","
        + "\"state\": \"Punjab\","
        + "\"city\": \"delhi\","
        + "\"zip\": \"123456\""
        + " }"
        + "},"
        + "\"contacts\": {"
        + "\"personal\": {"
        + "\"salutation\": \"Mr\","
        + "\"fName\": \""+AgentName+"\","
        + "\"lName\": \"Miara\","
        + "\"designation\": \"Manager\","
        + "\"phoneNo\": \"Manager\","
        + "\"phoneExtension\": \"101\","
        + "\"faxNo\": \"101\","
        + "\"email\": \"REINA@stayezee.com\","
        + "\"mobileNo\": \"123\","
        + "\"gender\": \"M\","
        + "\"dob\": \"2016-03-01\","
        + "\"website\": \"www.hotelogix.com\""
        + "},"
        + "\"billing\": {"
        + "\"salutation\": \"Mr\","
        + "\"fName\": \"RAIIII\","
        + "\"lName\": \"billing\","
        + "\"designation\": \"Manager\","
        + "\"phoneNo\": \"Manager\","
        + "\"phoneExtension\": \"101\","
        + "\"faxNo\": \"101\","
        + "\"email\": \"REINA@stayezee.com\","
        + "\"mobileNo\": \"123\","
        + "\"gender\": \"M\","
        + "\"dob\": \"2016-03-01\","
        + "\"website\": \"www.hotelogix.com\""
        + "}"
        + "},"
        + "\"creditCardDetail\": {"
        + "\"nameOnCard\": \"Mohan\","
        + "\"cardNo\": \"4111111111111111\","
        + "\"cardType\": \"Visa\","
        + "\"expiryMonth\": \"05\","
        + "\"expiryYear\": \"2029\","
        + "\"cvc\": \"101\","
        + "\"address\": \"creditCardAddress\","
        + "\"country\": \"India\","
        + "\"state\": \"Delhi\","
        + "\"city\": \"Delhi\","
        + "\"zip\": \"20160301\""
        + "},"
        + "\"creditLimit\": \"100\","
        + "\"paymentTerms\": \"5\","
        + "\"commissionType\": \"PV\","
        + "\"commission\": \"5\","        
        + "\"isCreditAllowed\":false,"
        + "\"defaultCustomTagId\":\"X8k|\","
        + "\"hotels\": ["
        + "{"
        + "\"id\": \""+hotelid1+"\","
        + "\"agentIds\": ["
        + "\""+AgentID+"\""
        + "]}"        
        + "]}}}}")
       
       
 .asJson();


JsonNode body = responseeditagent.getBody();
System.out.println("Edit agent response:::"+body);
responseJSONString = body.toString();
}
catch(UnirestException e)
{
e.printStackTrace();
}
}// end of Createagentcall() method

public String extractingmessageeditagent()
{
String localresponseJSONString = responseJSONString;
System.out.println("This is new:" +localresponseJSONString);
JSONObject jsonResult = new JSONObject(localresponseJSONString);
System.out.println("This is jsonresult:" +jsonResult );
String editagentsuccessstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
System.out.println("Edit Agent success:"  +editagentsuccessstring);
return editagentsuccessstring;
}
}

