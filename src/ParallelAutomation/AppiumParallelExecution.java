package ParallelAutomation;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppiumParallelExecution implements Runnable {
	
		   //Parallel Exection using Page factory model
	    String port;
	    String udid;
	    String path;

	    public AppiumParallelExecution(String portNumber, String udid) {
	        this.port = portNumber;
	        this.udid = udid;
	    }
	   
	    @AndroidFindBy(accessibility="Accessibility")
	    private MobileElement accessibility;
	    
	    AppiumDriver<WebElement> driver;
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	   
	   
	    private void openAppAndPerformSomeActions() {
	    	System.out.println("Session Creating........  ");
			path = System.getProperty("user.dir");
	        capabilities.setCapability("deviceName", "My Mobile Device");
	        capabilities.setCapability("udid", udid);
	        capabilities.setCapability("platformVersion", "6.0.1");
	        capabilities.setCapability("platformName", "Android");
	        capabilities.setCapability("app", path+"//app//ApiDemos.apk");
	        try {
	            driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:" + port + "/wd/hub"), capabilities);
	            Thread.sleep(10000);
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	       
	        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	       
	        accessibility.click();
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
	   
	    public static void main(String args[]) {
	        Runnable r1 = new AppiumParallelExecution("4723", "192.168.9.101:5555"); //device id of first mobile device
	        Runnable r2 = new AppiumParallelExecution("4724", "192.168.9.102:5555"); //device id of second mobile device
	        new Thread(r1).start();
	        new Thread(r2).start();
	       
	    }
	 
	    @Override
	    public void run() {
	        openAppAndPerformSomeActions();
	    }
	}

