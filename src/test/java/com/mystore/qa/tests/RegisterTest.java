package com.mystore.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.qa.base.BaseClass;
import com.mystore.qa.pages.AccountSuccessPage;
import com.mystore.qa.pages.IndexPage;
import com.mystore.qa.pages.RegisterPage;
import com.mystore.qa.utils.Utilities;

public class RegisterTest extends BaseClass {

	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setUpBrowser() {
		driver = intializeBrowser(prop.getProperty("browser"));
		IndexPage indexPage = new IndexPage(driver);
		indexPage.clickOnMyAccount();
		registerPage = indexPage.selectRegisterOption();
	}

	@Test(priority = 1)
	public void verifyRegisterAccountWithMandatoryFields() {
		registerPage.enterFirstName(dataProp.getProperty("first_Name"));
		registerPage.enterLastName(dataProp.getProperty("Last_Name"));
		registerPage.enterEmailAddress(Utilities.generateEmail());
		registerPage.enterTelephoneNo(dataProp.getProperty("Phone_No"));
		registerPage.enterPassword(dataProp.getProperty("Password"));
		registerPage.enterConfirmPassword(dataProp.getProperty("Confirm_Password"));
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();

		String actualsuccessAccountHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertTrue(actualsuccessAccountHeading.contains(dataProp.getProperty("Account_Create_Message")),
				"Your Account Has Been Created! message is not displayed");
	}

	@Test(priority = 2)
	public void verifyRegisterAccountByProvidingAllFields() {
		registerPage.enterFirstName(dataProp.getProperty("first_Name"));
		registerPage.enterLastName(dataProp.getProperty("Last_Name"));
		registerPage.enterEmailAddress(Utilities.generateEmail());
		registerPage.enterTelephoneNo(dataProp.getProperty("Phone_No"));
		registerPage.enterPassword(dataProp.getProperty("Password"));
		registerPage.enterConfirmPassword(dataProp.getProperty("Confirm_Password"));
		registerPage.selectSubscribeToYes();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();

		String actualsuccessAccountHeading = accountSuccessPage.retrieveAccountSuccessPageHeading();
		Assert.assertTrue(actualsuccessAccountHeading.contains(dataProp.getProperty("Account_Create_Message")),
				"Your Account Has Been Created! message is not displayed");
	}

	@Test(priority = 3)
	public void verifyRegisterAccountWithoutAnyFields() {
		registerPage.clickOnContinueButton();

		String actualPrivacyPolicyWarning = registerPage.getPrivacypolicyWarningMessage();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("Privacy_Policy_Warning")),
				"Privacy policy warning message is not displayed");

		String actualFirstNameWarning = registerPage.getFirstNameWarningMessage();
		Assert.assertTrue(actualFirstNameWarning.contains(dataProp.getProperty("First_Name_Warning")),
				"First Name warning message is not displayed");

		String actualLastNameWarning = registerPage.getLastNameWarningMessage();
		Assert.assertTrue(actualLastNameWarning.contains(dataProp.getProperty("Last_Name_Warning")),
				"Last Name warning message is not diplayed");

		String actualEmailWarning = registerPage.getEmailAddressWarningMessage();
		Assert.assertTrue(actualEmailWarning.contains(dataProp.getProperty("Email_warning")),
				"E-Mail Address warning message is not displayed");

		String actualTelephoneWarning = registerPage.getTelephoneWarningMessage();
		Assert.assertTrue(actualTelephoneWarning.contains(dataProp.getProperty("Telephone_Warning")),
				"Telephone warning message is not displayed");

		String actualPasswordWarning = registerPage.getPasswordWarningMessage();
		Assert.assertTrue(actualPasswordWarning.contains(dataProp.getProperty("Password_Warning")),
				"Password warning message is not displayed");
	}

	@Test(priority = 4)
	public void verifyRegisterAccountWithExistingEmail() {
		registerPage.enterFirstName(dataProp.getProperty("first_Name"));
		registerPage.enterLastName(dataProp.getProperty("Last_Name"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephoneNo(dataProp.getProperty("Phone_No"));
		registerPage.enterPassword(dataProp.getProperty("Password"));
		registerPage.enterConfirmPassword(dataProp.getProperty("Confirm_Password"));
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();

		String actualWarning = registerPage.getDuplicateEmailWarningMessageText();
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("Existing_Email_Warning")),
				"Warning message regarding existing email is not displayed");
	}

	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}

}
