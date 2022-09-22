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
import excel.ReadWriteExcel as ReadWriteExcel
import webTest.ReadWriteExcel as ReadWriteExcel
import webTest.NewKeywords as NewKeywords

import keywords.Keywords as Keywords
import keywords.ExcelHelper as ExcelHelper

ExcelHelper m = new ExcelHelper()
String excelPath = 'D:\\Project\\Result\\TC26 Approve Hospital Account\\TC26 Approve Hospital Account.xlsx'
String xlPath ="D:/Project/Result/TC26 Approve Hospital Account/TC26 Approve Hospital Account.xlsx";
String[][] tc = m.xlRead(excelPath, 0);
String[][] tp = m.xlRead(excelPath, 1);
String[][] td = m.xlRead(excelPath, 2);
for(int i = 1; i < tc.length; i++){
	if(tc[i][4].equalsIgnoreCase('y')){
		for(int j = 1; j < td.length; j++){
			if(td[j][1].equalsIgnoreCase('y')){
				
				String expected = td[j][5]
				String url = td[j][2]
				CustomKeywords.'keywords.Keywords.open_browser'(url)

WebUI.setText(findTestObject('Object Repository/Page_Login Page/input__username'), (td[j])[3])

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Login Page/input__password'), '4nvbrPglk7k=')

WebUI.click(findTestObject('Object Repository/Page_Login Page/button_'))

String  Click = tp[2][5]
CustomKeywords.'keywords.Keywords.buttonClick'(Click)

String  Click3 = tp[3][5]
CustomKeywords.'keywords.Keywords.buttonClick'(Click3)

//WebUI.click(findTestObject('Object Repository/Page_Insert title here/i__fa fa-eye'))

String  Clickk = tp[4][5]
CustomKeywords.'keywords.Keywords.buttonClick'(Clickk)
	actual = WebUI.getAlertText()
	Thread.sleep(2000)
					td[j][7] = actual
					if(actual.equals(td[j][6])){
						td[j][8] = "Pass"
					
					}else{
						   td[j][8] = "Fail"

						   td[j][9] = "- Not Match Expected Result -"
						   td[j][10] = "TC05_TD"+ j
						   CustomKeywords.'webTest.NewKeywords.Takescreen'("D:/Project/Result/Capture/TC26"+ j +".png")
					}
					
					
				}
				 WebUI.closeBrowser()
				
				m.xlWrite("D:\\Project\\Result\\TC26 Approve Hospital Account\\TC26 Approve Hospital AccountResult.xlsx", td)
				
}
}
}