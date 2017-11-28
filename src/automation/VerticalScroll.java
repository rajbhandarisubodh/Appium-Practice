/**
 * 
 */
package automation;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sun.javafx.scene.paint.GradientUtils.Point;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author Subodh
 *
 */
public class VerticalScroll {

	private static MobileElement startElement; //define element that called on scrollusingtouchaction_ByElement function
	private static MobileElement endElement;	//define element that called on scrollusingtouchaction_ByElement function
	AppiumDriver<MobileElement> driver;
	
	public void setUp() {
		
		// Desired Capabilities defined
		
		System.out.println("Session loading....");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "5.0");
		cap.setCapability("deviceName", "Samsung Galaxy S6");
		cap.setCapability("appPackage", "io.appium.android.apis");
		cap.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
		cap.setCapability("noReset", "True");
		
		//sever stared
		
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Hurray! Session Created ");
		
	}
	
	public void TabMenu ()	// Scrolling is done on ApiDemos>Views>Tab
	{
		boolean flag = false;
		MobileElement tab = driver.findElementByAccessibilityId("Views");
		tab.click();
		
		for(int i=1; i<=20; i++)
		{
			try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			driver.findElementByAccessibilityId("Tabs").click();
			}
			catch(Exception e) 
			{
				verticalScrollUsingSwipe();
			}
		}
		flag = driver.findElementByAccessibilityId("1. Content By Id").isDisplayed();
		
		if(flag)
		{
			System.out.println("Passed");
		}
		else {
			System.out.println("Failed");
		}
	}
	
	
	public void verticalScrollUsingSwipe()  // using driver.swipe() method
	{
		Dimension dim =  driver.manage().window().getSize(); //get size of device
		int height = dim.getHeight();  //device height
		int width = dim.getWidth();		// device width
		int x = width/2;				// x -axis remain constant for vertical scroll
		
		int startY = (int) (height*0.8); 	// start position of y axis
		int endY = (int) (height*0.20);		// end position of y axis 
		
		driver.swipe(x, startY, x, endY, 500);   //swipe method
				
	}

	
//	public void scrollUsingTouchAction_ByElements(MobileElement startElement, MobileElement endElement) // using TouchAction
//	{
//		TouchAction actions = new TouchAction(driver);
//		actions.press(startElement).waitAction().moveTo(endElement).release().perform();
//		
//	}
//	
//	public void element()
//	{
//		MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("Views");
//		el1.click();
//		
//		MobileElement animation = driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Animation\"]");
//				MobileElement gallery = driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Gallery\"]");
//				scrollUsingTouchAction_ByElements(animation, gallery);
//	}
//	
//	public void scrollUsingTouchAction_ByCoordinate() //scrolling by using touch action using coordinate
//	{
//		MobileElement gallery = driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Gallery\"]");
//		org.openqa.selenium.Point point = gallery.getLocation(); //location of mobile element
//		int startX = point.x;
//		int endX = point.x;
//		
//		Dimension dim = driver.manage().window().getSize();
//		int height = dim.height;
//		int width = dim.width;
//		
//		int startY = (int) (height*0.8);
//		int endY = (int) (height*0.2);
//		
//				TouchAction actions = new TouchAction(driver);
//		actions.press(startX, startY).waitAction().moveTo(endX, endY).release().perform();
//				
//	}
//	
	
	public void tearDown()
	{
		driver.quit();
	}
	
	
	public static void main(String[] args) {
		VerticalScroll obj = new VerticalScroll();
		obj.setUp();
		//obj.element();
		//obj.scrollUsingTouchAction_ByElements(startElement, endElement);
		//obj.scrollUsingTouchAction_ByCoordinate();
		obj.verticalScrollUsingSwipe();
		obj.TabMenu();
		obj.tearDown();
	}

}
