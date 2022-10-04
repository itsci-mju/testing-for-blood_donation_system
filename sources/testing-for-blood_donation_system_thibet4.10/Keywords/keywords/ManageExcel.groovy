package keywords

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
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable

public class ManageExcel {

	private int xRows, xCols;
	private String [][] xldata;

	@Keyword
	public String[][] xlRead(String sPath, int sheetNo) throws Exception{
		xldata = null;
		File myxl = new File(sPath);
		FileInputStream myStream = new FileInputStream(myxl);

		@SuppressWarnings("resource")
				XSSFWorkbook wb = new XSSFWorkbook(myStream);
		XSSFSheet sheet = wb.getSheetAt(sheetNo);	// Referring to 1st sheet
		xRows = sheet.getLastRowNum()+1;
		xCols = sheet.getRow(0).getLastCellNum();
		System.out.println("Rows "+sheetNo+" are " + xRows);
		System.out.println("Cols "+sheetNo+" are " + xCols);
		xldata = new String[xRows][xCols];
		for (int i = 0; i < xRows; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < xCols; j++) {
				XSSFCell cell = row.getCell(j); // To read value from each col in each row
				String value = cellToString(cell);
				xldata[i][j] = value;
			}
		}
		return xldata;
	}


	public String cellToString(XSSFCell cell) {
		Object result;
		switch (cell.getCellType()) {
			case 0: // 0
				if (DateUtil.isCellDateFormatted(cell)) {
					result = cell.getDateCellValue();
				} else {
					result = cell.getNumericCellValue();
				}
				break;
			case 1: // 1
				result = cell.getStringCellValue();
				break;
			case 2: // 2
				result = cell.getCellFormula();
				break;
			case 3: // 3
				result = "";
				break;
			case 4: // 4
				result = cell.getBooleanCellValue();
				break;
			default:
				result = "";
		}
		return result.toString();
	}

	@Keyword
	public void xlWrite(String xlPath, String[][] xldata, int rows, int cols) throws Exception {

		File outFile = new File(xlPath);
		@SuppressWarnings("resource")
				XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("TestCasesResults");
		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.createRow(i);
			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.createCell(j);
				cell.setCellType(CellType.STRING);
				cell.setCellValue(xldata[i][j]);
			}
			FileOutputStream fOut = new FileOutputStream(outFile);
			wb.write(fOut);
			fOut.flush();
			fOut.close();
		}
	}

	@Keyword
	public int getxCols() {
		return xCols;
	}


}