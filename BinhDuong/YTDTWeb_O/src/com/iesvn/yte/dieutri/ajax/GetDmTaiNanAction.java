package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmTaiNan;
import com.iesvn.yte.util.Utils;

public class GetDmTaiNanAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listTaiNan = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listTaiNan = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DmTaiNan", "dmtainanNgaygiocn");
	} catch (Exception ex) {

		}

		buf.append("<list>");
		if (listTaiNan != null) {
			for (Object obj : listTaiNan) {
				DmTaiNan pt = (DmTaiNan)obj;
				buf.append("<record " +
						"MaSo='" + pt.getDmtainanMaso() +
						"' Ma='" + pt.getDmtainanMa() +
						"' Ten='"	+ pt.getDmtainanTen() + 
						"' NgayChinhSua='"	+ pt.getDmtainanNgaygiocn() +
						
						"' DT='" + Utils.reFactorString (pt.getDmtainanDt()) + 
						"' QL='" + Utils.reFactorString (pt.getDmtainanQl()) + 
						"' DP='" +Utils.reFactorString ( pt.getDmtainanDp()) +
						
						"' />");
				
				
			}
		}
		buf.append("</list>");
	
		return buf.toString();
	}
}


