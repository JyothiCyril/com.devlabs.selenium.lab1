package com.qa.testscripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkingwithFrames {
	
	WebDriver Driver;
	
	
	@BeforeTest
	public void setUp() {
		
		WebDriverManager.chromedriver().setup();
		Driver = new ChromeDriver();
		Driver.get("https://docs.oracle.com/javase/7/docs/api/");
		
	}
	
	@AfterTest
	public void tearDown() {
		Driver.quit();
	}
	
	
	@Test(priority=1)
	public void framesById() {

		// switching to a frame by index...
		
		List<WebElement> listFrames = Driver.findElements(By.tagName("frame"));
		int Count =  listFrames.size();

		System.out.println("Total no. of frames are :" + Count);

		for(int i=0; i<Count; i++) {
			WebDriver frame = Driver.switchTo().frame(i);
			System.out.println(frame.getTitle());
			
			List<WebElement> findElements = frame.findElements(By.tagName("a"));
			System.out.println("Total no. of links on the frame" + i + " are :" + findElements.size());
			
			frame.switchTo().defaultContent();
		}
	}

	
	@Test(priority=2)
	public void frameByName() throws InterruptedException {
		// switching to a frame by name attribute
		
		WebElement frameEle = Driver.findElement(By.name("packageFrame"));
		
		String Framename = frameEle.getAttribute("name");
		WebDriver frame = Driver.switchTo().frame(Framename); // name attribute // driver to frame
		
		frame.findElement(By.linkText("Arrays")).click();
		Thread.sleep(3000);
		
		frame.switchTo().defaultContent(); // frame to driver
		
		
		
		
	}
	
	@Test(priority=3)
	public void frameByElement() {
		// switching to a frame by presence of web element
		
		WebElement frameEle = Driver.findElement(By.name("packageListFrame"));
		WebDriver frame = Driver.switchTo().frame(frameEle); // based on the element
		String attribute = frame.findElement(By.linkText("java.io")).getAttribute("href");
		System.out.println(attribute);
		frame.switchTo().defaultContent();
		
		
	}


}
