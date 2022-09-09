package com.OrangeHRM.Testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.OrangeHRM.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Basetest
{
	
	ReadConfig readconfig= new ReadConfig();
	
	public String baseURL=readconfig.getApplicationUrl();
	public String uname=readconfig.getUserName();
	public String pass=readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
			
		logger = Logger.getLogger("OrangeHRM");
		PropertyConfigurator.configure("log4j.properties");
	
		if(br.equalsIgnoreCase("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
			}		
		else if(br.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
				}
		else if(br.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
					}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(baseURL);	
		}
	
	

public void captureScreen(WebDriver driver, String tname) throws IOException
{
	TakesScreenshot ts= (TakesScreenshot) driver;
	File source= ts.getScreenshotAs(OutputType.FILE);
	File target= new File(System.getProperty("user.dir")+ "/Screenshots/" +tname+ ".png");
	FileUtils.copyFile(source, target);
	System.out.println("ScreenShot Captured successfully");
	
	
}
@AfterClass
public void teardown()
{
	System.out.println("Driver Successfully terminated");
	driver.close();
}
}
