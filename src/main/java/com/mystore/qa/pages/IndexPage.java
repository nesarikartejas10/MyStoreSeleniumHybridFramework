package com.mystore.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {

	WebDriver driver;
	
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement logInLink;
	
	@FindBy(linkText = "Register")
	private WebElement registerLink;
	
	@FindBy(name = "search")
	private WebElement searchBoxfield;
	
	@FindBy(xpath = "//div[@id='search']//descendant::button")
	private WebElement searchButton;
	
	public IndexPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	
	public LogInPage selectLogInOption() {
		logInLink.click();
		return new LogInPage(driver);
	}
	
	public RegisterPage selectRegisterOption() {
		registerLink.click();
		return new RegisterPage(driver);
	}
	
	public void enterProuctIntosearchField(String searchText) {
		searchBoxfield.sendKeys(searchText);
	}
	
	public SearchPage clickOnSearchbutton() {
		searchButton.click();
		return new SearchPage(driver);
	}

}
