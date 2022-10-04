package excel

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

public class ReadWriteExcel {
	private int xRows, xCols;
	private String[][] xData;

	public int getxRows() {
		return xRows;
	}

	public void setxRows(int xRows) {
		this.xRows = xRows;
	}

	public String[][] getData() {
		return xData;
	}

	public void setData(String[][] xData) {
		this.xData = xData;
	}

	public int getxCols() {
		return xCols;
	}

	public void xlRead(String sPath, int sheetNo) throws Exception {

		FileInputStream file = new FileInputStream(sPath);

		XSSFWorkbook wb = new XSSFWorkbook(file);

		XSSFSheet sheet = wb.getSheetAt(sheetNo);
		xRows = sheet.getLastRowNum() + 1;
		xCols = sheet.getRow(0).getLastCellNum();

		xData = new String[xRows][xCols];
		for (int i = 0; i < xRows; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < xCols; j++) {
				XSSFCell cell = row.getCell(j); // To read value from each col in each row
				String value = cellToString(cell);
				xData[i][j] = value;
			}
		}
		wb.close();
	}

	public String cellToString(XSSFCell cell) {
		try {
			int type = cell.getCellType();

			Object result;
			switch (type) {
				case 0: // 0
					result = cell.getNumericCellValue();
					break;
				case 1: // 1
					result = cell.getStringCellValue();
					break;
				case 2: // 2
					throw new RuntimeException("We can't evaluate formulas in Java");
				case 3: // 3
					result = "-";
					break;
				case 4: // 4
					result = cell.getBooleanCellValue();
					break;
				case 5: // 5
					throw new RuntimeException("This cell has an error");
				default:
					throw new RuntimeException("We don't support this cell type: " + type);
			}
			return result.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public void xlWrite(String xlPath, String[][] xldata) throws Exception {

		XSSFWorkbook wb = new XSSFWorkbook();

		XSSFSheet sheet = wb.createSheet("TESTRESULTS");

		for (int i = 0; i < xldata.length; i++) {

			XSSFRow row = sheet.createRow(i);

			for (int j = 0; j < xldata[i].length; j++) {
				XSSFCell cell = row.createCell(j);
				cell.setCellType(CellType.STRING);
				cell.setCellValue(xldata[i][j]);
			}
		}
		FileOutputStream fOut = new FileOutputStream(xlPath);
		wb.write(fOut);
		wb.close();
		fOut.flush();
		fOut.close();
	}
}
