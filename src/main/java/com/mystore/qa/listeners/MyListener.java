package com.mystore.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.mystore.qa.utils.ExtentReporter;
import com.mystore.qa.utils.Utilities;



public class MyListener implements ITestListener{

	ExtentReports extentReports;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context) {
		extentReports = ExtentReporter.generateExtentReport();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		extentTest = extentReports.createTest(testName);
		extentTest.log(Status.INFO, testName+" started executing");
		System.out.println(testName+" started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.PASS, testName+" got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		System.out.println("ScreenShot Taken");
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String screenshotPath = Utilities.captureScreenshot(driver, testName);
		extentTest.addScreenCaptureFromPath(screenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName+" got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName+" got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
		File extentReportFile = new File(System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html");
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
