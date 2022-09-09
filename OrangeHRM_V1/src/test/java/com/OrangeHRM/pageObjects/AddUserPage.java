package com.OrangeHRM.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddUserPage {

	 WebDriver ldriver;
	 public AddUserPage(WebDriver rdriver)
	 {
		 ldriver=rdriver;
		 PageFactory.initElements(rdriver, this);
		
	 }
	 
	 @FindBy(how =How.ID, using="menu_admin_viewAdminModule")
	 @CacheLookup
	 WebElement AdminTab;
	 
	 @FindBy(how =How.ID, using="menu_admin_viewSystemUsers")
	 @CacheLookup
	 WebElement userTab;
	 
	 @FindBy(how =How.NAME, using="searchSystemUser[userName]")
	 @CacheLookup
	 WebElement AddUnm;
	
	 @FindBy(how=How.NAME, using="searchSystemUser_userType")
	 @CacheLookup
	 WebElement SelectRole;

	 @FindBy(how=How.CLASS_NAME, using="ac_input")
	 @CacheLookup
	 WebElement addempName;

	 @FindBy(how=How.ID, using="searchSystemUser_status")
	 @CacheLookup
	 WebElement addstatus;
	 
	 @FindBy(how=How.ID, using="btnAdd")
	 @CacheLookup
	 WebElement addUserTab;
	 
	 @FindBy(how =How.XPATH, using="//input[@id='systemUser_employeeName_empName']")
	 @CacheLookup
	 WebElement empName;
	 
	 @FindBy(how =How.XPATH, using="//input[@id='systemUser_userName']")
	 @CacheLookup
	 WebElement adduname;


	 @FindBy(how =How.CSS, using="#systemUser_status")
	 @CacheLookup
	 WebElement addStatus;
	 
	 
	 
	 @FindBy(how =How.XPATH, using="//input[@id='systemUser_password']")
	 @CacheLookup
	 WebElement password;
	 
	 
	 @FindBy(how =How.XPATH, using="//input[@id='systemUser_confirmPassword']")
	 @CacheLookup
	 WebElement confirm;
	 
	 @FindBy(how =How.CSS, using="#btnSave")
	 @CacheLookup
	 WebElement save;
	 
	


}



