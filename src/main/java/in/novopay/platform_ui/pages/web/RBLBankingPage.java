package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
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
import org.testng.Assert;
import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.Log;
import in.novopay.platform_ui.utils.ServerUtils;

public class RBLBankingPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	ServerUtils srvUtils = new ServerUtils();
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

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

	@FindBy(xpath = "//a[contains(@href,'banking')]/span[2][contains(text(),'Banking')]")
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
	public RBLBankingPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void rblBanking(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			clickElement(menu);
			commonUtils.refreshBalance();
			scrollElementDown(scrollBar, banking);
			Log.info("Banking option clicked");
			waitUntilElementIsVisible(pageTitle);
			System.out.println(pageTitle.getText() + " page displayed");
			clickElement(menu);

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
				commonUtils.refreshBalance(); // refresh wallet balances
			}

			commonUtils.displayInitialBalance("retailer"); // display main wallet balance
			commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

			double initialWalletBalance = commonUtils.getInitialBalance("retailer"); // store main wallet balance
			double initialCashoutBalance = commonUtils.getInitialBalance("cashout"); // store cashout wallet balance

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
					depositTxns(usrData, initialWalletBalance);
				} else if (usrData.get("TXNTYPE").equalsIgnoreCase("Withdrawal")) {
					withdrawalTxns(usrData, initialCashoutBalance);
				} else if (usrData.get("TXNTYPE").equalsIgnoreCase("Balance Enquiry")) {
					balanceEnquiryTxns(usrData, initialCashoutBalance);
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
			throws ClassNotFoundException, InterruptedException, ParseException {
		waitUntilElementIsClickableAndClickTheElement(depositTab);
		Log.info("Deposit tab clicked");
		waitUntilElementIsClickableAndClickTheElement(depositDropdown);
		Log.info("Dropdown clicked");
		waitUntilElementIsClickableAndClickTheElement(dropdownSearch);
		dropdownSearch.sendKeys(usrData.get("BANKNAME"));
		Log.info(usrData.get("BANKNAME") + " entered");
		dropdownSelect(usrData);
		Log.info(usrData.get("BANKNAME") + " selected");
		waitUntilElementIsClickableAndClickTheElement(depositMobNum);
		depositMobNum.sendKeys(getAEPSMobNum(usrData.get("MOBNUM")));
		Log.info("Mobile number " + usrData.get("MOBNUM") + " entered");
		depositAadhaarNum.click();

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank MN")) {
			waitUntilElementIsVisible(depositMobNumError);
			Assert.assertEquals(depositMobNumError.getText(), "Required Field");
			Log.info(depositMobNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("MN < 10 digits")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Invalid MN")) {
			waitUntilElementIsVisible(depositMobNumError);
			Assert.assertEquals(depositMobNumError.getText(), "Invalid mobile number");
			Log.info(depositMobNumError.getText());
		}

		depositAadhaarNum.sendKeys(getAadhaarFromIni(usrData.get("AADHAAR")));
		Log.info("Aadhaar number " + usrData.get("AADHAAR") + " entered");
		depositAmount.click();

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank Aadhaar")) {
			waitUntilElementIsVisible(depositAadhaarNumError);
			Assert.assertEquals(depositAadhaarNumError.getText(), "Required Field");
			Log.info(depositAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Aadhaar < 12 digits")) {
			waitUntilElementIsVisible(depositAadhaarNumError);
			Assert.assertEquals(depositAadhaarNumError.getText(), "Length should be 12 digits");
			Log.info(depositAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid Aadhaar")) {
			waitUntilElementIsVisible(depositAadhaarNumError);
			Assert.assertEquals(depositAadhaarNumError.getText(), "Enter Valid Aadhaar Number");
			Log.info(depositAadhaarNumError.getText());
		}

		depositAmount.sendKeys(usrData.get("AMOUNT"));
		Log.info("Amount " + usrData.get("AMOUNT") + " entered");

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Wallet")) {
			depositAadhaarNum.click();
			waitUntilElementIsVisible(depositAmountError);
			Assert.assertEquals(depositAmountError.getText(), "Insufficient wallet balance");
			Log.info(depositAmountError.getText());
			dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Max")) {
			depositAadhaarNum.click();
			waitUntilElementIsVisible(depositAmountError);
			Assert.assertEquals(depositAmountError.getText(),
					"Amount entered exceeds your transaction limit ₹ 10,000.00");
			Log.info(depositAmountError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount < Min")) {
			depositAadhaarNum.click();
			waitUntilElementIsVisible(depositAmountError);
			Assert.assertEquals(depositAmountError.getText(), "Minimum amount should be ₹ 10.00");
			Log.info(depositAmountError.getText());
		}

		if (usrData.get("SCAN").equalsIgnoreCase("Yes")) {
			Assert.assertEquals("Click to scan fingerprint", depositFingerprintUnscanned.getText());
			depositScanFingerprint.click();
			Log.info("Scan fingerprint button clicked");
			waitUntilElementIsVisible(depositScanSuccessScreen);
			Assert.assertEquals("Fingerprints scanned successfully", depositFingerprintSuccess.getText());
			Log.info(depositFingerprintSuccess.getText());
			depositFingerprintScreenOkButton.click();
			Log.info("Ok button clicked");
			waitUntilElementIsVisible(depositFingerprintGreen);
			Assert.assertEquals("Fingerprint scanned successfully!", depositFingerprintGreen.getText());
		}

		if (usrData.get("SUBMIT").equalsIgnoreCase("Yes")) {
			Thread.sleep(1000);
			waitUntilElementIsClickableAndClickTheElement(depositSubmit);
			Log.info("Submit button clicked");
			waitUntilElementIsClickableAndClickTheElement(mpinScreen);
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
				waitUntilElementIsVisible(depositFingerprintGreen);
				Log.info("Cancel button clicked");
			} else {
				submitMpin.click();
				Log.info("MPIN submitted");
//				waitUntilElementIsVisible(processingScreen));
//				Log.info("Processing screen displayed");

				if (usrData.get("TXNSCREENBUTTON").equals("Process in Background")) {
					waitUntilElementIsVisible(processInBackgroundButton);
					processInBackgroundButton.click();
					Log.info("Process in Background button clicked");
				} else {
					waitUntilElementIsVisible(aepsTxnScreen);
					Log.info("Txn screen displayed");

					if (aepsTxnScreen.getText().equalsIgnoreCase("Success!")) {
						if (aepsTxnScreenType.getAttribute("class").contains("completed")) {
							assertionOnDepositSuccessScreen(usrData);
							if (usrData.get("ASSERTION").contains("SMS")) {
								assertionOnDepositSMS(usrData);
							}
							if (usrData.get("TXNSCREENBUTTON").equals("Save")) {
								waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenSaveButton);
								Log.info("Save button clicked");
							} else if (usrData.get("TXNSCREENBUTTON").equals("Print")) {
								waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenPrintButton);
								Log.info("Print button clicked");
							}
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
							Log.info("Done button clicked");
							commonUtils.refreshBalance();
							verifyUpdatedBalanceAfterDepositSuccessTxn(usrData, walletBalance);
						}
					} else if (aepsTxnScreen.getText().equalsIgnoreCase("Failed!")) {
						assertionOnDepositFailedScreen(usrData);
						if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenExitButton);
							Log.info("Exit button clicked");
						} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Done")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
							Log.info("Done button clicked");
						} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenRetryButton);
							Log.info("Retry button clicked");
							waitUntilElementIsVisible(depositScanSuccessScreen);
							Assert.assertEquals("Fingerprints scanned successfully",
									depositFingerprintSuccess.getText());
							Log.info(depositFingerprintSuccess.getText());
							depositFingerprintScreenOkButton.click();
							Log.info("Ok button clicked");
							waitUntilElementIsVisible(depositFingerprintGreen);
							Assert.assertEquals("Fingerprint scanned successfully!", depositFingerprintGreen.getText());
							depositSubmit.click();
							Log.info("Submit button clicked");
							waitUntilElementIsClickableAndClickTheElement(mpinScreen);
							if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
								enterMpin.sendKeys(getAuthfromIni("MPIN"));
							} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
								enterMpin.sendKeys("9999");
							}
							Log.info("MPIN entered");
							submitMpin.click();
							Log.info("MPIN submitted");
//							waitUntilElementIsVisible(processingScreen));
//							Log.info("Processing screen displayed");
							waitUntilElementIsVisible(aepsTxnScreen);
							Log.info("Txn screen displayed");
							assertionOnDepositFailedScreen(usrData);
							if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
								waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
								Log.info("Done button clicked");
							} else {
								waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenExitButton);
								Log.info("Exit button clicked");
							}
						}
						if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
							dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
						} else {
							commonUtils.refreshBalance();
							verifyUpdatedBalanceAfterDepositFailTxn(usrData, walletBalance);
						}
					}
				}
			}
		} else {
			depositClear.click();
			Log.info("Clear button clicked");
			waitUntilElementIsVisible(depositDropdown);
			Log.info("Data cleared");
		}
	}

	// withdrawal transaction
	public void withdrawalTxns(Map<String, String> usrData, double walletBalance)
			throws ClassNotFoundException, InterruptedException, ParseException {
		commonUtils.waitForSpinner();
		waitUntilElementIsClickableAndClickTheElement(withdrawalTab);
		Log.info("Withdrawal tab clicked");
		waitUntilElementIsClickableAndClickTheElement(withdrawalDropdown);
		Log.info("Dropdown clicked");
		waitUntilElementIsClickableAndClickTheElement(dropdownSearch);
		dropdownSearch.sendKeys(usrData.get("BANKNAME"));
		Log.info(usrData.get("BANKNAME") + " entered");
		dropdownSelect(usrData);
		Log.info(usrData.get("BANKNAME") + " selected");
		waitUntilElementIsClickableAndClickTheElement(withdrawalMobNum);
		withdrawalMobNum.sendKeys(usrData.get("MOBNUM"));
		Log.info("Mobile number " + usrData.get("MOBNUM") + " entered");
		withdrawalAadhaarNum.click();

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank MN")) {
			waitUntilElementIsVisible(withdrawalMobNumError);
			Assert.assertEquals(withdrawalMobNumError.getText(), "Required Field");
			Log.info(withdrawalMobNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("MN < 10 digits")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Invalid MN")) {
			waitUntilElementIsVisible(withdrawalMobNumError);
			Assert.assertEquals(withdrawalMobNumError.getText(), "Invalid mobile number");
			Log.info(withdrawalMobNumError.getText());
		}

		withdrawalAadhaarNum.sendKeys(usrData.get("AADHAAR"));
		Log.info("Aadhaar number " + usrData.get("AADHAAR") + " entered");
		withdrawalAmount.click();

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank Aadhaar")) {
			waitUntilElementIsVisible(withdrawalAadhaarNumError);
			Assert.assertEquals(withdrawalAadhaarNumError.getText(), "Required Field");
			Log.info(withdrawalAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Aadhaar < 12 digits")) {
			waitUntilElementIsVisible(withdrawalAadhaarNumError);
			Assert.assertEquals(withdrawalAadhaarNumError.getText(), "Length should be 12 digits");
			Log.info(withdrawalAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid Aadhaar")) {
			waitUntilElementIsVisible(withdrawalAadhaarNumError);
			Assert.assertEquals(withdrawalAadhaarNumError.getText(), "Enter Valid Aadhaar Number");
			Log.info(withdrawalAadhaarNumError.getText());
		}

		withdrawalAmount.sendKeys(usrData.get("AMOUNT"));
		Log.info("Amount " + usrData.get("AMOUNT") + " entered");

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Wallet")) {
			withdrawalAadhaarNum.click();
			waitUntilElementIsVisible(withdrawalAmountError);
			Assert.assertEquals(withdrawalAmountError.getText(), "Insufficient wallet balance");
			Log.info(withdrawalAmountError.getText());
			dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Max")) {
			withdrawalAadhaarNum.click();
			waitUntilElementIsVisible(withdrawalAmountError);
			Assert.assertEquals(withdrawalAmountError.getText(),
					"Amount entered exceeds your transaction limit ₹10,000.00");
			Log.info(withdrawalAmountError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount < Min")) {
			withdrawalAadhaarNum.click();
			waitUntilElementIsVisible(withdrawalAmountError);
			Assert.assertEquals(withdrawalAmountError.getText(), "Minimum amount should be ₹10.00");
			Log.info(withdrawalAmountError.getText());
		}

		if (usrData.get("SCAN").equalsIgnoreCase("Yes")) {
			Assert.assertEquals("Click to scan fingerprint", withdrawalFingerprintUnscanned.getText());
			withdrawalScanFingerprint.click();
			Log.info("Scan fingerprint button clicked");
			waitUntilElementIsVisible(withdrawalScanSuccessScreen);
			Assert.assertEquals("Fingerprints scanned successfully", withdrawalFingerprintSuccess.getText());
			Log.info(withdrawalFingerprintSuccess.getText());
			withdrawalFingerprintScreenOkButton.click();
			Log.info("Ok button clicked");
			waitUntilElementIsVisible(withdrawalFingerprintGreen);
			Assert.assertEquals("Fingerprint scanned successfully!", withdrawalFingerprintGreen.getText());
		}

		if (usrData.get("SUBMIT").equalsIgnoreCase("Yes")) {
			Thread.sleep(1000);
			waitUntilElementIsClickableAndClickTheElement(withdrawalSubmit);
			Log.info("Submit button clicked");

			confirmScreen(usrData);

//			waitUntilElementIsVisible(processingScreen));
//			Log.info("Processing screen displayed");

			if (usrData.get("TXNSCREENBUTTON").equals("Process in Background")) {
				waitUntilElementIsClickableAndClickTheElement(processInBackgroundButton);
				Log.info("Process in Background button clicked");
			} else {
				waitUntilElementIsVisible(aepsTxnScreen);
				Log.info("Txn screen displayed");

				if (aepsTxnScreen.getText().equalsIgnoreCase("Success!")) {
					if (aepsTxnScreenType.getAttribute("class").contains("completed")) {
						assertionOnWithdrawalSuccessScreen(usrData);
						if (usrData.get("ASSERTION").contains("SMS")) {
							assertionOnWithdrawalSMS(usrData);
						}
						if (usrData.get("TXNSCREENBUTTON").equals("Save")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenSaveButton);
							Log.info("Save button clicked");
						} else if (usrData.get("TXNSCREENBUTTON").equals("Print")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenPrintButton);
							Log.info("Print button clicked");
						}
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
						Log.info("Done button clicked");
						commonUtils.refreshBalance();
						verifyUpdatedBalanceAfterWithdrawalSuccessTxn(usrData, walletBalance);
					}
				} else if (aepsTxnScreen.getText().equalsIgnoreCase("Failed!")) {
					assertionOnWithdrawalFailedScreen(usrData);
					if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenExitButton);
						Log.info("Exit button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Done")) {
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
						Log.info("Done button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenRetryButton);
						Log.info("Retry button clicked");
						waitUntilElementIsVisible(withdrawalScanSuccessScreen);
						Assert.assertEquals("Fingerprints scanned successfully",
								withdrawalFingerprintSuccess.getText());
						Log.info(withdrawalFingerprintSuccess.getText());
						withdrawalFingerprintScreenOkButton.click();
						Log.info("Ok button clicked");
						waitUntilElementIsInvisible(withdrawalFingerprintScreenOkButton);
						waitUntilElementIsVisible(withdrawalFingerprintGreen);
						Assert.assertEquals("Fingerprint scanned successfully!", withdrawalFingerprintGreen.getText());
						waitUntilElementIsClickableAndClickTheElement(withdrawalSubmit);
						Log.info("Submit button clicked");
						confirmScreen(usrData);
						waitUntilElementIsVisible(processingScreen);
						Log.info("Processing screen displayed");
						waitUntilElementIsVisible(aepsTxnScreen);
						Log.info("Txn screen displayed");
						assertionOnWithdrawalFailedScreen(usrData);
						if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
							Log.info("Done button clicked");
						} else {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenExitButton);
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
						commonUtils.refreshBalance();
//						verifyUpdatedBalanceAfterWithdrawalFailTxn(usrData, walletBalance);
					}
				} else {
					withdrawalClear.click();
					Log.info("Clear button clicked");
					waitUntilElementIsVisible(withdrawalDropdown);
					Log.info("Data cleared");
				}
			}
		}
	}

	// Balance Enquiry transaction
	public void balanceEnquiryTxns(Map<String, String> usrData, double walletBalance)
			throws ClassNotFoundException, InterruptedException, ParseException {
		waitUntilElementIsClickableAndClickTheElement(balanceEnquiryDropdown);
		Log.info("Dropdown clicked");
		waitUntilElementIsClickableAndClickTheElement(dropdownSearch);
		Log.info(usrData.get("BANKNAME") + " entered");
		dropdownSelect(usrData);
		Log.info(usrData.get("BANKNAME") + " selected");
		waitUntilElementIsClickableAndClickTheElement(balanceEnquiryMobNum);
		balanceEnquiryMobNum.sendKeys(usrData.get("MOBNUM"));
		Log.info("Mobile number " + usrData.get("MOBNUM") + " entered");
		balanceEnquiryAadhaarNum.click();

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank MN")) {
			waitUntilElementIsVisible(balanceEnquiryMobNumError);
			Assert.assertEquals(balanceEnquiryMobNumError.getText(), "Required Field");
			Log.info(balanceEnquiryMobNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("MN < 10 digits")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Invalid MN")) {
			waitUntilElementIsVisible(balanceEnquiryMobNumError);
			Assert.assertEquals(balanceEnquiryMobNumError.getText(), "Invalid mobile number");
			Log.info(balanceEnquiryMobNumError.getText());
		}

		balanceEnquiryAadhaarNum.sendKeys(usrData.get("AADHAAR"));
		Log.info("Aadhaar number " + usrData.get("AADHAAR") + " entered");
		balanceEnquiryMobNum.click();

		// Field level validation in Amount field
		if (usrData.get("ASSERTION").equalsIgnoreCase("Blank Aadhaar")) {
			waitUntilElementIsVisible(balanceEnquiryAadhaarNumError);
			Assert.assertEquals(balanceEnquiryAadhaarNumError.getText(), "Required Field");
			Log.info(balanceEnquiryAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Aadhaar < 12 digits")) {
			waitUntilElementIsVisible(balanceEnquiryAadhaarNumError);
			Assert.assertEquals(balanceEnquiryAadhaarNumError.getText(), "Length should be 12 digits");
			Log.info(balanceEnquiryAadhaarNumError.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid Aadhaar")) {
			waitUntilElementIsVisible(balanceEnquiryAadhaarNumError);
			Assert.assertEquals(balanceEnquiryAadhaarNumError.getText(), "Enter Valid Aadhaar Number");
			Log.info(balanceEnquiryAadhaarNumError.getText());
		}

		if (usrData.get("SCAN").equalsIgnoreCase("Yes")) {
			Assert.assertEquals("Click to scan fingerprint", balanceEnquiryFingerprintUnscanned.getText());
			balanceEnquiryScanFingerprint.click();
			Log.info("Scan fingerprint button clicked");
			waitUntilElementIsVisible(balanceEnquiryScanSuccessScreen);
			Assert.assertEquals("Fingerprints scanned successfully", balanceEnquiryFingerprintSuccess.getText());
			Log.info(balanceEnquiryFingerprintSuccess.getText());
			balanceEnquiryFingerprintScreenOkButton.click();
			Log.info("Ok button clicked");
			waitUntilElementIsVisible(balanceEnquiryFingerprintGreen);
			Assert.assertEquals("Fingerprint scanned successfully!", balanceEnquiryFingerprintGreen.getText());
		}

		if (usrData.get("SUBMIT").equalsIgnoreCase("Yes")) {
			Thread.sleep(1000);
			waitUntilElementIsVisible(balanceEnquirySubmit);
			balanceEnquirySubmit.click();
			Log.info("Submit button clicked");
//			waitUntilElementIsVisible(processingScreen));
//			Log.info("Processing screen displayed");

			if (usrData.get("TXNSCREENBUTTON").equals("Process in Background")) {
				waitUntilElementIsVisible(processInBackgroundButton);
				processInBackgroundButton.click();
				Log.info("Process in Background button clicked");
			} else {
				waitUntilElementIsVisible(aepsTxnScreen);
				Log.info("Txn screen displayed");

				if (aepsTxnScreen.getText().equalsIgnoreCase("Success!")) {
					if (aepsTxnScreenType.getAttribute("class").contains("completed")) {
						assertionOnBalanceEnquirySuccessScreen(usrData);
						if (usrData.get("ASSERTION").contains("SMS")) {
							assertionOnBalanceEnquirySMS(usrData);
						}
						if (usrData.get("TXNSCREENBUTTON").equals("Save")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenSaveButton);
							Log.info("Save button clicked");
						} else if (usrData.get("TXNSCREENBUTTON").equals("Print")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenPrintButton);
							Log.info("Print button clicked");
						}
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
						Log.info("Done button clicked");
						commonUtils.refreshBalance();
						verifyUpdatedBalanceAfterBalanceEnquirySuccessTxn(usrData, walletBalance);
					}
				} else if (aepsTxnScreen.getText().equalsIgnoreCase("Failed!")) {
					assertionOnBalanceEnquiryFailedScreen(usrData);
					if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenExitButton);
						Log.info("Exit button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Done")) {
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
						Log.info("Done button clicked");
					} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
						waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenRetryButton);
						Log.info("Retry button clicked");
						waitUntilElementIsVisible(balanceEnquiryScanSuccessScreen);
						Assert.assertEquals("Fingerprints scanned successfully",
								balanceEnquiryFingerprintSuccess.getText());
						Log.info(balanceEnquiryFingerprintSuccess.getText());
						balanceEnquiryFingerprintScreenOkButton.click();
						Log.info("Ok button clicked");
						waitUntilElementIsVisible(balanceEnquiryFingerprintGreen);
						Assert.assertEquals("Fingerprint scanned successfully!",
								balanceEnquiryFingerprintGreen.getText());
						Thread.sleep(1000);
						balanceEnquirySubmit.click();
						Log.info("Submit button clicked");
//						waitUntilElementIsVisible(processingScreen));
//						Log.info("Processing screen displayed");
						waitUntilElementIsVisible(aepsTxnScreen);
						Log.info("Txn screen displayed");
						assertionOnBalanceEnquiryFailedScreen(usrData);
						if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenDoneButton);
							Log.info("Done button clicked");
						} else {
							waitUntilElementIsClickableAndClickTheElement(aepsTxnScreenExitButton);
							Log.info("Exit button clicked");
						}
					}
					commonUtils.refreshBalance();
					verifyUpdatedBalanceAfterBalanceEnquiryFailTxn(usrData, walletBalance);
				} else {
					balanceEnquiryClear.click();
					Log.info("Clear button clicked");
					waitUntilElementIsVisible(balanceEnquiryDropdown);
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
		return "RBL";
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

		String balance = df.format(commonUtils.getInitialBalance("retailer"));
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

		String balance = df.format(commonUtils.getInitialBalance("cashout"));
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
				+ " IST is Led Bal: INR 1511.00, Ava Bal: INR 1511.00, Ref No: " + txnDetailsFromIni("GetTxnRefNo", "");

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
				+ " IST is Led Bal: 1511.00, Ava Bal: 1511.00 Response code: (00) SUCCESS Reference No: "
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

	// Confirm screen
	public void confirmScreen(Map<String, String> usrData) throws InterruptedException {
		waitUntilElementIsVisible(confirmScreen);
		Log.info("Confirm the details screen displayed");
		Assert.assertEquals(replaceSymbols(confirmScreenAmount.getText()), usrData.get("AMOUNT") + ".00");
		waitUntilElementIsVisible(confirmScreenSubmit);
		confirmScreenSubmit.click();
		Thread.sleep(2000);
		Log.info("Submit button clicked");
	}
}
