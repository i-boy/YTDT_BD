package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmChiDan;
import com.iesvn.yte.util.Utils;

public class GetDtDmChiDanAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDtDmChiDan = null;
		
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDtDmChiDan = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DtDmChiDan", "dtdmchidanNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listDtDmChiDan != null) {
			for (Object obj : listDtDmChiDan) {
				DtDmChiDan dtdmcd = (DtDmChiDan)obj;
				buf.append("<record " +
						"MaSo='" + dtdmcd.getDtdmchidanMaso() + 
						"' Ma='" + dtdmcd.getDtdmchidanMa() + 
						"' Ten='"	+ dtdmcd.getDtdmchidanTen() + 
						"' DTDMCHIDAN_MAPHU='"	+ Utils.reFactorString(dtdmcd.getDtdmchidanMaphu()) + 
						"' NgayChinhSua='"	+ Utils.reFactorString (dtdmcd.getDtdmchidanNgaygiocn())+  						
						"' DT='" + Utils.reFactorString (dtdmcd.getDtdmchidanChon()) + 						
						"' />");
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}
