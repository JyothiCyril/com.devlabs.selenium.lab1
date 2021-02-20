package com.qa.testscripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NewToursTestScript {
	
	WebDriver driver;
	FileInputStream fileLoc;
	Properties prop;
	
	
	
	@BeforeTest
	public void setUp() throws IOException {
		
		// opens the file in read mode...
		fileLoc = new FileInputStream("D:\\SeleniumTraining\\com.devlabs.selenium.lab1\\src\\test\\java\\com\\qa\\testdata\\Testdata.properties");
		
		
		// load the file to read the value in the file
		prop = new Properties();
		prop.load(fileLoc);
		
		
		
		
		String Browser = prop.getProperty("browser");
		
		if(Browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if(Browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(Browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} 
		
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		
	}
	
	@Test
	public void Login() {
		
		driver.findElement(By.name("userName")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.name("password")).sendKeys(prop.getProperty("password"));
		
		driver.findElement(By.name("submit")).click();
		
		String title = driver.getTitle();
		
		boolean status = title.contains("Login");
		
		Assert.assertTrue(status);		
		
	}

}
