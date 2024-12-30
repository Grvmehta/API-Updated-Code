package com.hms.APITestingUsingUnirest.Generic;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;
public class ApiUtils {
	public static String msg=null;


	private static ApiUtils AU;

	public ApiUtils(){

	}

	public static ApiUtils GA(){
	if(AU==null){
	AU=new ApiUtils();
	}
	return AU;
	}



	private  final String Num_LIST ="0123456789";
	private  final int RANDOM_NUM_LENGTH = 4;
	private  final String CHAR_LIST ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private  final int RANDOM_STRING_LENGTH =5;
	public static String fromdate;
	public static String Time;
	public static String todate;



	public void fn_GetCurrentDate() throws Throwable{
	try{
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	fromdate = format.format(date);
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DATE, 1);
	Date dob=cal.getTime();
	SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
	Time=time.format(dob);
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	todate= format1.format(dob);
	}catch(Exception e){
	throw e;
	}
	}



	public String GetRoundFigureAmountInDecimalPoint(String amtindouble) throws Exception{
	try{
	Double amt=Double.parseDouble(amtindouble);
	DecimalFormat df = new DecimalFormat("#.0");
	String resultado = df.format(amt);
	return resultado;
	}catch(Exception e){
	throw e;
	}
	}


	public String GetAmountWithTax(String amount,String tax) throws Exception{
	try{
	Double amout=Double.parseDouble(amount);
	Double taxd=Double.parseDouble(tax);
	Double discountedamt=amout+taxd;
	return discountedamt.toString();
	}catch(Exception e){
	throw e;
	}
	}


	public String GetDiscountAmtInDouble(String amount,String discountamt) throws Exception{
	try{
	Double amout=Double.parseDouble(amount);
	Double disamou=Double.parseDouble(discountamt);
	Double discountedamt=amout-disamou;
	return discountedamt.toString();
	}catch(Exception e){
	throw e;
	}
	}


	public  String generateRandomnum(){
	StringBuffer randStr = new StringBuffer();
	for(int i=0; i<RANDOM_NUM_LENGTH-1; i++){
	int number = getRandomNumber();
	char ch = Num_LIST.charAt(number);
	randStr.append(ch);
	}
	return randStr.toString();
	}


	public  String generateRandomString(){
	StringBuffer randStr = new StringBuffer();
	for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	int number = getRandomNumber();
	char ch = CHAR_LIST.charAt(number);
	randStr.append(ch);
	}
	return randStr.toString();
	}


	public  int getRandomNumber() {
	int randomInt = 0;
	Random randomGenerator = new Random();
	randomInt = randomGenerator.nextInt(Num_LIST.length());
	if (randomInt - 1 == -1) {
	return randomInt;
	} else {
	return randomInt - 1;
	}
	}

	public String GetAmountAfterDiscount(String orderamt, String chargeamtinpercent) throws Exception{
	double orderamount=Double.parseDouble(orderamt);
	double chageamtinpercent=Double.parseDouble(chargeamtinpercent);
	Double cancellationcharge=orderamount*chageamtinpercent/100;
	return cancellationcharge.toString();
	}

	public String GetCancellationChargeInAmount(String orderamt, String chargeamtinpercent) throws Exception{
	double orderamount=Double.parseDouble(orderamt);
	double chageamtinpercent=Double.parseDouble(chargeamtinpercent);
	Double cancellationcharge=orderamount*chageamtinpercent/100;
	return cancellationcharge.toString();
	}

	public String GetChargeAmountAfterSubstraction(String orderamt, String chargeamtinpercent) throws Exception{
	double orderamount=Double.parseDouble(orderamt);
	double chageamtinpercent=Double.parseDouble(chargeamtinpercent);
	Double cancellationcharge=orderamount-chageamtinpercent;
	return cancellationcharge.toString();
	}

	public JSONObject GetJsonObject(String value) throws JSONException{
	String str=value.substring(1, value.length()-1);
	JSONObject bookingjobj=new JSONObject(str);
	return bookingjobj;
	}

	public String fn_getAMtInDouble(String amt){
	Double db=Double.parseDouble(amt);
	String amt1=db.toString();
	return amt1;
	}


	public String fn_setFrmToDate(String NAdate,int noOfNights) throws Exception{
	try{
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	String date1=NAdate;
	Calendar cal=Calendar.getInstance();
	Date date11=cal.getTime();
	String date2=format.format(date11);
	Date dateBefore = format.parse(date1);
	Date dateAfter = format.parse(date2);
	long difference = dateAfter.getTime() - dateBefore.getTime();
	float daysBetween = (difference / (1000*60*60*24));
	// System.out.println(difference);
	int i=(int)daysBetween;
	//Date date = format.parse(NAdate);
	cal.add(Calendar.DATE, -i+noOfNights);
	Date dob=cal.getTime();  
	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	todate= format1.format(dob);
	// System.out.println(todate);
	return todate;
	}catch(Exception e){
	throw e;
	}
	}


	}

