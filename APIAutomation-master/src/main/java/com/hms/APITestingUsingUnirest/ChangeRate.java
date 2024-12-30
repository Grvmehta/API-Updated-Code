
package com.hms.APITestingUsingUnirest;

import java.util.ArrayList;

import org.json.JSONObject;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ChangeRate {


static String responseJSONString;

String keytype;
String accesskey;
String serverurl;
String hotelid1;
String nightauditdate3;
String nightauditdate4;
String bookingid;
ArrayList<String> rateid;
String fname;
String lname;
String editbookingid;
String rateidforchange1;


public void fn_changerate(String s1){

keytype = s1;

System.out.println("Welcome to Change Rate API");

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
System.out.println("Hello Change Rate API");
serverurl = CommonConfig.serverurl;
hotelid1 = CommonConfig.hotelid1;

nightauditdate3 = CommonConfig.immediatenextdate;
nightauditdate4 = CommonConfig.immediatenexttonextdate;

Getratestochange getratestochangeobj = new Getratestochange();
getratestochangeobj.Getratestochangecall("login");
bookingid= getratestochangeobj.extractingbookingid();

//Extracting booking id of a particular booking id
rateidforchange1 = getratestochangeobj.rateidforchangegetrate;

//Extracting array of rateids
rateid= getratestochangeobj.extractingrateid();
rateid.remove(rateidforchange1);

rateidforchange1=rateid.get(0);


Editbooking editbookingobj = new Editbooking();
editbookingobj.fn_editbookingcallforexistingbookingid("login", bookingid, "S");
editbookingid = editbookingobj.extractingtempid();


   HttpResponse<JsonNode> responsechangerate = Unirest.post(""+serverurl+"/ws/web/changerate")
               .header("content-type", "application/json")
               .header("x-ig-sg", "D_gg%fkl85_j")
               .header("cache-control", "no-cache")
               .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
               .body("{"
                + "\"hotelogix\": {"
                + "\"version\": \"1.0\","
                + "\"datetime\": \"2012-01-16T10:10:15\","
                + "\"request\": {"
                + "\"method\": \"changerate\","
                + "\"key\": \""+accesskey+"\","
                + "\"data\": {"
                + "\"bookings\": ["
                + "{"
                      + "\"id\": \""+editbookingid+"\","
                + "\"rateId\": \""+rateidforchange1+"\","
                + "\"removeInclusion\": \"false\""
                + "}]}}}}")
                    .asJson();
    JsonNode body = responsechangerate.getBody();
    responseJSONString = body.toString();
    System.out.println("Change Rate:"+responseJSONString);
}
    catch(UnirestException e)
    {
    e.printStackTrace();
    }
finally{
System.out.println("CommitBooking Change Rate");
Commiteditbooking commitbookingobj = new Commiteditbooking();
commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", editbookingid );

}


}
      public void fn_changerateforgroup(String s1){

keytype = s1;

System.out.println("Welcome to Change Rate API");

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
System.out.println("Hello Change Rate API");
serverurl = CommonConfig.serverurl;
hotelid1 = CommonConfig.hotelid1;

nightauditdate3 = CommonConfig.immediatenextdate;
nightauditdate4 = CommonConfig.immediatenexttonextdate;

Confirmbooking confirmbookingobj = new Confirmbooking();
confirmbookingobj.confirmbookingforgroupresv("login","1");
System.out.println("new");
String groupcode = confirmbookingobj.extractinggroupcode();

System.out.println("Welcome GetGroup API");
Getgroup getgroupobj = new Getgroup();
getgroupobj.Getgroupcall("login", groupcode);

//Extracting Booking Id
   String Bookingid=  getgroupobj.extractinggetgroupidmain();
   String rateidtoremove =  getgroupobj.extractingrateidfromgetgroup();

   
   Editbooking editbookingobj = new Editbooking();
editbookingobj.fn_editbookingcallforexistingbookingid("login", Bookingid, "G");
System.out.println("Booking Edited");
editbookingid = editbookingobj.extractingtempid();



//Extracting booking id of a particular booking id
Getratestochange getratestochangeobj = new Getratestochange();
getratestochangeobj.Getratestochangecallforexistingbookingid("login", editbookingid);


//rateidforchange1 = getratestochangeobj.rateidforchangegetrate;

//Extracting array of rateids
rateid= getratestochangeobj.extractingrateid();
rateid.remove(rateidtoremove);

rateidforchange1=rateid.get(0);


   HttpResponse<JsonNode> responsechangerate = Unirest.post(""+serverurl+"/ws/web/changerate")
               .header("content-type", "application/json")
               .header("x-ig-sg", "D_gg%fkl85_j")
               .header("cache-control", "no-cache")
               .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
               .body("{"
                + "\"hotelogix\": {"
                + "\"version\": \"1.0\","
                + "\"datetime\": \"2012-01-16T10:10:15\","
                + "\"request\": {"
                + "\"method\": \"changerate\","
                + "\"key\": \""+accesskey+"\","
                + "\"data\": {"
                + "\"bookings\": ["
                + "{"
                      + "\"id\": \""+editbookingid+"\","
                + "\"rateId\": \""+rateidforchange1+"\","
                + "\"removeInclusion\": \"false\""
                + "}]}}}}")
                    .asJson();
    JsonNode body = responsechangerate.getBody();
    responseJSONString = body.toString();
    System.out.println("Change Rate:"+responseJSONString);
}
    catch(UnirestException e)
    {
    e.printStackTrace();
    }
finally{
System.out.println("CommitBooking Change Rate");
Commiteditbooking commitbookingobj = new Commiteditbooking();
commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "G", editbookingid );

}

      }
public String extractingmessagechangerate()
{
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
String changerate;
changerate = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
System.out.println("at last getbookings success:"+changerate);
return changerate;
}

}