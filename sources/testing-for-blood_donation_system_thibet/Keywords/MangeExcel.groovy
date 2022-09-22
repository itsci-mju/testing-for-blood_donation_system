import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MangeExcel {
	private int rows;
	private int cols;
	private String[][] data;

	
	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public String[][] getData() {
		return data;
	}

	public void xlRead(String path, int sheetNo) {
		try {
			FileInputStream file = new FileInputStream(path);
			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet sheet = wb.getSheetAt(0);
			rows = sheet.getLastRowNum() + 1;
			cols = sheet.getRow(0).getLastCellNum();
			data = new String[rows][cols];
			
			for (int i = 0; i < rows; i++) {
				XSSFRow row = sheet.getRow(i);
				for (int j = 0; j < cols; j++) {
					XSSFCell cell = row.getCell(j);
					String str = this.convertString(cell);
					data[i][j] = str;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public void xlWrite(String path, String[][] wdata) {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("TestResult");
		for (int i = 0; i< wdata.length; i++) {
			XSSFRow row = sheet.createRow(i);
			for(int j = 0; j< wdata[i].length; j++) {
				XSSFCell cell = row.createCell(j);
				cell.setCellType(CellType.STRING);
				cell.setCellValue(data[i][j]);
			}
		}
		try {
			FileOutputStream file = new FileOutputStream(path);
			wb.write(file);
			wb.close();
			file.flush();
			file.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String convertString(XSSFCell cell) {
		CellType type = cell.getCellType();
		Object result;
		switch (type) {
		case NUMERIC:
			result = cell.getNumericCellValue();
			break;
		case STRING:
			result = cell.getStringCellValue();
			break;
		case FORMULA:
			throw new RuntimeException("We can't evaluate formulas");
		case BLANK:
			result = "-";
			break;
		case BOOLEAN:
			result = cell.getBooleanCellValue();
			break;
		case ERROR:
			throw new RuntimeException("This cell has an error");
		default:
			throw new RuntimeException("We don't support this cell type");
		}
		return result.toString();
	}
}
