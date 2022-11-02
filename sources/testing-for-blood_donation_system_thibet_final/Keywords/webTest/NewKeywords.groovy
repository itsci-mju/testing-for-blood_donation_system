package webTest
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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.text.SimpleDateFormat
import java.time.LocalDate;
import internal.GlobalVariable
import java.util.Date;
import java.util.Locale;
import org.openqa.selenium.Alert
import org.openqa.selenium.TakesScreenshot
//import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import org.openqa.selenium.WebDriver
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

public class NewKeywords {

	WebDriver webDriver = DriverFactory.getWebDriver()

	@Keyword
	public void button_click(String xPath) {
		WebUI.delay(1)
		webDriver.findElement(By.xpath(xPath)).click();
	}

	@Keyword
	public void butt_clicks(String fText) {
		try {
			if(fText.equals("ไม่อนุมัติ")){
				WebUI.click(findTestObject('Object Repository/Page_Insert title here/a__1'))
			}else if(fText.equals("อนุมัติ")){
				WebUI.click(findTestObject('Object Repository/Page_Insert title here/a_'))
			}
		}catch(Exception e){

		}

	}

	@Keyword
	public void app_clicks(String fText) {
		try {
			if(fText.equals("ไม่อนุมัติ")){
				WebUI.click(findTestObject('Object Repository/Page_Insert title here/button__1'))
			}else if(fText.equals("อนุมัติ")){
				WebUI.click(findTestObject('Object Repository/Page_Insert title here/button_'))
			}
		}catch(Exception e){

		}

	}
	@Keyword
	public void app_click( String fText) {
		try {
			if(fText.equals("No")){
				webDriver.findElement(By.xpath('//span[contains(text(),"×")]')).click();
				//				 WebUI.click(findTestObject('Object Repository/Page_Login Page/button__1_2_3'))
			}else if(fText.equals("Yes")){
				//				WebUI.click(findTestObject('Object Repository/Page_Login Page/button__1_2'))   //บันทึก
				webDriver.findElement(By.xpath("//button[contains(text(),'บันทึก')]")).click();
				//				webDriver.findElement(By.xpath('//span[contains(text(),"×")]')).click();
			}
		}catch(Exception e){

		}

	}

	@Keyword
	public void rem_click(String fText) {
		try {
			if(fText.equals("ยืนยัน")){
				//				WebUI.click(findTestObject('Object Repository/Page_Login Page/button__1_2'))
				webDriver.findElement(By.xpath('//button[contains(text(),"บันทึก")]')).click()
			}else if(fText.equals("ปิด")){
				webDriver.findElement(By.xpath('//body/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/button[2]')).click()
				//button[contains(text(),'บันทึก')]
			}
		}catch(Exception e){

		}

	}

	@Keyword
	public void more_click(String fText) {
		try {
			System.out.println(fText);
			String[]  rsult= fText.split (",");
			System.out.println("id ---hrhtrhtrdjyseryhttfujrdut-> "+rsult.length);
			for(int i =0 ; i< rsult.length ; i++) {
				if("Deferred due to".equals(rsult[i])){
					webDriver.findElement(By.xpath('//input[@id="deferred1"]')).click()

				}else if("กินยาที่มีผลต่อเกล็ดเลือด".equals(rsult[i])){
					webDriver.findElement(By.xpath('//input[@id="medicationAffectsPlatelets1"]')).click()

				}else if("Under volume".equals(rsult[i])){
					webDriver.findElement(By.xpath('//input[@id="underVolume1"]')).click()

				}else if("High volume".equals(rsult[i])){
					webDriver.findElement(By.xpath('//input[@id="highVolume1"]')).click()

				}else if("Discarded".equals(rsult[i])){
					webDriver.findElement(By.xpath('//input[@id="discarded1"]]')).click()

				}
			}

		}catch(Exception e){

		}

	}



	@Keyword
	public void edit_input(String xPath, String fText) {
		WebUI.delay(1)
		webDriver.findElement(By.xpath(xPath)).click();
		webDriver.findElement(By.xpath(xPath)).sendKeys(fText);
	}
	@Keyword

	public String geterror_text() {
		WebUI.delay(1)
		String 	result = WebUI.getText(findTestObject('Object Repository/Page_Error/h1_Were sorry for the error'));
		return result ;
	}
	@Keyword
	public String getalert_text() {
		WebUI.delay(1)
		String 	results = WebUI.getText(findTestObject('Object Repository/Page_Login Page/label_'));
		return results ;
	}

	@Keyword
	public void edit_inputs(String xPath, String fText) {
		WebUI.delay(1)
		webDriver.findElement(By.xpath(xPath)).clear();
		webDriver.findElement(By.xpath(xPath)).sendKeys(fText);
	}

	@Keyword
	public String get_text(String xPath) {
		WebUI.delay(1)
		String text = webDriver.findElement(By.xpath(xPath)).getText();
		return text;
	}

	@Keyword
	public void list_select(String xPath, String fText) {
		Select select = new Select(webDriver.findElement(By.xpath(xPath)));
		List <WebElement> options = select.getOptions();

		if(fText.equals('นาง')){

			WebUI.selectOptionByValue(findTestObject('TS01/Page_/select_title'), "นาง", true)

		}else if(fText.equals("จัดส่ง")){
			WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Login Page/select_'), 'จัดส่งสำเร็จ', true)
		}else if(fText.equals("กำลังดำเนินการ")){
			WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Login Page/select_'), 'กำลังจัดส่ง', true)
		}else if(fText.equals("เปิดให้แลก")){
			WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Login Page/select_'), 'เปิดให้แลก', true)
		}else if(fText.equals("ปิดให้แลก")){
			WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Login Page/select_'), 'ปิดให้แลก', true)
		}else if(fText.equals("รับบริจาค")){
			WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Login Page/select_'), 'รับบริจาค', true)
		}else if(fText.equals("งดรับบริจาค")){
			WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Login Page/select_'), 'งดรับบริจาค', true)
		}

	}

	@Keyword
	public void listsearch_select(String xPath, String fText) {
		Select select = new Select(webDriver.findElement(By.xpath(xPath)));
		List <WebElement> options = select.getOptions();
		if(fText.equals('เลือดนี้เพื่อน้องDonation : แม่โจ้MJU')){
			WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Login Page/select_Donation  MJU . .   10 . .   10'), '1', true)

		}else if(fText.equals("เวลาราชการ อ. พฤ. : ภาคบริการโลหิตแห่งชาติที่ 10")){
			WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Login Page/select_Donation  MJU . .   10 . .   10'), '2', true)
		}else if(fText.equals("เวลาราชการ ส. อา. : ภาคบริการโลหิตแห่งชาติที่ 10")){
			WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Login Page/select_Donation  MJU . .   10 . .   10'), '3',  true)
		}
	}

	@Keyword
	public void listhealth_select(String xPath, String fText) {
		Select select = new Select(webDriver.findElement(By.xpath(xPath)));
		List <WebElement> options = select.getOptions();
		System.out.println("id ---hrhtrhtrdjyseryhttfujrdut-> "+options.size() );
		for(int i =0 ; i< options.size() ; i++) {
			System.out.println("id ---hrhtrhtrdjyseryhttfujrdut-> "+options.get(i).getText());
			if(options.get(i).getText().equals(fText)){
				options.get(i).click()
			}
		}
	}


	@Keyword
	public void listbagKeep_select(String xPath, String fText) {
		Select select = new Select(webDriver.findElement(By.xpath(xPath)));
		List <WebElement> options = select.getOptions();
		if(fText.equals('สมชาย ใจดี')){
			WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Login Page/select_1111 111'), '2', true)

		}else if(fText.equals("สมศรี จริงใจ")){
			WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Login Page/select_1111 111'), '4', true)
		}
	}

	@Keyword
	public void listtestBlood_select(String xPath, String fText) {
		Select select = new Select(webDriver.findElement(By.xpath(xPath)));
		List <WebElement> options = select.getOptions();
		if(fText.equals('สมชาย ใจดี')){
			WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Login Page/select_1111 111'), '2', true)

		}else if(fText.equals("สมศรี จริงใจ")){
			WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Login Page/select_1111 111'), '4', true)
		}
	}



	@Keyword
	public void PictureList (String xPath, String fText) {
		try{
			if(fText.equals("Water.png")){
				WebUI.uploadFile(findTestObject('Object Repository/Page_Login Page/input__imgreward'),'D:\\Project\\Photo\\Water.png')
			}else if(fText.equals("Cap.jpg")){
				WebUI.uploadFile(findTestObject('Object Repository/Page_Login Page/input__imgreward'),'D:\\Project\\Photo\\Cap.jpg')
			}else if(fText.equals("เก้าเก้า.pdf")){
				WebUI.uploadFile(findTestObject('Object Repository/Page_Login Page/input__imgreward'),'D:\\Project\\Photo\\เก้าเก้า.pdf')
			}


			if(fText.equals("profile.pdf")){
				WebUI.uploadFile(findTestObject('TS01/Page_/input__avatar'), 'D:\\Image\\profile.pdf')
			}else if(fText.equals("profile.gif")){
				WebUI.uploadFile(findTestObject('TS01/Page_/input__avatar'), 'D:\\Image\\profile.gif')
			}

			if(fText.equals("IDcard.pdf")){
				WebUI.uploadFile(findTestObject('TS01/Page_/input_(PDF  )_copyOfIdCard'), 'D:\\Image\\IDcard.pdf')
			}else if(fText.equals("IDcard.gif")){
				WebUI.uploadFile(findTestObject('TS01/Page_/input_(PDF  )_copyOfIdCard'), 'D:\\Image\\IDcard.gif')
			}

			if(fText.equals("house.pdf")){
				WebUI.uploadFile(findTestObject('TS01/Page_/input_(PDF  )_houseRegistrationDocument'), 'D:\\Image\\house.pdf')
			}else if(fText.equals("house.gif")){
				WebUI.uploadFile(findTestObject('TS01/Page_/input_(PDF  )_houseRegistrationDocument'), 'D:\\Image\\house.gif')
			}

			if(fText.equals("transcript.pdf")){
				WebUI.uploadFile(findTestObject('TS01/Page_/input_(PDF  )_educationalCertificate'), 'D:\\Image\\transcript.pdf')
			}else if(fText.equals("transcript.gif")){
				WebUI.uploadFile(findTestObject('TS01/Page_/input_(PDF  )_educationalCertificate'), 'D:\\Image\\transcript.gif')
			}

			if(fText.equals("medical.pdf")){
				WebUI.uploadFile(findTestObject('TS01/Page_/input_(  )_medicalCertificate'), 'D:\\Image\\medical.pdf')
			}else if(fText.equals("medical.gif")){
				WebUI.uploadFile(findTestObject('TS01/Page_/input_(  )_medicalCertificate'), 'D:\\Image\\medical.gif')
			}

			WebUI.delay(1)
			if(fText.equals("proof.pdf")){
				WebUI.uploadFile(findTestObject('TS03/Page_/input__proof'), 'D:\\Image\\proof.pdf')
			}else if(fText.equals("proof.gif")){
				WebUI.uploadFile(findTestObject('TS03/Page_/input__proof'), 'D:\\Image\\proof.gif')
			}

			WebUI.delay(1)
			if(fText.equals("Image1.pdf")){
				WebUI.uploadFile(findTestObject('TS06/Page_/input__mainimage'), 'D:\\Image\\Image.pdf')
			}else if(fText.equals("Image1.gif")){
				WebUI.uploadFile(findTestObject('TS06/Page_/input__mainimage'), 'D:\\Image\\Image.gif')
			}

			WebUI.delay(1)
			if(fText.equals("Image2.pdf")){
				WebUI.uploadFile(findTestObject('TS06/Page_/input_  Ctrl  1 _image_article'), 'D:\\Image\\Image.pdf')
			}else if(fText.equals("Image2.gif")){
				WebUI.uploadFile(findTestObject('TS06/Page_/input_  Ctrl  1 _image_article'), 'D:\\Image\\Image.gif')
			}
			WebUI.delay(1)
			if(fText.equals("Image3.pdf")){
				WebUI.uploadFile(findTestObject('TS07/Page_/input__new_mainimg'), 'D:\\Image\\Image.pdf')
			}else if(fText.equals("Image3.gif")){
				WebUI.uploadFile(findTestObject('TS07/Page_/input__new_mainimg'), 'D:\\Image\\Image.gif')
			}

			WebUI.delay(1)
			if(fText.equals("Image4.pdf")){
				WebUI.uploadFile(findTestObject('TS07/Page_/input_  (120)_newimg'), 'D:\\Image\\Image.pdf')
			}else if(fText.equals("Image4.gif")){
				WebUI.uploadFile(findTestObject('TS07/Page_/input_  (120)_newimg'), 'D:\\Image\\Image.gif')
			}

		}catch(Exception e){

		}
	}

	/*	@Keyword
	 public  void radio_select(String xPath, String fText) {
	 List<WebElement> radio = webDriver.findElements(By.xpath(xPath));
	 for (int i = 0; i < radio.size(); i++) {
	 if (radio.get(i).getAttribute(fText).equals(fText)) {
	 radio.get(i).click();
	 return ;
	 }
	 }
	 throw new NoSuchElementException("Invalid Radio button Selected");
	 }
	 */

	@Keyword
	public String Takescreen (String path) {
		WebUI.takeScreenshot(path)
	}
}


