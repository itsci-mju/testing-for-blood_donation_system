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

String xlPath_tc = "D:/Project/Result/TC19 Approve Request Blood/TC19 Approve Request BloodResult.xlsx";
String xlPath_ts = "D:/Project/Result/TC19 Approve Request Blood/TS19 Approve Request BloodResult.xlsx";
String xlPath_td = "D:/Project/Result/TC19 Approve Request Blood/TD19 Approve Request BloodResult.xlsx";
String xlPath ="D:/Project/KDF/TC19 Approve Request Blood.xlsx";

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
				CustomKeywords.'webTest.NewKeywords.app_clicks'(xTDdata[j][4])
			
				try {
						actual_text = WebUI.getAlertText()
						Thread.sleep(2000)
				}catch(Exception e){
			
					actual_text = CustomKeywords.'webTest.NewKeywords.geterror_text'();
					
				}
					xTDdata[j][6] = actual_text
					if(actual_text.equals(xTDdata[j][5])){
						xTDdata[j][7] = "Pass"
					}else{
						   xTDdata[j][7] = "Fail"
						   xTDdata[j][8] = "- Not Match Expected Result -"
						   xTDdata[j][9] = "TC26_TD"+ j
						   CustomKeywords.'webTest.NewKeywords.Takescreen'("D:/Project/Result/Capture/TC26_TD"+ j +".png")
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
	
