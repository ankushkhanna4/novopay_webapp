package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.Log;

public class SwiggyPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	DecimalFormat df = new DecimalFormat("#.00");

	WebDriverWait wait = new WebDriverWait(wdriver, 30);
	WebDriverWait waitWelcome = new WebDriverWait(wdriver, 3);

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	@FindBy(xpath = "//span[contains(text(),'Cash Management')]")
	WebElement cashManagement;

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

	@FindBy(xpath = "//h1[contains(text(),'Cash Management Services (CMS)')]")
	WebElement pageTitle;

	@FindBy(xpath = "//span[contains(text(),'Swiggy')]/parent::li")
	WebElement swiggyIcon;

	@FindBy(id = "money-transfer-mobile-number")
	WebElement depositorMobNum;

	@FindBy(xpath = "//button[contains(text(),'Get Amount')]")
	WebElement getAmountButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	WebElement clearButton;

	@FindBy(id = "money-transfer-amount-to-be-transferred")
	WebElement fetchedAmount;

	@FindBy(xpath = "//swiggy-collection/div//button[contains(text(),'Submit')]")
	WebElement swgSubmitButton;

	@FindBy(xpath = "//h5[contains(text(),'Enter 4 digit PIN')]")
	WebElement MPINScreen;

	@FindBy(id = "money-transfer-mpin-number")
	WebElement enterMPIN;

	@FindBy(xpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/following-sibling::div/following-sibling::div/button[contains(text(),'Submit')]")
	WebElement submitMPIN;

	@FindBy(xpath = "//div//button[contains(text(),'Cancel')]")
	WebElement cancelButton;

	@FindBy(xpath = "//pin-modal/div//button[contains(text(),'Submit')]")
	WebElement mpinSubmitButton;

	@FindBy(xpath = "//h4[contains(text(),'Processing...')]")
	WebElement processingScreen;

	@FindBy(xpath = "//div[contains(@class,'cms-modal')]/div/div/div/h4[contains(text(),'!')]")
	WebElement cmsTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'cms-modal')]/div/div/div")
	WebElement cmsTxnScreenType;

	@FindBy(xpath = "//div[contains(@class,'cms-modal')]/div/div/div/following-sibling::div/div[1]")
	WebElement cmsTxnScreenMessage;

	@FindBy(xpath = "//button[contains(text(),'Exit')]")
	WebElement exitButton;

	@FindBy(xpath = "//button[contains(text(),'Retry')]")
	WebElement retryButton;

	@FindBy(xpath = "//button[contains(text(),'Done')]")
	WebElement doneButton;

	@FindBy(xpath = "//div[contains(@class,'toast-message')]")
	WebElement toasterMsg;

	// Load all objects
	public SwiggyPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void swiggy(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			menu.click();
			refreshBalance(); // refresh wallet balances
			menu.click();
			menu.click();
			wait.until(ExpectedConditions.elementToBeClickable(scrollBar));
			scrollElementDown(scrollBar, cashManagement);
			Log.info("Cash Management option clicked");
			wait.until(ExpectedConditions.elementToBeClickable(pageTitle));
			menu.click();

			displayInitialBalance(usrData, "retailer"); // display wallet balances in console

			double initialWalletBalance = getInitialBalance("retailer"); // store wallet balance as double datatype

			// Click on Swiggy icon
			wait.until(ExpectedConditions.elementToBeClickable(swiggyIcon));
			clickElement(swiggyIcon);
			Log.info("Swiggy icon clicked");

			// Click on depositor mobile number field
			wait.until(ExpectedConditions.elementToBeClickable(depositorMobNum));
			clickElement(depositorMobNum);
			depositorMobNum.clear();
			depositorMobNum.sendKeys(usrData.get("MOBILENUMBER"));
			Log.info("Depositor mobile number entered");

			if (usrData.get("GETAMOUNT").equalsIgnoreCase("YES")) {
				// Click on Get Amount button
				wait.until(ExpectedConditions.elementToBeClickable(getAmountButton));
				clickElement(getAmountButton);

				waitForSpinner();

				if (usrData.get("MOBILENUMBER").equalsIgnoreCase("6000000001")) {
					wait.until(ExpectedConditions.visibilityOf(toasterMsg));
					Assert.assertEquals(toasterMsg.getText(), "Invalid delivery boy");
					Log.info(toasterMsg.getText());
				} else {
					// Store fetched amount
					wait.until(ExpectedConditions.visibilityOf(fetchedAmount));
					String amount = fetchedAmount.getAttribute("value");
					Log.info("Amount is " + amount);
					cmsDetailsFromIni("StoreSwiggyAmount", replaceSymbols(amount));
				}
			} else if (usrData.get("GETAMOUNT").equalsIgnoreCase("Clear")) {
				// Click on Clear button
				clickElement(clearButton);
			}

			if (usrData.get("SUBMIT").equalsIgnoreCase("Yes")) {
				if (!usrData.get("AMOUNT").equalsIgnoreCase("SKIP")) {
					fetchedAmount.clear();
					fetchedAmount.sendKeys(usrData.get("AMOUNT"));
					cmsDetailsFromIni("StoreSwiggyAmount", usrData.get("AMOUNT"));
				}
				// Click on Submit button
				wait.until(ExpectedConditions.elementToBeClickable(swgSubmitButton));
				clickElement(swgSubmitButton);

				wait.until(ExpectedConditions.visibilityOf(MPINScreen));
				Log.info("MPIN screen displayed");
				wait.until(ExpectedConditions.elementToBeClickable(enterMPIN));
				enterMPIN.click();
				if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
					enterMPIN.sendKeys(getAuthfromIni("MPIN"));
				} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
					enterMPIN.sendKeys("9999");
				}
				Log.info("MPIN entered");

				String mpinButtonName = usrData.get("MPINSCREENBUTTON");
				String mpinScreenButtonXpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/"
						+ "following-sibling::div/following-sibling::div/button[contains(text(),'" + mpinButtonName
						+ "')]";
				WebElement mpinScreenButton = wdriver.findElement(By.xpath(mpinScreenButtonXpath));
				wait.until(ExpectedConditions.elementToBeClickable(mpinScreenButton));
				mpinScreenButton.click();
				Log.info(mpinButtonName + " button clicked");
				if (mpinButtonName.equalsIgnoreCase("Cancel")) {
					Log.info("Cancel button clicked");
				} else if (mpinButtonName.equalsIgnoreCase("Submit")) {
					wait.until(ExpectedConditions.visibilityOf(processingScreen));
					Log.info("Processing screen displayed");

					wait.until(ExpectedConditions.visibilityOf(cmsTxnScreen));
					Log.info("Txn screen displayed");

					// Verify the details on transaction screen
					if (cmsTxnScreen.getText().equalsIgnoreCase("Success!")) {
						assertionOnSuccessScreen(usrData);
						assertionOnSMS(usrData);

						wait.until(ExpectedConditions.elementToBeClickable(doneButton));
						doneButton.click();
						Log.info("Done button clicked");
						refreshBalance();
						verifyUpdatedBalanceAfterSuccessTxn(usrData, initialWalletBalance);
					} else if (cmsTxnScreen.getText().equalsIgnoreCase("Failed!")) {
						if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
							assertionOnFailedScreen(usrData);
							assertionOnSMS(usrData);
							if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
								Log.info("Clicking exit button");
							} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
								retryButton.click();
								Thread.sleep(1000);
								wait.until(ExpectedConditions.visibilityOf(MPINScreen));
								Log.info("MPIN screen displayed");
								wait.until(ExpectedConditions.elementToBeClickable(enterMPIN));
								enterMPIN.click();
								enterMPIN.sendKeys(getAuthfromIni("MPIN"));
								Log.info("MPIN entered");
								wait.until(ExpectedConditions.elementToBeClickable(submitMPIN));
								submitMPIN.click();
								Log.info("Submit button clicked");
								wait.until(ExpectedConditions.visibilityOf(processingScreen));
								Log.info("Processing screen displayed");
								wait.until(ExpectedConditions.visibilityOf(cmsTxnScreen));
								Log.info("Txn screen displayed");
								assertionOnFailedScreen(usrData);
							}
							wait.until(ExpectedConditions.elementToBeClickable(exitButton));
							exitButton.click();
							Log.info("Exit button clicked");
						} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
							wait.until(ExpectedConditions.elementToBeClickable(cmsTxnScreenMessage));
							Log.info(cmsTxnScreenMessage.getText());
							if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
								exitButton.click();
								Log.info("Exit button clicked");
							} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
								retryButton.click();
								Thread.sleep(1000);
								wait.until(ExpectedConditions.visibilityOf(MPINScreen));
								Log.info("MPIN screen displayed");
								wait.until(ExpectedConditions.elementToBeClickable(enterMPIN));
								enterMPIN.click();
								enterMPIN.sendKeys(getAuthfromIni("MPIN"));
								Log.info("MPIN entered");
								wait.until(ExpectedConditions.elementToBeClickable(submitMPIN));
								submitMPIN.click();
								Log.info("Submit button clicked");
								wait.until(ExpectedConditions.visibilityOf(processingScreen));
								Log.info("Processing screen displayed");
								wait.until(ExpectedConditions.visibilityOf(cmsTxnScreen));
								Log.info("Txn screen displayed");
								assertionOnSuccessScreen(usrData);
								doneButton.click();
								Log.info("Done button clicked");
								refreshBalance();
							}
						}
					}
				}
			} else if (usrData.get("SUBMIT").equalsIgnoreCase("Clear")) {
				clearButton.click();
				Log.info("Clear button clicked");
			}
		} catch (Exception e) {
			wdriver.navigate().refresh();
			e.printStackTrace();
			Log.info("Test Case Failed");
			Assert.fail();
		}
	}

	// Show balances in console
	public void displayInitialBalance(Map<String, String> usrData, String wallet) throws ClassNotFoundException {
		String walletBalance = dbUtils.getWalletBalance(mobileNumFromIni(), "retailer");
		String walletBal = walletBalance.substring(0, walletBalance.length() - 4);
		String cashoutBalance = dbUtils.getWalletBalance(mobileNumFromIni(), "cashout");
		String cashoutBal = cashoutBalance.substring(0, cashoutBalance.length() - 4);
		String merchantBalance = dbUtils.getWalletBalance(mobileNumFromIni(), "merchant");
		String merchantBal = merchantBalance.substring(0, merchantBalance.length() - 4);

		String initialWalletBal = replaceSymbols(retailerWalletBalance.getText());
		String initialCashoutBal = replaceSymbols(cashoutWalletBalance.getText());
		String initialMerchantBal = replaceSymbols(merchantWalletBalance.getText());

		// Compare wallet balance shown in WebApp to DB
		if (usrData.get("ASSERTION").equals("Initial Balance")) {
			Assert.assertEquals(walletBal, initialWalletBal);
			Assert.assertEquals(cashoutBal, initialCashoutBal);
			Assert.assertEquals(merchantBal, initialMerchantBal);
		}

		if (wallet.equalsIgnoreCase("retailer")) {
			Log.info("Retailer Balance: " + initialWalletBal);
		} else if (wallet.equalsIgnoreCase("cashout")) {
			Log.info("Cashout Balance: " + initialCashoutBal);
		} else if (wallet.equalsIgnoreCase("merchant")) {
			Log.info("Merchant Balance: " + initialMerchantBal);
		}
	}

	// Get wallet(s) balance
	@SuppressWarnings("null")
	public double getInitialBalance(String wallet) throws ClassNotFoundException {
		String initialWalletBal = replaceSymbols(retailerWalletBalance.getText());
		String initialCashoutBal = replaceSymbols(cashoutWalletBalance.getText());
		String initialMerchantBal = replaceSymbols(merchantWalletBalance.getText());

		// Converting balance from String to Double and returning the same
		if (wallet.equalsIgnoreCase("retailer")) {
			return Double.parseDouble(initialWalletBal);
		} else if (wallet.equalsIgnoreCase("cashout")) {
			return Double.parseDouble(initialCashoutBal);
		} else if (wallet.equalsIgnoreCase("merchant")) {
			return Double.parseDouble(initialMerchantBal);
		}
		return (Double) null;
	}

	// To refresh the wallet balance
	public void refreshBalance() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(refreshButton));
		clickInvisibleElement(refreshButton);
		wait.until(ExpectedConditions.elementToBeClickable(syncButton));
		wait.until(ExpectedConditions.elementToBeClickable(refreshButton));
		Log.info("Balance refreshed successfully");
	}

	// Scroll down the page
	public void pageScrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) wdriver;
		jse.executeScript("scroll(0, 250);");
	}

	// Wait for screen to complete loading
	public void waitForSpinner() {
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'spinner')]/parent::div")));
		Log.info("Please wait...");
	}

	// Get Partner name
	public String partner() {
		return "RBL";
	}

	// Get mobile number from Ini file
	public String mobileNumFromIni() {
		return getLoginMobileFromIni(partner().toUpperCase() + "RetailerMobNum");
	}

	// Remove rupee symbol and comma from the string
	public String replaceSymbols(String element) {
		String editedElement = element.replaceAll("₹", "").replaceAll(",", "").trim();
		return editedElement;
	}

	// click on WebElement forcefully
	public void clickElement(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			clickInvisibleElement(element);
		}
	}

	// Verify details on success screen
	public void assertionOnSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(cmsTxnScreenMessage.getText(), "Deposit to Swiggy successful.");
		Log.info(cmsTxnScreenMessage.getText());
	}

	// Verify details on failed screen
	public void assertionOnFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid MPIN")) {
			Assert.assertEquals(cmsTxnScreenMessage.getText(), "Authentication Failed Invalid MPIN");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
			Assert.assertEquals(cmsTxnScreenMessage.getText(), "Insufficient balance");
		} else {
			Assert.assertEquals(cmsTxnScreenMessage.getText(),
					"Deposit to Swiggy failed. Transaction reversed successfully.");
		}
		Log.info(cmsTxnScreenMessage.getText());
	}

	// SMS assertion
	public void assertionOnSMS(Map<String, String> usrData) throws ClassNotFoundException, InterruptedException {
		String successSMS = "Success! Deposit of Rs " + cmsDetailsFromIni("SwiggyAmount", "") + " for MSISDN "
				+ usrData.get("MOBILENUMBER") + " was successful.";
		String failSMS = "Failure! Deposit of Rs " + cmsDetailsFromIni("SwiggyAmount", "") + " for MSISDN "
				+ usrData.get("MOBILENUMBER") + " failed.";
		Thread.sleep(5000);
		if (usrData.get("ASSERTION").equalsIgnoreCase("Success SMS")) {
			Assert.assertEquals(successSMS, dbUtils.sms());
			Log.info(successSMS);
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Fail SMS")) {
			Assert.assertEquals(failSMS, dbUtils.sms());
			Log.info(successSMS);
		}
	}

	// Assertion after success or orange screen is displayed
	public void verifyUpdatedBalanceAfterSuccessTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		double amount = Double.parseDouble(cmsDetailsFromIni("SwiggyAmount", ""));
		double comm = amount * 3 / 1000;
		double commission = Math.round(comm * 100.0) / 100.0;
		double taxDS = commission * Double.parseDouble(dbUtils.getTDSPercentage(mobileNumFromIni())) / 10000;
		double tds = Math.round(taxDS * 100.0) / 100.0;
		double newWalletBal = initialWalletBalance - amount + commission - tds;
		txnDetailsFromIni("StoreComm", String.valueOf(commission));
		txnDetailsFromIni("StoreTds", String.valueOf(tds));
		String newWalletBalance = df.format(newWalletBal);
		Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
		Log.info("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
	}
}