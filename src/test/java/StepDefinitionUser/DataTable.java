package StepDefinitionUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class DataTable {
	String path;
	//String body;
	FileInputStream fis;
	Workbook workbook;
	Sheet sheet;
	//Cell cell;
	//CellType celltype;

	Row row;

	public DataTable(String path) {
		this.path = path;
	}

	public void createConnection(String sheetName) throws Exception {
		File file = new File(path);
		fis = new FileInputStream(file);
		workbook = new HSSFWorkbook(fis);
		//sheet = workbook.getSheetAt(0);
		sheet = workbook.getSheet(sheetName);
		
	}

	public String getDataFromExcel(String rowName, String colName) throws IOException {
		int dataRowNum = -1;
		int dataColNum = -1;
		int totalRows = sheet.getLastRowNum();
		int totalCols = sheet.getRow(0).getPhysicalNumberOfCells();
		for (int i = 0; i <= totalRows; i++) {
			if (sheet.getRow(i).getCell(0).getStringCellValue().equals(rowName)) {
				dataRowNum = i;
				break;
			}

		}
		for (int j = 0; j <= totalCols; j++) {
			if (sheet.getRow(0).getCell(j).getStringCellValue().equals(colName)) {
				dataColNum = j;
				break;
			}
		}
/*
		if(cell.getCellTypeEnum()==celltype.STRING)
			//return cell.getStringCellValue();
			body =  sheet.getRow(dataRowNum).getCell(dataColNum).getStringCellValue();
		else if(cell.getCellTypeEnum()==CellType.NUMERIC){
			  
			  String cellText  = String.valueOf(cell.getNumericCellValue());
			//return cellText;
			  body =  sheet.getRow(dataRowNum).getCell(dataColNum).String.valueOf(cell.getNumericCellValue());
			
		}*/
		
		//System.out.println("RowNum " + dataRowNum + "CellNum " + dataRowNum );
		String body =  sheet.getRow(dataRowNum).getCell(dataColNum).getStringCellValue();
		fis.close(); 
		return body;
	}
	
}
