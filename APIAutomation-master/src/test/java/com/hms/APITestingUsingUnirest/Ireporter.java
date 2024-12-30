package com.hms.APITestingUsingUnirest;

import java.io.File;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.IRetryAnalyzer;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Test;

public class Ireporter implements IReporter{
	
	private static ExtentReports extent;
	public static ExtentTest test;
	
	
	public static void getStartTime()
	{
		test.setStartedTime(new Date());
	}
	
	public ExtentReports getExtentReport()
	{
		String s1=LocalDate.now().toString();
		System.out.println(s1);
		
		 return extent = new ExtentReports("Report\\" + File.separator + "API "+s1+".html");
		
	}
	
	
	 public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
			//   extent = new ExtentReports("Report\\" + File.separator + "ExtentReportTestNG.html", true);
			    	try{
			    	extent =getExtentReport();
			        for (ISuite suite : suites) {
			            Map<String, ISuiteResult> result = suite.getResults();
			            for (ISuiteResult r : result.values()) {
			                ITestContext context = r.getTestContext();
			                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
			                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
			                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			            }
			        }
			        extent.flush();
			        extent.close();
			    	}catch(Exception e){
			    		try {
							throw e;
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			    	    }
			           }
	 
	 
 public void buildTestNodes(IResultMap tests, LogStatus status) throws Exception {
      if (tests.size() > 0) {
          for (ITestResult result : tests.getAllResults()) {
             test = extent.startTest(result.getInstanceName().split("\\.")[1]+" :: "+result.getMethod().getMethodName());
             test.getStartedTime();
             test.getEndedTime();
             test.setStartedTime(getTime(result.getStartMillis()));
             test.setEndedTime(getTime(result.getEndMillis()));
              for (String group : result.getMethod().getGroups())
                  test.assignCategory(group);
              if (result.getThrowable() != null) {
             	 test.log(status,result.getMethod().getMethodName()+":failed" ,result.getThrowable());
              }else{
                  test.log(status, result.getMethod().getDescription());
              } 
              extent.endTest(test);
         }
         }
         }
 
 
 private Date getTime(long millis) {
     Calendar calendar = Calendar.getInstance();
     calendar.setTimeInMillis(millis);
     return calendar.getTime();        
 }
 
/*  private int retryCount = 0;
	private int maxRetryCount = 1;
	public boolean retry(ITestResult result) {
		  if (retryCount < maxRetryCount) {
	            retryCount++;
	            return true;
	        }return false;
	}*/
//	ATUTestRecorder recorder;
	
	
	public void onTestStart(ITestResult result) {
//		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
//		  Date date = new Date();
//		try {
//			recorder = new ATUTestRecorder("D:\\livestable_Smoke\\livestable_Smoak\\livestable_Smoak\\FailedVideos",result.getMethod().getMethodName()+dateFormat.format(date),false);
//			recorder.start();
//		} catch (ATUTestRecorderException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("Start execution of Test "+ result.getMethod().getMethodName());
	}
	
	public void onTestSuccess(ITestResult result) {
//		 try {
//			recorder.stop();
//		} catch (ATUTestRecorderException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String newLine = System.getProperty("line.separator");
		System.out.println(result.getMethod().getMethodName()+" is Passed."+newLine+result.getMethod().getDescription());
	}
	
	public void onTestFailure(ITestResult result) {
//		 try {
//			recorder.stop();
//		} catch (ATUTestRecorderException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 System.err.println(result.getMethod().getMethodName()+" is Failed."+" Reason "+result.getThrowable().getMessage());
	}
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}
	
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}
	
	public void onFinish(ITestContext context){
		// TODO Auto-generated method stub
	}
	

}
