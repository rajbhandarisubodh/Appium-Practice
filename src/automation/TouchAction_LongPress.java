/**
 * 
 */
package automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author Subodh
 *@param In this part longpress action is performed supped to delete message,etc
 */
public class TouchAction_LongPress {
	
	AppiumDriver<MobileElement> driver;
	
	public void setUp()
	{
		System.out.println("Session Creating........  ");
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName","Android");
		cap.setCapability("platformVersion", "5.0");
		cap.setCapability("deviceName", "Samsung Galaxy S6");
		cap.setCapability("appPackage", "com.android.mms");
		cap.setCapability("appActivity", "com.android.mms.ui.ConversationList");
		cap.setCapability("noReset", "True");
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println(" Session Created ");
	}
	
	public void longPress() throws InterruptedException
	{
		TouchAction action = new TouchAction(driver);
		MobileElement el1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout[1]");
		action.longPress(el1).perform().release(); // longpress perfomed
		Thread.sleep(2000);
		boolean flag = driver.findElementByAccessibilityId("Delete").isDisplayed();
		if(flag)
		{
			System.out.println("Passed");
		}else
		{
			System.out.println("Failed");
		}
		
		
	}
	
	public void tearDown()
	{
		driver.quit();
	}
	
	public static void main(String[] args)
	{
		TouchAction_LongPress obj = new TouchAction_LongPress();
		obj.setUp();
		try {
			obj.longPress();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		obj.tearDown();
	}
}
