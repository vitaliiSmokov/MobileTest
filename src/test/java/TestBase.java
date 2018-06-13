import framework.appiumdriver.AppiumDriverFactory;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;

public class TestBase {

	protected AppiumDriver<WebElement> appiumDriver;

	@BeforeMethod
	public void setup() throws MalformedURLException {
		appiumDriver = AppiumDriverFactory.getAppiumDriver();
		try {
			appiumDriver.resetApp();
		} catch(Exception e) { }
	}

	@AfterMethod
	public void tearDown() {
		if (appiumDriver != null) {
			AppiumDriverFactory.killDriverInstance();
		}
	}
}