package keywords
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil


public class ExcelHelper {

	private int xRows, xCols
	private String[][] xldata;

	/*-Read excel file-*/
	@Keyword
	public String[][] xlRead(String sPath, int sheetNo) throws Exception{
		xldata = null;
		File myxl = new File(sPath);
		FileInputStream myStream = new FileInputStream(myxl);

		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(myStream);
		XSSFSheet sheet = wb.getSheetAt(sheetNo);
		xRows = sheet.getLastRowNum()+1;
		xCols = sheet.getRow(0).getLastCellNum();
		System.out.println("Rows "+sheetNo+" are " + xRows);
		System.out.println("Cols "+sheetNo+" are " + xCols);
		xldata = new String[xRows][xCols];
		for (int i = 0; i < xRows; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < xCols; j++) {
				XSSFCell cell = row.getCell(j);
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

	/*-Write excel file-*/
	@Keyword
	public void xlWrite(String xlPath, String[][] xldata) throws Exception {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("TestCasesResults");

		//println xldata[0][0]

		for (int i = 0; i < xRows; i++) {

			XSSFRow row = sheet.createRow(i);


			for (int j = 0; j < xCols; j++) {

				XSSFCell cell = row.createCell(j);
				XSSFCellStyle cellStyle = wb.createCellStyle();

				cellStyle.setBorderRight(BorderStyle.THIN);
				cellStyle.setBorderLeft(BorderStyle.THIN);
				cellStyle.setBorderTop(BorderStyle.THIN);
				cellStyle.setBorderBottom(BorderStyle.THIN);

				cell.setCellStyle(cellStyle);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(xldata[i][j]);
			}
		}
		FileOutputStream fOut = new FileOutputStream(xlPath);
		wb.write(fOut);
		fOut.flush();
		fOut.close();
	}

	public void xlwrite(String xlPath, String[][] xldata, int rows, int cols) throws Exception {
		File outFile = new File(xlPath);
		@SuppressWarnings("resource")
				XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("TestCasesResults");
		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.createRow(i);
			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.createCell(j);
				cell.setCellType(cell.CELL_TYPE_STRING);
				cell.setCellValue(xldata[i][j]);
			}
			FileOutputStream fOut = new FileOutputStream(outFile);
			wb.write(fOut);
			fOut.flush();
			fOut.close();
		}
	}


	@Keyword
	public int getxRows() {
		return xRows;
	}
	@Keyword
	public int getxCols() {
		return xCols;
	}

}

