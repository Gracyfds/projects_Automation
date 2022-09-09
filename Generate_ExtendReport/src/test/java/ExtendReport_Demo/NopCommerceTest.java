package ExtendReport_Demo;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NopCommerceTest {

	public WebDriver driver;
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest test;
	


@BeforeTest()
public void setExtent()
{
	extent= new ExtentReports();
	spark=new ExtentSparkReporter("Extentreport.html");
	spark.config().setDocumentTitle("Automation Report");
	spark.config().setReportName("Functional Report");
	spark.config().setTheme(Theme.DARK);
	extent.attachReporter(spark);
	extent.setSystemInfo("Hostname", "Localhost");
	extent.setSystemInfo("OS", "Windows 10");
	extent.setSystemInfo("Testname", "Gracy");
	extent.setSystemInfo("Browsername", "Chrome");
}

@AfterTest()
public void endReport()
{
	extent.flush();
}

@BeforeMethod
public void setup()
{
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	driver.get("https://www.nopcommerce.com/en/demo");

}
@Test
public void noCommerceTitleTest()
{
test=extent.createTest("noCommerceTitleTest");
String title= driver.getTitle();
System.out.println(title);
Assert.assertEquals(title, "Store Demo - nopCommerce");
}

@Test
public void noCommerceLogoTest()
{
	test=extent.createTest("noCommerceLogoTest");
	Boolean status=driver.findElement(By.id("ph-title")).isDisplayed();
	Assert.assertTrue(status);
	
}

@Test
public void noCommerceLoginTest()
{
	test=extent.createTest("noCommerceLoginTest");
	Boolean logo=driver.findElement(By.xpath("//span[@class='user-actions-ico']//*[name()='svg']//*[name()='path' and contains(@fill-rule,'evenodd')]")).isDisplayed();
	Assert.assertTrue(logo);
}

@AfterMethod
public void tearDown(ITestResult result) throws IOException
{
	if(result.getStatus()==ITestResult.FAILURE) {
		test.log(Status.FAIL, "Test Case Failed is" + result.getName());
		test.log(Status.FAIL, "Test Case Failed is" + result.getThrowable());
		
		String screenshotPath=NopCommerceTest.getScreenshot(driver, result.getName());
		test.addScreenCaptureFromPath(screenshotPath);
	}
	else if(result.getStatus()==ITestResult.SKIP)
	{
		test.log(Status.SKIP, "Test Case Skipped is" +result.getName());
		
	}
	else if(result.getStatus()==ITestResult.SUCCESS)
	{
		test.log(Status.PASS, "Test Case Passed is" +result.getName());
	}

	driver.quit();
}

public static String getScreenshot(WebDriver driver, String screenshotname) throws IOException
{
String dateName= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
TakesScreenshot ts= (TakesScreenshot) driver;
File src= ts.getScreenshotAs(OutputType.FILE);

String des= System.getProperty("user.dir") +"/Screenshots/" +screenshotname +dateName +".png";
File fdest=new File(des);
FileUtils.copyFile(src, fdest);
return des;


}

}
