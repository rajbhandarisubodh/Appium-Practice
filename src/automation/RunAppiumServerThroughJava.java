package automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import sun.print.resources.serviceui;

/**
 * 
 * @author Subodh
 *
 */
public class RunAppiumServerThroughJava {

	AppiumDriver<MobileElement> driver;
	AppiumDriverLocalService service; // declaring service is important
	String path;
	
	public void setUp()
	{
		 service = AppiumDriverLocalService.buildDefaultService();
		service.start();
		
		path = System.getProperty("user.dir");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName", "Android");
		//cap.setCapability("platformVersion", "5.0");
		cap.setCapability("deviceName", "Samsung Galaxy S6");
		cap.setCapability("app", path+"//app/ApiDemos.apk");
		driver = new AndroidDriver<MobileElement>(service.getUrl(), cap);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
		service.stop();
	}
	
	
	public static void main(String[] args) {
		
		RunAppiumServerThroughJava obj = new RunAppiumServerThroughJava();
		obj.setUp();
		obj.validatText();

	}
}
