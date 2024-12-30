package com.hms.APITestingUsingUnirest;

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
import com.hms.APITestingUsingUnirest.Generic.Log4j;

public class Getinvoices
{

static String responseJSONString;

String serverurl;
String hotelid1;
String keytype;
String accesskey;
String restype;
String mainid;
String itemid;
static String amount;
String bookingid;
static String paid;
static String guestID="";

public void Getinvoicescall(String s1, String s2)
{
keytype = s1;

restype = s2;
System.out.println("keytype:"+s1);
System.out.println("restype:"+s2);



if(keytype == "wsauth")
{

String keyw = Wsauth.wsauthkeyfinalstring;
accesskey = keyw;
System.out.println("wsauthkey in getinvoices:"+keyw);

}

else if(keytype == "login")
{

String keyl = Login.finalloginaccesskey;
Log4j.info("login key in getinvoices:"+keyl);
//System.out.println();
accesskey = keyl;
}

try
{
serverurl = CommonConfig.serverurl;

GeneratePerformaInvoice generateperformainvoiceobj = new GeneratePerformaInvoice();
generateperformainvoiceobj.GeneratePerformaInvoiceAPI(keytype, restype);
mainid = GeneratePerformaInvoice.mainid;
guestID=GeneratePerformaInvoice.guestID;
System.out.println("Hi");

HttpResponse<JsonNode> responsegetinvoices = Unirest.post(""+serverurl+"/ws/web/getinvoices")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "c6c8cdea-3cc5-7953-79c7-1d9850356bd2")
 .body("{\r\n  \"hotelogix\": {\r\n    \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"getinvoices\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"id\": \""+mainid+"\",\r\n         \"type\":\""+restype+"\"\r\n       }\r\n    }\r\n  }\r\n}")
 .asJson();

JsonNode body = responsegetinvoices.getBody();
responseJSONString = body.toString();
System.out.println("getinvoices response"+responseJSONString);
}

catch(UnirestException e)
{
e.printStackTrace();
}
}//End of Getinvoicescall() method

public void GetinvoiceswithExistingbookingid(String s1, String s2, String s3)
{
keytype = s1;
bookingid = s2;
restype = s3;
System.out.println("keytype:"+s1);
System.out.println("restype:"+s2);


if(keytype == "wsauth")
{

String keyw = Wsauth.wsauthkeyfinalstring;
accesskey = keyw;
System.out.println("wsauthkey in getinvoices:"+keyw);

}

else if(keytype == "login")
{


String keyl = Login.finalloginaccesskey;
System.out.println("login key in getinvoices:"+keyl);
accesskey = keyl;
}

try
{
serverurl = CommonConfig.serverurl;

HttpResponse<JsonNode> responsegetinvoices = Unirest.post(""+serverurl+"/ws/web/getinvoices")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "c6c8cdea-3cc5-7953-79c7-1d9850356bd2")
 .body("{"
                + "\"hotelogix\": {"
                + "\"version\": \"1.0\","
                + "\"datetime\": \"2012-01-16T10:10:15\","
                + "\"request\": {"
                + "\"method\": \"getinvoices\","
                + "\"key\": \""+accesskey+"\","
                + "\"data\": {"
                       + "\"id\": \""+bookingid+"\","
                + "\"type\": \""+restype+"\""
               
                      + "}}}}")
                   .asJson();
JsonNode body = responsegetinvoices.getBody();
responseJSONString = body.toString();
System.out.println("GetInvoices Response:" +responseJSONString);
}

catch(UnirestException e)
{
e.printStackTrace();
}

}

public String extractingmessagegetinvoices()
{
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
String getinvoicesstring;
getinvoicesstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
System.out.println("at last getinvoices success :"+getinvoicesstring);
return getinvoicesstring;
}// End of extractingmessagegetinvoices() method

public String extractinginvoiceid()
{
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
String getinvoicestring;
JSONArray getinvoicesarray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("invoices");
JSONObject invoiceid = getinvoicesarray.getJSONObject(0);
JSONArray itemidarr = invoiceid.getJSONArray("lineitems");
JSONObject  itemidobj = itemidarr.getJSONObject(0);
//Extracting amount
amount = itemidobj.getString("amount");
//Extracting itemid
itemid = itemidobj.getString("itemId");
getinvoicestring = invoiceid.getString("invoiceId");
//paid = invoiceid.getString("paid");
System.out.println("get invoices invoice id:"+getinvoicestring);
return getinvoicestring;
}

public String extractinginvoiceidsecond()
{
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
String getinvoicestring;
JSONArray getinvoicesarray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("invoices");
JSONObject invoiceid = getinvoicesarray.getJSONObject(1);
JSONArray itemidarr = invoiceid.getJSONArray("lineitems");
JSONObject  itemidobj = itemidarr.getJSONObject(1);
//Extracting amount
amount = itemidobj.getString("amount");
//Extracting itemid
itemid = itemidobj.getString("itemId");
getinvoicestring = invoiceid.getString("invoiceId");
//paid = invoiceid.getString("paid");
System.out.println("get invoices invoice id:"+getinvoicestring);
return getinvoicestring;
}
}