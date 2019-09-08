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

public class ElectricityPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	MongoDBUtils mongoDbUtils = new MongoDBUtils();
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	@FindBy(xpath = "//span[contains(text(),'Bill Payment')]")
	WebElement billPayments;

	@FindBy(xpath = "//span[contains(text(),'wallet balance')]")
	WebElement retailerWallet;

	@FindBy(xpath = "//span[contains(text(),'wallet balance')]/parent::p/following-sibling::p/span")
	WebElement retailerWalletBalance;

	@FindBy(xpath = "//span[contains(text(),'cashout balance')]")
	WebElement cashoutWallet;

	@FindBy(xpath = "//span[contains(text(),'cashout balance')]/parent::p/following-sibling::p/span")
	WebElement cashoutWalletBalance;

	@FindBy(xpath = "//h1[contains(text(),'Billers')]")
	WebElement pageTitle;

	@FindBy(xpath = "//span[contains(text(),'ELECTRICITY')]/parent::li")
	WebElement electricityIcon;

	@FindBy(id = "money-transfer-mobile-number")
	WebElement payerMobNum;

	@FindBy(id = "money-transfer-customer-name")
	WebElement payerName;

	@FindBy(xpath = "//label[contains(text(),'Payer Name')]/parent::div/div/input")
	WebElement disabledPayerName;

	@FindBy(xpath = "//div[contains(text(),'Pay New Bill')]")
	WebElement payNewBillButton;

	@FindBy(className = "biller-cards")
	WebElement billerCards;

	@FindBy(xpath = "//*[@id='ifsc-search-state']//span[contains(text(),'Select...')]/parent::span")
	WebElement billerList;

	@FindBy(xpath = "(//input[@id='money-transfer-beneficiary-name'])[1]")
	WebElement id1;

	@FindBy(xpath = "(//input[@id='money-transfer-beneficiary-name'])[2]")
	WebElement id2;

	@FindBy(xpath = "(//input[@id='money-transfer-beneficiary-name'])[3]")
	WebElement id3;

	@FindBy(xpath = "(//input[@id='money-transfer-beneficiary-name'])[1]/parent::div/preceding-sibling::label")
	WebElement idLabel1;

	@FindBy(xpath = "(//input[@id='money-transfer-beneficiary-name'])[2]/parent::div/preceding-sibling::label")
	WebElement idLabel2;

	@FindBy(xpath = "(//input[@id='money-transfer-beneficiary-name'])[3]/parent::div/preceding-sibling::label")
	WebElement idLabel3;

	@FindBy(xpath = "//div[contains(text(),'Biller Name')]/following-sibling::div[contains(@class,'bill-value')]")
	WebElement fetchedBillerName;

	@FindBy(xpath = "//electricity-form//div[contains(text(),'Account ID')]/following-sibling::div")
	WebElement fetchedBillerIdBescom;

	@FindBy(xpath = "//electricity-form//div[contains(text(),'Consumer Number')]/following-sibling::div")
	WebElement fetchedBillerIdMsedc;

	@FindBy(xpath = "//electricity-form//div[contains(text(),'Bill Number')]/following-sibling::div")
	WebElement fetchedBillNumber;

	@FindBy(xpath = "//electricity-form//div[contains(text(),'Customer Name')]/following-sibling::div")
	WebElement fetchedCustomerName;

	@FindBy(xpath = "//electricity-form//div[contains(text(),'Bill Amount')]/following-sibling::div")
	WebElement fetchedBillAmount;

	@FindBy(xpath = "//electricity-form//div[contains(text(),'Charges')]/following-sibling::div")
	WebElement fetchedCharges;

	@FindBy(xpath = "//electricity-form//div[contains(@class,'final-payment-amount')]")
	WebElement fetchedFinalAmount;

	@FindBy(xpath = "//button[contains(text(),'Proceed')]")
	WebElement proceedButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	WebElement clearButton;

	@FindBy(xpath = "//button[contains(text(),'Proceed to Pay')]")
	WebElement proceedToPayButton;

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

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]/div/div/div/h4[contains(text(),'!')]")
	WebElement elecTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]/div/div/div")
	WebElement elecTxnScreenType;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//div[contains(@class,'transaction-title')]")
	WebElement elecTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//i[contains(@class,'failure-cross')]/parent::span")
	WebElement elecTxnScreenFailureMessage;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//strong[contains(text(),'Biller Name')]/parent::div/following-sibling::div/div")
	WebElement txnScreenBillerName;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//strong[contains(text(),'Account ID')]/parent::div/following-sibling::div/div")
	WebElement txnScreenAccountID;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//strong[contains(text(),'Consumer Number')]/parent::div/following-sibling::div/div")
	WebElement txnScreenConsumerNumber;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//strong[contains(text(),'Bill Number')]/parent::div/following-sibling::div/div")
	WebElement txnScreenBillNumber;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//strong[contains(text(),'Customer Name')]/parent::div/following-sibling::div/div")
	WebElement txnScreenCustomerName;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//strong[contains(text(),'Bill Amount')]/parent::div/following-sibling::div/span")
	WebElement txnScreenBillAmount;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//strong[contains(text(),'Charges')]/parent::div/following-sibling::div/span")
	WebElement txnScreenCharges;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//strong[contains(text(),'Txn ID')]/parent::div/following-sibling::div/div")
	WebElement txnScreenTxnId;

	@FindBy(xpath = "//div[contains(@class,'recharge-modal')]//p[contains(@class,'cashCollected')]")
	WebElement txnScreenFinalAmount;

	@FindBy(xpath = "//button[contains(text(),'Exit')]")
	WebElement exitButton;

	@FindBy(xpath = "//button[contains(text(),'Retry')]")
	WebElement retryButton;

	@FindBy(xpath = "//button[contains(text(),'Done')]")
	WebElement doneButton;

	@FindBy(xpath = "//button[contains(text(),'Print Receipt')]")
	WebElement printButton;

	@FindBy(xpath = "//div[contains(@class,'toast-message')]")
	WebElement toasterMsg;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading1;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent1;

	// Load all objects
	public ElectricityPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void electricity(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			commonUtils.displayInitialBalance("retailer"); // display main wallet balance
			commonUtils.displayInitialBalance("cashout"); // display cashout wallet balance

			double initialWalletBalance = commonUtils.getInitialBalance("retailer"); // store main wallet balance
			double initialCashoutBalance = commonUtils.getInitialBalance("cashout"); // store cashout wallet balance

			// Update retailer wallet balance to 0 for scenario where amount > wallet
			if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
				dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "0");
			}

			clickElement(menu);
			commonUtils.refreshBalance(); // refresh wallet balances
			scrollElementDown(scrollBar, billPayments);
			Log.info("Bill Payments clicked");
			waitUntilElementIsClickableAndClickTheElement(pageTitle);
			System.out.println(pageTitle.getText() + " page displayed");
			clickElement(menu);

			if (usrData.get("ASSERTION").equalsIgnoreCase("Clear")) {
				clickElement(clearButton);
			}

			if (usrData.get("ASSERTION").contains("FCM")) {
				assertionOnFCM(usrData);
			} else {
				// Click on electricity icon
				waitUntilElementIsClickableAndClickTheElement(electricityIcon);
				Log.info("Electricity icon clicked");
				
				commonUtils.waitForSpinner();
				
				// Click on payer mobile number field
				waitUntilElementIsClickableAndClickTheElement(payerMobNum);
				payerMobNum.clear();
				payerMobNum.sendKeys(getCustomerDetailsFromIni(usrData.get("PAYERMOBILENUMBER")));
				Log.info("Payer mobile number " + payerMobNum.getText() + " entered");

				commonUtils.waitForSpinner();
			}

			if (usrData.get("BILLTYPE").equalsIgnoreCase("Existing")) {
				waitUntilElementIsClickableAndClickTheElement(billerCards);
				Log.info("Biller Card is clicked");

				if (usrData.get("BILLERNAME").equalsIgnoreCase("Bangalore Electricity Supply Company")) {
					waitUntilElementIsVisible(idLabel1);
					Assert.assertTrue(idLabel1.getText().contains("Account ID"));
					Log.info("Account Id verified");
				} else if (usrData.get("BILLERNAME").equalsIgnoreCase("MSEDC Limited")) {
					waitUntilElementIsVisible(idLabel1);
					Assert.assertTrue(idLabel1.getText().contains("Consumer Number"));
					Log.info("Consumer Number verified");
					waitUntilElementIsVisible(idLabel2);
					Assert.assertTrue(idLabel2.getText().contains("Billing Unit"));
					Log.info("Billing Unit verified");
					waitUntilElementIsVisible(idLabel3);
					Assert.assertTrue(idLabel3.getText().contains("Processing Cycle"));
					Log.info("Processing cycle verified");
				}
			} else if (usrData.get("BILLTYPE").equalsIgnoreCase("New")) {
				// Click on pay new bill button
				waitUntilElementIsClickableAndClickTheElement(payNewBillButton);
				Log.info("Pay New Bill button clicked");
				Thread.sleep(1000);

				waitUntilElementIsClickableAndClickTheElement(billerList);
				Log.info("Biller drop down clicked");
				String ifscState = "//li[contains(text(),'" + usrData.get("BILLERNAME") + "')]";
				WebElement ifscSearchState = wdriver.findElement(By.xpath(ifscState));
				ifscSearchState.click();
				Log.info(usrData.get("BILLERNAME") + " selected");

				if (usrData.get("BILLERNAME").equalsIgnoreCase("Bangalore Electricity Supply Company")) {
					waitUntilElementIsClickableAndClickTheElement(id1);
					id1.sendKeys(usrData.get("ACCOUNTID"));
					Log.info("Account Id entered");
				} else if (usrData.get("BILLERNAME").equalsIgnoreCase("MSEDC Limited")) {
					waitUntilElementIsClickableAndClickTheElement(id1);
					id1.sendKeys(usrData.get("ACCOUNTID"));
					Log.info("Account Id entered");
					waitUntilElementIsClickableAndClickTheElement(id2);
					id2.sendKeys(usrData.get("BILLINGUNIT"));
					Log.info("Billing Unit entered");
					waitUntilElementIsClickableAndClickTheElement(id3);
					id3.sendKeys(usrData.get("PROSCYCLE"));
					Log.info("Processing cycle entered");
				}
			}

			mongoDbUtils.updateBillpayVendor(usrData.get("BILLERNAME"), usrData.get("VENDOR"));

			if (usrData.get("FETCHBUTTON").equalsIgnoreCase("YES")) {
				// Click on Proceed button
				waitUntilElementIsClickableAndClickTheElement(proceedButton);

				commonUtils.waitForSpinner();

				if (usrData.get("ASSERTION").equalsIgnoreCase("Bill not fetched")) {
					waitUntilElementIsVisible(toasterMsg);
					Assert.assertEquals(toasterMsg.getText(), "Bill was not fetched");
					Log.info(toasterMsg.getText());
				} else {
					waitUntilElementIsVisible(fetchedBillerName);
					Assert.assertEquals(fetchedBillerName.getText(), usrData.get("BILLERNAME"));
					Log.info("Biller name is " + usrData.get("BILLERNAME"));

					if (usrData.get("BILLERNAME").equalsIgnoreCase("Bangalore Electricity Supply Company")) {
						Assert.assertEquals(fetchedBillerIdBescom.getText(), usrData.get("ACCOUNTID"));
						Log.info("Account Id fetched: " + usrData.get("ACCOUNTID"));

						if (usrData.get("VENDOR").equalsIgnoreCase("BILLAVENUE")) {
							Assert.assertEquals(fetchedBillNumber.getText(), usrData.get("BILLNUMBER"));
							Log.info("Bill Number fetched: " + usrData.get("BILLNUMBER"));

							Assert.assertEquals(fetchedCustomerName.getText(), usrData.get("CUSTOMERNAME"));
							Log.info("Customer Name fetched: " + usrData.get("CUSTOMERNAME"));
						}

					} else if (usrData.get("BILLERNAME").equalsIgnoreCase("MSEDC Limited")) {
						Assert.assertEquals(fetchedBillerIdMsedc.getText(), usrData.get("ACCOUNTID"));
						Log.info("Consumer Number fetched: " + usrData.get("ACCOUNTID"));

						if (usrData.get("VENDOR").equalsIgnoreCase("BILLAVENUE")) {
							Assert.assertEquals(fetchedCustomerName.getText(), usrData.get("CUSTOMERNAME"));
							Log.info("Customer Name fetched: " + usrData.get("CUSTOMERNAME"));
						}
					}

					Assert.assertEquals(replaceSymbols(fetchedBillAmount.getText()), usrData.get("BILLAMOUNT"));
					Log.info("Bill Amount fetched: " + usrData.get("BILLAMOUNT"));
					txnDetailsFromIni("StoreTxfAmount", replaceSymbols(fetchedBillAmount.getText()));

					String chrges = dbUtils.getBillPaymentCharges(usrData.get("VENDOR"));
					Assert.assertEquals(replaceSymbols(fetchedCharges.getText()), chrges);
					Log.info("Charges fetched: " + chrges);
					txnDetailsFromIni("StoreCharges", chrges);

					double amount = Double.parseDouble(replaceSymbols(fetchedBillAmount.getText()));
					double charges = Double.parseDouble(chrges);
					double totalAmount = amount + charges;
					String cashToBeCollected = df.format(totalAmount);
					Assert.assertEquals(replaceSymbols(fetchedFinalAmount.getText()), cashToBeCollected);
					Log.info("Cash to be Collected: " + replaceSymbols(fetchedFinalAmount.getText()));
				}
			} else if (usrData.get("FETCHBUTTON").equalsIgnoreCase("Clear")) {
				// Click on Clear button
				clickElement(clearButton);
			}

			if (usrData.get("PAYBUTTON").equalsIgnoreCase("Yes")) {
				
				// Click on Proceed to pay button
				waitUntilElementIsClickableAndClickTheElement(proceedToPayButton);
				
				if (getWalletBalanceFromIni("GetCashout", "").equals("0.00")) {
					Log.info("Cashout Balance is 0, hence money will be deducted from Main Wallet");
				} else {
					commonUtils.chooseWalletScreen(usrData);
				}

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
						+ "following-sibling::div/following-sibling::div/button[contains(text(),'" + mpinButtonName
						+ "')]";
				WebElement mpinScreenButton = wdriver.findElement(By.xpath(mpinScreenButtonXpath));
				waitUntilElementIsClickableAndClickTheElement(mpinScreenButton);
				Log.info(mpinButtonName + " button clicked");
				if (mpinButtonName.equalsIgnoreCase("Cancel")) {
					Log.info("Cancel button clicked");
				} else if (mpinButtonName.equalsIgnoreCase("Submit")) {
					commonUtils.waitForSpinner();

					waitUntilElementIsVisible(elecTxnScreen);
					Log.info("Txn screen displayed");

					// Update retailer wallet balance to 1000000 for scenario where amount > wallet
					if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
						dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
					}

					// Verify the details on transaction screen
					if (elecTxnScreen.getText().equalsIgnoreCase("Success!")
							|| elecTxnScreen.getText().equalsIgnoreCase("Pending!")) {
						assertionOnSuccessScreen(usrData);
						if (usrData.get("ASSERTION").equalsIgnoreCase("Success SMS")) {
							assertionOnSMS(usrData);
						}
						if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Print")) {
							printButton.click();
							Log.info("Print button clicked");
						}
						doneButton.click();
						Log.info("Done button clicked");
						commonUtils.refreshBalance();
						verifyUpdatedBalanceAfterSuccessTxn(usrData, initialWalletBalance);
					} else if (elecTxnScreen.getText().equalsIgnoreCase("Failed!")) {
						if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
							assertionOnFailedScreen(usrData);
							waitUntilElementIsClickableAndClickTheElement(exitButton);
							Log.info("Exit button clicked");
							commonUtils.refreshBalance();
							verifyUpdatedBalanceAfterFailTxn(usrData, initialWalletBalance);
						} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
							assertionOnFailedScreen(usrData);
							if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
								exitButton.click();
								Log.info("Exit button clicked");
							} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
								retryButton.click();
								waitUntilElementIsVisible(MPINScreen);
								Log.info("MPIN screen displayed");
								waitUntilElementIsClickableAndClickTheElement(enterMPIN);
								enterMPIN.sendKeys(getAuthfromIni("MPIN"));
								Log.info("MPIN entered");
								waitUntilElementIsClickableAndClickTheElement(submitMPIN);
								Log.info("Submit button clicked");
								waitUntilElementIsVisible(elecTxnScreen);
								Log.info("Txn screen displayed");
								assertionOnSuccessScreen(usrData);
								doneButton.click();
								Log.info("Done button clicked");
								commonUtils.refreshBalance();
								verifyUpdatedBalanceAfterSuccessTxn(usrData, initialWalletBalance);
							}
						}
					}
				}
			} else if (usrData.get("PAYBUTTON").equalsIgnoreCase("Clear")) {
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
		if (elecTxnScreen.getText().equalsIgnoreCase("Success!")) {
			Assert.assertEquals(elecTxnScreenMessage.getText(), "Bill Payment Successful!");
		} else if (elecTxnScreen.getText().equalsIgnoreCase("Pending!")) {
			Assert.assertEquals(elecTxnScreenMessage.getText(),
					"Bill Payment Awaiting Confirmation. Use status enquiry to get updated status of this transaction.");
		}
		Log.info(elecTxnScreenMessage.getText());

		Assert.assertEquals(txnScreenBillerName.getText(), usrData.get("BILLERNAME"));
		Log.info("Biller Name: " + usrData.get("BILLERNAME"));

		if (usrData.get("BILLERNAME").equalsIgnoreCase("Bangalore Electricity Supply Company")) {
			Assert.assertEquals(txnScreenAccountID.getText(), usrData.get("ACCOUNTID"));
			Log.info("Account Id: " + usrData.get("ACCOUNTID"));

			if (usrData.get("VENDOR").equalsIgnoreCase("BILLAVENUE")) {
				Assert.assertEquals(txnScreenBillNumber.getText(), usrData.get("BILLNUMBER"));
				Log.info("Bill Number: " + usrData.get("BILLNUMBER"));

				Assert.assertEquals(txnScreenCustomerName.getText(), usrData.get("CUSTOMERNAME"));
				Log.info("Customer Name: " + usrData.get("CUSTOMERNAME"));
			}
		} else if (usrData.get("BILLERNAME").equalsIgnoreCase("MSEDC Limited")) {
			Assert.assertEquals(txnScreenConsumerNumber.getText(), usrData.get("ACCOUNTID"));
			Log.info("Consumer Number: " + usrData.get("ACCOUNTID"));

			if (usrData.get("VENDOR").equalsIgnoreCase("BILLAVENUE")) {
				Assert.assertEquals(txnScreenCustomerName.getText(), usrData.get("CUSTOMERNAME"));
				Log.info("Customer Name: " + usrData.get("CUSTOMERNAME"));
			}
		}

		Assert.assertEquals(replaceSymbols(txnScreenBillAmount.getText()), txnDetailsFromIni("GetTxfAmount", ""));
		Log.info("Bill Amount: " + txnDetailsFromIni("GetTxfAmount", ""));

		Assert.assertEquals(replaceSymbols(txnScreenCharges.getText()), txnDetailsFromIni("GetCharges", ""));
		Log.info("Charges: " + txnDetailsFromIni("GetCharges", ""));

		txnDetailsFromIni("StoreTxnRefNo", txnScreenTxnId.getText());

		double amount = Double.parseDouble(replaceSymbols(fetchedBillAmount.getText()));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double totalAmount = amount + charges;
		String cashToBeCollected = df.format(totalAmount);
		Assert.assertEquals(replaceSymbols(txnScreenFinalAmount.getText()), cashToBeCollected);
		Log.info("Cash to be Collected: " + replaceSymbols(txnScreenFinalAmount.getText()));
	}

	// Verify details on failed screen
	public void assertionOnFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid MPIN")) {
			Assert.assertEquals(elecTxnScreenFailureMessage.getText(), "Authentication Failed Invalid MPIN  ");
			Log.info(elecTxnScreenFailureMessage.getText());
		} else if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
			Assert.assertEquals(elecTxnScreenFailureMessage.getText(), "Insufficient balance  ");
			Log.info(elecTxnScreenFailureMessage.getText());
			dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "1000000");
		} else {
			Assert.assertEquals(elecTxnScreenMessage.getText(), "Bill Payment failed");
			Log.info(elecTxnScreenMessage.getText());

			Assert.assertEquals(txnScreenBillerName.getText(), usrData.get("BILLERNAME"));
			Log.info("Biller Name: " + usrData.get("BILLERNAME"));

			if (usrData.get("BILLERNAME").equalsIgnoreCase("Bangalore Electricity Supply Company")) {
				Assert.assertEquals(txnScreenAccountID.getText(), usrData.get("ACCOUNTID"));
				Log.info("Account Id: " + usrData.get("ACCOUNTID"));
			} else if (usrData.get("BILLERNAME").equalsIgnoreCase("MSEDC Limited")) {
				Assert.assertEquals(txnScreenConsumerNumber.getText(), usrData.get("ACCOUNTID"));
			}

			Assert.assertEquals(replaceSymbols(txnScreenBillAmount.getText()), txnDetailsFromIni("GetTxfAmount", ""));
			Log.info("Bill Amount: " + txnDetailsFromIni("GetTxfAmount", ""));

			Assert.assertEquals(replaceSymbols(txnScreenCharges.getText()), txnDetailsFromIni("GetCharges", ""));
			Log.info("Charges: " + txnDetailsFromIni("GetCharges", ""));
		}
	}

	// SMS assertion
	public void assertionOnSMS(Map<String, String> usrData) throws ClassNotFoundException, InterruptedException {
		String successSMS = "Thank you for payment of INR " + txnDetailsFromIni("GetTxfAmount", "") + " against "
				+ usrData.get("BILLERNAME") + ", Account ID - " + usrData.get("ACCOUNTID") + ", Txn Ref ID "
				+ txnDetailsFromIni("GetTxnRefNo", "") + " on " + dbUtils.billPaymentDate() + ".";
		Assert.assertEquals(successSMS, dbUtils.sms());
		Log.info(successSMS);
	}

	// Assertion after success or orange screen is displayed
	public void verifyUpdatedBalanceAfterSuccessTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double newWalletBal = initialWalletBalance - amount - charges;
		String newWalletBalance = df.format(newWalletBal);
		Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
		Log.info("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
	}

	// Assertion after success or orange screen is displayed
	public void verifyUpdatedBalanceAfterFailTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		double newWalletBal = initialWalletBalance;
		String newWalletBalance = df.format(newWalletBal);
		Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
		Log.info("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
	}

	public void assertionOnFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successSummaryFCMHeading = "Bill Payment";
		String successSummaryFCMContent = "Bill payment of ₹ " + txnDetailsFromIni("GetTxfAmount", "") + " against "
				+ usrData.get("BILLERNAME") + ", " + usrData.get("ACCOUNTID") + ", Txn Ref ID "
				+ txnDetailsFromIni("GetTxnRefNo", "") + " on " + dbUtils.billPaymentDate() + " is successful.";
		Assert.assertEquals(fcmHeading1.getText(), successSummaryFCMHeading);
		Assert.assertEquals(fcmContent1.getText(), successSummaryFCMContent);
		Log.info(fcmHeading1.getText());
		Log.info(fcmContent1.getText());
	}

}