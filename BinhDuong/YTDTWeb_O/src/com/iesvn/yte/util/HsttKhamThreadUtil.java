package com.iesvn.yte.util;


import java.util.Date;

import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.TiepDon;

public class HsttKhamThreadUtil extends Thread {
	
	private TiepDon tiepDon;
	
	public TiepDon getTiepDon() {
		return tiepDon;
	}

	public void setTiepDon(TiepDon tiepDon) {
		this.tiepDon = tiepDon;
	}

	public void run() {
		System.out.println("HsttKhamThreadUtil starting -- " + new Date());
		
		HsThtoank hsttk = new HsThtoank();
		hsttk.setTiepdonMa(tiepDon);			
		HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(tiepDon);
		hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
		Utils.setInforForHsThToan(hsttk);

		System.out.println("HsttKhamThreadUtil stopping -- " + new Date());
	}
	
}