/**
 * 
 */
package automation;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author Subodh
 * @param in this part screenshot tutorial is covered. In this tes case scenario screen shot is taken with date format time if test failed and saved into specific filder
 */
public class ScreenshotOnFail {
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
	
	public void longPress() throws InterruptedException, IOException
	{
		try {
			
			TouchAction action = new TouchAction(driver);
			MobileElement el1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout[12]");
			action.longPress(el1).perform().release(); // longpress perfomed
			Thread.sleep(2000);
			boolean flag = driver.findElementByAccessibilityId("Delete").isDisplayed();
			if(flag)
			{
				System.out.println("Passed");
			}else
			{
				System.out.println("Failed");
				getScreenshot(driver);
			}
			
		}
		catch(Exception e){
			System.out.println("Failed");
			getScreenshot(driver);
		}
		
	}
	
	public void getScreenshot(AppiumDriver<MobileElement> d) throws IOException // function for screenshot
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss"); // date format defined to save on screenshot name
		Date date = new Date(); // date defined
		String fileName = sdf.format(date); // renamed file name to date
		File des = d.getScreenshotAs(OutputType.FILE); // screenshot
		//save screenshot to folder
		FileUtils.copyFile(des, new File(System.getProperty("user.dir")+ "//Screenshot//" +fileName+".png"));
		System.out.println("Screenshot captured");
	}
	
	
	public void tearDown()
	{
		driver.quit();
	}
	
	public static void main(String[] args) throws InterruptedException, IOException
	{
		ScreenshotOnFail obj = new ScreenshotOnFail();
		obj.setUp();
		obj.longPress();
		
		obj.tearDown();
	}
}
