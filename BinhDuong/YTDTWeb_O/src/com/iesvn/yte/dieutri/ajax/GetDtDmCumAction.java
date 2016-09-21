package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmCum;
import com.iesvn.yte.util.Utils;

public class GetDtDmCumAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listObj = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listObj =  dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DtDmCum",
						"dtdmcumNgaygiocn");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		buf.append("<list>");
		if (listObj != null) {
			for (Object obj : listObj) {
				DtDmCum cum = (DtDmCum) obj;
				buf.append("<record " 
						+ " MaSo='"	+ cum.getDtdmcumMaso() + "'"
						+ " Ma='"	+ cum.getDtdmcumMa() + "'"						
						+ " Ten='"	+ cum.getDtdmcumTen() + "'" 
						+ " NgayChinhSua='"	+ cum.getDtdmcumNgaygiocn() + "'"
						+ " DT='"	+ Utils.reFactorString (cum.getDtdmcumChon()) + "'"
						+ " DTDMCUM_GHICHU='"	+ Utils.reFactorString (cum.getDtdmcumGhichu()) + "'" 
						+ "/>");
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}
