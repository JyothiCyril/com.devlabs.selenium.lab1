package com.qa.testscripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pages.GooglePages;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParameterizationThruExcel {
	
	WebDriver driver;
	FileInputStream fileLoc;
	XSSFWorkbook Workbook;
	XSSFSheet Sheet;
	GooglePages googelOR;
	
	
	
	@BeforeMethod
	public void setup() throws IOException {
		
		// open the file in read mode : open any flatfile.
		fileLoc = new FileInputStream("D:\\SeleniumTraining\\com.devlabs.selenium.lab1\\src\\test\\java\\com\\qa\\testdata\\InputData.xlsx");
		// Open the input file as a workbook
		Workbook = new XSSFWorkbook(fileLoc);
		// read the work sheet with in the workbook
		Sheet = Workbook.getSheet("Sheet1");
		
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		
		googelOR = new GooglePages(driver);
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		
	}
	
	@Test
	public void googleSearchitem() throws InterruptedException {
		
		
		// Get the total no. of rows in the work sheet
		int lastRowNum = Sheet.getLastRowNum();
		
		// how many columns are there base on the total no. of cell values..
		int lastCellNum = Sheet.getRow(0).getLastCellNum();
		
		//loop iterated till the last row of the sheet..
		for(int i=1 ; i<=lastRowNum ; i++) {
			XSSFRow row = Sheet.getRow(i);
			
			for(int j=0; j<lastCellNum;j++) {
				String item = row.getCell(j).toString();
				Reporter.log(item,true);
				
				googelOR.setinputSearchTextBox(item);
				Thread.sleep(3000);
				
				List<WebElement> autoSuggest = googelOR.getAutoSuggest();
				
				for(WebElement ele:autoSuggest) {
					Reporter.log(ele.getText(),true);
				}
				
				googelOR.setinputSearchTextBoxSubmit();
				
			}
			
			
		}
		
		
		
	}
	
	@AfterMethod
	public void tearDown() throws IOException {
		driver.quit();
		fileLoc.close();
		
	}

}
