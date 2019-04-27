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

public class AxisStatusEnquiryPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	ServerUtils srvUtils = new ServerUtils();

	WebDriverWait wait = new WebDriverWait(wdriver, 30);
	WebDriverWait waitWelcome = new WebDriverWait(wdriver, 3);

	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//h4[contains(text(),'Welcome')]")
	WebElement welcomeMessage;

	@FindBy(xpath = "//h4[contains(text(),'Welcome')]/parent::div/following-sibling::div[2]/button")
	WebElement welcomeOKButton;

	@FindBy(xpath = "//*[@id='sidebar']/div/div[1]/div[3]/div[1]/p[2]/span")
	WebElement walletBalance;

	@FindBy(xpath = "//h1[contains(text(),'Status Enquiry')]")
	WebElement pageTitle;

	@FindBy(xpath = "//select2[contains(@id,'status-enquiry-product')]/parent::div")
	WebElement product;

	@FindBy(xpath = "//li[contains(text(),'Money Transfer')]")
	WebElement moneyTransferProduct;

	@FindBy(id = "status-enquiry-txn-id")
	WebElement txnId;

	@FindBy(id = "status-enquiry-customer-mobile-number")
	WebElement seCustMobNum;

	@FindBy(id = "status-enquiry-txn-id")
	WebElement enterSetxnId;

	@FindBy(xpath = "//div[contains(@class,'clearfix')]/div[contains(@class,'pull-right')]/button")
	WebElement statusEnquirySubmitButton;

	@FindBy(id = "searchByMobileNumber")
	WebElement pageCustMobNum;

	@FindBy(id = "searchByTxnID")
	WebElement pageTxnId;

	@FindBy(xpath = "//*[@id='reports-list-status-enquiry']//button")
	WebElement pageSubmitButton;

	@FindBy(xpath = "//select2[contains(@id,'reportsDropDown')]")
	WebElement reportPage;

	@FindBy(xpath = "//table//tr[@class='table-row'][1]")
	WebElement firstTxnInList;

	@FindBy(xpath = "//h4[contains(text(),'!')]")
	WebElement seTxnTitle;

	@FindBy(xpath = "//h4[contains(text(),'!')]/parent::div/following-sibling::div//span")
	WebElement seTxnSuccessMessage;

	@FindBy(xpath = "//h4[contains(text(),'!')]/parent::div/following-sibling::div//span/following-sibling::span")
	WebElement seTxnTimeoutMessage;

	@FindBy(xpath = "//h4[contains(text(),'!')]/parent::div/following-sibling::div/div")
	WebElement seTxnRefundedMessage;

	@FindBy(xpath = "//span[contains(text(),'Transferred Amount:')]/parent::div/following-sibling::div")
	WebElement seTxnScreenTxnAmount;

	@FindBy(xpath = "//span[contains(text(),'Charges:')]/parent::div/following-sibling::div")
	WebElement seTxnScreenCharges;

	@FindBy(xpath = "//span[contains(text(),'Refunded Amount:')]/parent::div/following-sibling::div")
	WebElement seTxnScreenRefundedAmount;

	@FindBy(xpath = "//span[contains(text(),'Failed Amount:')]/parent::div/following-sibling::div")
	WebElement seTxnScreenFailedAmount;

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

	@FindBy(xpath = "//div/button[contains(text(),'Initiate Refund')]")
	WebElement failSeInitiateRefundBtn;

	@FindBy(xpath = "//div/button[contains(text(),'Close')]")
	WebElement closeRefundBtn;

	@FindBy(xpath = "//h4[contains(text(),'Confirm Refund')]")
	WebElement confirmRefund;

	@FindBy(xpath = "//div/button[contains(text(),'Ok')]")
	WebElement confirmRefundOkBtn;

	@FindBy(xpath = "//h5[contains(text(),'Enter Customer OTP')]")
	WebElement custOTPScreen;

	@FindBy(id = "money-transfer-otp-number")
	WebElement custOTP;

	@FindBy(xpath = "//*[@id='money-transfer-otp-number']/parent::div//li")
	WebElement custOTPError;

	@FindBy(xpath = "//div/button[contains(text(),'Confirm')]")
	WebElement otpConfirmBtn;

	@FindBy(xpath = "//div/button[contains(text(),'Cancel')]")
	WebElement otpCancelBtn;

	@FindBy(xpath = "//div//button[contains(text(),'Resend')]")
	WebElement otpResendBtn;

	@FindBy(xpath = "//div[contains(@class,'toast-message')]")
	WebElement toasterMsg;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

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

	String txnID = txnDetailsFromIni("GetTxnRefNo", ""), batchConfigSection = "rblaepsstatusenquiry";;

	public AxisStatusEnquiryPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on navigation key
	public void axisStatusEnquiry(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {
		try {
			HashMap<String, String> batchFileConfig = readSectionFromIni(batchConfigSection);
			batchFileConfig = readSectionFromIni(batchConfigSection);
			if (!usrData.get("KEY").isEmpty()) {
				srvUtils.uploadFile(batchFileConfig, usrData.get("KEY"));
			}
			
			// call status enquiry report
			if (usrData.get("TXNDETAILS").equalsIgnoreCase("11112222") || usrData.get("ASSERTION").contains("FCM")) {
				welcomePopup();
			}

			if (usrData.get("ASSERTION").contains("FCM")) {
				menu.click();
				menu.click();
				assertionOnRefundFCM(usrData);
			} else {
				if (usrData.get("STATUS").equalsIgnoreCase("Failed")) {
					updateTxnStatus();
				}
				if (usrData.get("TYPE").equalsIgnoreCase("Section")) {
					statusEnquirySection(usrData);
					menu.click();
					menu.click();
					Thread.sleep(2000);
				} else if (usrData.get("TYPE").equalsIgnoreCase("Page")) {
					menu.click();
					menu.click();
					menu.click();
					wait.until(ExpectedConditions.elementToBeClickable(scrollBar));
					scrollElementDown(scrollBar, reports);
//					wait.until(ExpectedConditions.elementToBeClickable(reports));
//					clickInvisibleElement(reports);
					Log.info("Reports option clicked");
					wait.until(ExpectedConditions.elementToBeClickable(reportsPage));
					wait.until(ExpectedConditions.elementToBeClickable(reportsDropdown));
					menu.click();

					if (usrData.get("TXNDETAILS").equalsIgnoreCase("MobNum")) {
						pageCustMobNum.click();
						pageCustMobNum.clear();
						pageCustMobNum.sendKeys(getCustomerDetailsFromIni("ExistingNum"));
						Log.info("Customer mobile number entered");
					} else if (usrData.get("TXNDETAILS").equalsIgnoreCase("TxnID")) {
						pageTxnId.click();
						pageTxnId.clear();
						pageTxnId.sendKeys(txnID);
						Log.info("Txn ID entered");
					} else {
						pageTxnId.click();
						pageTxnId.clear();
						pageTxnId.sendKeys(usrData.get("TXNDETAILS"));
					}

					wait.until(ExpectedConditions.visibilityOf(pageSubmitButton));
					pageSubmitButton.click();
					Log.info("Submit button clicked");
					waitForSpinner();
				}
				if (usrData.get("TXNDETAILS").equalsIgnoreCase("11112222")) {
					wait.until(ExpectedConditions.visibilityOf(toasterMsg));
					Assert.assertEquals("No Transaction available", toasterMsg.getText());
				} else {
					reportsData(usrData);
					selectTxn();
					Log.info("Status enquiry of " + usrData.get("STATUS") + " Transaction");
					Thread.sleep(1000);
					wait.until(ExpectedConditions.visibilityOf(seTxnTitle));
					assertionOnTxnScreen(usrData);
					if (usrData.get("STATUS").equalsIgnoreCase("Success")) {
						seDoneBtn.click();
					} else if (usrData.get("STATUS").equalsIgnoreCase("Auto-Refunded")
							|| usrData.get("STATUS").equalsIgnoreCase("Late-Refunded")) {
						seDoneBtn.click();
					} else if (usrData.get("STATUS").equalsIgnoreCase("Timeout")) {
						seDoneBtn.click();
					} else if (usrData.get("STATUS").equalsIgnoreCase("To_Be_Refunded")) {
						closeRefundBtn.click();
					} else if (usrData.get("STATUS").equalsIgnoreCase("Failed")) {
						seExitBtn.click();
					} else if (usrData.get("STATUS").equalsIgnoreCase("Refund")) {
						wait.until(ExpectedConditions.elementToBeClickable(failSeInitiateRefundBtn));
						Thread.sleep(1000);
						failSeInitiateRefundBtn.click();
						Thread.sleep(1000);
						wait.until(ExpectedConditions.visibilityOf(confirmRefund));
						confirmRefundOkBtn.click();
						Thread.sleep(1000);
						wait.until(ExpectedConditions.visibilityOf(custOTPScreen));
						custOTP.click();
						custOTP.clear();
						if (usrData.get("OTP").equalsIgnoreCase("Valid")) {
							custOTP.sendKeys(getAuthfromIni(otpFromIni()));
							Log.info("Refund OTP entered");
							wait.until(ExpectedConditions.visibilityOf(otpConfirmBtn));
							otpConfirmBtn.click();
							waitForSpinner();
							wait.until(ExpectedConditions.visibilityOf(seTxnTitle));
							seDoneBtn.click();
							Log.info("Refund successful");
						} else if (usrData.get("OTP").equalsIgnoreCase("Invalid")
								|| usrData.get("OTP").equalsIgnoreCase("Retry")) {
							custOTP.sendKeys("111111");
							Log.info("Refund OTP entered");
							wait.until(ExpectedConditions.visibilityOf(otpConfirmBtn));
							otpConfirmBtn.click();
							waitForSpinner();
							wait.until(ExpectedConditions.visibilityOf(seTxnTitle));
							Assert.assertEquals("OTP does not match", failSeTxnMsg.getText());
							Log.info(failSeTxnMsg.getText());
							if (usrData.get("OTP").equalsIgnoreCase("Retry")) {
								seRetryBtn.click();
								waitForSpinner();
								wait.until(ExpectedConditions.visibilityOf(custOTPScreen));
								custOTP.click();
								custOTP.clear();
								custOTP.sendKeys(getAuthfromIni(otpFromIni()));
								Log.info("Refund OTP entered");
								wait.until(ExpectedConditions.visibilityOf(otpConfirmBtn));
								otpConfirmBtn.click();
								waitForSpinner();
								wait.until(ExpectedConditions.visibilityOf(seTxnTitle));
								assertionOnTxnScreen(usrData);
								seDoneBtn.click();
								Log.info("Refund successful");
							} else {
								seExitBtn.click();
							}
						} else if (usrData.get("OTP").equalsIgnoreCase("Cancel")) {
							otpCancelBtn.click();
						} else if (usrData.get("OTP").equalsIgnoreCase("Resend")) {
							wait.until(ExpectedConditions.elementToBeClickable(otpResendBtn));
							otpResendBtn.click();
							wait.until(ExpectedConditions.visibilityOf(custOTPScreen));
							custOTP.click();
							custOTP.clear();
							custOTP.sendKeys(getAuthfromIni(otpFromIni()));
							Log.info("Refund OTP entered");
							wait.until(ExpectedConditions.visibilityOf(otpConfirmBtn));
							otpConfirmBtn.click();
							waitForSpinner();
							wait.until(ExpectedConditions.visibilityOf(seTxnTitle));
							seDoneBtn.click();
							Log.info("Refund successful");
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

	public void waitForSpinner() {
		wait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div/ng-busy/div/ng-component/div/div/div[1]")));
		Log.info("Please wait...");
	}

	public void updateTxnStatus() throws ClassNotFoundException {
		dbUtils.updateAxisTransactionStatus(txnID);
		Log.info("Updated AXIS txn for refund");
	}

	public void selectTxn() throws ClassNotFoundException {
		wait.until(ExpectedConditions.visibilityOf(firstTxnInList));
		firstTxnInList.click();
		Log.info("Transaction selected");
		waitForSpinner();
	}

	public void statusEnquirySection(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {
		wait.until(ExpectedConditions.visibilityOf(product));
		product.click();
		Log.info("Status Enquiry drop down clicked");

		wait.until(ExpectedConditions.visibilityOf(moneyTransferProduct));
		moneyTransferProduct.click();
		Log.info("Money Transfer selected");
		wait.until(ExpectedConditions.visibilityOf(seCustMobNum));

		if (usrData.get("TXNDETAILS").equalsIgnoreCase("MobNum")) {
			seCustMobNum.click();
			seCustMobNum.clear();
			seCustMobNum.sendKeys(getCustomerDetailsFromIni("ExistingNum"));
			Log.info("Customer mobile number entered");
		} else if (usrData.get("TXNDETAILS").equalsIgnoreCase("TxnID")) {
			enterSetxnId.click();
			enterSetxnId.clear();
			enterSetxnId.sendKeys(txnID);
			Log.info("Txn ID entered");
		} else {
			enterSetxnId.click();
			enterSetxnId.clear();
			enterSetxnId.sendKeys(usrData.get("TXNDETAILS"));
		}

		wait.until(ExpectedConditions.visibilityOf(statusEnquirySubmitButton));
		statusEnquirySubmitButton.click();
		Log.info("Submit button clicked");
		waitForSpinner();
		wait.until(ExpectedConditions.visibilityOf(reportPage));
		Log.info("Report page displayed");
	}

	public void reportsData(Map<String, String> usrData) throws ClassNotFoundException {
		String[][] dataFromUI = new String[1][9];
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 9; j++) {
				String dataXpath = "//tbody/tr[" + (i + 1) + "]/td[" + (j + 1) + "]";
				WebElement data = wdriver.findElement(By.xpath(dataXpath));
				if (j == 3) {
					dataFromUI[i][j] = replaceSymbols(data.getText());
				} else {
					dataFromUI[i][j] = data.getText();
				}
			}
		}
		String statusXpath = "//tbody/tr[1]/td[10]";
		WebElement statusData = wdriver.findElement(By.xpath(statusXpath));
		if (usrData.get("STATUS").equalsIgnoreCase("Success")) {
			Assert.assertEquals(statusData.getText(),
					usrData.get("TXNDETAILS").equalsIgnoreCase("TxnID") ? "TXN_SUCCESS" : "SUCCESS");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Auto-Refunded")) {
			Assert.assertEquals(statusData.getText(), "TXN_REVERSED");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Timeout")) {
			Assert.assertEquals(statusData.getText(), "91");
		} else if (usrData.get("STATUS").equalsIgnoreCase("To_Be_Refunded")) {
			Assert.assertEquals(statusData.getText(), "TXN_REVERSAL_INITIATED");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Late-Refunded")) {
			Assert.assertEquals(statusData.getText(), "TXN_REVERSED");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Queued")) {
			Assert.assertEquals(statusData.getText(), "TXN_INQUEUE");
		} else if (usrData.get("STATUS").equalsIgnoreCase("Failed")) {
			Assert.assertEquals(statusData.getText(), "TXN_FAIL");
		}
		Log.info(statusData.getText());

		List<String> listFromUI = new ArrayList<String>();
		for (String[] array : dataFromUI) {
			listFromUI.addAll(Arrays.asList(array));
		}
		/*
		 * for (String t : listFromUI) { System.out.print(t + " | "); }
		 * System.out.println();
		 */

		List<String[]> list = new ArrayList<String[]>();
		List<String[]> aepsStatusEnquiry = dbUtils.mtStatusEnquiry(txnID);
		list = aepsStatusEnquiry;

		List<String> listFromDB = new ArrayList<String>();
		for (String[] data : list) {
			for (String s : data) {
				// System.out.print(s + " | ");
				listFromDB.add(s);
			}
		}
		// System.out.println();

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
		for (String u : listFromUI) {
			System.out.print(u + " | ");
		}
		System.out.println();
	}

	// Verify details on txn screen
	public void assertionOnTxnScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("STATUS").equalsIgnoreCase("Success") || usrData.get("STATUS").equalsIgnoreCase("Timeout")) {
			Assert.assertEquals(seTxnSuccessMessage.getText(), "Funds transferred successfully");
			Log.info(seTxnSuccessMessage.getText());
		} else if (usrData.get("STATUS").equalsIgnoreCase("Auto-Refunded")
				|| usrData.get("STATUS").equalsIgnoreCase("Late-Refunded")
				|| usrData.get("STATUS").equalsIgnoreCase("To_Be_Refunded")) {
			Assert.assertEquals(seTxnRefundedMessage.getText().contains("Funds transfer failed"), true);
		} else if (usrData.get("STATUS").equalsIgnoreCase("Queued")) {
			Assert.assertEquals(seTxnSuccessMessage.getText(),
					"Transactions have been accepted for processing, Beneficiary Bank switch down, please check the status after some time");
			dbUtils.updateBatchStatus("DisableRemittanceQueuing", "SUCCESS");
		}
		if (usrData.get("STATUS").equalsIgnoreCase("Timeout")) {
			Assert.assertEquals(seTxnTimeoutMessage.getText(), "(91)");
		}
		if (usrData.get("STATUS").equalsIgnoreCase("Success") || usrData.get("STATUS").equalsIgnoreCase("Timeout")) {
			Assert.assertEquals(replaceSymbols(seTxnScreenTxnAmount.getText()),
					txnDetailsFromIni("GetTxfAmount", "") + ".00");
			Log.info("Transferred Amount: " + replaceSymbols(seTxnScreenTxnAmount.getText()));
		} else if (usrData.get("STATUS").equalsIgnoreCase("Auto-Refunded")) {
			Assert.assertEquals(replaceSymbols(seTxnScreenRefundedAmount.getText()),
					txnDetailsFromIni("GetFailAmount", "") + ".00");
			Log.info("Refunded Amount: " + replaceSymbols(seTxnScreenRefundedAmount.getText()));
		} else if (usrData.get("STATUS").equalsIgnoreCase("To_Be_Refunded")
				|| usrData.get("STATUS").equalsIgnoreCase("Failed")) {
			Assert.assertEquals(replaceSymbols(seTxnScreenFailedAmount.getText()),
					txnDetailsFromIni("GetTxfAmount", "") + ".00");
			Log.info("Failed Amount: " + replaceSymbols(seTxnScreenFailedAmount.getText()));
		} else if (usrData.get("STATUS").equalsIgnoreCase("Late-Refunded")) {
			Assert.assertEquals(replaceSymbols(seTxnScreenRefundedAmount.getText()),
					txnDetailsFromIni("GetTxfAmount", "") + ".00");
			Log.info("Refunded Amount: " + replaceSymbols(seTxnScreenRefundedAmount.getText()));
		}
		Assert.assertEquals(replaceSymbols(seTxnScreenCharges.getText()), txnDetailsFromIni("GetCharges", ""));
		Log.info("Charges: " + replaceSymbols(seTxnScreenCharges.getText()));
	}

	// FCM assertion
	public void assertionOnRefundFCM(Map<String, String> usrData) throws ClassNotFoundException {
		double amount = Double.parseDouble(txnDetailsFromIni("GetTxfAmount", ""));
		double charges = Double.parseDouble(txnDetailsFromIni("GetCharges", ""));
		double totalAmount = amount + charges;

		String successFCMHeading = "Refund : SUCCESS";
		String failFCMHeading = "Refund : FAIL";

		String successFCMContent = "Customer Mobile: " + getCustomerDetailsFromIni("ExistingNum")
				+ " Money Transfer with cash payment: Rs." + df.format(amount) + " and Charges:Rs." + df.format(charges)
				+ ", Total: Rs." + df.format(totalAmount)
				+ " has been refunded to Agent Wallet.Transaction Reference No. " + dbUtils.paymentRefCode(partner());
		String failFCMContent = "Customer Mobile: " + getCustomerDetailsFromIni("ExistingNum") + " OTP does not match.";

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

	// Remove rupee symbol and comma from the string
	public String replaceSymbols(String element) {
		String editedElement = element.replaceAll("₹", "").replaceAll(",", "").trim();
		return editedElement;
	}

	// Get Partner name
	public String partner() {
		return "AXIS";
	}

	// Get mobile number from Ini file
	public String mobileNumFromIni() {
		return getLoginMobileFromIni(partner().toUpperCase() + "RetailerMobNum");
	}

	// Get otp from Ini file
	public String otpFromIni() {
		return partner().toUpperCase() + "OTP";
	}

}