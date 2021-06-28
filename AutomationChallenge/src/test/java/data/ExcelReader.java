package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	static FileInputStream fileInputStream = null; 

	/**
	 * Returns the FileInputStream of the file
	* Create the FileInputStream [Bytes] for our test data file.
	* @param  filepath  the test data file path.
	*/
	public FileInputStream getFileInputStream(String filepath) {
		
		File srcFile = new File(filepath);
		try {
			fileInputStream = new FileInputStream(srcFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error in File: "+ e.getMessage()+ " Check the File Path");
			System.exit(0);
		}
		return fileInputStream;
	}
	
	/**
	 * Returns Object[][] containing the rows and columns of the Excel sheet ignoring the first row [Headers]
	* Extracts the data from the Excel FileInputStream 
	* @param  filepath  the test data file path.
	*/
	public Object[][] extractExcelData(String filepath) throws IOException{
		fileInputStream = getFileInputStream(filepath);
		XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
		XSSFSheet sheet1 = workBook.getSheetAt(0);
		int TotalNumberOfRows = (sheet1.getLastRowNum());
		int TotalNumberOfColumns = (sheet1.getRow(0).getLastCellNum());
		String[][] arrayExcelData = new String[TotalNumberOfRows][TotalNumberOfColumns];
		//Starting Rows from 1 to neglect the headers
		for (int i = 1; i <= TotalNumberOfRows; i++) {
			XSSFRow row = sheet1.getRow(i);
			for (int j = 0; j < TotalNumberOfColumns; j++) {
				arrayExcelData[i-1][j] = row.getCell(j).toString();
			}
		}
		workBook.close();
		return arrayExcelData;
	}
}
