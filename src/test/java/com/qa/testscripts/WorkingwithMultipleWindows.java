package com.qa.testscripts;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.pages.RediffPages;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkingwithMultipleWindows {
	
	
	
	WebDriver Driver;
	RediffPages RediffOR;
	
	@BeforeTest
	public void setUp() {
		
		WebDriverManager.chromedriver().setup();
		Driver = new ChromeDriver(); // 1st windows --> parent window
		RediffOR = new RediffPages(Driver);
		
		Driver.get("https://www.rediff.com/");		
	}
	
	@AfterTest
	public void tearDown() {
		
		Driver.quit();
		
	}
	
	@Test
	public void multipleWindows() {
		
		RediffOR.setNewRegistrationLinkClick();
		
		RediffOR.setPrivacyPoliciyLinkClick(); // 2nd window opened
		
		RediffOR.setTandCLinkClick(); // 3rd window opened
		
		Set<String> WinID = Driver.getWindowHandles();
		
		
		Iterator<String> iter = WinID.iterator();
		
		/*
		 * String id1 = iter.next(); System.out.println(id1);
		 * System.out.println(Driver.switchTo().window(id1).getTitle());
		 * 
		 * 
		 * String id2 = iter.next(); System.out.println(id2);
		 * System.out.println(Driver.switchTo().window(id2).getTitle());
		 * 
		 * 
		 * String id3 = iter.next(); System.out.println(id3);
		 * System.out.println(Driver.switchTo().window(id3).getTitle());
		 */
		
		while(iter.hasNext()) {
			System.out.println("******");
			String next = iter.next();
			WebDriver window = Driver.switchTo().window(next);
			String title = window.getTitle();
			List<WebElement> findElements = window.findElements(By.tagName("a"));
			System.out.println("Total no. of elements on:" + title + " are:" + findElements.size());

			for(WebElement e:findElements) {
			System.out.println(e.getText());

			}
			System.out.println("--------");

			}
		
		
	}
	
	

}
