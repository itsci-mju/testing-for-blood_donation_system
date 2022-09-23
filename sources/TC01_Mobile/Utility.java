package TC01_Mobile;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

public class Utility {
	AndroidDriver<MobileElement> driver = null;
	
	public void application_open(String fURL) throws InterruptedException, MalformedURLException {
		if (fURL == null){
			File app = new File("C:\\Users\\Asus\\Desktop\\Project_Test\\Application\\app-debug.apk");
			
			DesiredCapabilities cap = DesiredCapabilities.android();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy_J8_Thipok");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
			cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			cap.setCapability(MobileCapabilityType.FULL_RESET, false);
			
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
		else {
			File app = new File(fURL);
			
			DesiredCapabilities cap = DesiredCapabilities.android();
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy_J8_Thipok");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
			cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
			cap.setCapability(MobileCapabilityType.FULL_RESET, false);
			
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
			
		driver.get(fURL);
		System.out.println(fURL);
		
	}
	
	public String get_text(String id) {
		String text = "";
		text = driver.findElementById(id).getText();
		System.out.println("get text : " + text);
		return 	driver.findElement(By.id(id)).getText();
		
//		String txt1 = "";
//		String txt2 = "";
//		txt1 = driver.findElementById(id1).getText();
//		txt2 = driver.findElementById(id2).getText();
//		if(txt1 == null) {
//			System.out.println("txt1 : " + txt1);
//			return 	driver.findElement(By.id(id1)).getText();
//			
//		}else {
//			System.out.println("txt1 : " + txt2);
//			return 	driver.findElement(By.id(id2)).getText();
//			
//		}
		
	}
	
	public void keyword_search(String id, String fText) {
		driver.findElement(By.id(id)).clear();
		driver.findElement(By.id(id)).sendKeys(fText);
		}

	public void type_search(String xpath, String fText) { 
		List <MobileElement> type_search = driver.findElements(By.xpath(xpath));
		if(fText.equals("วันที่")) {
			type_search.get(0).click();
		}else if(fText.equals("สถานที่")){
			type_search.get(1).click();
		}else {
			type_search.get(0).click();
		}
//		for(MobileElement ts : type_search) {
//			System.out.println( "type_search : " + ts.getText());
//			if(ts.getText().equals(fText)) {
//				ts.click();
//				break;
//			}else {
//				ts.click();
//			}
//		}
	}
	
	public void button_click(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	  public void application_close() {
		  //Thread.sleep(1000);
		  //driver.quit();//จบการทำงาน
		  driver.close();
		  //driver.closeApp();
	}
	  public String saveScreen(String index) {
		 String nameimg = "";
		 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 try {
			FileUtils.copyFile(scrFile, new File("C:\\Users\\Asus\\Desktop\\Project_Test\\TC01_Search-Activity-Donation\\" + "" + index + ".png"));
			nameimg = index;
		 } catch (IOException e) {
			e.printStackTrace();
		}
		 return nameimg;
	}
}	  
