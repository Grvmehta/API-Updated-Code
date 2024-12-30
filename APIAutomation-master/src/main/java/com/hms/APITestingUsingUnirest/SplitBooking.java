package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SplitBooking {

static String responseJSONString;
String keytype;
String accesskey;
String serverurl;
String hotelid1;
String bookingid;
String rateid;
    String nightauditdate3;
    String nightauditdate4;
    static String editbookingid;


public void fn_splitbooking(String s1){

keytype = s1;

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
System.out.println("hello try createagent");
serverurl = CommonConfig.serverurl;
hotelid1 = CommonConfig.hotelid1;
nightauditdate3 = CommonConfig.immediatenextdate;
nightauditdate4 = CommonConfig.immediatenexttonextdate;
Confirmbooking confirmbookingobj = new Confirmbooking();
confirmbookingobj.confirmbookingforFrontDeskfor3DResv("login","false");
System.out.println("new");
String newbookingid = confirmbookingobj.extracingbookingid();

Editbooking editbookingobj = new Editbooking();
editbookingobj.fn_editbookingcallforexistingbookingid("login", newbookingid, "S");
System.out.println("Booking Edited");
editbookingid = editbookingobj.extractingtempid();

Getroomstoassign getroomstoassignobj=new Getroomstoassign();
getroomstoassignobj.getroomstoassigncall("login", newbookingid);
String roomtypeid = getroomstoassignobj.extractingroomtypeid();



HttpResponse<JsonNode> responseassignrom = Unirest.post(""+serverurl+"/ws/web/splitbooking")
.header("content-type", "application/json")
.header("x-ig-sg", "D_gg%fkl85_j")
.header("cache-control", "no-cache")
.header("postman-token", "c52633bd-8abb-e05d-ec86-0641bf085b8f")
.body("{"
              + "\"hotelogix\": {"
              + "\"version\": \"1.0\","
              + "\"datetime\": \"2012-01-16T10:10:15\","
              + "\"request\": {"
              + "\"method\": \"splitbooking\","
              + "\"key\": \""+accesskey+"\","
              + "\"data\": {"
              + "\"bookings\": ["
              + "{"
              + "\"id\": \""+newbookingid+"\","
              + "\"roomTypeId\":\""+roomtypeid+"\","
              + "\"fromDate\": \""+nightauditdate3+"\","
                             + "\"toDate\": \""+nightauditdate4+"\""
              + "}]}}}}")
                   .asJson();
JsonNode body = responseassignrom.getBody();
responseJSONString = body.toString();
System.out.println("Response of AssignRoom");
System.out.println("assignroom:"+responseJSONString);
}
    catch(UnirestException e)
    {
    e.printStackTrace();
    }
finally{

Commiteditbooking commitbookingobj = new Commiteditbooking();
commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", editbookingid );

}
}

public void fn_splitbookingforgroup(String s1){

keytype = s1;

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
System.out.println("hello try createagent");
serverurl = CommonConfig.serverurl;
hotelid1 = CommonConfig.hotelid1;
nightauditdate3 = CommonConfig.immediatenextdate;
nightauditdate4 = CommonConfig.immediatenexttonextdate;
Confirmbooking confirmbookingobj = new Confirmbooking();
confirmbookingobj.confirmbookingforgroupresv("login" ,"1");
System.out.println("new");
String groupcode = confirmbookingobj.extractinggroupcode();

//Extracting group code
System.out.println("Welcome GetGroup API");
Getgroup getgroupobj = new Getgroup();
getgroupobj.Getgroupcall("login", groupcode);

//Extracting Booking Id
   String Bookingid=  getgroupobj.extractinggetgroupidmain();


Editbooking editbookingobj = new Editbooking();
editbookingobj.fn_editbookingcallforexistingbookingid("login", Bookingid, "G");
System.out.println("Booking Edited");
editbookingid = editbookingobj.extractingtempid();

Getroomstoassign getroomstoassignobj=new Getroomstoassign();
getroomstoassignobj.getroomstoassigncall("login", editbookingid);
String roomtypeid = getroomstoassignobj.extractingroomtypeid();



HttpResponse<JsonNode> responseassignrom = Unirest.post(""+serverurl+"/ws/web/splitbooking")
.header("content-type", "application/json")
.header("x-ig-sg", "D_gg%fkl85_j")
.header("cache-control", "no-cache")
.header("postman-token", "c52633bd-8abb-e05d-ec86-0641bf085b8f")
.body("{"
              + "\"hotelogix\": {"
              + "\"version\": \"1.0\","
              + "\"datetime\": \"2012-01-16T10:10:15\","
              + "\"request\": {"
              + "\"method\": \"splitbooking\","
              + "\"key\": \""+accesskey+"\","
              + "\"data\": {"
              + "\"bookings\": ["
              + "{"
              + "\"id\": \""+editbookingid+"\","
              + "\"roomTypeId\":\""+roomtypeid+"\","
              + "\"fromDate\": \""+nightauditdate3+"\","
                             + "\"toDate\": \""+nightauditdate4+"\""
              + "}]}}}}")
                   .asJson();
JsonNode body = responseassignrom.getBody();
responseJSONString = body.toString();
System.out.println("Response of AssignRoom");
System.out.println("assignroom:"+responseJSONString);
}
    catch(UnirestException e)
    {
    e.printStackTrace();
    }
finally{

Commiteditbooking commitbookingobj = new Commiteditbooking();
commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", editbookingid );

}
}
public String extractingmessagesplitbooking()
{
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
String getsplitbooking;
getsplitbooking = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
System.out.println("at last getbookings success:"+getsplitbooking);
return getsplitbooking;
}

   }

	