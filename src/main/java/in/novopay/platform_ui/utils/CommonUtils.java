package in.novopay.platform_ui.utils;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonUtils extends BasePage {
	DBUtils dbUtils = new DBUtils();

	public Map<String, String> usrData;

	WebDriverWait wait = new WebDriverWait(wdriver, 30);
	WebDriverWait waitWelcome = new WebDriverWait(wdriver, 3);

	@FindBy(xpath = "//h4[contains(text(),'Welcome')]")
	WebElement welcomeMessage;

	@FindBy(xpath = "//h4[contains(text(),'Welcome')]/parent::div/following-sibling::div[2]/button")
	WebElement welcomeOKButton;

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

	public CommonUtils(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Click OK on Welcome pop-up (whenever displayed)
	public void welcomePopup() {
		try {
			waitWelcome.until(ExpectedConditions.visibilityOf(welcomeMessage));
			Log.info("Welcome pop-up displayed");
			waitWelcome.until(ExpectedConditions.elementToBeClickable(welcomeOKButton));
			clickElement(welcomeOKButton);
			Log.info("OK button clicked");
			waitWelcome.until(ExpectedConditions
					.invisibilityOfElementLocated(By.xpath("//button[contains(text(),'OK. Got it!')]")));
			Log.info("Pop-up disappeared");
		} catch (Exception e) {
			Log.info("No pop-up displayed");
		}
	}

	// Wait for screen to complete loading
	public void waitForSpinner() {
		String spinnerXpath = "//div[contains(@class,'spinner')]/parent::div";
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(spinnerXpath)));
		Log.info("Please wait...");
	}

	// To refresh the wallet balance
	public void refreshBalance() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(refreshButton));
		clickInvisibleElement(refreshButton);
		wait.until(ExpectedConditions.visibilityOf(syncButton));
		wait.until(ExpectedConditions.elementToBeClickable(refreshButton));
		Log.info("Balance refreshed successfully");
	}

	// Get wallet(s) balance
	@SuppressWarnings("null")
	public double getInitialBalance(String wallet) throws ClassNotFoundException {
		String initialWalletBal = replaceSymbols(retailerWalletBalance.getText());
		String initialCashoutBal = replaceSymbols(cashoutWalletBalance.getText());
//			String initialMerchantBal = replaceSymbols(merchantWalletBalance.getText());

		// Converting balance from String to Double and returning the same
		if (wallet.equalsIgnoreCase("retailer")) {
			return Double.parseDouble(initialWalletBal);
		} else if (wallet.equalsIgnoreCase("cashout")) {
			return Double.parseDouble(initialCashoutBal);
//			} else if (wallet.equalsIgnoreCase("merchant")) {
//				return Double.parseDouble(initialMerchantBal);
		}
		return (Double) null;
	}

	// Show balances in console
	public void displayInitialBalance(Map<String, String> usrData, String wallet) throws ClassNotFoundException {
		String walletBalance = dbUtils.getWalletBalance(mobileNumFromIni(), "retailer");
		String walletBal = walletBalance.substring(0, walletBalance.length() - 4);
		String cashoutBalance = dbUtils.getWalletBalance(mobileNumFromIni(), "cashout");
		String cashoutBal = cashoutBalance.substring(0, cashoutBalance.length() - 4);
//			String merchantBalance = dbUtils.getWalletBalance(mobileNumFromIni(), "merchant");
//			String merchantBal = merchantBalance.substring(0, merchantBalance.length() - 4);

		wait.until(ExpectedConditions.elementToBeClickable(retailerWalletBalance));
		String initialWalletBal = replaceSymbols(retailerWalletBalance.getText());
		String initialCashoutBal = replaceSymbols(cashoutWalletBalance.getText());
//			String initialMerchantBal = replaceSymbols(merchantWalletBalance.getText());

		// Compare wallet balance shown in WebApp to DB
		if (usrData.get("ASSERTION").equals("Initial Balance")) {
			Assert.assertEquals(walletBal, initialWalletBal);
			Assert.assertEquals(cashoutBal, initialCashoutBal);
//				Assert.assertEquals(merchantBal, initialMerchantBal);
		}

		if (wallet.equalsIgnoreCase("retailer")) {
			Log.info("Retailer Balance: " + initialWalletBal);
			getWalletBalanceFromIni(wallet.toLowerCase(), replaceSymbols(retailerWalletBalance.getText()));
		} else if (wallet.equalsIgnoreCase("cashout")) {
			Log.info("Cashout Balance: " + initialCashoutBal);
			getWalletBalanceFromIni(wallet.toLowerCase(), replaceSymbols(cashoutWalletBalance.getText()));
//			} else if (wallet.equalsIgnoreCase("merchant")) {
//				Log.info("Merchant Balance: " + initialMerchantBal);
//				getWalletBalanceFromIni(wallet.toLowerCase(), replaceSymbols(merchantWalletBalance.getText()));
		}
	}

	public double commissionAndTDS(String commOrTds, double minComm, double minCharge, double commPer) throws NumberFormatException, ClassNotFoundException {
		int i = 0;
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double commission = 0.0;

		if (amount <= 5000.0) {
			i = 0;
		} else if (amount > 5000.0 && amount <= 10000.0) {
			i = 1;
		} else if (amount > 10000.0 && amount <= 15000.0) {
			i = 2;
		} else if (amount > 15000.0 && amount <= 20000.0) {
			i = 3;
		} else if (amount > 20000.0 && amount <= 25000.0) {
			i = 4;
		}

		commission = minComm * i + Math.max(minCharge, (amount - 5000 * i) * commPer / 10000);

		double tds = roundTo2Decimals(
				(roundTo2Decimals(minComm * Double.parseDouble(dbUtils.getTDSPercentage(mobileNumFromIni())) / 10000)) * i
						+ roundTo2Decimals((commission - (minComm) * i)
								* Double.parseDouble(dbUtils.getTDSPercentage(mobileNumFromIni())) / 10000));

		if (commOrTds.equalsIgnoreCase("comm")) {
			return commission;
		} else
			return tds;
	}
}
