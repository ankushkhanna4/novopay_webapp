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
import in.novopay.platform_ui.utils.Log;
import in.novopay.platform_ui.utils.MongoDBUtils;

public class FullertonPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	MongoDBUtils mongoDbUtils = new MongoDBUtils();
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	@FindBy(xpath = "//span[contains(text(),'Cash Management')]")
	WebElement cashManagement;

	@FindBy(xpath = "//span[contains(text(),'wallet balance')]")
	WebElement retailerWallet;

	@FindBy(xpath = "//span[contains(text(),'wallet balance')]/parent::p/following-sibling::p/span")
	WebElement retailerWalletBalance;

	@FindBy(xpath = "//span[contains(text(),'cashout balance')]")
	WebElement cashoutWallet;

	@FindBy(xpath = "//span[contains(text(),'cashout balance')]/parent::p/following-sibling::p/span")
	WebElement cashoutWalletBalance;

	@FindBy(xpath = "//h1[contains(text(),'Cash Management Services (CMS)')]")
	WebElement pageTitle;

	@FindBy(xpath = "//span[contains(text(),'Fullerton')]/parent::li")
	WebElement fullertonIcon;

	@FindBy(id = "money-transfer-emp-id")
	WebElement empId;

	@FindBy(xpath = "//button[contains(text(),'Get Amount')]")
	WebElement getAmountButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	WebElement clearButton;

	@FindBy(id = "money-transfer-amount-to-be-transferred")
	WebElement fetchedAmount;

	@FindBy(xpath = "//fullerton-collection/div//button[contains(text(),'Submit')]")
	WebElement ftSubmitButton;

	@FindBy(xpath = "//*[@id='money-transfer-amount-to-be-transferred']/following-sibling::ul/li")
	WebElement amountErrorMsg;

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
	public FullertonPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void fullerton(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			// Update wallet balance as per the scenarios
			updateWalletBalance(usrData);

			commonUtils.selectFeatureFromMenu2(cashManagement, pageTitle);

			commonUtils.displayInitialBalance("retailer"); // display main wallet balance
			commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

			// Click on Fullerton icon
			waitUntilElementIsClickableAndClickTheElement(fullertonIcon);
			Log.info("Fullerton icon clicked");

			String dueDate = "";
			if (usrData.get("DUEDATE").equalsIgnoreCase("Today")) {
				dueDate = getTodaysDate("dd-MMM-yy");
			} else if (usrData.get("DUEDATE").equalsIgnoreCase("Old Date")) {
				dueDate = "30-Aug-18";
			}

			int dueAmount = 0, paidAmount = 0;
			dueAmount = Integer.parseInt(usrData.get("DUEAMOUNT"));
			paidAmount = Integer.parseInt(usrData.get("PAIDAMOUNT"));

			if (!usrData.get("STATUS").equalsIgnoreCase("SUCCESS")) {
				mongoDbUtils.updateValues("111", dueDate, dueAmount, paidAmount, usrData.get("STATUS"),
						usrData.get("OFFICERMOBNUM"), usrData.get("ADDMOBNUM"));
			}

			// Click on depositor mobile number field
			waitUntilElementIsClickableAndClickTheElement(empId);
			empId.clear();
			empId.sendKeys(usrData.get("EMPID"));
			Log.info("Employee Id entered");

			if (usrData.get("GETAMOUNT").equalsIgnoreCase("YES")) {

				// Click on Get Amount button
				waitUntilElementIsClickableAndClickTheElement(getAmountButton);

				commonUtils.waitForSpinner();

				if (usrData.get("EMPID").equalsIgnoreCase("999")
						|| usrData.get("DUEDATE").equalsIgnoreCase("Old Date")) {
					waitUntilElementIsVisible(toasterMsg);
					Assert.assertEquals(toasterMsg.getText(), "Employee ID not found. Please re-check employee ID.");
					Log.info(toasterMsg.getText());
				} else if (usrData.get("STATUS").equalsIgnoreCase("SUCCESS")) {
					waitUntilElementIsVisible(toasterMsg);
					Assert.assertEquals(toasterMsg.getText(), "You have reached maximum deposit limit for the day.");
					Log.info(toasterMsg.getText());
				} else {
					// Store fetched amount
					waitUntilElementIsVisible(fetchedAmount);
					String amount = fetchedAmount.getAttribute("value");
					Log.info("Amount is " + amount);
					cmsDetailsFromIni("StoreFtAmount", replaceSymbols(amount));

					if (usrData.get("ASSERTION").contains("Reversed")) {
						mongoDbUtils.updateValues("111", dueDate, dueAmount, paidAmount, "SUCCESS",
								usrData.get("OFFICERMOBNUM"), usrData.get("ADDMOBNUM"));
					}
				}
			} else if (usrData.get("GETAMOUNT").equalsIgnoreCase("Clear")) {
				// Click on Clear button
				clickElement(clearButton);
			}

			if (usrData.get("SUBMIT").equalsIgnoreCase("Yes")) {
				if (!usrData.get("AMOUNT").equalsIgnoreCase("SKIP")) {
					fetchedAmount.clear();
					Thread.sleep(1000);
					fetchedAmount.sendKeys(usrData.get("AMOUNT"));
					cmsDetailsFromIni("StoreFtAmount", usrData.get("AMOUNT"));
				}

				if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Wallet")) {
					waitUntilElementIsVisible(amountErrorMsg);
					Assert.assertEquals(amountErrorMsg.getText(), "Insufficient wallet balance");
					Log.info(amountErrorMsg.getText());
				} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Max")) {
					waitUntilElementIsVisible(amountErrorMsg);
					Assert.assertEquals(amountErrorMsg.getText().substring(0, 43),
							"Amount entered exceeds your available limit");
					Log.info(amountErrorMsg.getText());
				} else {
					// Click on Submit button
					waitUntilElementIsClickableAndClickTheElement(ftSubmitButton);

					if (getWalletBalanceFromIni("GetCashout", "").equals("0.00")) {
						Log.info("Cashout Balance is 0, hence money will be deducted from Main Wallet");
					} else {
						commonUtils.chooseWalletScreen(usrData);
					}

					if (!getWalletFromIni("GetWallet", "").equalsIgnoreCase("-")) {
						Thread.sleep(1000);
						waitUntilElementIsVisible(MPINScreen);
						Log.info("MPIN screen displayed");
						waitUntilElementIsClickableAndClickTheElement(enterMPIN);
						if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
							enterMPIN.sendKeys(getAuthfromIni("MPIN"));
						} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
							enterMPIN.sendKeys("9999");
						}
						Log.info("MPIN entered");

						String mpinButtonName = usrData.get("MPINSCREENBUTTON");
						String mpinScreenButtonXpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/"
								+ "following-sibling::div/following-sibling::div/button[contains(text(),'"
								+ mpinButtonName + "')]";
						WebElement mpinScreenButton = wdriver.findElement(By.xpath(mpinScreenButtonXpath));
						waitUntilElementIsClickableAndClickTheElement(mpinScreenButton);
						Log.info(mpinButtonName + " button clicked");
						if (mpinButtonName.equalsIgnoreCase("Cancel")) {
							Log.info("Cancel button clicked");
						} else if (mpinButtonName.equalsIgnoreCase("Submit")) {
							commonUtils.processingScreen();

							waitUntilElementIsVisible(cmsTxnScreen);
							Log.info("Txn screen displayed");

							// Verify the details on transaction screen
							if (cmsTxnScreen.getText().equalsIgnoreCase("Success!")) {
								assertionOnSuccessScreen(usrData);
								assertionOnSMS(usrData);

								waitUntilElementIsClickableAndClickTheElement(doneButton);
								Log.info("Done button clicked");
								commonUtils.refreshBalance();
								verifyUpdatedBalanceAfterSuccessTxn(usrData);
							} else if (cmsTxnScreen.getText().equalsIgnoreCase("Failed!")) {
								if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
									assertionOnFailedScreen(usrData);
									assertionOnSMS(usrData);
									if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
										Log.info("Clicking exit button");
									} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
										retryButton.click();
										Thread.sleep(1000);
										waitUntilElementIsVisible(MPINScreen);
										Log.info("MPIN screen displayed");
										waitUntilElementIsClickableAndClickTheElement(enterMPIN);
										enterMPIN.sendKeys(getAuthfromIni("MPIN"));
										Log.info("MPIN entered");
										waitUntilElementIsClickableAndClickTheElement(submitMPIN);
										Log.info("Submit button clicked");
										commonUtils.processingScreen();
										waitUntilElementIsVisible(cmsTxnScreen);
										Log.info("Txn screen displayed");
										assertionOnFailedScreen(usrData);
									}
									waitUntilElementIsClickableAndClickTheElement(exitButton);
									Log.info("Exit button clicked");
								} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
									waitUntilElementIsVisible(cmsTxnScreenMessage);
									Log.info(cmsTxnScreenMessage.getText());
									if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
										exitButton.click();
										Log.info("Exit button clicked");
									} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
										retryButton.click();
										Thread.sleep(1000);
										waitUntilElementIsVisible(MPINScreen);
										Log.info("MPIN screen displayed");
										waitUntilElementIsClickableAndClickTheElement(enterMPIN);
										enterMPIN.sendKeys(getAuthfromIni("MPIN"));
										Log.info("MPIN entered");
										waitUntilElementIsClickableAndClickTheElement(submitMPIN);
										Log.info("Submit button clicked");
										commonUtils.processingScreen();
										waitUntilElementIsVisible(cmsTxnScreen);
										Log.info("Txn screen displayed");
										assertionOnSuccessScreen(usrData);
										doneButton.click();
										Log.info("Done button clicked");
										commonUtils.refreshBalance();
									}
								}
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

	// Verify details on success screen
	public void assertionOnSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(cmsTxnScreenMessage.getText(), "Deposit to Fullerton successful.");
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
					"Information about this employee is not present in our system. Transaction reversed successfully.");
		}
		Log.info(cmsTxnScreenMessage.getText());
	}

	// SMS assertion
	public void assertionOnSMS(Map<String, String> usrData) throws ClassNotFoundException, InterruptedException {
		String successSMS = "Success! Deposit of Rs " + cmsDetailsFromIni("FtAmount", "") + " for EMP-ID "
				+ usrData.get("EMPID") + " was successful.";
		String failSMS = "Failure! Deposit of Rs " + cmsDetailsFromIni("FtAmount", "") + " for EMP-ID "
				+ usrData.get("EMPID") + " failed.";
		Thread.sleep(5000);
		if (usrData.get("ASSERTION").equalsIgnoreCase("Success SMS")) {
			Assert.assertEquals(successSMS, dbUtils.sms());
			Assert.assertEquals(successSMS, dbUtils.smsFt());
			try {
				Assert.assertEquals(mongoDbUtils.getOfficerMobNum(), dbUtils.smsNum2());
				Assert.assertEquals(mongoDbUtils.getAddMobNum(), dbUtils.smsNum1());
			} catch (Exception e) {
				Assert.assertEquals(mongoDbUtils.getOfficerMobNum(), dbUtils.smsNum1());
				Assert.assertEquals(mongoDbUtils.getAddMobNum(), dbUtils.smsNum2());
			}
			Log.info(successSMS);
			Log.info("SMS sent successfully to " + dbUtils.smsNum2() + " and " + dbUtils.smsNum1());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Reversed SMS")) {
			Assert.assertEquals(failSMS, dbUtils.sms());
			Assert.assertEquals(mongoDbUtils.getOfficerMobNum(), dbUtils.smsNum1());
			Log.info(failSMS);
			Log.info("SMS sent successfully to " + dbUtils.smsNum1());
		}
	}

	// Assertion after success or orange screen is displayed
	public void verifyUpdatedBalanceAfterSuccessTxn(Map<String, String> usrData) throws ClassNotFoundException {
		double initialWalletBalance = 1000000.00;
		if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
			initialWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetRetailer", ""));
		} else if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Cashout")) {
			initialWalletBalance = Double.parseDouble(getWalletBalanceFromIni("GetCashout", ""));
		}
		double amount = Double.parseDouble(cmsDetailsFromIni("FtAmount", ""));
		double comm = amount * 2 / 1000;
		double commission = Math.round(comm * 100.0) / 100.0;
		double taxDS = commission * Double.parseDouble(dbUtils.getTDSPercentage(mobileNumFromIni())) / 10000;
		double tds = Math.round(taxDS * 100.0) / 100.0;
		double newWalletBal = initialWalletBalance - amount + commission - tds;
		txnDetailsFromIni("StoreComm", String.valueOf(commission));
		txnDetailsFromIni("StoreTds", String.valueOf(tds));
		String newWalletBalance = df.format(newWalletBal);
		if (getWalletFromIni("GetWallet", "").equalsIgnoreCase("Main")) {
			Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
			Log.info("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
		} else {
			Assert.assertEquals(replaceSymbols(cashoutWalletBalance.getText()), newWalletBalance);
			Log.info("Updated Cashout Wallet Balance: " + replaceSymbols(cashoutWalletBalance.getText()));
		}
	}

	public void updateWalletBalance(Map<String, String> usrData) throws ClassNotFoundException {
		if (usrData.get("ASSERTION").equalsIgnoreCase("Main < Amount")) {
			dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Cashout < Amount")) {
			dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "1");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Amount > Both Wallets")) {
			dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1");
			dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "1");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Main=0 Cashout!=0")) {
			dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "0");
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Main!=0 Cashout=0")) {
			dbUtils.updateWalletBalance(mobileNumFromIni(), "cashout", "0");
		}
	}
}