
# Appium-Practice
This is the repository of Appium training and practices using JAVA for app APIdemos, which is available on app folder.
In this appium, following library are used:
  > java version "1.8.0_144"
  > java-client-4.1.2.jar
  > selenium-server-standalone-2.4.8.2.jar
  > commom-validator-1.6.jar
  > selenium-server-java-library-3.5.2

  Tips & Tricks:
  1. Two way to find appPackage and appActivity (follow this - http://www.automationtestinghub.com/apppackage-and-appactivity-name/ )
Method 1: Using ‘mCurrentFocus’ or ‘mFocusedApp’ in Command Prompt

Step 1: Unlock your mobile device and connect it to your computer using USB cable

Step 2: Open Command Prompt and run ‘adb devices’ command. We are running this command to just make sure that your mobile is properly connected.

Step 3: Once you run ‘adb devices’ command, you should see that it displays the list of attached devices as shown in the below image (the actual device name that you see would be different based on what mobile phone you use) –
finding appPackage and appActivity for android devices

Step 4: Run ‘adb shell’ command. After running this command, the command prompt should look something like this –
finding appPackage and appActivity using shell command

Step 5: Now in your mobile phone, open the app for which you want to find the appPackage and appActivity. Since we are doing this for Play Store, hence we will open “Play Store” on our mobile phone.

playstore application launch

Note: Please make sure that you open the app before going to the next step, because command in the next step would provide the details only for the app which is currently in focus.

Step 6: Now run this command: dumpsys window windows | grep -E ‘mCurrentFocus’

Step 7: The above command would display the details of the app which is currently in focus. From that, you can figure out the appPackage and appActivity name as per the below image –

command mCurrentFocus to find the appPackage and appActivity

appPackage starts with com. and ends before backshash (/). So from the above image, appPackage name is – com.android.vending

appActivity starts after the backslash (/) and goes till the end. From the above image, appActivity name is – com.google.android.finsky.activities.MainActivity

Step 8: There is one more similar command that provides the appPackage and appActivity name. This command adds some additional details before and after the package name & activity name, but you can still try it out just to verify that the results from the above command are same. This command is – dumpsys window windows | grep -E ‘mFocusedApp’ and the output of this command is shown below –

using command mFocusedApp to find appPackage and appActivity


2. How to use Ui Automator Viewer to inspect element (follow this tutorial for complete knowledge to locate element - http://toolsqa.com/mobile-automation/appium/findelement-and-findelements-commands/ 
, webapp element: http://toolsqa.com/mobile-automation/appium/inspect-elements-of-mobile-web-application/ 
And webView http://toolsqa.com/mobile-automation/appium/how-to-inspect-and-automate-webview-in-hybrid-app/ )
  