package com.app.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import com.app.excelRead.ExcelRead;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ApplicationFilePath {

	public static String filepath = "Amazon.xlsx";
	public static String Sheet1 = "Login";
	public static  String chromewebDriverValue = System.getProperty("user.dir") +"/target/tmp_webdrivers/chromedriver-windows-32bit.exe";
	public static  String iewebDriverValue = System.getProperty("user.dir") +"/target/tmp_webdrivers/internetexplorerdriver-windows-32bit.exe";
	public static  String firefoxwebDriverValue = System.getProperty("user.dir") +"/target/tmp_webdrivers/geckodriver-windows-32bit.exe";
	public static Properties p = new Properties();
	public static File f;
	public static FileInputStream fis;
	public static FileOutputStream fio;
	static ExcelRead excelRead = new ExcelRead();
	public static ExtentReports report;
	public static ExtentTest logger;
}
