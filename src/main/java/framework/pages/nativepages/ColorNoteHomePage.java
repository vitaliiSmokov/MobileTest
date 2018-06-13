package framework.pages.nativepages;

import framework.pages.Screen;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ColorNoteHomePage extends Screen {
	
	@FindBy(how = How.XPATH, using = "//android.widget.ImageButton[contains(@resource-id,'main_btn1')]")
	public WebElement addButton;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@text,'Text')]")
	public WebElement textButton;
	
	@FindBy(how = How.XPATH, using = "//android.widget.TextView[contains(@resource-id,'id/text')][1]")
	public WebElement firstNote;
	
	public ColorNoteHomePage(WebDriver appiumDriver) {
		super(appiumDriver);
	}
	
	public NewNoteEditPage openAddNote() {
		addButton.click();
		textButton.click();
		return PageFactory.initElements(appiumDriver, NewNoteEditPage.class);
	}
	
	public String getFirstNoteText() {
		return firstNote.getAttribute("text");
	}

}
