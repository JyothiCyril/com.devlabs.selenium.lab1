package com.qa.testscripts;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ExampleforScreenshots {
	
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
	public void elementScreenshot() {
		// get the screen shot of an element
		File src= Driver.findElement(By.id("nav-logo-sprites")).getScreenshotAs(OutputType.FILE);
		String Dest = System.getProperty("user.dir") + "/Screenshots/" + "AmazonLogo" + ".png";
		try {
			FileUtils.copyFile(src, new File(Dest));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void driverScreenshot() {
		// get the screen shot of a driver.
		
		String Filename = "Amazon";
		TakesScreenshot sshot = (TakesScreenshot)Driver;
		File sourceFile = sshot.getScreenshotAs(OutputType.FILE);
		String Dest = System.getProperty("user.dir") + "/Screenshots/" + Filename + ".png";
		try {
			FileUtils.copyFile(sourceFile, new File(Dest));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	@Test
	public void fullPageScreenshot() throws IOException {
		
		String Filename = "AmazonFullPage";
		
		AShot Screen = new AShot();
		Screenshot sshot = Screen.shootingStrategy(ShootingStrategies.viewportPasting(200)).takeScreenshot(Driver);
		
		String Dest = System.getProperty("user.dir") + "/Screenshots/" + Filename + ".jpg";
		
		File FinalDestination = new File(Dest);
		
		ImageIO.write(sshot.getImage(), "jpg", FinalDestination);
		
		
		
	}
	
	
	

}
