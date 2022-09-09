package com.OrangeHRM.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties pro;	
	FileInputStream fis;

	public ReadConfig()
	{
		//File src= new File(System.getProperty("User.key") +"Config\\config.properties");
		File src= new File("D:\\Selenium-Workspace\\OrangeHRM_V1\\Config\\config.properties");
		try
		{
			fis= new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
} 
		catch (FileNotFoundException e) {
				System.out.println("Exception is:" +e.getMessage());		} catch (IOException e) {
					}
		
	}
	public String getBrowser()
	{
		String Browser1= pro.getProperty("browser");
		return Browser1;
	}
	
	public String getApplicationUrl()
	{
		String url=pro.getProperty("baseURL");
		return url;
	}
	
	public String getUserName()
	{
		String username= pro.getProperty("uname");
		return username;
	}
	
	public String getPassword()
	{
		String password= pro.getProperty("pass");
		return password;
	
	}
	
}
