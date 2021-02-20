package com.qa.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExampleDragandDrop {
	
	
	@Test
	public void dragdropExample() throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver Driver = new ChromeDriver();
		Driver.manage().window().maximize();
		Driver.get("https://jqueryui.com/droppable/");
		
		JavascriptExecutor Js = (JavascriptExecutor)Driver;
		
		//Js.executeScript("window.scrollBy(x,y)");
		//x --> horizontal
		//y --> vertical
		
		
		Js.executeScript("window.scrollBy(0,300)"); // top to down
		Thread.sleep(2000);
		
		Driver.switchTo().frame(0);
		
		WebElement dragEle = Driver.findElement(By.id("draggable"));
		
		WebElement dropEle = Driver.findElement(By.id("droppable"));
		
		
		Actions act = new Actions(Driver);
		
		act.clickAndHold(dragEle).moveToElement(dropEle).release().build().perform();
		
		Thread.sleep(5000);
		
		
	}

}
