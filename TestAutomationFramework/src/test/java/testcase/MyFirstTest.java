package testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ReadXLSData;


public class MyFirstTest extends BaseTest{

	@Test(dataProviderClass=ReadXLSData.class, dataProvider="bvtdata")
	
	public static void LoginTest(String uname, String pwd) throws InterruptedException
	{
		driver.findElement(By.cssSelector(loc.getProperty("signin_button"))).click();
		driver.findElement(By.id(loc.getProperty("email_field"))).sendKeys(uname);
		driver.findElement(By.id(loc.getProperty("next_button"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(loc.getProperty("pwd_field"))).sendKeys(pwd);
		driver.findElement(By.cssSelector(loc.getProperty("login_next_button"))).click();
		WebDriverWait wait= new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("zod-zia-content")));

		
//		driver.findElement(By.xpath("//img[@id='ztb-profile-image']")).click();
//		driver.findElement(By.xpath("//a[@id='ztb-signout']")).click();
	
	}
	
	
}