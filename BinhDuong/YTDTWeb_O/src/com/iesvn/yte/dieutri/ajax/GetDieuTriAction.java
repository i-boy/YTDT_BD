package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmDieuTri;
import com.iesvn.yte.util.Utils;

public class GetDieuTriAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDieutri = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDieutri = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DmDieuTri", "dmdieutriNgaygiocn");
		
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listDieutri != null) {
			for (Object obj : listDieutri) {
				DmDieuTri dieutri = (DmDieuTri) obj;
				buf.append("<record " +
						" MaSo='" + dieutri.getDmdieutriMaso() + 
						"' Ma='"	+ dieutri.getDmdieutriMa() + 
						"' Ten='" + dieutri.getDmdieutriTen() + 
						"' DMDIEUTRI_DIENGIAI2='"	+  Utils.reFactorString (dieutri.getDmdieutriDiengiai2()) + 
						"' DMDIEUTRI_DEFA='"	+  Utils.reFactorString (dieutri.getDmdieutriDefa()) + 
						"' NgayChinhSua='"	+ dieutri.getDmdieutriNgaygiocn() + 
						"' DT='" + Utils.reFactorString (dieutri.getDmdieutriDt()) + 
						"' QL='" + Utils.reFactorString (dieutri.getDmdieutriQl()) + 
						"' DP='" +Utils.reFactorString ( dieutri.getDmdieutriDp()) + 
						"' />");
				
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}


