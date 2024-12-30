package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RemovePromoCodeFromCart {
	
	String responseJSONString;
	 String serverurl;
	 String hotelid1;
	 String keytype;
	 String accesskey;
	 HttpResponse<JsonNode> responseRemovePromoCodeFromCart ;
	 String nightauditdate3;
	 String nightauditdate4;
	 String mainId="";
	 String code;
	 String rateId;
	
	public void removePromoCodeFromCartCall(String key)
	{
		keytype = key;
		 
		 if(keytype == "wsauth")
			{
				System.out.println("hello if RemovePromoCodeFromCart");
				/*Wsauth objwsauth = new Wsauth();
				objwsauth.Wsauthcall();
				String keyw = objwsauth.extractingWsauthKey();
				accesskey = keyw;
				System.out.println("wsauth key in RemovePromoCodeFromCart"+ keyw);*/
				
				String keyw = Wsauth.wsauthkeyfinalstring;
				accesskey = keyw;
				System.out.println("wsauthkey in RemovePromoCodeFromCart::"+keyw);
			}
			
			else if(keytype == "login")
			{
				/*Login objlogin = new Login();
				objlogin.Logincall();
				String keyl = objlogin.extractingLoginKey();
				System.out.println("login key in RemovePromoCodeFromCart"+keyl);
				accesskey = keyl;*/
		
				
				String keyl = Login.finalloginaccesskey;
				System.out.println("login key in RemovePromoCodeFromCart:"+keyl);
				accesskey = keyl;
			}
			
		 serverurl = CommonConfig.serverurl;
		 hotelid1=CommonConfig.hotelid1;
		 ApplyPromocodeToCart applyPromocodeToCartObj=new ApplyPromocodeToCart();
		 applyPromocodeToCartObj.applyPromocodeToCartCall(keytype);
		//mainId= applyPromocodeToCartObj.extractingbookingIdWithPromoCode();
		
try {
	responseRemovePromoCodeFromCart= Unirest.post(""+serverurl+"/ws/web/removepromocodefromcart")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "ebd1bb38-292b-54ad-c70f-f226cab49295")
					  .body("{\"hotelogix\":{\"version\":\"1.0\",\"datetime\":\"2020-11-17T09:36:40\",\"request\":{\"method\":\"removepromocodefromcart\",\"key\":\""+accesskey+"\",\"data\":{\"hotels\":[{\"id\":\""+hotelid1+"\"}]},\"languagecode\":\"en\"}}}")
					  .asJson();

		 
		 JsonNode body = responseRemovePromoCodeFromCart.getBody();
		 System.out.println(responseRemovePromoCodeFromCart.getStatus());
		 System.out.println(responseRemovePromoCodeFromCart.getStatusText());
		 responseJSONString = body.toString();
		 System.out.println(responseJSONString);
}
		 catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	}
	
	 public String extractingMessageRemovePromoCodeFromCart()
	 {
		 String localresponseJSONString = responseJSONString;
			JSONObject jsonResult = new JSONObject(localresponseJSONString);
			
			String removePromoCodeFromCartString;
			int res=responseRemovePromoCodeFromCart.getStatus();
	
			removePromoCodeFromCartString = jsonResult.getJSONObject("hotelogix").getJSONObject("response").getJSONObject("status").getString("message");
			//getJSONObject("currencycode").toString();
			System.out.println("at last RemovePromoCodeFromCart::"+removePromoCodeFromCartString);
			return removePromoCodeFromCartString;
	 }


}
