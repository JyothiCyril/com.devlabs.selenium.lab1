package com.qa.testscripts;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.pages.RediffPages;

import io.github.bonigarcia.wdm.WebDriverManager;


public class AssertsinTestNG {
	
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
	public void HardAssertDemo() {
		
		String Actualtitle = Driver.getTitle();
		String ExpectedTitle = "Welcome to Rediff";
		Assert.assertEquals(ExpectedTitle, Actualtitle); // 2 string are compared 
		Reporter.log(Actualtitle,true);
		
		boolean siginLinkDisplayed = RediffOR.getSiginLinkDisplayed();
		Assert.assertTrue(siginLinkDisplayed, "Element not present on the page");
		
		
	}
	
	@Test(priority=2)
	public void SoftassertDemo() {
		
		RediffOR.setSiginLinkClick();
		// check if login page is loaded
		
		String currentUrl = Driver.getCurrentUrl();
		
		boolean contains = currentUrl.contains("login");
		
		
		SoftAssert SAssert = new SoftAssert();
		
		SAssert.assertTrue(contains);		
		List<WebElement> alllinks = RediffOR.getAlllinks();
		int size = alllinks.size();
		
		SAssert.assertTrue(size<=5); // 2nd
		
		
		boolean staySignedInSelected = RediffOR.getStaySignedInSelected();
		SAssert.assertTrue(staySignedInSelected); // 3rd assert
		
		
		SAssert.assertAll();
	}

}
