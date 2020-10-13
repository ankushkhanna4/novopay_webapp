package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;

public class LoadMoneyNowPage extends BasePage {

	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//button[contains(text(),'Manage Wallet')]")
	WebElement manageWalletButton;

	@FindBy(xpath = "//a[contains(text(),'Load Money Now')]")
	WebElement loadMoneyNowTab;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	@FindBy(xpath = "//i[contains(@class,'np np-refresh')]")
	WebElement refreshButton;

	@FindBy(xpath = "//i[contains(@class,'np np-sync')]")
	WebElement syncButton;

	@FindBy(xpath = "//span[contains(text(),'wallet balance')]")
	WebElement retailerWallet;

	@FindBy(xpath = "//span[contains(text(),'wallet balance')]/parent::p/following-sibling::p/span")
	WebElement retailerWalletBalance;

	@FindBy(xpath = "//span[contains(text(),'cashout balance')]")
	WebElement cashoutWallet;

	@FindBy(xpath = "//span[contains(text(),'cashout balance')]/parent::p/following-sibling::p/span")
	WebElement cashoutWalletBalance;

	@FindBy(xpath = "//span[contains(text(),'merchant balance')]")
	WebElement merchantWallet;

	@FindBy(xpath = "//span[contains(text(),'merchant balance')]/parent::p/following-sibling::p/span")
	WebElement merchantWalletBalance;

	@FindBy(xpath = "//h1[contains(text(),'Manage Wallet')]")
	WebElement pageTitle;

	@FindBy(xpath = "//div[@id='pd-16']//load-money-request//div/input")
	WebElement amount;

	@FindBy(xpath = "//select2[contains(@id,'payment-search-mode')]/parent::div")
	WebElement paymentDropdown;

	@FindBy(xpath = "//button[contains(text(),'Proceed')]")
	WebElement proceedButton;

	@FindBy(xpath = "//div[@id= 'form-common']//button[1]")
	WebElement preferedPaymentModeUPI;

	@FindBy(xpath = "//*[@id='form-common']//button[2]")
	WebElement preferedPaymentModeUPIQR;

	@FindBy(xpath = "//*[@id='form-common']//button[3]")
	WebElement preferedPaymentModeWalletPhonePe;

	@FindBy(xpath = "//*[contains(text(), 'PAY')]")
	WebElement payButton;

	@FindBy(xpath = "//*[@id='response-modal']/div/div/div")
	WebElement successUPI;

	@FindBy(xpath = "//*[@id='response-modal']//div/p")
	WebElement loadMoneyNowTxnScreenMessage;

	@FindBy(xpath = "//*[@id='response-modal']//span[2]")
	WebElement dateAndTime;

	@FindBy(xpath = "//*[@id='response-modal']//li[2]/span[2]")
	WebElement OrderID;

	@FindBy(xpath = "//*[@id='response-modal']//li/span[2]/strong")
	WebElement WalletLoadAmount;

	@FindBy(xpath = "//*[@id='response-modal']//div[3]/button")
	WebElement DoneButton;

	@FindBy(xpath = "//select2[contains(@id,'bank-search-list')]/parent::div")
	WebElement bankListDropdown;

	@FindBy(xpath = "//select[@id = 'bank-select']")
	WebElement AvailableBanksSelect;

	@FindBy(xpath = "//button[@class='success']")
	WebElement payGatwaySuccessButton;

	@FindBy(xpath = "//button[contains(text(),'Ok')]")
	WebElement payGatwayOKButton;

	@FindBy(xpath = "//button[@class='success']")
	WebElement razorpayBankSuccessButton;

	@FindBy(xpath = "//div[@id='gpay-omnichannel']")
	WebElement GooglePayphonenumber;

	@FindBy(xpath = "//button[@method='card']")
	WebElement cardButton;

	@FindBy(xpath = "//div[@class='saved-card']")
	WebElement savedCard;

	@FindBy(xpath = "//iframe[@class='razorpay-checkout-frame']")
	WebElement iFrame;

	@FindBy(xpath = "//h4[contains(text(),'Processing')]")
	WebElement processingScreen;

	@FindBy(xpath = "//div[contains(@id,'response-modal')]//h4[contains(text(),'!')]")
	WebElement loadMoneyNowTxnScreen;

	@FindBy(xpath = "//div[contains(@id,'response-modal')]//button[contains(text(),'Done')]")
	WebElement loadMoneyNowTxnScreenDoneButton;

	@FindBy(xpath = "//div[contains(@id,'response-modal')]//button[contains(text(),'Ok')]")
	WebElement loadMoneyNowTxnScreenOkButton;

	@FindBy(xpath = "//div[@class='charges-slab-first-row']/label[2]")
	WebElement fetchedAmount;

	@FindBy(xpath = "//label[contains(@class,'principal')][contains(text(),'Charges')]/following-sibling::label")
	WebElement fetchedCharges;

	@FindBy(xpath = "//label[contains(@class,'principal')][contains(text(),'Load Amount')]/following-sibling::label")
	WebElement fetchedLoadAmount;
	
	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent;

	// Load all objects
	public LoadMoneyNowPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void loadMoneyNow(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			commonUtils.displayInitialBalance("retailer"); // display main wallet balance
			commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

			commonUtils.selectFeatureFromMenu1(manageWalletButton, pageTitle);

			waitUntilElementIsClickableAndClickTheElement(loadMoneyNowTab);
			System.out.println("Load Money Now Tab clicked");

			waitUntilElementIsClickableAndClickTheElement(amount);
			amount.sendKeys(usrData.get("AMOUNT"));
			System.out.println("Amount entered: " + usrData.get("AMOUNT"));
			loadMoneyNowDataFromIni("StoreAmount", usrData.get("AMOUNT"));

			waitUntilElementIsClickableAndClickTheElement(paymentDropdown);
			System.out.println("Drop down clicked");
			Thread.sleep(1000);
			String typeXpath = "//*[contains(@id,'select2')]/li[contains(text(),'" + usrData.get("MODEOFPAYMENT")
					+ "')]";
			waitUntilElementIsClickableAndClickTheElement(wdriver.findElement(By.xpath(typeXpath)));
			System.out.println(usrData.get("MODEOFPAYMENT") + " selected");

			if (usrData.get("MODEOFPAYMENT").equalsIgnoreCase("Net banking")) {
				waitUntilElementIsClickableAndClickTheElement(bankListDropdown);
				System.out.println("Drop down clicked");
				String bank = "//*[contains(@id,'select2')]/li[contains(text(),'" + usrData.get("Banks") + "')]";
				waitUntilElementIsClickableAndClickTheElement(wdriver.findElement(By.xpath(bank)));
				System.out.println(usrData.get("Banks") + " selected");
			}

			commonUtils.waitForSpinner();

			waitUntilElementIsVisible(fetchedAmount);
			double amt = Double.parseDouble(replaceSymbols(fetchedAmount.getText()));
			double chrgs = Double.parseDouble(replaceSymbols(fetchedCharges.getText()));
			double loadAmt = amt - chrgs;

			Assert.assertEquals(replaceSymbols(fetchedAmount.getText()),
					loadMoneyNowDataFromIni("GetAmount", "") + ".00");
			System.out.println("Amount: " + replaceSymbols(fetchedAmount.getText()));
			System.out.println("Charges: " + replaceSymbols(fetchedCharges.getText()));
			Assert.assertEquals(replaceSymbols(fetchedLoadAmount.getText()), df.format(loadAmt));
			System.out.println("Load Amount: " + replaceSymbols(fetchedLoadAmount.getText()));
			loadMoneyNowDataFromIni("StoreLoadAmount", replaceSymbols(fetchedLoadAmount.getText()));

			waitUntilElementIsClickableAndClickTheElement(proceedButton);
			System.out.println("Proceed button clicked");
			commonUtils.waitForSpinner();
			waitUntilElementIsVisible(iFrame);
			System.out.println("iFrame is visible");
			Thread.sleep(5000);

			if (usrData.get("MODEOFPAYMENT").equalsIgnoreCase("Credit Card")) {
			} else if (usrData.get("MODEOFPAYMENT").equalsIgnoreCase("Net banking")) {
				System.out.println("Credit Card data submitted");
			} else if (usrData.get("MODEOFPAYMENT").equalsIgnoreCase("Debit Card")) {
				System.out.println("Debit Card data submitted");
			} else if (usrData.get("MODEOFPAYMENT").equalsIgnoreCase("UPI")) {
				Thread.sleep(1000);
				iFrame.sendKeys(Keys.TAB);
				Thread.sleep(1000);
				iFrame.sendKeys(Keys.TAB);
				Thread.sleep(1000);
				iFrame.sendKeys(Keys.TAB);
				Thread.sleep(1000);
				iFrame.sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				iFrame.sendKeys(Keys.ENTER);
				Thread.sleep(1000);
				System.out.println("UPI data submitted");
			} else if (usrData.get("MODEOFPAYMENT").equalsIgnoreCase("Wallet")) {
				System.out.println("Wallet data submitted");
			}

			waitUntilElementIsVisible(processingScreen);
			System.out.println("Processing screen displayed");
			waitUntilElementIsVisible(loadMoneyNowTxnScreen);
			System.out.println("Txn screen displayed");
			assertionOnTxnScreen(usrData, df.format(loadAmt));

			if (loadMoneyNowTxnScreen.getText().equalsIgnoreCase("Success!")) {
				waitUntilElementIsClickableAndClickTheElement(loadMoneyNowTxnScreenDoneButton);
				System.out.println("Done button clicked");
				if (usrData.get("ASSERTION").contains("FCM")) {
					assertionOnFCM(usrData);
				}
				verifyUpdatedBalanceAfterSuccessTxn(usrData, loadAmt);
			} else if (loadMoneyNowTxnScreen.getText().equalsIgnoreCase("Pending!")) {
				waitUntilElementIsClickableAndClickTheElement(loadMoneyNowTxnScreenOkButton);
				System.out.println("Ok button clicked");
			}
		} catch (Exception e) {
			wdriver.navigate().refresh();
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}

	public void assertionOnTxnScreen(Map<String, String> usrData, String loadAmount)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (loadMoneyNowTxnScreen.getText().equalsIgnoreCase("Pending!")) {
			Assert.assertEquals(loadMoneyNowTxnScreenMessage.getText(),
					"Waiting for confirmation… We will notify you once we get an update.");
		} else if (loadMoneyNowTxnScreen.getText().equalsIgnoreCase("Success!")) {
			Assert.assertEquals(loadMoneyNowTxnScreenMessage.getText(), "Money added to the wallet");
		}
		System.out.println(loadMoneyNowTxnScreenMessage.getText());

		System.out.println("Order Id: " + OrderID.getText());
		loadMoneyNowDataFromIni("StoreOrderId", OrderID.getText());

		Assert.assertEquals(replaceSymbols(WalletLoadAmount.getText()), loadAmount);
		System.out.println("Wallet Load amount: " + replaceSymbols(WalletLoadAmount.getText()));
	}

	public void verifyUpdatedBalanceAfterSuccessTxn(Map<String, String> usrData, double loadAmount)
			throws ClassNotFoundException {
		double initialWalletBalance = 1000000.00;
		double newWalletBal = 0.00;
		newWalletBal = initialWalletBalance + loadAmount;
		String newWalletBalance = df.format(newWalletBal);
		Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
		System.out.println("Updated Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
	}

	// FCM assertion
	public void assertionOnFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successFCMHeading = "Wallet Deposit : SUCCESS";
		String successFCMContent = "Customer Mobile: " + getLoginMobileFromIni("GetRetailerMobNum") + " Rs."
				+ loadMoneyNowDataFromIni("GetLoadAmount", "") + " has been added to your Novopay wallet. Txn id: "
				+ loadMoneyNowDataFromIni("GetOrderId", "") + ".";

		fcmMethod(successFCMHeading, successFCMContent);
	}

	public void fcmMethod(String heading, String content) {
		Assert.assertEquals(fcmHeading.getText(), heading);
		Assert.assertEquals(fcmContent.getText(), content);
		System.out.println(fcmHeading.getText());
		System.out.println(fcmContent.getText());
	}
}
