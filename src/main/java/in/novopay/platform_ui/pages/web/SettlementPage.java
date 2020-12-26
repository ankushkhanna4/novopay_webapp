package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
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

public class SettlementPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//button[contains(text(),'Manage Wallet')]")
	WebElement manageWalletButton;

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

	@FindBy(id = "money-transfer-mobile-number")
	WebElement cashoutBalanceField;

	@FindBy(xpath = "//select2[contains(@id,'wallet-transfer-amount-to-be-transferred-to-account')]/parent::div")
	WebElement toDropDown;

	@FindBy(xpath = "//*[contains(@id,'select2')]/li[contains(text(),'Bank account')]")
	WebElement bankAccountDropDownValue;

	@FindBy(xpath = "//*[contains(@id,'select2')]/li[contains(text(),'Wallet Balance')]")
	WebElement retailerCreditDropDownValue;

	@FindBy(id = "money-transfer-amount-to-be-transferred")
	WebElement amountField;

	@FindBy(xpath = "//*[@id='money-transfer-amount-to-be-transferred']/parent::div/following-sibling::div//li")
	WebElement amountErrorMsg;

	@FindBy(xpath = "//button[contains(@class,'input-group-addon btn-icon')]")
	WebElement applicableChargesButton;

	@FindBy(xpath = "//*[@id='cashout-balance-form']//button[contains(text(),'Submit')]")
	WebElement submitButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	WebElement clearButton;

	@FindBy(xpath = "//*[contains(@class,'retailer-account-status')]")
	WebElement accountStatus;

	@FindBy(xpath = "//p[contains(@class,'failure')]")
	WebElement blockedMessage;

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

	@FindBy(xpath = "//h4[contains(text(),'Applicable charges')]")
	WebElement applicableChargesScreen;

	@FindBy(xpath = "//h4[contains(text(),'Applicable charges')]/parent::div/following-sibling::div/div/div/span[contains(text(),'Transaction Amount')]/parent::div/following-sibling::div")
	WebElement applicableTxnAmount;

	@FindBy(xpath = "//h4[contains(text(),'Applicable charges')]/parent::div/following-sibling::div/div/div/span[contains(text(),'Charges')]/parent::div/following-sibling::div")
	WebElement applicableCharges;

	@FindBy(xpath = "//h4[contains(text(),'Applicable charges')]/parent::div/following-sibling::div/div/p[contains(text(),'Amount to be Transferred')]/following-sibling::p")
	WebElement applicableTotalAmount;

	@FindBy(xpath = "//h4[contains(text(),'Applicable charges')]/parent::div/following-sibling::div[2]/button")
	WebElement applicableChargesOkButton;

	@FindBy(xpath = "//h4[contains(text(),'Processing...')]")
	WebElement processingScreen;

	@FindBy(xpath = "//button[contains(text(),'Process in Background')]")
	WebElement processInBackgroundButton;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]/div/div/div/h4[contains(text(),'!')]")
	WebElement settlementTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]/div/div/div")
	WebElement settlementTxnScreenType;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]/div/div/div/following-sibling::div/div[1]")
	WebElement settlementTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]/div/div/div/following-sibling::div/div[2]/span")
	WebElement settlementTxnFailScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]//span[contains(text(),'Amount:')]/parent::div/following-sibling::div")
	WebElement settlementTxnScreenRequestedAmount;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]//span[contains(text(),'Charges:')]/parent::div/following-sibling::div")
	WebElement settlementTxnScreenCharges;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]//span[contains(text(),'Ref.ID:')]/parent::div/following-sibling::div")
	WebElement settlementTxnScreenRefId;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]//span[contains(text(),'Failed Amount:')]/parent::div/following-sibling::div")
	WebElement settlementTxnScreenFailedAmount;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]//p[contains(text(),'Amount Transferred')]/parent::div/p[2]")
	WebElement settlementTxnScreenTotalAmount;

	@FindBy(xpath = "//div[contains(@class,'cashout-balance-modal')]/div/div/div/following-sibling::div/div/span")
	WebElement settlementTxnScreenFailureReason;

	@FindBy(xpath = "//button[contains(text(),'Exit')]")
	WebElement exitButton;

	@FindBy(xpath = "//button[contains(text(),'Retry')]")
	WebElement retryButton;

	@FindBy(xpath = "//button[contains(text(),'Done')]")
	WebElement doneButton;

	@FindBy(xpath = "//div[contains(@class,'toast-message')]")
	WebElement toasterMsg;

	@FindBy(xpath = "//button[contains(text(),'Edit')]")
	WebElement editButton;

	@FindBy(xpath = "//button[contains(text(),'Ok')]")
	WebElement okButton;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent;

	@FindBy(xpath = "//li[2][contains(@class,'notifications')]//strong")
	WebElement fcmHeading2;

	@FindBy(xpath = "//li[2][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent2;

	@FindBy(xpath = "//a[contains(text(),'Cashout Settlement')]")
	WebElement cashoutTab;

	// Load all objects
	public SettlementPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void settlement(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {

			commonUtils.displayInitialBalance("retailer"); // display main wallet balance
			commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

			// Updating org_stlmnt_info table as per test case
			if (usrData.get("MODE").equalsIgnoreCase("Verified")) {
				dbUtils.deleteOrgSettlementInfo(mobileNumFromIni());
				dbUtils.insertOrgSettlementInfo("TO_BANK", "2", "1", mobileNumFromIni(), "1");
//				dbUtils.updateOrgSettlementInfo("TO_BANK", "2", "1", "(NULL)", mobileNumFromIni());
			} else if (usrData.get("MODE").equalsIgnoreCase("Pending")) {
				dbUtils.deleteOrgSettlementInfo(mobileNumFromIni());
				dbUtils.insertOrgSettlementInfo("TO_BANK", "1", "1", mobileNumFromIni(), "0");
//				dbUtils.updateOrgSettlementInfo("TO_BANK", "1", "1", "(NULL)", mobileNumFromIni());
			} else if (usrData.get("MODE").equalsIgnoreCase("Rejected")) {
				dbUtils.deleteOrgSettlementInfo(mobileNumFromIni());
				dbUtils.insertOrgSettlementInfo("TO_BANK", "3", "1", mobileNumFromIni(), "0");
//				dbUtils.updateOrgSettlementInfo("TO_BANK", "3", "1", "(NULL)", mobileNumFromIni());
			} else if (usrData.get("MODE").equalsIgnoreCase("Blocked")) {
				dbUtils.deleteOrgSettlementInfo(mobileNumFromIni());
				dbUtils.insertOrgSettlementInfo("TO_BANK", "4", "1", mobileNumFromIni(), "0");
//				dbUtils.updateOrgSettlementInfo("TO_BANK", "4", "0", "Incorrect bank details", mobileNumFromIni());
			} else if (usrData.get("MODE").equalsIgnoreCase("Deleted")) {
				dbUtils.deleteOrgSettlementInfo(mobileNumFromIni());
				dbUtils.insertOrgSettlementInfo("TO_BANK", "6", "1", mobileNumFromIni(), "0");
//				dbUtils.updateOrgSettlementInfo("TO_BANK", "6", "0", "(NULL)", mobileNumFromIni());
			}

			dbUtils.updateWalletManagedByBank(usrData.get("PARTNER").toUpperCase(),
					getLoginMobileFromIni("RetailerMobNum"));

			if (usrData.get("TYPE").equalsIgnoreCase("NEFT")) {
				dbUtils.updateSettlementModeInPlatformMasterData("Y", usrData.get("PARTNER"));
			} else if (usrData.get("TYPE").equalsIgnoreCase("IMPS")) {
				dbUtils.updateSettlementModeInPlatformMasterData("N", usrData.get("PARTNER"));
			}

			if (usrData.get("ASSERTION").equalsIgnoreCase("Public Holiday")) {
				dbUtils.updatePublicHoliday(usrData.get("PARTNER"), "CURDATE()");
			} else {
				dbUtils.updatePublicHoliday(usrData.get("PARTNER"), "CURDATE() - INTERVAL 1 DAY");
			}

			if (usrData.get("ASSERTION").equalsIgnoreCase("Non-Working Hours")) {
				dbUtils.updateSetllementStartAndEndTime(usrData.get("PARTNER"), "TIME_FORMAT(CURTIME()-1, '%H:%i:%s')",
						"CURTIME()");
			} else {
				dbUtils.updateSetllementStartAndEndTime(usrData.get("PARTNER"), "CURTIME()",
						"DATE_ADD(CURTIME(), INTERVAL 1 HOUR)");
			}

			commonUtils.selectFeatureFromMenu1(manageWalletButton, pageTitle);

			waitUntilElementIsClickableAndClickTheElement(cashoutTab);
			System.out.println("Cashout tab clicked");

			commonUtils.waitForSpinner();

			if (usrData.get("MODE").equalsIgnoreCase("Blocked")) {
				waitUntilElementIsVisible(blockedMessage);
				Assert.assertEquals(blockedMessage.getText(),
						"Not allowed as settlement is blocked. Please contact customer support.");
				System.out.println(blockedMessage.getText());
			} else if (usrData.get("MODE").equalsIgnoreCase("Pending")
					|| usrData.get("MODE").equalsIgnoreCase("Rejected")) {
				waitUntilElementIsVisible(blockedMessage);
				Assert.assertEquals(blockedMessage.getText(),
						"Settlement not allowed as your settlement details have either been rejected or "
								+ "pending for verification. Please contact customer support for further assistance.");
				System.out.println(blockedMessage.getText());
			} else if (usrData.get("MODE").equalsIgnoreCase("Verified")) {
				waitUntilElementIsVisible(cashoutBalanceField);
				waitUntilElementIsClickableAndClickTheElement(toDropDown);
				System.out.println("Drop down clicked");

				bankAccountDropDownValue.click();
				System.out.println(usrData.get("TODROPDOWN") + " selected");

				waitUntilElementIsClickableAndClickTheElement(amountField);
				amountField.sendKeys(usrData.get("AMOUNT"));
				System.out.println("Amount entered");

				// Field level validation in Amount field
				if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Wallet")) {
					waitUntilElementIsVisible(amountErrorMsg);
					Assert.assertEquals(amountErrorMsg.getText().substring(0, 49),
							"Amount entered exceeds your cashout balance limit");
					System.out.println(amountErrorMsg.getText());
					dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "1000000");
				} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount < Min")) {
					waitUntilElementIsVisible(amountErrorMsg);
					Assert.assertEquals(amountErrorMsg.getText(), "Minimum amount should be ₹10.00");
					System.out.println(amountErrorMsg.getText());
				}

				if (!(usrData.get("SETTLEMENTBUTTON").equalsIgnoreCase("SKIP")
						|| (usrData.get("SETTLEMENTBUTTON").equalsIgnoreCase("Charges")))) {
					String buttonName = usrData.get("SETTLEMENTBUTTON");
					String buttonXpath = "//*[@id='cashout-balance-form']//button[contains(text(),'" + buttonName
							+ "')]";
					WebElement button = wdriver.findElement(By.xpath(buttonXpath));
					Thread.sleep(1000);
					waitUntilElementIsClickableAndClickTheElement(button);
					if (buttonName.equalsIgnoreCase("Clear")) {
						Thread.sleep(2000);
						System.out.println("Clear button clicked");
					} else if (buttonName.equalsIgnoreCase("Submit")) {
						System.out.println("Submit button clicked");
					}
				}

				if (usrData.get("SETTLEMENTBUTTON").equalsIgnoreCase("Submit")) {
					waitUntilElementIsVisible(MPINScreen);
					System.out.println("MPIN screen displayed");
					waitUntilElementIsClickableAndClickTheElement(enterMPIN);
					if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
						enterMPIN.sendKeys(getAuthfromIni("MPIN"));
					} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
						enterMPIN.sendKeys("9999");
					}
					System.out.println("MPIN entered");

					if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
						dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "0");
					}

					String mpinButtonName = usrData.get("MPINSCREENBUTTON");
					String mpinScreenButtonXpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/"
							+ "following-sibling::div/following-sibling::div/button[contains(text(),'" + mpinButtonName
							+ "')]";
					WebElement mpinScreenButton = wdriver.findElement(By.xpath(mpinScreenButtonXpath));
					waitUntilElementIsClickableAndClickTheElement(mpinScreenButton);
					System.out.println(mpinButtonName + " button clicked");
					if (mpinButtonName.equalsIgnoreCase("Cancel")) {
						commonUtils.waitForSpinner();
					} else if (mpinButtonName.equalsIgnoreCase("Submit")) {
						if (usrData.get("TXNSCREENBUTTON").equals("Process in Background")) {
							waitUntilElementIsVisible(processingScreen);
							System.out.println("Processing screen displayed");
							waitUntilElementIsVisible(processInBackgroundButton);
							processInBackgroundButton.click();
							System.out.println("Process in Background button clicked");
						} else {
							try {
								waitUntilElementIsVisible(settlementTxnScreen);
								System.out.println("Txn screen displayed");
							} catch (Exception e) {
								System.out.println("30 sec wait time elapsed");
								try {
									waitUntilElementIsVisible(settlementTxnScreen);
									System.out.println("Txn screen displayed");
								} catch (Exception f) {
									System.out.println("60 sec wait time elapsed");
									waitUntilElementIsVisible(settlementTxnScreen);
									System.out.println("Txn screen displayed");
								}
							}

							// Verify the details on transaction screen
							if (settlementTxnScreen.getText().equalsIgnoreCase("Success!")
									|| settlementTxnScreen.getText().equalsIgnoreCase("Pending!")) {
								if (settlementTxnScreen.getText().equalsIgnoreCase("Success!")) {
									assertionOnSuccessScreen(usrData);
								} else if (settlementTxnScreen.getText().equalsIgnoreCase("Pending!")) {
									assertionOnWarnScreen(usrData);
								}
								waitUntilElementIsClickableAndClickTheElement(doneButton);
								System.out.println("Done button clicked");
								commonUtils.waitForSpinner();
								verifyUpdatedBalanceAfterSuccessTxn(usrData);
								assertionOnSMS(usrData);
								if (usrData.get("ASSERTION").contains("FCM")) {
									assertionOnFCM(usrData);
								}
							} else if (settlementTxnScreen.getText().equalsIgnoreCase("Failed!")) {
								if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
									assertionOnFailedScreen(usrData);
									if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
										System.out.println("Clicking exit button");
									} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
										retryButton.click();
										Thread.sleep(1000);
										waitUntilElementIsVisible(MPINScreen);
										System.out.println("MPIN screen displayed");
										waitUntilElementIsClickableAndClickTheElement(enterMPIN);
										enterMPIN.sendKeys(getAuthfromIni("MPIN"));
										System.out.println("MPIN entered");
										waitUntilElementIsClickableAndClickTheElement(submitMPIN);
										System.out.println("Submit button clicked");
										commonUtils.waitForSpinner();
										waitUntilElementIsVisible(settlementTxnScreen);
										System.out.println("Txn screen displayed");
										assertionOnFailedScreen(usrData);
									}
									waitUntilElementIsClickableAndClickTheElement(exitButton);
									System.out.println("Exit button clicked");
								} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
									waitUntilElementIsVisible(settlementTxnFailScreenMessage);
									System.out.println(settlementTxnFailScreenMessage.getText());
									if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
										exitButton.click();
										System.out.println("Exit button clicked");
									} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
										retryButton.click();
										Thread.sleep(1000);
										waitUntilElementIsVisible(MPINScreen);
										System.out.println("MPIN screen displayed");
										waitUntilElementIsClickableAndClickTheElement(enterMPIN);
										enterMPIN.click();
										enterMPIN.sendKeys(getAuthfromIni("MPIN"));
										System.out.println("MPIN entered");
										waitUntilElementIsClickableAndClickTheElement(submitMPIN);
										System.out.println("Submit button clicked");
										commonUtils.waitForSpinner();
										waitUntilElementIsVisible(settlementTxnScreen);
										System.out.println("Txn screen displayed");
										assertionOnSuccessScreen(usrData);
										waitUntilElementIsClickableAndClickTheElement(doneButton);
										System.out.println("Done button clicked");
										commonUtils.waitForSpinner();
										verifyUpdatedBalanceAfterSuccessTxn(usrData);
									}
								}
							}
						}
					}

					dbUtils.updateWalletManagedByBank("RBL", getLoginMobileFromIni("RetailerMobNum"));
					dbUtils.updateSetllementStartAndEndTime(usrData.get("PARTNER"), "'00:00:00'", "'23:59:59'");

				} else if (usrData.get("SETTLEMENTBUTTON").equalsIgnoreCase("Charges")) {
					waitUntilElementIsClickableAndClickTheElement(applicableChargesButton);
					waitUntilElementIsVisible(applicableChargesScreen);
					assertionOnApplicableCharges(usrData);
					applicableChargesOkButton.click();
				}
			}
		} catch (

		Exception e) {
			wdriver.navigate().refresh();
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}

	// Verify details on success screen
	public void assertionOnSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("TYPE").equalsIgnoreCase("NEFT")) {
			Assert.assertEquals(settlementTxnScreenMessage.getText().substring(29),
					"Please check your bank account after 4 hours for updated balance.");
		} else if (usrData.get("TYPE").equalsIgnoreCase("IMPS")) {
			Assert.assertEquals(settlementTxnScreenMessage.getText(), "Transfer request successful.");
		}
		System.out.println(settlementTxnScreenMessage.getText());
		Assert.assertEquals(replaceSymbols(settlementTxnScreenRequestedAmount.getText()),
				usrData.get("AMOUNT") + ".00");
		System.out.println("Transferred Amount: " + replaceSymbols(settlementTxnScreenRequestedAmount.getText()));
		txnDetailsFromIni("StoreTxfAmount", usrData.get("AMOUNT"));
		Assert.assertEquals(replaceSymbols(settlementTxnScreenCharges.getText()),
				dbUtils.getOnDemandSettlementCharges(usrData.get("TYPE"), usrData.get("PARTNER")));
		System.out.println("Charges: " + replaceSymbols(settlementTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreCharges", replaceSymbols(settlementTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreTxnRefNo", settlementTxnScreenRefId.getText());
		double amount = Double.parseDouble(usrData.get("AMOUNT"));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double totalAmount = amount - charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(settlementTxnScreenTotalAmount.getText()), cashToBeCollected);
		System.out.println("Amount Transferred: " + replaceSymbols(settlementTxnScreenTotalAmount.getText()));
	}

	// Assertion after success screen is displayed
	public void verifyUpdatedBalanceAfterSuccessTxn(Map<String, String> usrData) throws ClassNotFoundException {
		double initialCashoutWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetCashout", ""));
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double newCashoutWalletBal = initialCashoutWalletBalance - amount;
		String newCashoutWalletBalance = df.format(newCashoutWalletBal);
		waitUntilElementIsVisible(toDropDown);
		Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newCashoutWalletBalance);
		System.out.println("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
		getWalletBalanceFromIni("cashout", newCashoutWalletBalance);
	}

	// Verify details on success screen
	public void assertionOnWarnScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		try {
			Assert.assertEquals(settlementTxnScreenMessage.getText(), "Transfer initiated successfully.");
		} catch (AssertionError e) {
			Assert.assertEquals(
					settlementTxnScreenMessage.getText().substring(0, 32)
							+ settlementTxnScreenMessage.getText().substring(33),
					"Your Bank does not support IMPS." + "Transaction will be settled through NEFT in 2-3 hours.");
		}
		System.out.println(settlementTxnScreenMessage.getText());
		Assert.assertEquals(replaceSymbols(settlementTxnScreenRequestedAmount.getText()),
				usrData.get("AMOUNT") + ".00");
		System.out.println("Transferred Amount: " + replaceSymbols(settlementTxnScreenRequestedAmount.getText()));
		txnDetailsFromIni("StoreTxfAmount", usrData.get("AMOUNT"));
		Assert.assertEquals(replaceSymbols(settlementTxnScreenCharges.getText()),
				dbUtils.getOnDemandSettlementCharges(usrData.get("TYPE"), usrData.get("PARTNER")));
		System.out.println("Charges: " + replaceSymbols(settlementTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreCharges", replaceSymbols(settlementTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreTxnRefNo", settlementTxnScreenRefId.getText());
		double amount = Double.parseDouble(usrData.get("AMOUNT"));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double totalAmount = amount - charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(settlementTxnScreenTotalAmount.getText()), cashToBeCollected);
		System.out.println("Amount Transferred: " + replaceSymbols(settlementTxnScreenTotalAmount.getText()));
	}

	// Verify details on failed screen
	public void assertionOnFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid MPIN")) {
			Assert.assertEquals(settlementTxnFailScreenMessage.getText(), "Authentication Failed Invalid MPIN");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient balance")) {
			Assert.assertEquals(settlementTxnFailScreenMessage.getText(), "Insufficient balance");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Public Holiday")
				|| usrData.get("ASSERTION").equalsIgnoreCase("Non-Working Hours")) {
			Assert.assertEquals(settlementTxnFailScreenMessage.getText(),
					dbUtils.settlementServiceUnavailableMessage());
		} else {
			Assert.assertEquals(settlementTxnFailScreenMessage.getText(), "Transaction has failed. Try again later!");
		}
		System.out.println(settlementTxnFailScreenMessage.getText());
	}

	// Verify details on applicable charges screen
	public void assertionOnApplicableCharges(Map<String, String> usrData) throws ClassNotFoundException {
		System.out.println("Verifying charges");
		Assert.assertEquals(replaceSymbols(applicableTxnAmount.getText()), usrData.get("AMOUNT") + ".00");
		System.out.println("Transaction Amount: " + replaceSymbols(applicableTxnAmount.getText()));
		String chrges = dbUtils.getOnDemandSettlementCharges(usrData.get("TYPE"), usrData.get("PARTNER"));
		Assert.assertEquals(replaceSymbols(applicableCharges.getText()), chrges);
		System.out.println("Charges: " + replaceSymbols(applicableCharges.getText()));

		double amount = Double.parseDouble(usrData.get("AMOUNT"));
		double charges = Double.parseDouble(chrges);
		double totalAmount = amount - charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(applicableTotalAmount.getText()), cashToBeCollected);
		System.out.println("Cash to be Collected: " + replaceSymbols(applicableTotalAmount.getText()));
	}

	// SMS assertion
	public void assertionOnSMS(Map<String, String> usrData) throws ClassNotFoundException {
		String SMSsuccessIMPS = "Balance transfer: INR " + usrData.get("AMOUNT")
				+ " (Withdrawable balance->Bank account). Transfer request successful. Ref#"
				+ txnDetailsFromIni("GetTxnRefNo", "") + ", charges: INR " + txnDetailsFromIni("GetCharges", "")
				+ ", available Withdrawable balance: INR " + getWalletBalanceFromIni("GetCashout", "");
		String SMSpendingIMPS = "Balance transfer: INR " + usrData.get("AMOUNT")
				+ " (Withdrawable balance->Bank account). Transfer request deemed successful. Ref#"
				+ txnDetailsFromIni("GetTxnRefNo", "") + ", charges: INR " + txnDetailsFromIni("GetCharges", "")
				+ ", available Withdrawable balance: INR " + getWalletBalanceFromIni("GetCashout", "");
		String SMSsuccessNEFT = "Balance transfer: INR " + usrData.get("AMOUNT")
				+ " (Withdrawable balance->Bank account). Transfer request successful. "
				+ "Please check your bank account after 4 hours for updated balance. Ref#"
				+ txnDetailsFromIni("GetTxnRefNo", "") + ", charges: INR " + txnDetailsFromIni("GetCharges", "")
				+ ", available Withdrawable balance: INR " + getWalletBalanceFromIni("GetCashout", "");

		if (usrData.get("ASSERTION").equalsIgnoreCase("SMS IMPS Success")) {
			Assert.assertEquals(dbUtils.sms(), SMSsuccessIMPS);
			System.out.println(SMSsuccessIMPS);
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("SMS IMPS Pending")) {
			Assert.assertEquals(dbUtils.sms(), SMSpendingIMPS);
			System.out.println(SMSpendingIMPS);
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("SMS NEFT Success")) {
			Assert.assertEquals(dbUtils.sms(), SMSsuccessNEFT);
			System.out.println(SMSsuccessNEFT);
		}
	}

	// FCM assertion
	public void assertionOnFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String bankFCMHeading = "Balance transfer: INR " + txnDetailsFromIni("GetTxfAmount", "")
				+ " (Withdrawable balance->Bank account)";
		String bankFCMContent = "", bankFCMContentPending = "";

		if (usrData.get("ASSERTION").equalsIgnoreCase("FCM IMPS Success")) {
			bankFCMContent = "Transfer request successful. Ref#" + txnDetailsFromIni("GetTxnRefNo", "")
					+ ", charges: INR " + txnDetailsFromIni("GetCharges", "") + ", available Withdrawable balance: INR "
					+ getWalletBalanceFromIni("GetCashout", "");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("FCM IMPS Pending")) {
			bankFCMContent = "Transfer request deemed successful. Ref#" + txnDetailsFromIni("GetTxnRefNo", "")
					+ ", charges: INR " + txnDetailsFromIni("GetCharges", "") + ", available Withdrawable balance: INR "
					+ getWalletBalanceFromIni("GetCashout", "");
			bankFCMContentPending = "Transfer request deemed successful.";
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("FCM NEFT Success")) {
			bankFCMContent = "Please check your bank account after 4 hours for updated balance. Ref#"
					+ txnDetailsFromIni("GetTxnRefNo", "") + ", charges: INR " + txnDetailsFromIni("GetCharges", "")
					+ ", available Withdrawable balance: INR " + getWalletBalanceFromIni("GetCashout", "");
		}

		if (usrData.get("ASSERTION").equalsIgnoreCase("FCM IMPS Pending")) {
			try {
				fcmMethod1(usrData, bankFCMHeading, bankFCMContent);
				fcmMethod2(bankFCMHeading, bankFCMContentPending);
			} catch (AssertionError e) {
				fcmMethod1(usrData, bankFCMHeading, bankFCMContentPending);
				fcmMethod2(bankFCMHeading, bankFCMContent);
			}
		} else {
			fcmMethod1(usrData, bankFCMHeading, bankFCMContent);
		}

	}

	public void fcmMethod1(Map<String, String> usrData, String heading, String content) {
		Assert.assertEquals(fcmHeading.getText(), heading);
		if (usrData.get("ASSERTION").equalsIgnoreCase("FCM NEFT Success")) {
			Assert.assertEquals(fcmContent.getText().substring(29), content);
		} else {
			Assert.assertEquals(fcmContent.getText(), content);
		}
		System.out.println(fcmHeading.getText());
		System.out.println(fcmContent.getText());
	}

	public void fcmMethod2(String heading, String content) {
		Assert.assertEquals(fcmHeading2.getText(), heading);
		Assert.assertEquals(fcmContent2.getText(), content);
		System.out.println(fcmHeading2.getText());
		System.out.println(fcmContent2.getText());
	}

	// Get Partner name
	public String partner() {
		return "RBL";
	}
}
