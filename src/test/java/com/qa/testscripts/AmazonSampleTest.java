package com.qa.testscripts;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.pages.AmazonPages;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonSampleTest {
	
	WebDriver Driver;
	AmazonPages AmazonOR;
	
	
	@BeforeTest
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
		AmazonOR = new AmazonPages(Driver);
		Driver.manage().window().maximize();
		Driver.get(Url);		
		
	}
	
	
	
	
	@AfterTest
	public void tearDown() {
		Driver.quit();
	}
	
	
	
	
	// search an item.
	@Test(priority=2)
	@Parameters({"Category","ItemName"}) // read the values from XML file
	public void SearchItem(String Category, String ItemName) { // source the input to the methods
		
		AmazonOR.SetCategoryList(Category); // input field --> Replace the test data with parameter
		AmazonOR.setSearchInput(ItemName); // 2nd input --> Replaces the test data with parameter
		AmazonOR.setMagnifierBtn();
		
	}
	
	@Test(priority=1)
	public void CheckFooterlinks() {
		
		int size = AmazonOR.getAllFooterLinks().size();
		Assert.assertTrue(size>60);
		
		
	}
	

}
