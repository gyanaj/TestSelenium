package com.app.excelRead;

import org.testng.annotations.DataProvider;

import com.app.config.ApplicationFilePath;

public class Dataprovider {

	@DataProvider
	public static Object[][] login() {
		Object[][] readdata = ExcelRead.getData(ApplicationFilePath.filepath, ApplicationFilePath.Sheet1);
		return (readdata);
	}
}
