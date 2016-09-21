package com.iesvn.yte.util;

import org.apache.log4j.Logger;

import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.entity.TonKho;

public class TonKhoUtil {

	private static Logger log = Logger.getLogger(TonKhoUtil.class);

	public static TonKho getTonKhoHienTai(String sMaLK, Integer maKhoa) {
		TonKho tonkho = new TonKho();
		try {
			TonKhoDelegate tkDel = TonKhoDelegate.getInstance();
			tonkho = tkDel.getTonKhoHienTai(sMaLK, maKhoa);
		} catch (Exception ex) {
			log.info("****** ERROR! " + ex.toString());
		}
		return tonkho;
	}

}
