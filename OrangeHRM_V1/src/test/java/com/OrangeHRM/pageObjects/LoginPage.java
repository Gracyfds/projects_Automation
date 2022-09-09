package com.OrangeHRM.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage {

	public static WebDriver ldriver;

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(id = "txtUsername")
	@CacheLookup
	WebElement txtUsername;

	@FindBy(id = "txtPassword")
	@CacheLookup
	WebElement txtPassword;

	@FindBy(name = "Submit")
	@CacheLookup
	WebElement loginButton;

	@FindBy(xpath = "//img[@alt='OrangeHRM']")
	@CacheLookup
	WebElement logo;

	@FindBy(xpath = "//a[@id='welcome']")
	@CacheLookup
	WebElement Userprofile;

	@FindBy(linkText = "Logout")
	@CacheLookup
	WebElement logout_btn;

	@FindBy(xpath="//a/img[@src=\"/webres_62f284fd7ec1a6.82970195/themes/default/images/logo.png\"]")
	@CacheLookup
	WebElement title;
	
	public void setUserName(String uname) {
		txtUsername.sendKeys(uname);
	}

	public void setPassword(String pass) {
		txtPassword.sendKeys(pass);
	}

	public void ClickLogin() {
		loginButton.click();
	}
	
	public void UserLogout() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(ldriver, 30);
		title.isDisplayed();
		Assert.assertTrue(true);
//		wait.until(ExpectedConditions.elementToBeClickable(Userprofile));
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='OrangeHRM']")));
		System.out.println("OrangeHRM Logo Visible");

		Userprofile.click();
		logout_btn.click();

	}
}
