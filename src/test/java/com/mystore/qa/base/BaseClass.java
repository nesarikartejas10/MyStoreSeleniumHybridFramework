package com.mystore.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.mystore.qa.utils.Utilities;

public class BaseClass {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public BaseClass() {

		prop = new Properties();
		dataProp = new Properties();
		File dataPropFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\mystore\\qa\\config\\testData.properties");
		File propFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\mystore\\qa\\config\\config.properties");

		try {
			FileInputStream fis = new FileInputStream(dataPropFile);
			dataProp.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver intializeBrowser(String browserName) {

		if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\india\\Downloads\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.Implicit_Wait_Time));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.Page_Load_Time));
		return driver;
	}

	public void quitBrowser() {
		driver.quit();
	}

}
