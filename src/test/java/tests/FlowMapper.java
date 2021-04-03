package tests;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import in.novopay.platform_ui.utils.BasePage;
import in.novopay.platform_ui.utils.CommonUtils;
import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.JavaUtils;
import in.novopay.platform_ui.utils.Log;
import in.novopay.platform_ui.utils.MongoDBUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class FlowMapper {
	public AndroidDriver<MobileElement> mdriver;
	public WebDriver wdriver;
	private String sheetName = "FLOWMAPPER";
	private JavaUtils javaUtils = new JavaUtils();
	private DBUtils dbUtils = new DBUtils();
	private CommonUtils commonUtils = new CommonUtils(wdriver);
	MongoDBUtils mongoDbUtils = new MongoDBUtils();
	private Map<String, String> usrData;
	private Object obj;
	private String errMsg;
	private String testCaseID = "";
	private String stepNo = "";
	private String className = "";
	private String currentPackage = "";
	private String classNameWithPackage, workbook = "WebAppUITestData", pack;
	private Set<String> flows;
	private BasePage wBasePage = new BasePage(wdriver);

	@BeforeSuite
	public void generateIniFile() throws EncryptedDocumentException, InvalidFormatException, IOException {
		javaUtils.readConfigProperties();
	}

	@Test(dataProvider = "getData")
	public void flowMapperTest(HashMap<String, String> usrData) throws Throwable {
		this.usrData = usrData;
		Log.info("Executing --> " + usrData.get("TCID"));
		if (!usrData.get("CONTRACT").equalsIgnoreCase("-")) {
			dbUtils.modifyContract(usrData.get("CONTRACT"), javaUtils.getLoginMobileFromIni("RetailerMobNum"));
		} else if (usrData.get("CONTRACT").equalsIgnoreCase("ALL")) {
			dbUtils.insertContract(javaUtils.getLoginMobileFromIni("RetailerMobNum"));
		}
		if (usrData.get("FEATURE").equalsIgnoreCase("EKYC")) {
			dbUtils.updateAepsPartner("RBL", mobileNumFromIni());
			dbUtils.updateRBLEKYCStatus("PENDING", mobileNumFromIni());
			dbUtils.updateDmtBcAgentId("NOV2160858", mobileNumFromIni());
		} else {
			dbUtils.updateRBLEKYCStatus("APPROVED", mobileNumFromIni());
			if (usrData.get("FEATURE").equalsIgnoreCase("Money Transfer")) {
				dbUtils.updateDmtBcAgentId("NOV1000704", mobileNumFromIni());
			} else if (usrData.get("FEATURE").equalsIgnoreCase("Banking")) {
				dbUtils.updateAepsPartner(usrData.get("CONTRACT"), mobileNumFromIni());
				commonUtils.verifyAndInsertValueInOrgAttribute("RBL_AEPS_TERMINAL_ID","567765667126757");
				commonUtils.verifyAndInsertValueInOrgAttribute("RBL_AEPS_AGENT_ID","NOV112200");
				commonUtils.verifyAndInsertValueInOrgAttribute("RBL_AEPS_DEVICE_ID","UP000102");
				commonUtils.verifyAndInsertValueInOrgAttribute("RBL_AEPS_AGENT_PASSWORD","40bd001563085fc35165329ea1ff5c5ecbdbbeef");
			} else if (usrData.get("CONTRACT").equalsIgnoreCase("CMS")) {
				javaUtils.cmsDetailsFromIni("StoreCmsBiller", usrData.get("FEATURE"));
			} else if (usrData.get("CONTRACT").equalsIgnoreCase("BILLPAY")) {
				commonUtils.verifyAndInsertValueInOrgAttribute("BILL_AVENUE_AGENT_ID","123456");
			}
		}
		

		javaUtils.getWalletFromIni("StoreWallet", usrData.get("WALLET"));

		for (String flowTestID : flows) {
			if ((!usrData.get(flowTestID).equalsIgnoreCase("SKIP")) && (!usrData.get(flowTestID).isEmpty())) {
				testCaseID = usrData.get(flowTestID);
				currentPackage = getClass().getPackage().getName();
				className = testCaseID.split("_")[0];

				classNameWithPackage = currentPackage + ".api." + className;
				Class<?> flow = null;
				stepNo = flowTestID;
				try {
					flow = Class.forName(classNameWithPackage);
					pack = "api";
				} catch (ClassNotFoundException e) {
					classNameWithPackage = currentPackage + ".ui." + className;
					flow = Class.forName(classNameWithPackage);
					pack = "ui";
				}

				String pattern = Character.toLowerCase((className + "Test").charAt(0))
						+ (className + "Test").substring(1, (className).length());
				Pattern r = Pattern.compile(pattern);

				try {
					obj = flow.getDeclaredConstructor().newInstance();
					Method[] method = obj.getClass().getDeclaredMethods();
					for (int i = 0; i < method.length; i++) {
						String message = method[i].toString();
						Matcher m = r.matcher(message);
						if (m.find()) {
							String sheetname = obj.getClass().getDeclaredField("sheetname").get(obj).toString();
							String workbook = obj.getClass().getDeclaredField("workbook").get(obj).toString();
							// write if condition to switch if api class is
							if (pack.equals("api")) {
								HashMap<String, String> data = javaUtils.readExcelData(workbook, sheetname,
										usrData.get(flowTestID));
								method[i].invoke(obj, data);
							} else {
								HashMap<String, String> data = javaUtils.readExcelData(workbook, sheetname,
										usrData.get(flowTestID));

								Field webDriver = obj.getClass().getDeclaredField("wdriver");
								webDriver.set(obj, wdriver);
								method[i].invoke(obj, data);
								wdriver = (WebDriver) webDriver.get(obj);
							}

						}
					}

				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					wdriver.navigate().refresh();
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (WebDriverException e) {
					wdriver.navigate().refresh();
					e.printStackTrace();
				} catch (Exception e) {
					wdriver.navigate().refresh();
					throw e.getCause();
				}
			}
		}
	}

	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, InvalidFormatException, IOException {
		Object[][] data = javaUtils.returnAllUniqueValuesInMap(workbook, sheetName, "no-check");
		if (data.length != 0) {

			@SuppressWarnings("unchecked")
			HashMap<String, String> datamap = (HashMap<String, String>) data[0][0];
			flows = new TreeSet<>(datamap.keySet());
			flows = new TreeSet<>(datamap.keySet().stream().filter(s -> s.toLowerCase().startsWith("step"))
					.collect(Collectors.toSet()));
		}
		return data;
	}

	@AfterClass
	public void killDriver() {

		if (wdriver != null) {
			wBasePage.closeBrowser();
		}
	}

	// STORING EXECUTION RESULTS IN EXCEL
	@AfterMethod
	public void result(ITestResult result) throws InvalidFormatException, IOException, ClassNotFoundException {

		String failureReason = "";
		String testStartTime = javaUtils.getTestExcutionTime(result.getStartMillis());
		String testEndTime = javaUtils.getTestExcutionTime(result.getEndMillis());
		wBasePage.captureScreenshotOnFailedTest(result, testCaseID);
		if (!result.isSuccess()) {
			failureReason = errMsg;
			failureReason = stepNo + ": " + testCaseID + ": " + result.getThrowable() + "";
		}
		String[] execeutionDtls = { usrData.get("TCID"), usrData.get("CONTRACT"), usrData.get("FEATURE"),
				usrData.get("DESCRIPTION"), javaUtils.getExecutionResultStatus(result.getStatus()), failureReason,
				testStartTime, testEndTime };
		javaUtils.writeExecutionStatusToExcel(execeutionDtls);

		if (!usrData.get("CONTRACT").equalsIgnoreCase("-")) {
			System.out.println("Inserting all contracts");
			dbUtils.insertContract(javaUtils.getLoginMobileFromIni("RetailerMobNum"));
		}
	}

	// Get mobile number from Ini file
	public String mobileNumFromIni() {
		return dbUtils.getLoginMobileFromIni("RetailerMobNum");
	}
}