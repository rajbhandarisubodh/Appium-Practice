/**
 * 
 */
package automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

/**
 * @author Subodh
 *
 */


//In this code practice server is started using java code and have similar code for vetical swipe of class VerticalScroll
public class HorizontalScroll {

	AppiumDriver<MobileElement> driver;
	AppiumDriverLocalService service;	//local service to run appium
	
	public void setUp() 
	{
		
		System.out.println("Appium Server Starting");
		service = AppiumDriverLocalService.buildDefaultService();
		service.start();
		
		System.out.println("Appium Server Stated");
		
		System.out.println("Session loading....");
		
		//Define Desired Capabilites
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		//cap.setCapability("platformVersion", "5.0");
		cap.setCapability("deviceName", "Samsung Galaxy S6");
		//cap.setCapability("app", path+"//app/ApiDemos.apk");
		cap.setCapability("appPackage", "io.appium.android.apis");
		cap.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
		cap.setCapability("noReset", "True");
		
		driver = new AndroidDriver<MobileElement>(service.getUrl(), cap);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	public void teadDown() {
		driver.quit();
		service.stop();
		System.out.println("Appium Server Stopped");
	}
	
	public void TabMenu ()	// Scrolling is done on ApiDemos>Views>Tab
	{
		
		MobileElement tab = driver.findElementByAccessibilityId("Views");
		tab.click();
		for(int i = 0; i<= 20; i++) 
		{
			try 
			{
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				driver.findElementByAccessibilityId("Tabs").click();
				break;
			}
			catch(Exception e)
			{
				System.out.println("exception: " + i);
				verticalScrollUsingSwipe();
			}
		}
		driver.findElementByAccessibilityId("5. Scrollable").click();		
	}
	
	public void horizontallyScrollingTabs(){ //Tab located on ApiDemos>Views>Tab>Scrollable
		
		boolean flag = false;
		for(int i=0;i<=20; i++) 
		{
			try
			{
				flag = driver.findElementByXPath("//*[@text = 'Tab 28']").isDisplayed();
				flag = true;
				break;
			}
			catch(Exception e)
			{
				horizontalScrollUsingSwipe();
			}
		}
		
	}
	
	
	public void horizontalScrollUsingSwipe() {
		
		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		
		//int y = width/2;
		int y = (int) (height*0.2);
		
		int startX = (int)	(width* 0.75);
		int endX = (int)	(width*0.35);
	
		
		System.out.println("\n" +"Height of device: "+ height);
		System.out.println("Width of device: "+ width);
		System.out.println("y-axis of device: "+ y);
		System.out.println("start x: "+ startX);
		System.out.println("end x: "+ endX);
		
		driver.swipe(startX, y, endX, y, 500);
	}
	
	public void verticalScrollUsingSwipe()  // using driver.swipe() method
	{
		Dimension dim =  driver.manage().window().getSize(); //get size of device
		int height = dim.getHeight();  //device height
		
		int width = dim.getWidth();		// device width
		int x = width/2;				// x -axis remain constant for vertical scroll
		
		int startY = (int) (height*0.8); 	// start position of y axis
		int endY = (int) (height*0.20);		// end position of y axis 
		
		
		System.out.println("\n" +"Height of device: "+ height);
		System.out.println("Width of device: "+ width);
		System.out.println("x-axis of device: "+ x);
		System.out.println("start y: "+ startY);
		System.out.println("end y: "+ endY);
		
		driver.swipe(x, startY, x, endY, 500);   //swipe method
				
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HorizontalScroll obj = new HorizontalScroll();
		obj.setUp();
		obj.TabMenu();
		obj.horizontallyScrollingTabs();
		obj.teadDown();
	}

}
