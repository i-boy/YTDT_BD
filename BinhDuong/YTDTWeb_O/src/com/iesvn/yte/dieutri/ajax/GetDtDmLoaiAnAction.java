package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmLoaiAn;
import com.iesvn.yte.util.Utils;


public class GetDtDmLoaiAnAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDtDmLoaiAn = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDtDmLoaiAn =  dtutilDelegate.findByNgayGioCN(Double
					.parseDouble(request), "DtDmLoaiAn",
					"dtdmlaNgaygiocn");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		buf.append("<list>");
		if (listDtDmLoaiAn != null) {
			for (Object obj : listDtDmLoaiAn) {
				DtDmLoaiAn dmLoaiAn = (DtDmLoaiAn) obj;
				buf.append("<record " +
						"MaSo='" + dmLoaiAn.getDtdmlaMaso() + 
						"' Ma='" + dmLoaiAn.getDtdmlaMa() + 
						"' Ten='"	+ dmLoaiAn.getDtdmlaTen() + 
						"' NgayChinhSua='"	+ dmLoaiAn.getDtdmlaNgaygiocn() + 
						"' DT='"	+ Utils.reFactorString (dmLoaiAn.getDtdmlaChon()) +
						"' />");
				
				
				
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}


