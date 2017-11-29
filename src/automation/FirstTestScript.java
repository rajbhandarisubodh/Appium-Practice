package automation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
/**
 * 
 * @author Subodh
 *
 */

public class FirstTestScript {

	AppiumDriver<MobileElement> driver;
	
	String path;

	//Setup device and server
	public void setUp() {
		
		System.out.println("Session Creating........  ");
		path = System.getProperty("user.dir");
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
	
	
	public void validatText() {
		driver.findElementByAccessibilityId("Accessibility").click();
		String Text = driver.findElementByAccessibilityId("Accessibility Node Provider").getText();
		
		if(Text.equalsIgnoreCase("Accessibility Node Provider"))
		{
			System.out.println("Text validated");
		}
		else
		{
			System.out.println("Text not found");
		}
	}
	
	public void tearDown()
	{
		driver.quit(); //quit session
	}
	
	
	public static void main(String[] args) {
		
		FirstTestScript obj = new FirstTestScript();
		obj.setUp();
		obj.validatText();

	}
	
	

}
