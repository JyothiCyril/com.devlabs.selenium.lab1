package com.qa.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkingwithHiddenDivisionPopUp {
	
	WebDriver Driver;
	
	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		Driver=new ChromeDriver();
		Driver.manage().window().maximize();
		Driver.get("https://www.cleartrip.com/");
		
		
	}

	@AfterTest
	public void tearDown() {
		Driver.quit();
	}

	
	@Test
	public void demoHiddenPopup() throws InterruptedException {
		
		Driver.findElement(By.id("DepartDate")).click();
		// publish the calendar --> hidden pop
		Thread.sleep(3000);
		
		Driver.findElement(By.xpath("(//a[.=26])[2]")).click();
		Thread.sleep(3000);
		
	}
}
