/**
 * 
 */
package automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author Subodh
 * @param Here, device will rotated to landscape or portrait mode
 *
 */
public class DeviceOrientation {

	AppiumDriver<MobileElement> driver;

	//Setup device and server
	public void setUp() {
		
		System.out.println("Session Creating........  ");
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName","Android");
		cap.setCapability("platformVersion", "5.0");
		cap.setCapability("deviceName", "Samsung Galaxy S6");
		//cap.setCapability("app", path+"//app//ApiDemos.apk");
		cap.setCapability("appPackage", "io.appium.android.apis");
		cap.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
		cap.setCapability("noReset", "True");
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println("Hurray! Session Created ");

	}
		
	public void testRotate() throws InterruptedException
	{
		if (driver.getOrientation().equals("LANDSCAPE"))
				{
					screenRotation("PORTRAIT");
				}
		else
		{
			screenRotation("LANDSCAPE");
		}
	}
	
			
		public void screenRotation(String modType) throws InterruptedException //Device orientation method
		{
			ScreenOrientation currentOrientation = driver.getOrientation(); // get current orientation
			System.out.println("Current orientation: " + currentOrientation);
				
			if(modType.equalsIgnoreCase("LANDSCAPE"))
			{
			driver.rotate(ScreenOrientation.LANDSCAPE); // rotate to landscape mode
			Thread.sleep(2000);
			currentOrientation = driver.getOrientation();
			System.out.println("Current orientation: " + currentOrientation);
			}
			else if(modType.equalsIgnoreCase("PORTRAIT"))
			{
			driver.rotate(ScreenOrientation.PORTRAIT); // rotate to portrait mode
			currentOrientation = driver.getOrientation();
			
			System.out.println("Current orientation: " + currentOrientation);
			Thread.sleep(2000);
			}
		}
		
		public void tearDown()
{
	driver.quit();
	
}
		public static void main(String[] args) throws InterruptedException
		{
			DeviceOrientation obj = new DeviceOrientation();
			obj.setUp();
			obj.testRotate();
			obj.tearDown();
		}


}


