package in.novopay.platform_ui.pages.web;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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

public class LoginPage extends BasePage {
	WebDriverWait wait = new WebDriverWait(wdriver, 30);
	DBUtils dbUtils = new DBUtils();

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

	@FindBy(xpath = "//h1[contains(text(),'Money Transfer')]")
	WebElement pageTitle;

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

		// wdriver.manage().window().maximize();
		Log.info("Retailer WebApp 2.0 Login page displayed");

		// Start the test and set the browser's viewport size to 800x600.
		// eyes.open(wdriver, "Hello World!", "My first Selenium Java test!",
		// new RectangleSize(800, 600));
		try {
			String mobNumFromSheet = "";
			if (usrData.get("MOBILENUMBER").equalsIgnoreCase("RetailerMobNum")) {
				mobNumFromSheet = getLoginMobileFromIni(mobNumFromSheet);
			} else {
				mobNumFromSheet = usrData.get("MOBILENUMBER");
			}
			wait.until(ExpectedConditions.visibilityOf(mobNum));
			mobNum.click();
			mobNum.clear();
			Log.info("entering mobile number");
			mobNum.sendKeys(mobNumFromSheet);
			if (usrData.get("FORGOTPIN").equalsIgnoreCase("NO")) {
				wait.until(ExpectedConditions.elementToBeClickable(mPin));
				mPin.click();
				mPin.clear();
				Log.info("entering MPIN");
				mPin.sendKeys(usrData.get("MPIN"));
				Log.info("clicking on LOGIN button");
				wait.until(ExpectedConditions.elementToBeClickable(login));
				login.click();

				if (mobNumFromSheet.startsWith("RBL") || mobNumFromSheet.startsWith("AXIS")
						|| mobNumFromSheet.startsWith("FINO") || mobNumFromSheet.startsWith("YBL")) {
					mobNumFromSheet = getLoginMobileFromIni(mobNumFromSheet);
				}
				if (mobNumValidation(mobNumFromSheet).equalsIgnoreCase("valid")
						&& checkMobNumExistence(mobNumFromSheet).equalsIgnoreCase("exists")
						&& (usrData.get("MPIN").equals("1111") || usrData.get("MPIN").equals("2222"))) {
					waitForSpinner();
					String txnOtp = "";
					Thread.sleep(2000);
					wait.until(ExpectedConditions.elementToBeClickable(otp));
					otp.click();
					otp.clear();
					Log.info("entering OTP");
					if (usrData.get("OTP").equalsIgnoreCase("Yes")) {
						txnOtp = getAuthfromIni("LoginOTP");
					} else if (usrData.get("OTP").equalsIgnoreCase("Resend")) {
						wait.until(ExpectedConditions.elementToBeClickable(resendOTP));
						resendOTP.click();
						txnOtp = getAuthfromIni("LoginOTP");
					} else {
						txnOtp = usrData.get("OTP");
					}
					otp.sendKeys(txnOtp);
					Log.info("clicking on PROCEED button");
					wait.until(ExpectedConditions.elementToBeClickable(proceedOTP));
					proceedOTP.click();
					if (txnOtp.equals("342360")) {
						waitForSpinner();
						wait.until(ExpectedConditions.visibilityOf(pageTitle));
						Log.info(pageTitle.getText() + " page displayed");
						/*
						 * if (usrData.get("NEWPIN").equalsIgnoreCase("1111")) {
						 * dbUtils.updateMPIN(getLoginMobileFromIni(mobNumFromSheet)); }
						 */
					} else if (txnOtp.equals("")) {
						wait.until(ExpectedConditions.visibilityOf(otpErrorMsg));
						Assert.assertEquals(otpErrorMsg.getText(), "Required Field");
						Log.info(otpErrorMsg.getText());
						wait.until(ExpectedConditions.visibilityOf(goBackToLogin));
						goBackToLogin.click();
					} else if (otpValidation(txnOtp).equalsIgnoreCase("invalid")) {
						wait.until(ExpectedConditions.visibilityOf(otpErrorMsg));
						if (txnOtp.length() < 4) {
							Assert.assertEquals(otpErrorMsg.getText(), "Minimum length should be 4 digits");
							Log.info(otpErrorMsg.getText());
						} else {
							Assert.assertEquals(otpErrorMsg.getText(), "Invalid OTP");
							Log.info(otpErrorMsg.getText());
						}
						wait.until(ExpectedConditions.visibilityOf(goBackToLogin));
						goBackToLogin.click();
					} else if (otpValidation(txnOtp).equalsIgnoreCase("valid")
							&& !usrData.get("OTP").equals("342360")) {
						wait.until(ExpectedConditions.elementToBeClickable(toasterMsg));
						Assert.assertEquals(toasterMsg.getText(), "OTP does not match");
						Log.info(toasterMsg.getText());
						waitForSpinner();
						wait.until(ExpectedConditions.visibilityOf(goBackToLogin));
						goBackToLogin.click();
					}
				} else if (mobNumFromSheet.equals("") && usrData.get("MPIN").equals("")) {
					wait.until(ExpectedConditions.visibilityOf(mobNumErrorMsg));
					Assert.assertEquals(mobNumErrorMsg.getText(), "Required Field");
					Log.info(mobNumErrorMsg.getText());
					wait.until(ExpectedConditions.visibilityOf(passwordErrorMsg));
					Assert.assertEquals(passwordErrorMsg.getText(), "Required Field");
					Log.info(passwordErrorMsg.getText());
				} else if (mobNumValidation(mobNumFromSheet).equalsIgnoreCase("invalid")) {
					wait.until(ExpectedConditions.visibilityOf(mobNumErrorMsg));
					Assert.assertEquals(mobNumErrorMsg.getText(), "Invalid mobile number");
					Log.info(mobNumErrorMsg.getText());
				} else if (mpinValidation(usrData.get("MPIN")).equals("invalid")) {
					wait.until(ExpectedConditions.visibilityOf(passwordErrorMsg));
					if (usrData.get("MPIN").length() < 4) {
						Assert.assertEquals(passwordErrorMsg.getText(), "Length should be 4 digits");
						Log.info(passwordErrorMsg.getText());
					} else {
						Assert.assertEquals(passwordErrorMsg.getText(), "Invalid MPIN");
						Log.info(passwordErrorMsg.getText());
					}
				} else if (!usrData.get("MPIN").equals("1111")) {
					waitForSpinner();
					wait.until(ExpectedConditions.elementToBeClickable(toasterMsg));
					Assert.assertEquals(toasterMsg.getText(), "Invalid credentials");
					Log.info(toasterMsg.getText());
				} else if (checkMobNumExistence(mobNumFromSheet).equalsIgnoreCase("does not exist")) {
					waitForSpinner();
					wait.until(ExpectedConditions.elementToBeClickable(toasterMsg));
					Assert.assertEquals(toasterMsg.getText(), "Invalid credentials");
					Log.info(toasterMsg.getText());
				}
			} else if (usrData.get("FORGOTPIN").equalsIgnoreCase("YES")) {
				wait.until(ExpectedConditions.visibilityOf(forgotPin));
				Log.info("clicking Forgot Pin");
				clickInvisibleElement(forgotPin);
				waitForSpinner();
				wait.until(ExpectedConditions.visibilityOf(panNumber));
				panNumber.click();
				panNumber.clear();
				Log.info("entering PAN number");
				panNumber.sendKeys(usrData.get("PAN"));
				Log.info("clicking on PROCEED button");
				wait.until(ExpectedConditions.elementToBeClickable(proceedPan));
				proceedPan.click();
				if (panValidation(usrData.get("PAN")).equalsIgnoreCase("valid")) {
					if (usrData.get("PAN")
							.equalsIgnoreCase(dbUtils.getPanNumber(getLoginMobileFromIni(mobNumFromSheet)))) {
						waitForSpinner();
						wait.until(ExpectedConditions.elementToBeClickable(otp));
						otp.click();
						otp.clear();
						Log.info("entering OTP");
						otp.sendKeys(getAuthfromIni("LoginOTP"));
						Log.info("clicking on PROCEED button");
						wait.until(ExpectedConditions.elementToBeClickable(proceedOTP));
						proceedOTP.click();
						wait.until(ExpectedConditions.elementToBeClickable(newpin));
						newpin.click();
						newpin.clear();
						Log.info("entering New PIN");
						newpin.sendKeys(usrData.get("NEWPIN"));
						wait.until(ExpectedConditions.elementToBeClickable(reenterpin));
						reenterpin.click();
						reenterpin.clear();
						Log.info("Re-entering PIN");
						reenterpin.sendKeys(usrData.get("REENTERPIN"));
						Log.info("clicking on FINISH button");
						wait.until(ExpectedConditions.elementToBeClickable(finish));
						finish.click();
						wait.until(ExpectedConditions.elementToBeClickable(toasterMsg));
						if (usrData.get("NEWPIN").equals(usrData.get("REENTERPIN"))) {
							Log.info(toasterMsg.getText());
							toastCloseButton.click();
							wait.until(ExpectedConditions.visibilityOf(mobNum));
						} else {
							Assert.assertEquals(toasterMsg.getText(), "New MPIN and Re-entered MPIN does not match.");
							Log.info(toasterMsg.getText());
							toastCloseButton.click();
							wait.until(ExpectedConditions.visibilityOf(goBackToLogin));
							clickInvisibleElement(goBackToLogin);
						}
					} else {
						waitForSpinner();
						wait.until(ExpectedConditions.elementToBeClickable(toasterMsg));
						Assert.assertEquals(toasterMsg.getText(), "incorrect POI value entered.");
						Log.info(toasterMsg.getText());
						wait.until(ExpectedConditions.visibilityOf(goBackToLogin));
						clickInvisibleElement(goBackToLogin);
					}
				} else if (panValidation(usrData.get("PAN")).equalsIgnoreCase("invalid")) {
					wait.until(ExpectedConditions.visibilityOf(panErrorMsg));
					if (usrData.get("PAN").equalsIgnoreCase("")) {
						Assert.assertEquals(panErrorMsg.getText(), "Required Field");
					} else {
						Assert.assertEquals(panErrorMsg.getText(), "Invalid Pan");
					}
					Log.info(panErrorMsg.getText());
					wait.until(ExpectedConditions.visibilityOf(goBackToLogin));
					clickInvisibleElement(goBackToLogin);
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
