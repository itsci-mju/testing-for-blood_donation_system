import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import webTest.ReadWriteExcel as ReadWriteExcel
import webTest.NewKeywords as NewKeywords

import keywords.Keywords as Keywords
import keywords.ExcelHelper as ExcelHelper
String [][]xTSdata;
String [][]xTCdata;
String [][]xTDdata;
String []result;

String xlPath_tc = "D:/Project/Result/TC23 Edit Status Shipping/TC23 Edit Status ShippingResult.xlsx";
String xlPath_ts = "D:/Project/Result/TC23 Edit Status Shipping/TS23 Edit Status ShippingResult.xlsx";
String xlPath_td = "D:/Project/Result/TC23 Edit Status Shipping/TD23 Edit Status ShippingResult.xlsx";
String xlPath ="D:/Project/KDF/TC23 Edit Status Shipping.xlsx";

ReadWriteExcel kdf = new ReadWriteExcel();
xTCdata = kdf.xlRead(xlPath, 0);
xTSdata = kdf.xlRead(xlPath, 1);
xTDdata = kdf.xlRead(xlPath, 2);

//ExcelHelper m = new ExcelHelper()
//String excelPath = 'D:\\Project\\Result\\TC23 Edit Status Shipping\\TC23 Edit Status Shipping.xlsx'
//String xlPath ="D:/Project/Result/TC23 Edit Status Shipping/TC23 Edit Status Shipping.xlsx";
//String[][] tc = m.xlRead(excelPath, 0);
//String[][] tp = m.xlRead(excelPath, 1);
//String[][] td = m.xlRead(excelPath, 2);
//
//ReadWriteExcel kdf = new ReadWriteExcel();
//xtcdata = kdf.xlRead(excelPath, 0);
//xtpdata = kdf.xlRead(excelPath, 1);
//xtddata = kdf.xlRead(excelPath, 2);

for(int i = 1; i < xTCdata.length; i++){
	if(xTCdata[i][4].equalsIgnoreCase('y')){
		try {
		for(int j = 1; j < xTDdata.length; j++){
			if(xTDdata[j][1].equalsIgnoreCase('y')){

				WebUI.openBrowser('')
				WebUI.navigateToUrl('http://localhost:8090/login')

WebUI.setText(findTestObject('Object Repository/Page_Login Page/input__username'), 'admin')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Login Page/input__password'), '4nvbrPglk7k=')

WebUI.click(findTestObject('Object Repository/Page_Login Page/button_'))

CustomKeywords.'webTest.NewKeywords.button_click'(xTSdata[2][5])
//String  Click = tp[2][5]
//CustomKeywords.'keywords.Keywords.buttonClick'(Click)
CustomKeywords.'webTest.NewKeywords.button_click'(xTSdata[3][5])
//String  Click2 = tp[3][5]
//CustomKeywords.'keywords.Keywords.buttonClick'(Click2)

CustomKeywords.'webTest.NewKeywords.list_select'(xTSdata[4][5], xTDdata[j][4])
//String  Status = tp[4][5]
//String val_Cn = td[j][5]
//CustomKeywords.'keywords.Keywords.dropdownList'(Status, val_Cn)

CustomKeywords.'webTest.NewKeywords.button_click'(xTSdata[5][5])
//String  Click3 = tp[5][5]
//CustomKeywords.'keywords.Keywords.buttonClick'(Click3)

try {
	
					actual_text = WebUI.getAlertText()
					Thread.sleep(2000)
			}catch(Exception e){
				actual_text = "";
			}
				xTDdata[j][6] = actual_text
				if(actual_text.equals(xTDdata[j][5])){
					xTDdata[j][7] = "Pass"

				}else{
					   xTDdata[j][7] = "Fail"
					   xTDdata[j][8] = "- Not Match Expected Result -"
					   xTDdata[j][9] = "TC15_TD"+ j
					   CustomKeywords.'webTest.NewKeywords.Takescreen'("D:/Project/Result/Capture/TC15_TD"+ j +".png")
				}
			}
			 WebUI.closeBrowser()
		}
		xTCdata[i][6]= "Pass"
		
	}catch(Exception e){
		xTCdata[i][6] = "Fail"
	}

   kdf.xlWrite(xlPath_ts, xTSdata, xTSdata.length, xTSdata[1].length)
   kdf.xlWrite(xlPath_td, xTDdata, xTDdata.length, xTDdata[1].length)
   kdf.xlWrite(xlPath_tc, xTCdata, xTCdata.length, xTCdata[1].length)
	}
	
}


