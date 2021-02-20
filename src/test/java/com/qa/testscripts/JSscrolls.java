package com.qa.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JSscrolls {
	
	WebDriver Driver;
	JavascriptExecutor Js;
	
	@BeforeTest
	public void setUp() {
		
		WebDriverManager.chromedriver().setup();
		Driver = new ChromeDriver();		
		Driver.get("https://www.amazon.in/");
		Driver.manage().window().maximize();
		
		Js = (JavascriptExecutor)Driver;
	}
	
	@AfterTest
	public void tearDown() {
		Driver.quit();
	}
	
	@Test(priority=1)
	public void scrollbars() {
		
		// scroll by points
		for(int i=0; i<=5;i++) {
			Js.executeScript("window.scrollBy(0,500)"); // top to bottom
		}
		
		
		for(int i=0; i<=5;i++) {
			Js.executeScript("window.scrollBy(0,-500)"); // botton to top
		}
		
		
		/*
		 * for(int i=0; i<=5;i++) { Js.executeScript("window.scrollBy(500,0)"); // right
		 * to left }
		 */
	}
	
	@Test(priority=2)
	public void presenceofElement() throws InterruptedException {
		
		// scroll by presence of elements
		WebElement ele = Driver.findElement(By.linkText("Careers"));
		System.out.println(ele.getText());
		
		
		Js.executeScript("arguments[0].scrollIntoView()", ele);
		Thread.sleep(3000);
		ele.click();
		
		Thread.sleep(3000);
		
		
		
	}
	
	
	

}
