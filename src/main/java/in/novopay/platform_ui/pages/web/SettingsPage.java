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

public class SettingsPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);
	DecimalFormat df = new DecimalFormat("#.00");

	@FindBy(xpath = "//*[@class='fa fa-bars fa-lg text-white']")
	WebElement menu;

	@FindBy(xpath = "//span[contains(text(),'Settings')]")
	WebElement settings;

	@FindBy(xpath = "//*[@class='slimScrollBar']")
	WebElement scrollBar;

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

	@FindBy(xpath = "//h1[contains(text(),'Settings')]")
	WebElement pageTitle;

	@FindBy(xpath = "//h1[contains(text(),'Money Transfer')]")
	WebElement pageTitle2;

	@FindBy(xpath = "//a[@href='/newportal/np-money-transfer']/span[contains(text(),'Money Transfer')]")
	WebElement moneyTransfer;

	@FindBy(xpath = "//span[contains(text(),'Capital First')]/parent::li")
	WebElement capitalFirstIcon;

	@FindBy(id = "capital-first-money-transfer-mobile-number")
	WebElement depositorMobNum;

	@FindBy(xpath = "//label[@for='settlement-mode-retailer-credit']")
	WebElement doNotSettleRadioButton;

	@FindBy(xpath = "//label[@for='settlement-mode-bank-account']")
	WebElement bankAccountRadioButton;

	@FindBy(xpath = "//button[contains(text(),'Save Changes')]")
	WebElement saveChangesButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	WebElement clearButton;

	@FindBy(xpath = "//*[contains(@class,'retailer-account-status')]")
	WebElement accountStatus;

	@FindBy(xpath = "//i[contains(@class,'icon-32-size')]")
	WebElement blockedIcon;

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

	@FindBy(xpath = "//additional-info-modal//h4")
	WebElement additionalInfoModal;

	@FindBy(xpath = "//div[contains(@class,'settlement-modal')]/div/div/div/h4[contains(text(),'!')]")
	WebElement settingsTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'settlement-modal')]/div/div/div")
	WebElement settingsTxnScreenType;

	@FindBy(xpath = "//div[contains(@class,'settlement-modal')]/div/div/div/following-sibling::div/div[1]")
	WebElement settingsTxnScreenMessage;

	@FindBy(xpath = "//div[contains(@class,'settlementTxn-modal')]/div/div/div/h4[contains(text(),'Pending')]")
	WebElement multiAccountPendingScreen;

	@FindBy(xpath = "//div[contains(@class,'settlementTxn-modal')]/div/div/div/h4[contains(text(),'Success')]")
	WebElement multiAccountSuccessScreen;

	@FindBy(xpath = "//div[contains(@class,'settlementTxn-modal')]/div/div/div")
	WebElement multiAccountScreenType;

	@FindBy(xpath = "//div[contains(@class,'settlementTxn-modal')]//div[contains(@class,'messages')]//strong")
	WebElement multiAccountTxnScreenMessage;

	@FindBy(xpath = "//button[contains(text(),'Exit')]")
	WebElement exitButton;

	@FindBy(xpath = "//button[contains(text(),'Retry')]")
	WebElement retryButton;

	@FindBy(xpath = "//button[contains(text(),'Done')]")
	WebElement doneButton;

	@FindBy(xpath = "//div[contains(@class,'toast-message')]")
	WebElement toasterMsg;

	@FindBy(xpath = "//settlement-mode//p")
	WebElement noBankAccountMsg;

	@FindBy(xpath = "//p[contains(@class,'debitInfo')]")
	WebElement debitInfo;

	@FindBy(xpath = "//button[contains(text(),'Add Bank Account')]")
	WebElement addButton;

	@FindBy(xpath = "//button[contains(text(),'OK')]")
	WebElement okButton;

	@FindBy(id = "settlement-mode-retailer-name")
	WebElement accHolderName;

	@FindBy(id = "settlement-mode-create-field-retailer-bank-ifsc-list")
	WebElement ifscCode;

	@FindBy(xpath = "//input[contains(@id,'settlement-mode-create-field-retailer-bank-ifsc-list')]/parent::div/following-sibling::span")
	WebElement ifscSearchIcon;

	@FindBy(id = "settlement-mode-retailer-account-number")
	WebElement accNumber;

	@FindBy(xpath = "//*[@name='settlement-mode-retailer-confirm-account-number']")
	WebElement confirmAccNumber;

	@FindBy(xpath = "//*[@for='file-upload']")
	WebElement uploadFile;

	@FindBy(id = "settlement-mode-retailer-file-name")
	WebElement inputFile;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	WebElement submitButton;

	@FindBy(xpath = "//h5[contains(text(),'IFSC Search')]")
	WebElement ifscSearchScreen;

	@FindBy(xpath = "//*[@id='ifsc-search-bank']//span[contains(text(),'Select...')]/parent::span")
	WebElement ifscSearchBankList;

	@FindBy(xpath = "//*[@id='ifsc-search-state']//span[contains(text(),'Select...')]/parent::span")
	WebElement ifscSearchStateList;

	@FindBy(id = "ifsc-search-district")
	WebElement ifscSearchDistrict;

	@FindBy(id = "ifsc-search-branch")
	WebElement ifscSearchBranch;

	@FindBy(xpath = "//button[contains(text(),'Search')]")
	WebElement ifscSearchButton;

	@FindBy(xpath = "//div/i[contains(@class,'fa fa-arrow-left')]")
	WebElement ifscSearchBack;

	@FindBy(id = "search-bank-ifsc")
	WebElement ifscSearch;

	@FindBy(xpath = "//div[contains(@class,'ifsc-search-modal')]//button[contains(text(),'OK')]")
	WebElement ifscSearchOK;

	@FindBy(xpath = "//span[contains(@class,'custom-ul-errormessage')]/span[contains(text(),'Branch')]")
	WebElement validateIFSC;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]//strong")
	WebElement fcmHeading;

	@FindBy(xpath = "//li[1][contains(@class,'notifications')]/span[2]")
	WebElement fcmContent;

	// Load all objects
	public SettingsPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	// Perform action on page based on given commands
	public void settings(Map<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {

		try {
			commonUtils.waitForSpinner();

			// Updating org_stlmnt_info table as per test case
			if (usrData.get("MODE").equalsIgnoreCase("Change to Bank Account")) {
				dbUtils.deleteOrgSettlementInfo(mobileNumFromIni());
				dbUtils.insertOrgSettlementInfo("DO_NOT_SETTLE", "2", "1", mobileNumFromIni(), "1");
//				dbUtils.updateOrgSettlementInfo("DO_NOT_SETTLE", "2", "1", "(NULL)", mobileNumFromIni());
			} else if (usrData.get("MODE").equalsIgnoreCase("Change to Do Not Settle")) {
				dbUtils.deleteOrgSettlementInfo(mobileNumFromIni());
				dbUtils.insertOrgSettlementInfo("TO_BANK", "2", "1", mobileNumFromIni(), "1");
//				dbUtils.updateOrgSettlementInfo("TO_BANK", "2", "1", "(NULL)", mobileNumFromIni());
			} else if (usrData.get("MODE").equalsIgnoreCase("Keep Bank Account")) {
				dbUtils.deleteOrgSettlementInfo(mobileNumFromIni());
				dbUtils.insertOrgSettlementInfo("TO_BANK", "2", "1", mobileNumFromIni(), "1");
//				dbUtils.updateOrgSettlementInfo("TO_BANK", "2", "1", "(NULL)", mobileNumFromIni());
			} else if (usrData.get("MODE").equalsIgnoreCase("Keep Do Not Settle")) {
				dbUtils.deleteOrgSettlementInfo(mobileNumFromIni());
				if (usrData.get("ACCOUNT").equalsIgnoreCase("No exisitng account")) {
					System.out.println("No new entry added");
				} else {
					dbUtils.insertOrgSettlementInfo("DO_NOT_SETTLE", "2", "1", mobileNumFromIni(), "1");
//					dbUtils.updateOrgSettlementInfo("DO_NOT_SETTLE", "2", "1", "(NULL)", mobileNumFromIni());
				}
			}

			if (usrData.get("ASSERTION").equalsIgnoreCase("Pending")) {
				dbUtils.updateOrgSettlementInfo("DO_NOT_SETTLE", "1", "1", "(NULL)", mobileNumFromIni());
			} else if (usrData.get("ASSERTION").equalsIgnoreCase("Rejected")) {
				dbUtils.updateOrgSettlementInfo("DO_NOT_SETTLE", "3", "1", "(NULL)", mobileNumFromIni());
			} else if (usrData.get("ASSERTION").equalsIgnoreCase("Blocked")) {
				dbUtils.updateOrgSettlementInfo("DO_NOT_SETTLE", "4", "0", "Incorrect bank details",
						mobileNumFromIni());
			}

			commonUtils.selectFeatureFromMenu1(settings, pageTitle);
			commonUtils.waitForSpinner();

			if (usrData.get("ASSERTION").equalsIgnoreCase("Pending")) {
				waitUntilElementIsVisible(accountStatus);
				Assert.assertEquals(accountStatus.getText(), "PENDINGVERIFICATION");
				System.out.println("The status is " + accountStatus.getText());
			} else if (usrData.get("ASSERTION").equalsIgnoreCase("Rejected")) {
				waitUntilElementIsVisible(accountStatus);
				Assert.assertEquals(accountStatus.getText(), "REJECTED");
				System.out.println("The status is " + accountStatus.getText());
			} else if (usrData.get("ASSERTION").equalsIgnoreCase("Blocked")) {
				waitUntilElementIsVisible(blockedIcon);
				System.out.println("The status is blocked");

			}

			if (usrData.get("ASSERTION").equalsIgnoreCase("Pending")
					|| usrData.get("ASSERTION").equalsIgnoreCase("Blocked")) {
				System.out.println("No edit is allowed on this page");
			} else {
				// Changing mode
				if (usrData.get("MODE").contains("Change")) {
					if (usrData.get("MODE").equalsIgnoreCase("Change to Bank Account")) {
						Thread.sleep(1000);
						waitUntilElementIsClickableAndClickTheElement(bankAccountRadioButton);
					} else if (usrData.get("MODE").equalsIgnoreCase("Change to Do Not Settle")) {
						Thread.sleep(1000);
						waitUntilElementIsClickableAndClickTheElement(doNotSettleRadioButton);
					}
					System.out.println("Radio button clicked");
					Thread.sleep(1000);
					waitUntilElementIsClickableAndClickTheElement(saveChangesButton);
					System.out.println("Save changes button clicked");
				}

				if (usrData.get("MODE").contains("Keep")) {
					if (usrData.get("MODE").equalsIgnoreCase("Keep Bank Account")) {
						waitUntilElementIsClickableAndClickTheElement(addButton);
						waitUntilElementIsVisible(settingsTxnScreen);
						System.out.println("Txn screen displayed");
						assertionOnInfoScreen(usrData);
						waitUntilElementIsClickableAndClickTheElement(okButton);
						System.out.println("Ok button clicked");
					} else if (usrData.get("MODE").equalsIgnoreCase("Keep Do Not Settle")) {
						waitUntilElementIsVisible(noBankAccountMsg);
						System.out.println(noBankAccountMsg.getText());
						Thread.sleep(1000);
						Assert.assertTrue(noBankAccountMsg.getText().contains("There are no saved accounts."));
						waitUntilElementIsClickableAndClickTheElement(addButton);

						if (!usrData.get("ACHOLDERNAME").equalsIgnoreCase("SKIP")) {
							Thread.sleep(1000);

							Assert.assertEquals(debitInfo.getText(), "Rs. " + dbUtils.getAccountValidationCharge()
									+ " will be deducted from Cashout Wallet for Account details submission.");
							System.out.println(debitInfo.getText());

							waitUntilElementIsClickableAndClickTheElement(accHolderName);
							accHolderName.clear();
							accHolderName.sendKeys(getBeneNameFromIni(usrData.get("ACHOLDERNAME")));
							System.out.println("Account holder name '" + usrData.get("ACHOLDERNAME") + "' entered");

							waitUntilElementIsClickableAndClickTheElement(accNumber);
							accNumber.clear();
							accNumber.sendKeys(getAccountNumberFromIni(usrData.get("ACNUMBER")));
							System.out.println("Account number '" + getAccountNumberFromIni("GetNum") + "' entered");

							waitUntilElementIsClickableAndClickTheElement(confirmAccNumber);
							confirmAccNumber.clear();
							confirmAccNumber.sendKeys(usrData.get("ACNUMBER"));
							System.out.println("Confirm Account number '" + usrData.get("ACNUMBER") + "' entered");

							if (usrData.get("IFSCTYPE").equalsIgnoreCase("Manual")) {
								waitUntilElementIsClickableAndClickTheElement(ifscCode);
								ifscCode.clear();
								ifscCode.sendKeys(usrData.get("IFSCCODE"));
								System.out.println("IFSC code '" + usrData.get("IFSCCODE") + "' entered");
							} else if (usrData.get("IFSCTYPE").equalsIgnoreCase("Search Screen")) {
								waitUntilElementIsClickableAndClickTheElement(ifscSearchIcon);
								System.out.println("IFSC search icon clicked");
								waitUntilElementIsVisible(ifscSearchScreen);
								waitUntilElementIsVisible(ifscSearchBankList);
								ifscSearchBankList.click();
								System.out.println("IFSC bank drop down clicked");
								String ifscBank = "//li[contains(text(),'"
										+ dbUtils.ifscCodeDetails(usrData.get("IFSCCODE"), "bank") + "')]";
								WebElement ifscSearchBank = wdriver.findElement(By.xpath(ifscBank));
								ifscSearchBank.click();
								System.out.println("IFSC bank selected");
								ifscSearchStateList.click();
								System.out.println("IFSC state drop down clicked");
								String stateFromDB = dbUtils.ifscCodeDetails(usrData.get("IFSCCODE"), "state");
								String stateCapitalized = stateFromDB.toUpperCase();
								String ifscState = "//li[contains(text(),'" + stateCapitalized + "')]";
								WebElement ifscSearchState = wdriver.findElement(By.xpath(ifscState));
								ifscSearchState.click();
								System.out.println("IFSC state selected");
								ifscSearchDistrict
										.sendKeys(dbUtils.ifscCodeDetails(usrData.get("IFSCCODE"), "district"));
								System.out.println("IFSC district entered");
								ifscSearchBranch.sendKeys(dbUtils.ifscCodeDetails(usrData.get("IFSCCODE"), "branch"));
								System.out.println("IFSC branch entered");
								ifscSearchButton.click();
								System.out.println("Search button clicked");
								commonUtils.waitForSpinner();
								waitUntilElementIsVisible(ifscSearchBack);
								String searchCode = "//span[contains(@class,'add-beneficiary-list')][contains(text(),'"
										+ usrData.get("IFSCCODE") + "')]/parent::li";
								WebElement ifscSearchCode = wdriver.findElement(By.xpath(searchCode));
								waitUntilElementIsClickableAndClickTheElement(ifscSearchCode);
								System.out.println("IFSC code '" + usrData.get("IFSCCODE") + "' entered");
								ifscSearchOK.click();
								System.out.println("OK button clicked");
							} else if (usrData.get("IFSCTYPE").equalsIgnoreCase("Drop Down")) {
								waitUntilElementIsClickableAndClickTheElement(ifscCode);
								ifscCode.clear();
								String searchCode = "//span[contains(@class,'add-beneficiary-sublist')][contains(text(),'"
										+ usrData.get("IFSCCODE") + "')]/parent::li";
								WebElement ifscSearchCode = wdriver.findElement(By.xpath(searchCode));
								waitUntilElementIsClickableAndClickTheElement(ifscSearchCode);
								System.out.println("IFSC code '" + usrData.get("IFSCCODE") + "' entered");
							}
							getBankNameFromIni(dbUtils.ifscCodeDetails(usrData.get("IFSCCODE"), "bank"));
							waitUntilElementIsVisible(validateIFSC); // wait for Branch name
							System.out.println(validateIFSC.getText());
						}

						String buttonName = usrData.get("SETTINGSBUTTON");
						String buttonXpath = "//button[contains(text(),'" + buttonName + "')]";
						WebElement button = wdriver.findElement(By.xpath(buttonXpath));
						Thread.sleep(3000);
						waitUntilElementIsClickableAndClickTheElement(button);
						if (buttonName.equalsIgnoreCase("Clear")) {
							Thread.sleep(2000);
							System.out.println("Clear button clicked");
						} else if (buttonName.equalsIgnoreCase("Submit")) {
							System.out.println("Submit button clicked");
						}
					}
				}
				if (usrData.get("ASSERTION").equalsIgnoreCase("Same Data")) {
					Assert.assertEquals(toasterMsg.getText(),
							"The Bank detail provided already exists in our system, kindly check and update");
					System.out.println(toasterMsg.getText());
				} else if (!usrData.get("MODE").equalsIgnoreCase("Keep Bank Account")
						&& !usrData.get("SETTINGSBUTTON").equalsIgnoreCase("Clear")) {
					waitUntilElementIsVisible(MPINScreen);
					System.out.println("MPIN screen displayed");
					waitUntilElementIsClickableAndClickTheElement(enterMPIN);
					if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
						enterMPIN.sendKeys(getAuthfromIni("MPIN"));
					} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
						enterMPIN.sendKeys("9999");
					}
					System.out.println("MPIN entered");

					String mpinButtonName = usrData.get("MPINSCREENBUTTON");
					String mpinScreenButtonXpath = "//h5[contains(text(),'Enter 4 digit PIN')]/parent::div/"
							+ "following-sibling::div/following-sibling::div/button[contains(text(),'" + mpinButtonName
							+ "')]";
					WebElement mpinScreenButton = wdriver.findElement(By.xpath(mpinScreenButtonXpath));
					waitUntilElementIsClickableAndClickTheElement(mpinScreenButton);
					System.out.println(mpinButtonName + " button clicked");
					if (mpinButtonName.equalsIgnoreCase("Cancel")) {
						System.out.println("Cancel button clicked");
					} else if (mpinButtonName.equalsIgnoreCase("Submit")) {
						if (usrData.get("MODE").equalsIgnoreCase("Change to Do Not Settle")) {
							commonUtils.waitForSpinner();

							// Verify the details on transaction screen
							if (settingsTxnScreen.getText().equalsIgnoreCase("Success!")) {
								assertionOnSuccessScreen(usrData);
								waitUntilElementIsClickableAndClickTheElement(doneButton);
								System.out.println("Done button clicked");
								if (usrData.get("ASSERTION").contains("FCM")) {
									commonUtils.selectFeatureFromMenu1(moneyTransfer, pageTitle2);
									assertionOnFCM(usrData);
								}
							} else if (settingsTxnScreen.getText().equalsIgnoreCase("Failed!")) {
								if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
									assertionOnFailedScreen(usrData);
									if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
										System.out.println("Clicking exit button");
									} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
										retryButton.click();
										waitUntilElementIsVisible(MPINScreen);
										System.out.println("MPIN screen displayed");
										waitUntilElementIsClickableAndClickTheElement(enterMPIN);
										enterMPIN.sendKeys(getAuthfromIni("MPIN"));
										System.out.println("MPIN entered");
										waitUntilElementIsClickableAndClickTheElement(submitMPIN);
										System.out.println("Submit button clicked");
										commonUtils.waitForSpinner();
										waitUntilElementIsVisible(settingsTxnScreen);
										System.out.println("Txn screen displayed");
										assertionOnFailedScreen(usrData);
									}
									waitUntilElementIsClickableAndClickTheElement(exitButton);
									System.out.println("Exit button clicked");
								} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
									waitUntilElementIsVisible(settingsTxnScreenMessage);
									System.out.println(settingsTxnScreenMessage.getText());
									if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
										exitButton.click();
										System.out.println("Exit button clicked");
									} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
										retryButton.click();
										waitUntilElementIsVisible(MPINScreen);
										System.out.println("MPIN screen displayed");
										Thread.sleep(1000);
										waitUntilElementIsClickableAndClickTheElement(enterMPIN);
										enterMPIN.sendKeys(getAuthfromIni("MPIN"));
										System.out.println("MPIN entered");
										waitUntilElementIsClickableAndClickTheElement(submitMPIN);
										System.out.println("Submit button clicked");
										commonUtils.waitForSpinner();
										waitUntilElementIsVisible(settingsTxnScreen);
										System.out.println("Txn screen displayed");
										assertionOnSuccessScreen(usrData);
										waitUntilElementIsClickableAndClickTheElement(doneButton);
										System.out.println("Done button clicked");
									}
								}
							}
						} else {
							commonUtils.pendingScreen();

							waitUntilRblAccountValElementIsVisible(additionalInfoModal);
							System.out.println("Need Additional Info modal displayed");

							commonUtils.uploadFile(uploadFile);
							System.out.println("Image selected");

							waitUntilElementIsVisible(toasterMsg);
							Assert.assertEquals(toasterMsg.getText(), "Image Upload success !!");

							while (okButton.getCssValue("background-color").equals("rgba(0, 150, 197, 1)") == false) {
								okButton.click();
							}
							okButton.click();
							System.out.println("Ok button clicked");

							waitUntilElementIsVisible(multiAccountPendingScreen);
							System.out.println("Pending screen displayed");

							assertionOnPendingScreen(usrData);
							waitUntilElementIsClickableAndClickTheElement(okButton);
							System.out.println("OK button clicked");
						}
					} else if (usrData.get("SUBMIT").equalsIgnoreCase("Clear")) {
						clearButton.click();
						System.out.println("Clear button clicked");
					}
				}
			}
		} catch (Exception e) {
			wdriver.navigate().refresh();
			e.printStackTrace();
			System.out.println("Test Case Failed");
			Assert.fail();
		}
	}

	// Verify details on success screen
	public void assertionOnSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("MODE").equalsIgnoreCase("Change to Bank Account")) {
			Assert.assertEquals(settingsTxnScreenMessage.getText(),
					"Your end of the day withdrawable amount will now be credited to your Primary bank account.");
			System.out.println(settingsTxnScreenMessage.getText());
		} else if (usrData.get("MODE").equalsIgnoreCase("Change to Do Not Settle")) {
			Assert.assertEquals(settingsTxnScreenMessage.getText(),
					"You can now use your withdrawable amount for transaction purposes.");
			System.out.println(settingsTxnScreenMessage.getText());
		} else if (usrData.get("MODE").equalsIgnoreCase("Keep Do Not Settle")) {
			Assert.assertEquals(settingsTxnScreenMessage.getText(),
					"The bank account details have been submitted for verification. "
							+ "Amount shall not be settled to older account.");
			System.out.println(settingsTxnScreenMessage.getText());
		}
	}

	// Verify details on success screen
	public void assertionOnPendingScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		waitUntilElementIsVisible(multiAccountTxnScreenMessage);
		Assert.assertEquals(multiAccountTxnScreenMessage.getText(),
				"Bank Account details have been submitted successfully");
		System.out.println(multiAccountTxnScreenMessage.getText());
	}

	// Verify details on failed screen
	public void assertionOnFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid MPIN")) {
			Assert.assertEquals(settingsTxnScreenMessage.getText(), "Authentication Failed Invalid MPIN");
		}
		System.out.println(settingsTxnScreenMessage.getText());
	}

	// Verify details on info screen
	public void assertionOnInfoScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(settingsTxnScreenMessage.getText(),
				"Please change settlement mode to 'Do Not Settle' before updating bank details");
		System.out.println(settingsTxnScreenMessage.getText());
	}

	// FCM assertion
	public void assertionOnFCM(Map<String, String> usrData) throws ClassNotFoundException {
		String successFCMHeading = "You have updated your account details with ";
		String successFCMContent = "The bank account details have been submitted for verification.";

		fcmMethod(successFCMHeading, successFCMContent);
	}

	public void fcmMethod(String heading, String content) {
		Assert.assertEquals(fcmHeading.getText().substring(0, 43), heading);
		Assert.assertEquals(fcmContent.getText().substring(0, 62), content);
		System.out.println(fcmHeading.getText());
		System.out.println(fcmContent.getText());
	}

	// Get Partner name
	public String partner() {
		return "RBL";
	}
}
