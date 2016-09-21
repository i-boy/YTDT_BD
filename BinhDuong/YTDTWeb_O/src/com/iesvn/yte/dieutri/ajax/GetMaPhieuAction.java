package com.iesvn.yte.dieutri.ajax;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.PhieuNhapKhoDelegate;

public class GetMaPhieuAction extends Action {

	public String performAction(String request) throws Exception {
		PhieuNhapKhoDelegate pnkDelegate = PhieuNhapKhoDelegate.getInstance();
		//PhieuNhapKhoWSService pnkService = new PhieuNhapKhoWSServiceLocator();
		//PhieuNhapKhoWS pnkWS = pnkService.getPhieuNhapKhoWSPort();
		
		return String.format("<result>%s</result>", pnkDelegate.getMaPhieu());
	}
}


