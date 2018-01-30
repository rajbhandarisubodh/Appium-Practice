package automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.Table.Cell;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
/**
 * 
 * @author Subodh
 * this practice is based on tutorial http://learn-automation.com/read-and-write-excel-files-in-selenium/
 *
 */

public class ReadAndWriteExcelFile {

	AppiumDriver<MobileElement> driver;
	
	String path;

	//Setup device and server
	public void setUp() {
		
		System.out.println("Session Creating........  ");
		path = System.getProperty("user.dir");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("platformName","Android");
		cap.setCapability("platformVersion", "6.0");
		cap.setCapability("deviceName", "Samsung Galaxy S6");
		cap.setCapability("app", path+"//app//ApiDemos.apk");
		//cap.setCapability("appPackage", "io.appium.android.apis");
		//cap.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
		
		cap.setCapability("udid", "10160a5273cd3302"); //device udid
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
	
	
	public void excelFileReadWrite()
	{
		try {
			// Specify the path of file
		File src = new File("E:\\eclipse-java-oxygen-M5-NEW\\Text_Excel_Files\\First test practice.xlsx");
		
		
		 // load file
		FileInputStream fis = new FileInputStream(src);
		
		// Load workbook
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		
		// Load sheet- Here we are loading first sheetonly
		XSSFSheet sh1 = wb.getSheetAt(0);
		
		
		String cell = sh1.getRow(0).getCell(0).getStringCellValue();
		
		//to read
		
		// getRow() specify which row we want to read.
		 
		  // and getCell() specify which column to read.
		  // getStringCellValue() specify that we are reading String data.
		
	
		System.out.println("Email content" +cell);
		System.out.println(sh1.getRow(0).getCell(1).getStringCellValue());
		
		System.out.println("File read done");
		//to write
		// here createCell will create column
		 
		// and setCellvalue will set the value
		
		sh1.getRow(0).createCell(3).setCellValue("CC Mobile number");
		sh1.getRow(0).createCell(4).setCellValue("Mobile Number");
		
		// here we need to specify where you want to save file
		
		FileOutputStream fos = new FileOutputStream(new File("E:\\\\eclipse-java-oxygen-M5-NEW\\\\Text_Excel_Files\\\\test.xlsx"));
		
		System.out.println("File write done");
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		
	}
	
	public void tearDown()
	{
		driver.quit(); //quit session
	}
	
	
	public static void main(String[] args) {
		
		ReadAndWriteExcelFile obj = new ReadAndWriteExcelFile();
		obj.setUp();
		obj.excelFileReadWrite();
		obj.tearDown();

	}
	
	

}
