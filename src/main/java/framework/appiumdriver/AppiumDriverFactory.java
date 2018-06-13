package framework.appiumdriver;

import framework.utility.PropertyLoader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AppiumDriverFactory {

	private static AppiumDriver<WebElement> appiumDriver;

	private AppiumDriverFactory() {
		
	}

	public static AppiumDriver<WebElement> getAppiumDriver() throws MalformedURLException {

		DesiredCapabilities desiredCaps = new DesiredCapabilities();

		String osType = PropertyLoader.loadProperty("os");
		String osVersion = PropertyLoader.loadProperty("osVersion");
		String mobileDeviceId = PropertyLoader.loadProperty("mobileDeviceId");
		String appType = PropertyLoader.loadProperty("appType");
		String appPath = PropertyLoader.loadProperty("appPath");

		if (osType.equals("android")) {
			desiredCaps.setCapability(MobileCapabilityType.PLATFORM_NAME, osType);
			desiredCaps.setCapability(MobileCapabilityType.PLATFORM_VERSION, osVersion);
			desiredCaps.setCapability(MobileCapabilityType.DEVICE_NAME, mobileDeviceId);
			
			desiredCaps.setCapability(MobileCapabilityType.APP, appPath);
			//desiredCaps.setCapability("appPackage", "com.socialnmobile.dictapps.notepad.color.note");
			//desiredCaps.setCapability("appActivity", "com.socialnmobile.colornote.activity.Main");
			
			appiumDriver = new AndroidDriver<WebElement>(
			        new URL(PropertyLoader.loadProperty("appium.server.url")), desiredCaps);

		} else if (osType.equals("ios")) {
			desiredCaps.setCapability(MobileCapabilityType.PLATFORM_NAME, osType);
		} else throw new IllegalArgumentException("Invalid mobile OS property set. Should be 'android' or 'ios'");
		
		appiumDriver.manage().timeouts().implicitlyWait(Integer.valueOf(PropertyLoader.loadProperty("implicit-timeout")), TimeUnit.SECONDS);
		
		if (appType.equals("hybrid")) {
			switchtoWebView(appiumDriver, Integer.valueOf(PropertyLoader.loadProperty("webView.appearance.timeout")));
		}

		return appiumDriver;
	}


	public static void killDriverInstance() {
		appiumDriver.quit();
	}


	private static void switchtoWebView(AppiumDriver<WebElement> driver, int webViewAppearanceTimeout) {
		try {
			Thread.sleep(webViewAppearanceTimeout * 4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Set<String> contexts = driver.getContextHandles();
		for (String context : contexts) {
			System.out.println(context);
			if (context.toLowerCase().contains("web")) {
				driver.context(context);
				break;
			}
		}
	}
}