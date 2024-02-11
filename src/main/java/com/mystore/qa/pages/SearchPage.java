package com.mystore.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement validHPProductLink;
	
	@FindBy(xpath = "//input[@id='button-search']/following-sibling::p")
	private WebElement noProductMatch;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean dispalyStatusOfValidHPProductLink() {
		return validHPProductLink.isDisplayed();
	}
	
	public String getNoProductMatchMessage() {
		return noProductMatch.getText();
	}
	
}
