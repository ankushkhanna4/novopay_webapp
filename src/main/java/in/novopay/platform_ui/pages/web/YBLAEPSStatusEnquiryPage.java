package in.novopay.platform_ui.pages.web;

import java.awt.AWTException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.Log;
import in.novopay.platform_ui.utils.ServerUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class YBLAEPSStatusEnquiryPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	ServerUtils srvUtils = new ServerUtils();

	WebDriverWait wait = new WebDriverWait(wdriver, 30);
	WebDriverWait waitWelcome = new WebDriverWait(wdriver, 3);

	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//h4[contains(text(),'Welcome')]")
	WebElement welcomeMessage;

	@FindBy(xpath = "//h4[contains(text(),'Welcome')]/parent::div/following-sibling::div[2]/button")
	WebElement welcomeOKButton;

	@FindBy(xpath = "//h1[contains(text(),'Status Enquiry')]")
	WebElement pageTitle;

	@FindBy(xpath = "//select2[contains(@id,'status-enquiry-product')]/parent::div")
	WebElement product;

	@FindBy(xpath = "//li[contains(text(),'Banking')]")
	WebElement bankingProduct;

	@FindBy(id = "status-enquiry-txn-id")
	WebElement txnId;

	@FindBy(xpath = "//div[contains(@class,'clearfix')]/div[contains(@class,'pull-right')]/button")
	WebElement statusEnquirySubmitButton;

	@FindBy(id = "searchByMobileNumber")
	WebElement pageCustMobNum;

	@FindBy(id = "bankingTxnID")
	WebElement pageTxnId;

	@FindBy(xpath = "//*[@id='banking-status-enquiry']//button")
	WebElement pageSubmitButton;

	@FindBy(xpath = "//label[contains(text(),'Txn. ID')]")
	WebElement aepsStatusEnquiryPage;

	@FindBy(xpath = "//select2[contains(@id,'reportsDropDown')]")
	WebElement reportPage;

	@FindBy(xpath = "//table//tr[contains(@class,'table-row')]")
	WebElement firstTxnInList;

	@FindBy(xpath = "//h4[contains(text(),'Success!')]")
	WebElement successSeTxn;

	@FindBy(xpath = "//h4[contains(text(),'Failed!')]")
	WebElement failSeTxn;

	@FindBy(xpath = "//h4[contains(text(),'Refunded!')]")
	WebElement refundedSeTxn;

	@FindBy(xpath = "//div/button[contains(text(),'Done')]")
	WebElement seDoneBtn;

	@FindBy(xpath = "//div/button[contains(text(),'Retry')]")
	WebElement seRetryBtn;

	@FindBy(xpath = "//div/button[contains(text(),'Exit')]")
	WebElement seExitBtn;

	@FindBy(xpath = "//h4[contains(text(),'Failed!')]/parent::div/following-sibling::div//span")
	WebElement failSeTxnMsg;

	@FindBy(xpath = "//div/span/button[contains(text(),'Resend OTP')]")
	WebElement seResendOTPBtn;

	@FindBy(xpath = "//button[contains(text(),'Initiate Refund')]")
	WebElement initiateRefundBtn;

	@FindBy(xpath = "//h4[contains(text(),'Initiate Refund')]")
	WebElement initiateRefundScreen;

	@FindBy(id = "money-transfer-mobile-number")
	WebElement mobNum;

	@FindBy(id = "aeps-deposit-aadhar-number")
	WebElement aadhaarNum;

	@FindBy(xpath = "//app-deposit//input[@id='money-transfer-mobile-number']/parent::div//li")
	WebElement aadhaarNumError;

	@FindBy(xpath = "//div[contains(@class,'scan_finger_container')]")
	WebElement fingerprintScan;

	@FindBy(xpath = "//span[contains(text(),'Fingerprint scanned successfully!')]")
	WebElement fingerprintGreen;

	@FindBy(xpath = "//span[contains(text(),'Click to scan fingerprint')]")
	WebElement fingerprintUnscanned;

	@FindBy(xpath = "//button[contains(text(),'Proceed')]")
	WebElement proceedBtn;

	@FindBy(xpath = "//button[contains(text(),'Exit')]")
	WebElement exitBtn;

	@FindBy(xpath = "//*[@id='money-transfer-otp-number']/parent::div//li")
	WebElement custOTPError;

	@FindBy(xpath = "//span[contains(text(),'Reports')]")
	WebElement reports;

	@FindBy(xpath = "//label[contains(text(),'Select Report to View')]")
	WebElement reportsPage;

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//*[@id='reportsDropDown']//span[contains(@class,'select2-selection')]")
	WebElement reportsDropdown;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent;

	@FindBy(xpath = "//h5[contains(text(),'Enter 4 digit PIN')]")
	WebElement MPINScreen;

	@FindBy(id = "money-transfer-mpin-number")
	WebElement enterMPIN;

	@FindBy(xpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/following-sibling::div/following-sibling::div/button[contains(text(),'Submit')]")
	WebElement mpinSubmitButton;

	@FindBy(xpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/following-sibling::div/following-sibling::div/button[contains(text(),'Cancel')]")
	WebElement mpinCancelButton;

	@FindBy(xpath = "//h4[contains(text(),'Processing...')]")
	WebElement processingScreen;

	@FindBy(xpath = "//h4[contains(text(),'!')]")
	WebElement refundTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'statusenquiry-aeps-refund-modal')]/div/div/div/following-sibling::div/div[1]")
	WebElement aepsTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'statusenquiry-aeps-refund-modal')]/div/div/div/following-sibling::div/div/strong")
	WebElement aepsDeemedTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'statusenquiry-aeps-refund-modal')]//span[contains(text(),'Amount:')]/parent::div/following-sibling::div")
	WebElement aepsTxnScreenTxnAmount;

	@FindBy(xpath = "//div[contains(@class,'statusenquiry-aeps-refund-modal')]//button[contains(text(),'Done')]")
	WebElement aepsTxnScreenDoneButton;

	@FindBy(xpath = "//div[contains(@class,'statusenquiry-aeps-refund-modal')]//span[contains(text(),'Refund Amount:')]/parent::div/following-sibling::div")
	WebElement aepsTxnScreenRefundAmount;

	@FindBy(xpath = "//div[contains(@class,'statusenquiry-aeps-refund-modal')]/div/div/div/following-sibling::div/div/span")
	WebElement aepsTxnScreenFailureReason;

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

	@FindBy(xpath = "//tbody/tr[1]/td[6]")
	WebElement noTxnAvailable;

	String txnID = "", batchConfigSection = "rblaepsstatusenquiry";

	public YBLAEPSStatusEnquiryPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on navigation key
	public void yBLAEPSStatusEnquiry(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {
		try {
			HashMap<String, String> batchFileConfig = readSectionFromIni(batchConfigSection);
			batchFileConfig = readSectionFromIni(batchConfigSection);
			if (!usrData.get("KEY").isEmpty()) {
				srvUtils.uploadFile(batchFileConfig, usrData.get("KEY"));
			}
			
			if (usrData.get("STATUS").equalsIgnoreCase("REFUNDED")
					|| usrData.get("ASSERTION").equalsIgnoreCase("Refund Success FCM")) {
				txnID = txnDetailsFromIni("GetTxnRefNo", "");
			} else {
				txnID = dbUtils.aepsRefNum();
			}

			// store wallet balance as double
			double initialRetailerWalletBalance = getInitialBalance("retailer");

			// call status enquiry report
			if (usrData.get("TXNDETAILS").equalsIgnoreCase("11112222") || usrData.get("ASSERTION").contains("FCM")) {
				welcomePopup();
			}

			if (usrData.get("ASSERTION").contains("FCM")) {
				assertionOnStatusEnquiryFCM(usrData);
			} else {
				if (!usrData.get("STATUS").equalsIgnoreCase("REFUNDED")) {
					statusEnquirySection(usrData);
					Thread.sleep(1000);
				}
				wait.until(ExpectedConditions.elementToBeClickable(aepsStatusEnquiryPage));
				menu.click();
				menu.click();
				Log.info("Status enquiry of " + usrData.get("STATUS") + " Transaction");
				Thread.sleep(1000);

				wait.until(ExpectedConditions.visibilityOf(firstTxnInList));
				if (usrData.get("ASSERTION").equalsIgnoreCase("11112222")) {
					pageTxnId.click();
					pageTxnId.clear();
					pageTxnId.sendKeys("11112222");
					Log.info("Txn ID entered");
					pageSubmitButton.click();
					Log.info("Submit button clicked");
					waitForSpinner();
					wait.until(ExpectedConditions.visibilityOf(firstTxnInList));
					Assert.assertEquals(noTxnAvailable.getText(),
							"Transaction with RRN number 11112222 does not exist");
					Log.info(noTxnAvailable.getText());
				} else if (usrData.get("TXNDETAILS").equalsIgnoreCase("11112222")) {
					Assert.assertEquals(noTxnAvailable.getText(),
							"Transaction with RRN number 11112222 does not exist");
					Log.info(noTxnAvailable.getText());
				} else {
					reportsData(usrData);
				}
				if (usrData.get("STATUS").equalsIgnoreCase("Refund")) {
					initiateRefundBtn.click();
					wait.until(ExpectedConditions.visibilityOf(initiateRefundScreen));
					if (usrData.get("AADHAAR").equalsIgnoreCase("Valid")) {
						aadhaarNum.click();
						aadhaarNum.sendKeys(getAadhaarFromIni("GetAadhaarNum"));
						Log.info("Aadhaar Number entered");
						Assert.assertEquals("Click to scan fingerprint", fingerprintUnscanned.getText());
						fingerprintScan.click();
						Log.info("Scan fingerprint button clicked");
						wait.until(ExpectedConditions.elementToBeClickable(fingerprintGreen));
						Assert.assertEquals("Fingerprint scanned successfully!", fingerprintGreen.getText());
						wait.until(ExpectedConditions.visibilityOf(proceedBtn));
						proceedBtn.click();
						wait.until(ExpectedConditions.visibilityOf(MPINScreen));
						Log.info("MPIN screen displayed");
						wait.until(ExpectedConditions.elementToBeClickable(enterMPIN));
						if (usrData.get("MPIN").equalsIgnoreCase("Cancel")) {
							mpinCancelButton.click();
						} else {
							enterMPIN.click();
							if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
								enterMPIN.sendKeys(getAuthfromIni("MPIN"));
							} else {
								enterMPIN.sendKeys("0000");
							}
							Log.info(usrData.get("MPIN") + " entered");
							mpinSubmitButton.click();
							Log.info("MPIN button clicked");
							wait.until(ExpectedConditions.visibilityOf(refundTxnScreen));
							Log.info("Txn screen displayed");
							if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
								if (!usrData.get("KEY").equalsIgnoreCase("fail")) {
									assertionOnRefundSuccessScreen(usrData);
									Log.info("Refund successful");
									assertionOnRefundSMS(usrData);
									wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenDoneButton));
									aepsTxnScreenDoneButton.click();
									Log.info("Done button clicked");
									refreshBalance();
									verifyUpdatedBalanceAfterRefundSuccessTxn(usrData, initialRetailerWalletBalance);
								} else {
									assertionOnRefundFailedScreen(usrData);
									Log.info("Refund failed");
									seExitBtn.click();
									Log.info("Exit button clicked");
								}
							} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
								assertionOnRefundFailedScreen(usrData);
								seDoneBtn.click();
								Log.info("Done button clicked");
							} else if (usrData.get("MPIN").equalsIgnoreCase("Retry")) {
								assertionOnRefundFailedScreen(usrData);
								seRetryBtn.click();
								wait.until(ExpectedConditions.visibilityOf(initiateRefundScreen));
								aadhaarNum.click();
								aadhaarNum.sendKeys(getAadhaarFromIni("GetAadhaarNum"));
								Log.info("Aadhaar Number entered");
								Assert.assertEquals("Click to scan fingerprint", fingerprintUnscanned.getText());
								fingerprintScan.click();
								Log.info("Scan fingerprint button clicked");
								wait.until(ExpectedConditions.elementToBeClickable(fingerprintGreen));
								Assert.assertEquals("Fingerprint scanned successfully!", fingerprintGreen.getText());
								wait.until(ExpectedConditions.visibilityOf(proceedBtn));
								proceedBtn.click();
								wait.until(ExpectedConditions.visibilityOf(MPINScreen));
								Log.info("MPIN screen displayed");
								wait.until(ExpectedConditions.elementToBeClickable(enterMPIN));
								enterMPIN.click();
								enterMPIN.sendKeys(getAuthfromIni("MPIN"));
								Log.info(usrData.get("MPIN") + " entered");
								mpinSubmitButton.click();
								Log.info("MPIN button clicked");
								wait.until(ExpectedConditions.visibilityOf(processingScreen));
								Log.info("Processing screen displayed");
								wait.until(ExpectedConditions.visibilityOf(refundTxnScreen));
								Log.info("Txn screen displayed");
								assertionOnRefundSuccessScreen(usrData);
								Log.info("Refund successful");
								wait.until(ExpectedConditions.elementToBeClickable(aepsTxnScreenDoneButton));
								aepsTxnScreenDoneButton.click();
								Log.info("Done button clicked");
								refreshBalance();
								verifyUpdatedBalanceAfterRefundSuccessTxn(usrData, initialRetailerWalletBalance);
							}
						}
					} else if (usrData.get("AADHAAR").equalsIgnoreCase("Exit")) {
						exitBtn.click();
						Log.info("Exit button clicked");
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

	public void waitForSpinner() {
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'spinner')]/parent::div")));
		Log.info("Please wait...");
	}

	public void statusEnquirySection(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {
		wait.until(ExpectedConditions.visibilityOf(product));
		product.click();
		Log.info("Status Enquiry drop down clicked");

		wait.until(ExpectedConditions.visibilityOf(bankingProduct));
		bankingProduct.click();
		Log.info("Banking selected");
		wait.until(ExpectedConditions.visibilityOf(txnId));

		if (usrData.get("STATUS").equalsIgnoreCase("Refund")
				|| usrData.get("STATUS").equalsIgnoreCase("Ready For Refund")) {
			dbUtils.updateAEPSTxn(txnDetailsFromIni("GetTxnRefNo", ""));
		}

		if (usrData.get("TXNDETAILS").equalsIgnoreCase("TxnID")) {
			txnId.click();
			txnId.clear();
			txnId.sendKeys(txnID);
			Log.info("Txn ID entered");
		} else {
			txnId.click();
			txnId.clear();
			txnId.sendKeys(usrData.get("TXNDETAILS"));
		}

		wait.until(ExpectedConditions.visibilityOf(statusEnquirySubmitButton));
		statusEnquirySubmitButton.click();
		Log.info("Submit button clicked");
		waitForSpinner();
	}

	// SMS assertion
	public void assertionOnRefundSMS(Map<String, String> usrData) throws ClassNotFoundException {
		String successRefundSMS = "Dear Customer, Refund for aadhaar  XXXX XXXX "
				+ getAadhaarFromIni("GetAadhaarNum").substring(8, 12) + " and RRN " + txnID
				+ " is successful. Please collect the cash from Aryan";

		if (usrData.get("ASSERTION").equalsIgnoreCase("Success SMS")) {
			Assert.assertEquals(successRefundSMS, dbUtils.sms());
			Log.info(successRefundSMS);
		}
	}

	// FCM assertion
	public void assertionOnStatusEnquiryFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String balance = df.format(getInitialBalance("retailer"));

		String successSEFCMHeading = "Transaction Status enquiry:SUCCESS";
		String failSEFCMHeading = "Transaction Status enquiry:FAIL";
		String successRefundFCMHeading = "Cash Deposit Refund: SUCCESS";
		String failRefundFCMHeading = "Cash Deposit Refund: FAIL";

		String successSEFCMContent = "Request for Transaction status enquiry with RRN " + txnID
				+ " is completed and the status of the transaction is SUCCESS Response code: (00) SUCCESS Reference No: "
				+ txnID + " Balance after txn: " + balance;
		String failSEFCMContent = "Status enquiry - txn has failed: Failed to perform transaction(M3)";
		String deemedSuccessSEFCMContent = "Request for Transaction status enquiry with RRN " + txnID
				+ " is completed and the status of the transaction is DEEMED SUCCESS(Error Code 91)."
				+ " Reference No: " + txnID + " Agent Wallet Balance after txn: " + balance;
		String laterFailedSEFCMContent = "AEPS deposit of Rs. " + txnDetailsFromIni("GetTxfAmount", "")
				+ ".00 for mobile " + getAEPSMobNum("GetAEPSMobNum") + " & Txn id "
				+ txnDetailsFromIni("GetTxnRefNo", "") + " on " + dbUtils.aepsStatusEnquiryDate(txnID)
				+ " was deemed success before and has failed now. Please refund cash via Refund option.";
		String successRefundFCMContent = "Refund for customer with mobile " + getAEPSMobNum("GetAEPSMobNum")
				+ " and RRN " + txnID + " is successful. Please return the cash to customer";
		String failRefundFCMContent = "Refund for customer with Aadhaar XXXX XXXX "
				+ getAadhaarFromIni("GetAadhaarNum").substring(8, 12) + " and RRN " + txnID
				+ " has failed : Failed to perform transaction(M3)";

		switch (usrData.get("ASSERTION")) {
		case "Success FCM":
			fcmMethod(successSEFCMHeading, successSEFCMContent);
			break;
		case "Failed FCM":
			fcmMethod(failSEFCMHeading, failSEFCMContent);
			break;
		case "Deemed Success FCM":
			fcmMethod(successSEFCMHeading, deemedSuccessSEFCMContent);
			break;
		case "Later Failed FCM":
			fcmMethod(failSEFCMHeading, laterFailedSEFCMContent);
			break;
		case "Refund Success FCM":
			fcmMethod(successRefundFCMHeading, successRefundFCMContent);
			break;
		case "Refund Fail FCM":
			fcmMethod(failRefundFCMHeading, failRefundFCMContent);
			break;
		}
	}

	public void fcmMethod(String heading, String content) {
		Assert.assertEquals(fcmHeading.getText(), heading);
		Assert.assertEquals(fcmContent.getText(), content);
		Log.info(fcmHeading.getText());
		Log.info(fcmContent.getText());
	}

	public void reportsData(Map<String, String> usrData) throws ClassNotFoundException {
		String[][] dataFromUI = new String[1][7];
		String[][] dataFromUIwithoutMessage = new String[1][6];
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 7; j++) {
				String dataXpath = "//tbody/tr[" + (i + 1) + "]/td[" + (j + 1) + "]";
				WebElement data = wdriver.findElement(By.xpath(dataXpath));
				dataFromUI[i][j] = data.getText();
				if (j == 5) {
					dataFromUIwithoutMessage[i][j] = wdriver.findElement(By.xpath("//tbody/tr[1]/td[7]")).getText();
				} else if (j != 6) {
					dataFromUIwithoutMessage[i][j] = data.getText();
				}
			}
		}

		if (usrData.get("STATUS").equalsIgnoreCase("Success")) {
			Assert.assertEquals(dataFromUI[0][5],
					"transaction with RRN: " + txnDetailsFromIni("GetTxnRefNo", "") + " is successful");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Failed")) {
			Assert.assertEquals(dataFromUI[0][5], "Status enquiry - txn has failed: Failed to perform transaction(M3)");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Deemed Success")) {
			Assert.assertEquals(dataFromUI[0][5], "transaction with RRN " + txnDetailsFromIni("GetTxnRefNo", "")
					+ " is considered as deemed success(91) and amount will be credited to customer shortly.");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Ready For Refund")) {
			Assert.assertEquals(dataFromUI[0][5],
					"AEPS deposit of Rs. " + txnDetailsFromIni("GetTxfAmount", "") + ".00 for mobile "
							+ getAEPSMobNum("GetAEPSMobNum") + " & Txn id " + txnDetailsFromIni("GetTxnRefNo", "")
							+ " on " + dbUtils.aepsStatusEnquiryDate(txnDetailsFromIni("GetTxnRefNo", ""))
							+ " was deemed success before and has failed now. Please refund cash via Refund option.");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Refunded")) {
			Assert.assertEquals(dataFromUI[0][5],
					"transaction with rrn " + txnDetailsFromIni("GetTxnRefNo", "") + " has been refunded");
		}

		List<String> listFromUIWithMessage = new ArrayList<String>();
		for (String[] array : dataFromUI) {
			listFromUIWithMessage.addAll(Arrays.asList(array));
		}

		List<String> listFromUI = new ArrayList<String>();
		for (String[] array : dataFromUIwithoutMessage) {
			listFromUI.addAll(Arrays.asList(array));
		}
		/*
		 * for (String t : listFromUI) { System.out.print(t + " | "); }
		 * System.out.println();
		 */

		List<String[]> list = new ArrayList<String[]>();
		List<String[]> aepsStatusEnquiry = dbUtils.aepsStatusEnquiry(txnID);
		list = aepsStatusEnquiry;

		List<String> listFromDB = new ArrayList<String>();
		for (String[] data : list) {
			for (String s : data) {
//				System.out.print(s + " | ");
				listFromDB.add(s);
			}
		}
//		System.out.println();

		HashMap<String, String> map = new HashMap<String, String>();
		Iterator<String> iUI = listFromUI.iterator();
		Iterator<String> iDB = listFromDB.iterator();
		while (iUI.hasNext() && iDB.hasNext()) {
			map.put(iUI.next(), iDB.next());
		}
		for (Map.Entry<String, String> entry : map.entrySet()) {
			Assert.assertEquals(entry.getKey(), entry.getValue());
		}
		/*
		 * for (int i = 0; i < listFromUI.size(); i++) {
		 * System.out.print(listFromUI.get(i) + " | "); } System.out.println();
		 */
		for (String u : listFromUIWithMessage) {
			System.out.print(u + " | ");
		}
		System.out.println();
	}

	// Verify details on success screen
	public void assertionOnRefundSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(aepsTxnScreenMessage.getText(), "Cash refunded to customer successfully");
		Log.info(aepsTxnScreenMessage.getText());
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		Assert.assertEquals(replaceSymbols(aepsTxnScreenTxnAmount.getText()), df.format(amount));
		Log.info("Refund Amount: " + replaceSymbols(aepsTxnScreenTxnAmount.getText()));
		Assert.assertEquals(replaceSymbols(aepsTxnScreenTxnAmount.getText()), df.format(amount));
		Log.info("Cash to be Refunded: " + replaceSymbols(aepsTxnScreenTxnAmount.getText()));
	}

	// Verify details on failure screen
	public void assertionOnRefundFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("MPIN").equalsIgnoreCase("Invalid") || usrData.get("MPIN").equalsIgnoreCase("Retry")) {
			Assert.assertEquals(aepsTxnScreenFailureReason.getText(), "Authentication Failed Invalid MPIN");
			Log.info(aepsTxnScreenFailureReason.getText());
		} else {
			Assert.assertEquals(aepsTxnScreenFailureReason.getText(), "Failed to perform transaction(M3)");
			Log.info(aepsTxnScreenFailureReason.getText());
			double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
			Assert.assertEquals(replaceSymbols(aepsTxnScreenRefundAmount.getText()), df.format(amount));
			Log.info("Refund Amount: " + replaceSymbols(aepsTxnScreenRefundAmount.getText()));
		}
	}

	// Assertion after success screen is displayed
	public void verifyUpdatedBalanceAfterRefundSuccessTxn(Map<String, String> usrData, double initialWalletBalance)
			throws ClassNotFoundException {
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double comm = Double.parseDouble(txnDetailsFromIni("GetComm", ""));
		double tds = Double.parseDouble(txnDetailsFromIni("GetTds", ""));
		double newWalletBal = initialWalletBalance + amount + charges - comm + tds;
		String newWalletBalance = df.format(newWalletBal);
		Assert.assertEquals(replaceSymbols(retailerWalletBalance.getText()), newWalletBalance);
		Log.info("Updated Retailer Wallet Balance: " + replaceSymbols(retailerWalletBalance.getText()));
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

	// To refresh the wallet balance
	public void refreshBalance() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(refreshButton));
		clickInvisibleElement(refreshButton);
		wait.until(ExpectedConditions.elementToBeClickable(syncButton));
		wait.until(ExpectedConditions.elementToBeClickable(refreshButton));
		Log.info("Balance refreshed successfully");
	}

	// Remove rupee symbol and comma from the string
	public String replaceSymbols(String element) {
		String editedElement = element.replaceAll("₹", "").replaceAll(",", "").trim();
		return editedElement;
	}

	// Get Partner name
	public String partner() {
		return "YBL";
	}

	// Get mobile number from Ini file
	public String mobileNumFromIni() {
		return getLoginMobileFromIni(partner().toUpperCase() + "RetailerMobNum");
	}

	// Click OK on Welcome pop-up (whenever displayed)
	public void welcomePopup() {
		try {
			waitWelcome.until(ExpectedConditions.visibilityOf(welcomeMessage));
			Log.info("Welcome pop-up displayed");
			waitWelcome.until(ExpectedConditions.elementToBeClickable(welcomeOKButton));
			clickElement(welcomeOKButton);
			Log.info("OK. Got it! button clicked");
			waitWelcome.until(
					ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h4[contains(text(),'Welcome')]")));
			Log.info("Welcome pop-up disappeared");
		} catch (Exception e) {
			Log.info("No welcome pop-up displayed");
		}
	}

	// click on WebElement forcefully
	public void clickElement(WebElement element) {
		try {
			element.click();
		} catch (Exception e) {
			clickInvisibleElement(element);
		}
	}

	// Get otp from Ini file
	public String otpFromIni() {
		return "123456";
	}

}