package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmLyDoCv;
import com.iesvn.yte.util.Utils;

public class GetDmLyDoCVAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDmLyDoCv = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDmLyDoCv = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DtDmLyDoCv", "dtdmlydocvNgaygiocn");
	
		} catch (Exception ex) {

		}

		buf.append("<list>");
		
		if (listDmLyDoCv != null) {
			for (Object obj : listDmLyDoCv) {
				DtDmLyDoCv lydocv = (DtDmLyDoCv) obj;
				buf.append("<record " +
						"MaSo='" + lydocv.getDtdmlydocvMaso() + 
						"' Ma='" + lydocv.getDtdmlydocvMa() + 
						"' Ten='"	+ lydocv.getDtdmlydocvTen() + 
						"' NgayChinhSua='"	+ lydocv.getDtdmlydocvNgaygiocn() + 
						"' DT='"	+ Utils.reFactorString (lydocv.getDtdmlydocvChon()) + 
						"' />");
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}


