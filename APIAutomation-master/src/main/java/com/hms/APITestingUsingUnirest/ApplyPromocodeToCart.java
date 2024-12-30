package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ApplyPromocodeToCart {
	
	String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 HttpResponse<JsonNode> responseApplyPromocodeToCart ;
	 String nightauditdate3;
	 String nightauditdate4;
	 String mainId="";
	 String code;
	 String rateId;
	
	public void applyPromocodeToCartCall(String key)
	{
		keytype = key;
		 
		 if(keytype == "wsauth")
			{
				System.out.println("hello if ApplyPromocodeToCart");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in getallpaytypes"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in ApplyPromocodeToCart"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in gethousestatus:"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in ApplyPromocodeToCart"+keyl);
				accesskey = keyl;
			}
			
		 Loadcart loadcartobj = new Loadcart();
		 loadcartobj.Loadcartcall(keytype);
		 //String s1 = loadcartobj.extractingmessageloadcart();
		 mainId = loadcartobj.extracingbookingid();
		 
		 System.out.println(mainId);
		 rateId=loadcartobj.extractingRateId();

		serverurl = CommonConfig.serverurl;
		 hotelid1=CommonConfig.hotelid1;
		 System.out.println("hello:: accesskey in ApplyPromocodeToCart"+accesskey);
		 nightauditdate3=CommonConfig.currentsystemdate;
		 nightauditdate4=CommonConfig.currentsystemdate;
		
try {
	responseApplyPromocodeToCart= Unirest.post(""+serverurl+"/ws/web/applypromocodetocart")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebd1bb38-292b-54ad-c70f-f226cab49295")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2020-11-17T09:35:48\",\"request\":{\"method\":\"applypromocodetocart\",\"key\":\""+accesskey+"\",\"data\":{\"hotels\":[{\"id\":\""+hotelid1+"\",\"promoCode\":{\"code\":\"promo55\",\"source\":\"WEB\"}}],\"userId\":\"QXAyWVNXMHVoS3c9\",\"userType\":\"GUEST\"},\"languagecode\":\"en\"}}}")
					  .asJson();

		 
		 JsonNode body = responseApplyPromocodeToCart.getBody();
		 System.out.println(responseApplyPromocodeToCart.getStatus());
		 System.out.println(responseApplyPromocodeToCart.getStatusText());
		 responseJSONString = body.toString();
		 System.out.println(responseJSONString);
}
		 catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}
	
	 public String extractingMessageApplyPromocodeToCart()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			
			String applyPromocodeToCartString;
			int res=responseApplyPromocodeToCart.getStatus();
	
			applyPromocodeToCartString = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			//getJSONObject("currencycode").toString();
			System.out.println("at last ApplyPromocodeToCart::"+applyPromocodeToCartString);
			return applyPromocodeToCartString;
	 }

	 public String extractingbookingIdWithPromoCode()
	 {
				return mainId;
	 }




}
