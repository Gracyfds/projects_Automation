package com.OrangeHRM.Testcases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.OrangeHRM.pageObjects.LoginPage;
import com.OrangeHRM.utilities.XLUtils;

import io.opentelemetry.exporter.logging.SystemOutLogExporter;

public class TC_loginDDT_002 extends Basetest{

	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException
	{
		LoginPage lp= new LoginPage(driver);
		lp.setUserName(user);
		logger.info("UserName entered");
		lp.setPassword(pwd);
		logger.info("Passsword entered");

		lp.ClickLogin();
		logger.info("Submit Button Clicked");
		lp.UserLogout();
		
				}
		
//		if(isTitlePresent()==true) // To check alert is present or not after login
//		{
//			driver.switchTo().alert().accept();
//			driver.switchTo().defaultContent();
//			Assert.assertTrue(false);
//			logger.warn("Login Failed");
//		}
//		else
//		{
//			Assert.assertTrue(true);
//			logger.info("Login Passed");
//			lp.UserLogout();
//			Thread.sleep(3000);
//
//			
//		}
		
		
//	public boolean isTitlePresent() //user defined method to check alert is present or not
//	{
//		try
//		{
//		driver.switchTo().alert();
//			return true;
//		}
//		catch(NoAlertPresentException e)
//		{
//			System.out.println("No Alter displayed");
//			return false;
//		}
//	}
	
	@DataProvider(name="LoginData")
	String[][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+ "/src/test/java/com/OrangeHRMTestData\\LoginData.xlsx";
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path, "Sheet1", 1);
		
	String logindata[][]=new String[rownum][colcount];
	
	for(int i=1; i<=rownum; i++)
	{
		for(int j=0; j<colcount;j++)
		{
			logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
		}	
	}
			
	return logindata;
	
	}
}
