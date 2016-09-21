package com.iesvn.yte.dieutri.ajax;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;

public class XoaSoThuTuFromGoiBNAction extends Action {
	public String performAction(String request) throws Exception {
		String maTiepDon = request;
		
		try {
			ThamKhamDelegate tkDele = ThamKhamDelegate.getInstance();
			maTiepDon = tkDele.xoaSoThuThuBNKham(maTiepDon);
		} catch (Exception ex) {

		}
		return String.format("<result>%s</result>", maTiepDon); 
	}
}


