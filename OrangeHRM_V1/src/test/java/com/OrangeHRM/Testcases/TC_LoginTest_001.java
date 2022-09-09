package com.OrangeHRM.Testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.OrangeHRM.pageObjects.LoginPage;

public class TC_LoginTest_001 extends Basetest {
	
	LoginPage lp;
	
	@Test
	public void UserLogin() throws IOException
	{	
		logger.info("Navigated to URL Successfully");
		
		lp=new LoginPage(driver);
		lp.setUserName(uname);
		logger.info("Username entered successfully");
		
		lp.setPassword(pass);
		logger.info("Paswword entered successfully");
		
		lp.ClickLogin();
		
		if(driver.getTitle().equals("OrangeHR"))
			{
		Assert.assertTrue(true);
		logger.info("LoginTest passed");
		
			}
		else
		{
			captureScreen(driver, "UserLogin");
		Assert.assertTrue(false);
		logger.info("LoginTest Failed");
		}
	}
	
	
	@Test(dependsOnMethods={"UserLogin"})
	public void Logout() throws InterruptedException {
		lp.UserLogout();
		System.out.println("Successfully logout");

	}
	}

