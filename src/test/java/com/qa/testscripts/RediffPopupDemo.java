package com.qa.testscripts;

import org.junit.Assert;
import org.openqa.selenium.Alert;
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

public class RediffPopupDemo {
	
	
	WebDriver Driver;
	RediffPages RediffOR;
	
	@BeforeMethod
	
	public void setUp() {
		
		
		WebDriverManager.chromedriver().setup();
		Driver = new ChromeDriver();
		RediffOR = new RediffPages(Driver);
		Driver.get("https://www.rediff.com/");
		Driver.manage().window().maximize();

	}
	
	@AfterMethod
	public void tearDown() {
		
		Driver.quit();
	}
	
	
	
	@Test(priority=1)
	public void CheckSignInPage() throws InterruptedException {
		

		
		RediffOR.setSiginLinkClick();
		String currentUrl = Driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("login"));
		Reporter.log("The user is on Login page", true);
				
		RediffOR.setSigInButtonClick(); // pop is generated
		
		Thread.sleep(3000);
		
		Alert JSAlert = Driver.switchTo().alert();
		System.out.println(JSAlert.getText());
		
		JSAlert.accept(); // accept the pop up
		
		//JSAlert.dismiss(); // dismiss / terminate the popup
		
		
		
		RediffOR.setStaySignedInClick();
		Thread.sleep(2000);
		String title = Driver.getTitle();
		Assert.assertEquals("Rediff",title);
		
		
	}
	
	

}
