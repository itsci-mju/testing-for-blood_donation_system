package TC01_Mobile;
import java.io.*;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ManageExcel {

	private int xRows, xCols;
	
	private String xldata[][]; 

	
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
		
	        @SuppressWarnings("deprecation")
			CellType type = cell.getCellTypeEnum();
	        Object result;
	        switch (type) {
	            case NUMERIC: //0
	                result = cell.getNumericCellValue();
	                break;
	            case STRING: //1
	                result = cell.getStringCellValue();
	                break;
	            case FORMULA: //2
	                throw new RuntimeException("We can't evaluate formulas in Java");
	            case BLANK: //3
	                result = "";
	                break;
	            case BOOLEAN: //4
	                result = cell.getBooleanCellValue();
	                break;
	            case ERROR: //5
	                throw new RuntimeException ("This cell has an error");
	            default:
	                throw new RuntimeException("We don't support this cell type: " + type);
	        }
	        return result.toString();
	    }
	    
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
		
	}
	
