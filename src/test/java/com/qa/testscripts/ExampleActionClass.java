package com.qa.testscripts;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.pages.AmazonPages;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExampleActionClass {
	
	
	WebDriver Driver;
	AmazonPages AmazonOR;
	
	
	@BeforeTest
	
	public void setUp() {
		
		
		WebDriverManager.chromedriver().setup();
		Driver = new ChromeDriver();		
		AmazonOR = new AmazonPages(Driver);
		Driver.manage().window().maximize();
		Driver.get("https://www.amazon.in/");		
		
	}
	
	
	
	
	@AfterTest
	public void tearDown() {
		Driver.quit();
	}
	
	
	@Test
	public void amazonNewReg() {
		
		Actions act = new Actions(Driver);
		
		WebElement ele = Driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		act.moveToElement(ele).build().perform();
		
		Driver.findElement(By.linkText("Start here.")).click();
		
		String currentUrl = Driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("register"));
		
		Driver.findElement(By.name("customerName")).sendKeys("Sample input");
		
		
	}
	
	
	
	
	

}
