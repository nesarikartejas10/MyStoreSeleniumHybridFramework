package com.mystore.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mystore.qa.base.BaseClass;
import com.mystore.qa.pages.AccountPage;
import com.mystore.qa.pages.IndexPage;
import com.mystore.qa.pages.LogInPage;
import com.mystore.qa.utils.Utilities;

public class LogInTest extends BaseClass {

	public WebDriver driver;
	LogInPage logInPage;

	public LogInTest() {
		super();
	}

	@BeforeMethod
	public void setUpBrowser() {
		driver = intializeBrowser(prop.getProperty("browser"));
		IndexPage indexPage = new IndexPage(driver);
		indexPage.clickOnMyAccount();
		logInPage = indexPage.selectLogInOption();
	}

	@Test(priority = 1, dataProvider = "supplyLogInData")
	public void verifyLoginWithValidCredentials(String email, String password) {
		logInPage.enterEmail(email);
		logInPage.enterPassword(password);
		AccountPage accountPage = logInPage.clickOnlogInButton();
		
		Assert.assertTrue(accountPage.displayStatusOfEditYourAccountInformationOption(),
				"Edit your account information link is not available");
	}

	@DataProvider(name = "supplyLogInData")
	public Object[][] getLogInData() {
		Object[][] data = Utilities.getExcelData("LogIn");
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {
		logInPage.enterEmail(Utilities.generateEmail());
		logInPage.enterPassword(Utilities.generatePassword());
		logInPage.clickOnlogInButton();

		String actualWarningMessage = logInPage.getEmailPasswordNoMatchWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message not dispalyed");
	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		logInPage.enterEmail(Utilities.generateEmail());
		logInPage.enterPassword(prop.getProperty("validPassword"));
		logInPage.clickOnlogInButton();
		
		String actualWarningMessage = logInPage.getEmailPasswordNoMatchWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message not dispalyed");
	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		logInPage.enterEmail(prop.getProperty("validEmail"));
		logInPage.enterPassword(Utilities.generatePassword());
		logInPage.clickOnlogInButton();

		String actualWarningMessage = logInPage.getEmailPasswordNoMatchWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message not dispalyed");
	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {
		logInPage.clickOnlogInButton();

		String actualWarningMessage = logInPage.getEmailPasswordNoMatchWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected warning message not dispalyed");
	}

	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}

}
