package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
import com.iesvn.yte.util.Utils;

public class CapNhatHSTTKAction extends Action {
	public String performAction(String request) throws Exception {
		List lstTiepDon = null;
		
		try {
			TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
			lstTiepDon = tdDelegate.findAll();
		} catch (Exception ex) {

		}
		HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
		if (lstTiepDon != null) {
			for (Object obj : lstTiepDon) {
				TiepDon td = (TiepDon)obj;
				if ("TE".equals(td.getDoituongMa(true).getDmdoituongMa())){
					HsThtoank hsttk = new HsThtoank();
					hsttk.setTiepdonMa(td);			
					tinhToanChoHSTKKham(hsttk,td);
					Utils.setInforForHsThToan(hsttk);
				
				}
			}
		}
				
		return "";
	}
	
	private void tinhToanChoHSTKKham(HsThtoank hsttk, TiepDon tiepDon){
		HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(tiepDon);
		hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
		Utils.setInforForHsThToan(hsttk);	
		
	}
}


