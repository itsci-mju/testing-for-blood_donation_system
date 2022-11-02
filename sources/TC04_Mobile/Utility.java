package TC04_Mobile;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
	
	public void edit_input_firstname(String id, String fText) {
		driver.findElement(By.id(id)).clear();
		driver.findElement(By.id(id)).sendKeys(fText);
		}
	
	public void edit_input_lastname(String id, String fText) {
		driver.findElement(By.id(id)).clear();
		driver.findElement(By.id(id)).sendKeys(fText);
		}
	
	public void edit_input_birthday(String id, String id2, String xpath, String id3, String id4, String fText) throws ParseException {
		MobileActions MBAction = new MobileActions(driver);
		String Months[] = {"มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน",
				"พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม",
				"กันยายน", "ตุลาคม", "พฤษจิกายน", "ธันวาคม"};
		
		String IP_Birthday = fText;
		System.out.println("IP_Birthday : " + IP_Birthday);
		boolean flag = false;
		
		driver.findElement(By.id(id)).click();

		String[] arrA = IP_Birthday.split("/");
		
		String SP_Year = arrA[2];
		System.out.println("Split Year : " + SP_Year);
		
		do {
		List <MobileElement> years = driver.findElements(By.id(id2));
				for(MobileElement y : years) {
					System.out.println( "Years : " + y.getText());
					if(SP_Year.equals(y.getText())) {
						flag = true;
						y.click();
						break;
					}
				}
				if(flag== false) {
					if(SP_Year.compareTo(years.get(0).getText()) <0){
						MBAction.swipeByElements(years.get(0), years.get(years.size()-1));
					}else {
						MBAction.swipeByElements(years.get(years.size()-1), years.get(0));
					}
				}
		}while(flag== false);
		
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(IP_Birthday);
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", new Locale("th", "TH"));
		String date_format = formatter.format(date);
		System.out.println("date_format : " + date_format);
		
		String[] arrB = date_format.split(" ");
		String SP_month = arrB[1];
		System.out.println("Split Month : " + SP_month);
		
		ArrayList <MobileElement> date_picker = (ArrayList<MobileElement>) driver.findElements(By.xpath(xpath));
		String get_text_date_picker = date_picker.get(0).getAttribute("contentDescription");
		System.out.println("date_picker : " + get_text_date_picker);
		
		String[] arrC = get_text_date_picker.split(" ");
		String SP_month2 = arrC[1];
		
		System.out.println("Split Month2 : " + SP_month2);
		
		int month_index = 0;
		int month_index2 = 0;
		int sum = 0;
		int i = 0;
		for (i=0; i<Months.length; i++) {
			if (Months[i].equals(SP_month)) {
				month_index = i+1;
		    }
		    if (Months[i].equals(SP_month2)) {
		    	month_index2 = i+1;
		    }
		}
		System.out.println("SP_month Index of " + month_index);
		System.out.println("SP_month2 Index2 of " + month_index2);
		
		if(!SP_month.equals(SP_month2)) {
			if(month_index < month_index2) {
				sum = month_index2 - month_index;
				System.out.println("sum index month : " + sum);
				
				for(i=0; i<sum; i++) {
					driver.findElement(By.id(id3)).click();
					System.out.println("prev of : เดือนที่แล้ว");
					}
			
			String SP_Day = arrA[0];
			System.out.println("SP_Day " + SP_Day);
			
			List <MobileElement> date_picker2 = driver.findElements(By.xpath(xpath));
			for(MobileElement ap : date_picker2) {
				System.out.println( "mont_picker : " + ap.getText());
				
				if(SP_Day.equals(ap.getText())) {
					ap.click();
					break;
					}
				}
			}else {
				sum = month_index - month_index2;
				System.out.println("sum index month : " + sum);
				
				for(i=0; i<sum; i++) {
					driver.findElementById(id4).click();
					System.out.println("next of : เดือนหน้า");
				}
			}
		}
	}
	
	public void edit_input_address(String id, String fText) {
		driver.findElement(By.id(id)).clear();
		driver.findElement(By.id(id)).sendKeys(fText);
		}
	
	public void edit_input_tel(String id, String fText) {
		driver.findElement(By.id(id)).clear();
		driver.findElement(By.id(id)).sendKeys(fText);
		}
	
	public void edit_input_career(String id, String fText) {
		driver.findElement(By.id(id)).clear();
		driver.findElement(By.id(id)).sendKeys(fText);
		}
	
	public void edit_input_email(String id, String fText) {
		driver.findElement(By.id(id)).clear();
		driver.findElement(By.id(id)).sendKeys(fText);
		}

	public void edit_type_gender(String xpath, String fText) {
		
		List <MobileElement> type_gender = driver.findElements(By.xpath(xpath));
		
		for(MobileElement ts : type_gender) {
			System.out.println( "fText : " + fText);
			System.out.println( "type_gender : " + ts.getText());
			
			if(fText.equals(ts.getText())) {
				ts.click();
				break;
				}else {
					
					}
			}
	} 
	
	public void button_click(String id, String id2) {
		try {
			driver.findElement(By.id(id)).click();
		}catch(Exception e) {
			driver.findElement(By.xpath(id2)).click();
		}
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
			FileUtils.copyFile(scrFile, new File("C:\\Users\\Asus\\Desktop\\Project_Test\\TC04_Edit-Donor-Profile\\Img\\" + "" + index + ".png"));
			nameimg = index;
			} catch (IOException e) {
				e.printStackTrace();
				}
		return nameimg;
		
	}
}	  
