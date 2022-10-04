package TC02_Mobile;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class MobileActions {

	private AndroidDriver<MobileElement> driver;
	 
    public MobileActions (AndroidDriver<MobileElement> driver) {
        this.driver = driver;
    }
    
    //Swipe by elements
    public void swipeByElements (MobileElement mobileElement, MobileElement mobileElement2) {
    	int startX = mobileElement.getLocation().getX() + (mobileElement.getSize().getWidth() / 2);
        int startY = mobileElement.getLocation().getY() + (mobileElement.getSize().getHeight() / 2);
 
        int endX = mobileElement2.getLocation().getX() + (mobileElement2.getSize().getWidth() / 2);
        int endY = mobileElement2.getLocation().getY() + (mobileElement2.getSize().getHeight() / 2);
        
        new TouchAction(driver)
        	.press(point(startX,startY))
            .waitAction(waitOptions(ofMillis(1000)))
            .moveTo(point(endX, endY))
            .release().perform();
    }
    
}
