package tests.ui;

import java.awt.AWTException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import in.novopay.platform_ui.pages.web.AxisStatusEnquiryPage;
import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.JavaUtils;
import in.novopay.platform_ui.utils.ServerUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AxisStatusEnquiryTest {
	String featureName = "Axis Status Enquiry page";
	public AndroidDriver<MobileElement> mdriver;
	public WebDriver wdriver;
	private BasePage mBasePage = new BasePage(wdriver);
	private AxisStatusEnquiryPage wAxisStatusEnquiryPage;
	private Map<String, String> usrData;
	public String sheetname = "AxisStatusEnquiryPage", workbook = "WebAppUITestData";
	ServerUtils srvUtils = new ServerUtils();
	private JavaUtils javaUtils = new JavaUtils();

	// Start adding all the page objects below this line
	@BeforeSuite
	public void generateIniFile() throws EncryptedDocumentException, InvalidFormatException, IOException {
		javaUtils.readConfigProperties();
	}

	@Test(dataProvider = "getData")
	public void axisStatusEnquiryTest(HashMap<String, String> usrData)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException {
		this.usrData = usrData;
		String testOn = usrData.get("TESTON");
		if (testOn.toUpperCase().equals("MOBILE")) {
			System.out.println("LAUNCHING THE MOBILE APP FOR FLOW : " + usrData.get("TCID"));
			if (mdriver == null) {
				try {
					mdriver = mBasePage.launchApp(usrData.get("DEVICE"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else if (testOn.toUpperCase().equals("WEB")) {
			if (wdriver == null) {
				System.out.println("LAUNCHING THE WEB APP FOR FLOW : " + usrData.get("TCID"));
				wdriver = mBasePage.launchBrowser();
			} else if ((wdriver != null)) {
				System.out.println("LAUNCHING THE WEB APP FOR FLOW : " + usrData.get("TCID"));
			}
			
			HashMap<String, String> batchFileConfig = javaUtils.readSectionFromIni("axisimpsstatusenquiry");
			if (!usrData.get("KEY").isEmpty()) {
				srvUtils.uploadFile(batchFileConfig, usrData.get("KEY"));
			}
			
			wAxisStatusEnquiryPage = new AxisStatusEnquiryPage(wdriver);
			wAxisStatusEnquiryPage.axisStatusEnquiry(usrData);

		}

		// if(usrData.get("Status").equalsIgnoreCase("success")){
		// batchFileConfig.put("batch.execution.summary","response string
		// :00\\|Success\\|"+paymentRefCode);
		// } else if(usrData.get("Status").equalsIgnoreCase("fail")){
		// batchFileConfig.put("batch.execution.summary","response string
		// :92\\|Success\\|"+paymentRefCode);
		// } else if(usrData.get("Status").equalsIgnoreCase("unknown")){
		// batchFileConfig.put("batch.execution.summary","response string
		// :T1\\|Success\\|"+paymentRefCode);
		// }

	}

	@AfterClass
	public void killDriver() {

		if (wdriver != null) {
			mBasePage.closeBrowser();
		}
	}

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, InvalidFormatException, IOException {

		return mBasePage.returnAllUniqueValuesInMap(workbook, sheetname, "no-check");
	}

	// STORING EXECUTION RESULTS IN EXCEL
	@AfterMethod
	public void result(ITestResult result) throws InvalidFormatException, IOException {

		String failureReason = "";

		if (!result.isSuccess()) {
			failureReason = result.getThrowable() + "";
		}
		String[] execeutionDtls = { JavaUtils.configProperties.get("buildNumber"), featureName, usrData.get("TCID"),
				usrData.get("DESCRIPTION"), javaUtils.getExecutionResultStatus(result.getStatus()), failureReason };
		javaUtils.writeExecutionStatusToExcel(execeutionDtls);
	}

}
