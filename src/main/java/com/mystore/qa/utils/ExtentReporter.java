package com.mystore.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() {

		ExtentReports extentReport = new ExtentReports();
		File extentreportFile = new File(
				System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentreportFile);

		// Set Report Configuration
		// 1.set Theme
		sparkReporter.config().setTheme(Theme.DARK);
		// 2.Set Report Name
		sparkReporter.config().setReportName("MyStore Test Automation Result");
		// 3.Set Title of Report
		sparkReporter.config().setDocumentTitle("MyStore Project Report");
		// 4.Set TimeStamp
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentReport.attachReporter(sparkReporter);

		Properties configProp = new Properties();
		File configPropFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\mystore\\qa\\config\\config.properties");
		try {
			FileInputStream fis = new FileInputStream(configPropFile);
			configProp.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browser"));
		extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
		extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
		extentReport.setSystemInfo("Username", System.getProperty("user.name"));
		extentReport.setSystemInfo("Operating Sysem", System.getProperty("os.name"));
		extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReport;

	}

}
