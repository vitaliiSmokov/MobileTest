package framework.pages.nativepages;

import framework.pages.Screen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class NewNoteEditPage extends Screen {

	@FindBy(how = How.XPATH, using = "//android.widget.EditText[contains(@resource-id,'edit_note')]")
	public WebElement editField;

	@FindBy(how = How.XPATH, using = "//android.widget.ImageButton[contains(@resource-id,'color_btn')]")
	public WebElement colorButton;

	@FindBy(how = How.XPATH, using = "//android.widget.ImageButton[contains(@resource-id,'btn5')]")
	public WebElement colorButtonBlue;
	
	@FindBy(how = How.XPATH, using = "//android.widget.ImageButton[contains(@resource-id,'edit_btn')]")
	public WebElement pencilButton;

	public NewNoteEditPage(WebDriver appiumDriver) {
		super(appiumDriver);
	}

	@SuppressWarnings("unchecked")
	public ColorNoteHomePage addNewNoteWithText(String text)
			throws InterruptedException {
		
		editField.click();
		editField.sendKeys(text);
		((AndroidDriver<WebElement>) appiumDriver).hideKeyboard();
		colorButton.click();
		colorButtonBlue.click();
		/*if (appiumDriver instanceof AndroidDriver) {
			((AndroidDriver<WebElement>) appiumDriver).pressKeyCode(AndroidKeyCode.BACK);
		}*/
		
		((AndroidDriver<WebElement>) appiumDriver).pressKeyCode(AndroidKeyCode.BACK);
		//Thread.sleep(2000);
		Wait<AndroidDriver<WebElement>> wait = new FluentWait<AndroidDriver<WebElement>>
		((AndroidDriver<WebElement>) getWebDriver())
			.withTimeout(30, TimeUnit.SECONDS)
			.pollingEvery(2, TimeUnit.SECONDS);
			
		wait.until(wd -> pencilButton.isEnabled());
		
		((AndroidDriver<WebElement>) appiumDriver).pressKeyCode(AndroidKeyCode.BACK);
		
		return PageFactory.initElements(appiumDriver, ColorNoteHomePage.class);
	}

}
