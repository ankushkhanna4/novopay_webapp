package in.novopay.platform_ui.pages.web;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.Log;

public class LoginPage extends BasePage {
	DBUtils dbUtils = new DBUtils();
	CommonUtils commonUtils = new CommonUtils(wdriver);

	public LoginPage(WebDriver wdriver) {
		super(wdriver);
		PageFactory.initElements(wdriver, this);
	}

	@FindBy(id = "regMobileNumber")
	WebElement mobNum;

	@FindBy(id = "password")
	WebElement mPin;

	@FindBy(xpath = "//button[contains(text(),'LOGIN')]")
	WebElement login;

	@FindBy(xpath = "//*[@id='otp']")
	WebElement otp;

	@FindBy(xpath = "//app-otpcomponent//button")
	WebElement proceedOTP;

	@FindBy(xpath = "//u[contains(text(),'Forgot')]")
	WebElement forgotPin;

	@FindBy(id = "panNumber")
	WebElement panNumber;

	@FindBy(xpath = "//app-forgotpin//button")
	WebElement proceedPan;

	@FindBy(id = "newpin")
	WebElement newpin;

	@FindBy(id = "reenterpin")
	WebElement reenterpin;

	@FindBy(xpath = "//button[contains(text(),'FINISH')]")
	WebElement finish;

	@FindBy(xpath = "//div[contains(@class,'toast-message')]")
	WebElement toasterMsg;

	@FindBy(xpath = "//button[@class='toast-close-button']")
	WebElement toastCloseButton;

	@FindBy(xpath = "//a[contains(@aria-describedby,'tooltip')][1]")
	WebElement hambergerMenu;

	@FindBy(xpath = "//*[@id='regMobileNumber']/parent::div/ul/li")
	WebElement mobNumErrorMsg;

	@FindBy(xpath = "//*[@id='password']/parent::div/ul/li")
	WebElement passwordErrorMsg;

	@FindBy(xpath = "//*[@id='otp']/parent::div/ul/li")
	WebElement otpErrorMsg;

	@FindBy(xpath = "//*[@id='panNumber']/parent::div/ul/li")
	WebElement panErrorMsg;

	@FindBy(xpath = "//*[contains(text(),'Go back to login')]")
	WebElement goBackToLogin;

	@FindBy(xpath = "//*[contains(text(),'RESEND OTP')]")
	WebElement resendOTP;

	public void login(Map<String, String> usrData) throws ClassNotFoundException, InterruptedException {

		Log.info("Retailer WebApp 2.0 Login page displayed");

		try {
			String mobNumFromSheet = "";
			if (usrData.get("MOBILENUMBER").equalsIgnoreCase("RetailerMobNum")) {
				mobNumFromSheet = getLoginMobileFromIni(mobNumFromSheet);
			} else {
				mobNumFromSheet = usrData.get("MOBILENUMBER");
			}
			waitUntilElementIsClickableAndClickTheElement(mobNum);
			mobNum.clear();
			Log.info("entering mobile number");
			mobNum.sendKeys(mobNumFromSheet);
			if (usrData.get("FORGOTPIN").equalsIgnoreCase("NO")) {
				waitUntilElementIsClickableAndClickTheElement(mPin);
				mPin.clear();
				Log.info("entering MPIN");
				mPin.sendKeys(usrData.get("MPIN"));
				Log.info("clicking on LOGIN button");
				waitUntilElementIsClickableAndClickTheElement(login);

				if (mobNumFromSheet.startsWith("RBL") || mobNumFromSheet.startsWith("AXIS")
						|| mobNumFromSheet.startsWith("FINO") || mobNumFromSheet.startsWith("YBL")) {
					mobNumFromSheet = getLoginMobileFromIni(mobNumFromSheet);
				}
				if (mobNumValidation(mobNumFromSheet).equalsIgnoreCase("valid")
						&& checkMobNumExistence(mobNumFromSheet).equalsIgnoreCase("exists")
						&& (usrData.get("MPIN").equals("1111") || usrData.get("MPIN").equals("2222"))) {
					commonUtils.waitForSpinner();
					String txnOtp = "";
					Thread.sleep(2000);
					waitUntilElementIsClickableAndClickTheElement(otp);
					otp.clear();
					Log.info("entering OTP");
					if (usrData.get("OTP").equalsIgnoreCase("Yes")) {
						txnOtp = getAuthfromIni("LoginOTP");
					} else if (usrData.get("OTP").equalsIgnoreCase("Resend")) {
						waitUntilElementIsClickableAndClickTheElement(resendOTP);
						txnOtp = getAuthfromIni("LoginOTP");
					} else {
						txnOtp = usrData.get("OTP");
					}
					otp.sendKeys(txnOtp);
					Log.info("clicking on PROCEED button");
					waitUntilElementIsClickableAndClickTheElement(proceedOTP);
					if (txnOtp.equals("342360")) {
						commonUtils.waitForSpinner();
						waitUntilElementIsVisible(hambergerMenu);
						Log.info("Page displayed");
						/*
						 * if (usrData.get("NEWPIN").equalsIgnoreCase("1111")) {
						 * dbUtils.updateMPIN(getLoginMobileFromIni(mobNumFromSheet)); }
						 */
					} else if (txnOtp.equals("")) {
						waitUntilElementIsVisible(otpErrorMsg);
						Assert.assertEquals(otpErrorMsg.getText(), "Required Field");
						Log.info(otpErrorMsg.getText());
						waitUntilElementIsVisible(goBackToLogin);
						goBackToLogin.click();
					} else if (otpValidation(txnOtp).equalsIgnoreCase("invalid")) {
						waitUntilElementIsVisible(otpErrorMsg);
						if (txnOtp.length() < 4) {
							Assert.assertEquals(otpErrorMsg.getText(), "Minimum length should be 4 digits");
							Log.info(otpErrorMsg.getText());
						} else {
							Assert.assertEquals(otpErrorMsg.getText(), "Invalid OTP");
							Log.info(otpErrorMsg.getText());
						}
						waitUntilElementIsClickableAndClickTheElement(goBackToLogin);
					} else if (otpValidation(txnOtp).equalsIgnoreCase("valid")
							&& !usrData.get("OTP").equals("342360")) {
						waitUntilElementIsVisible(toasterMsg);
						Assert.assertEquals(toasterMsg.getText(), "OTP does not match");
						Log.info(toasterMsg.getText());
						commonUtils.waitForSpinner();
						waitUntilElementIsClickableAndClickTheElement(goBackToLogin);
					}
				} else if (mobNumFromSheet.equals("") && usrData.get("MPIN").equals("")) {
					waitUntilElementIsVisible(mobNumErrorMsg);
					Assert.assertEquals(mobNumErrorMsg.getText(), "Required Field");
					Log.info(mobNumErrorMsg.getText());
					waitUntilElementIsVisible(passwordErrorMsg);
					Assert.assertEquals(passwordErrorMsg.getText(), "Required Field");
					Log.info(passwordErrorMsg.getText());
				} else if (mobNumValidation(mobNumFromSheet).equalsIgnoreCase("invalid")) {
					waitUntilElementIsVisible(mobNumErrorMsg);
					Assert.assertEquals(mobNumErrorMsg.getText(), "Invalid mobile number");
					Log.info(mobNumErrorMsg.getText());
				} else if (mpinValidation(usrData.get("MPIN")).equals("invalid")) {
					waitUntilElementIsVisible(passwordErrorMsg);
					if (usrData.get("MPIN").length() < 4) {
						Assert.assertEquals(passwordErrorMsg.getText(), "Length should be 4 digits");
						Log.info(passwordErrorMsg.getText());
					} else {
						Assert.assertEquals(passwordErrorMsg.getText(), "Invalid MPIN");
						Log.info(passwordErrorMsg.getText());
					}
				} else if (!usrData.get("MPIN").equals("1111")) {
					commonUtils.waitForSpinner();
					waitUntilElementIsVisible(toasterMsg);
					Assert.assertEquals(toasterMsg.getText(), "Invalid credentials");
					Log.info(toasterMsg.getText());
				} else if (checkMobNumExistence(mobNumFromSheet).equalsIgnoreCase("does not exist")) {
					commonUtils.waitForSpinner();
					waitUntilElementIsVisible(toasterMsg);
					Assert.assertEquals(toasterMsg.getText(), "Invalid credentials");
					Log.info(toasterMsg.getText());
				}
			} else if (usrData.get("FORGOTPIN").equalsIgnoreCase("YES")) {
				waitUntilElementIsVisible(forgotPin);
				Log.info("clicking Forgot Pin");
				clickInvisibleElement(forgotPin);
				commonUtils.waitForSpinner();
				waitUntilElementIsClickableAndClickTheElement(panNumber);
				panNumber.clear();
				Log.info("entering PAN number");
				panNumber.sendKeys(usrData.get("PAN"));
				Log.info("clicking on PROCEED button");
				waitUntilElementIsClickableAndClickTheElement(proceedPan);
				if (panValidation(usrData.get("PAN")).equalsIgnoreCase("valid")) {
					if (usrData.get("PAN")
							.equalsIgnoreCase(dbUtils.getPanNumber(getLoginMobileFromIni(mobNumFromSheet)))) {
						commonUtils.waitForSpinner();
						waitUntilElementIsClickableAndClickTheElement(otp);
						otp.clear();
						Log.info("entering OTP");
						otp.sendKeys(getAuthfromIni("LoginOTP"));
						Log.info("clicking on PROCEED button");
						waitUntilElementIsClickableAndClickTheElement(proceedOTP);
						waitUntilElementIsClickableAndClickTheElement(newpin);
						Log.info("entering New PIN");
						newpin.sendKeys(usrData.get("NEWPIN"));
						waitUntilElementIsClickableAndClickTheElement(reenterpin);
						Log.info("Re-entering PIN");
						reenterpin.sendKeys(usrData.get("REENTERPIN"));
						Log.info("clicking on FINISH button");
						waitUntilElementIsClickableAndClickTheElement(finish);
						waitUntilElementIsVisible(toasterMsg);
						if (usrData.get("NEWPIN").equals(usrData.get("REENTERPIN"))) {
							Log.info(toasterMsg.getText());
							toastCloseButton.click();
							waitUntilElementIsVisible(mobNum);
						} else {
							Assert.assertEquals(toasterMsg.getText(), "New MPIN and Re-entered MPIN does not match.");
							Log.info(toasterMsg.getText());
							toastCloseButton.click();
							waitUntilElementIsClickableAndClickTheElement(goBackToLogin);
						}
					} else {
						commonUtils.waitForSpinner();
						waitUntilElementIsVisible(toasterMsg);
						Assert.assertEquals(toasterMsg.getText(), "incorrect POI value entered.");
						Log.info(toasterMsg.getText());
						waitUntilElementIsClickableAndClickTheElement(goBackToLogin);
					}
				} else if (panValidation(usrData.get("PAN")).equalsIgnoreCase("invalid")) {
					waitUntilElementIsVisible(panErrorMsg);
					if (usrData.get("PAN").equalsIgnoreCase("")) {
						Assert.assertEquals(panErrorMsg.getText(), "Required Field");
					} else {
						Assert.assertEquals(panErrorMsg.getText(), "Invalid Pan");
					}
					Log.info(panErrorMsg.getText());
					waitUntilElementIsClickableAndClickTheElement(goBackToLogin);
				}
			}

		} catch (Exception e) {
			wdriver.navigate().refresh();
			e.printStackTrace();
			Log.info("Test Case Failed");
			Assert.fail();
		}
	}

	public String mobNumValidation(String mobNum) {
		if (mobNum.length() == 10 && (mobNum.startsWith("6") || mobNum.startsWith("7") || mobNum.startsWith("8")
				|| mobNum.startsWith("9"))) {
			return "valid";
		} else {
			return "invalid";
		}
	}

	public String mpinValidation(String mPin) {
		if (mPin.length() == 4 && mPin.chars().allMatch(Character::isDigit)) {
			return "valid";
		} else {
			return "invalid";
		}
	}

	public String otpValidation(String otp) {
		if (otp.length() >= 4 && otp.chars().allMatch(Character::isDigit)) {
			return "valid";
		} else {
			return "invalid";
		}
	}

	public String panValidation(String pan) {
		String firstFiveChar = "";
		String numericChar = "";
		String lastChar = "";
		if (pan.length() == 10) {
			firstFiveChar = pan.substring(0, 5);
			numericChar = pan.substring(5, 9);
			lastChar = pan.substring(9);
			if (firstFiveChar.chars().allMatch(Character::isLetter) && numericChar.chars().allMatch(Character::isDigit)
					&& lastChar.chars().allMatch(Character::isLetter)) {
				return "valid";
			} else {
				return "invalid";
			}
		} else {
			return "invalid";
		}
	}

	public String checkMobNumExistence(String mobNum) throws ClassNotFoundException {
		List<String> list = dbUtils.getListOfRetailerMobNum(); // get list of mobile numbers of retailers
		if (list.contains(mobNum)) {
			return "exists";
		} else {
			return "does not exist";
		}

	}
}
