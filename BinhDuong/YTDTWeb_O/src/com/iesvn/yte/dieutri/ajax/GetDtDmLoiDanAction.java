package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmLoiDan;
import com.iesvn.yte.util.Utils;

public class GetDtDmLoiDanAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List list = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			list = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DtDmLoiDan", "dtdmldNgaygiocn");
	} catch (Exception ex) {

		}

		buf.append("<list>");
		if (list != null) {
			for (Object obj : list) {
				DtDmLoiDan pt = (DtDmLoiDan)obj;
				buf.append("<record " 
						+ " MaSo='"	+ pt.getDtdmldMaso() + "'"
						+ " Ma='"	+ pt.getDtdmldMa() + "'"						
						+ " Ten='"	+ pt.getDtdmldTen() + "'" 
						+ " NgayChinhSua='"	+ pt.getDtdmldNgaygiocn() + "'"
						+ " DT='"	+ Utils.reFactorString (pt.getDtdmldChon()) + "'"
						+ "/>");
				
				
			}
		}
		buf.append("</list>");
	
		return buf.toString();
	}
}


