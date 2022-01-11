package com.lms.api.stepdef.skills;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * This class is a utility class and is used to Read Excel sheet
 */
public class DataTable {
	
	String path;
	FileInputStream fis;
	Workbook workbook;
	Sheet sheet;
	Row row;
	
	public DataTable(String path) {
		super();
		this.path = path;
	}
	
	public void createConnection(String sheetName) throws Exception {
		
		File file=new File(path);
		fis=new FileInputStream(file);
		workbook=new HSSFWorkbook(fis);
		sheet=workbook.getSheet(sheetName);
		
	}
	
	public String getDataFromExcel(String rowName,String colName) throws IOException {
	
		int dataRowNum =-1;
		int dataColNum=-1;
		int totalRows=sheet.getLastRowNum();
		int totalCols=sheet.getRow(0).getPhysicalNumberOfCells();
		for(int i=0;i<=totalRows;i++) {
			if(sheet.getRow(i).getCell(0).getStringCellValue().equals(rowName)) {
				dataRowNum=i;
				break;
		}
		
		}
		
		for(int j=0;j<=totalCols;j++) {
			if (sheet.getRow(0).getCell(j).getStringCellValue().equals(colName)) {
				dataColNum = j;
				break;
			}
	}
		
		
		String body=sheet.getRow(dataRowNum).getCell(dataColNum).getStringCellValue();
		fis.close();
		return body;
	}
	
	
	
	

}