package com.app.excelRead;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.app.config.ApplicationFilePath;

public class ExcelRead {

	public static org.apache.poi.ss.usermodel.Cell c;
	public static org.apache.poi.ss.usermodel.Sheet sh;
	public static Workbook wb;

	public static Object[][] getData(String datafile, String sheet) {
		ApplicationFilePath.f = new File(datafile);
		try {
			ApplicationFilePath.fis = new FileInputStream(ApplicationFilePath.f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(ApplicationFilePath.fis);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sh = wb.getSheet(sheet);
		int row = sh.getLastRowNum();
		Row r = sh.getRow(0);
		int column = r.getLastCellNum();
		String readdata[][] = null;
		readdata = new String[row][column];
		int ci, cj;

		ci = 0;
		for (int i = 1; i <= row; ci++, i++) {
			cj = 0;
			for (int j = 0; j < column; cj++, j++) {
				readdata[ci][cj] = getCelData(i, j);
			}
		}
		return readdata;
	}

	public static String getCelData(int RowNum, int ColNum) {
		c = sh.getRow(RowNum).getCell(ColNum);

		if (c == null || c.getCellType() == c.CELL_TYPE_BLANK) {
			return "";
		} else {
			String CellData = c.getStringCellValue();
			return CellData;

		}
	}

}
