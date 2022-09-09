package com.OrangeHRM.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.IReporter;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName="Test-Report-" +timeStamp+ ".html";
		
		sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir")+ "/test-output/"+repName);	
		
		try {
			sparkReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Hostname", "Localhost");
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Testname", "Gracy");
		extent.setSystemInfo("Browsername", "Chrome");
		
		sparkReporter.config().setDocumentTitle("OrangeHRM App testing");
		sparkReporter.config().setReportName("Functional test Report");
		sparkReporter.config().setTheme(Theme.DARK);
		

	}

	public void onTestSuccess(ITestResult tr)
	{
	test=extent.createTest("Reporting");
	test.log(Status.PASS, "Test Case Passes is " +tr.getName());
	}
	
	public void onTestFailure(ITestResult tr)
	{
	test=extent.createTest("Reporting");
	test.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
	test.log(Status.FAIL, "Test Case failed" +tr.getThrowable());
	
	String screenshotPath= System.getProperty("user.dir")+ "\\Screenshots\\"+tr.getName()+".png";
	File f= new File(screenshotPath);
	if(f.exists())
	{
		test.fail("Screenshot is below:" +test.addScreenCaptureFromPath(screenshotPath));
	}
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		test=extent.createTest(tr.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
		
	}
		
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		
	}
}
