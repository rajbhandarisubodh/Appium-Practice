/**
 * 
 */
package automation;

import java.util.Set;
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
/**
 * @author user
 *
 */
/**
 * @author user
 *
 */
/**
 * @author user
 *
 */
public class HybridWebApp_GetContext {

	/**
	 * @param Switching app context using Context. In this case, native app is switched 
	 * to hybrid app
	 */
	
	AppiumDriver<MobileElement> driver;
	AppiumDriverLocalService service;
	
	public void setUp()
	{
		System.out.println("Appium Server Strating");
		
		service = AppiumDriverLocalService.buildDefaultService();
		service.start();
		
		System.out.println("Appium Server Stated");
		
		System.out.println("Session loading....");
	
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
		
		System.out.println("Session Created");
	}
	
	
	public void webView() // Webview is redirected which is located at View>Webview
	{
		System.out.println("First Current Context: " +driver.getContext());
		MobileElement el1 = (MobileElement) driver.findElementByAccessibilityId("Views");
		el1.click();
		//scroll to find webview button
		for(int i = 0; i<=20; i++)
		{
			try
			{
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
				MobileElement el2 = (MobileElement) driver.findElementByAccessibilityId("WebView");
				el2.click();
				break;
			}
			catch(Exception e)
			{
				verticalSwipe();
			}
		}
		switchContext("WEBVIEW");
		//String webViewText = driver.findElementByXPath("//a[text()='Hello World! - 1']").getText();
		String webViewText = driver.findElementByXPath("/html/body/a").getText();

		System.out.println(webViewText);
	}
	
	/**
	 * 
	 * @param Context switching function
	 */
	
	public void switchContext(String context)
	{
		System.out.println("Before Switching: " +driver.getContext());
		Set<String> con = driver.getContextHandles();
		for(String c : con)
		{
			System.out.println("Available Conntext:" +c);
			if(c.contains(context))
			{
				driver.context(c);
				break;
			}
		}
		System.out.println("After Switching : " + driver.getContext());
	}
	
	public void verticalSwipe()
	{
		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		
		//For vertical swipe x is constant
		
		int x = width/2;
		int startY = (int) (height*0.80);
		int endY = (int) (height*0.20);
		
		driver.swipe(x, startY, x, endY, 500);
		
	}
	
	
	
	
	public void tearDown()
	{
		driver.quit();
		service.stop();
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HybridWebApp_GetContext obj = new HybridWebApp_GetContext();
		obj.setUp();
		obj.webView();
		obj.tearDown();

	}

}
