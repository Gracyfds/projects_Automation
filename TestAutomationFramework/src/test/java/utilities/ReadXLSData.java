package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ReadXLSData {

@DataProvider(name="bvtdata")
public String[][] getData(Method m) throws EncryptedDocumentException, IOException, Exception
{
	String excelSheetName=m.getName();
	File f= new File(System.getProperty("user.dir")+ "\\src\\test\\resources\\Testdata\\data.xlsx");
	FileInputStream fis= new FileInputStream(f);
	
	XSSFWorkbook wb= new XSSFWorkbook(fis);
	XSSFSheet sheetName= wb.getSheet(excelSheetName);
		
	int totalRows=sheetName.getLastRowNum();
	
	//System.out.println(totalRows);
	
	XSSFRow rowCells=sheetName.getRow(0);
	
	int totalCols= rowCells.getLastCellNum();
	
	//System.out.println(totalCols);
	
	DataFormatter format= new DataFormatter();
	
	String testData[][]= new String [totalRows][totalCols];
	
	for(int i=1; i<=totalRows; i++)
	{
	//XSSFRow row=sheetName.getRow(i);
	for(int j=0; j<totalCols;j++)
	{
	testData[i-1][j]=format.formatCellValue(sheetName.getRow(i).getCell(j));	
	//System.out.println(testData[i-1][j]);
	}
	
	}
	return testData;
	}

}

