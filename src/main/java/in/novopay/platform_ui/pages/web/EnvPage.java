package in.novopay.platform_ui.pages.web;

import java.util.Map;

import in.novopay.platform_ui.utils.DBUtils;
import in.novopay.platform_ui.utils.JavaUtils;

public class EnvPage extends JavaUtils {

	DBUtils dbUtils = new DBUtils();

	public void env(Map<String, String> usrData) throws ClassNotFoundException {

		changeEnvURLfromIni(usrData.get("ENV"));
		String amount = usrData.get("AMOUNT");

		if (usrData.get("UPDATERETAILERBALANCE").equals("YES")) {
			updateBalance("retailer", amount);
		}
		if (usrData.get("UPDATECASHOUTBALANCE").equals("YES")) {
			updateBalance("cashout", amount);
		}
		if (usrData.get("UPDATEMERCHANTBALANCE").equals("YES")) {
			updateBalance("merchant", amount);
		}
	}

	public void updateBalance(String wallet, String amount) throws ClassNotFoundException {
		dbUtils.updateWalletBalance(getLoginMobileFromIni("RetailerMobNum"), wallet, amount);
	}
}