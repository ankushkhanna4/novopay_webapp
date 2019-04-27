package in.novopay.platform_ui.pages.web;

import java.util.Map;

import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.JavaUtils;

public class EnvPage extends JavaUtils {

	DBUtils dbUtils = new DBUtils();

	public void env(Map<String, String> usrData) throws ClassNotFoundException {

		changeEnvURLfromIni(usrData.get("ENV"));
		String partner = usrData.get("PARTNER");
		String amount = usrData.get("AMOUNT");

		if (usrData.get("UPDATERETAILERBALANCE").equals("YES")) {
			updateBalance(partner,amount,"retailer");
		}
		if (usrData.get("UPDATECASHOUTBALANCE").equals("YES")) {
			updateBalance(partner,amount,"cashout");
		}
		if (usrData.get("UPDATEMERCHANTBALANCE").equals("YES")) {
			updateBalance(partner,amount,"merchant");
		}
	}

	public void updateBalance(String partner, String amount, String wallet) throws ClassNotFoundException {
		if (partner.equalsIgnoreCase("ALL")) {
			dbUtils.updateWalletBalance(getLoginMobileFromIni("RBLRetailerMobNum"), wallet, amount);
			dbUtils.updateWalletBalance(getLoginMobileFromIni("AXISRetailerMobNum"), wallet, amount);
			dbUtils.updateWalletBalance(getLoginMobileFromIni("FINORetailerMobNum"), wallet, amount);
			dbUtils.updateWalletBalance(getLoginMobileFromIni("YBLRetailerMobNum"), wallet, amount);
		} else {
			dbUtils.updateWalletBalance(getLoginMobileFromIni(partner.toUpperCase() + "RetailerMobNum"), "retailer",
					amount);
		}
	}
}