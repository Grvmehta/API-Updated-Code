
package com.hms.APITestingUsingUnirest;

import java.util.ArrayList;

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

public class Getratestochange
{

static String responseJSONString;

String serverurl;
String hotelid1;
String keytype;
String accesskey;
String nightauditdate1;
String editbookingtempid;
String nightauditdate4;
String rateidforchangegetrate;

public void Getratestochangecall(String s)
{
keytype = s;
System.out.println("keytype:"+s);

if(keytype == "wsauth")
{
/*Wsauth objwsauth = new Wsauth();
objwsauth.Wsauthcall();
String keyw = objwsauth.extractingWsauthKey();
accesskey = keyw;
System.out.println("hello if"+ accesskey);*/
String keyw = Wsauth.wsauthkeyfinalstring;
accesskey = keyw;
System.out.println("wsauthkey in getratestochange:"+keyw);

}

else if(keytype == "login")
{
/*Login objlogin = new Login();
objlogin.Logincall();
String keyl = objlogin.extractingLoginKey();
System.out.println("login key in gethousestatus:"+keyl);
accesskey = keyl;*/

String keyl = Login.finalloginaccesskey;
System.out.println("login key in getratestochange:"+keyl);
accesskey = keyl;
}

try
{
serverurl = CommonConfig.serverurl;
nightauditdate1 = CommonConfig.currentsystemdate;
nightauditdate4 = CommonConfig.immediatenexttonextdate;

/*Editbooking editbookingobj = new Editbooking();
editbookingobj.Editbookingcall("login");
String editbookingtempid = editbookingobj.extractingtempid();*/


Editbooking editbookingobj = new Editbooking();
editbookingobj.EditbookingcallforReserveGuest("login", "S");
rateidforchangegetrate = editbookingobj.rateidforchange;

editbookingtempid =editbookingobj.extractingtempid();
   
System.out.println("editbooking temp id in getratestochange"+editbookingtempid);

/*Getbookings getbookingsobj = new Getbookings();
getbookingsobj.Getbookingscall("login");
String mainid = getbookingsobj.extractingmainid();
System.out.println("getbookings mainid in getratestochange"+mainid);*/

HttpResponse<JsonNode> responsegetratestochange = Unirest.post(""+serverurl+"/ws/web/getratestochange")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "518f9cae-f825-9a76-f007-a274056704f2")
 .body("{\r\n \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n   \"datetime\": \"2012-01-16T10:10:15\",\r\n   \"request\": {\r\n     \"method\": \"getratestochange\",\r\n     \"key\": \""+accesskey+"\",\r\n     \"data\": {\r\n       \"bookings\": [\r\n         {\r\n           \"id\": \""+editbookingtempid+"\",\r\n           \"fromDate\":\""+nightauditdate1+"\",\r\n           \"toDate\":\""+nightauditdate4+"\"\r\n           \r\n         }\r\n       ]\r\n     }\r\n   }\r\n }\r\n}")
 .asJson();
JsonNode body = responsegetratestochange.getBody();
responseJSONString = body.toString();
}

catch(UnirestException e)
{
e.printStackTrace();
}
}
//End of Getratestochangecall() method
public void Getratestochangecallforexistingbookingid(String s1, String s2)
{
keytype = s1;
editbookingtempid = s2;
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
System.out.println("wsauthkey in getratestochange:"+keyw);

}

else if(keytype == "login")
{
/*Login objlogin = new Login();
objlogin.Logincall();
String keyl = objlogin.extractingLoginKey();
System.out.println("login key in gethousestatus:"+keyl);
accesskey = keyl;*/

String keyl = Login.finalloginaccesskey;
System.out.println("login key in getratestochange:"+keyl);
accesskey = keyl;
}

try
{
serverurl = CommonConfig.serverurl;
nightauditdate1 = CommonConfig.currentsystemdate;
nightauditdate4 = CommonConfig.immediatenexttonextdate;


HttpResponse<JsonNode> responsegetratestochange = Unirest.post(""+serverurl+"/ws/web/getratestochange")
 .header("content-type", "application/json")
 .header("x-ig-sg", "D_gg%fkl85_j")
 .header("cache-control", "no-cache")
 .header("postman-token", "518f9cae-f825-9a76-f007-a274056704f2")
 .body("{\r\n \"hotelogix\": {\r\n   \"version\": \"1.0\",\r\n   \"datetime\": \"2012-01-16T10:10:15\",\r\n   \"request\": {\r\n     \"method\": \"getratestochange\",\r\n     \"key\": \""+accesskey+"\",\r\n     \"data\": {\r\n       \"bookings\": [\r\n         {\r\n           \"id\": \""+editbookingtempid+"\",\r\n           \"fromDate\":\""+nightauditdate1+"\",\r\n           \"toDate\":\""+nightauditdate4+"\"\r\n           \r\n         }\r\n       ]\r\n     }\r\n   }\r\n }\r\n}")
 .asJson();
JsonNode body = responsegetratestochange.getBody();
responseJSONString = body.toString();
System.out.println("get rates to change::::"+responseJSONString);
}

catch(UnirestException e)
{
e.printStackTrace();
}
}

public String extractingmessagegetratestochange()
{
try{
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
String getratestochangestring;
getratestochangestring = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
return getratestochangestring;
}



finally
{
System.out.println("Getratestochange finally block ::");
Commiteditbooking commiteditbookingobj = new Commiteditbooking();
commiteditbookingobj.Commiteditbookingcall("login");

}

}// End of extractingmessagegetratestochange() method

public ArrayList<String> extractingrateid()
{

   ArrayList<String> arr=new ArrayList<String>();
String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
JSONObject jsonResult1 = jsonResult.getJSONObject("hotelogix").getJSONObject("response");
JSONArray JSONArray1 = jsonResult1.getJSONArray("availableRates");
JSONObject jsonResult2 = JSONArray1.getJSONObject(0);
//JSONArray JSONArray6 =jsonResult2.getJSONArray("rates");
//JSONObject availabelrates =JSONArray6.getJSONObject(0);
//String rateid = rateidobj.getString("id");
JSONArray rates=jsonResult2.getJSONArray("rates");
for(int i=0;i<=rates.length()-1;i++){
String rateid=rates.getJSONObject(i).getString("id");
arr.add(rateid);
}

return arr;




}

public String extractingbookingid()
{


String localresponseJSONString = responseJSONString;
JSONObject jsonResult = new JSONObject(localresponseJSONString);
JSONObject jsonResult1 = jsonResult.getJSONObject("hotelogix").getJSONObject("response");
JSONArray JSONArray1 = jsonResult1.getJSONArray("availableRates");
JSONObject jsonResult2 = JSONArray1.getJSONObject(0);
String bookingid = jsonResult2.getString("bookingId");
return bookingid;




}


}



