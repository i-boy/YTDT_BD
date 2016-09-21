package com.iesvn.yte.dieutri.ajax;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;

public class GetMaTDFromGoiBNAction extends Action {
	public String performAction(String request) throws Exception {
		String maBanKham = request;
		String maTiepDon = "";
		try {
			ThamKhamDelegate tkDele = ThamKhamDelegate.getInstance();
			maTiepDon = tkDele.getMaTiepDonByGoiBN(maBanKham);
		} catch (Exception ex) {

		}
		return String.format("<result>%s</result>", maTiepDon); 
	}
}


