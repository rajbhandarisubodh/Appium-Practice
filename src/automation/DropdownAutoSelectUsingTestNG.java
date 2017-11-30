/**
 * 
 */
package automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

/**
 * @author Subodh
 *@param testng is invoked
 */
public class DropdownAutoSelectUsingTestNG {
	/**
	 * @param This part is related to selection of autocomplete dropdown list
	 */
	
	AndroidDriver<MobileElement> driver;
	
	@BeforeClass
	public void setUp() {
		
		System.out.println("Session Creating........  ");
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName","Android");
		cap.setCapability("platformVersion", "5.0");
		cap.setCapability("deviceName", "Samsung Galaxy S6");
		
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
	@Test
	public void autoComplete() throws InterruptedException
	{
		MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("Views");
		el1.click();
		MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("Auto Complete");
		el2.click();
		MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("1. Screen Top");
		el3.click();
		MobileElement el4 = (MobileElement) driver.findElementById("io.appium.android.apis:id/edit");
		el4.sendKeys("India");
		Thread.sleep(2000);
		
		//android key code method
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_PAGE_DOWN);
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_PAGE_DOWN);
		driver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		//Note: driver must be define using AndroidDriver i.e AndroidDriver<MobileElement> driver;
		
		String text = driver.findElementById("io.appium.android.apis:id/edit").getText();
		if (text.equals("India"))
		{
			System.out.println("Passed");
		}
		else
		{
			System.out.println("Failed");
		}
		
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit(); //quit session
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		// TODO Auto-generated method stub
		
		DropdownAutoSelect obj = new DropdownAutoSelect();
		obj.setUp();
		obj.autoComplete();
		obj.tearDown();
		

		//Setup device and server
		
		

	}
}
