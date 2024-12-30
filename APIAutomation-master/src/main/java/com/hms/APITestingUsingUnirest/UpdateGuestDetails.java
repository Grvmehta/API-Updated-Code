package com.hms.APITestingUsingUnirest;
import org.json.JSONObject;

import com.hms.APITestingUsingUnirest.Generic.ApiUtils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UpdateGuestDetails {



static String responseJSONString;

String keytype;
String accesskey;
String serverurl;
String hotelid1;
String nightauditdate3;
String nightauditdate4;
String bookingid;
String fname;
String lname;
String editbookingid;
String guestdetailsid;

public void fn_updateguestdetails(String s1){

keytype = s1;

System.out.println("Welcome to Update Guest Details API");

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


fname =ApiUtils.GA().generateRandomString();
lname =ApiUtils.GA().generateRandomString();

Editbooking editbookingobj = new Editbooking();
editbookingobj.EditbookingcallforReserveGuest("login","S");
editbookingid = editbookingobj.extractingtempid();
guestdetailsid = editbookingobj.extractingdetailsId();


   HttpResponse<JsonNode> responsechangerate = Unirest.post(""+serverurl+"/ws/web/updateguestdetails")
               .header("content-type", "application/json")
               .header("x-ig-sg", "D_gg%fkl85_j")
               .header("cache-control", "no-cache")
               .header("postman-token", "586c8072-4a88-dff1-cb04-294c764db5d7")
               .body("{"
                + "\"hotelogix\": {"
                + "\"version\": \"1.0\","
                + "\"datetime\": \"2012-01-16T10:10:15\","
                + "\"request\": {"
                + "\"method\": \"updateguestdetails\","
                + "\"key\": \""+accesskey+"\","
                + "\"data\": {"
                + "\"id\": \""+guestdetailsid+"\","
                + "\"guestDetails\": {"    
                      + "\"id\": \""+guestdetailsid+"\","
                         + "\"code\": \"Dr.\","
                         + "\"fName\": \""+fname+"\","
                         + "\"lName\": \""+lname+"\","  
                         + "\"email\": \""+accesskey+"\","
                         + "\"phoneNo\": \"643646464\","
                         + "\"mobileNo\": \"35346436346\","
                         + "\"gender\": \"f\","
                         + "\"nationality\": \"AF\","
                         + "\"dob\": \"2015-05-05\","
                         + "\"organization\": \"HMS\","
                         + "\"designation\": \"Manager\","
                      + "\"addresses\": {"
                      + "\"home\": {"
                      + "\"address\": \"homeaddress\","
                      + "\"country\": \"HT\","    
                      + "\"state\": \"CE\","        
                      + "\"city\": \"Home City\","  
                      + "\"zip\": \"1100925\","  
                      + "\"fax\": \"244444445\","
                      + "\"work\": {"
                      + "\"workaddress\": \"workaddress\","
                      + "\"country\": \"AL\","    
                      + "\"state\": \"DV\","        
                      + "\"city\": \"work city\","  
                      + "\"zip\": \"34444444445\","  
                      + "\"fax\": \"1100925\","
                      + "\"phone\": \"244444445\","  
                      + "\"mobile\": \"244444445\""  
                            + "}"
                      + "},"
                      + "\"otherDetails\": {"
                      + "\"spouseSalutation\": \"Mr.\","
                      + "\"spouseFName\": \""+fname+"\","    
                      + "\"spouseLName\": \""+fname+"\","        
                      + "\"spouseDob\": \"1985-02-05\","  
                      + "\"anniversary\": \"2015-12-05\""                                        
                + "}}}}}}}")
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
System.out.println("CommitBooking Update Guest Details");
Commiteditbooking commitbookingobj = new Commiteditbooking();
commitbookingobj.CommiteditBookingCallForExistingBookingid("login", "S", editbookingid );

}
}


public String extractingmessageupdateguestdetails()
{
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
String updateguestdetails;
updateguestdetails = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
System.out.println("at last getbookings success:"+updateguestdetails);
return updateguestdetails;
}

   


}
