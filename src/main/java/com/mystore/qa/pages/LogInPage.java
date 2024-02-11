package com.mystore.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {

	WebDriver driver;

	@FindBy(name = "email")
	private WebElement emailField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(xpath = "//input[@value='Login']")
	private WebElement logInButton;
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement emailPasswordNoMatchWarning;

	public LogInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);
	}

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public AccountPage clickOnlogInButton() {
		logInButton.click();
		return new AccountPage(driver);
	}

	public String getEmailPasswordNoMatchWarningText() {
		return emailPasswordNoMatchWarning.getText();
	}
}
