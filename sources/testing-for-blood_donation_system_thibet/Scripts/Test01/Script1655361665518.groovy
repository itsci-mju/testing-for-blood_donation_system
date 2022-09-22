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

ReadWriteExcel kwe = new ReadWriteExcel()

kwe.xlRead('/Users/ADMINS/Desktop/Blood.xlsx', 0)
String [][] data = kwe.getData()

for (int i = 1; i < data.length; i++) {
WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8090/login')

WebUI.setText(findTestObject('Object Repository/Page_Login Page/input__username'), (data[i])[0])

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Login Page/input__password'), '4nvbrPglk7k=')

WebUI.click(findTestObject('Object Repository/Page_Login Page/button_'))

WebUI.click(findTestObject('Object Repository/Page_Login Page/h5_'))

WebUI.click(findTestObject('Object Repository/Page_Login Page/button__1'))

WebUI.setText(findTestObject('Object Repository/Page_Login Page/input_()_qty'), (data[i])[2])

WebUI.click(findTestObject('Object Repository/Page_Login Page/button__1_2'))
Thread.sleep(3000)
actual_text = WebUI.getAlertText()

	((data[i])[4]) = actual_text
	Thread.sleep(3000)
if (actual_text.equalsIgnoreCase((data[i])[3])) {
	println('Pass')
	data[i][5] = 'Pass'
} else {
	println('Fail')
	data[i][5] = 'Fail'
}
WebUI.closeBrowser()
}
kwe.xlWrite('/Users/ADMINS/Desktop/BloodResult.xlsx', data)