package com.qa.testscripts;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.pages.RediffPages;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RediffExample {
	
	WebDriver Driver;
	RediffPages RediffOR;
	
	@BeforeMethod
	@Parameters({"Browser","Url"})
	public void setUp(String Browser, String Url) {
		
		if(Browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			Driver = new ChromeDriver();
		} else if(Browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			Driver = new FirefoxDriver();
		} else if(Browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			Driver = new InternetExplorerDriver();
		} 
		
		RediffOR = new RediffPages(Driver);
		Driver.get(Url);
		Driver.manage().window().maximize();
		
	}
	
	@AfterMethod
	public void tearDown() {
		
		Driver.quit();
	}
	
	
	@Test(priority=1)
	public void checkCreateAccountPage() {
		
		RediffOR.setNewRegistrationLinkClick();
		
		String currentUrl = Driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("register"));
		Reporter.log("The user is on registration page", true);
		
	}
	
	@Test(priority=2)
	public void CheckSignInPage() {
		

		
		RediffOR.setSiginLinkClick();
		String currentUrl = Driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("login"));
		Reporter.log("The user is on Login page", true);
		
		
	}

}
