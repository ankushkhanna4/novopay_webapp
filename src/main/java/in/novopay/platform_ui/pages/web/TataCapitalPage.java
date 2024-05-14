package in.novopay.platform_ui.pages.web;
import java.text.DecimalFormat;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;
import java.awt.AWTException;
import java.io.IOException;

public class TataCapitalPage extends BasePage {
	

	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	DecimalFormat df = new DecimalFormat("#.00");
	
	 @FindBy(xpath = "//span[text()='Cash Services']")
	 WebElement cashServices;
	 @FindBy(xpath = "//div[@class='biller-name' and normalize-space()='Tata Capital'] ']")
	 WebElement tatacapital;
	 @FindBy(xpath ="//input[contains(@class, 'textbox') and @name='depositerMobNumber']")
	 WebElement depositorMobileNo;
	 @FindBy(xpath ="//input[contains(@class, 'textbox') and @name='batchId']")
	 WebElement agentbatchId;
	 @FindBy(xpath ="//input[contains(@class, 'textbox') and @name='creCode']")
	 WebElement agentcreCode;
	 @FindBy(xpath ="//button[@class='button button-primary mar-3 proceed-btn']")
	 WebElement proceedbutton;
	 @FindBy(xpath ="//button[@class='button button-basic mar-3 ng-star-inserted']")
	 WebElement clearButton;
	 @FindBy(xpath ="//input[@id='customer' and @name='tata_capital' and @type='radio' and @value='customer']")
	 WebElement customerradioButton;
	 @FindBy(xpath ="//input[contains(@class, 'textbox') and @name='contractNo']")
	 WebElement contractNo;
	 
		// Perform action on page based on given commands
		public void Tatacpital(Map<String, String> usrData)
				throws InterruptedException, AWTException, IOException, ClassNotFoundException {

			try {
	 
	// Update wallet balance as per the scenarios
				commonUtils.selectFeatureFromMenu2(cashServices, tatacapital);

				commonUtils.displayInitialBalance("retailer"); // display main wallet balance
				commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

				// Click on capital first option
				commonUtils.selectCmsBiller();
				System.out.println("Cash service clicked");

				// Click on depositor mobile number field
				waitUntilElementIsClickableAndClickTheElement(depositorMobileNo);
				depositorMobileNo.clear();
				depositorMobileNo.sendKeys(usrData.get("MOBILENUMBER"));
				System.out.println("Depositor mobile number entered");

				// Click on depositor batch field
				waitUntilElementIsClickableAndClickTheElement(agentbatchId);
				agentbatchId.clear();
				agentbatchId.sendKeys(usrData.get("BATCHID"));
				System.out.println("batchId entered");
				
				
				
			
		

	// Load all objects
	public TataCapitalPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}
}