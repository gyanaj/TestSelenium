package com.app.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.config.ApplicationFilePath;

public class Utils {

	public static WebDriver driver;

	public WebDriver selectBrowser(String Browser) {
		if (Browser.equalsIgnoreCase("chrome") || (Browser.equalsIgnoreCase("CHROME"))) {

			System.setProperty("webdriver.chrome.driver", ApplicationFilePath.chromewebDriverValue);
			driver = new ChromeDriver();
		}

		else if (Browser.equalsIgnoreCase("IE") || (Browser.equalsIgnoreCase("InternetExplorer"))) {
			System.setProperty("webdriver.ie.driver", ApplicationFilePath.iewebDriverValue);
			driver = new InternetExplorerDriver();

		} else if (Browser.equalsIgnoreCase("Firefox") || (Browser.equalsIgnoreCase("FIREFOX"))) {
			System.setProperty("webdriver.gecko.driver", ApplicationFilePath.firefoxwebDriverValue);
			driver = new FirefoxDriver();
		} else {
			System.out.println("Browser Not Present");
		}
		return driver;
	}

	public void BrowserMaximize() {
		driver.manage().window().maximize();
	}

	public int waitforpageload(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		return time;
	}

	public WebElement waitElementclick(WebElement ele, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public Boolean waitElementload(WebElement ele, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.elementToBeSelected(ele));
	}

	public WebElement waitElement(WebElement ele, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.visibilityOf(ele));

	}

	public WebElement waitElementvisibleAll(WebElement ele, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return (WebElement) wait.until(ExpectedConditions.visibilityOfAllElements(ele));

	}

	public void closecurrentapp() {
		driver.close();
	}

	public void navigateback() {
		driver.navigate().back();
	}

	public void refreshpage() {
		driver.navigate().refresh();
	}

	public void navigateforward() {
		driver.navigate().forward();
	}

	public void getScreenshot(String ImageN) {
		File Image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String ImageLocation = ".//TestSelenium//src/main//java//com//app//screenshot";
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss");
		String ActualImage = ImageLocation + ImageN + "_" + format.format(calender.getTime()) + ".jpg";
		try {
			FileUtils.copyFile(Image, new File(ActualImage));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
