package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.hms.APITestingUsingUnirest.Generic.Log4j;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Addothercharge {

	
String posid;
static String bookingid;
String discountamt;
String responseJSONString;
String serverurl;
String hotelid1;
String keytype;
String accesskey;
String message;
static String groupcode;
String GroupId;
static String bookingcode;
static String productid;
String restype;
String GuestDetailsId;
static String guestid;

public void Addotherchargecall(String s1){
keytype = s1;

if(keytype == "wsauth")

{
Log4j.info("hello to Addothercharge block:");

String keyw = Wsauth.wsauthkeyfinalstring;
accesskey = keyw;
Log4j.info("wsauthkey in Addothercharge:"+keyw);
}

else if(keytype == "login")
{
String keyl = Login.finalloginaccesskey;
Log4j.info("login key in Addothercharge:"+keyl);
accesskey = keyl;
}
try{
   serverurl = CommonConfig.serverurl;
hotelid1 = CommonConfig.hotelid1;
Log4j.info("Here is the access key" +accesskey);

Getbookings getbookingsobj = new Getbookings();
getbookingsobj.Getbookingscall("login","CHECKIN");
bookingid= getbookingsobj.extractingmainid();
guestid=getbookingsobj.extractingguestStayId();
bookingcode = getbookingsobj.extractingcode();
Log4j.info("Booking Id for getbookings:" +bookingid);
Getpos getposobj=new Getpos();
getposobj.Getposcall(keytype);
posid=getposobj.extractingposid();
Getposproduct getposproductobj=new Getposproduct();
getposproductobj.Getposproductcall(keytype);
productid=getposproductobj.extractingposproductid();


HttpResponse<JsonNode> responseaddothercharge = Unirest.post(""+serverurl+"/ws/web/addothercharge")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
 .body("{ \"hotelogix\": {"
                + "\"version\": \"1.0\","
                + "\"datetime\": \"2015-09-16T11:01:29\","
                + " \"request\": {"
                + " \"method\": \"addothercharge\","
                + "\"key\": \""+accesskey+"\","
                + "\"data\": {"
                + "\"chargeType\": \"room\","
                + "\"bookingId\": \""+bookingid+"\","
                + "\"posId\": \""+posid+"\","
                + "\"productId\": \""+productid+"\","
                + "\"quantity\": \"1\""            
                + "}}}}")
 .asJson();
JsonNode body = responseaddothercharge.getBody();
responseJSONString = body.toString();
Log4j.info("Add Other Charge:" +responseJSONString);
}catch(UnirestException e)
{
e.printStackTrace();
}
finally{
Commiteditbooking commitedbookingobj = new Commiteditbooking();
commitedbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", bookingid);
}

}
public void AddotherchargecallWithGroupChargetype(String s1) {
keytype = s1;

if(keytype == "wsauth")

{
Log4j.info("hello to Addothercharge block:");

String keyw = Wsauth.wsauthkeyfinalstring;
accesskey = keyw;
Log4j.info("wsauthkey in Addothercharge:"+keyw);
}

else if(keytype == "login")
{
String keyl = Login.finalloginaccesskey;
Log4j.info("login key in Addothercharge:"+keyl);
accesskey = keyl;
}
try{
   serverurl = CommonConfig.serverurl;
hotelid1 = CommonConfig.hotelid1;
Log4j.info("hello try"+accesskey);
Getbookings getbookingsobj = new Getbookings();
getbookingsobj.Getbookingscall("login","CHECKIN");
groupcode=getbookingsobj.extractinggroupcode();
/*editbookingobj.Editbookingcall("login","G","CHECKIN");
bookingid=editbookingobj.extracttinggroupid();
Log4j2Example.info("Booking id from editbooking:"+bookingid);*/
Getpos getposobj=new Getpos();
getposobj.Getposcall(keytype);
posid=getposobj.extractingposid();
Log4j.info("pos id is : "+posid);
Getposproduct getposproductobj=new Getposproduct();
getposproductobj.Getposproductcall(keytype);
String productid=getposproductobj.extractingposproductid();
Log4j.info("pos product id is: "+productid);
Getgroup Getgroupobj=new Getgroup();
Getgroupobj.Getgroupcall("login", groupcode);
GroupId = Getgroupobj.extractinggetgroupidmain();
bookingid = Getgroupobj.extractinggetgroupidmain();

Log4j.info("Group Other charge:::: "+GroupId);
Log4j.info("pGroup Other charge:::: "+bookingid);

HttpResponse<JsonNode> responseaddothercharge = Unirest.post(""+serverurl+"/ws/web/addothercharge")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
 .body("{ \"hotelogix\": {"
                + "\"version\": \"1.0\","
                + "\"datetime\": \"2015-09-16T11:01:29\","
                + " \"request\": {"
                + " \"method\": \"addothercharge\","
                + "\"key\": \""+accesskey+"\","
                + "\"data\": {"
                + "\"chargeType\": \"group\","
                + "\"groupId\": \""+GroupId+"\","
                + "\"posId\": \""+posid+"\","
                + "\"productId\": \""+productid+"\","
                + "\"quantity\": \"1\","
                + "\"discount\": \""+discountamt+"\","
                + "\"discountType\": \"FV\""
                + "}}}}")
 .asJson();
JsonNode body = responseaddothercharge.getBody();
responseJSONString = body.toString();
Log4j.info(responseJSONString);
}catch(UnirestException e)
{
e.printStackTrace();
}
finally{
Commiteditbooking commitedbookingobj = new Commiteditbooking();
commitedbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", GroupId);
}

}
public void AddotherchargecallWithGuestChargetype(String s1) {
keytype = s1;

if(keytype == "wsauth")

{
Log4j.info("hello to Addothercharge block:");

String keyw = Wsauth.wsauthkeyfinalstring;
accesskey = keyw;
Log4j.info("wsauthkey in Addothercharge:"+keyw);
}

else if(keytype == "login")
{
String keyl = Login.finalloginaccesskey;
Log4j.info("login key in Addothercharge:"+keyl);
accesskey = keyl;
}
try{
   serverurl = CommonConfig.serverurl;
hotelid1 = CommonConfig.hotelid1;
Log4j.info("hello try"+accesskey);
Getbookings getbookingsobj = new Getbookings();
getbookingsobj.Getbookingscall("login","CHECKIN");
bookingid=getbookingsobj.extractingmainid();
guestid=getbookingsobj.extractingguestStayId();
GuestDetailsId=Getbookings.guestdetailsid;
Getpos getposobj=new Getpos();
getposobj.Getposcall(keytype);
posid=getposobj.extractingposid();
Log4j.info("pos id is : "+posid);
Getposproduct getposproductobj=new Getposproduct();
getposproductobj.Getposproductcall(keytype);
String productid=getposproductobj.extractingposproductid();
Log4j.info("pos product id is: "+productid);

HttpResponse<JsonNode> responseaddothercharge = Unirest.post(""+serverurl+"/ws/web/addothercharge")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
 .body("{ \"hotelogix\": {"
                + "\"version\": \"1.0\","
                + "\"datetime\": \"2015-09-16T11:01:29\","
                + " \"request\": {"
                + " \"method\": \"addothercharge\","
                + "\"key\": \""+accesskey+"\","
                + "\"data\": {"
                + "\"chargeType\": \"guest\","
                + "\"bookingId\": \""+bookingid+"\","
                + "\"guestId\": \""+GuestDetailsId+"\","
                + "\"posId\": \""+posid+"\","
                + "\"productId\": \""+productid+"\","
                + "\"quantity\": \"1\","
                + "\"discount\": \""+discountamt+"\","
                + "\"discountType\": \"FV\""
                + "}}}}")
 .asJson();
JsonNode body = responseaddothercharge.getBody();
responseJSONString = body.toString();
Log4j.info(responseJSONString);
}catch(UnirestException e)
{
e.printStackTrace();
}
finally{
Commiteditbooking commitedbookingobj = new Commiteditbooking();
commitedbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", bookingid);
}

}

public void Addotherchargecallwithnewreservation(String s1, String s2) {
keytype = s1;
restype = s2;
if(keytype == "wsauth")

{
Log4j.info("hello to Addothercharge block:");

String keyw = Wsauth.wsauthkeyfinalstring;
accesskey = keyw;
Log4j.info("wsauthkey in Addothercharge:"+keyw);
}

else if(keytype == "login")
{
String keyl = Login.finalloginaccesskey;
Log4j.info("login key in Addothercharge:"+keyl);
accesskey = keyl;
}
try{
   serverurl = CommonConfig.serverurl;
hotelid1 = CommonConfig.hotelid1;
Log4j.info("hello try"+accesskey);
if(restype == "S"){
bookingid=GuestCheckin.BookingID;
guestid=GuestCheckin.Gueststayid;
GuestDetailsId=GuestCheckin.guestdetailsid;
Getpos getposobj=new Getpos();
getposobj.Getposcall(keytype);
posid=getposobj.extractingposid();
Log4j.info("pos id is : "+posid);
Getposproduct getposproductobj=new Getposproduct();
getposproductobj.Getposproductcall(keytype);
String productid=getposproductobj.extractingposproductid();
Log4j.info("pos product id is: "+productid);
HttpResponse<JsonNode> responseaddothercharge = Unirest.post(""+serverurl+"/ws/web/addothercharge")
.header("content-type", "application/json")
.header("x-ig-sg", "D_gg%fkl85_j")
.header("cache-control", "no-cache")
.header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
.body("{ \"hotelogix\": {"
               + "\"version\": \"1.0\","
               + "\"datetime\": \"2015-09-16T11:01:29\","
               + " \"request\": {"
               + " \"method\": \"addothercharge\","
               + "\"key\": \""+accesskey+"\","
               + "\"data\": {"
               + "\"chargeType\": \"guest\","
               + "\"bookingId\": \""+bookingid+"\","
               + "\"guestId\": \""+GuestDetailsId+"\","
               + "\"posId\": \""+posid+"\","
               + "\"productId\": \""+productid+"\","
               + "\"quantity\": \"1\","
               + "\"discount\": \""+discountamt+"\","
               + "\"discountType\": \"FV\""
               + "}}}}")
.asJson();
JsonNode body = responseaddothercharge.getBody();
responseJSONString = body.toString();
Log4j.info(responseJSONString);
}

if(restype == "G"){
	
	
	groupcode = GuestCheckin.groupcode;
	Getpos getposobj=new Getpos();
	getposobj.Getposcall(keytype);
	posid=getposobj.extractingposid();
	Log4j.info("pos id is : "+posid);
	Getposproduct getposproductobj=new Getposproduct();
	getposproductobj.Getposproductcall(keytype);
	String productid=getposproductobj.extractingposproductid();
	Log4j.info("pos product id is: "+productid);
	Getgroup Getgroupobj=new Getgroup();
	Getgroupobj.Getgroupcall("login", GuestCheckin.groupcode);
	bookingid=Getgroupobj.extractingGOUPPID();
	GroupId = Getgroupobj.extractinggetgroupidmain();
	
	HttpResponse<JsonNode> responseaddothercharge = Unirest.post(""+serverurl+"/ws/web/addothercharge")
			 .header("content-type", "application/json")
			 .header("x-ig-sg", "D_gg%fkl85_j")
			 .header("cache-control", "no-cache")
			 .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
			 .body("{ \"hotelogix\": {"
			                + "\"version\": \"1.0\","
			                + "\"datetime\": \"2015-09-16T11:01:29\","
			                + " \"request\": {"
			                + " \"method\": \"addothercharge\","
			                + "\"key\": \""+accesskey+"\","
			                + "\"data\": {"
			                + "\"chargeType\": \"group\","
			                + "\"groupId\": \""+GroupId+"\","
			                + "\"guestId\": \""+GuestDetailsId+"\","
			                + "\"posId\": \""+posid+"\","
			                + "\"productId\": \""+productid+"\","
			                + "\"quantity\": \"1\","
			                + "\"discount\": \""+discountamt+"\","
			                + "\"discountType\": \"FV\""
			                + "}}}}")
			 .asJson();
			JsonNode body = responseaddothercharge.getBody();
			responseJSONString = body.toString();
			Log4j.info(responseJSONString);
}

}catch(UnirestException e)
{
e.printStackTrace();
}
finally{
Commiteditbooking commitedbookingobj = new Commiteditbooking();
commitedbookingobj.CommiteditBookingCallForExistingBookingid("login", restype, bookingid);
}

}


public void AddotherchargecallForExistingBooking(String keyType,String bookingID){
keytype = keyType;

if(keytype == "wsauth")

{
Log4j.info("hello to Addothercharge block:");

String keyw = Wsauth.wsauthkeyfinalstring;
accesskey = keyw;
Log4j.info("wsauthkey in Addothercharge:"+keyw);
}

else if(keytype == "login")
{
String keyl = Login.finalloginaccesskey;
Log4j.info("login key in Addothercharge:"+keyl);
accesskey = keyl;
}
try{
   serverurl = CommonConfig.serverurl;
hotelid1 = CommonConfig.hotelid1;
Log4j.info("Here is the access key" +accesskey);

/*Getbookings getbookingsobj = new Getbookings();
getbookingsobj.Getbookingscall("login","CHECKIN");
bookingid= getbookingsobj.extractingmainid();
guestid=getbookingsobj.extractingguestStayId();
bookingcode = getbookingsobj.extractingcode();
Log4j.info("Booking Id for getbookings:" +bookingid);*/
Getpos getposobj=new Getpos();
getposobj.Getposcall(keytype);
posid=getposobj.extractingposid();
Getposproduct getposproductobj=new Getposproduct();
getposproductobj.Getposproductcall(keytype);
productid=getposproductobj.extractingposproductid();


HttpResponse<JsonNode> responseaddothercharge = Unirest.post(""+serverurl+"/ws/web/addothercharge")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "68c17692-2456-7fd3-6057-fe652a0d60eb")
 .body("{ \"hotelogix\": {"
                + "\"version\": \"1.0\","
                + "\"datetime\": \"2015-09-16T11:01:29\","
                + " \"request\": {"
                + " \"method\": \"addothercharge\","
                + "\"key\": \""+accesskey+"\","
                + "\"data\": {"
                + "\"chargeType\": \"room\","
                + "\"bookingId\": \""+bookingID+"\","
                + "\"posId\": \""+posid+"\","
                + "\"productId\": \""+productid+"\","
                + "\"quantity\": \"1\""            
                + "}}}}")
 .asJson();
JsonNode body = responseaddothercharge.getBody();
responseJSONString = body.toString();
Log4j.info("Add Other Charge:" +responseJSONString);
}catch(UnirestException e)
{
e.printStackTrace();
}
finally{
Commiteditbooking commitedbookingobj = new Commiteditbooking();
commitedbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", bookingid);
}

}



public String extractingmessageAddothercharge() {

Log4j.info("welcome to extractingmessageAddothercharge");
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
message = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
Log4j.info("status msg of addothercharge:"+message);
return message;
}

}
