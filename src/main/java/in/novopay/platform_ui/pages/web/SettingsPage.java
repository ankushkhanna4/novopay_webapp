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

	@FindBy(xpath = "//span[contains(text(),'Capital First')]/parent::li")
	WebElement capitalFirstIcon;

	@FindBy(id = "capital-first-money-transfer-mobile-number")
	WebElement depositorMobNum;

	@FindBy(xpath = "//label[@for='settlement-mode-retailer-credit']")
	WebElement retailerCreditRadioButton;

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

	@FindBy(xpath = "//div[contains(@class,'settlement-modal')]/div/div/div/h4[contains(text(),'!')]")
	WebElement settingsTxnScreen;

	@FindBy(xpath = "//div[contains(@class,'settlement-modal')]/div/div/div")
	WebElement settingsTxnScreenType;

	@FindBy(xpath = "//div[contains(@class,'settlement-modal')]/div/div/div/following-sibling::div/div[1]")
	WebElement settingsTxnScreenMessage;

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

	@FindBy(id = "settlement-mode-retailer-name")
	WebElement accHolderName;

	@FindBy(id = "settlement-mode-create-field-retailer-bank-ifsc-list")
	WebElement ifscCode;

	@FindBy(xpath = "//input[contains(@id,'settlement-mode-create-field-retailer-bank-ifsc-list')]/parent::div/following-sibling::span")
	WebElement ifscSearchIcon;

	@FindBy(id = "settlement-mode-retailer-account-number")
	WebElement accNumber;

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

			if (usrData.get("ASSERTION").contains("FCM")) {
				assertionOnFCM(usrData);
			} else {
				// Updating org_stlmnt_info table as per test case
				if (usrData.get("MODE").equalsIgnoreCase("Change to Bank Account")) {
					dbUtils.updateOrgSettlementInfo("TO_WALLET", "2", "1", "(NULL)", mobileNumFromIni());
				} else if (usrData.get("MODE").equalsIgnoreCase("Change to Retailer Credit")) {
					dbUtils.updateOrgSettlementInfo("TO_BANK", "2", "1", "(NULL)", mobileNumFromIni());
				} else if (usrData.get("MODE").equalsIgnoreCase("Keep Bank Account")) {
					dbUtils.updateOrgSettlementInfo("TO_BANK", "2", "1", "(NULL)", mobileNumFromIni());
				} else if (usrData.get("MODE").equalsIgnoreCase("Keep Retailer Credit")) {
					dbUtils.updateOrgSettlementInfo("TO_WALLET", "2", "1", "(NULL)", mobileNumFromIni());
				}

				if (usrData.get("ASSERTION").equalsIgnoreCase("Pending")) {
					dbUtils.updateOrgSettlementInfo("TO_WALLET", "1", "1", "(NULL)", mobileNumFromIni());
				} else if (usrData.get("ASSERTION").equalsIgnoreCase("Rejected")) {
					dbUtils.updateOrgSettlementInfo("TO_WALLET", "3", "1", "(NULL)", mobileNumFromIni());
				} else if (usrData.get("ASSERTION").equalsIgnoreCase("Blocked")) {
					dbUtils.updateOrgSettlementInfo("TO_WALLET", "4", "0", "Incorrect bank details",
							mobileNumFromIni());
				}

				clickElement(menu);
				scrollElementDown(scrollBar, settings);
				Log.info("Settings option clicked");
				waitUntilElementIsVisible(pageTitle);
				System.out.println(pageTitle.getText() + " page displayed");
				clickElement(menu);
				commonUtils.waitForSpinner();

				if (usrData.get("ASSERTION").equalsIgnoreCase("Pending")) {
					waitUntilElementIsVisible(accountStatus);
					Assert.assertEquals(accountStatus.getText(), "PENDING VERIFICATION");
					Log.info("The status is " + accountStatus.getText());
				} else if (usrData.get("ASSERTION").equalsIgnoreCase("Rejected")) {
					waitUntilElementIsVisible(accountStatus);
					Assert.assertEquals(accountStatus.getText(), "REJECTED");
					Log.info("The status is " + accountStatus.getText());
				} else if (usrData.get("ASSERTION").equalsIgnoreCase("Blocked")) {
					waitUntilElementIsVisible(blockedIcon);
					Log.info("The status is blocked");

				}

				if (usrData.get("ASSERTION").equalsIgnoreCase("Pending")
						|| usrData.get("ASSERTION").equalsIgnoreCase("Blocked")) {
					Log.info("No edit is allowed on this page");
				} else {
					// Changing mode
					if (usrData.get("MODE").contains("Change")) {
						if (usrData.get("MODE").equalsIgnoreCase("Change to Bank Account")) {
							Thread.sleep(1000);
							waitUntilElementIsClickableAndClickTheElement(bankAccountRadioButton);
						} else if (usrData.get("MODE").equalsIgnoreCase("Change to Retailer Credit")) {
							Thread.sleep(1000);
							waitUntilElementIsClickableAndClickTheElement(retailerCreditRadioButton);
						}
						Log.info("Radio button clicked");
						Thread.sleep(1000);
						waitUntilElementIsClickableAndClickTheElement(saveChangesButton);
						Log.info("Save changes button clicked");
					}

					if (usrData.get("MODE").contains("Keep")) {
						if (usrData.get("MODE").equalsIgnoreCase("Keep Bank Account")) {
							waitUntilElementIsClickableAndClickTheElement(editButton);
							waitUntilElementIsVisible(settingsTxnScreen);
							Log.info("Txn screen displayed");
							assertionOnInfoScreen(usrData);
							waitUntilElementIsClickableAndClickTheElement(okButton);
							Log.info("Ok button clicked");
						} else if (usrData.get("MODE").equalsIgnoreCase("Keep Retailer Credit")) {
							waitUntilElementIsClickableAndClickTheElement(editButton);

							if (!usrData.get("ACHOLDERNAME").equalsIgnoreCase("SKIP")) {
								Thread.sleep(1000);
								waitUntilElementIsClickableAndClickTheElement(accHolderName);
								accHolderName.clear();
								accHolderName.sendKeys(getBeneNameFromIni(usrData.get("ACHOLDERNAME")));
								Log.info("Account holder name '" + usrData.get("ACHOLDERNAME") + "' entered");
								waitUntilElementIsClickableAndClickTheElement(ifscCode);
								ifscCode.clear();
								if (usrData.get("IFSCTYPE").equalsIgnoreCase("Manual")) {
									ifscCode.sendKeys(usrData.get("IFSCCODE"));
									Log.info("IFSC code '" + usrData.get("IFSCCODE") + "' entered");
								} else if (usrData.get("IFSCTYPE").equalsIgnoreCase("Search Screen")) {
									Log.info("IFSC search icon clicked");
									waitUntilElementIsVisible(ifscSearchScreen);
									waitUntilElementIsVisible(ifscSearchBankList);
									ifscSearchBankList.click();
									Log.info("IFSC bank drop down clicked");
									String ifscBank = "//li[contains(text(),'"
											+ dbUtils.ifscCodeDetails(usrData.get("IFSCCODE"), "bank") + "')]";
									WebElement ifscSearchBank = wdriver.findElement(By.xpath(ifscBank));
									ifscSearchBank.click();
									Log.info("IFSC bank selected");
									ifscSearchStateList.click();
									Log.info("IFSC state drop down clicked");
									String stateFromDB = dbUtils.ifscCodeDetails(usrData.get("IFSCCODE"), "state");
									String stateFirstLetterCapitalized = stateFromDB.substring(0, 1).toUpperCase()
											+ stateFromDB.substring(1).toLowerCase();
									String ifscState = "//li[contains(text(),'" + stateFirstLetterCapitalized + "')]";
									WebElement ifscSearchState = wdriver.findElement(By.xpath(ifscState));
									ifscSearchState.click();
									Log.info("IFSC state selected");
									ifscSearchDistrict
											.sendKeys(dbUtils.ifscCodeDetails(usrData.get("IFSCCODE"), "district"));
									Log.info("IFSC district entered");
									ifscSearchBranch
											.sendKeys(dbUtils.ifscCodeDetails(usrData.get("IFSCCODE"), "branch"));
									Log.info("IFSC branch entered");
									ifscSearchButton.click();
									Log.info("Search button clicked");
									commonUtils.waitForSpinner();
									waitUntilElementIsVisible(ifscSearchBack);
									String searchCode = "//span[contains(@class,'add-beneficiary-list')][contains(text(),'"
											+ usrData.get("IFSCCODE") + "')]/parent::li";
									WebElement ifscSearchCode = wdriver.findElement(By.xpath(searchCode));
									waitUntilElementIsClickableAndClickTheElement(ifscSearchCode);
									Log.info("IFSC code '" + usrData.get("IFSCCODE") + "' entered");
									ifscSearchOK.click();
									Log.info("OK button clicked");
								} else if (usrData.get("IFSCTYPE").equalsIgnoreCase("Drop Down")) {
									String searchCode = "//span[contains(@class,'add-beneficiary-sublist')][contains(text(),'"
											+ usrData.get("IFSCCODE") + "')]/parent::li";
									WebElement ifscSearchCode = wdriver.findElement(By.xpath(searchCode));
									waitUntilElementIsClickableAndClickTheElement(ifscSearchCode);
									Log.info("IFSC code '" + usrData.get("IFSCCODE") + "' entered");
								}
								getBankNameFromIni(dbUtils.ifscCodeDetails(usrData.get("IFSCCODE"), "bank"));
								waitUntilElementIsVisible(validateIFSC); // wait for Branch name
								Log.info(validateIFSC.getText());

								waitUntilElementIsClickableAndClickTheElement(accNumber);
								accNumber.clear();
								accNumber.sendKeys(getAccountNumberFromIni(usrData.get("ACNUMBER")));
								Log.info("Bene account number '" + getAccountNumberFromIni("GetNum") + "' entered");
							}
							uploadFile(uploadFile);
							Log.info("Image selected");

							String buttonName = usrData.get("SETTINGSBUTTON");
							String buttonXpath = "//button[contains(text(),'" + buttonName + "')]";
							WebElement button = wdriver.findElement(By.xpath(buttonXpath));
							Thread.sleep(3000);
							waitUntilElementIsClickableAndClickTheElement(button);
							if (buttonName.equalsIgnoreCase("Clear")) {
								Thread.sleep(2000);
								Log.info("Clear button clicked");
							} else if (buttonName.equalsIgnoreCase("Submit")) {
								Log.info("Submit button clicked");
							}
						}
					}
					if (usrData.get("ASSERTION").equalsIgnoreCase("Same Data")) {
						Assert.assertEquals(toasterMsg.getText(),
								"The Bank detail provided already exists in our system, kindly check and update");
						Log.info(toasterMsg.getText());
					} else if (!usrData.get("MODE").equalsIgnoreCase("Keep Bank Account")
							&& !usrData.get("SETTINGSBUTTON").equalsIgnoreCase("Clear")) {
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
							commonUtils.waitForSpinner();
							waitUntilElementIsVisible(settingsTxnScreen);
							Log.info("Txn screen displayed");

							// Verify the details on transaction screen
							if (settingsTxnScreen.getText().equalsIgnoreCase("Success!")) {
								assertionOnSuccessScreen(usrData);
								waitUntilElementIsClickableAndClickTheElement(doneButton);
								Log.info("Done button clicked");
							} else if (settingsTxnScreen.getText().equalsIgnoreCase("Failed!")) {
								if (usrData.get("MPIN").equalsIgnoreCase("Valid")) {
									assertionOnFailedScreen(usrData);
									if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
										Log.info("Clicking exit button");
									} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
										retryButton.click();
										waitUntilElementIsVisible(MPINScreen);
										Log.info("MPIN screen displayed");
										waitUntilElementIsClickableAndClickTheElement(enterMPIN);
										enterMPIN.sendKeys(getAuthfromIni("MPIN"));
										Log.info("MPIN entered");
										waitUntilElementIsClickableAndClickTheElement(submitMPIN);
										Log.info("Submit button clicked");
										commonUtils.waitForSpinner();
										waitUntilElementIsVisible(settingsTxnScreen);
										Log.info("Txn screen displayed");
										assertionOnFailedScreen(usrData);
									}
									waitUntilElementIsClickableAndClickTheElement(exitButton);
									Log.info("Exit button clicked");
								} else if (usrData.get("MPIN").equalsIgnoreCase("Invalid")) {
									waitUntilElementIsVisible(settingsTxnScreenMessage);
									Log.info(settingsTxnScreenMessage.getText());
									if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Exit")) {
										exitButton.click();
										Log.info("Exit button clicked");
									} else if (usrData.get("TXNSCREENBUTTON").equalsIgnoreCase("Retry")) {
										retryButton.click();
										waitUntilElementIsVisible(MPINScreen);
										Log.info("MPIN screen displayed");
										Thread.sleep(1000);
										waitUntilElementIsClickableAndClickTheElement(enterMPIN);
										enterMPIN.sendKeys(getAuthfromIni("MPIN"));
										Log.info("MPIN entered");
										waitUntilElementIsClickableAndClickTheElement(submitMPIN);
										Log.info("Submit button clicked");
										commonUtils.waitForSpinner();
										waitUntilElementIsVisible(settingsTxnScreen);
										Log.info("Txn screen displayed");
										assertionOnSuccessScreen(usrData);
										waitUntilElementIsClickableAndClickTheElement(doneButton);
										Log.info("Done button clicked");
									}
								}
							}
						} else if (usrData.get("SUBMIT").equalsIgnoreCase("Clear")) {
							clearButton.click();
							Log.info("Clear button clicked");
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

	// Verify details on success screen
	public void assertionOnSuccessScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("MODE").equalsIgnoreCase("Bank Account")) {
			Assert.assertEquals(settingsTxnScreenMessage.getText(),
					"Your Cashout Balance and Merchant Balance will now be credited to your verified bank account on End of Day basis.");
			Log.info(settingsTxnScreenMessage.getText());
		} else if (usrData.get("MODE").equalsIgnoreCase("Retailer Credit")) {
			Assert.assertEquals(settingsTxnScreenMessage.getText(),
					"Your Cashout Balance and Merchant Balance will now be credited to your retailer wallet on End of Day basis.");
			Log.info(settingsTxnScreenMessage.getText());
		}
	}

	// Verify details on failed screen
	public void assertionOnFailedScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		if (usrData.get("ASSERTION").equalsIgnoreCase("Invalid MPIN")) {
			Assert.assertEquals(settingsTxnScreenMessage.getText(), "Authentication Failed Invalid MPIN");
		}
		Log.info(settingsTxnScreenMessage.getText());
	}

	// Verify details on info screen
	public void assertionOnInfoScreen(Map<String, String> usrData)
			throws ClassNotFoundException, ParseException, InterruptedException {
		Assert.assertEquals(settingsTxnScreenMessage.getText(),
				"Please change settlement mode to 'Do Not Settle' before updating bank details");
		Log.info(settingsTxnScreenMessage.getText());
	}

	public void uploadFile(WebElement cancelledCheque) throws InterruptedException, IOException {
		Log.info("selecting cancelled cheque image");
		Thread.sleep(2000);
		waitUntilElementIsClickableAndClickTheElement(cancelledCheque);
		Thread.sleep(500);
		String uploadFile = "./test-data/UploadFile.exe";
		Runtime.getRuntime().exec(uploadFile);
		Thread.sleep(500);
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
		Log.info(fcmHeading.getText());
		Log.info(fcmContent.getText());
	}

	// Get Partner name
	public String partner() {
		return "RBL";
	}
}
