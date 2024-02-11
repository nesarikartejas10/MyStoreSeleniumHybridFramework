package com.mystore.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mystore.qa.base.BaseClass;
import com.mystore.qa.pages.IndexPage;
import com.mystore.qa.pages.SearchPage;

public class SearchTest extends BaseClass {

	public WebDriver driver;
	IndexPage indexPage;
	SearchPage searchPage;

	public SearchTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver = intializeBrowser(prop.getProperty("browser"));
		indexPage = new IndexPage(driver);
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		indexPage.enterProuctIntosearchField(dataProp.getProperty("Valid_Product"));
		searchPage = indexPage.clickOnSearchbutton();

		Assert.assertTrue(searchPage.dispalyStatusOfValidHPProductLink(), "Existing product is not displayed");
	}

	@Test(priority = 2,dependsOnMethods = "verifySearchWithValidProduct")
	public void verifySearchWithInvalidProduct() {
		indexPage.enterProuctIntosearchField(dataProp.getProperty("Invalid_Product"));
		searchPage = indexPage.clickOnSearchbutton();

		String actualSearchMessage = searchPage.getNoProductMatchMessage();
		Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.",
				"No product message in search result is not displayed");
	}

	@Test(priority = 3)
	public void verifySearchWithoutProduct() {
		searchPage = indexPage.clickOnSearchbutton();

		String actualSearchMessage = searchPage.getNoProductMatchMessage();
		Assert.assertEquals(actualSearchMessage, "There is no product that matches the search criteria.",
				"No product message in search result is not displayed");

	}

	@AfterMethod
	public void tearDown() {
		quitBrowser();
	}

}
