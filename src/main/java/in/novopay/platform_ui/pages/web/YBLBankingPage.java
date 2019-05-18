package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
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
import in.novopay.platform_ui.utils.ServerUtils;

public class YBLBankingPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	ServerUtils srvUtils = new ServerUtils();
	DecimalFormat df = new DecimalFormat("#.00");

	WebDriverWait wait = new WebDriverWait(wdriver, 30);
	WebDriverWait waitWelcome = new WebDriverWait(wdriver, 3);

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

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

	@FindBy(xpath = "//h1[contains(text(),'Banking')]")
	WebElement pageTitle;

	@FindBy(xpath = "//a[contains(@href,'ybl-banking')]/span[2][contains(text(),'Banking')]")
	WebElement banking;

	@FindBy(xpath = "//a[contains(text(),'Deposit')]")
	WebElement depositTab;

	@FindBy(xpath = "//app-deposit//span[contains(text(),'Select...')]/parent::span")
	WebElement depositDropdown;

	@FindBy(xpath = "//app-deposit//input[@id='money-transfer-mobile-number']")
	WebElement depositMobNum;

	@FindBy(xpath = "//app-deposit//input[@id='money-transfer-mobile-number']/parent::div//li")
	WebElement depositMobNumError;

	@FindBy(xpath = "//app-deposit//input[@id='aeps-deposit-aadhar-number']")
	WebElement depositAadhaarNum;

	@FindBy(xpath = "//app-deposit//input[@id='aeps-deposit-aadhar-number']/parent::div//li")
	WebElement depositAadhaarNumError;

	@FindBy(xpath = "//app-deposit//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]")
	WebElement depositConsentCheckbox;

	@FindBy(xpath = "//app-deposit//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p")
	WebElement depositConsentMessage;

	@FindBy(xpath = "//app-deposit//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p/a")
	WebElement depositConsentLink;

	@FindBy(xpath = "//app-deposit//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/following-sibling::ul/li")
	WebElement depositConsentError;

	@FindBy(xpath = "//app-deposit//input[@id='money-transfer-amount-to-be-transferred']")
	WebElement depositAmount;

	@FindBy(xpath = "//app-deposit//input[@id='money-transfer-amount-to-be-transferred']/parent::div//li")
	WebElement depositAmountError;

	@FindBy(xpath = "//app-deposit//div[contains(@class,'scan_finger_container')]")
	WebElement depositScanFingerprint;

	@FindBy(xpath = "//app-deposit//h4[contains(text(),'Success!')]")
	WebElement depositScanSuccessScreen;

	@FindBy(xpath = "//app-deposit//div[contains(text(),'Fingerprints scanned successfully')]")
	WebElement depositFingerprintSuccess;

	@FindBy(xpath = "//app-deposit//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement depositFingerprintGreen;

	@FindBy(xpath = "//app-deposit//span[contains(text(),'Click to scan fingerprint')]")
	WebElement depositFingerprintUnscanned;

	@FindBy(xpath = "//app-deposit//button[contains(text(),'Ok')]")
	WebElement depositFingerprintScreenOkButton;

	@FindBy(xpath = "//app-deposit//h5[contains(text(),'Enter 4 digit PIN')]")
	WebElement mpinScreen;

	@FindBy(xpath = "//app-deposit//input[@id='money-transfer-mpin-number']")
	WebElement enterMpin;

	@FindBy(xpath = "//app-deposit//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/following-sibling::div[2]/button[contains(text(),'Submit')]")
	WebElement submitMpin;

	@FindBy(xpath = "//app-deposit//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/following-sibling::div[2]/button[contains(text(),'Cancel')]")
	WebElement cancelMpin;

	@FindBy(xpath = "//app-deposit/div//button[contains(text(),'Submit')]")
	WebElement depositSubmit;

	@FindBy(xpath = "//app-deposit/div//button[contains(text(),'Clear')]")
	WebElement depositClear;

	@FindBy(xpath = "//a[contains(text(),'Withdrawal')]")
	WebElement withdrawalTab;

	@FindBy(xpath = "//app-withdrawl/div//span[contains(text(),'Select...')]/parent::span")
	WebElement withdrawalDropdown;

	@FindBy(xpath = "//app-withdrawl//input[@id='money-transfer-mobile-number']")
	WebElement withdrawalMobNum;

	@FindBy(xpath = "//app-withdrawl//input[@id='money-transfer-mobile-number']/parent::div//li")
	WebElement withdrawalMobNumError;

	@FindBy(xpath = "//app-withdrawl//input[@id='aeps-deposit-aadhar-number']")
	WebElement withdrawalAadhaarNum;

	@FindBy(xpath = "//app-withdrawl//input[@id='aeps-deposit-aadhar-number']/parent::div//li")
	WebElement withdrawalAadhaarNumError;

	@FindBy(xpath = "//app-withdrawl//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]")
	WebElement withdrawalConsentCheckbox;

	@FindBy(xpath = "//fapp-withdrawl//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p")
	WebElement withdrawalConsentMessage;

	@FindBy(xpath = "//app-withdrawl//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p/a")
	WebElement withdrawalConsentLink;

	@FindBy(xpath = "//app-withdrawl//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/following-sibling::ul/li")
	WebElement withdrawalConsentError;

	@FindBy(xpath = "//app-withdrawl//input[@id='money-transfer-amount-to-be-transferred']")
	WebElement withdrawalAmount;

	@FindBy(xpath = "//app-withdrawl//input[@id='money-transfer-amount-to-be-transferred']/parent::div//li")
	WebElement withdrawalAmountError;

	@FindBy(xpath = "//app-withdrawl//div[contains(@class,'scan_finger_container')]")
	WebElement withdrawalScanFingerprint;

	@FindBy(xpath = "//app-withdrawl//h4[contains(text(),'Success!')]")
	WebElement withdrawalScanSuccessScreen;

	@FindBy(xpath = "//app-withdrawl//div[contains(text(),'Fingerprints scanned successfully')]")
	WebElement withdrawalFingerprintSuccess;

	@FindBy(xpath = "//app-withdrawl//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement withdrawalFingerprintGreen;

	@FindBy(xpath = "//app-withdrawl//span[contains(text(),'Click to scan fingerprint')]")
	WebElement withdrawalFingerprintUnscanned;

	@FindBy(xpath = "//app-withdrawl//button[contains(text(),'Ok')]")
	WebElement withdrawalFingerprintScreenOkButton;

	@FindBy(xpath = "//app-withdrawl/div//button[contains(text(),'Submit')]")
	WebElement withdrawalSubmit;

	@FindBy(xpath = "//app-withdrawl/div//button[contains(text(),'Clear')]")
	WebElement withdrawalClear;

	@FindBy(xpath = "//*[contains(text(),'Confirm the details')]")
	WebElement confirmScreen;

	@FindBy(xpath = "//h4[contains(text(),'Confirm the details')]/../following-sibling::div/div[6]//strong")
	WebElement confirmScreenAmount;

	@FindBy(xpath = "//h4[contains(text(),'Confirm the details')]/../following-sibling::div[2]/button[contains(text(),'Submit')]")
	WebElement confirmScreenSubmit;

	@FindBy(xpath = "//h4[contains(text(),'Confirm the details')]/../following-sibling::div[2]/button[contains(text(),'Cancel')]")
	WebElement confirmScreenCancel;

	@FindBy(xpath = "//app-aepsbalanceenquiry//span[contains(text(),'Select...')]/parent::span")
	WebElement balanceEnquiryDropdown;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='money-transfer-mobile-number']")
	WebElement balanceEnquiryMobNum;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='money-transfer-mobile-number']/parent::div//li")
	WebElement balanceEnquiryMobNumError;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='aeps-deposit-aadhar-number']")
	WebElement balanceEnquiryAadhaarNum;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='aeps-deposit-aadhar-number']/parent::div//li")
	WebElement balanceEnquiryAadhaarNumError;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]")
	WebElement balanceEnquiryConsentCheckbox;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p")
	WebElement balanceEnquiryConsentMessage;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/p/a")
	WebElement balanceEnquiryConsentLink;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[contains(@id,'aadhaarDataConsent') and contains(@class,'ng-invalid')]/parent::span/following-sibling::ul/li")
	WebElement balanceEnquiryConsentError;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='money-transfer-amount-to-be-transferred']")
	WebElement balanceEnquiryAmount;

	@FindBy(xpath = "//app-aepsbalanceenquiry//input[@id='money-transfer-amount-to-be-transferred']/parent::div//li")
	WebElement balanceEnquiryAmountError;

	@FindBy(xpath = "//app-aepsbalanceenquiry//div[contains(@class,'scan_finger_container')]")
	WebElement balanceEnquiryScanFingerprint;

	@FindBy(xpath = "//app-aepsbalanceenquiry//h4[contains(text(),'Success!')]")
	WebElement balanceEnquiryScanSuccessScreen;

	@FindBy(xpath = "//app-aepsbalanceenquiry//div[contains(text(),'Fingerprints scanned successfully')]")
	WebElement balanceEnquiryFingerprintSuccess;

	@FindBy(xpath = "//app-aepsbalanceenquiry//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement balanceEnquiryFingerprintGreen;

	@FindBy(xpath = "//app-aepsbalanceenquiry//span[contains(text(),'Click to scan fingerprint')]")
	WebElement balanceEnquiryFingerprintUnscanned;

	@FindBy(xpath = "//app-aepsbalanceenquiry//button[contains(text(),'Ok')]")
	WebElement balanceEnquiryFingerprintScreenOkButton;

	@FindBy(xpath = "//app-aepsbalanceenquiry/div//button[contains(text(),'Submit')]")
	WebElement balanceEnquirySubmit;

	@FindBy(xpath = "//app-aepsbalanceenquiry/div//button[contains(text(),'Clear')]")
	WebElement balanceEnquiryClear;

	@FindBy(xpath = "//*[@type='search']")
	WebElement dropdownSearch;

	@FindBy(xpath = "//h4[contains(text(),'Processing...')]")
	WebElement processingScreen;

	@FindBy(xpath = "//button[contains(text(),'Process in Background')]")
	WebElement processInBackgroundButton;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]/div/div/div/h4[contains(text(),'!')]")
	WebElement aepsTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]/div/div/div")
	WebElement aepsTxnScreenType;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]/div/div/div/following-sibling::div/div[1]")
	WebElement aepsTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]/div/div/div/following-sibling::div/div/strong")
	WebElement aepsDeemedTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//span[contains(text(),'Amount:')]/parent::div/following-sibling::div")
	WebElement aepsTxnScreenTxnAmount;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//span[contains(text(),'Charges:')]/parent::div/following-sibling::div")
	WebElement aepsTxnScreenCharges;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//span[contains(text(),'Failed Amount:')]/parent::div/following-sibling::div")
	WebElement aepsTxnScreenFailedAmount;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//p[contains(text(),'Cash to be')]/parent::div/p[2]")
	WebElement aepsTxnScreenTotalAmount;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]/div/div/div/following-sibling::div/div/span")
	WebElement aepsTxnScreenFailureReason;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//button[contains(text(),'Done')]")
	WebElement aepsTxnScreenDoneButton;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//button[contains(text(),'Exit')]")
	WebElement aepsTxnScreenExitButton;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//button[contains(text(),'Retry')]")
	WebElement aepsTxnScreenRetryButton;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//button[contains(text(),'Save')]")
	WebElement aepsTxnScreenSaveButton;

	@FindBy(xpath = "//div[contains(@class,'aeps-modal')]//button[contains(text(),'Print')]")
	WebElement aepsTxnScreenPrintButton;

	@FindBy(xpath = "//div[contains(@class,'deposit-aeps-modal')]//strong[contains(text(),'Ref.ID')]/parent::span/parent::div/following-sibling::div//span")
	WebElement depositRefNo;

	@FindBy(xpath = "//div[contains(@class,'withdraw-aeps-modal')]//strong[contains(text(),'Ref.ID')]/parent::span/parent::div/following-sibling::div//span")
	WebElement withdrawalRefNo;

	@FindBy(xpath = "//div[contains(@class,'enquiry-aeps-modal')]//strong[contains(text(),'Ref.ID')]/parent::span/parent::div/following-sibling::div//span")
	WebElement balanceEnquiryRefNo;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	String batchConfigSection = "rblaepsstatusenquiry";

	// Load all objects
	public YBLBankingPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void yblBanking(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			menu.click();
			refreshBalance();
//			menu.click();
//			menu.click();
//			wait.until(ExpectedConditions.elementToBeClickable(scrollBar));
			scrollElementDown(scrollBar, banking);
			Log.info("Banking option clicked");
			wait.until(ExpectedConditions.elementToBeClickable(pageTitle));
			menu.click();

			HashMap<String, String> batchFileConfig = readSectionFromIni(batchConfigSection);
			batchFileConfig = readSectionFromIni(batchConfigSection);
			if (!usrData.get("KEY").isEmpty()) {
				srvUtils.uploadFile(batchFileConfig, usrData.get("KEY"));
			}

			// Update retailer wallet balance to 0 for scenario where amount > wallet
			if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Wallet")) {
				if (usrData.get("TXNTYPE").equalsIgnoreCase("Deposit")) {
					dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "0");
				} else if (usrData.get("TXNTYPE").equalsIgnoreCase("Withdrawal")) {
					dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "0");
				}
			}

			// Refresh wallet balances whenever required
			if (usrData.get("REFRESH").equalsIgnoreCase("YES")) {
				refreshBalance(); // refresh wallet balances
			}

			// display wallet balances in console
			displayInitialBalance(usrData, "cashout");

			// store wallet balance as double
			double initialRetailerWalletBalance = getInitialBalance("retailer");
			double initialCashoutWalletBalance = getInitialBalance("cashout");

			if (usrData.get("ASSERTION").contains("FCM")) {
				if (usrData.get("TXNTYPE").equalsIgnoreCase("Deposit")) {
					assertionOnDepositFCM(usrData);
				} else if (usrData.get("TXNTYPE").equalsIgnoreCase("Withdrawal")) {
					assertionOnWithdrawalFCM(usrData);
				} else if (usrData.get("TXNTYPE").equalsIgnoreCase("Balance Enquiry")) {
					assertionOnBalanceEnquiryFCM(usrData);
				}
			} else {
				if (usrData.get("TXNTYPE").equalsIgnoreCase("Deposit")) {
					depositTxns(usrData, initialRetailerWalletBalance);
				} else if (usrData.get("TXNTYPE").equalsIgnoreCase("Withdrawal")) {
					withdrawalTxns(usrData, initialCashoutWalletBalance);
				} else if (usrData.get("TXNTYPE").equalsIgnoreCase("Balance Enquiry")) {
					balanceEnquiryTxns(usrData, initialCashoutWalletBalance);
				}
			}

		} catch (Exception e) {
			wdriver.navigate().refresh();
			e.printStackTrace();
			Log.info("Test Case Failed");
			Assert.fail();
		}
	}

	// Deposit transaction
	public void depositTxns(Map<String, String> usrData, double walletBalance)
			throws ClassNotFoundException, InterruptedException, ParseException, AWTException {
		wait.until(ExpectedConditions.elementToBeClickable(depositTab));
		depositTab.click();
		Log.info("Deposit tab clicked");
		wait.until(ExpectedConditions.elementToBeClickable(depositDropdown));
		depositDropdown.click();
		Log.info("Dropdown clicked");
		wait.until(ExpectedConditions.elementToBeClickable(dropdownSearch));
		dropdownSearch.sendKeys(usrData.get("BANKNAME"));
		Log.info(usrData.get("BANKNAME") + " entered");
		dropdownSelect(usrData);
		Log.info(usrData.get("BANKNAME") + " selected");
		wait.until(ExpectedConditions.elementToBeClickable(depositMobNum));
		depositMobNum.sendKeys(getAEPSMobNum(usrData.get("MOBNUM")));
		Log.info("Mobile number " + usrData.get("MOBNUM") + " entered");
		depositAadhaarNum.click();

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank MN")) {
			wait.until(ExpectedConditions.visibilityOf(depositMobNumError));
			Assert.assertEquals(depositMobNumError.getText(), "Required Field");
			Log.info(depositMobNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("MN < 10 digits")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Invalid MN")) {
			wait.until(ExpectedConditions.visibilityOf(depositMobNumError));
			Assert.assertEquals(depositMobNumError.getText(), "Invalid mobile number");
			Log.info(depositMobNumError.getText());
		}

		depositAadhaarNum.sendKeys(getAadhaarFromIni(usrData.get("AADHAAR")));
		Log.info("Aadhaar number " + usrData.get("AADHAAR") + " entered");
		depositAmount.click();

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank Aadhaar")) {
			wait.until(ExpectedConditions.visibilityOf(depositAadhaarNumError));
			Assert.assertEquals(depositAadhaarNumError.getText(), "Required Field");
			Log.info(depositAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Aadhaar < 12 digits")) {
			wait.until(ExpectedConditions.visibilityOf(depositAadhaarNumError));
			Assert.assertEquals(depositAadhaarNumError.getText(), "Length should be 12 digits");
			Log.info(depositAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid Aadhaar")) {
			wait.until(ExpectedConditions.visibilityOf(depositAadhaarNumError));
			Assert.assertEquals(depositAadhaarNumError.getText(), "Enter Valid Aadhaar Number");
			Log.info(depositAadhaarNumError.getText());
		}

		depositAmount.sendKeys(usrData.get("AMOUNT"));
		Log.info("Amount " + usrData.get("AMOUNT") + " entered");

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Wallet")) {
			depositAadhaarNum.click();
			wait.until(ExpectedConditions.visibilityOf(depositAmountError));
			Assert.assertEquals(depositAmountError.getText(), "Insufficient wallet balance");
			Log.info(depositAmountError.getText());
			dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Max")) {
			depositAadhaarNum.click();
			wait.until(ExpectedConditions.visibilityOf(depositAmountError));
			Assert.assertEquals(depositAmountError.getText(),
					"Amount entered exceeds your transaction limit ₹ 10,000.00");
			Log.info(depositAmountError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount < Min")) {
			depositAadhaarNum.click();
			wait.until(ExpectedConditions.visibilityOf(depositAmountError));
			Assert.assertEquals(depositAmountError.getText(), "Minimum amount should be ₹ 10.00");
			Log.info(depositAmountError.getText());
		}

		// Field level validation in checkbox field
		if (usrData.get("ASSERTION").equalsIgnoreCase("No Checkbox")) {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			wait.until(ExpectedConditions.visibilityOf(depositConsentError));
			Assert.assertEquals(depositConsentError.getText(), "Required Field");
			Log.info(depositConsentError.getText());
		} else {
			depositConsentCheckbox.click();
			Log.info("Checkbox selected");
		}

		if (usrData.get("SCAN").equalsIgnoreCase("Yes")) {
			Assert.assertEquals("Click to scan fingerprint", depositFingerprintUnscanned.getText());
			depositScanFingerprint.click();
			Log.info("Scan fingerprint button clicked");
			wait.until(ExpectedConditions.elementToBeClickable(depositScanSuccessScreen));
			Assert.assertEquals("Fingerprints scanned successfully", depositFingerprintSuccess.getText());
			Log.info(depositFingerprintSuccess.getText());
			depositFingerprintScreenOkButton.click();
			Log.info("Ok button clicked");
			wait.until(ExpectedConditions.elementToBeClickable(depositFingerprintGreen));
			Assert.assertEquals("Fingerprint scanned successfully!", depositFingerprintGreen.getText());
		}

		if (usrData.get("SUBMIT").equalsIgnoreCase("Yes")) {
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(depositSubmit));
			depositSubmit.click();
			Log.info("Submit button clicked");
			wait.until(ExpectedConditions.elementToBeClickable(mpinScreen));
			if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
				enterMpin.sendKeys(getAuthfromIni("MPIN"));
			} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
				enterMpin.sendKeys("9999");
			}
			Log.info("MPIN entered");
			if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
				dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "0");
			}

			if (usrData.get("MPIN").equalsIgnoreCase("Cancel")) {
				cancelMpin.click();
				wait.until(ExpectedConditions.elementToBeClickable(depositFingerprintGreen));
				Log.info("Cancel button clicked");
			} else {
				submitMpin.click();
				Log.info("MPIN submitted");
//				wait.until(ExpectedConditions.visibilityOf(processingScreen));
//				Log.info("Processing screen displayed");

				if (usrData.get("TXNSCREENBUTTON").equals("Process in Background")) {
					wait.until(ExpectedConditions.visibilityOf(processInBackgroundButton));
					processInBackgroundButton.click();
					Log.info("Process in Background button clicked");
				} else {
					wait.until(ExpectedConditions.visibilityOf(aepsTxnScreen));
					Log.info("Txn screen displayed");

					if (aepsTxnScreen.getText().equalsIgnoreCase("Success!")) {
						if (aepsTxnScreenType.getAttribute("class").contains("completed")) {
							assertionOnDepositSuccessScreen(usrData);
							if (usrData.get("ASSERTION").contains("SMS")) {
								assertionOnDepositSMS(usrData);
							}
							if (usrData.get("TXNSCREENBUTTON").equals("Save")) {
								wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenSaveButton));
								aepsTxnScreenSaveButton.click();
								Log.info("Save button clicked");
							} else if (usrData.get("TXNSCREENBUTTON").equals("Print")) {
								wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenPrintButton));
								aepsTxnScreenPrintButton.click();
								Log.info("Print button clicked");
							}
							wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenDoneButton));
							aepsTxnScreenDoneButton.click();
							Log.info("Done button clicked");
							refreshBalance();
							verifyUpdatedBalanceAfterDepositSuccessTxn(usrData, walletBalance);
						}
					} else if (aepsTxnScreen.getText().equalsIgnoreCase("Failed!")) {
						assertionOnDepositFailedScreen(usrData);
						if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
							wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenExitButton));
							aepsTxnScreenExitButton.click();
							Log.info("Exit button clicked");
						} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Done")) {
							wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenDoneButton));
							aepsTxnScreenDoneButton.click();
							Log.info("Done button clicked");
						} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
							wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenRetryButton));
							aepsTxnScreenRetryButton.click();
							Log.info("Retry button clicked");
							wait.until(ExpectedConditions.elementToBeClickable(depositScanSuccessScreen));
							Assert.assertEquals("Fingerprints scanned successfully",
									depositFingerprintSuccess.getText());
							Log.info(depositFingerprintSuccess.getText());
							depositFingerprintScreenOkButton.click();
							Log.info("Ok button clicked");
							wait.until(ExpectedConditions.elementToBeClickable(depositFingerprintGreen));
							Assert.assertEquals("Fingerprint scanned successfully!", depositFingerprintGreen.getText());
							depositSubmit.click();
							Log.info("Submit button clicked");
							wait.until(ExpectedConditions.elementToBeClickable(mpinScreen));
							if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
								enterMpin.sendKeys(getAuthfromIni("MPIN"));
							} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
								enterMpin.sendKeys("9999");
							}
							Log.info("MPIN entered");
							submitMpin.click();
							Log.info("MPIN submitted");
//							wait.until(ExpectedConditions.visibilityOf(processingScreen));
//							Log.info("Processing screen displayed");
							wait.until(ExpectedConditions.visibilityOf(aepsTxnScreen));
							Log.info("Txn screen displayed");
							assertionOnDepositFailedScreen(usrData);
							if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
								wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenDoneButton));
								aepsTxnScreenDoneButton.click();
								Log.info("Done button clicked");
							} else {
								wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenExitButton));
								aepsTxnScreenExitButton.click();
								Log.info("Exit button clicked");
							}
						}
						if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
							dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
						} else {
							refreshBalance();
							verifyUpdatedBalanceAfterDepositFailTxn(usrData, walletBalance);
						}
					}
				}
			}
		} else {
			depositClear.click();
			Log.info("Clear button clicked");
			wait.until(ExpectedConditions.elementToBeClickable(depositDropdown));
			Log.info("Data cleared");
		}
	}

	// withdrawal transaction
	public void withdrawalTxns(Map<String, String> usrData, double walletBalance)
			throws ClassNotFoundException, InterruptedException, ParseException, AWTException {
		waitForSpinner();
		wait.until(ExpectedConditions.elementToBeClickable(withdrawalTab));
		withdrawalTab.click();
		Log.info("Withdrawal tab clicked");
		wait.until(ExpectedConditions.elementToBeClickable(withdrawalDropdown));
		withdrawalDropdown.click();
		Log.info("Dropdown clicked");
		wait.until(ExpectedConditions.elementToBeClickable(dropdownSearch));
		dropdownSearch.sendKeys(usrData.get("BANKNAME"));
		Log.info(usrData.get("BANKNAME") + " entered");
		dropdownSelect(usrData);
		Log.info(usrData.get("BANKNAME") + " selected");
		wait.until(ExpectedConditions.elementToBeClickable(withdrawalMobNum));
		withdrawalMobNum.sendKeys(usrData.get("MOBNUM"));
		Log.info("Mobile number " + usrData.get("MOBNUM") + " entered");
		withdrawalAadhaarNum.click();

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank MN")) {
			wait.until(ExpectedConditions.visibilityOf(withdrawalMobNumError));
			Assert.assertEquals(withdrawalMobNumError.getText(), "Required Field");
			Log.info(withdrawalMobNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("MN < 10 digits")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Invalid MN")) {
			wait.until(ExpectedConditions.visibilityOf(withdrawalMobNumError));
			Assert.assertEquals(withdrawalMobNumError.getText(), "Invalid mobile number");
			Log.info(withdrawalMobNumError.getText());
		}

		withdrawalAadhaarNum.sendKeys(usrData.get("AADHAAR"));
		Log.info("Aadhaar number " + usrData.get("AADHAAR") + " entered");
		withdrawalAmount.click();

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank Aadhaar")) {
			wait.until(ExpectedConditions.visibilityOf(withdrawalAadhaarNumError));
			Assert.assertEquals(withdrawalAadhaarNumError.getText(), "Required Field");
			Log.info(withdrawalAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Aadhaar < 12 digits")) {
			wait.until(ExpectedConditions.visibilityOf(withdrawalAadhaarNumError));
			Assert.assertEquals(withdrawalAadhaarNumError.getText(), "Length should be 12 digits");
			Log.info(withdrawalAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid Aadhaar")) {
			wait.until(ExpectedConditions.visibilityOf(withdrawalAadhaarNumError));
			Assert.assertEquals(withdrawalAadhaarNumError.getText(), "Enter Valid Aadhaar Number");
			Log.info(withdrawalAadhaarNumError.getText());
		}

		withdrawalAmount.sendKeys(usrData.get("AMOUNT"));
		Log.info("Amount " + usrData.get("AMOUNT") + " entered");

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Wallet")) {
			withdrawalAadhaarNum.click();
			wait.until(ExpectedConditions.visibilityOf(withdrawalAmountError));
			Assert.assertEquals(withdrawalAmountError.getText(), "Insufficient wallet balance");
			Log.info(withdrawalAmountError.getText());
			dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Max")) {
			withdrawalAadhaarNum.click();
			wait.until(ExpectedConditions.visibilityOf(withdrawalAmountError));
			Assert.assertEquals(withdrawalAmountError.getText(),
					"Amount entered exceeds your transaction limit ₹ 10,000.00");
			Log.info(withdrawalAmountError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount < Min")) {
			withdrawalAadhaarNum.click();
			wait.until(ExpectedConditions.visibilityOf(withdrawalAmountError));
			Assert.assertEquals(withdrawalAmountError.getText(), "Minimum amount should be ₹ 10.00");
			Log.info(withdrawalAmountError.getText());
		}

		// Field level validation in checkbox field
		if (usrData.get("ASSERTION").equalsIgnoreCase("No Checkbox")) {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			wait.until(ExpectedConditions.visibilityOf(withdrawalConsentError));
			Assert.assertEquals(withdrawalConsentError.getText(), "Required Field");
			Log.info(withdrawalConsentError.getText());
		} else {
			withdrawalConsentCheckbox.click();
			Log.info("Checkbox selected");
		}

		if (usrData.get("SCAN").equalsIgnoreCase("Yes")) {
			Assert.assertEquals("Click to scan fingerprint", withdrawalFingerprintUnscanned.getText());
			withdrawalScanFingerprint.click();
			Log.info("Scan fingerprint button clicked");
			wait.until(ExpectedConditions.elementToBeClickable(withdrawalScanSuccessScreen));
			Assert.assertEquals("Fingerprints scanned successfully", withdrawalFingerprintSuccess.getText());
			Log.info(withdrawalFingerprintSuccess.getText());
			withdrawalFingerprintScreenOkButton.click();
			Log.info("Ok button clicked");
			wait.until(ExpectedConditions.elementToBeClickable(withdrawalFingerprintGreen));
			Assert.assertEquals("Fingerprint scanned successfully!", withdrawalFingerprintGreen.getText());
		}

		if (usrData.get("SUBMIT").equalsIgnoreCase("Yes")) {
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(withdrawalSubmit));
			withdrawalSubmit.click();
			Log.info("Submit button clicked");

			confirmScreen(usrData);

//			wait.until(ExpectedConditions.visibilityOf(processingScreen));
//			Log.info("Processing screen displayed");

			if (usrData.get("TXNSCREENBUTTON").equals("Process in Background")) {
				wait.until(ExpectedConditions.visibilityOf(processInBackgroundButton));
				processInBackgroundButton.click();
				Log.info("Process in Background button clicked");
			} else {
				wait.until(ExpectedConditions.visibilityOf(aepsTxnScreen));
				Log.info("Txn screen displayed");

				if (aepsTxnScreen.getText().equalsIgnoreCase("Success!")) {
					if (aepsTxnScreenType.getAttribute("class").contains("completed")) {
						assertionOnWithdrawalSuccessScreen(usrData);
						if (usrData.get("ASSERTION").contains("SMS")) {
							assertionOnWithdrawalSMS(usrData);
						}
						if (usrData.get("TXNSCREENBUTTON").equals("Save")) {
							wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenSaveButton));
							aepsTxnScreenSaveButton.click();
							Log.info("Save button clicked");
						} else if (usrData.get("TXNSCREENBUTTON").equals("Print")) {
							wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenPrintButton));
							aepsTxnScreenPrintButton.click();
							Log.info("Print button clicked");
						}
						wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenDoneButton));
						aepsTxnScreenDoneButton.click();
						Log.info("Done button clicked");
						refreshBalance();
						verifyUpdatedBalanceAfterWithdrawalSuccessTxn(usrData, walletBalance);
					}
				} else if (aepsTxnScreen.getText().equalsIgnoreCase("Failed!")) {
					assertionOnWithdrawalFailedScreen(usrData);
					if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
						wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenExitButton));
						aepsTxnScreenExitButton.click();
						Log.info("Exit button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Done")) {
						wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenDoneButton));
						aepsTxnScreenDoneButton.click();
						Log.info("Done button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
						wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenRetryButton));
						aepsTxnScreenRetryButton.click();
						Log.info("Retry button clicked");
						wait.until(ExpectedConditions.elementToBeClickable(withdrawalScanSuccessScreen));
						Assert.assertEquals("Fingerprints scanned successfully",
								withdrawalFingerprintSuccess.getText());
						Log.info(withdrawalFingerprintSuccess.getText());
						withdrawalFingerprintScreenOkButton.click();
						Log.info("Ok button clicked");
						wait.until(ExpectedConditions.elementToBeClickable(withdrawalFingerprintGreen));
						Assert.assertEquals("Fingerprint scanned successfully!", withdrawalFingerprintGreen.getText());
						wait.until(ExpectedConditions.elementToBeClickable(withdrawalSubmit));
						withdrawalSubmit.click();
						Log.info("Submit button clicked");
						
						confirmScreen(usrData);
						
//						wait.until(ExpectedConditions.visibilityOf(processingScreen));
//						Log.info("Processing screen displayed");
						wait.until(ExpectedConditions.visibilityOf(aepsTxnScreen));
						Log.info("Txn screen displayed");
						assertionOnWithdrawalFailedScreen(usrData);
						if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
							wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenDoneButton));
							aepsTxnScreenDoneButton.click();
							Log.info("Done button clicked");
						} else {
							wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenExitButton));
							aepsTxnScreenExitButton.click();
							Log.info("Exit button clicked");
						}
					}
					if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
						if (usrData.get("TXNTYPE").equalsIgnoreCase("Deposit")) {
							dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
						} else if (usrData.get("TXNTYPE").equalsIgnoreCase("Withdrawal")) {
							dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "1000000");
						}
					} else {
						refreshBalance();
//						verifyUpdatedBalanceAfterWithdrawalFailTxn(usrData, walletBalance);
					}
				} else {
					withdrawalClear.click();
					Log.info("Clear button clicked");
					wait.until(ExpectedConditions.elementToBeClickable(withdrawalDropdown));
					Log.info("Data cleared");
				}
			}
		}
	}

	// Balance Enquiry transaction
	public void balanceEnquiryTxns(Map<String, String> usrData, double walletBalance)
			throws ClassNotFoundException, InterruptedException, ParseException, AWTException {
		waitForSpinner();
		wait.until(ExpectedConditions.elementToBeClickable(balanceEnquiryDropdown));
		balanceEnquiryDropdown.click();
		Log.info("Dropdown clicked");
		wait.until(ExpectedConditions.elementToBeClickable(dropdownSearch));
		dropdownSearch.sendKeys(usrData.get("BANKNAME"));
		Log.info(usrData.get("BANKNAME") + " entered");
		dropdownSelect(usrData);
		Log.info(usrData.get("BANKNAME") + " selected");
		wait.until(ExpectedConditions.elementToBeClickable(balanceEnquiryMobNum));
		balanceEnquiryMobNum.sendKeys(usrData.get("MOBNUM"));
		Log.info("Mobile number " + usrData.get("MOBNUM") + " entered");
		balanceEnquiryAadhaarNum.click();

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank MN")) {
			wait.until(ExpectedConditions.visibilityOf(balanceEnquiryMobNumError));
			Assert.assertEquals(balanceEnquiryMobNumError.getText(), "Required Field");
			Log.info(balanceEnquiryMobNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("MN < 10 digits")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Invalid MN")) {
			wait.until(ExpectedConditions.visibilityOf(balanceEnquiryMobNumError));
			Assert.assertEquals(balanceEnquiryMobNumError.getText(), "Invalid mobile number");
			Log.info(balanceEnquiryMobNumError.getText());
		}

		balanceEnquiryAadhaarNum.sendKeys(usrData.get("AADHAAR"));
		Log.info("Aadhaar number " + usrData.get("AADHAAR") + " entered");
		balanceEnquiryMobNum.click();

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank Aadhaar")) {
			wait.until(ExpectedConditions.visibilityOf(balanceEnquiryAadhaarNumError));
			Assert.assertEquals(balanceEnquiryAadhaarNumError.getText(), "Required Field");
			Log.info(balanceEnquiryAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Aadhaar < 12 digits")) {
			wait.until(ExpectedConditions.visibilityOf(balanceEnquiryAadhaarNumError));
			Assert.assertEquals(balanceEnquiryAadhaarNumError.getText(), "Length should be 12 digits");
			Log.info(balanceEnquiryAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid Aadhaar")) {
			wait.until(ExpectedConditions.visibilityOf(balanceEnquiryAadhaarNumError));
			Assert.assertEquals(balanceEnquiryAadhaarNumError.getText(), "Enter Valid Aadhaar Number");
			Log.info(balanceEnquiryAadhaarNumError.getText());
		}

		// Field level validation in checkbox field
		if (usrData.get("ASSERTION").equalsIgnoreCase("No Checkbox")) {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			wait.until(ExpectedConditions.visibilityOf(balanceEnquiryConsentError));
			Assert.assertEquals(balanceEnquiryConsentError.getText(), "Required Field");
			Log.info(balanceEnquiryConsentError.getText());
		} else {
			balanceEnquiryConsentCheckbox.click();
			Log.info("Checkbox selected");
		}

		if (usrData.get("SCAN").equalsIgnoreCase("Yes")) {
			Assert.assertEquals("Click to scan fingerprint", balanceEnquiryFingerprintUnscanned.getText());
			balanceEnquiryScanFingerprint.click();
			Log.info("Scan fingerprint button clicked");
			wait.until(ExpectedConditions.elementToBeClickable(balanceEnquiryScanSuccessScreen));
			Assert.assertEquals("Fingerprints scanned successfully", balanceEnquiryFingerprintSuccess.getText());
			Log.info(balanceEnquiryFingerprintSuccess.getText());
			balanceEnquiryFingerprintScreenOkButton.click();
			Log.info("Ok button clicked");
			wait.until(ExpectedConditions.elementToBeClickable(balanceEnquiryFingerprintGreen));
			Assert.assertEquals("Fingerprint scanned successfully!", balanceEnquiryFingerprintGreen.getText());
		}

		if (usrData.get("SUBMIT").equalsIgnoreCase("Yes")) {
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOf(balanceEnquirySubmit));
			balanceEnquirySubmit.click();
			Log.info("Submit button clicked");
//			wait.until(ExpectedConditions.visibilityOf(processingScreen));
//			Log.info("Processing screen displayed");

			if (usrData.get("TXNSCREENBUTTON").equals("Process in Background")) {
				wait.until(ExpectedConditions.visibilityOf(processInBackgroundButton));
				processInBackgroundButton.click();
				Log.info("Process in Background button clicked");
			} else {
				wait.until(ExpectedConditions.visibilityOf(aepsTxnScreen));
				Log.info("Txn screen displayed");

				if (aepsTxnScreen.getText().equalsIgnoreCase("Success!")) {
					if (aepsTxnScreenType.getAttribute("class").contains("completed")) {
						assertionOnBalanceEnquirySuccessScreen(usrData);
						if (usrData.get("ASSERTION").contains("SMS")) {
							assertionOnBalanceEnquirySMS(usrData);
						}
						if (usrData.get("TXNSCREENBUTTON").equals("Save")) {
							wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenSaveButton));
							aepsTxnScreenSaveButton.click();
							Log.info("Save button clicked");
						} else if (usrData.get("TXNSCREENBUTTON").equals("Print")) {
							wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenPrintButton));
							aepsTxnScreenPrintButton.click();
							Log.info("Print button clicked");
						}
						wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenDoneButton));
						aepsTxnScreenDoneButton.click();
						Log.info("Done button clicked");
						refreshBalance();
//						verifyUpdatedBalanceAfterBalanceEnquirySuccessTxn(usrData, walletBalance);
					}
				} else if (aepsTxnScreen.getText().equalsIgnoreCase("Failed!")) {
					assertionOnBalanceEnquiryFailedScreen(usrData);
					if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
						wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenExitButton));
						aepsTxnScreenExitButton.click();
						Log.info("Exit button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Done")) {
						wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenDoneButton));
						aepsTxnScreenDoneButton.click();
						Log.info("Done button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
						wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenRetryButton));
						aepsTxnScreenRetryButton.click();
						Log.info("Retry button clicked");
						wait.until(ExpectedConditions.elementToBeClickable(balanceEnquiryScanSuccessScreen));
						Assert.assertEquals("Fingerprints scanned successfully",
								balanceEnquiryFingerprintSuccess.getText());
						Log.info(balanceEnquiryFingerprintSuccess.getText());
						balanceEnquiryFingerprintScreenOkButton.click();
						Log.info("Ok button clicked");
						wait.until(ExpectedConditions.elementToBeClickable(balanceEnquiryFingerprintGreen));
						Assert.assertEquals("Fingerprint scanned successfully!",
								balanceEnquiryFingerprintGreen.getText());
						balanceEnquirySubmit.click();
						Log.info("Submit button clicked");
//						wait.until(ExpectedConditions.visibilityOf(processingScreen));
//						Log.info("Processing screen displayed");
						wait.until(ExpectedConditions.visibilityOf(aepsTxnScreen));
						Log.info("Txn screen displayed");
						assertionOnBalanceEnquiryFailedScreen(usrData);
						if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
							wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenDoneButton));
							aepsTxnScreenDoneButton.click();
							Log.info("Done button clicked");
						} else {
							wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenExitButton));
							aepsTxnScreenExitButton.click();
							Log.info("Exit button clicked");
						}
					}
					refreshBalance();
					verifyUpdatedBalanceAfterBalanceEnquiryFailTxn(usrData, walletBalance);
				} else {
					balanceEnquiryClear.click();
					Log.info("Clear button clicked");
					wait.until(ExpectedConditions.elementToBeClickable(balanceEnquiryDropdown));
					Log.info("Data cleared");
				}
			}
		}
	}

	public void dropdownSelect(Map<String, String> usrData) {
		String dropdownXpath = "//li[contains(text(),'" + usrData.get("BANKNAME") + "')]";
		WebElement dropdownValue = wdriver.findElement(By.xpath(dropdownXpath));
		dropdownValue.click();
	}

	// Get Partner name
	public String partner() {
		return "YBL";
	}

	// Get mobile number from Ini file
	public String mobileNumFromIni() {
		return getLoginMobileFromIni("RetailerMobNum");
	}

	// Verify details on success screen
	public void assertionOnDepositSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("ASSERTION").contains("Deemed Success")) {
			Assert.assertEquals(aepsDeemedTxnScreenMessage.getText(), "deemed success:error code 91");
			Log.info(aepsDeemedTxnScreenMessage.getText());
		} else {
			Assert.assertEquals(aepsTxnScreenMessage.getText(), "Cash deposited to customer successfully");
			Log.info(aepsTxnScreenMessage.getText());
		}
		Assert.assertEquals(replaceSymbols(aepsTxnScreenTxnAmount.getText()), usrData.get("AMOUNT") + ".00");
		Log.info("Transferred Amount: " + replaceSymbols(aepsTxnScreenTxnAmount.getText()));
		txnDetailsFromIni("StoreTxfAmount", usrData.get("AMOUNT"));
		Assert.assertEquals(replaceSymbols(aepsTxnScreenCharges.getText()), "0.00");
		Log.info("Charges: " + replaceSymbols(aepsTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreCharges", "0.00");
		txnDetailsFromIni("StoreTxnRefNo", depositRefNo.getText());
		String comm = dbUtils.getAepsComm(usrData.get("AMOUNT"), "Deposit", partner());
		txnDetailsFromIni("StoreComm", comm);
		double tds = Double.parseDouble(comm) * Double.parseDouble(dbUtils.getTDSPercentage(mobileNumFromIni()))
				/ 10000;
		txnDetailsFromIni("StoreTds", df.format(tds));
		double amount = Double.parseDouble(usrData.get("AMOUNT"));
		double charges = 0.00;
		double totalAmount = amount + charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(aepsTxnScreenTotalAmount.getText()), cashToBeCollected);
		Log.info("Cash to be Collected: " + replaceSymbols(aepsTxnScreenTotalAmount.getText()));
	}

	// Verify details on failure screen
	public void assertionOnDepositFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
			Assert.assertEquals(aepsTxnScreenFailureReason.getText(),
					"Agent Wallet Debit Failed :Insufficient account balance.");
			Log.info(aepsTxnScreenFailureReason.getText());
		} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
			Assert.assertEquals(aepsTxnScreenFailureReason.getText(), "Authentication Failed Invalid Agent MPIN");
			Log.info(aepsTxnScreenFailureReason.getText());
		} else {
			Assert.assertEquals(aepsTxnScreenFailureReason.getText(), "Failed to perform transaction(M3)");
			Log.info(aepsTxnScreenFailureReason.getText());
			Assert.assertEquals(replaceSymbols(aepsTxnScreenFailedAmount.getText()), usrData.get("AMOUNT") + ".00");
			Log.info("Failed Amount: " + replaceSymbols(aepsTxnScreenFailedAmount.getText()));
		}
	}

	// Assertion after success screen is displayed
	public void verifyUpdatedBalanceAfterDepositSuccessTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double comm = Double.parseDouble(txnDetailsFromIni("GetComm", ""));
		double tds = Double.parseDouble(txnDetailsFromIni("GetTds", ""));
		double newWalletBal = initialWalletBalance - amount - charges + comm - tds;
		String newWalletBalance = df.format(newWalletBal);
		Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
		Log.info("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
	}

	// Assertion after failure screen is displayed
	public void verifyUpdatedBalanceAfterDepositFailTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		String newWalletBalance = df.format(initialWalletBalance);
		Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
		Log.info("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
	}

	// SMS assertion
	public void assertionOnDepositSMS(Map<String, String> usrData) throws ClassNotFoundException {
		String successSMS = "Cash Deposit: INR " + usrData.get("AMOUNT") + ".00 deposited successfully in your "
				+ usrData.get("BANKNAME") + " a/c linked with Aadhaar  XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + ". Reference Number: "
				+ txnDetailsFromIni("GetTxnRefNo", "") + ", Charges: 0.00, Date: " + dbUtils.aepsTxnDate() + " IST";
		String deemedSuccessSMS = "Cash Deposit of INR " + usrData.get("AMOUNT") + ".00 to your "
				+ usrData.get("BANKNAME") + " a/c linked with Aadhaar  XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12)
				+ " has been initiated and will be processed shortly. Reference Number: "
				+ txnDetailsFromIni("GetTxnRefNo", "") + ", Charges: 0.00, Date: " + dbUtils.aepsTxnDate() + " IST";

		if (usrData.get("ASSERTION").equalsIgnoreCase("Success SMS")) {
			Assert.assertEquals(successSMS, dbUtils.sms());
			Log.info(successSMS);
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Deemed Success SMS")) {
			Assert.assertEquals(deemedSuccessSMS, dbUtils.sms());
			Log.info(deemedSuccessSMS);
		}
	}

	// FCM assertion
	public void assertionOnDepositFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successFCMHeading = "Cash Deposit: SUCCESS";
		String failFCMHeading = "Cash Deposit: FAIL";

		String balance = df.format(getInitialBalance("retailer"));
		String successFCMContent = "Request of cash deposit: INR " + usrData.get("AMOUNT") + ".00 deposited in "
				+ usrData.get("BANKNAME") + " a/c linked with Aadhaar XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + "; Charges: INR 0.00; Date " + dbUtils.aepsTxnDate()
				+ " IST Response code:(00)SUCCESS Reference No: " + txnDetailsFromIni("GetTxnRefNo", "")
				+ " Agent Wallet Balance after txn: INR " + balance;
		String deemedSuccessFCMContent = "Request of cash deposit: INR " + usrData.get("AMOUNT") + ".00 deposited in "
				+ usrData.get("BANKNAME") + " a/c linked with Aadhaar XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + "; Charges: INR 0.00; Date " + dbUtils.aepsTxnDate()
				+ " IST Response code:(Error Code 91)SUCCESS Reference No: " + txnDetailsFromIni("GetTxnRefNo", "")
				+ " Agent Wallet Balance after txn: INR " + balance;
		String failFCMContent = "deposit for customer account linked with aadhaar no. XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + " has failed : Error - Failed to perform transaction(M3)";

		switch (usrData.get("ASSERTION")) {
		case "Success FCM":
			fcmMethod(successFCMHeading, successFCMContent);
			break;
		case "Deemed Success FCM":
			fcmMethod(successFCMHeading, deemedSuccessFCMContent);
			break;
		case "Failed FCM":
			fcmMethod(failFCMHeading, failFCMContent);
			break;
		}
	}

	// Verify details on success screen
	public void assertionOnWithdrawalSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenMessage.getText(), "Cash withdrawn from customer successfully");
		Log.info(aepsTxnScreenMessage.getText());
		Assert.assertEquals(replaceSymbols(aepsTxnScreenTxnAmount.getText()), usrData.get("AMOUNT") + ".00");
		Log.info("Withdrawn Amount: " + replaceSymbols(aepsTxnScreenTxnAmount.getText()));
		txnDetailsFromIni("StoreTxfAmount", usrData.get("AMOUNT"));
		Assert.assertEquals(replaceSymbols(aepsTxnScreenCharges.getText()), "0.00");
		Log.info("Charges: " + replaceSymbols(aepsTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreCharges", "0.00");
		txnDetailsFromIni("StoreTxnRefNo", withdrawalRefNo.getText());
		String comm = dbUtils.getAepsComm(usrData.get("AMOUNT"), "Deposit", partner());
		txnDetailsFromIni("StoreComm", comm);
		double tds = Double.parseDouble(comm) * Double.parseDouble(dbUtils.getTDSPercentage(mobileNumFromIni()))
				/ 10000;
		txnDetailsFromIni("StoreTds", df.format(tds));
		double amount = Double.parseDouble(usrData.get("AMOUNT"));
		double charges = 0.00;
		double totalAmount = amount + charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(aepsTxnScreenTotalAmount.getText()), cashToBeCollected);
		Log.info("Cash to be Given: " + replaceSymbols(aepsTxnScreenTotalAmount.getText()));
	}

	// Verify details on failure screen
	public void assertionOnWithdrawalFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenFailureReason.getText(), "Failed to perform transaction(M3)");
		Log.info(aepsTxnScreenFailureReason.getText());
		Assert.assertEquals(replaceSymbols(aepsTxnScreenFailedAmount.getText()), usrData.get("AMOUNT") + ".00");
		Log.info("Failed Amount: " + replaceSymbols(aepsTxnScreenFailedAmount.getText()));
	}

	// Assertion after success screen is displayed
	public void verifyUpdatedBalanceAfterWithdrawalSuccessTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double comm = Double.parseDouble(txnDetailsFromIni("GetComm", ""));
		double tds = Double.parseDouble(txnDetailsFromIni("GetTds", ""));
		double newWalletBal = initialWalletBalance + amount - charges + comm - tds;
		String newWalletBalance = df.format(newWalletBal);
		Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
		Log.info("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
	}

	// Assertion after failure screen is displayed
	public void verifyUpdatedBalanceAfterWithdrawalFailTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		String newWalletBalance = df.format(initialWalletBalance);
		Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
		Log.info("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
	}

	// SMS assertion
	public void assertionOnWithdrawalSMS(Map<String, String> usrData) throws ClassNotFoundException {
		String successSMS = "Cash Withdrawal: INR " + usrData.get("AMOUNT") + ".00 withdrawn from your "
				+ usrData.get("BANKNAME") + " a/c linked to Aadhaar  XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + ". Reference Number: "
				+ txnDetailsFromIni("GetTxnRefNo", "") + ", Charges: 0.00, Date: " + dbUtils.aepsTxnDate() + " IST";

		if (usrData.get("ASSERTION").equalsIgnoreCase("Success SMS")) {
			Assert.assertEquals(successSMS, dbUtils.sms());
			Log.info(successSMS);
		}
	}

	// FCM assertion
	public void assertionOnWithdrawalFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successFCMHeading = "Cash Withdrawal: SUCCESS";
		String failFCMHeading = "Cash Withdrawal: FAIL";

		String balance = df.format(getInitialBalance("cashout"));
		String successFCMContent = "Request for cash withdrawal: INR " + usrData.get("AMOUNT")
				+ ".00 withdrawn from your " + usrData.get("BANKNAME") + " a/c linked with Aadhaar XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + "; Charges INR 0.00; Date " + dbUtils.aepsTxnDate()
				+ " IST Response code: (00) SUCCESS Reference No: " + txnDetailsFromIni("GetTxnRefNo", "")
				+ " Balance after txn: INR " + balance;
		String failFCMContent = "Withdrawal for customer with aadhaar XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + " has failed : Failed to perform transaction(M3)";

		switch (usrData.get("ASSERTION")) {
		case "Success FCM":
			fcmMethod(successFCMHeading, successFCMContent);
			break;
		case "Failed FCM":
			fcmMethod(failFCMHeading, failFCMContent);
			break;
		}
	}

	// Verify details on success screen
	public void assertionOnBalanceEnquirySuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenMessage.getText(), "Balance enquired successfully");
		Log.info(aepsTxnScreenMessage.getText());
		txnDetailsFromIni("StoreTxnRefNo", balanceEnquiryRefNo.getText());
	}

	// Verify details on failure screen
	public void assertionOnBalanceEnquiryFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenFailureReason.getText(), "Failed to perform transaction(M3)");
		Log.info(aepsTxnScreenFailureReason.getText());
	}

	// Assertion after success screen is displayed
	public void verifyUpdatedBalanceAfterBalanceEnquirySuccessTxn(Map<String, String> usrData,
			double initialWalletBalance) throws ClassNotFoundException {
		String newWalletBalance = df.format(initialWalletBalance);
		Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
		Log.info("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
	}

	// Assertion after failure screen is displayed
	public void verifyUpdatedBalanceAfterBalanceEnquiryFailTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		String newWalletBalance = df.format(initialWalletBalance);
		Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
		Log.info("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
	}

	// SMS assertion
	public void assertionOnBalanceEnquirySMS(Map<String, String> usrData) throws ClassNotFoundException {
		String successSMS = "Balance in " + usrData.get("BANKNAME") + " a/c linked with Aadhaar  XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + " as on " + dbUtils.aepsTxnDate()
				+ " IST is Led Bal: INR 5944.85, Ava Bal: INR 5944.85, Ref No: " + txnDetailsFromIni("GetTxnRefNo", "");

		if (usrData.get("ASSERTION").equalsIgnoreCase("Success SMS")) {
			Assert.assertEquals(successSMS, dbUtils.sms());
			Log.info(successSMS);
		}
	}

	// FCM assertion
	public void assertionOnBalanceEnquiryFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successFCMHeading = "Balance Enquiry: SUCCESS";
		String failFCMHeading = "Balance Enquiry: FAIL";

		String successFCMContent = "Balance in " + usrData.get("BANKNAME") + " a/c linked with Aadhaar XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + " as on " + dbUtils.aepsTxnDate()
				+ " IST is Led Bal: 5944.85, Ava Bal: 5944.85 Response code: (00) SUCCESS Reference No: "
				+ txnDetailsFromIni("GetTxnRefNo", "");
		String failFCMContent = "Balance Enquiry for customer with Aadhaar XXXX XXXX "
				+ usrData.get("AADHAAR").substring(8, 12) + " has failed : Failed to perform transaction(M3)";

		switch (usrData.get("ASSERTION")) {
		case "Success FCM":
			fcmMethod(successFCMHeading, successFCMContent);
			break;
		case "Failed FCM":
			fcmMethod(failFCMHeading, failFCMContent);
			break;
		}
	}

	public void fcmMethod(String heading, String content) {
		Assert.assertEquals(fcmHeading.getText(), heading);
		Assert.assertEquals(fcmContent.getText(), content);
		Log.info(fcmHeading.getText());
		Log.info(fcmContent.getText());
	}

	// Wait for screen to complete loading
	public void waitForSpinner() {
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'spinner')]/parent::div")));
		Log.info("Please wait...");
	}

	// Remove rupee symbol and comma from the string
	public String replaceSymbols(String element) {
		String editedElement = element.replaceAll("₹", "").replaceAll(",", "").trim();
		return editedElement;
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

	// Confirm screen
	public void confirmScreen(Map<String, String> usrData) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(confirmScreen));
		Log.info("Confirm the details screen displayed");
		Assert.assertEquals(replaceSymbols(confirmScreenAmount.getText()), usrData.get("AMOUNT") + ".00");
		wait.until(ExpectedConditions.visibilityOf(confirmScreenSubmit));
		confirmScreenSubmit.click();
		Thread.sleep(2000);
		Log.info("Submit button clicked");
	}
}
