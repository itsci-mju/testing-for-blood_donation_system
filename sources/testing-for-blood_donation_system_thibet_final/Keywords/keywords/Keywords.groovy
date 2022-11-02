package keywords

import org.apache.commons.io.FileUtils
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.ui.Select
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class Keywords {

	private WebDriver driver = null

	/*-Open Browser-*/
	@Keyword
	public void open_browser(String url){
		WebUI.openBrowser(url)
	}

	/*-Input Text-*/
	@Keyword
	public void inputText(String xPath,String value){
		try{
			driver = DriverFactory.getWebDriver()
			WebElement element = driver.findElement(By.xpath(xPath))
			element.sendKeys(Keys.chord(Keys.CONTROL,'a',Keys.DELETE))
			element.sendKeys(value)
		}catch(Exception e){
		}
	}

	/*-Get Text-*/
	@Keyword
	public String getText(String gtText){
		try{
			String txt = ""
			driver = DriverFactory.getWebDriver()
			WebElement element = driver.findElement(By.xpath(gtText))
			txt = element.getText()
			return txt
		}catch(Exception e){}
	}

	/*-Get Text-*/
	@Keyword
	public String getText2(String gtText){
		try{
			String txt = ""
			driver = DriverFactory.getWebDriver()
			WebElement element = driver.findElement(By.xpath(gtText))
			txt = element.getText()
			return txt
		}catch(Exception e){
			return null;
		}
	}




	/*-Get Attribute Class-*/
	public String getTextAttribute(String xpath){
		String result = null
		driver = DriverFactory.getWebDriver()
		WebElement element = driver.findElement(By.xpath(xpath))
		result = element.getAttribute('class')
		println (result)
		return result
	}

	/*-Get Count List Table-*/
	public String getListElement(String list_element, String txt){
		driver = DriverFactory.getWebDriver()
		List<WebElement> elements = driver.findElements(By.xpath(list_element))
		int count = elements.size()
		String text = null
		if(count > 0){
			text = "ListData > 0"
			println("list = " + count)
		}else{
			text = "ไม่พบข้อมูล" + txt
			println("list = " + count)
		}
		return text
	}

	/*-Link Text-*/
	@Keyword
	public void linkText(String linkText){
		driver = DriverFactory.getWebDriver()
		WebElement element = driver.findElement(By.xpath(linkText))
		element.click()
	}

	/*-Hover & click-*/
	@Keyword
	public void hoverAndClick(String elementToHover,String elementToClick) {
		driver = DriverFactory.getWebDriver()
		Actions action = new Actions(driver)
		WebElement hover = driver.findElement(By.xpath(elementToHover))
		WebElement click = driver.findElement(By.xpath(elementToClick))
		action.moveToElement(hover).click(click).build().perform()
	}

	/*-ScrollBar-*/
	@Keyword
	public void scrollBarPage(String url,String element){
		driver = DriverFactory.getWebDriver()
		JavascriptExecutor js = (JavascriptExecutor) driver
		driver.get(url)
		WebElement Element = driver.findElement(By.xpath(element))
		//This will scroll the page till the element is found
		js.executeScript("arguments[0].scrollIntoView();", Element)
	}

	/*-Radio-*/
	@Keyword
	public void radio(String xPath, String value) {
		driver = DriverFactory.getWebDriver()
		try{
			if(value!=''){
				List<WebElement> radio = driver.findElements(By.xpath(xPath))
				for(WebElement we : radio) {
					if(we.getAttribute("value").equalsIgnoreCase(value)){
						we.click();
						KeywordUtil.markPassed("-Radio PASS-");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			KeywordUtil.markFailed("-Radio FAIL-")
		}
	}

	/*-DropdownList-*/
	public void dropdownList(String xPath, String value) {
		driver = DriverFactory.getWebDriver()
		try{
			Select select = new Select(driver.findElement(By.xpath(xPath)))
			select.selectByVisibleText(value)
			KeywordUtil.markPassed("-DropdownList Select PASS-");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*-Check box-*/
	@Keyword
	public void checkBox(String chckbox,String value){
		String[] extract = value.split(",")
		driver = DriverFactory.getWebDriver()
		try	{
			if(extract.length > 0) {
				List<WebElement> checkBox = driver.findElements(By.xpath(chckbox))
				for (int i = 0; i < extract.length; i++){
					if(extract[i].equalsIgnoreCase("ยืนยัน")){
						checkBox.get(i).click()
						println(checkBox.get(i).getText())
						KeywordUtil.markPassed("-Checkbox Pass-")
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace()
			KeywordUtil.markFailed("-Checkbox Fail-")
		}
	}


	/*-Check box2-*/
	@Keyword
	public void checkBox2(String chckbox,String value){

		driver = DriverFactory.getWebDriver()
		driver.findElement(By.xpath(chckbox+"'"+value+"'"+"]")).click()
	}


	@Keyword
	public void gettexreove(String chckbox,String value){

		driver = DriverFactory.getWebDriver()
		driver.findElement(By.xpath(chckbox+"'"+value+"'"+"]")).getText()
	}
	/*-Button-*/
	@Keyword
	public void buttonClick(String btn){
		try{
			driver = DriverFactory.getWebDriver()
			driver.findElement(By.xpath(btn)).click()
		}catch(Exception e){
		}
	}

	/*-Button-*/
	@Keyword
	public void buttonClick2(String btn){
		try{
			driver = DriverFactory.getWebDriver()
			driver.findElement(By.xpath(btn)).click()
		}catch(Exception e){
		}
	}

	/*-ButtonClicktext-*/
	@Keyword
	public void buttonClicktext(String buttonClickNum,String value){
		try{
			driver = DriverFactory.getWebDriver()
			driver.findElement(By.xpath(buttonClickNum+"'"+value+"'"+")"+"]")).click()
		}catch(Exception e){
		}
	}


	@Keyword
	public void buttonClick3(String buttonClickNum,String value){

		driver = DriverFactory.getWebDriver()
		driver.findElement(By.xpath(buttonClickNum+value+"'"+"]")).click()
	}

	/*-Alert-*/
	@Keyword
	public String alert_accept(){
		String alText = null
		driver = DriverFactory.getWebDriver()
		try{
			alText = driver.switchTo().alert().getText()
			println(alText)
			driver.switchTo().alert().accept()
		}catch(Exception e){
			e.printStackTrace()
		}
		return alText
	}

	/*-Upload File-*/
	@Keyword
	public void uploadFile(String xpath,String fileName){
		driver = DriverFactory.getWebDriver()
		WebElement upload = driver.findElement(By.xpath(xpath))
		String extension = fileName.substring(fileName.lastIndexOf("."))
		println(extension)
	}

	/*-Get Attribute Value-*/
	public String getTextValue(String xpath){
		String result = null
		driver = DriverFactory.getWebDriver()
		WebElement element = driver.findElement(By.xpath(xpath))
		result = element.getAttribute('value')
		println (result)
		return result
	}

	/*-Get Text Tooltip-*/
	public String getTextTitle(String xpath){
		driver = DriverFactory.getWebDriver()
		WebElement element = driver.findElement(By.xpath(xpath))
		String tooltip = element.getAttribute('title')
		println ("tooltip is: " +tooltip)
		return tooltip
	}

	/*-Date-*/
	@Keyword
	public void datePicker(String xpath, String value){
		driver = DriverFactory.getWebDriver()
		WebElement element = driver.findElement(By.xpath(xpath))
		WebUI.executeJavaScript("arguments[0].value='"+value+"'", Arrays.asList(element))
	}

	/*-Screen Capture-*/
	@Keyword
	public String saveScreen(int index,String filePath, String fileName) {
		driver = DriverFactory.getWebDriver()
		String srcName = ""
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(filePath + fileName + "_ER" + index +".png"));
			srcName = fileName + "_ER" + index +".png"
		} catch (IOException e) {
			e.printStackTrace();
		}
		return srcName
	}

	/*-Close Browser-*/
	@Keyword
	public void close_browser(){
		driver = DriverFactory.getWebDriver()
		driver.quit()
	}
}
