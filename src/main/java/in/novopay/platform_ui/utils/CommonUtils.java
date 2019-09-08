package in.novopay.platform_ui.utils;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CommonUtils extends BasePage {
	DBUtils dbUtils = new DBUtils();

	public Map<String, String> usrData;

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
	
	@FindBy(xpath = "//div[contains(@class,'spinner')]/parent::div")
	WebElement spinner;
	
	@FindBy(xpath = "//button[contains(text(),'OK. Got it!')]")
	WebElement welcomeButton;

	@FindBy(xpath = "//*[contains(text(),'Choose a Wallet')]")
	WebElement chooseWalletScreen;

	@FindBy(xpath = "//*[@for='agent-wallet']")
	WebElement mainWalletRadioButton;

	@FindBy(xpath = "//*[@for='cashout-wallet']")
	WebElement cashoutWalletRadioButton;

	@FindBy(xpath = "//h5[contains(text(),'Main Wallet')]/following-sibling::p[contains(text(),' ₹')]")
	WebElement mainWalletScreenBalance;

	@FindBy(xpath = "//h5[contains(text(),'Cashout Wallet')]/following-sibling::p[contains(text(),' ₹')]")
	WebElement cashoutWalletScreenBalance;

	@FindBy(xpath = "//*[contains(text(),'Choose a Wallet')]/parent::div/following-sibling::div/button[contains(text(),'Proceed')]")
	WebElement chooseWalletProceedButton;

	@FindBy(xpath = "//*[@for='agent-wallet']//small")
	WebElement chooseWalletErrorMsg;

	@FindBy(xpath = "//table//tr[contains(@class,'table-row')][1]")
	WebElement firstTxnInList;

	@FindBy(xpath = "//h4[contains(text(),'Processing...')]")
	WebElement processingScreen;

	public CommonUtils(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Click OK on Welcome pop-up (whenever displayed)
	public void welcomePopup() {
		if (usrData.get("WELCOMEPOPUP").equalsIgnoreCase("YES")) {
			try {
				waitUntilElementIsVisible(welcomeMessage);
				Log.info("Welcome pop-up displayed");
				waitUntilElementIsClickableAndClickTheElement(welcomeOKButton);
				Log.info("OK button clicked");
				waitUntilElementIsInvisible(welcomeButton);
				Log.info("Pop-up disappeared");
			} catch (Exception e) {
				Log.info("No pop-up displayed");
			}
		}
	}

	// Wait for screen to complete loading
	public void waitForSpinner() {
		waitUntilElementIsInvisible(spinner);
		Log.info("Please wait...");
	}

	// To refresh the wallet balance
	public void refreshBalance() throws InterruptedException {
		waitUntilElementIsClickableAndClickTheElement(refreshButton);
		waitUntilElementIsVisible(syncButton);
		waitUntilElementIsVisible(refreshButton);
		Log.info("Balance refreshed successfully");
	}

	// Get wallet(s) balance
	@SuppressWarnings("null")
	public double getInitialBalance(String wallet) throws ClassNotFoundException {
		String initialWalletBal = replaceSymbols(retailerWalletBalance.getText());
		String initialCashoutBal = replaceSymbols(cashoutWalletBalance.getText());

		// Converting balance from String to Double and returning the same
		if (wallet.equalsIgnoreCase("retailer")) {
			return Double.parseDouble(initialWalletBal);
		} else if (wallet.equalsIgnoreCase("cashout")) {
			return Double.parseDouble(initialCashoutBal);
		}
		return (Double) null;
	}

	// Show balances in console
	public void displayInitialBalance(String wallet) throws ClassNotFoundException {
		String walletBalance = dbUtils.getWalletBalance(mobileNumFromIni(), "retailer");
		String walletBal = walletBalance.substring(0, walletBalance.length() - 4);
		String cashoutBalance = dbUtils.getWalletBalance(mobileNumFromIni(), "cashout");
		String cashoutBal = cashoutBalance.substring(0, cashoutBalance.length() - 4);

		waitUntilElementIsVisible(retailerWalletBalance);
		String initialWalletBal = replaceSymbols(retailerWalletBalance.getText());
		String initialCashoutBal = replaceSymbols(cashoutWalletBalance.getText());

		// Compare wallet balance shown in WebApp to DB
		if (usrData.get("ASSERTION").equals("Initial Balance")) {
			Assert.assertEquals(walletBal, initialWalletBal);
			Assert.assertEquals(cashoutBal, initialCashoutBal);
		}

		if (wallet.equalsIgnoreCase("retailer")) {
			Log.info("Retailer Balance: " + initialWalletBal);
			getWalletBalanceFromIni(wallet.toLowerCase(), replaceSymbols(retailerWalletBalance.getText()));
		} else if (wallet.equalsIgnoreCase("cashout")) {
			Log.info("Cashout Balance: " + initialCashoutBal);
			getWalletBalanceFromIni(wallet.toLowerCase(), replaceSymbols(cashoutWalletBalance.getText()));
		}
	}

	public double commissionAndTDS(String commOrTds, double minComm, double minCharge, double commPer)
			throws NumberFormatException, ClassNotFoundException {
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
				(roundTo2Decimals(minComm * Double.parseDouble(dbUtils.getTDSPercentage(mobileNumFromIni())) / 10000))
						* i
						+ roundTo2Decimals((commission - (minComm) * i)
								* Double.parseDouble(dbUtils.getTDSPercentage(mobileNumFromIni())) / 10000));

		if (commOrTds.equalsIgnoreCase("comm")) {
			return commission;
		} else
			return tds;
	}

	// Choose Wallet screen
	public void chooseWalletScreen(Map<String, String> usrData) throws InterruptedException {
		waitUntilElementIsVisible(chooseWalletScreen);
		Log.info("Choose a Wallet screen displayed");
//			Assert.assertEquals(replaceSymbols(mainWalletScreenBalance.getText()),
//					getWalletBalanceFromIni("GetRetailer", ""));
		Log.info("Main Wallet balance: " + mainWalletScreenBalance.getText());
		Assert.assertEquals(replaceSymbols(cashoutWalletScreenBalance.getText()),
				getWalletBalanceFromIni("GetCashout", ""));
		Log.info("Cashout Wallet balance: " + cashoutWalletScreenBalance.getText());
		mainWalletRadioButton.click();
		Log.info("Main wallet radio button clicked");
//			cashoutWalletRadioButton.click();
//			Log.info("Cashout wallet radio button clicked");
		waitUntilElementIsVisible(chooseWalletProceedButton);
		chooseWalletProceedButton.click();
		Log.info("Proceed button clicked");
	}

	public void selectTxn() throws ClassNotFoundException {
		waitUntilElementIsClickableAndClickTheElement(firstTxnInList);
		Log.info("Transaction selected");
		waitForSpinner();
	}

	public void processingScreen() {
		try {
			waitUntilElementIsVisible(processingScreen);
			Log.info("Processing screen displayed");
		} catch (Exception e) {
			Log.info("Processing screen skipped");
		}
	}
}
