package com.hms.APITestingUsingUnirest;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom.JDOMException;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hms.APITestingUsingUnirest.Generic.Log4j;
import com.mashape.unirest.http.Unirest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

public class SmokeTest
{
	@BeforeSuite(alwaysRun = true)
	 
	  public void beforesuite()
	  {
		  System.out.println("hello beforesuite");
		  
		  
		   
		  
		  
		Login loginobj = new Login();
		loginobj.Logincall();
		loginobj.extractingLoginKey();
		String s1 = loginobj.extractingmessagelogin();
		Assert.assertEquals(s1, "success");
		  }
		 
	  		
	
	//@AfterMethod(groups = {"frontdeskview"})
	public void unassignroom()
	{
	Unassignroom unassignroomobj = new Unassignroom();
	unassignroomobj.unassignroomcallaftermethod("login","Single");
	unassignroomobj.unassignroomcallaftermethod("login","Group");

	}
	
	///////////////////////////////////-*-START OF 'FRONTDESK' SECTION  -*-////////////////////////////////
	//This is the starting of "frontdesk" group. In this section we put all the test cases of "frontdesk" here 
	
 /* @Test(groups = { "exemptfrontdeskview" })
  public void UC_FD_1_TC_1_login()
  { 
 
 
	  Login loginobj = new Login();
	  loginobj.Logincall();
	  String s1 = loginobj.extractingmessagelogin();
	  Assert.assertEquals(s1, "success");
	  
	  
	    
 
  }  
  
 
  */
  
  
@Test(priority = 1 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_37_paymentfailedcaptures(){
	PaymentFailedCaptures PaymentFailedCapturesobj= new PaymentFailedCaptures();
	PaymentFailedCapturesobj.PaymentFailedCapturescall("login");
	String s1= 	PaymentFailedCapturesobj.extractingmessagepaymentfailedcaptures();	
	Assert.assertEquals(s1, "success");
}
//Validate update booking preference for Group reservation.
@Test(priority = 2 ,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_26_EditBookingPreferenceforGroup(){
EditBookingPreference editbookingpreferenceobj = new EditBookingPreference();
editbookingpreferenceobj.fn_editbookingpreferenceforgroup("login");
String s1 = editbookingpreferenceobj.extractingmessageeditbookingpreference();
Assert.assertEquals(s1,"success");
}

@Test(priority = 2,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_11_guestcheckin_cancelcheckin(){
	GuestCheckin guestcheckinobj=new GuestCheckin();
	guestcheckinobj.GuestCheckincall("login" ,"false" ,"S");
	String s1=guestcheckinobj.extractingmessageGuestCheckin();
	Assert.assertEquals(s1, "success");
	CancelCheckin cancelcheckinobj=new CancelCheckin();
	cancelcheckinobj.CancelCheckincall("login");
	String s2=cancelcheckinobj.extractingmessageCancelCheckin();
	Assert.assertEquals(s2, "success");
	
	
}
@Test(priority = 2,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_17_cancelcheckout_guestcheckout(){
	CancelCheckout CancelCheckoutobj=new CancelCheckout();
	CancelCheckoutobj.CancelCheckoutcall("login");
	String s1=CancelCheckoutobj.extractingmessageCancelCheckout();
	Assert.assertEquals(s1, "success");
	GuestCheckOut GuestCheckOutobj=new GuestCheckOut();
	GuestCheckOutobj.GuestCheckOutCall("login");
	String s2=GuestCheckOutobj.extractingmessageGuestCheckOut();
	Assert.assertEquals(s2, "success");
}

//Change Room type for single reservation
@Test(priority = 2,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_13_ChangeRoomType(){
ChangeRoomType changeroomtypeobj = new ChangeRoomType();
changeroomtypeobj.ChangeRoomTypeAPI("login");
String s1 = changeroomtypeobj.extractingmessagegetchaneroomtypemsg();
Assert.assertEquals(s1,"success");
}

//change room type for group reservation
@Test(priority = 2,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_NEW1_ChangeRoomTypeforgroup(){

ChangeRoomType changeroomtypeobj = new ChangeRoomType();
changeroomtypeobj.ChangeRoomTypeAPIgroup("login");
String s1 = changeroomtypeobj.extractingmessagegetchaneroomtypemsg();
Assert.assertEquals(s1,"success");
}

@Test(priority = 2,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_NEW1_ChangeRoomTypes(){

ChangeRoomTypes changeroomtypeobj = new ChangeRoomTypes();
changeroomtypeobj.ChangeRoomTypesAPIgroup("login");
String s1 = changeroomtypeobj.extractingmessagegetchaneroomtypemsg();
Assert.assertEquals(s1,"success");
}

@Test(priority = 2,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_20_ChangeGroupStay(){

ChangeGroupStay changegroupstayobj = new ChangeGroupStay();
changegroupstayobj.fn_changegroupstay("login");
String s1 = changegroupstayobj.extractingmessagechangegroupstay();
Assert.assertEquals(s1,"success");
}

@Test(priority = 3 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_38_removeguest(){
	Removeguest removeguestobj=new Removeguest();
	removeguestobj.RemoveguestinGroupcall("login");
	String s1 = removeguestobj.extractingmessageRemoveguest();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 4 ,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_27_AddBookingsToGroup(){
AddBookingsToGroup addbookingstogroupobj = new AddBookingsToGroup();
addbookingstogroupobj.fn_addbookingstogroup("login");
String s1 = addbookingstogroupobj.extractingmessageaddbookingstogroup();
Assert.assertEquals(s1,"success");
}
@Test(priority = 5 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_39_getbookingcancelchargeForGroupReservation(){
	GetbookingCancelCharge GetbookingCancelChargeobj=new GetbookingCancelCharge();
	GetbookingCancelChargeobj.GetbookingCancelChargecallForGroupReservation("login","2","true");
	String s1=GetbookingCancelChargeobj.extractingmessageGetbookingCancelCharge();
	Assert.assertEquals(s1, "success");
	
	
}
@Test(priority = 6 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_40_reinstatebookinginGroupReservation(){
	CancelCommitGroup CancelCommitGroupobj=new CancelCommitGroup();
	CancelCommitGroupobj.fn_cancelcommitgroup("login");
	ReinstateBooking ReinstateBookingOBJ=new ReinstateBooking();
	ReinstateBookingOBJ.ReinstateBookingcallinGrpResrvation("login",CancelCommitGroup.edibookingid);
	String s1=ReinstateBookingOBJ.extractingmessageReinstateBooking();
	Assert.assertEquals(s1, "success");
}
//To check the functionality of changegroupowner API in group
@Test(priority = 7 ,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_28_ChangeGroupOwnerInGroup(){
ChangeGroupOwner chnangegroupownerobj = new ChangeGroupOwner();
chnangegroupownerobj.fn_changegroupowner("login");
String s1 = chnangegroupownerobj.extractingmessagechangegroupowner();
Assert.assertEquals(s1,"success");
}
@Test(priority = 8 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_41_addotherchargeWithGroupChargetype(){
	Addothercharge addotherchargeobj=new Addothercharge();
	addotherchargeobj.AddotherchargecallWithGroupChargetype("login");
	String s1 = addotherchargeobj.extractingmessageAddothercharge();
	Assert.assertEquals(s1, "success");	
}
@Test(priority = 9 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_43_addotherchargeWithGuestChargetype(){
	Addothercharge addotherchargeobj=new Addothercharge();
	addotherchargeobj.AddotherchargecallWithGuestChargetype("login");
	String s1=addotherchargeobj.extractingmessageAddothercharge();
	Assert.assertEquals(s1, "success");	
}
//To check the functionality of changegroupowner API in Agent
@Test(priority = 10 ,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_28_ChangeGroupOwnerInAgent(){
ChangeGroupOwner chnangegroupownerobj = new ChangeGroupOwner();
chnangegroupownerobj.fn_changegroupownerforTACorp("login", "agent");
String s1 = chnangegroupownerobj.extractingmessagechangegroupowner();
Assert.assertEquals(s1,"success");
}
@Test(priority = 11 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_44_assignroomforGroup(){
	Assignroom Assignroomobj=new Assignroom();
	Assignroomobj.assignroomcallForGrpReservation("login");
	String s1=Assignroomobj.extractingmessageassignroom();
	Assert.assertEquals(s1, "success");
}
//To check the functionality of changegroupowner API in Corporate
@Test(priority = 12 ,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_28_ChangeGroupOwnerInCorp(){
ChangeGroupOwner chnangegroupownerobj = new ChangeGroupOwner();
chnangegroupownerobj.fn_changegroupownerforTACorp("login", "corporate");
String s1 = chnangegroupownerobj.extractingmessagechangegroupowner();
Assert.assertEquals(s1,"success");
}
//To Check the functionality of remove occupanices
@Test(priority = 13 ,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_29_RemoveOccupancies(){
RemoveOccupancies removeoccupanciesobj = new RemoveOccupancies();
removeoccupanciesobj.fn_removeoccupancies("login");
String s1 = removeoccupanciesobj.extractingmessageremoveoccupancies();
Assert.assertEquals(s1,"success");
}
@Test(priority = 14 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_45_addcustomchargeWithGroupChargeType(){
	Addcustomcharge addcustomchargeobj=new Addcustomcharge();
	addcustomchargeobj.AddcustomchargecallWithGroupChargeType("login");
	String s1 = addcustomchargeobj.extractingmessageAddcustomcharge();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 15 ,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_16_SplitBooking(){

SplitBooking splitbookingobj = new SplitBooking();
splitbookingobj.fn_splitbooking("login");
String s1 = splitbookingobj.extractingmessagesplitbooking();
Assert.assertEquals(s1,"success");


}


@Test(priority = 16 ,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_NEW2_SplitBookingforGroup(){

SplitBooking splitbookingobj = new SplitBooking();
splitbookingobj.fn_splitbookingforgroup("login");
String s1 = splitbookingobj.extractingmessagesplitbooking();
Assert.assertEquals(s1,"success");


}
@Test(priority = 17 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_46_addcustomchargeWithGuestChargeType(){
	Addcustomcharge addcustomchargeobj=new Addcustomcharge();
	addcustomchargeobj.AddcustomchargecallWithGuestChargeType("login");
	String s1 = addcustomchargeobj.extractingmessageAddcustomcharge();
	Assert.assertEquals(s1, "success");
}
//To check the functionality of remove occupanices for group reservation
@Test(priority = 18 ,groups ={"frontdeskcreate"})
public void UC_FD_NEW_NEW3_RemoveOccupanciesforgroup(){
RemoveOccupancies removeoccupanciesobj = new RemoveOccupancies();
removeoccupanciesobj.fn_removeoccupanciesforgroup("login");
String s1 = removeoccupanciesobj.extractingmessageremoveoccupancies();
Assert.assertEquals(s1,"success");
}
@Test(priority = 19 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_47_applypromocodeForGroupReservation_removepromocodeForGroupReservation(){
	ApplyPromocode applypromocodeobj=new ApplyPromocode();
	applypromocodeobj.ApplyPromocodecallForGroupReservation("login");
	String s1=applypromocodeobj.extractingmessageApplyPromocode();
	Assert.assertEquals(s1, "success");
	RemovePromoCode RemovePromoCodeobj=new RemovePromoCode();
	RemovePromoCodeobj.RemovePromoCodecallForGroupReservation("login",ApplyPromocode.editbookigid);
	String s2=RemovePromoCodeobj.extractingmessageremovepromocode();
	Assert.assertEquals(s2, "success");

}
@Test(priority = 20 ,groups = {"frontdeskview"})
public void UC_FD_NEW_TC_48_getaccountstatementsforGRPReservation()
{
	 Getaccountstatement getaccountstatementobj = new Getaccountstatement();
	 getaccountstatementobj.GetaccountstatementcallforGRPReservation("login");
	 String s1 = getaccountstatementobj.extractingmessagegetaccountstatement();
	 Assert.assertEquals(s1, "success");
}
@Test(priority = 21 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_49_editbookingpretaxForGroup(){
	EditBookingPretax EditBookingPretaxobj=new EditBookingPretax();
	EditBookingPretaxobj.EditBookingPretaxcallForGroup("login");
	String s1=EditBookingPretaxobj.extractingmessageeditbookingpretax();
	Assert.assertEquals(s1, "success");
}
//To check the Functionality of sendinvoiceemail
@Test(priority = 22 ,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_30_Sendinvoiceemail(){
Sendinvoiceemail sendinvoicemailobj = new Sendinvoiceemail();
sendinvoicemailobj.Sendinvoiceemailcall("login", "S");
String s1 = sendinvoicemailobj.extractingmessagesendinvoiceemail();
Assert.assertEquals(s1,"success");
}
@Test(priority = 23 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC116_changerateamountgroup(){
	changerateamountgroup changerateamountgroupobj=new changerateamountgroup();
	changerateamountgroupobj.changerateamountgroupcall("login");
	String s1=changerateamountgroupobj.extractingmessagechangerateamountgroup();
	Assert.assertEquals(s1, "success");
}
	
@Test(priority = 24 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC117_confirmholdtillbookings_EditInSingleResv(){
	ConfirmHoldtillBookings confirmholdtillbookingsobj=new ConfirmHoldtillBookings();
	confirmholdtillbookingsobj.confirmholdtillbookingscall_singleRsv_editbooking("login");
	String s1=confirmholdtillbookingsobj.extractingmessageconfirmholdtillbookings();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 25 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC118_confirmholdtillbookings_getbooking_SingleResv(){
	ConfirmHoldtillBookings confirmholdtillbookingsobj=new ConfirmHoldtillBookings();
	confirmholdtillbookingsobj.confirmholdtillbookingscall_singleRsv_getbooking("login");
	String s1=confirmholdtillbookingsobj.extractingmessageconfirmholdtillbookings();
	Assert.assertEquals(s1, "success");
}
//To check the Functionality of sendinvoiceemail for Group resevation
@Test(priority = 26 ,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_SC179_Sendinvoiceemail(){
Sendinvoiceemail sendinvoicemailobj = new Sendinvoiceemail();
sendinvoicemailobj.Sendinvoiceemailcall("login", "G");
String s1 = sendinvoicemailobj.extractingmessagesendinvoiceemail();
Assert.assertEquals(s1,"success");
}
@Test(priority = 27 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC119_confirmholdtillbookings_GrpResv_Getgroup(){
	ConfirmHoldtillBookings confirmholdtillbookingsobj=new ConfirmHoldtillBookings();
	confirmholdtillbookingsobj.confirmholdtillbookingscall_GrpResv_Getgroup("login");
	String s1=confirmholdtillbookingsobj.extractingmessageconfirmholdtillbookings();
	Assert.assertEquals(s1, "success");
}
//To check the functionality of addpaymenttoinvoice API with Cash from getbooking API
//for cheque will be done later
//look onlater
//@Test(priority = 28 ,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_34_addpaymenttoinvoiceWithCash(){
AddPaymenttoInvoice addpaymemttoinvoiceobj = new AddPaymenttoInvoice();
addpaymemttoinvoiceobj.addpaymenttoinvoice("login");
String s1 = addpaymemttoinvoiceobj.extractingmessageaddpaymenttoinvoice();
Assert.assertEquals(s1,"success");
}
//To check the functionality of addpaymenttoinvoice API with credit card from getbooking API

@Test(priority = 29 ,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_31_addpaymenttoinvoiceWithCreditCard(){
AddPaymenttoInvoice addpaymemttoinvoiceobj = new AddPaymenttoInvoice();
addpaymemttoinvoiceobj.addpaymenttoinvoice("login");
String s1 = addpaymemttoinvoiceobj.extractingmessageaddpaymenttoinvoice();
Assert.assertEquals(s1,"success");
}
//To check the functionality of addpaymenttoinvoice API with credit card for Group reservation
//cash and cheque will be done later

@Test(priority = 30 ,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_SC173_addpaymenttoinvoiceforgroup(){
AddPaymenttoInvoice addpaymemttoinvoiceobj = new AddPaymenttoInvoice();
addpaymemttoinvoiceobj.addpaymenttoinvoiceforgroup("login");
String s1 = addpaymemttoinvoiceobj.extractingmessageaddpaymenttoinvoice();
Assert.assertEquals(s1,"success");
}
@Test(priority = 31 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC120_confirmholdtillbookings_GrpResv_Editbooking(){
	ConfirmHoldtillBookings confirmholdtillbookingsobj=new ConfirmHoldtillBookings();
	confirmholdtillbookingsobj.confirmholdtillbookingscall_GrpResv_EditBooking("login");
	String s1=confirmholdtillbookingsobj.extractingmessageconfirmholdtillbookings();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 32 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC121_confirmholdtillbookings_GrpSingleResv_Getgroup(){
	ConfirmHoldtillBookings confirmholdtillbookingsobj=new ConfirmHoldtillBookings();
	confirmholdtillbookingsobj.confirmholdtillbookingscall_GrpSingleResv_Getgroup("login");
	String s1=confirmholdtillbookingsobj.extractingmessageconfirmholdtillbookings();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 33 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC122_confirmholdtillbookings_GrpSingleResv_Editbooking(){
	ConfirmHoldtillBookings confirmholdtillbookingsobj=new ConfirmHoldtillBookings();
	confirmholdtillbookingsobj.confirmholdtillbookings_GrpSingleResv_Editbooking("login");
	String s1=confirmholdtillbookingsobj.extractingmessageconfirmholdtillbookings();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 34 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC123_editholdtilltime_SingleResv_getbooking() throws Throwable{
	editholdtilltime editholdtilltimeobj=new editholdtilltime();
	editholdtilltimeobj.editholdtilltime_SingleResv_getbookingcall("login");
	String s1=editholdtilltimeobj.extractingmessageeditholdtilltime();
	Assert.assertEquals(s1, "success");
}	
@Test(priority = 35 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC124_editholdtilltime_SingleResv_Editbooking() throws Throwable{
	editholdtilltime editholdtilltimeobj=new editholdtilltime();
	editholdtilltimeobj.editholdtilltimecall_SingleResv_Editbooking("login");
	String s1=editholdtilltimeobj.extractingmessageeditholdtilltime();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 36 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC125_editholdtilltime_GrpSingleResv_Getgroup() throws Throwable{
	editholdtilltime editholdtilltimeobj=new editholdtilltime();
	editholdtilltimeobj.editholdtilltimecall_GrpSingleResv_Getgroup("login");
	String s1=editholdtilltimeobj.extractingmessageeditholdtilltime();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 37 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC126_editholdtilltime_GrpSingleResv_Editbooking() throws Throwable{
	editholdtilltime editholdtilltimeobj=new editholdtilltime();
	editholdtilltimeobj.editholdtilltimecall_GrpSingleResv_Editbooking("login");
	String s1=editholdtilltimeobj.extractingmessageeditholdtilltime();
	Assert.assertEquals(s1, "success");
}
//To check the functionality of addpaymenttoinvoice API with cash/credit card/cheque for Single Reservation

//
//@Test(priority = 38 ,groups = {"frontdeskcreate"})
//public void UC_FD_NEW_TC_52_addpaymenttoinvoice() throws Throwable{
//AddPaymentToBooking addpaymentbookingobj = new AddPaymentToBooking();
//addpaymentbookingobj.fn_addpaymenttobookinggroup("login");
//String s1= addpaymentbookingobj.extractingmessageAddPaymenttobooking();
//Assert.assertEquals(s1, "success");
//
//}


@Test(priority = 39 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_routefolios() throws Throwable{
	
    RoutetoFolio routetofolioobj = new RoutetoFolio();
    routetofolioobj.fn_routetofolio("login", "S" );
    String s2 = routetofolioobj.extractingmessageforroutetofolio();
    Assert.assertEquals(s2, "success");

}

@Test(priority = 40 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_routefoliosgroup() throws Throwable{
	
    RoutetoFolio routetofolioobj = new RoutetoFolio();
    routetofolioobj.fn_routetofolio("login", "G" );
    String s2 = routetofolioobj.extractingmessageforroutetofolio();
    Assert.assertEquals(s2, "success");

}


@Test(priority = 41 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC130_editsharer(){
	EditSharer EditSharerobj= new EditSharer();
	EditSharerobj.EditSharercall("login");
	String s1= EditSharerobj.extractingmessageeditsharer();
	Assert.assertEquals(s1, "success");
}
 @Test(priority = 42 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC131_editsharer_Grp_SingleResv(){
	EditSharer EditSharerobj= new EditSharer();
	EditSharerobj.EditSharercall_Grp_SingleResv("login");
	String s1= EditSharerobj.extractingmessageeditsharer();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 43 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC136_splitrate_SingleResv(){
	Splitrate Splitrateobj=new Splitrate();
	Splitrateobj.splitrate_SingleResv("login");
	String s1=Splitrateobj.extractingmessagessplitrate();
		Assert.assertEquals(s1, "success");
}
//To check the Functionality of updatebookingsource for Single resevation
@Test(priority = 44 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC138_updatebookingsource() throws Throwable{
UpdateBookingSource updatebookingsourceobj = new UpdateBookingSource();
updatebookingsourceobj.fn_UpdateBookingSource("login","S","Single");
    String s1= updatebookingsourceobj.extractingmessagegeupdatebookingsource();
    Assert.assertEquals(s1, "success");

}
//To check the functionality of getvoidinvoicebydate API

@Test(priority = 45 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC189_getinvoicesbydate() throws Throwable{
GetInvoicesByDate getinvoicesobj = new GetInvoicesByDate();
getinvoicesobj.fn_getinvoicesbydate("login");
String s1= getinvoicesobj.extractingmessageforgetinvoicesbydate();
Assert.assertEquals(s1, "success");

}
//To check the Functionality of updatebookingsource for Single resevation with main booking id

@Test(priority = 46 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC139_updatebookingsource() throws Throwable{
UpdateBookingSource updatebookingsourceobj = new UpdateBookingSource();
updatebookingsourceobj.fn_UpdateBookingSource("login","S","GetBooking");
    String s1= updatebookingsourceobj.extractingmessagegeupdatebookingsource();
    Assert.assertEquals(s1, "success");

}
//To check the Functionality of updatebookingsource for Group reservation

@Test(priority = 47 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC140_updatebookingsource() throws Throwable{
UpdateBookingSource updatebookingsourceobj = new UpdateBookingSource();
updatebookingsourceobj.fn_UpdateBookingSource("login","G","Group");
    String s1= updatebookingsourceobj.extractingmessagegeupdatebookingsource();
    Assert.assertEquals(s1, "success");

}
@Test(priority = 48 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC137_splitrate_GRP_SingleResv(){
	Splitrate Splitrateobj=new Splitrate();
	Splitrateobj.splitrate_GRP_SingleResv("login");
	String s1=Splitrateobj.extractingmessagessplitrate();
	Assert.assertEquals(s1, "success");
}
//To check the Functionality of updatebookingsource for Group resevation with main booking id
@Test(priority = 49 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC141_updatebookingsource() throws Throwable{
UpdateBookingSource updatebookingsourceobj = new UpdateBookingSource();
updatebookingsourceobj.fn_UpdateBookingSource("login","G","GroupCode");
  String s1= updatebookingsourceobj.extractingmessagegeupdatebookingsource();
  Assert.assertEquals(s1, "success");

}
@Test(priority = 50 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC050_releasebooking_SingleResv(){
	Releasebooking Releasebookingobj=new Releasebooking();
	Releasebookingobj.releasebookingcall_SingleResv("login");
	String s1=Releasebookingobj.extractingmessagereleasebooking();
	Assert.assertEquals(s1, "success");
}
//To check the Functionality of updatebookingsource for Group's Single resevation
@Test(priority = 51 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC142_updatebookingsource() throws Throwable{
UpdateBookingSource updatebookingsourceobj = new UpdateBookingSource();
updatebookingsourceobj.fn_UpdateBookingSource("login","S","GroupSingleresevation");
    String s1= updatebookingsourceobj.extractingmessagegeupdatebookingsource();
    Assert.assertEquals(s1, "success");

}
//To check the Functionality of updatebookingsource for Group's Single resevation with main booking id
@Test(priority = 52 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC143_updatebookingsource() throws Throwable{
UpdateBookingSource updatebookingsourceobj = new UpdateBookingSource();
updatebookingsourceobj.fn_UpdateBookingSource("login","S","GroupSingleresevationwithmainbookingid");
    String s1= updatebookingsourceobj.extractingmessagegeupdatebookingsource();
    Assert.assertEquals(s1, "success");

}
@Test(priority = 53 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC051_releasebooking_Grp_SingleResv(){
	Releasebooking Releasebookingobj=new Releasebooking();
	Releasebookingobj.releasebooking_Grp_SingleResv("login");
	String s1=Releasebookingobj.extractingmessagereleasebooking();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 54 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC133_getguestdetail_GrpResv(){
	GetGuestDetail GetGuestDetailOBJ=new GetGuestDetail();
	GetGuestDetailOBJ.getguestdetailcall_GrpResv("login");
	String s1=GetGuestDetailOBJ.extractingmessageGetGuestDetail();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 55 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC115_changerateamount_GrpResv(){
	ChangeRateAmount ChangeRateAmountobj=new ChangeRateAmount();
	ChangeRateAmountobj.changerateamountcall_GrpResv("login");
	String s1=ChangeRateAmountobj.extractingmessagechangerateamount();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 56 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_50_addpaymenttobooking() throws Throwable{
AddPaymentToBooking addpaymentbookingobj = new AddPaymentToBooking();
addpaymentbookingobj.fn_addpaymenttobooking("login");
String s1= addpaymentbookingobj.extractingmessageAddPaymenttobooking();
Assert.assertEquals(s1, "success");

}
@Test(priority = 57 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_51_addpaymenttobooking() throws Throwable{
AddPaymentToBooking addpaymentbookingobj = new AddPaymentToBooking();
addpaymentbookingobj.fn_addpaymenttobookinggroup("login");
String s1= addpaymentbookingobj.extractingmessageAddPaymenttobooking();
Assert.assertEquals(s1, "success");

}
//To check the Functionality of routepaymentstofolios from one invoice to another.
@Test(priority = 58 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC174_routepaymenttofolios() throws Throwable{
RoutePaymentsToFolios routepaymenttofoliosobj = new RoutePaymentsToFolios();
routepaymenttofoliosobj.fn_routepaymenttofolios("login","S");
String invoiceid = routepaymenttofoliosobj.invoiceid;
String s1= routepaymenttofoliosobj.extractingmessageroutepaymenttofolios();
Assert.assertEquals(s1, "success");
FinalizeInvoice finalizeinvoiceobj = new FinalizeInvoice();
finalizeinvoiceobj.fn_finalizeinvoice("login", invoiceid);

}

//To check the Functionality of routepaymentstofolios from one invoice to another in group.
@Test(priority = 59 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC175_routepaymenttofolios() throws Throwable{
RoutePaymentsToFolios routepaymenttofoliosobj = new RoutePaymentsToFolios();
routepaymenttofoliosobj.fn_routepaymenttofolios("login","G");
String invoiceid = routepaymenttofoliosobj.invoiceid;
String s1= routepaymenttofoliosobj.extractingmessageroutepaymenttofolios();
Assert.assertEquals(s1, "success");
FinalizeInvoice finalizeinvoiceobj = new FinalizeInvoice();
finalizeinvoiceobj.fn_finalizeinvoice("login", invoiceid);

}


//To check the functionality of  finalizeinvoice API for the Single reservation
//@Test(groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC176_finalizeinvoice() throws Throwable{
RoutePaymentsToFolios routepaymenttofoliosobj = new RoutePaymentsToFolios();
routepaymenttofoliosobj.fn_routepaymenttofolios("login","S");
    String s1= routepaymenttofoliosobj.extractingmessageroutepaymenttofolios();
    Assert.assertEquals(s1, "success");
}
@Test(priority = 60 ,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC156_consolidateaccount_Grp_SingleResv() throws Throwable{
	Consolidateaccount consolidateaccountobj = new Consolidateaccount();
	consolidateaccountobj.Consolidateaccountcall("login", "G");
	String s1 = consolidateaccountobj.extractingmessageconsolidteaccount();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 61, groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_SC156_consolidateaccount_Grp_Resv() throws Throwable{
	Consolidateaccount consolidateaccountobj = new Consolidateaccount();
	consolidateaccountobj.Consolidateaccountcall_Grp_Resv("login", "G");
	String s1 = consolidateaccountobj.extractingmessageconsolidteaccount();
	Assert.assertEquals(s1, "success");
}

@Test(priority = 62, groups = {"frontdeskcreate"})
public void UC_FD_3_TC_1_editbooking ()
{
	  Editbooking editbookingobj = new Editbooking();
	  editbookingobj.Editbookingcall("login");
	  String s1 = editbookingobj.extractingmessageeditbooking();
	  Assert.assertEquals(s1, "success");
}

@Test(priority = 63, groups = {"frontdeskcreate"})
public void UC_FD_4_TC_1_commitedbooking()
{
	  Commiteditbooking commiteditbookingobj = new Commiteditbooking();
	  commiteditbookingobj.Commiteditbookingcall("login","G","RESERVE");
	  String s1 = commiteditbookingobj.extractingmessagecommiteditbooking();
	  Assert.assertEquals(s1, "success");
}

@Test(priority = 64, groups = {"frontdeskcreate"})
public void UC_FD_5_TC_1_closeeditbooking()
{
	  Closeeditbooking closeeditbookingobj = new Closeeditbooking();
	  closeeditbookingobj.Closeeditbookingcall("login");
	  String s1 = closeeditbookingobj.extractingmessagecloseeditbooking();
	  Assert.assertEquals(s1, "success");
}

@Test(priority = 65, groups = {"frontdeskview"})
public void UC_FD_10_TC_1_getroomtypestochange()
{
	  Getroomtypestochange getroomtypestochangeobj = new Getroomtypestochange();
	  getroomtypestochangeobj.Getroomtypestochangecall("login");
	  String s1 = getroomtypestochangeobj.extractingmessagegetroomtypestochange();
	  Assert.assertEquals(s1, "success");
}


@Test(priority = 66, groups= {"frontdeskview"})
public void UC_FD_13_TC_1_getusers()
{
	  Getusers getusersobj = new Getusers();
	  getusersobj.Getuserscall("wsauth");
	  String s1 = getusersobj.extractingmessagegetusers();
	  Assert.assertEquals(s1, "success");
}

@Test(priority = 67, groups = {"frontdeskview"})
public void UC_FD_14_TC_1_getdnrs()
{
	  Getdnrs getdnrsobj = new Getdnrs();
	  getdnrsobj.Getdnrscall("login");
	  //getdnrsobj.extractingdnrid();
	  String s1 = getdnrsobj.extractingmessagegetdnrs();
	  Assert.assertEquals(s1, "success");
}

@Test(priority = 68, groups = {"frontdeskview"})
public void UC_FD_15_TC_1_gethousestatus()
{
	  Gethousestatus gethousestatusobj = new Gethousestatus();
	  gethousestatusobj.Gethousestatuscall("login");
	  String s1 = gethousestatusobj.extractingmessagegethousestatus();
	  gethousestatusobj.extractingroomtypeids();
	  gethousestatusobj.extractingunassignroomsid();
	  Assert.assertEquals(s1, "success");
}


@Test(priority = 69, groups = {"frontdeskview"})
public void UC_FD_16_TC_1_gethoteldata()
{
	  Gethoteldata gethoteldataobj = new Gethoteldata();
	  gethoteldataobj.Gethoteldatacall("login");
	  String s1 = gethoteldataobj.extractingmessagegethoteldata();
	  Assert.assertEquals(s1, "success");
}

@Test(priority = 70, groups = {"frontdeskview"})
public void UC_FD_17_TC_1_getcheckins()
{
	  Getcheckins getcheckinsobj = new Getcheckins();
	  getcheckinsobj.Getcheckinscall("login");
	  String s1 = getcheckinsobj.extractingmessagegetcheckins();
	  Assert.assertEquals(s1, "success");
}

@Test(priority = 71, groups = {"frontdeskview"})
public void UC_FD_18_TC_1_getcheckouts()
{
	  Getcheckouts getcheckoutsobj = new Getcheckouts();
	  getcheckoutsobj.Getcheckoutscall("login");
	  String s1 = getcheckoutsobj.extractingmessagegetcheckouts();
	  Assert.assertEquals(s1, "success");
}

 
 

//////////////////////////////////////////////////////////////////////////////////////////////////////
//@AfterSuite(alwaysRun = true)
public void aftersuite()
{
	  System.out.println("hello aftersuite");
	  Logout logoutobj = new Logout();
	  logoutobj.Logoutcall();
	  String s1 = logoutobj.extractingmessageLogout();
	  //Assert.assertEquals(s1, "success");
	  System.out.println(s1);
}
//////////////////////////////////////////////////////////////////////////////////////////////////////


@Test(priority = 72, groups = {"frontdeskview"})
public void UC_FD_19_TC_1_getbookings()
{
	  System.out.println("hello test");
	  Getbookings getbookingsobj = new Getbookings();
	  getbookingsobj.Getbookingscall("login");
	  String s1 =getbookingsobj.extractingmessagegetbookings();
	  Assert.assertEquals(s1,"success");
}


@Test(priority = 73,groups = {"frontdeskview"})
public void UC_FD_20_TC_1_getbookingsearch()
{
	  Getbookingsearch getbookingsearchobj = new Getbookingsearch();
	  getbookingsearchobj.Getbookingsearchcall("login","singlereserve");
	  String s1 = getbookingsearchobj.extractingmessagegetbookingsearch();
	  Assert.assertEquals(s1, "success");
}
 
@Test(priority = 74,groups = {"frontdeskview"})
public void UC_FD_21_TC_1_getbooking() throws IOException
{
	  Getbooking getbookingobj = new Getbooking();
	  getbookingobj.getbookingcall("login");
	  String s1 = getbookingobj.extractingmessagegetbooking();
	  Assert.assertEquals(s1, "success");
}

@Test(priority = 75,groups = {"frontdeskview"})
public void UC_FD_22_TC_1_getgroup()
{
	 Getgroup getgroupobj = new Getgroup();
	 getgroupobj.Getgroupcall("login");
	 String s1 = getgroupobj.extractingmessagegetgroup();
	 Assert.assertEquals(s1, "success");
}

@Test(priority = 76,groups = {"frontdeskview"})
public void UC_FD_23_TC_1_getaccountstatements()
{
	 Getaccountstatement getaccountstatementobj = new Getaccountstatement();
	 getaccountstatementobj.Getaccountstatementcall("login");
	 String s1 = getaccountstatementobj.extractingmessagegetaccountstatement();
	 Assert.assertEquals(s1, "success");
}

@Test(priority = 77,groups = {"frontdeskview"})
public void UC_FD_24_TC_1_getinvoices()
{
Getinvoices getinvoicesobj = new Getinvoices();
getinvoicesobj.Getinvoicescall("login","S");
String s1 = getinvoicesobj.extractingmessagegetinvoices();
String s2 = getinvoicesobj.extractinginvoiceid();
Assert.assertEquals(s1, "success");



}

//To check the functionality of getinvoices API for group reservation

@Test(priority = 78 ,groups = {"frontdeskview"})
public void UC_FD_24_TC_SC163_getinvoices()
{
Getinvoices getinvoicesobj = new Getinvoices();
getinvoicesobj.Getinvoicescall("login", "G");
String s1 = getinvoicesobj.extractingmessagegetinvoices();
String s2 = getinvoicesobj.extractinginvoiceid();
Assert.assertEquals(s1, "success");

}


@Test(priority = 79, groups = {"frontdeskview"})
public void UC_FD_25_TC_1_getinvoice()
{
Getinvoice getinvoiceobj = new Getinvoice();
getinvoiceobj.Getinvoicecall("login");
String s1 = getinvoiceobj.extractingmessagegetinvoice();
Assert.assertEquals(s1, "success");

}
	

@Test(priority = 80, groups = {"frontdeskview"})
public void UC_FD_26_TC_1_getearlycheckincharge()
{
	Getearlycheckincharge getearlycheckinchargeobj = new Getearlycheckincharge();
	//getearlycheckinchargeobj.getearlycheckinchargecall("login", "singlereserve");
	getearlycheckinchargeobj.getearlycheckinchargecall("login", "G");

	String s1 = getearlycheckinchargeobj.extractingmessagegetearlycheckincharge();
	Assert.assertEquals(s1, "success");
}

@Test(priority = 81, groups = {"frontdeskview"})
public void UC_FD_27_TC_1_getearlycheckoutcharge()
{
	Getearlycheckoutcharge getearlycheckoutchargeobj = new Getearlycheckoutcharge();
	getearlycheckoutchargeobj.Getearlycheckoutchargecall("login");
	String s1 = getearlycheckoutchargeobj.extractingmessagegetearlycheckoutcharge();
	Assert.assertEquals(s1, "success");
}

@Test(priority = 82,groups = {"frontdeskview"})
public void UC_FD_29_TC_1_getratestochange()
{
	Getratestochange getratestochangeobj = new Getratestochange();
	getratestochangeobj.Getratestochangecall("login");
	String s1 = getratestochangeobj.extractingmessagegetratestochange();
	Assert.assertEquals(s1, "success");
}

@Test(priority = 83,groups = {"frontdeskview"})
public void UC_FD_30_TC_1_getgroupcancellationcharge()
{
	Getgroupcancelcharge getgroupcancelchargeobj = new Getgroupcancelcharge();
	getgroupcancelchargeobj.Getgroupcancelchargecall("login","G");
	String s1 = getgroupcancelchargeobj.extractingmessagegetgroupcancellationcharge();
	Assert.assertEquals(s1, "success");
}

@Test(priority = 84,groups = {"frontdeskview"})
public void UC_FD_31_TC_1_getdepositamount()
{
	Getdepositamount getdepositamountobj = new Getdepositamount();
	getdepositamountobj.Getdepositamountcall("login");
	String s1 = getdepositamountobj.extractingmessagegetdepositamount();
	Assert.assertEquals(s1, "success");
}

@Test(groups = {"tst"})
public void UC_FD_33_TC_1_assignroom()
{
	Assignroom assignroomobj = new Assignroom();
	assignroomobj.assignroomcall("login", "singlereserveunassigned");
	String s1 = assignroomobj.extractingmessageassignroom();
	Assert.assertEquals(s1, "success");
}

@Test(priority = 85,groups = {"frontdeskview"})
public void UC_FD_34_TC_1_unassignroom()
{
	Unassignroom unassignroomobj = new Unassignroom();
	try {
		unassignroomobj.unassignroomcall("login");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String s1 = unassignroomobj.extractingmessageunassignroom();
	Assert.assertEquals(s1, "success");
}


@Test(priority = 86,groups = {"frontdeskcreate"})
public void UC_FD_37_TC_1_movebooking()
{
Movebooking movebookingobj = new Movebooking();
movebookingobj.Movebookingcall("login");
String s1 = movebookingobj.extractingmessagemovebooking();
Assert.assertEquals(s1, "success");
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
@Test(priority = 87,groups = {"frontdeskview"})
public void UC_FD_45_TC_1()
{
	Getallpaytypes getallpaytypesobj = new Getallpaytypes();
	getallpaytypesobj.Getallpaytypescall("login");
	String s1 = getallpaytypesobj.extractingmessageGetallpaytypes();
	System.out.println("mukesh"+s1);
	Assert.assertEquals(s1, "success");
}







/////////////////////////////////////////////////////////////////////////////////////////////////////////////



/*@Test(priority = 88,groups = {"frontdeskcreate"})
public void UC_FD_50_TC_1_adddnrs()
{
	Adddnrs adddnrsobj = new Adddnrs();
	adddnrsobj.Adddnrscall("login");
	String s1 = adddnrsobj.extractingmessageadddnrs();
	Assert.assertEquals(s1, "success");
}*/


@Test(priority = 89,groups = {"frontdeskcreate"})
public void UC_FD_51_TC_1_deletedandaddnrs()
{
	Deletednrs deletednrsobj = new Deletednrs();
	deletednrsobj.Deletednrscall("login");
	String s1 = deletednrsobj.extractingmessagedeletednrs();
	Assert.assertEquals(s1, "success");
	Adddnrs adddnrsobj = new Adddnrs();
	adddnrsobj.Adddnrscall("login");
	String s2 = adddnrsobj.extractingmessageadddnrs();
	Assert.assertEquals(s2, "success");
}


@Test(priority = 90,groups = {"frontdeskcreate"})
public void UC_FD_52_TC_1_editdnrs()
{
	Editdnrs editdnrsobj = new Editdnrs();
	editdnrsobj.Editdnrcall("login");
	String s1 = editdnrsobj.extractingmessageeditdnrs();
	Assert.assertEquals(s1, "success");
}

@Test(priority = 91,groups = {"frontdesk"})
public void UC_FD_53_TC_1_keepalive()
{
	Keepalive keepaliveobj = new Keepalive();
	keepaliveobj.Keepalivecall("login");
	String s1 = keepaliveobj.extractingmessagekeepalive();
	Assert.assertEquals(s1, "success");
}

@Test(priority = 92,groups = {"frontdeskcreate"})
public void UC_FD_54_TC_1_addoccupancies()
{
	Addoccupancies addoccupanciesobj = new Addoccupancies();
	addoccupanciesobj.Addoccupanciescall("login","S","RESERVE");
	String s1 = addoccupanciesobj.extractingmessageaddoccupancies();
	Assert.assertEquals(s1, "success");
}

@Test(priority = 93,groups = {"frontdeskcreate"})
public void UC_FD_54_TC_SC093_addoccupancies()
{
Addoccupancies addoccupanciesobj = new Addoccupancies();
addoccupanciesobj.Addoccupanciescallforgroup("login");
String s1 = addoccupanciesobj.extractingmessageaddoccupancies();
Assert.assertEquals(s1, "success");
}



@Test(priority = 94,groups ={"frontdeskcreate"})
public void UC_FD_55_TC_1_changepayterm()
{
	Changepayterm changepaytermobj = new Changepayterm();
	changepaytermobj.Changepaytermcall("login");
	String s1 = changepaytermobj.extractingmessagechangepayterm();
	Assert.assertEquals(s1, "success");
}

@Test(priority = 95,groups = {"frontdeskview"})
public void UC_FD_56_TC_1_checknightauditstatus()
{
	Checknightauditstatus Checknightauditstatusobj = new Checknightauditstatus();
	Checknightauditstatusobj.Checknightauditstatuscall("login");
	String s1 = Checknightauditstatusobj.extractingmessagecheckingnightauditstatus();
	Assert.assertEquals(s1, "Night Audit completed and current date of the system is");
}

@Test(priority = 96,groups = {"frontdeskcreate"})
public void UC_FD_57_TC_1_consolidateaccount()
{
	Consolidateaccount consolidateaccountobj = new Consolidateaccount();
	consolidateaccountobj.Consolidateaccountcall("login", "S");
	String s1 = consolidateaccountobj.extractingmessageconsolidteaccount();
	Assert.assertEquals(s1, "success");
}

@Test(priority = 97,groups = {"frontdeskcreate"})
public void UC_FD_58_TC_1_deleteproformainvoice()
{
	Deleteproformainvoice deleteproformainvoiceobj = new Deleteproformainvoice();
deleteproformainvoiceobj.Deleteproformainvoicecall("login", "S");
	String s1 = deleteproformainvoiceobj.extractingmessagedeleteproformainvoice();
	Assert.assertEquals(s1, "success");
}

@Test(priority = 98,groups = {"frontdeskview"})
public void UC_FD_70_TC_1_guestlookup()
{
	Guestlookup guestlookupobj = new Guestlookup();
	guestlookupobj.Guestlookupcall("login");
	String s1 = guestlookupobj.extractingmessageguestlookup();
	Assert.assertEquals(s1, "success");
}

@Test(priority = 99,groups = {"frontdeskcreate"})
public void UC_FD_84_TC_1_sendbookingemail()
{
	Sendbookingemail sendbookingemailobj = new Sendbookingemail();
	sendbookingemailobj.Senbookingemailcall("login");

	String s1 = sendbookingemailobj.extractingmessagesendbookingemail();
System.out.println("New");
	Assert.assertEquals(s1, "success");
}
	
@Test(priority = 100,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_1_removeguest(){
	Removeguest removeguestobj=new Removeguest();
	removeguestobj.Removeguestcall("login");
	String s1 = removeguestobj.extractingmessageRemoveguest();
	Assert.assertEquals(s1, "success");
	
}
@Test(priority = 101,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_2_addothercharge(){
	Log4j.startTestCase("Addothercharge API Script Started");
	Addothercharge addotherchargeobj=new Addothercharge();
	addotherchargeobj.Addotherchargecall("login");
	String s1 = addotherchargeobj.extractingmessageAddothercharge();
	Assert.assertEquals(s1, "success");
	Log4j.endTestCase("Ended Script Successfully");
	
}

@Test(priority = 102,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_3_addcustomcharge(){
	Addcustomcharge addcustomchargeobj=new Addcustomcharge();
	addcustomchargeobj.Addcustomchargecall("login");
	String s1 = addcustomchargeobj.extractingmessageAddcustomcharge();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 103,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_4_getcustomchargetaxes(){
	GetCustomChargetaxes getcustomchargetaxesobj=new GetCustomChargetaxes();
	getcustomchargetaxesobj.GetCustomChargetaxescall("login");
	String s1 = getcustomchargetaxesobj.extractingmessageGetCustomChargetaxes();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 104,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_5_addbookinggstin(){
	AddBookingGstin AddBookingGstinobj=new AddBookingGstin();
	AddBookingGstinobj.AddBookingGstincall("login");
	String s1 = AddBookingGstinobj.extractingmessageAddBookingGstin();
	Assert.assertEquals(s1, "GSTIN Details saved");
}
@Test(priority = 105,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_6_applypromocode_removepromocode(){
	ApplyPromocode applypromocodeobj=new ApplyPromocode();
	applypromocodeobj.ApplyPromocodecall("login");
	String s1 = applypromocodeobj.extractingmessageApplyPromocode();
	Assert.assertEquals(s1, "success");
	RemovePromoCode RemovePromoCodeobj=new RemovePromoCode();
	RemovePromoCodeobj.RemovePromoCodecall("login",ApplyPromocode.editbookigid);
	String s2=RemovePromoCodeobj.extractingmessageremovepromocode();
	Assert.assertEquals(s2, "success");
	
}
/*@Test(priority = 106,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_14_generatePerformaInvoice(){
GeneratePerformaInvoice generateperformainovoiceobj = new GeneratePerformaInvoice();
generateperformainovoiceobj.GeneratePerformaInvoiceAPI("login", "S");


}
//To check the functionality of  generateproformainvoice API for the Group reservation
@Test(priority = 107,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_SC159_generatePerformaInvoice(){
GeneratePerformaInvoice generateperformainovoiceobj = new GeneratePerformaInvoice();
generateperformainovoiceobj.GeneratePerformaInvoiceAPI("login", "G");


}*/
@Test(priority = 108,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_7_getavailabledisounts(){
	GetAvailableDisounts getavailabledisountsobj=new GetAvailableDisounts();
	getavailabledisountsobj.GetAvailableDisountscall("login");
	String s1=getavailabledisountsobj.extractingmessageGetAvailableDisounts();
	Assert.assertEquals(s1, "success");
	
}
/*//Change Room type for single reservation
@Test(priority = 109,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_13_ChangeRoomType(){

ChangeRoomType changeroomtypeobj = new ChangeRoomType();
changeroomtypeobj.ChangeRoomTypeAPI("login");
String s1 = changeroomtypeobj.extractingmessagegetchaneroomtypemsg();
Assert.assertEquals(s1,"success");
}
//change room type for group reservation
@Test(priority = 110,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_NEW1_ChangeRoomTypeforgroup(){

ChangeRoomType changeroomtypeobj = new ChangeRoomType();
changeroomtypeobj.ChangeRoomTypeAPIgroup("login");
String s1 = changeroomtypeobj.extractingmessagegetchaneroomtypemsg();
Assert.assertEquals(s1,"success");
}*/
@Test(priority = 111,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_8_applyspecialdiscount(){
	ApplySpecialDiscount applyspecialdiscountobj=new ApplySpecialDiscount();
	applyspecialdiscountobj.ApplySpecialDiscountcall("login");
	String s1=applyspecialdiscountobj.extractingmessageApplySpecialDiscount();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 112,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_9_getroomstoassign(){
	Getroomstoassign Getroomstoassignobj=new Getroomstoassign();
	Getroomstoassignobj.getroomstoassigncall("login");
	String s1=Getroomstoassignobj.extractingmessagegetroomstoassign();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 113,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_10_assignroom(){
	Assignroom Assignroomobj=new Assignroom();
	Assignroomobj.assignroomcall("login");
	String s1=Assignroomobj.extractingmessageassignroom();
	Assert.assertEquals(s1, "success");
}
/*@Test(priority = 114,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_NEW1_ChangeRoomTypes(){

ChangeRoomTypes changeroomtypeobj = new ChangeRoomTypes();
changeroomtypeobj.ChangeRoomTypesAPIgroup("login");
String s1 = changeroomtypeobj.extractingmessagegetchaneroomtypemsg();
Assert.assertEquals(s1,"success");
}*/
/*@Test(priority = 115,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_11_guestcheckin_cancelcheckin(){
	GuestCheckin guestcheckinobj=new GuestCheckin();
	guestcheckinobj.GuestCheckincall("login" ,"false" ,"S");
	String s1=guestcheckinobj.extractingmessageGuestCheckin();
	Assert.assertEquals(s1, "success");
	CancelCheckin cancelcheckinobj=new CancelCheckin();
	cancelcheckinobj.CancelCheckincall("login");
	String s2=cancelcheckinobj.extractingmessageCancelCheckin();
	Assert.assertEquals(s2, "success");
	
	
}*/
@Test(priority = 116,groups = {"frontdeskcreate"})
  public void UC_FD_NEW_TC_12_getbookingcancelcharge(){
	GetbookingCancelCharge GetbookingCancelChargeobj=new GetbookingCancelCharge();
	GetbookingCancelChargeobj.GetbookingCancelChargecallForFDReservation("login","1","false");
	String s1=GetbookingCancelChargeobj.extractingmessageGetbookingCancelCharge();
	Assert.assertEquals(s1, "success");
}
//Change Room for Single Reservation
//HOLD Due to  NIght audit issue
//@Test(groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_15_ChangeRoom() throws Throwable{

ChangeRoom changeroomobj = new ChangeRoom();
changeroomobj.fn_changeRoom("login");

}

	@Test(groups = {"frontdeskcreate"})
	public void UC_FD_NEW_TC_42_cancelandcommitbooking_reinstatebooking(){
	CancelandCommitBooking CancelandCommitBookingobj=new CancelandCommitBooking();
	CancelandCommitBookingobj.CancelandCommitBookingcallforcancelcharge("login","false");
	String s2=CancelandCommitBookingobj.extractingmessageCancelandCommitBooking();
	Assert.assertEquals(s2, "success");
	ReinstateBooking ReinstateBookingOBJ=new ReinstateBooking();
	ReinstateBookingOBJ.ReinstateBookingcall("login",CancelandCommitBooking.editbookigid);
	String s3=ReinstateBookingOBJ.extractingmessageReinstateBooking();
	Assert.assertEquals(s3, "success");
}
/*@Test(priority = 117,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_17_cancelcheckout_guestcheckout(){
	CancelCheckout CancelCheckoutobj=new CancelCheckout();
	CancelCheckoutobj.CancelCheckoutcall("login");
	String s1=CancelCheckoutobj.extractingmessageCancelCheckout();
	Assert.assertEquals(s1, "success");
	GuestCheckOut GuestCheckOutobj=new GuestCheckOut();
	GuestCheckOutobj.GuestCheckOutCall("login");
	String s2=GuestCheckOutobj.extractingmessageGuestCheckOut();
	Assert.assertEquals(s2, "success");
}*/

@Test(priority = 118,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_19_getguestdetail(){
	GetGuestDetail GetGuestDetailOBJ=new GetGuestDetail();
	GetGuestDetailOBJ.GetGuestDetail("login");
	String s1=GetGuestDetailOBJ.extractingmessageGetGuestDetail();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 119,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_21_gethotelsettings(){
	GetHotelSettings GetHotelSettingsobj=new GetHotelSettings();
	GetHotelSettingsobj.GetHotelSettingscall("login");
	String s1=GetHotelSettingsobj.extractingmessageGetHotelSettings();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 120,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_18_ChangeBookingStay(){

ChangeBookingStay changebookingstayobj = new ChangeBookingStay();
changebookingstayobj.fn_changebooking("login", "S", "Singlebookinid");
String s1 = changebookingstayobj.extractingmessagechangebooking();
Assert.assertEquals(s1,"success");
}
//To check the functionality of changebookingstay API for the Group's Single reservation.
@Test(priority = 121,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TCSC084_ChangeBookingStay(){

ChangeBookingStay changebookingstayobj = new ChangeBookingStay();
changebookingstayobj.fn_changebooking("login", "G", "GroupSinglebookinid");
String s1 = changebookingstayobj.extractingmessagechangebooking();
Assert.assertEquals(s1,"success");
}
/*@Test(priority = 122,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_20_ChangeGroupStay(){

ChangeGroupStay changegroupstayobj = new ChangeGroupStay();
changegroupstayobj.fn_changegroupstay("login");
String s1 = changegroupstayobj.extractingmessagechangegroupstay();
Assert.assertEquals(s1,"success");
}*/
@Test(priority = 123,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_22_getminimumbookingrule(){
	GetMinimumBookingRule GetMinimumBookingRuleobj=new GetMinimumBookingRule();
	GetMinimumBookingRuleobj.GetMinimumBookingRulecall("login");
	String s1=GetMinimumBookingRuleobj.extractingmessageGetMinimumBookingRule();
	Assert.assertEquals(s1, "success");
}
/*@Test(priority = 124,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_24_changerateamount(){
	ChangeRateAmount ChangeRateAmountobj=new ChangeRateAmount();
	ChangeRateAmountobj.ChangeRateAmountCall("login");
	String s1=ChangeRateAmountobj.extractingmessagechangerateamount();
	Assert.assertEquals(s1, "success");
}*/
//API On hold due to incorrect response while doing manually.
//@Test(groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_21_ChangeGuestStay(){

ChangeGuestStay changegueststayobj = new ChangeGuestStay();
changegueststayobj.fn_changegueststay("login");
String s1 = changegueststayobj.extractingmessagechangegueststay();
Assert.assertEquals(s1,"success");
}
@Test(priority = 125,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_22_AttachGuest(){

AttachGuest attachguestobj = new AttachGuest();
attachguestobj.fn_attachguest("login" , "S");
String s1 = attachguestobj.extractingmessageattachguest();
Assert.assertEquals(s1,"success");
}

@Test(priority = 126,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_SC089_AttachGuest(){

AttachGuest attachguestobj = new AttachGuest();
attachguestobj.fn_attachguest("login" , "G");
String s1 = attachguestobj.extractingmessageattachguest();
Assert.assertEquals(s1,"success");
}
@Test(priority = 127,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_23_ChangeRate(){

ChangeRate changerateobj = new ChangeRate();
changerateobj.fn_changerate("login");
String s1 = changerateobj.extractingmessagechangerate();
Assert.assertEquals(s1,"success");
}
@Test(priority = 128,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_32_logoutusers(){
	LogoutUsers LogoutUsersOBJ=new LogoutUsers();	
	LogoutUsersOBJ.LogoutUserscall("login");
	String s1=LogoutUsersOBJ.extractingmessageLogoutUsers();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 129,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_NEW3_ChangeRateforgroup(){

ChangeRate changerateobj = new ChangeRate();
changerateobj.fn_changerateforgroup("login");
String s1 = changerateobj.extractingmessagechangerate();
Assert.assertEquals(s1,"success");
}
@Test(priority = 130,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_24_UpdateGuestDetails(){
UpdateGuestDetails updateguestdetailsobj = new UpdateGuestDetails();
updateguestdetailsobj.fn_updateguestdetails("login");
String s1 = updateguestdetailsobj.extractingmessageupdateguestdetails();
Assert.assertEquals(s1,"success");
}
@Test(priority = 131,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_35_editbookingpretax(){
	EditBookingPretax EditBookingPretaxobj=new EditBookingPretax();
	EditBookingPretaxobj.EditBookingPretaxcall("login");
	String s1=EditBookingPretaxobj.extractingmessageeditbookingpretax();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 132,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_36_editgrouppretax(){
	EditGroupPreTax EditGroupPreTaxobj=new EditGroupPreTax();
	EditGroupPreTaxobj.EditGroupPreTaxcall("login");
	String s1=EditGroupPreTaxobj.extractingmessageeditgrouppretax();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 133,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_25_GetPayments(){
GetPayments getpaymentsobj = new GetPayments();
getpaymentsobj.fn_getpayments("login");
String s1 = getpaymentsobj.extractingmessagegetpayments();
Assert.assertEquals(s1,"success");
}
//Validate update booking preference for single reservation.
@Test(priority = 134,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_26_EditBookingPreference(){
EditBookingPreference editbookingpreferenceobj = new EditBookingPreference();
editbookingpreferenceobj.fn_editbookingpreference("login");
String s1 = editbookingpreferenceobj.extractingmessageeditbookingpreference();
Assert.assertEquals(s1,"success");
}
@Test(priority = 135,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_30_CancelCommitGroup(){
CancelCommitGroup cancelcommitgroupsobj = new CancelCommitGroup();
cancelcommitgroupsobj.fn_cancelcommitgroup("login");
String s1 = cancelcommitgroupsobj.extractingmessagecancelcommitgroup();
Assert.assertEquals(s1,"success");
}

@Test(priority = 136,groups = {"frontdeskcreate"},invocationCount=8)
public void UC_FD_NEW_cancelandcommitbooking(){
	  CancelandCommitBooking CancelandCommitBookingobj=new CancelandCommitBooking();
	  CancelandCommitBookingobj.CancelandCommitBookingcall("login", "true");
    String s1=CancelandCommitBookingobj.extractingmessageCancelandCommitBooking();
    Assert.assertEquals(s1, "success");
}

@Test(priority = 135,groups = {"frontdeskcreate"})
public void UC_FD_NEW_TC_24_changerateamount(){
	ChangeRateAmount ChangeRateAmountobj=new ChangeRateAmount();
	ChangeRateAmountobj.ChangeRateAmountCall("login");
	String s1=ChangeRateAmountobj.extractingmessagechangerateamount();
	Assert.assertEquals(s1, "success");
}
@Test(priority = 136,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_14_generatePerformaInvoice(){
GeneratePerformaInvoice generateperformainovoiceobj = new GeneratePerformaInvoice();
generateperformainovoiceobj.GeneratePerformaInvoiceAPI("login", "S");
String actual=generateperformainovoiceobj.extractingGeneratePerformaInvoice();
Assert.assertEquals(actual, "success");

}
//To check the functionality of  generateproformainvoice API for the Group reservation
@Test(priority = 136,groups ={"frontdeskcreate"})
public void UC_FD_NEW_TC_SC159_generatePerformaInvoice(){
GeneratePerformaInvoice generateperformainovoiceobj = new GeneratePerformaInvoice();
generateperformainovoiceobj.GeneratePerformaInvoiceAPI("login", "G");
String actual=generateperformainovoiceobj.extractingGeneratePerformaInvoice();
Assert.assertEquals(actual, "success");

}

@Test(priority = 136,groups ={"frontdeskcreate"},invocationCount=15)
public void UC_FD_NEW_TC_CancelandCommitGroup(){
CancelCommitGroup cancelcommitgroupsobj = new CancelCommitGroup();
cancelcommitgroupsobj.fn_cancelcommitgrouptoexistingids("login");
String s1 = cancelcommitgroupsobj.extractingmessagecancelcommitgroup();
Assert.assertEquals(s1,"success");
}



@Test(priority = 138,groups = {"frontdeskcreate"},invocationCount=15)
public void UC_FD_59_TC_1_earlycheckout()
{
	Earlycheckout earlycheckoutobj = new Earlycheckout();
	earlycheckoutobj.Earlycheckoutcall("login", "S");
String s1=	earlycheckoutobj.extractingmessageearlycheckout();
System.out.println("Early check out messasge came as:"+s1);
Assert.assertEquals(s1,"success");

}

@Test(priority = 139,groups = {"frontdeskcreate"},invocationCount=20)
public void UC_FD_59_TC_earlycheckoutgroup()
{
	Earlycheckout earlycheckoutobj = new Earlycheckout();
	earlycheckoutobj.Earlycheckoutcall("login", "G");
}


@Test(priority = 140,groups = {"frontdeskcreate"})
public void UC_FD_SC214_TC_getcurrencylist()
{
	GetCurrencyList getCurrencyListObj = new GetCurrencyList();
	getCurrencyListObj.getcurrencylist("login");
	String actual=	getCurrencyListObj.extractingmessgaegetcurrencylist();
	Assert.assertEquals(actual, "USD");
	
}

@Test(priority = 141,groups = {"frontdeskcreate"})
public void UC_FD_SC215_TC_getallcurrencies()
{
	GetAllCurrencies getallObj = new GetAllCurrencies();
	getallObj.getAllCurrencies("login");
	String actual=	getallObj.extractingmessgaegetAllCurrencies();
	Assert.assertEquals(actual, "Success");
	
}


@Test(priority = 142,groups = {"frontdeskcreate"})
public void UC_FD_SC216_TC_gethoteldefaultcurrency()
{
	Gethoteldefaultcurrency gethoteldefaultcurrencyObj = new Gethoteldefaultcurrency();
	gethoteldefaultcurrencyObj.gethoteldefaultcurrency("login");
	int actual=	gethoteldefaultcurrencyObj.extractingmessgaegetHoteldefaultcurrency();
	Assert.assertEquals(actual, 200);
	
}


@Test(priority = 142,groups = {"frontdeskcreate"})
public void UC_FD_SC216_TC_getminimumbookablenight()
{
	Getminimumbookablenight getminimumbookablenightObj = new Getminimumbookablenight();
	getminimumbookablenightObj.getminimumbookablenight("login");
	int actual=	getminimumbookablenightObj.extractingmessgaegetminimumbookablenight();
	Assert.assertEquals(actual, 200);
	
}

@Test(priority = 143,groups = {"frontdeskcreate"})
public void UC_FD_SC211_TC_getspdiscount()
{
	Getspdiscount getspdiscountObj = new Getspdiscount();
	getspdiscountObj.getspdiscount("login");
	String actual=	getspdiscountObj.extractingmessgaegetspdiscount();
	Assert.assertEquals(actual, "Success");
	
}
@Test(priority = 144,groups = {"frontdeskcreate"})
public void UC_FD_SC212_TC_getallowednights()
{
	Getallowednights getallowednightsObj = new Getallowednights();
	getallowednightsObj.getallowednights("login");
	int actual=	getallowednightsObj.extractingmessgaegetallowednights();
	Assert.assertEquals(actual, 200);
	
}

@Test(priority = 145,groups = {"frontdeskcreate"})
public void UC_FD_SC212_TC_getmaxpax()
{
	Getmaxpax getmaxpaxObj = new Getmaxpax();
	getmaxpaxObj.getmaxpax("login");
	int actual=	getmaxpaxObj.extractingmessgaegetmaxpax();
	Assert.assertEquals(actual, 200);
	
}

@Test(priority = 146,groups = {"frontdeskcreate"})
public void UC_FD_SC212_TC_confirmtempbookings()
{
	ConfirmTempBookings confirmtempbookingsObj=new  ConfirmTempBookings();
	confirmtempbookingsObj.confirmtempbookings("login");
	String actual=	confirmtempbookingsObj.extractingmessgaeconfirmtempbookings();
	Assert.assertEquals(actual, "success");
	
}

@Test(priority = 147,groups = {"frontdeskcreate"})
public void UC_FD_SC212_TC_setgroupleader()
{
	Setgroupleader setgroupleaderObj=new Setgroupleader();
	setgroupleaderObj.setgroupleader("login");
	String actual=setgroupleaderObj.extractingmessgaesetgroupleader();
	Assert.assertEquals(actual, "success");
		
}

@Test(priority = 148,groups = {"frontdeskcreate"})
public void UC_FD_SC213_TC_unsetgroupleader()
{
	UnsetGroupLeader unsetgroupleaderObj=new UnsetGroupLeader();
	unsetgroupleaderObj.unsetgroupleader("login");
	String actual=unsetgroupleaderObj.extractingmessgaeunsetgroupleader();
	Assert.assertEquals(actual, "success");
		
}


@Test(priority = 149,groups = {"frontdeskcreate"})
public void UC_FD_SC213_TC_GetCustomChargetaxes()
{
	GetCustomChargetaxes getCustomChargetaxesObj=new  GetCustomChargetaxes();
	getCustomChargetaxesObj.GetCustomChargetaxescall("login");
	String actual=getCustomChargetaxesObj.extractingmessageGetCustomChargetaxes();
	Assert.assertEquals(actual, "success");
		
}

@Test(priority = 150,groups = {"frontdeskcreate"})
public void UC_FD_SC213_TC_startlogoutusers()
{
	Startlogoutusers startlogoutusersObj=new  Startlogoutusers();
	startlogoutusersObj.startlogoutusers("login");
	String actual=startlogoutusersObj.extractingmessgaestartlogoutusers();
	Assert.assertEquals(actual, "success");
		
}

@Test(priority = 151,groups = {"frontdeskcreate"})
public void UC_FD_SC213_TC_addpaymenttogroup()
{
	AddPaymentToGroup addPaymentToGroupObj=new  AddPaymentToGroup();
	addPaymentToGroupObj.addpaymenttogroup("login");
	String actual=addPaymentToGroupObj.extractingmessgaeaddpaymenttogroup();
	Assert.assertEquals(actual, "success");
		
}

@Test(priority = 152,groups = {"frontdeskcreate"})
public void UC_FD_SC213_TC_RouteToNewFolioNew()
{
	RouteToNewFolioNew routeToNewFolioNewObj=new  RouteToNewFolioNew();
	routeToNewFolioNewObj.routetonewfolio("login");
	String actual=routeToNewFolioNewObj.extractingmessageroutetonewfolio();
	Assert.assertEquals(actual, "success");
		
}

@Test(priority = 152,groups = {"frontdeskcreate"})
public void UC_FD_SC213_TC_getRateDetails()
{
	GetRateDetails getRateDetailsObj=new  GetRateDetails();
	getRateDetailsObj.getratedetails("login");
	int actual=getRateDetailsObj.extractingmessgaegetratedetails();
	Assert.assertEquals(actual, 200);
		
}

@Test(priority = 153,groups = {"frontdeskcreate"})
public void UC_FD_SC213_TC_abandonNightAudit()
{
	AbandonNightAudit abandonNightAuditObj=new  AbandonNightAudit();
	abandonNightAuditObj.abandonnightaudit("login");
	int actual=abandonNightAuditObj.extractingmessgaeabandonnightaudit();
	Assert.assertEquals(actual, 200);
		
}


@Test(priority = 154,groups = {"frontdeskcreate"})
public void UC_FD_SC213_TC_generateCreditDebitProformaInvoice()
{
	GenerateCreditDebitProformaInvoice GenerateCreditDebitProformaInvoiceObj=new  GenerateCreditDebitProformaInvoice();
	GenerateCreditDebitProformaInvoiceObj.generateCreditDebitProformaInvoice("login", "S");
	String actual=GenerateCreditDebitProformaInvoiceObj.extractMessagegenerateCreditDebitProformaInvoice();
	//int actual=GenerateCreditDebitProformaInvoiceObj.extractingmessgaeabandonnightaudit();
	Assert.assertEquals(actual, "success");
		
}


@Test(priority = 155,groups = {"frontdeskcreate"})
public void UC_FD_SC213_TC_saveguestsignature()
{
	SaveGuestSignature saveGuestSignatureObj=new  SaveGuestSignature();
	saveGuestSignatureObj.saveguestsignature("login");
	String actual=saveGuestSignatureObj.extractingmessageSaveguestsignature();
	Assert.assertEquals(actual, "success");
		
}

@Test(priority = 156,groups = {"frontdeskcreate"})
public void UC_FD_SC213_TC_gatewayrefund() throws Throwable
{
	GatewayRefund gatewayRefundObj=new  GatewayRefund();
	gatewayRefundObj.gatewayRefund("login", "S");
	String actual=gatewayRefundObj.extractMessageGatewayRefund();
	Assert.assertEquals(actual, "success");
		
}

@Test(priority = 157,groups = {"frontdeskcreate"})
public void UC_FD_SC213_TC_captureorrefundtransaction() throws Throwable
{
	CaptureOrRefundTransaction captureOrRefundTransactionObj=new CaptureOrRefundTransaction();
	//gatewayRefundObj.gatewayRefund("login", "S");
	captureOrRefundTransactionObj.captureOrRefundTransaction("login", "S");
	String actual=captureOrRefundTransactionObj.extractMessagecaptureorrefundtransaction();
	Assert.assertEquals(actual, "success");
		
}


@Test(priority = 158,groups = {"frontdeskcreate"})
public void UC_FD_SC216_TC_getremaininghoursfornightaudit()
{
	Getremaininghoursfornightaudit getremaininghoursfornightauditObj = new Getremaininghoursfornightaudit();
	getremaininghoursfornightauditObj.getremaininghoursfornightaudit("login");
	int actual=	getremaininghoursfornightauditObj.extractingmessgaegetremaininghoursfornightaudit();
	Assert.assertEquals(actual, 200);
	
}


@Test(priority = 159,groups = {"frontdeskcreate"})
public void UC_FD_SC216_TC_getcolorcodes()
{
	Getcolorcodes getcolorcodesObj = new Getcolorcodes();
	getcolorcodesObj.getcolorcodes("login");
	int actual=	getcolorcodesObj.extractingmessagegetcolorcodes();
	Assert.assertEquals(actual, 200);
	
}

@Test(priority = 159,groups = {"frontdeskcreate"})
public void UC_FD_SC216_TC_getchilds()
{
	Getchilds getchildsObj = new Getchilds();
	getchildsObj.getchilds("login");
	int actual=	getchildsObj.extractingmessageGetchilds();
	Assert.assertEquals(actual, 200);
	
}

@Test(priority = 161,groups = {"frontdeskcreate"})
public void UC_FD_SC217_TC_GetAppliedDiscountedPrice() throws JDOMException, IOException
{
	GetAppliedDiscountedPrice getGetAppliedDiscountedPrice = new GetAppliedDiscountedPrice();
	getGetAppliedDiscountedPrice.getAppliedDiscountedPriceCall("login");
	int actual=	getGetAppliedDiscountedPrice.extractingmessageGetAppliedDiscountedPrice();
	Assert.assertEquals(actual, 200);
	
}







//@Test(priority = 165,groups = {"frontdeskcreate"})
public void UC_FD_SC213_TC_performnightaudit() throws Throwable
{
	PerformNightAudit performNightAuditObj=new PerformNightAudit();
	//gatewayRefundObj.gatewayRefund("login", "S");
	performNightAuditObj.performnightaudit("login");
	String actual=performNightAuditObj.extractMessagePerformnightaudit();
	Assert.assertEquals(actual, "Night Audit start sucessfully");
		
}


  ///////////////////////////////-*- END OF 'FRONTDESK' SECTION-*- ///////////////////////////////////
  
///////////////////////////////-*- Newly added FD-API's in 'FRONTDESK' SECTION-*- ///////////////////////////////////
  
  
  
  
  
  ////////////////////////////////-*- START OF 'ADMIN' SECTION-*-////////////////////////////////////

 @Test(groups = {"adminview"})
  public void UC_AD_1_TC_1_wsauth()
  {
Wsauth wsauthobj = new Wsauth();
wsauthobj.Wsauthcall();
String s1 = wsauthobj.extractingmessage();
Assert.assertEquals(s1, "success");
  }
 
  @Test(groups ={"adminview"})
  public void UC_AD_2_TC_1_login()
  {
Login loginobj = new Login();
loginobj.Logincall();
String s1 = loginobj.extractingmessagelogin();
Assert.assertEquals(s1, "success");
loginobj.extractingLoginKey();
  }
 
  @Test(groups = {"exception"})
  public void UC_AD_3_TC_1_logout()
  {
Logout logoutobj = new Logout();
logoutobj.Logoutcall();
String s1 = logoutobj.extractingmessageLogout();
Assert.assertEquals(s1, "success");
  }

  @Test(groups = {"adminview"})
  public void UC_AD_4_TC_1_getusertypes()
  {
Getusertypes getusertypesobj = new Getusertypes();
getusertypesobj.Getusertypescall("login");
String s2 = getusertypesobj.extractingusertypetitle();
String s1 = getusertypesobj.extractingmessageGetusertypes();
Assert.assertEquals(s1, "success");
//System.out.println(s2);
  }
 
 
 @Test(groups = {"adminview"})
 public void UC_AD_5_TC_1_getallpaytypes()
  {
 Getallpaytypes getallpaytypesobj = new Getallpaytypes();
 getallpaytypesobj.Getallpaytypescall("login");
 String s1 = getallpaytypesobj.extractingmessageGetallpaytypes();
 Assert.assertEquals(s1, "success");
  }
 
  @Test(groups = {"adminview"})
  public void UC_AD_6_TC_1_getcounters()
  {
 Getcounters getcountersobj = new Getcounters();
 getcountersobj.Getcounterscall();
 String s1 = getcountersobj.extractingmessagegetcounters();
 Assert.assertEquals(s1, "success");
  }
 
 
  @Test(groups = {"adminview"})
  public void UC_AD_7_TC_1_getusers()
  {
 Getusers getusersobj = new Getusers();
 getusersobj.Getuserscall("wsauth");
 String s1 = getusersobj.extractingmessagegetusers();
 Assert.assertEquals(s1, "success");
  }
 
  @Test(groups = {"adminview"})
  public void UC_AD_8_TC_1_getagents()
  {
 Getagents getagentsobj = new Getagents();
 getagentsobj.Getagentscall("wsauth");
 String s1 = getagentsobj.extractingmessagegetagents();
 Assert.assertEquals(s1, "success");
  }
 
  @Test(groups = {"adminview"})
  public void UC_AD_9_TC_1_getcorporates()
  {
 Getcorporates getcorporatesobj = new Getcorporates();
 getcorporatesobj.Getcorporatescall("wsauth");
 String s1 = getcorporatesobj.extractingmessagegetcorporates();
 Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"adminview"})
  public void UC_AD_10_TC_1_GetAmenity()
  {
	  GetAmenity getamenityObj = new GetAmenity();
	  getamenityObj.getAmenitycall("login");
	  String s1 = getamenityObj.extractingmessageGetamenity();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"adminview"})
  public void UC_AD_11_TC_1_GetRoomTax()
  {
	  GetRoomTax getRoomTaxObj = new GetRoomTax();
	  getRoomTaxObj.getRoomTaxcall("login");
	  String s1 = getRoomTaxObj.extractingmessageGetRoomTax();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"adminview"})
  public void UC_AD_12_TC_1_GetRoomType()
  {
	  GetRoomType getRoomTypeObj = new GetRoomType();
	  getRoomTypeObj.getRoomTypecall("login");
	  String s1 = getRoomTypeObj.extractingmessageGetRoomType();
	  Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"adminview"})
  public void UC_AD_13_TC_1_GetGuestTitle()
  {
	  GetGuestTitle getGuestTitleObj = new GetGuestTitle();
	  getGuestTitleObj.getGuestTitleCall("login");
	  int s1 = getGuestTitleObj.extractingmessageGetGuestTitle();
	  Assert.assertEquals(s1, 200);
  }
  
  
  @Test(groups = {"adminview"})
  public void UC_AD_134_TC_1_GetPaymentMode()
  {
	  GetPaymentMode getPaymentModeObj = new GetPaymentMode();
	  getPaymentModeObj.getPaymentModeCall("login");
	  int s1 = getPaymentModeObj.extractingmessageGetPaymentMode();
	  Assert.assertEquals(s1, 200);
  }
  
  
 
  @Test(groups = {"admincreate"})
  public void UC_AD_10_TC_1_createuser()
  {
 Createuser createuserobj = new Createuser();
 createuserobj.Createusercall("wsauth");
String s1 = createuserobj.extractingmessagecreateuser();
Assert.assertEquals(s1, "success");
  }
 
  @Test(groups= {"admincreate"})
  public void UC_AD_11_TC_1_edituser()
  {
 Edituser edituserobj = new Edituser();
 edituserobj.Editusercall("wsauth");
 String s1 = edituserobj.extractingmessageedituser();
 Assert.assertEquals(s1, "success");  
  }
 
  @Test(groups = {"admincreate"})
  public void UC_AD_12_TC_1_createagent()
  {
 Createagent createagentobj = new Createagent();
 createagentobj.Createagentcall("wsauth");
 String s1 = createagentobj.extractingmessagecreateagent();
 Assert.assertEquals(s1, "success");
  }


  @Test(groups = {"admincreate"})
  public void UC_AD_13_TC_1_createcorporate()
  {
 Createcorporate createcorporateobj = new Createcorporate();
 createcorporateobj.Createcorporatecall("wsauth");
 String s1 = createcorporateobj.extractingmessagecreatecorporate();
 Assert.assertEquals(s1, "success");
  }
 
  @Test(groups = {"admincreate"})
  public void UC_AD_14_TC_1_editcorporate()
  {
 Editcorporate editcorporateobj = new Editcorporate();
 editcorporateobj.Editcorporatecall("wsauth");
 String s1 = editcorporateobj.extractingmessageeditcorporate();
 Assert.assertEquals(s1, "success");
  }
 
  @Test(groups = {"admincreate"})
  public void UC_AD_15_TC_2_saveAnemailconfiguration()
  {
Saveemailconfiguration saveemailconfigurationobj = new Saveemailconfiguration();
saveemailconfigurationobj.Saveemailconfigurationcall("wsauth");
String s1 = saveemailconfigurationobj.extractingmessagesaveemailconfiguration();
Assert.assertEquals(s1, "success");
  }
 
  @Test(groups = {"admincreate"})
  public void UC_AD_16_TC_1_saveBlankemailconfiguration()
  {
Saveemailconfiguration saveemailconfigurationobj = new Saveemailconfiguration();
saveemailconfigurationobj.SaveBlankemailconfigurationcall("wsauth");
String s1 = saveemailconfigurationobj.extractingmessagesaveemailconfiguration();
Assert.assertEquals(s1, "success");
  }
  
  @Test(groups = {"admincreate"})
  public void UC_AD_16_TC_1_editpackageprice()
  {
 Editpackageprice editpackagepriceobj = new Editpackageprice();
 editpackagepriceobj.Editpackagepricecall("wsauth");
 String s1 = editpackagepriceobj.extractingmessageeditpricepackage();
 Assert.assertEquals(s1, "queued successfully");
  }

 @Test(groups = {"admincreate"})
  public void UC_AD_17_TC_1_opencloseoverbooking()
  {
 Opencloseoverbooking opencloseoverbookingobj = new Opencloseoverbooking();
 opencloseoverbookingobj.Opencloseoverbookingcall("wsauth");
 String s1 = opencloseoverbookingobj.extractingmessageopencloseoverbooking();
 Assert.assertEquals(s1, "success");
  }
 
 
 
 @Test(groups = {"admincreate"})
 public void UC_AD_18_TC_1_Editagent()
 {
 EditAgent editagentobj = new EditAgent();
 editagentobj.editagentcall("wsauth");
 String s1 = editagentobj.extractingmessageeditagent();
 Assert.assertEquals(s1, "success");
 }
 
 @Test(groups = {"admincreate"})
 public void UC_AD_19_TC_1_Getdailyrates()
 {
 GetDailyRates getdailyratesobj = new GetDailyRates();
 getdailyratesobj.getdailyratescall("login");
 String s1 = getdailyratesobj.extractingmsggetdailyrates();
 Assert.assertEquals(s1, "success");
 }

  @Test(groups = {"admincreate"})
 public void UC_AD_20_TC_1_GetStandardrates()
 {
 GetStandardRates getstandardratesobj = new GetStandardRates();
 getstandardratesobj.getstandardratescall("wsauth");
 String s1 = getstandardratesobj.extractingmsggetstandardrates();
 Assert.assertEquals(s1, "success");
 }
 
 // @Test(groups = {"admincreate"})
  public void UC_AD_21_TC_1_GetWeeklyRate()
  {
 GetWeeklyRates getweeklyratesobj = new GetWeeklyRates();
 getweeklyratesobj.getweeklyratescall("login");
 getweeklyratesobj.extractingrateID();
 getweeklyratesobj.extractingvalidityID();
 getweeklyratesobj.extractingroomtypecode();
   String s1 = getweeklyratesobj.extractingmsggetWeeklyrates();
   Assert.assertEquals(s1, "success");
  }
 
  @Test(groups = {"admincreate"})
  public void UC_AD_22_TC_1_SaveDailyRates()
  {
 SaveDailyRates savedailyratesobj = new SaveDailyRates();
 savedailyratesobj.savedailyratescall("wsauth");
   String s1 = savedailyratesobj.extractingmsgsavedailyrates();
   Assert.assertEquals(s1, "success");
  }
 
 @Test(groups = {"admincreate"})
 public void UC_AD_23_TC_1_SaveStandardRates()
 {
SaveStandardRates savestandardrateobj = new SaveStandardRates();
savestandardrateobj.saveStandardRates("wsauth");
String s1= savestandardrateobj.extractingmsgsavestandardrates();
Assert.assertEquals(s1, "success");

 }
 
 //@Test(groups = {"admincreate"})
 public void UC_AD_24_TC_1_SaveWeeklyRate()
 {
SaveWeeklyRates saveweeklyrateobj = new SaveWeeklyRates();
saveweeklyrateobj.saveWeeklyRate("wsauth");
String s1= saveweeklyrateobj.extractingmsgsavestandardrates();
Assert.assertEquals(s1, "success");
 }
 
 @Test(groups = {"admincreate"})
 public void UC_AD_25_TC_1_CreateAmenity()
 {
	 CreateAmenity createAmenityobj = new CreateAmenity();
	 createAmenityobj.createAmenitycall("login");
	 String s1= createAmenityobj.extractingmessageCreateAmenity();
	 Assert.assertEquals(s1, "success");
 }
 
 
 @Test(groups = {"admincreate"})
 public void UC_AD_26_TC_1_UpdateAmenity()
 {
	 UpdateAmenity updateAmenityobj = new UpdateAmenity();
	 updateAmenityobj.updateAmenitycall("login");
	 String s1= updateAmenityobj.extractingmessageUpdateAmenity();
	 Assert.assertEquals(s1, "success");
 }
 
 @Test(groups = {"admincreate"})
 public void UC_AD_27_TC_1_CreateRoom()
 {
	 CreateRoom createRoomobj = new CreateRoom();
	 createRoomobj.createRoomcall("login");
	 String s1= createRoomobj.extractingmessageCreateRoom();
	 Assert.assertEquals(s1, "success");
 }
 
 @Test(groups = {"admincreate"})
 public void UC_AD_28_TC_1_CreateRoomTax()
 {
	 CreateRoomTax createRoomTaxobj = new CreateRoomTax();
	 createRoomTaxobj.createRoomTaxcall("login");
	 String s1= createRoomTaxobj.extractingmessageCreateRoomTax();
	 Assert.assertEquals(s1, "success");
 }
 
 @Test(groups = {"admincreate"})
 public void UC_AD_29_TC_1_CreateRoomType()
 {
	 CreateRoomType createRoomTypeobj = new CreateRoomType();
	 createRoomTypeobj.CreateRoomTypecall("login");
	 String s1= createRoomTypeobj.extractingmessageCreateRoomType();
	 Assert.assertEquals(s1, "success");
 }
 
 
 @Test(groups = {"admincreate"})
 public void UC_AD_30_TC_1_UpdateRoomType()
 {
	 UpdateRoomType updateRoomTypeobj = new UpdateRoomType();
	 updateRoomTypeobj.updateRoomTypecall("login");
	 String s1= updateRoomTypeobj.extractingmessageUpdateRoomType();
	 Assert.assertEquals(s1, "success");
 }
 
 @Test(groups = {"admincreate"})
 public void UC_AD_31_TC_1_UpdateHotelInfo()
 {
	 UpdateHotelInfo updateHotelInfoobj = new UpdateHotelInfo();
	 updateHotelInfoobj.updateHotelInfocall("login");
	 String s1= updateHotelInfoobj.extractingmessageUpdateHotelInfo();
	 Assert.assertEquals(s1, "success");
 }
 
 @Test(groups = {"admincreate"})
 public void UC_AD_32_TC_1_UpdateDefaultSetting()
 {
	 UpdateDefaultSetting updateDefaultSettingObj = new UpdateDefaultSetting();
	 updateDefaultSettingObj.updateDefaultSettingcall("login");
	 String s1= updateDefaultSettingObj.extractingmessageUpdateDefaultSetting();
	 Assert.assertEquals(s1, "success");
 }
 
 @Test(groups = {"admincreate"})
 public void UC_AD_33_TC_1_UpdateCurrencies()
 {
	 UpdateCurrencies updateCurrenciesObj = new UpdateCurrencies();
	 updateCurrenciesObj.updateCurrenciescall("login");
	 String s1= updateCurrenciesObj.extractingmessageUpdateCurrencies();
	 Assert.assertEquals(s1, "success");
 }
 
 
 
 
 
 
////////////////////////////////-*- END OF 'ADMIN' SECTION-*-////////////////////////////////////
  
 
 
 
 
 
 
/////////////////////////////////////-* START OF 'CRS' SECTION -*-///////////////////////////////////
 
@Test(groups = {"web"})
 public void UC_WB_1_TC_1_search()
 {
	 Search searchobj = new Search();
	 searchobj.Searchcall("wsauth");
	 String s1 = searchobj.extractingmessagesearch();
	 //String rateid = searchobj.extractingrateid();
	 //System.out.println("hola::"+rateid);
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_2_TC_1_addtocart()
 {
	 Addtocart addtocartobj = new Addtocart();
	 addtocartobj.Addtocartcall("wsauth");
	 String s1 = addtocartobj.extractingmessageaddtocart();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_3_TC_1_loadcart()
 {
	 Loadcart loadcartobj = new Loadcart();
	 loadcartobj.Loadcartcall("wsauth");
	 String s1 = loadcartobj.extractingmessageloadcart();
	 String bookingid = loadcartobj.extracingbookingid();
	 
	 System.out.println(bookingid);
	 loadcartobj.extractinghotelid();
	 loadcartobj.extractinggueststayid();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 
 @Test(groups = {"web"})
 public void UC_WB_4_TC_1_deletefromcart()
 {
	 Deletefromcart deletefromcartobj = new Deletefromcart();
	 deletefromcartobj.Deletefromcartcall("wsauth");
	 String s1 = deletefromcartobj.extractingmessagedeletefromcart();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_6_TC_1_getaddons()
 {
	 Getaddons getaddonsobj = new Getaddons();
	 getaddonsobj.Getaddonscall("wsauth");
	 String s1 = getaddonsobj.exrtractingmessagegetaddons();
	 String s2 =getaddonsobj.extractingitemidfromrequest();
	 System.out.println(s2);
	 String s3 = getaddonsobj.extractingaddonsid();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 
 @Test(groups = {"web"})
 public void UC_WB_7_TC_1_attachaddons()
 {
	Attachaddons attachaddonsobj = new Attachaddons();
	attachaddonsobj.Attachaddonscall("wsauth");
	String s1 = attachaddonsobj.extractingmessageattachaddons();
	Assert.assertEquals(s1, "Successfull!");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_8_TC_1_savebooking()
 {
	 Savebooking savebookingobj = new Savebooking();
	 savebookingobj.Savebookingcall("wsauth");
	 String s1 = savebookingobj.extractingmessagesavebooking();
	 String s2 = savebookingobj.extractingorderid();
	 System.out.println(s2);
	 String s3 = savebookingobj.extractingdeposittotal();
	 System.out.println(s3);
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_9_TC_1_confirmbooking()
 {
	 Confirmbooking confirmbookingobj = new Confirmbooking();
	 confirmbookingobj.Confirmbookingcall("wsauth");
	 String s1 = confirmbookingobj.extractingmessageconfirmbooking();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_10_TC_1_gethoteloptions()
 {
	 Gethoteloptions gethotelooptionsobj = new Gethoteloptions();
	 gethotelooptionsobj.Gethoteloptionscall("wsauth");
	 String s1 = gethotelooptionsobj.extractingmessagegethoteloptions();
	 Assert.assertEquals(s1, "Success");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_11_TC_1_getpaytype()
 {
	 Getpaytype getpaytypeobj = new Getpaytype();
	 getpaytypeobj.Getpaytypecall("wsauth");
	 String s1 = getpaytypeobj.extractingmessagegetpaytype();
	 Assert.assertEquals(s1, "Success");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_12_TC_1_getcountrylist()
 {
	 Getcountrylist getcountrylistobj = new Getcountrylist();
	 getcountrylistobj.Getcountrylistcall("wsauth");
	 String s1 = getcountrylistobj.extractingmessgaegetcountrylist();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_13_TC_1_getzonelist()
 {
	 Getzonelist getzonelistobj = new Getzonelist();
	 getzonelistobj.Getzonelistcall("wsauth");
	 String s1 = getzonelistobj.extractingmessagegetzonelist();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_14_TC_1_bestsuggestedrate()
 {
	 Bestsuggestedrate bestsuggestedrateobj = new Bestsuggestedrate();
	 bestsuggestedrateobj.Bestsuggestedratecall("wsauth");
	 String s1 = bestsuggestedrateobj.extractingmessagebestsuggetedrate();
	 Assert.assertEquals(s1, "Success");
 }
 
 
 @Test(groups = {"web"})
 public void UC_WB_15_TC_1_getpos()
 {
	 Getpos getposobj = new Getpos();
	 getposobj.Getposcall("wsauth");
	 String s1 = getposobj.extractingmessagegetpos();
	 getposobj.extractingposid();
	 Assert.assertEquals(s1, "success");
 }
 
 @Test(groups = {"web"})
 public void UC_WB_16_TC_1_getposproduct()
 {
	 Getposproduct getposproductobj = new Getposproduct();
	 getposproductobj.Getposproductcall("wsauth");
	 String s1 = getposproductobj.extractingmessagegetposproduct();
	 Assert.assertEquals(s1, "success");
 }
 
 
 @Test(groups ={"web"})
 public void UC_WB_17_TC_1_bookingrestrictions()
 {
	 Bookingrestrictions bookingrestrictionsobj = new Bookingrestrictions();
	 bookingrestrictionsobj.Bookingrestrictionscall("wsauth");
	 String s1 = bookingrestrictionsobj.extractingmessagebookingrestrictions();
	 Assert.assertEquals(s1, "Success");
 }
 
 
 @Test(groups = {"web"})
 public void UC_WB_18_TC_1_occupancywiserate()
 {
	 Occupancywiserate occupancywiserateobj = new Occupancywiserate();
	 occupancywiserateobj.Occupancywiseratecall("wsauth");
	 String s1 = occupancywiserateobj.extractingmessageoccupancywiserate();
	 Assert.assertEquals(s1, "Success");
 }
 
 
 @Test(groups ={"web"})
 public void UC_WB_19_TC_1_clearcart()
 {
	 Clearcart clearcartobj = new Clearcart();
	 clearcartobj.Clearcartcall("wsauth");
	 String s1 = clearcartobj.extractingmessageclearcart();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 @Test(groups={"web"})
 public void UC_WB_20_TC_1_modifyamount(){
	 Modifyamount modifyamountobj=new Modifyamount();
	 modifyamountobj.Modifyamountcall("wsauth");
	 String s1=modifyamountobj.extractingmessageModifyamount();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups={"web"})
 public void UC_WB_21_TC_1_updatewebbookig() throws Exception{
	 
	 Updatewebbooking updatewebbookigobj=new Updatewebbooking();
	 updatewebbookigobj.Updatewebbookingcall("wsauth");
	 String s1=updatewebbookigobj.extractingmessageUpdatewebbooking();
	 Assert.assertEquals(s1, "SUCCESS");
	 
 }

 @Test(groups={"web"})
 public void UC_WB_22_TC_1_getorder() throws Exception{
	 Getorder getorderobj=new Getorder();
	 getorderobj.Getordercall("wsauth");
	 String s1=getorderobj.extractingmessageGetorder();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 @Test(groups={"web"})
 public void UC_WB_23_TC_1_getcancellationcharge() throws Exception{
	 Getcancellationcharge getcancellationchargeobj=new Getcancellationcharge(); 
	 getcancellationchargeobj.Getcancellationchargecall("wsauth");
	 String s1= getcancellationchargeobj.extractingmessageGetcancellationcharge();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 @Test(groups={"web"})
 public void UC_WB_24_TC_1_cancel() throws Exception{
	 Cancel cancelobj=new Cancel();
	 cancelobj.Cancelcall("wsauth");
	String s1= cancelobj.extractingmessageCancel();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups={"web"})
 public void UC_WB_25_TC_1_rateidsforadtocart() throws Exception{
	 Rateidsforadtocart rateidsforadtocartobj=new Rateidsforadtocart();
	 rateidsforadtocartobj.Rateidsforadtocartcall("wsauth");
	 String s1=rateidsforadtocartobj.extractingmessageRateidsforadtocart();
	 Assert.assertEquals(s1, "Success");
 }
 @Test(groups={"web"})
 public void UC_WB_26_TC_1_roomtyperates() throws Exception{
	 Roomtyperates roomtyperatesobj=new Roomtyperates();
	 roomtyperatesobj.Roomtyperatescall("wsauth");
	 String s1=roomtyperatesobj.extractingmessageRoomtyperates();
	 Assert.assertEquals(s1, "SUCCESS");
 }
 
 @Test(groups={"web"})
 public void UC_WB_27_TC_1_basicratesearch() throws Exception{
	 BasicRateSearch basicratesearchobj=new BasicRateSearch();
	 basicratesearchobj.BasicRateSearchcall("wsauth");
	 
	 
 }
 
 @Test(groups={"web"})
 public void UC_WB_28_TC_1_addPaycard() throws Exception{
	 Addpaycard addpaycardObj=new Addpaycard();
	 addpaycardObj.addpaycardcall("login");
	 String actual=addpaycardObj.extractingmessageAddpaycard();
	 Assert.assertEquals(actual, "success");
	 
 }
 
 @Test(groups={"web"})
 public void UC_WB_29_TC_1_ChangeRateforCart() throws Exception{
	 ChangeRateforCart changeRateforCartObj=new ChangeRateforCart();
	 changeRateforCartObj.changeRateforCartCall("wsauth"); 
	 String actual=changeRateforCartObj.extractingMessageChangeRateforCart();
	 Assert.assertEquals(actual, "success");
	 
 }
 
 @Test(groups={"web"})
 public void UC_WB_30_TC_1_GetConsoleBookings() throws Exception{
	 GetConsoleBookings getConsoleBookingsObj=new GetConsoleBookings();
	 getConsoleBookingsObj.getConsoleBookingscall("login"); 
	 String actual=getConsoleBookingsObj.extractingmessageGetConsoleBookings();
	 Assert.assertEquals(actual, "success");
	 
 }

 @Test(groups={"web"})
 public void UC_WB_31_TC_1_ApplyPromocodeToCart() throws Exception{
	 ApplyPromocodeToCart applyPromocodeToCartObj=new ApplyPromocodeToCart();
	 applyPromocodeToCartObj.applyPromocodeToCartCall("login"); 
	 String actual=applyPromocodeToCartObj.extractingMessageApplyPromocodeToCart();
	 Assert.assertEquals(actual, "success");
	 
 }
 
 @Test(groups={"web"})
 public void UC_WB_32_TC_1_RemovePromoCodeFromCart() throws Exception{
	 RemovePromoCodeFromCart removePromoCodeFromCartObj=new RemovePromoCodeFromCart();
	 removePromoCodeFromCartObj.removePromoCodeFromCartCall("login"); 
	 String actual=removePromoCodeFromCartObj.extractingMessageRemovePromoCodeFromCart();
	 Assert.assertEquals(actual, "success");
	 
 }
 
 @Test(groups={"web"})
 public void UC_WB_33_TC_1_ConfirmDepositBooking()
 {
 	ConfirmDepositBooking confirmDepositBookingObj = new ConfirmDepositBooking();
 	confirmDepositBookingObj.confirmDepositBookingCall("login");
 	String actual=	confirmDepositBookingObj.extractingMessageConfirmDepositBooking();
 	Assert.assertEquals(actual, "Reservations and Payments confirmed by Credit card.");
 	
 }
 
 
 //@Test(groups={"web"})
 public void UC_WB_28_TC_1_getreservations() throws Exception{
 }
 }

 
/////////////////////////////////////-* END OF 'CRS' SECTION -*-///////////////////////////////////
  
  
  

