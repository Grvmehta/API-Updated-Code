package com.hms.APITestingUsingUnirest;

import java.io.IOException;

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

public class Editbooking
{
static String responseJSONString;

String serverurl;
String hotelid1;
String keytype;
String bookingtype;
String accesskey;
String restype;
String searchkey;
static String Editbookingid;
String guestStayId;
String BookingID;
String guestdetailsid;
String RoomID;
String Noofroom;
String roomTypeId;
static String roomId;
String isGroupBookingValue;
String  rateidforchange;
String getbookingstempid;
String Isgroupbookingvalue;
static String mainid;

String code;
public void Editbookingcall(String s1)
{
System.out.println("welcome to editbookingcall with one parameter");
keytype = s1;
System.out.println("keytype:"+s1);

if(keytype == "wsauth")
{
/*Wsauth objwsauth = new Wsauth();
objwsauth.Wsauthcall();
String keyw = objwsauth.extractingWsauthKey();
accesskey = keyw;
System.out.println("hello if"+ accesskey);*/

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
System.out.println("login key in editbooking:"+keyl);
accesskey = keyl;
}

try
{
serverurl = CommonConfig.serverurl;

Getbookings getbookingsobj = new Getbookings();
getbookingsobj.Getbookingscall("login");
String mainid = getbookingsobj.extractingmainid();
System.out.println("getbookings mainid in editbooking:"+mainid);

HttpResponse<JsonNode> responseeditbooking = Unirest.post(""+serverurl+"/ws/web/editbooking")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "04356b9d-f04a-9171-2772-e7bb2fb534cc")
 .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"editbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"type\": \"S\",\r\n         \"id\": \""+mainid+"\"\r\n      }\r\n    }\r\n  }\r\n }")
 .asJson();
JsonNode body = responseeditbooking.getBody();
responseJSONString = body.toString();
System.out.println(responseJSONString);
}
catch(UnirestException e)
{
e.printStackTrace();
}
}// End of Editbookingcall(s1) method

public void EditbookingcallforConfirmBookingidforFutureDate(String s1,String s2,String s3){
keytype = s1;
Noofroom=s2;
isGroupBookingValue=s3;
System.out.println("keytype:"+s1);

if(keytype == "wsauth")
{
/*Wsauth objwsauth = new Wsauth();
objwsauth.Wsauthcall();
String keyw = objwsauth.extractingWsauthKey();
accesskey = keyw;
System.out.println("hello if"+ accesskey);*/

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
System.out.println("login key in editbooking:"+keyl);
accesskey = keyl;
}

try
{
serverurl = CommonConfig.serverurl;
Confirmbooking Confirmbookingobj=new Confirmbooking();
Confirmbookingobj.ConfirmbookingcallforFrontDeskforfutureDate(keytype,Noofroom,isGroupBookingValue);
String mainid =Confirmbookingobj.extracingbookingid();
System.out.println("confirmbookings mainid in editbooking:"+mainid);
HttpResponse<JsonNode> responseeditbooking = Unirest.post(""+serverurl+"/ws/web/editbooking")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "04356b9d-f04a-9171-2772-e7bb2fb534cc")
 .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"editbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"type\": \"S\",\r\n         \"id\": \""+mainid+"\"\r\n      }\r\n    }\r\n  }\r\n }")
 .asJson();
JsonNode body = responseeditbooking.getBody();
responseJSONString = body.toString();
System.out.println("Editbooking rspns from confirm booking:"+responseJSONString);
}
catch(UnirestException e)
{
e.printStackTrace();
}
}
public void EditbookingcallforConfirmBookingid(String s1, String s2, String s3){
System.out.println("welcome to editbookingcall with three parameter");
keytype = s1;
Isgroupbookingvalue = s2;
restype = s3;
System.out.println("keytype:"+s1);

if(keytype == "wsauth")
{
/*Wsauth objwsauth = new Wsauth();
objwsauth.Wsauthcall();
String keyw = objwsauth.extractingWsauthKey();
accesskey = keyw;
System.out.println("hello if"+ accesskey);*/

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
System.out.println("login key in editbooking:"+keyl);
accesskey = keyl;
}

try
{
serverurl = CommonConfig.serverurl;
Confirmbooking Confirmbookingobj=new Confirmbooking();
Confirmbookingobj.ConfirmbookingcallforFrontDesk("login",Isgroupbookingvalue);
if(restype =="S"){
		mainid =Confirmbookingobj.extracingbookingid();
	 System.out.println("The Single Bokking Id id:" +BookingID);
	}

	if(restype == "G"){
		mainid = Confirmbookingobj.extracinggroupid();
		System.out.println("The Group Booking id is:"+BookingID);
		}
/*Getbookings getbookingsobj = new Getbookings();
getbookingsobj.Getbookingscall("login");
String mainid = getbookingsobj.extractingmainid();*/
System.out.println("confirmbookings mainid in editbooking:"+mainid);

HttpResponse<JsonNode> responseeditbooking = Unirest.post(""+serverurl+"/ws/web/editbooking")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "04356b9d-f04a-9171-2772-e7bb2fb534cc")
 .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"editbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n        \"type\": \""+restype+"\",\r\n          \"id\": \""+mainid+"\"\r\n      }\r\n    }\r\n  }\r\n }")
 .asJson();
JsonNode body = responseeditbooking.getBody();
responseJSONString = body.toString();
System.out.println("Editbooking rspns from confirm booking:"+responseJSONString);
}
catch(UnirestException e)
{
e.printStackTrace();
}

}
public void EditbookingcallforReserveGuest(String s1,String s2){
System.out.println("welcome to editbookingcall with one parameter");
keytype = s1;
restype=s2;
System.out.println("keytype:"+s1);

if(keytype == "wsauth")
{

String keyw = Wsauth.wsauthkeyfinalstring;
accesskey = keyw;
System.out.println("wsauthkey in addtocart:"+keyw);
}

else if(keytype == "login")
{


String keyl = Login.finalloginaccesskey;
System.out.println("login key in editbooking:"+keyl);
accesskey = keyl;
}

try
{
serverurl = CommonConfig.serverurl;

Getbookings getbookingsobj = new Getbookings();
getbookingsobj.Getbookingscallforreserveguest("login");
rateidforchange = getbookingsobj.extractingrateid();
if(restype == "G"){
BookingID = getbookingsobj.extractinggroupmainid();
System.out.println("The Group Booking id is:"+BookingID);
}
if(restype =="S"){
 BookingID = getbookingsobj.extractingmainid();
 System.out.println("The Single Bokking Id id:" +BookingID);
}


HttpResponse<JsonNode> responseeditbooking = Unirest.post(""+serverurl+"/ws/web/editbooking")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "04356b9d-f04a-9171-2772-e7bb2fb534cc")
 .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"editbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"type\": \""+restype+"\",\r\n         \"id\": \""+BookingID+"\"\r\n      }\r\n    }\r\n  }\r\n }")
 .asJson();
JsonNode body = responseeditbooking.getBody();
responseJSONString = body.toString();
System.out.println(responseJSONString);
}
catch(UnirestException e)
{
e.printStackTrace();
}
}



public void Editbookingcall(String s1, String s2, String s3)
{
System.out.println("welcome to editbookingcall with three parameters");
keytype = s1;
bookingtype = s2;
restype = s3;


System.out.println("keytype::"+s1);
System.out.println("bookingtype::"+s2);
System.out.println("restype::"+s3);

if(keytype == "wsauth")
{
Wsauth objwsauth = new Wsauth();
objwsauth.Wsauthcall();
String keyw = objwsauth.extractingWsauthKey();
accesskey = keyw;
System.out.println("hello if"+ accesskey);
}

else if(keytype == "login")
{
/*Login objlogin = new Login();
objlogin.Logincall();
String keyl = objlogin.extractingLoginKey();
System.out.println("login key in gethousestatus:"+keyl);
accesskey = keyl;*/

String keyl = Login.finalloginaccesskey;
System.out.println("login key in editbooking:"+keyl);
accesskey = keyl;
}

try
{
serverurl = CommonConfig.serverurl;

Getbookings getbookingsobj = new Getbookings();
getbookingsobj.Getbookingscall(keytype,restype);
//String mainid = getbookingsobj.extractinggroupmainid();
//getbookingsobj.extractingmainid();
String mainid = getbookingsobj.extractinggroupmainid();
System.out.println("getbookings group mainid in editbooking::"+mainid);

HttpResponse<JsonNode> responseeditbooking = Unirest.post(""+serverurl+"/ws/web/editbooking")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "04356b9d-f04a-9171-2772-e7bb2fb534cc")
 .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"editbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"type\": \""+bookingtype+"\",\r\n         \"id\": \""+mainid+"\"\r\n      }\r\n    }\r\n  }\r\n }")
 .asJson();
JsonNode body = responseeditbooking.getBody();
responseJSONString = body.toString();
System.out.println(responseJSONString);
System.out.println("Status::::"+responseeditbooking.getStatus());
}
catch(UnirestException e)
{
e.printStackTrace();
}
}// End of Editbookingcall(s1,s2,s3) method
public void Editbookingcallforsingleid(String s1, String s2, String s3)
{
System.out.println("welcome to editbookingcall with three parameters");
keytype = s1;
bookingtype = s2;
restype = s3;


System.out.println("keytype::"+s1);
System.out.println("bookingtype::"+s2);
System.out.println("restype::"+s3);

if(keytype == "wsauth")
{
Wsauth objwsauth = new Wsauth();
objwsauth.Wsauthcall();
String keyw = objwsauth.extractingWsauthKey();
accesskey = keyw;
System.out.println("hello if"+ accesskey);
}

else if(keytype == "login")
{
/*Login objlogin = new Login();
objlogin.Logincall();
String keyl = objlogin.extractingLoginKey();
System.out.println("login key in gethousestatus:"+keyl);
accesskey = keyl;*/

String keyl = Login.finalloginaccesskey;
System.out.println("login key in editbooking:"+keyl);
accesskey = keyl;
}

try
{
serverurl = CommonConfig.serverurl;

Getbookings getbookingsobj = new Getbookings();
getbookingsobj.Getbookingscall(keytype,restype);
//String mainid = getbookingsobj.extractinggroupmainid();
//getbookingsobj.extractingmainid();
String mainid = getbookingsobj.extractingmainid();
System.out.println("getbookings mainid in editbooking:"+mainid);

HttpResponse<JsonNode> responseeditbooking = Unirest.post(""+serverurl+"/ws/web/editbooking")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "04356b9d-f04a-9171-2772-e7bb2fb534cc")
 .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"editbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"type\": \""+bookingtype+"\",\r\n         \"id\": \""+mainid+"\"\r\n      }\r\n    }\r\n  }\r\n }")
 .asJson();
JsonNode body = responseeditbooking.getBody();
responseJSONString = body.toString();
System.out.println(responseJSONString);
}
catch(UnirestException e)
{
e.printStackTrace();
}
}// End of Editbo

public void Editbookingcall(String s1, String s2)
{
System.out.println("welcome to editbookingcall with two parameters");
keytype = s1;
searchkey = s2;



System.out.println("keytype::"+s1);
System.out.println("searchkey::"+s2);


if(keytype == "wsauth")
{
/*Wsauth objwsauth = new Wsauth();
objwsauth.Wsauthcall();
String keyw = objwsauth.extractingWsauthKey();
accesskey = keyw;
System.out.println("hello if"+ accesskey);*/

String keyw = Wsauth.wsauthkeyfinalstring;
accesskey = keyw;
System.out.println("wsauthkey in editbooking:"+keyw);
}

else if(keytype == "login")
{
/*Login objlogin = new Login();
objlogin.Logincall();
String keyl = objlogin.extractingLoginKey();
System.out.println("login key in gethousestatus:"+keyl);
accesskey = keyl;*/

String keyl = Login.finalloginaccesskey;
System.out.println("login key in editbooking:"+keyl);
accesskey = keyl;
}

try
{
serverurl = CommonConfig.serverurl;

//Getbookings getbookingsobj = new Getbookings();
//getbookingsobj.Getbookingscall(keytype,restype);

//String mainid = getbookingsobj.extractinggroupmainid();
//getbookingsobj.extractingmainid();

/*getbookingsobj.extractinggroupmainid();
String mainid = Getbookings.getGroupTempID;
System.out.println("getbookings group mainid in editbooking::"+mainid);*/
Getbookings getbookingsobj = new Getbookings();
  getbookingsobj.Getbookingscallforreserveguest("login");
   String mainid2 = getbookingsobj.extractinggroupmainid();
  System.out.println("This is" +mainid2);

//Getbookingsearch getbookingsearchobj = new Getbookingsearch();
//getbookingsearchobj.Getbookingsearchcall(keytype, searchkey);
//String mainid = getbookingsearchobj.extractingmainid();


HttpResponse<JsonNode> responseeditbooking = Unirest.post(""+serverurl+"/ws/web/editbooking")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "04356b9d-f04a-9171-2772-e7bb2fb534cc")
 .body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"editbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"type\": \"G\",\r\n         \"id\": \""+mainid2+"\"\r\n      }\r\n    }\r\n  }\r\n }")
 .asJson();
JsonNode body = responseeditbooking.getBody();
responseJSONString = body.toString();
System.out.println("editbooking:"+responseJSONString);
}
catch(UnirestException e)
{
e.printStackTrace();
}
}// End of Editbookingcall(s1,s2) method


   
   
   
public void fn_editbookingcallforexistingbookingid(String s1, String s2,String s3){
   
    System.out.println("welcome to editbookingcall for EditBooking for Existing Booking id");
keytype = s1;
BookingID = s2;
restype=s3;
System.out.println("keytype:"+s1);

if(keytype == "wsauth")
{

String keyw = Wsauth.wsauthkeyfinalstring;
accesskey = keyw;
System.out.println("wsauthkey in addtocart:"+keyw);
}

else if(keytype == "login")
{

String keyl = Login.finalloginaccesskey;
System.out.println("login key in editbooking:"+keyl);
accesskey = keyl;
}

try
{
serverurl = CommonConfig.serverurl;
System.out.println("getbookings mainid in editbooking:"+BookingID);

HttpResponse<JsonNode> responseeditbooking = Unirest.post(""+serverurl+"/ws/web/editbooking")
.header("content-type", "application/json")
.header("x-ig-sg", "D_gg%fkl85_j")
.header("cache-control", "no-cache")
.header("postman-token", "04356b9d-f04a-9171-2772-e7bb2fb534cc")
.body("{\r\n  \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n    \"datetime\": \"2012-01-16T10:10:15\",\r\n    \"request\": {\r\n      \"method\": \"editbooking\",\r\n      \"key\": \""+accesskey+"\",\r\n      \"data\": {\r\n         \"type\": \""+restype+"\",\r\n         \"id\": \""+BookingID+"\"\r\n      }\r\n    }\r\n  }\r\n }")
.asJson();
JsonNode body = responseeditbooking.getBody();
System.out.println("new");
responseJSONString = body.toString();
System.out.println("This is existing editing booking id call method response" +responseJSONString);
}
catch(UnirestException e)
{
e.printStackTrace();
}
   
   }


public String extractingmessageeditbooking()
{
System.out.println("welcome to extractingmessageeditbooking");
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
String editbookingstring;
editbookingstring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
System.out.println("Booking id from editbooking:"+editbookingstring);
return editbookingstring;
}//End of extractingmessageeditbooking() method


public String extractingPaymentItemID()
{
System.out.println("welcome to extractingmessageeditbooking");
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
String editbookingstring;
JSONObject getbookingsObj = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings").getJSONObject(0);
JSONArray getPaymentsArray=getbookingsObj.getJSONArray("payments");
JSONObject getPaymentObj=getPaymentsArray.getJSONObject(0);
editbookingstring=getPaymentObj.getString("id");
System.out.println("Booking id from editbooking:"+editbookingstring);
return editbookingstring;
}




public String extractingtempid()
{
System.out.println("welcome to extractingtempid");
String localresponseJSONString = responseJSONString;
System.out.println(responseJSONString);
JSONObject jsonResult = new JSONObject(localresponseJSONString);
String Editbookingid;
JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
JSONObject bookingstempid = getbookingsArray.getJSONObject(0);
Editbookingid = bookingstempid.getString("id");
System.out.println(":Editbookings mainid:"+Editbookingid);
return Editbookingid;
}//End of extractingtempid() method

public String extractingguestStayId(){
System.out.println("welcome to extractingguestid");
String localresponseJSONString = responseJSONString;
System.out.println(localresponseJSONString);
JSONObject jsonResult = new JSONObject(localresponseJSONString);
JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
System.out.println(getbookingsArray.length());
JSONObject bkgObj=getbookingsArray.getJSONObject(0);
BookingID=bkgObj.getString("id");
System.out.println(BookingID);
JSONArray gueststaysarr=bkgObj.getJSONArray("guestStays");
System.out.println(gueststaysarr.length());
JSONObject gueststay=gueststaysarr.getJSONObject(0);
guestStayId= gueststay.getString("id");
System.out.println(guestStayId);
System.out.println("Guest Stays Id from editbooking:"+guestStayId);


return guestStayId;
}

public String extracttingowneridfromgroup()
{
System.out.println("welcome to Extracting Owner id of group reservation");
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
JSONObject bookingstempid = getbookingsArray.getJSONObject(0);
JSONObject grp = bookingstempid.getJSONObject("group");
JSONObject ownerobj = grp.getJSONObject("owner");
String ownerid = ownerobj.getString("id");
System.out.println(":Owner ID :"+ownerid);

return ownerid;
}
public String extractingSecondtempid()
{
System.out.println("welcome to extractingtempid");
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);

JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
JSONObject bookingstempid = getbookingsArray.getJSONObject(1);
getbookingstempid = bookingstempid.getString("id");
System.out.println(":getbookings mainid:"+getbookingstempid);

return getbookingstempid;
}



public String extractingroomTypeId(){
System.out.println("welcome to extractingguestid");
String localresponseJSONString = responseJSONString;
System.out.println(localresponseJSONString);
JSONObject jsonResult = new JSONObject(localresponseJSONString);
JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
System.out.println(getbookingsArray.length());
JSONObject bkgObj=getbookingsArray.getJSONObject(0);
BookingID=bkgObj.getString("id");
System.out.println(BookingID);
JSONArray roomstaysarr=bkgObj.getJSONArray("roomStays");
System.out.println(roomstaysarr.length());
JSONObject roomstay=roomstaysarr.getJSONObject(0);
roomId=roomstay.getString("roomId");
roomTypeId= roomstay.getString("roomTypeId");


return roomTypeId;
}
public String extractingguestStayIdofSecondGuest(){
System.out.println("welcome to extractingguestid");
String localresponseJSONString = responseJSONString;
System.out.println(localresponseJSONString);
JSONObject jsonResult = new JSONObject(localresponseJSONString);
JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
System.out.println(getbookingsArray.length());
JSONObject bkgObj=getbookingsArray.getJSONObject(0);
BookingID=bkgObj.getString("id");
System.out.println(BookingID);
JSONArray gueststaysarr=bkgObj.getJSONArray("guestStays");
System.out.println(gueststaysarr.length());
JSONObject gueststay=gueststaysarr.getJSONObject(1);
guestStayId= gueststay.getString("id");
System.out.println(guestStayId);
System.out.println("Guest Stays Id from editbooking:"+guestStayId);


return guestStayId;
}
/*public String extractingguestDetailsId(){

System.out.println("welcome to extractingguestid");
String localresponseJSONString = responseJSONString;
System.out.println(localresponseJSONString);
JSONObject jsonResult = new JSONObject(localresponseJSONString);
JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
System.out.println(getbookingsArray.length());
JSONObject bkgObj=getbookingsArray.getJSONObject(0);
BookingID=bkgObj.getString("id");
System.out.println(BookingID);
JSONArray gueststaysarr=bkgObj.getJSONArray("guestStays");
System.out.println(gueststaysarr.length());
JSONObject gueststay=gueststaysarr.getJSONObject(0);

guestStayId= gueststay.getString("id");
System.out.println(guestStayId);
System.out.println("Guest Stays Id from editbooking:"+guestStayId);
JSONObject Guestdetailsobj=gueststay.getJSONObject("guestDetails");
guestDetailsId=Guestdetailsobj.getString("id");

return guestDetailsId;

}*/
public String extractingdetailsId(){

System.out.println("welcome to extractingguestid");
String localresponseJSONString = responseJSONString;
System.out.println(localresponseJSONString);
JSONObject jsonResult = new JSONObject(localresponseJSONString);
JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
System.out.println(getbookingsArray.length());
JSONObject bkgObj=getbookingsArray.getJSONObject(0);
BookingID=bkgObj.getString("id");
System.out.println(BookingID);
JSONArray gueststaysarr=bkgObj.getJSONArray("guestStays");
JSONObject gueststay=gueststaysarr.getJSONObject(0);
JSONObject new1 = gueststay.getJSONObject("guestDetails");
guestdetailsid = new1.getString("id");

return guestdetailsid;
}
public String extracttinggroupid()
{
System.out.println("welcome to groupid2");
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
JSONObject bookingstempid = getbookingsArray.getJSONObject(0);
JSONObject grp = bookingstempid.getJSONObject("group");
String groupid = grp.getString("id");
System.out.println(":getbookings id:"+groupid);

return groupid;
}
public String extracttingCheckinDate(){
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
JSONObject bookingstempid = getbookingsArray.getJSONObject(0);
JSONObject grp = bookingstempid.getJSONObject("group");
String checkinDate=grp.getString("checkInDate");
System.out.println("CheckinDate is:"+checkinDate);
return checkinDate;

}
public String extracttingCheckoutDate(){
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
JSONObject bookingstempid = getbookingsArray.getJSONObject(0);
JSONObject grp = bookingstempid.getJSONObject("group");
String checkoutDate=grp.getString("checkOutDate");
System.out.println("CheckoutDate is:"+checkoutDate);
return checkoutDate;

}

public int extractingNoOfAdults(){

int noOfAdults=0;
System.out.println("welcome to extractingno of adults");
String localresponseJSONString = responseJSONString;
System.out.println(localresponseJSONString);
JSONObject jsonResult = new JSONObject(localresponseJSONString);
JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
System.out.println(getbookingsArray.length());
JSONObject bkgObj=getbookingsArray.getJSONObject(0);
BookingID=bkgObj.getString("id");
System.out.println(BookingID);

JSONArray gueststaysarr=bkgObj.getJSONArray("guestStays");
System.out.println(gueststaysarr.length());
noOfAdults=gueststaysarr.length();
System.out.println(bkgObj.getString("depositAmount"));
System.out.println(bkgObj.getString("checkOutDate"));
//System.out.println(bkgObj.getString("adult"));
//noOfAdults=bkgObj.getString("adult");
//System.out.println(noOfAdults);

/*JSONArray gueststaysarr=bkgObj.getJSONArray("guestStays");
JSONObject gueststay=gueststaysarr.getJSONObject(0);
JSONObject new1 = gueststay.getJSONObject("guestDetails");
guestdetailsid = new1.getString("id");*/

return noOfAdults;
}


public String extractingCode(){
System.out.println("welcome to extractingCode");
String localresponseJSONString = responseJSONString;
System.out.println(localresponseJSONString);
JSONObject jsonResult = new JSONObject(localresponseJSONString);
JSONArray getbookingsArray = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONArray("bookings");
System.out.println(getbookingsArray.length());
JSONObject bkgObj=getbookingsArray.getJSONObject(0);
code=bkgObj.getString("code");
System.out.println(code);

System.out.println(code);
System.out.println("extractingCode from editbooking:"+code);


return code;
}


}