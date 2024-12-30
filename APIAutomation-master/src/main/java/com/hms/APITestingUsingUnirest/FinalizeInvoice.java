package com.hms.APITestingUsingUnirest;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class FinalizeInvoice {
	
static String responseJSONString;
	
	String serverurl;
	String hotelid1;
	String keytype; 
	String accesskey;
	
	String invoiceid;

	
	public void fn_finalizeinvoice(String s1, String s2)
	{
		keytype = s1;
		
		invoiceid = s2;
		System.out.println("keytype:"+s1);
		
		
		
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
			
			
			
			HttpResponse<JsonNode> finalizeinvoiceresponse = Unirest.post(""+serverurl+"/ws/web/finalizeinvoice")
					  .header("content-type", "application/json")
					  .header("x-ig-sg", "D_gg%fkl85_j")
					  .header("cache-control", "no-cache")
					  .header("postman-token", "c6c8cdea-3cc5-7953-79c7-1d9850356bd2")
					  .body("{"
		                		+ "\"hotelogix\": {"
		                		+ "\"version\": \"1.0\","
		                		+ "\"datetime\": \"2012-01-16T10:10:15\","
		                		+ "\"request\": {"
		                		+ "\"method\": \"finalizeinvoice\","
		                		+ "\"key\": \""+accesskey+"\","
		                		+ "\"data\": {"
		                        + "\"invoiceId\": \""+invoiceid+"\","
		                		+ "\"settelInvoice\": true"
		                		
	                      		+ "}}}}")
		                    .asJson();
			
			JsonNode body = finalizeinvoiceresponse.getBody();
			responseJSONString = body.toString();
			System.out.println("getinvoices response"+responseJSONString);
		}
		
		catch(UnirestException e)
		{
			e.printStackTrace();
		}
	}
	
	public String extractingInvID() {

		System.out.println("welcome to  extractingGeneratePerformaInvoice");
		String localresponseJSONString = responseJSONString;
		JSONObject jsonResult = new JSONObject(localresponseJSONString);
		 JSONObject hotelogix= jsonResult.getJSONObject("hotelogix");
		 JSONObject respons=hotelogix.getJSONObject("response");
		 JSONObject invoice=respons.getJSONArray("invoices").getJSONObject(0);
		// InvoiceTotalAmount=invoice.getString("amount");
		String InvoiceID=invoice.get("invoiceId").toString();
		return InvoiceID;
		}

}
