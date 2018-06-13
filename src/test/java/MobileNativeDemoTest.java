import framework.pages.nativepages.ColorNoteHomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileNativeDemoTest extends TestBase {

	@Test
	public void mobileNativeDemoTest1() throws InterruptedException {
		ColorNoteHomePage colorNoteHomePage = PageFactory.initElements(
				appiumDriver, ColorNoteHomePage.class);
		
		String note1 = "This is new note!";
		colorNoteHomePage = colorNoteHomePage.openAddNote().addNewNoteWithText(note1);
		Assert.assertEquals(colorNoteHomePage.getFirstNoteText(), note1);
	}
}