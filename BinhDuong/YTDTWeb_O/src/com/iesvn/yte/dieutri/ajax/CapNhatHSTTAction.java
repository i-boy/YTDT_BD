package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.util.HoSoThanhToanUtil;
import com.iesvn.yte.util.Utils;

public class CapNhatHSTTAction extends Action {
	public String performAction(String request) throws Exception {
		List lstHsba = null;
		
		try {
			HsbaDelegate hsbaDele = HsbaDelegate.getInstance();
			lstHsba = hsbaDele.findAll();
		} catch (Exception ex) {

		}
		HsThtoanDelegate hsttDelegate = HsThtoanDelegate.getInstance();

		if (lstHsba != null) {
			for (Object obj : lstHsba) {
				Hsba hsba = (Hsba)obj;
				if ("TE".equals(hsba.getDoituongMa(true).getDmdoituongMa())){
					HsThtoan hstt = hsttDelegate.findBySovaovien(hsba.getHsbaSovaovien());
					if (hstt == null){
						//hsttDelegate.remove(hstt);
						continue;
					}			
								
					tinhToanChoHSTT(hstt, hsba);
					Utils.setInforForHsThToan(hstt);
					hsttDelegate.edit(hstt);
				
				}
			}
		}
				
		return "";
	}
	
	private void tinhToanChoHSTT(HsThtoan hstt, Hsba hsba) {
		HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hsba);
		hsthtoanUtil.tinhToanChoHSTT(hstt);

	}
}


