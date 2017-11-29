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
import io.appium.java_client.android.AndroidDriver;

/**
 * @author Subodh
 *
 */
public class SwitchApp {

	/**
	 * @param In this part, the app is switched from current app, Api Demos to messageing app and verified sent sms.
	 * Sent/received sms is OTP verification SMS and here we will verifying verification code
	 */
	
	
	AppiumDriver<MobileElement> driver;
	
	public void setUp()
	{
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
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println(" Session Created ");
	}
	
	public void sendMessageAndVerify() //Message is located on ApiDemos>OS>
	{
		MobileElement OS = driver.findElementByAccessibilityId("OS");
		OS.click();
		
		MobileElement sms = driver.findElementByAccessibilityId("SMS Messaging");
		sms.click();
		
		MobileElement recipient = driver.findElementById("io.appium.android.apis:id/sms_recipient");
		recipient.sendKeys("123456789");
		
		
		MobileElement messageBody = driver.findElementById("io.appium.android.apis:id/sms_content");
		messageBody.sendKeys("DEMO OTP: 12345");  // Message is consider as OTP verification message 
		
		MobileElement send = driver.findElementByAccessibilityId("Send");
		send.click();
		
		String otpValue = switchAppAndVerifyCode();
		System.out.println("OTP Valus is :" +otpValue);
	}
	
	 public String switchAppAndVerifyCode()
	 {
		 //change app by driver.startActivity("appPackage", "app Activity");
		 ((AndroidDriver<MobileElement>) driver).startActivity("com.android.mms","com.android.mms.ui.ConversationList");
		 String getOTPValue = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout[2]/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.TextView[2]").getText().split(":")[1].trim();
		// Split is used to split messaage and to getText after :
		 return getOTPValue;
		 
	 }
	
	
	public void tearDown()
	{
		driver.quit();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwitchApp obj = new SwitchApp();
		obj.setUp();
		obj.sendMessageAndVerify();
		obj.tearDown();
	}

}
