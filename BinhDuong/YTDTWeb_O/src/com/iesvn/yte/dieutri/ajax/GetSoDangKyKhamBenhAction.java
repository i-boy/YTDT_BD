package com.iesvn.yte.dieutri.ajax;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;

public class GetSoDangKyKhamBenhAction extends Action {

	@Override
    public String performAction(String request) {
		String stt = "0";
		DieuTriUtilDelegate dtDelegate = DieuTriUtilDelegate.getInstance();
		try {
			stt = dtDelegate.getSoKhamBenh(request).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "<result>" + stt + "</result>";
	}
}
