package com.mystore.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.github.javafaker.Faker;

public class Utilities {

	public final static int Implicit_Wait_Time = 10;
	public final static int Page_Load_Time = 10;
	static Faker fk = new Faker();

	public static String generateEmail() {
		String randomEmail = fk.internet().emailAddress();
		return randomEmail;
	}

	public static String generatePassword() {
		String randomPassword = fk.internet().password();
		return randomPassword;
	}

	public static Object[][] getExcelData(String sheet) {

		XSSFWorkbook wb = null;
		File file = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\mystore\\qa\\testdata\\MyStoreTestData.xlsx");
		try {
			FileInputStream fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		XSSFSheet sh = wb.getSheet(sheet);
		int rowCount = sh.getLastRowNum();
		int cellCount = sh.getRow(0).getLastCellNum();
		Object[][] data = new Object[rowCount][cellCount];
		for (int i = 0; i < rowCount; i++) {
			XSSFRow row = sh.getRow(i + 1);
			for (int j = 0; j < cellCount; j++) {
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				switch (cellType) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				default:
					break;
				}
			}
		}
		return data;
	}
	
	public static String captureScreenshot(WebDriver driver, String testName) {
		
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		File target = new File(destinationScreenshotPath);
		try {
			FileHandler.copy(source, target);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}

}
