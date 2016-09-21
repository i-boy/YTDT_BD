package com.iesvn.yte.util;

import java.util.Date;

import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.Hsba;

public class HsttThreadUtil extends Thread {
	
	private Hsba hoSoBenhAn;
	
	public Hsba getHoSoBenhAn() {
		return hoSoBenhAn;
	}

	public void setHoSoBenhAn(Hsba hoSoBenhAn) {
		this.hoSoBenhAn = hoSoBenhAn;
	}

	public void run() {
		System.out.println("HsttThreadUtil starting -- " + new Date());
		
		HsThtoanDelegate hsThtoanDelegate = HsThtoanDelegate.getInstance();
		HsThtoan hsThtoan = hsThtoanDelegate.findBySovaovien(hoSoBenhAn.getHsbaSovaovien());
		if (hsThtoan != null) {
			hsThtoanDelegate.remove(hsThtoan);
		}

		hsThtoan = new HsThtoan();
		hsThtoan.setHsbaSovaovien(hoSoBenhAn);
		HoSoThanhToanUtil hsthtoanUtil = new HoSoThanhToanUtil(hoSoBenhAn);
		hsthtoanUtil.tinhToanChoHSTT(hsThtoan);
		hsThtoan.setHsthtoanKhoa(hoSoBenhAn.getHsbaKhoadangdt());
		Utils.setInforForHsThToan(hsThtoan);
		hsThtoanDelegate.create(hsThtoan);
		
		System.out.println("HsttThreadUtil stopping -- " + new Date());
	}
	
}