package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmPhong;
import com.iesvn.yte.util.Utils;


public class GetDtDmPhongAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDtDmPhong = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDtDmPhong =  dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DtDmPhong","dtdmpNgaygiocn");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		buf.append("<list>");
		if (listDtDmPhong != null) {
			for (Object obj : listDtDmPhong) {
				DtDmPhong dmPhong = (DtDmPhong) obj;
				buf.append("<record " +
						"MaSo='" + dmPhong.getDtdmpMaso() + 
						"' Ma='" + dmPhong.getDtdmpMa() + 
						"' Ten='"	+ dmPhong.getDtdmpTen() + 
						"' NgayChinhSua='"	+ dmPhong.getDtdmpNgaygiocn() + 
						"' DT='"	+ Utils.reFactorString (dmPhong.getDtdmpChon()) +
						"' />");
				
				
				
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}


