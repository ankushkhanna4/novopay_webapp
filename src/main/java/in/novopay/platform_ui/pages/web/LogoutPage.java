package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import in.novopay.platform_ui.utils.BasePage;

public class LogoutPage extends BasePage {

	public LogoutPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(xpath = "//span[contains(text(),'Logout')]")
	WebElement logout;

	@FindBy(id = "regMobileNumber")
	WebElement mobNum;

	public void logout(Map<String, String> dataMap) throws AWTException {

		waitUntilElementIsClickableAndClickTheElement(logout);
		System.out.println("logging out");
		waitUntilElementIsVisible(mobNum);
		System.out.println("Logged out successfully");
	}
}
