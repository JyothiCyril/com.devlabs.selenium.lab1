package com.qa.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkingwithFileUploadpopup {
	
	
	@Test
	public void UploadPopup() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver Driver = new ChromeDriver();
		
		Driver.manage().window().maximize();
		Driver.get("https://www.naukri.com/account/createaccount?othersrcp=16201&err=1");
		
		Driver.findElement(By.xpath("//button[contains(text(),'I am a Fresher')]")).click();
		Thread.sleep(3000);
		
		Driver.findElement(By.name("uploadCV")).sendKeys("C:\\Users\\DELL\\Downloads\\resume.pdf"); // file upload popup generated
		
		Thread.sleep(3000);
		
	}

}
