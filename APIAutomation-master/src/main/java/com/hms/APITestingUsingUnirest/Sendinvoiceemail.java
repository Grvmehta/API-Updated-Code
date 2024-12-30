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

public class Sendinvoiceemail
{

static String responseJSONString;
String serverurl;
String hotelid1;
String keytype;
String accesskey;
String restype;
String invoiceid;
String bookingid;
String bookingcode;
String groupid;


public void Sendinvoiceemailcall(String s1, String s2)
{
keytype = s1;
restype = s2;

if(keytype == "wsauth")

{
System.out.println("hello if sendinvoiceemail:");

String keyw = Wsauth.wsauthkeyfinalstring;
accesskey = keyw;
System.out.println("wsauthkey in sendinvoiceemail:"+keyw);
}

else if(keytype == "login")
{
String keyl = Login.finalloginaccesskey;
System.out.println("login key in sendinvoiceemail:"+keyl);
accesskey = keyl;
}

try
{
serverurl = CommonConfig.serverurl;
hotelid1 = CommonConfig.hotelid1;
System.out.println("hello try"+accesskey);
if(restype== "S"){
	
	GuestCheckin guestcheckinobj=new GuestCheckin();
	guestcheckinobj.GuestCheckincall("login" ,"false", "S");
	guestcheckinobj.extractingguestStayId();
	bookingid = GuestCheckin.BookingID;
	bookingcode = GuestCheckin.Bookingcode;
	Addothercharge addotherchargeboj = new Addothercharge();
	addotherchargeboj.Addotherchargecallwithnewreservation("login", "S");
	
	GeneratePerformaInvoice generateperformainvoiceobj = new GeneratePerformaInvoice();
	generateperformainvoiceobj.GeneratePerformaInvoiceAPI("login", "S", bookingid);
	invoiceid = GeneratePerformaInvoice.InvoiceID;
	
/*
Getinvoices getinvoicesobj = new Getinvoices();
getinvoicesobj.Getinvoicescall("login","S");
invoiceid = getinvoicesobj.extractinginvoiceid();*/
}
else if(restype== "G"){
/*Getinvoices getinvoicesobj = new Getinvoices();
getinvoicesobj.Getinvoicescall("login","G");
invoiceid = getinvoicesobj.extractinginvoiceid();*/
	
	GuestCheckin guestcheckinobj=new GuestCheckin();
	guestcheckinobj.GuestCheckincall("login" ,"true", "G");
	guestcheckinobj.extractingguestStayId();
	guestcheckinobj.extractingguestStayIdforgroup();
	bookingid = GuestCheckin.BookingID;
	bookingcode = GuestCheckin.groupcode;
	Addothercharge addotherchargeboj = new Addothercharge();
	addotherchargeboj.Addotherchargecallwithnewreservation("login", "G");
	
	Getgroup getgroupobj = new Getgroup();
	getgroupobj.Getgroupcall("login", bookingcode);
	getgroupobj.extractingrateidfromgetgroup();

	groupid = getgroupobj.extractingGOUPPID();
	GeneratePerformaInvoice generateperformainvoiceobj = new GeneratePerformaInvoice();
	generateperformainvoiceobj.GeneratePerformaInvoiceAPI("login", "G", groupid);
	
	invoiceid = GeneratePerformaInvoice.InvoiceID;	
}

HttpResponse<JsonNode> responsesendinvoiceemail = Unirest.post(""+serverurl+"/ws/web/sendinvoiceemail")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "0190e7db-adae-6d84-065a-ef776b855f23")
 .body("{"
                + "\"hotelogix\": {"
                + "\"version\": \"1.0\","
                + "\"datetime\": \"2012-01-16T10:10:15\","
                + "\"request\": {"
                + "\"method\": \"sendinvoiceemail\","            
                + "\"key\": \""+accesskey+"\","  
                + "\"languagecode\": \"en\","
                + "\"data\": {"    
                + "\"name\": \"Himani Puri\","
                + "\"email\": \"mukesh.kumar@Stayezee.com\","
                + "\"message\": \"Your Invoice has been generated.\","
                + "\"mailToFolioOwner\": false,"
                + "\"invoices\": ["
                + "{"
                + "\"id\": \""+invoiceid+"\""  
                + "}]"               
                + "}}}}")
 .asJson();

JsonNode body = responsesendinvoiceemail.getBody();
responseJSONString = body.toString();
System.out.println(responseJSONString);
}

catch(UnirestException e)
{
e.printStackTrace();
}

}// End of Sendinvoiceemailcall() method

public String extractingmessagesendinvoiceemail()
{
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
String sendinvoiceemailstring;
sendinvoiceemailstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
System.out.println("at last sendinvoiceemail success::"+sendinvoiceemailstring);
return sendinvoiceemailstring;
}// End of extractingmessagesendinvoiceemail() method
}// End of class

