package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmTuyenKcb;
import com.iesvn.yte.util.Utils;

public class GetTuyenKCBAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDtDmTuyenKCB = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDtDmTuyenKCB =  dtutilDelegate.findByNgayGioCN(
					Double.parseDouble(request), "DtDmTuyenKcb", "dtdmtuyenkcbNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		if (listDtDmTuyenKCB != null) {
			for (Object obj : listDtDmTuyenKCB) {
				DtDmTuyenKcb tuyenKCB = (DtDmTuyenKcb) obj;
				buf.append("<record MaSo='" + tuyenKCB.getDtdmtuyenkcbMaso() + 
						"' Ma='" + tuyenKCB.getDtdmtuyenkcbMa() + 
						"' Ten='" + tuyenKCB.getDtdmtuyenkcbTen() + 						
						"' DT='" + Utils.reFactorString(tuyenKCB.getDtdmtuyenkcbChon()) + 
						"' NgayChinhSua='" + tuyenKCB.getDtdmtuyenkcbNgaygiocn() + "' />");
			}
		}
		
		buf.append("</list>");
		return buf.toString();
	}
}


