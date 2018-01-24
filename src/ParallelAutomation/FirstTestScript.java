/**
 * 
 */
package ParallelAutomation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author Subodh
 *@param in this code, automation code is run parallely on two device before staring please read prerequisites
 *
 *PREREQUISITES
 *Each device need separate appium server with different port, Bootstrap port. Eg: if two device is needed then two appium server
 *For 1st Server Address – 0.0.0.0
Server Port – 4723
Bootstrap Port – 100  // any valid port number (valid port number is any number between 0 and 65535) in Bootstrap Port field. 
Allow Session Override checkbox – Tick
 *For 2nd
 *Server Address – 0.0.0.0
Server Port – 5000
Bootstrap Port – 200
Allow Session Override checkbox – Tick and start server
 *
 *
 *
 */
public class FirstTestScript {
		AppiumDriver<MobileElement> driver;
	
	String path;
	
//	String port;
//	String udid;

	//Setup device and server
	public void setUp(String portNumber, String deviceid) {
		
		System.out.println("Session Creating........  ");
		path = System.getProperty("user.dir");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName","Android");
		//cap.setCapability("platformVersion", "5.0");
		cap.setCapability("deviceName", "Samsung Galaxy ");
		cap.setCapability("app", path+"//app//ApiDemos.apk");
//		cap.setCapability("appPackage", "io.appium.android.apis");
//		cap.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
		cap.setCapability("udid", deviceid); //device udid
		cap.setCapability("noReset", "True");
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:"+ portNumber+"/wd/hub"), cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Hurray! Session Created ");
	}
	
//	public void device1 ()
//	{
//		port = "4723";
//		udid = "192.168.9.101:5555";
//		setUp(port, udid);
//		
//	}
//	
//	public void device2 ()
//	{
//		port = "4723";
//		udid = "192.168.9.101:5555";
//		setUp(port, udid);
//		
//	}
	
	
	
	
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

	
	public void tearDown()
	{
		driver.quit(); //quit session
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		FirstTestScript obj = new FirstTestScript();
		obj.setUp("4723", "192.168.9.101:5555");
		Thread.sleep(2000);
		
		obj.TabMenu();
		obj.horizontallyScrollingTabs();
		obj.setUp("4724", "192.168.9.102:5555");
		obj.TabMenu();
		obj.horizontallyScrollingTabs();
		obj.tearDown();

	}

}
