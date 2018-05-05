package com.app.TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.app.Utils.Utils;
import com.app.config.ApplicationFilePath;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase extends Utils {

	@Test
	public void appInit() {
		
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		String SampleReport =  "TestReport"+ "_" + format.format(calender.getTime()) + ".html";
		//ApplicationFilePath.report = new ExtentReports(".//Reports//TestReport.html");
		ApplicationFilePath.report = new ExtentReports(".//Reports//"+SampleReport);

		ApplicationFilePath.logger = ApplicationFilePath.report.startTest("Verify Browser");
		loadProperties();
		selectBrowser(ApplicationFilePath.p.getProperty("app.browsertype"));
		ApplicationFilePath.logger.log(LogStatus.INFO, "Browser Started");
		Assert.assertEquals("LaunchBrowser", "LaunchBrowser");
		BrowserMaximize();
		ApplicationFilePath.logger.log(LogStatus.INFO, "Browser Maximize");
		waitforpageload(5);
		driver.get(ApplicationFilePath.p.getProperty("app.url"));
		ApplicationFilePath.logger.log(LogStatus.INFO, "Navigate to URL");
		// End the test

		//ApplicationFilePath.report.endTest(ApplicationFilePath.logger);

		// Flush the data to report
		//ApplicationFilePath.report.flush();

	}

	public void loadProperties() {
		ApplicationFilePath.f = new File(
				System.getProperty("user.dir") + "//src//main//java//com//app//config//config.properties");
		try {
			ApplicationFilePath.fis = new FileInputStream(ApplicationFilePath.f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ApplicationFilePath.p.load(ApplicationFilePath.fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public WebElement getLocator(String Locator) throws Exception {
		String LocatorKey = Locator.split("_")[0];
		String LocatorValue = Locator.split("_")[1];
		if (LocatorKey.toLowerCase().equalsIgnoreCase("id"))
			return driver.findElement(By.id(LocatorValue));
		else if (LocatorKey.toLowerCase().equalsIgnoreCase("name"))
			return driver.findElement(By.name(LocatorValue));
		else if (LocatorKey.toLowerCase().equalsIgnoreCase("className"))
			return driver.findElement(By.className(LocatorValue));
		else if (LocatorKey.toLowerCase().equalsIgnoreCase("linkText"))
			return driver.findElement(By.linkText(LocatorValue));
		else if (LocatorKey.toLowerCase().equalsIgnoreCase("partialLinkText"))
			return driver.findElement(By.partialLinkText(LocatorValue));
		else if (LocatorKey.toLowerCase().equalsIgnoreCase("tagName"))
			return driver.findElement(By.tagName(LocatorValue));
		else if (LocatorKey.toLowerCase().equalsIgnoreCase("xpath"))
			return driver.findElement(By.xpath(LocatorValue));
		else {
			throw new Exception("Unknown Locator Type" + Locator);
		}

	}

	public WebElement getWebElement(String Locator) throws Exception {
		return getLocator(ApplicationFilePath.p.getProperty(Locator));
	}

	public Select getSelect(WebElement ele) {
		Select select = new Select(ele);
		return select;
	}

	public Actions getAction(WebDriver d) {
		Actions action = new Actions(driver);
		return action;
	}

	

	@AfterTest
	public void closeapp() {
		driver.quit();
		ApplicationFilePath.report.endTest(ApplicationFilePath.logger);

		// Flush the data to report
		ApplicationFilePath.report.flush();
	}
	}


