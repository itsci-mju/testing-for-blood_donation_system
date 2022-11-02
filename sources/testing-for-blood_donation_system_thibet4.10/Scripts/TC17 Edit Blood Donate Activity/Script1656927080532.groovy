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

String xlPath_tc = "D:/Project/Result/TC17 Edit Blood Donate Activity/TC17 Edit Blood Donate ActivityResult.xlsx";
String xlPath_ts = "D:/Project/Result/TC17 Edit Blood Donate Activity/TS17 Edit Blood Donate ActivityResult.xlsx";
String xlPath_td = "D:/Project/Result/TC17 Edit Blood Donate Activity/TD17 Edit Blood Donate ActivityResult.xlsx";
String xlPath ="D:/Project/KDF/TC17 Edit Blood Donate Activity.xlsx";

ReadWriteExcel kdf = new ReadWriteExcel();
xTCdata = kdf.xlRead(xlPath, 0);
xTSdata = kdf.xlRead(xlPath, 1);
xTDdata = kdf.xlRead(xlPath, 2);

for(int i = 1; i < xTCdata.length; i++){
	if(xTCdata[i][4].equalsIgnoreCase('Y')){
		try {
		for(int j = 1; j < xTDdata.length; j++){
		String actual_text = "";
			
			if(xTDdata[j][1].equalsIgnoreCase('Y')){
				WebUI.openBrowser('')
				WebUI.navigateToUrl('http://localhost:8090/login')
				CustomKeywords.'webTest.NewKeywords.edit_inputs'(xTSdata[2][5], xTDdata[j][2])
				CustomKeywords.'webTest.NewKeywords.edit_inputs'(xTSdata[3][5], xTDdata[j][3])
				CustomKeywords.'webTest.NewKeywords.button_click'(xTSdata[4][5])
				
				CustomKeywords.'webTest.NewKeywords.button_click'(xTSdata[5][5])
				CustomKeywords.'webTest.NewKeywords.button_click'(xTSdata[6][5])
				CustomKeywords.'webTest.NewKeywords.edit_inputs'(xTSdata[7][5], xTDdata[j][4])
				CustomKeywords.'webTest.NewKeywords.edit_inputs'(xTSdata[8][5], xTDdata[j][5])
				CustomKeywords.'webTest.NewKeywords.edit_inputs'(xTSdata[9][5], xTDdata[j][6])
				CustomKeywords.'webTest.NewKeywords.edit_inputs'(xTSdata[10][5], xTDdata[j][7])
				CustomKeywords.'webTest.NewKeywords.edit_inputs'(xTSdata[11][5], xTDdata[j][8])
				CustomKeywords.'webTest.NewKeywords.edit_inputs'(xTSdata[12][5], xTDdata[j][9])
				CustomKeywords.'webTest.NewKeywords.edit_inputs'(xTSdata[13][5], xTDdata[j][10])
				CustomKeywords.'webTest.NewKeywords.listhealth_select'(xTSdata[14][5], xTDdata[j][11])
				CustomKeywords.'webTest.NewKeywords.button_click'(xTSdata[15][5])
				try {
						actual_text = WebUI.getAlertText()
						Thread.sleep(2000)
				}catch(Exception e){
					actual_text = CustomKeywords.'webTest.NewKeywords.geterror_text'();
					
				}
					xTDdata[j][13] = actual_text
					if(actual_text.equals(xTDdata[j][12])){
						xTDdata[j][14] = "Pass"
					}else{
						   xTDdata[j][14] = "Fail"
						   xTDdata[j][15] = "- Not Match Expected Result -"
						   xTDdata[j][16] = "TC17_TD"+ j
						   CustomKeywords.'webTest.NewKeywords.Takescreen'("D:/Project/Result/Capture/TC17_TD"+ j +".png")
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
	




