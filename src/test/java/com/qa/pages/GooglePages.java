package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GooglePages {
	
	WebDriver Driver;
	@FindBy(name="q")
	WebElement SearchTextBox;
	
	
	public void setinputSearchTextBox(String input) {
		SearchTextBox.clear();
		SearchTextBox.sendKeys(input);
	}
	
	public void setinputSearchTextBoxSubmit() {
		SearchTextBox.submit();
	
	}
	
	
	@FindBy(className="sbct")
	List<WebElement> AutoSuggest;
	
	public List<WebElement> getAutoSuggest(){
		return AutoSuggest;
	}
	
	
	@FindBy(name="btnK")
	WebElement SearchButton;
	
	
	public void setSearchButtonClick() {
		//SearchButton.submit();
		SearchButton.click();
	}
	
	
	
	public GooglePages(WebDriver Driver) {
		this.Driver=Driver;
		
		PageFactory.initElements(Driver, this);
	}
	
}
