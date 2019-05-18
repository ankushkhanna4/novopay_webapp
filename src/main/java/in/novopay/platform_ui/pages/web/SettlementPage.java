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

public class SettlementPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	DecimalFormat df = new DecimalFormat("#.00");

	WebDriverWait wait = new WebDriverWait(wdriver, 30);
	WebDriverWait waitSave = new WebDriverWait(wdriver, 3);

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//button[contains(text(),'Manage Wallet')]")
	WebElement manageWalletButton;

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

	@FindBy(id = "money-transfer-mobile-number")
	WebElement cashoutBalanceField;

	@FindBy(xpath = "//select2[contains(@id,'wallet-transfer-amount-to-be-transferred-to-account')]/parent::div")
	WebElement toDropDown;

	@FindBy(xpath = "//*[contains(@id,'select2')]/li[contains(text(),'Bank account')]")
	WebElement bankAccountDropDownValue;
//Bank account (12346565484, HDFC Bank )
	@FindBy(xpath = "//*[contains(@id,'select2')]/li[contains(text(),'Wallet Balance')]")
	WebElement retailerCreditDropDownValue;
//Wallet Balance (₹ 9,94,910)
	@FindBy(id = "money-transfer-amount-to-be-transferred")
	WebElement amountField;

	@FindBy(xpath = "//*[@id='money-transfer-amount-to-be-transferred']/following-sibling::ul/li")
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

			// display wallet balances in console
			displayInitialBalance(usrData, "cashout");

			// store wallet balance as double
			double initialRetailerWalletBalance = getInitialBalance("retailer");
			double initialCashoutWalletBalance = getInitialBalance("cashout");

			if (usrData.get("ASSERTION").contains("FCM")) {
				assertionOnFCM(usrData);
			} else {
				// Updating org_stlmnt_info table as per test case
				if (usrData.get("MODE").equalsIgnoreCase("Verified")) {
					dbUtils.updateOrgSettlementInfo("TO_BANK", "2", "1", "(NULL)", mobileNumFromIni());
				} else if (usrData.get("MODE").equalsIgnoreCase("Pending")) {
					dbUtils.updateOrgSettlementInfo("TO_BANK", "1", "1", "(NULL)", mobileNumFromIni());
				} else if (usrData.get("MODE").equalsIgnoreCase("Rejected")) {
					dbUtils.updateOrgSettlementInfo("TO_BANK", "3", "1", "(NULL)", mobileNumFromIni());
				} else if (usrData.get("MODE").equalsIgnoreCase("Blocked")) {
					dbUtils.updateOrgSettlementInfo("TO_BANK", "4", "0", "Incorrect bank details", mobileNumFromIni());
				}

				menu.click();
				wait.until(ExpectedConditions.elementToBeClickable(manageWalletButton));
				clickInvisibleElement(manageWalletButton);
				Log.info("Manage Wallet button clicked");
				wait.until(ExpectedConditions.elementToBeClickable(pageTitle));
				menu.click();

				wait.until(ExpectedConditions.elementToBeClickable(cashoutTab));
				cashoutTab.click();
				Log.info("Cashout tab clicked");

				Thread.sleep(2000);
				waitForSpinner();

				if (usrData.get("MODE").equalsIgnoreCase("Blocked")) {
					wait.until(ExpectedConditions.elementToBeClickable(blockedMessage));
					Assert.assertEquals(blockedMessage.getText(),
							"Not allowed as settlement is blocked. Please contact customer support.");
					Log.info(blockedMessage.getText());
				} else {
					wait.until(ExpectedConditions.visibilityOf(cashoutBalanceField));
					wait.until(ExpectedConditions.elementToBeClickable(toDropDown));
					toDropDown.click();
					Log.info("Drop down clicked");

					int valueCount = wdriver.findElements(By.xpath("//ul[contains(@class,'select2')]/li")).size();
					if (usrData.get("MODE").equalsIgnoreCase("Verified")) {
						Assert.assertEquals(valueCount, 2);
						Log.info("Both dropdown values displayed");
					} else {
						Assert.assertEquals(valueCount, 1);
						Log.info("Only Retailer Credit dropdown value displayed");
					}
					if (usrData.get("TODROPDOWN").equalsIgnoreCase("Bank Account")) {
						bankAccountDropDownValue.click();
					} else if (usrData.get("TODROPDOWN").equalsIgnoreCase("Retailer Credit")) {
						retailerCreditDropDownValue.click();
					}
					Log.info(usrData.get("TODROPDOWN") + "selected");

					wait.until(ExpectedConditions.elementToBeClickable(amountField));
					amountField.sendKeys(usrData.get("AMOUNT"));
					Log.info("Amount entered");

					// Field level validation in Amount field
					if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Wallet")) {
						wait.until(ExpectedConditions.visibilityOf(amountErrorMsg));
						Assert.assertEquals(amountErrorMsg.getText().substring(0, 49),
								"Amount entered exceeds your cashout balance limit");
						Log.info(amountErrorMsg.getText());
						dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "1000000");
					} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount < Min")) {
						wait.until(ExpectedConditions.visibilityOf(amountErrorMsg));
						Assert.assertEquals(amountErrorMsg.getText(), "Minimum amount should be ₹10.00");
						Log.info(amountErrorMsg.getText());
					}

					if (!(usrData.get("SETTLEMENTBUTTON").equalsIgnoreCase("SKIP")
							|| (usrData.get("SETTLEMENTBUTTON").equalsIgnoreCase("Charges")))) {
						String buttonName = usrData.get("SETTLEMENTBUTTON");
						String buttonXpath = "//*[@id='cashout-balance-form']//button[contains(text(),'" + buttonName
								+ "')]";
						WebElement button = wdriver.findElement(By.xpath(buttonXpath));
						wait.until(ExpectedConditions.elementToBeClickable(button));
						Thread.sleep(1000);
						clickElement(button);
						if (buttonName.equalsIgnoreCase("Clear")) {
							Thread.sleep(2000);
							Log.info("Clear button clicked");
						} else if (buttonName.equalsIgnoreCase("Submit")) {
							Log.info("Submit button clicked");
						}
					}

					if (usrData.get("SETTLEMENTBUTTON").equalsIgnoreCase("Submit")) {
						wait.until(ExpectedConditions.visibilityOf(MPINScreen));
						Log.info("MPIN screen displayed");
						wait.until(ExpectedConditions.elementToBeClickable(enterMPIN));
						if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
							enterMPIN.sendKeys(getAuthfromIni("MPIN"));
						} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
							enterMPIN.sendKeys("9999");
						}
						Log.info("MPIN entered");

						if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
							dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "0");
						}

						String mpinButtonName = usrData.get("MPINSCREENBUTTON");
						String mpinScreenButtonXpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/"
								+ "following-sibling::div/following-sibling::div/button[contains(text(),'"
								+ mpinButtonName + "')]";
						WebElement mpinScreenButton = wdriver.findElement(By.xpath(mpinScreenButtonXpath));
						wait.until(ExpectedConditions.elementToBeClickable(mpinScreenButton));
						mpinScreenButton.click();
						Log.info(mpinButtonName + " button clicked");
						if (mpinButtonName.equalsIgnoreCase("Cancel")) {
							Log.info("Cancel button clicked");
							waitForSpinner();
						} else if (mpinButtonName.equalsIgnoreCase("Submit")) {
							if (usrData.get("TXNSCREENBUTTON").equals("Process in Background")) {
								wait.until(ExpectedConditions.visibilityOf(processingScreen));
								Log.info("Processing screen displayed");
								wait.until(ExpectedConditions.visibilityOf(processInBackgroundButton));
								processInBackgroundButton.click();
								Log.info("Process in Background button clicked");
							} else {
								wait.until(ExpectedConditions.visibilityOf(settlementTxnScreen));
								Log.info("Txn screen displayed");

								// Verify the details on transaction screen
								if (settlementTxnScreen.getText().equalsIgnoreCase("Success!")) {
									assertionOnSuccessScreen(usrData);
									wait.until(ExpectedConditions.elementToBeClickable(doneButton));
									doneButton.click();
									Log.info("Done button clicked");
									waitForSpinner();
									verifyUpdatedBalanceAfterSuccessTxn(usrData, initialCashoutWalletBalance,
											initialRetailerWalletBalance);
									assertionOnSMS(usrData);
								} else if (settlementTxnScreen.getText().equalsIgnoreCase("Failed!")) {
									if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
										assertionOnFailedScreen(usrData);
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
											waitForSpinner();
											wait.until(ExpectedConditions.visibilityOf(settlementTxnScreen));
											Log.info("Txn screen displayed");
											assertionOnFailedScreen(usrData);
										}
										wait.until(ExpectedConditions.elementToBeClickable(exitButton));
										exitButton.click();
										Log.info("Exit button clicked");
									} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
										wait.until(ExpectedConditions
												.elementToBeClickable(settlementTxnFailScreenMessage));
										Log.info(settlementTxnFailScreenMessage.getText());
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
											waitForSpinner();
											wait.until(ExpectedConditions.visibilityOf(settlementTxnScreen));
											Log.info("Txn screen displayed");
											assertionOnSuccessScreen(usrData);
											wait.until(ExpectedConditions.elementToBeClickable(doneButton));
											doneButton.click();
											Log.info("Done button clicked");
											waitForSpinner();
											verifyUpdatedBalanceAfterSuccessTxn(usrData, initialCashoutWalletBalance,
													initialRetailerWalletBalance);
										}
									}
								}
							}
						} else if (usrData.get("SETTLEMENTBUTTON").equalsIgnoreCase("Charges")) {
							wait.until(ExpectedConditions.elementToBeClickable(applicableChargesButton));
							applicableChargesButton.click();
							wait.until(ExpectedConditions.visibilityOf(applicableChargesScreen));
							assertionOnApplicableCharges(usrData);
							applicableChargesOkButton.click();
						}

					}
				}
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
			getWalletBalanceFromIni(wallet.toLowerCase(), replaceSymbols(retailerWalletBalance.getText()));
		} else if (wallet.equalsIgnoreCase("cashout")) {
			Log.info("Cashout Balance: " + initialCashoutBal);
			getWalletBalanceFromIni(wallet.toLowerCase(), replaceSymbols(cashoutWalletBalance.getText()));
		} else if (wallet.equalsIgnoreCase("merchant")) {
			Log.info("Merchant Balance: " + initialMerchantBal);
			getWalletBalanceFromIni(wallet.toLowerCase(), replaceSymbols(merchantWalletBalance.getText()));
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
				.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'spinner')]/parent::div']")));
		Log.info("Please wait...");
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
		if (usrData.get("TODROPDOWN").equalsIgnoreCase("bank account")) {
//			Assert.assertEquals(settlementTxnScreenMessage.getText(), "Transfer request successful. " + 
//					"Please check your bank account after 4 hours for updated balance.");
		} else {
			Assert.assertEquals(settlementTxnScreenMessage.getText(), "Transfer request successful.");
		}
		Log.info(settlementTxnScreenMessage.getText());
		Assert.assertEquals(replaceSymbols(settlementTxnScreenRequestedAmount.getText()),
				usrData.get("AMOUNT") + ".00");
		Log.info("Transferred Amount: " + replaceSymbols(settlementTxnScreenRequestedAmount.getText()));
		txnDetailsFromIni("StoreTxfAmount", usrData.get("AMOUNT"));
		Assert.assertEquals(replaceSymbols(settlementTxnScreenCharges.getText()),
				dbUtils.getOnDemandSettlementCharges(usrData.get("TODROPDOWN")));
		Log.info("Charges: " + replaceSymbols(settlementTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreCharges", replaceSymbols(settlementTxnScreenCharges.getText()));
		txnDetailsFromIni("StoreTxnRefNo", settlementTxnScreenRefId.getText());
		double amount = Double.parseDouble(usrData.get("AMOUNT"));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double totalAmount = amount - charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(settlementTxnScreenTotalAmount.getText()), cashToBeCollected);
		Log.info("Amount Transferred: " + replaceSymbols(settlementTxnScreenTotalAmount.getText()));
	}

	// Assertion after success screen is displayed
	public void verifyUpdatedBalanceAfterSuccessTxn(Map<String, String> usrData, double initialCashoutWalletBalance,
			double initialRetailerWalletBalance) throws ClassNotFoundException {
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double newRetailerWalletBal = 0.00;
		double newCashoutWalletBal = initialCashoutWalletBalance - amount;
		if (usrData.get("TODROPDOWN").equalsIgnoreCase("bank account")) {
			newRetailerWalletBal = initialRetailerWalletBalance;
		} else {
			newRetailerWalletBal = initialRetailerWalletBalance + amount;
		}
		String newRetailerWalletBalance = df.format(newRetailerWalletBal);
		String newCashoutWalletBalance = df.format(newCashoutWalletBal);
		wait.until(ExpectedConditions.elementToBeClickable(toDropDown));
		Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newCashoutWalletBalance);
		Log.info("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
		getWalletBalanceFromIni("cashout", newCashoutWalletBalance);
		Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newRetailerWalletBalance);
		Log.info("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
		getWalletBalanceFromIni("retailer", newRetailerWalletBalance);
	}

	// Verify details on failed screen
	public void assertionOnFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid MPIN")) {
			Assert.assertEquals(settlementTxnFailScreenMessage.getText(), "Authentication Failed Invalid MPIN");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient balance")) {
			Assert.assertEquals(settlementTxnFailScreenMessage.getText(), "Insufficient balance");
		}
		Log.info(settlementTxnFailScreenMessage.getText());
	}

	// Verify details on applicable charges screen
	public void assertionOnApplicableCharges(Map<String, String> usrData) throws ClassNotFoundException {
		Log.info("Verifying charges");
		Assert.assertEquals(replaceSymbols(applicableTxnAmount.getText()), usrData.get("AMOUNT") + ".00");
		Log.info("Transaction Amount: " + replaceSymbols(applicableTxnAmount.getText()));
		String chrges = dbUtils.getOnDemandSettlementCharges(usrData.get("TODROPDOWN"));
		Assert.assertEquals(replaceSymbols(applicableCharges.getText()), chrges);
		Log.info("Charges: " + replaceSymbols(applicableCharges.getText()));

		double amount = Double.parseDouble(usrData.get("AMOUNT"));
		double charges = Double.parseDouble(chrges);
		double totalAmount = amount - charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(applicableTotalAmount.getText()), cashToBeCollected);
		Log.info("Cash to be Collected: " + replaceSymbols(applicableTotalAmount.getText()));
	}

	// SMS assertion
	public void assertionOnSMS(Map<String, String> usrData) throws ClassNotFoundException {
		String bankSMS = "Balance transfer: INR " + usrData.get("AMOUNT")
				+ " (Withdrawable balance->Bank account). Transfer request successful. Ref#"
				+ txnDetailsFromIni("GetTxnRefNo", "") + ", charges: INR " + txnDetailsFromIni("GetCharges", "")
				+ ", available Withdrawable balance: INR " + getWalletBalanceFromIni("GetCashout", "");

		String walletSMS = "Balance transfer: INR " + usrData.get("AMOUNT")
				+ " (Withdrawable balance->Retailer credit). Transfer request successful. Ref#"
				+ txnDetailsFromIni("GetTxnRefNo", "") + ", charges: INR " + txnDetailsFromIni("GetCharges", "")
				+ ", available Withdrawable balance: INR " + getWalletBalanceFromIni("GetCashout", "")
				+ ", available Retailer credit: INR " + getWalletBalanceFromIni("GetRetailer", "");

		if (usrData.get("ASSERTION").equalsIgnoreCase("SMS Bank")) {
			Assert.assertEquals(bankSMS, dbUtils.sms());
			Log.info(bankSMS);
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("SMS Wallet")) {
			Assert.assertEquals(walletSMS, dbUtils.sms());
			Log.info(walletSMS);
		}
	}

	// FCM assertion
	public void assertionOnFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String bankFCMHeading = "Balance transfer: INR " + txnDetailsFromIni("GetTxfAmount", "")
				+ " (Withdrawable balance->Bank account)";
		String walletFCMHeading = "Balance transfer: INR " + txnDetailsFromIni("GetTxfAmount", "")
				+ " (Withdrawable balance->Retailer credit)";
		String bankFCMContent = "Ref#" + txnDetailsFromIni("GetTxnRefNo", "") + ", charges: INR "
				+ txnDetailsFromIni("GetCharges", "") + ", available Withdrawable balance: INR "
				+ getWalletBalanceFromIni("GetCashout", "");
		String walletFCMContent = "Ref#" + txnDetailsFromIni("GetTxnRefNo", "") + ", charges: INR "
				+ txnDetailsFromIni("GetCharges", "") + ", available Withdrawable balance: INR "
				+ getWalletBalanceFromIni("GetCashout", "") + ", available Retailer credit: INR "
				+ getWalletBalanceFromIni("GetRetailer", "");

		if (usrData.get("ASSERTION").equalsIgnoreCase("FCM Bank")) {
			fcmMethod(usrData, bankFCMHeading, bankFCMContent);
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("FCM Wallet")) {
			fcmMethod(usrData, walletFCMHeading, walletFCMContent);
		}
	}

	public void fcmMethod(Map<String, String> usrData, String heading, String content) {
		Assert.assertEquals(fcmHeading.getText(), heading);
		Assert.assertEquals(fcmContent.getText().substring(29), content);
		Log.info(fcmHeading.getText());
		Log.info(fcmContent.getText());
	}

	// Get Partner name
	public String partner() {
		return "RBL";
	}

	// Get mobile number from Ini file
	public String mobileNumFromIni() {
		return getLoginMobileFromIni("RetailerMobNum");
	}
}
