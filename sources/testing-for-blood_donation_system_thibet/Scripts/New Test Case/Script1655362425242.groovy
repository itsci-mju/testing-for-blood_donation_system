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

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8090/main')

WebUI.setText(findTestObject('Object Repository/Page_Login Page/input__username'), 'admin')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Login Page/input__password'), 'RigbBhfdqOBGNlJIWM1ClA==')

WebUI.click(findTestObject('Object Repository/Page_Login Page/button_'))

WebUI.click(findTestObject('Object Repository/Page_Login Page/h5_'))

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Login Page/select_Donation  MJU . .   10 . .   10    ._0dcfe4'), 
    '1', true)

WebUI.click(findTestObject('Object Repository/Page_Login Page/button__1'))

WebUI.click(findTestObject('Object Repository/Page_Login Page/i_Donation  MJU_fa fa-pencil'))

WebUI.click(findTestObject('Object Repository/Page_Login Page/div_'))

WebUI.click(findTestObject('Object Repository/Page_Login Page/input__underVolume'))

WebUI.click(findTestObject('Object Repository/Page_Login Page/input_Under volume_highVolume'))

WebUI.click(findTestObject('Object Repository/Page_Login Page/input_High volume_discarded'))

