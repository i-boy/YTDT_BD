package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmDienMien;
import com.iesvn.yte.util.Utils;

public class GetDtDmDienMienAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listObj = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listObj =  dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DtDmDienMien",
						"dtdmdienmienNgaygiocn");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		buf.append("<list>");
		if (listObj != null) {
			for (Object obj : listObj) {
				DtDmDienMien dienMien = (DtDmDienMien) obj;
				buf.append("<record " 
						+ " MaSo='"	+ dienMien.getDtdmdienmienMaso() + "'"
						+ " Ma='"	+ dienMien.getDtdmdienmienMa() + "'"						
						+ " Ten='"	+ dienMien.getDtdmdienmienTen() + "'" 
						+ " NgayChinhSua='"	+ dienMien.getDtdmdienmienNgaygiocn() + "'"
						+ " DT='"	+ Utils.reFactorString (dienMien.getDtdmdienmienChon()) + "'"
						+ "/>");
			}
		}
		buf.append("</list>");
		return buf.toString();
	}

}
