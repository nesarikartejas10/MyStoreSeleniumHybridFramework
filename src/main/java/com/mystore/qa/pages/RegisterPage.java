package com.mystore.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(name = "firstname")
	private WebElement firstNameField;

	@FindBy(name = "lastname")
	private WebElement lastNameField;

	@FindBy(name = "email")
	private WebElement emailField;

	@FindBy(name = "telephone")
	private WebElement telephoneField;

	@FindBy(name = "password")
	private WebElement passwordField;

	@FindBy(name = "confirm")
	private WebElement confirmPasswordField;

	@FindBy(xpath = "//label[@class='radio-inline']//following-sibling::input[@value='1']")
	private WebElement subsribeYesRadioButton;

	@FindBy(name = "agree")
	private WebElement privacyPolicyCheckbox;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(css = "div.alert-danger")
	private WebElement dupliacteEmailAddressWarning;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;

	@FindBy(xpath = "//input[@name='firstname']/following-sibling::div")
	private WebElement FirstNameWarning;

	@FindBy(xpath = "//input[@name='lastname']/following-sibling::div")
	private WebElement LastNameWarning;

	@FindBy(xpath = "//input[@name='email']/following-sibling::div")
	private WebElement emailAddressWarning;

	@FindBy(xpath = "//input[@name='telephone']/following-sibling::div")
	private WebElement telephoneWarning;

	@FindBy(xpath = "//input[@name='password']/following-sibling::div")
	private WebElement passwordWarning;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}

	public void enterEmailAddress(String emailText) {
		emailField.sendKeys(emailText);
	}

	public void enterTelephoneNo(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public void enterConfirmPassword(String confirmPasswordtext) {
		confirmPasswordField.sendKeys(confirmPasswordtext);
	}

	public void selectSubscribeToYes() {
		subsribeYesRadioButton.click();
	}

	public void selectPrivacyPolicy() {
		privacyPolicyCheckbox.click();
	}

	public AccountSuccessPage clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public String getDuplicateEmailWarningMessageText() {
		return dupliacteEmailAddressWarning.getText();
	}

	public String getPrivacypolicyWarningMessage() {
		return privacyPolicyWarning.getText();
	}

	public String getFirstNameWarningMessage() {
		return FirstNameWarning.getText();
	}

	public String getLastNameWarningMessage() {
		return LastNameWarning.getText();
	}

	public String getEmailAddressWarningMessage() {
		return emailAddressWarning.getText();
	}

	public String getTelephoneWarningMessage() {
		return telephoneWarning.getText();
	}

	public String getPasswordWarningMessage() {
		return passwordWarning.getText();
	}

}
