package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmBuong;
import com.iesvn.yte.dieutri.entity.DtDmLoaiAn2;
import com.iesvn.yte.util.Utils;


public class GetDtDmBuongAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDtDmBuong = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDtDmBuong =  dtutilDelegate.findByNgayGioCN(Double
					.parseDouble(request), "DtDmBuong",
					"dtdmbNgaygiocn");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		buf.append("<list>");
		if (listDtDmBuong != null) {
			for (Object obj : listDtDmBuong) {
				DtDmBuong dmBuong = (DtDmBuong) obj;
				buf.append("<record " +
						"MaSo='" + dmBuong.getDtdmbMaso() + 
						"' MaSo2='" + dmBuong.getDmkhoaMaso().getDmkhoaMaso() +
						"' Ma='" + dmBuong.getDtdmbMa() + 
						"' Ten='"	+ dmBuong.getDtdmbTen() + 
						"' NgayChinhSua='"	+ dmBuong.getDtdmbNgaygiocn() + 
						"' DT='"	+ Utils.reFactorString (dmBuong.getDtdmbChon()) +
						"' />");
				
				
				
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}


