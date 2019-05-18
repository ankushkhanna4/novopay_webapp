package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.Log;

public class LogoutPage extends BasePage {
	WebDriverWait wait = new WebDriverWait(wdriver, 15);

	public LogoutPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(xpath = "//a[1]/i[contains(@class,'fa-bars')]")
	WebElement navBar;

	@FindBy(xpath = "//span[contains(text(),'Logout')]")
	WebElement logout;

	@FindBy(id = "regMobileNumber")
	WebElement mobNum;

	@FindBy(xpath = "//button[contains(text(),'Manage Wallet')]")
	WebElement manageWallet;

	public void logout(Map<String, String> dataMap) throws AWTException {

		wait.until(ExpectedConditions.elementToBeClickable(logout));
		Log.info("logging out");
		clickInvisibleElement(logout);
		wait.until(ExpectedConditions.visibilityOf(mobNum));
		Log.info("Logged out successfully");
	}
}
