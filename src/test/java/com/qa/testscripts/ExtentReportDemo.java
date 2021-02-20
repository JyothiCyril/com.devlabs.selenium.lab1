package com.qa.testscripts;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.pages.RediffPages;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportDemo {
	
	public static WebDriver driver;
	ExtentHtmlReporter htmlReport;
	ExtentReports extentReport;
	ExtentTest extentTest;
	RediffPages RediffOR;
	
	@BeforeTest
	public void setUp() {
		
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName="Test-Report-"+timeStamp+".html";
		
		htmlReport=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName);
		
		//htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myReport.html");
		htmlReport.config().setDocumentTitle("Automation Test Report");
		htmlReport.config().setReportName("Functional Testing");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setAutoCreateRelativePathMedia(true);
		
		
		extentReport = new ExtentReports();
		extentReport.attachReporter(htmlReport);
		extentReport.setSystemInfo("OS", "WINDOWS");
		extentReport.setSystemInfo("Browser", "Chrome");
		extentReport.setSystemInfo("QA Name", "Jyothi");
		
	}
	
	@AfterTest
	public void tearDown() {
		
		extentReport.flush();
	}
	
	@BeforeMethod
	public void launchApp() {
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		RediffOR = new RediffPages(driver);
		driver.manage().window().maximize();
		driver.get("https://www.rediff.com/");
		
	}
	

	
	@AfterMethod
	public void captureResults(ITestResult results) throws IOException {
		
		if(results.getStatus()== ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "Test Passed is :" + results.getName());
			String screenshotpath = ExtentReportDemo.getScreenShot(driver, results.getName());
			extentTest.addScreenCaptureFromPath(screenshotpath);
			
		}else if(results.getStatus()== ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, "Test Failed is :" + results.getName());
			extentTest.log(Status.FAIL, "Test Failed is :" + results.getThrowable());
			
			String screenshotpath = ExtentReportDemo.getScreenShot(driver, results.getName());
			extentTest.addScreenCaptureFromPath(screenshotpath);
			
		}else if(results.getStatus()== ITestResult.SKIP) {
			extentTest.log(Status.SKIP, "Test Skipped is :" + results.getName());
			extentTest.log(Status.SKIP, "Test Skipped is :" + results.getThrowable());
			String screenshotpath = ExtentReportDemo.getScreenShot(driver, results.getName());
			extentTest.addScreenCaptureFromPath(screenshotpath);
		}
		
		
		driver.quit();
		
	}
	
	@Test(priority=0)
	public void checkTitle() {
		
		extentTest = extentReport.createTest("checkTitle"); // mark an entry into extent report
		
		String title = driver.getTitle();
		String ExpTitle = "Welcome to Rediff";
		Assert.assertEquals(title,ExpTitle);
		
	}
	
	@Test(priority=1)
	public void Login() {
		extentTest = extentReport.createTest("Login");
		
		RediffOR.setSiginLinkClick();
		RediffOR.setUnameTextFieldInput("Kim smith");
		RediffOR.setPwdTextFieldInput("mercury");
		RediffOR.setSigInButtonClick();
		
	}
	
	
	
	@Test(priority=2)
	public void createAccount() {
		extentTest = extentReport.createTest("createAccount");
		RediffOR.setNewRegistrationLinkClick();
		
		String title = driver.getCurrentUrl();
		if(title.contains("register")) {
			Assert.assertTrue(true);
			RediffOR.setFullNameText("Kim Smith");
			RediffOR.setemailTextField("test@test.com");
			
		}else {
			Assert.assertTrue(false);
		}
		
		
		
		
	}
	
	/*
	 * @Test(priority=3,dependsOnMethods ="checkTitle") public void LoginVerify() {
	 * extentTest = extentReport.createTest("LoginVerify");
	 * RediffOR.setSiginLinkClick(); String title = driver.getCurrentUrl();
	 * if(title.contains("login")) { Assert.assertTrue(true);
	 * 
	 * 
	 * }else { Assert.assertTrue(false); }
	 * 
	 * 
	 * }
	 */
	
	
	public static String getScreenShot(WebDriver driver , String ScreenshotName) throws IOException {
		
		String DateName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		File source = ts.getScreenshotAs(OutputType.FILE);
		String Destination = System.getProperty("user.dir")+ "/Screenshots/" + ScreenshotName + DateName + ".png";
		
		
		File FinalDestination = new File(Destination);
		FileUtils.copyFile(source, FinalDestination);		
		
		return Destination;
	}
	

}
