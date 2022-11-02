package TC03_Mobile;
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
	private AndroidDriver<MobileElement> driver = null;
	
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
	
	public String get_text(String id, String id2) {
		try {
			//		String text1 = driver.findElementById(id).getText();
			//		String text2 = driver.findElementById(id2).getText();
			
					String g_text1 = "";
					String text1 = "";
					String text2 = "";
					
					text1 = driver.findElementById(id).getText();
					System.out.println("text1 = " + text1);
					
					text2 = driver.findElementById(id2).getText();
					System.out.println("text2 = " + text2);
					
					if(" ".equals(text2)) {
						g_text1 =  text1;
					}else {
						g_text1 =  text1 + " " + text2;
					}
					
					System.out.println("g_text1 = " + g_text1);	
					return g_text1;
					
				}catch(Exception e){
					
					System.out.println(e);	
					return null;
				}
		
	}
	
	public void input_idcard(String id, String fText) {
		driver.findElement(By.id(id)).clear();
		driver.findElement(By.id(id)).sendKeys(fText);
		}
	
	public void input_password(String id, String fText) {
		driver.findElement(By.id(id)).clear();
		driver.findElement(By.id(id)).sendKeys(fText);
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
			FileUtils.copyFile(scrFile, new File("C:\\Users\\Asus\\Desktop\\Project_Test\\TC03_Login-By-Donor\\Img\\" + "" + index + ".png"));
			nameimg = index;
			} catch (IOException e) {
				e.printStackTrace();
				}
		return nameimg;
		
	}
}	  
