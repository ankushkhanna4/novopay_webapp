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
import in.novopay.platform_ui.utils.MongoDBUtils;

public class ElectricityPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	MongoDBUtils mongoDbUtils = new MongoDBUtils();
	DecimalFormat df = new DecimalFormat("#.00");

	WebDriverWait wait = new WebDriverWait(wdriver, 30);
	WebDriverWait waitWelcome = new WebDriverWait(wdriver, 3);

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

	@FindBy(xpath = "//span[contains(text(),'Bill Payment')]")
	WebElement billPayments;

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

	@FindBy(xpath = "//div[contains(text(),'Pay New Bill')]/parent::div/parent::div")
	WebElement payNewBillButton;
	// *[@class='biller-cards']
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

	@FindBy(xpath = "//h4[contains(text(),'Processing...')]")
	WebElement processingScreen;

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

	// Load all objects
	public ElectricityPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void electricity(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			displayInitialBalance(usrData, "retailer"); // display wallet balances in console
			double initialWalletBalance = getInitialBalance("retailer"); // store wallet balance as double datatype

			// Update retailer wallet balance to 0 for scenario where amount > wallet
			if (usrData.get("ASSERTION").equalsIgnoreCase("Insufficient Balance")) {
				dbUtils.updateWalletBalance(mobileNumFromIni(), "retailer", "0");
			}

			menu.click();
			refreshBalance(); // refresh wallet balances
//			menu.click();
//			menu.click();
//			wait.until(ExpectedConditions.elementToBeClickable(scrollBar));
			scrollElementDown(scrollBar, billPayments);
			Log.info("Bill Payments clicked");
			wait.until(ExpectedConditions.elementToBeClickable(pageTitle));
			menu.click();

			if (usrData.get("ASSERTION").equalsIgnoreCase("Clear")) {
				clickElement(clearButton);
			}

			if (usrData.get("ASSERTION").contains("FCM")) {
				assertionOnFCM(usrData);
			} else {
				// Click on payer mobile number field
				wait.until(ExpectedConditions.elementToBeClickable(payerMobNum));
				clickElement(payerMobNum);
				payerMobNum.clear();
				payerMobNum.sendKeys(getCustomerDetailsFromIni(usrData.get("PAYERMOBILENUMBER")));
				Log.info("Payer mobile number " + payerMobNum.getText() + " entered");

				if (usrData.get("PAYERNAME").equalsIgnoreCase("NewName")) {
					// Click on payer name number field
					wait.until(ExpectedConditions.elementToBeClickable(payerName));
					clickElement(payerName);
					payerName.clear();
					payerName.sendKeys(getCustomerDetailsFromIni("NewName"));
					Log.info("Payer name entered");
				}

				// Click on electricity icon
				wait.until(ExpectedConditions.elementToBeClickable(electricityIcon));
				clickElement(electricityIcon);
				Log.info("Electricity icon clicked");

				waitForSpinner();
			}

			if (usrData.get("PAYERMOBILENUMBER").equalsIgnoreCase("ExistingNum")) {
				wait.until(ExpectedConditions.elementToBeClickable(billerCards));
				Assert.assertTrue(disabledPayerName.getAttribute("class").contains("pointer-none"));
				Log.info("Payer name is " + getCustomerDetailsFromIni("ExistingName"));
			}

			if (usrData.get("BILLTYPE").equalsIgnoreCase("Existing")) {
				wait.until(ExpectedConditions.elementToBeClickable(billerCards));
				clickElement(billerCards);
				Log.info("Biller Card is clicked");

				if (usrData.get("BILLERNAME").equalsIgnoreCase("Bangalore Electricity Supply Company")) {
					wait.until(ExpectedConditions.elementToBeClickable(idLabel1));
					Assert.assertTrue(idLabel1.getText().contains("Account ID"));
					Log.info("Account Id verified");
				} else if (usrData.get("BILLERNAME").equalsIgnoreCase("MSEDC Limited")) {
					wait.until(ExpectedConditions.elementToBeClickable(idLabel1));
					Assert.assertTrue(idLabel1.getText().contains("Consumer Number"));
					Log.info("Consumer Number verified");
					wait.until(ExpectedConditions.elementToBeClickable(idLabel2));
					Assert.assertTrue(idLabel2.getText().contains("Billing Unit"));
					Log.info("Billing Unit verified");
					wait.until(ExpectedConditions.elementToBeClickable(idLabel3));
					Assert.assertTrue(idLabel3.getText().contains("Processing Cycle"));
					Log.info("Processing cycle verified");
				}
			} else if (usrData.get("BILLTYPE").equalsIgnoreCase("New")) {
				// Click on pay new bill button
				wait.until(ExpectedConditions.elementToBeClickable(payNewBillButton));
				clickElement(payNewBillButton);
				Log.info("Pay New Bill button clicked");

				wait.until(ExpectedConditions.elementToBeClickable(billerList));
				billerList.click();
				Log.info("Biller drop down clicked");
				String ifscState = "//li[contains(text(),'" + usrData.get("BILLERNAME") + "')]";
				WebElement ifscSearchState = wdriver.findElement(By.xpath(ifscState));
				ifscSearchState.click();
				Log.info(usrData.get("BILLERNAME") + " selected");

				if (usrData.get("BILLERNAME").equalsIgnoreCase("Bangalore Electricity Supply Company")) {
					wait.until(ExpectedConditions.elementToBeClickable(id1));
					clickElement(id1);
					id1.sendKeys(usrData.get("ACCOUNTID"));
					Log.info("Account Id entered");
				} else if (usrData.get("BILLERNAME").equalsIgnoreCase("MSEDC Limited")) {
					wait.until(ExpectedConditions.elementToBeClickable(id1));
					clickElement(id1);
					id1.sendKeys(usrData.get("ACCOUNTID"));
					Log.info("Account Id entered");
					wait.until(ExpectedConditions.elementToBeClickable(id2));
					clickElement(id2);
					id2.sendKeys(usrData.get("BILLINGUNIT"));
					Log.info("Billing Unit entered");
					wait.until(ExpectedConditions.elementToBeClickable(id3));
					clickElement(id3);
					id3.sendKeys(usrData.get("PROSCYCLE"));
					Log.info("Processing cycle entered");
				}
			}

			mongoDbUtils.updateBillpayVendor(usrData.get("BILLERNAME"), usrData.get("VENDOR"));

			if (usrData.get("FETCHBUTTON").equalsIgnoreCase("YES")) {
				// Click on Proceed button
				wait.until(ExpectedConditions.elementToBeClickable(proceedButton));
				clickElement(proceedButton);

				waitForSpinner();

				if (usrData.get("ASSERTION").equalsIgnoreCase("Bill not fetched")) {
					wait.until(ExpectedConditions.visibilityOf(toasterMsg));
					Assert.assertEquals(toasterMsg.getText(), "Bill was not fetched");
					Log.info(toasterMsg.getText());
				} else {
					wait.until(ExpectedConditions.elementToBeClickable(fetchedBillerName));
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
				wait.until(ExpectedConditions.visibilityOf(proceedToPayButton));
				clickElement(proceedToPayButton);
				
				if (getWalletBalanceFromIni("GetCashout", "").equals("0.00")) {
					Log.info("Cashout Balance is 0, hence money will be deducted from Main Wallet");
				} else {
					chooseWalletScreen(usrData);
				}

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
					waitForSpinner();

					wait.until(ExpectedConditions.visibilityOf(elecTxnScreen));
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
						refreshBalance();
						verifyUpdatedBalanceAfterSuccessTxn(usrData, initialWalletBalance);
					} else if (elecTxnScreen.getText().equalsIgnoreCase("Failed!")) {
						if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
							assertionOnFailedScreen(usrData);
							wait.until(ExpectedConditions.elementToBeClickable(exitButton));
							exitButton.click();
							Log.info("Exit button clicked");
							refreshBalance();
							verifyUpdatedBalanceAfterFailTxn(usrData, initialWalletBalance);
						} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
							assertionOnFailedScreen(usrData);
							if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
								exitButton.click();
								Log.info("Exit button clicked");
							} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
								retryButton.click();
								wait.until(ExpectedConditions.visibilityOf(MPINScreen));
								Log.info("MPIN screen displayed");
								wait.until(ExpectedConditions.elementToBeClickable(enterMPIN));
								enterMPIN.click();
								enterMPIN.sendKeys(getAuthfromIni("MPIN"));
								Log.info("MPIN entered");
								wait.until(ExpectedConditions.elementToBeClickable(submitMPIN));
								submitMPIN.click();
								Log.info("Submit button clicked");
								wait.until(ExpectedConditions.visibilityOf(elecTxnScreen));
								Log.info("Txn screen displayed");
								assertionOnSuccessScreen(usrData);
								doneButton.click();
								Log.info("Done button clicked");
								refreshBalance();
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

	// Show balances in console
	public void displayInitialBalance(Map<String, String> usrData, String wallet) throws ClassNotFoundException {
		String walletBalance = dbUtils.getWalletBalance(mobileNumFromIni(), "retailer");
		String walletBal = walletBalance.substring(0, walletBalance.length() - 4);
		String cashoutBalance = dbUtils.getWalletBalance(mobileNumFromIni(), "cashout");
		String cashoutBal = cashoutBalance.substring(0, cashoutBalance.length() - 4);
		String merchantBalance = dbUtils.getWalletBalance(mobileNumFromIni(), "merchant");
		String merchantBal = merchantBalance.substring(0, merchantBalance.length() - 4);

		wait.until(ExpectedConditions.elementToBeClickable(retailerWalletBalance));

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
		return getLoginMobileFromIni("RetailerMobNum");
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

	// Confirm screen
	public void chooseWalletScreen(Map<String, String> usrData) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(chooseWalletScreen));
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
		wait.until(ExpectedConditions.visibilityOf(chooseWalletProceedButton));
		chooseWalletProceedButton.click();
//			Thread.sleep(2000);
		Log.info("Proceed button clicked");
	}
}