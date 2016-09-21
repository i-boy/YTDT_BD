package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmCapCuuPhien;
import com.iesvn.yte.util.Utils;

public class GetCapCuuPhienAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDtDmCCPhien = null;
		
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDtDmCCPhien = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DtDmCapCuuPhien", "dtdmccpNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		if (listDtDmCCPhien != null) {
			for (Object obj : listDtDmCCPhien) {
				DtDmCapCuuPhien ccPhien = (DtDmCapCuuPhien)obj; 
			
				buf.append("<record " 
						+ " MaSo='"	+ ccPhien.getDtdmccpMaso() + "'"
						+ " Ma='"	+ ccPhien.getDtdmccpMa() + "'"						
						+ " Ten='"	+ ccPhien.getDtdmccpTen() + "'" 
						+ " NgayChinhSua='"	+ ccPhien.getDtdmccpNgaygiocn() + "'"
						
						+ " DT='"	+ Utils.reFactorString (ccPhien.getDtdmccpChon()) + "'" 
						
						+ "/>");
				
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}


