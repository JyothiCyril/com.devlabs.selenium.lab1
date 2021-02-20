package com.qa.testscripts;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.pages.AmazonPages;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonSearchItem {
	
	WebDriver Driver;
	AmazonPages AmazonOR;
	
	
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
		
		AmazonOR = new AmazonPages(Driver);
		Driver.manage().window().maximize();
		Driver.get(Url);		
		
	}
	
	
	
	
	@AfterMethod
	public void tearDown() {
		Driver.quit();
	}
	
	
	
	
	// search an item.
	@Test(dataProvider="getData")
	//@Parameters({"Category","ItemName"}) // read the values from XML file
	public void SearchItem(String Category, String ItemName) { // source the input to the methods
		
		AmazonOR.SetCategoryList(Category); // input field --> Replace the test data with parameter
		AmazonOR.setSearchInput(ItemName); // 2nd input --> Replaces the test data with parameter
		AmazonOR.setMagnifierBtn();
		
		String title = Driver.getTitle();
		
		Assert.assertTrue(title.contains(ItemName));
		Reporter.log(title,true);
		
	}
	
	
	// 3 values & 2 field : each field should receive 3 inputs 
	@DataProvider // getData method will source data to another method (@Test where ever it is called)
	 public Object[][] getData() {
		Object[][] data = new Object[3][2]; //[m:# of values][n: #fields]--> total no. of values m*n
		
		//Field 1
		
		data[0][0] = "Books";
		data[1][0] = "Electronics";
		data[2][0] = "Furniture";
				
		//Field 2
				
		data[0][1] = "Da vinci Code";
		data[1][1] = "Mobile phones";
		data[2][1] = "Wooden table";
		
		return data; 
	 }
	

}
