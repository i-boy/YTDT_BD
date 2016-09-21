package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmLoaiThucPham;
import com.iesvn.yte.dieutri.entity.DtDmNhaSxSpdd;


public class GetDtDmLoaiTpAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDtDmLoaiTp = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDtDmLoaiTp =  dtutilDelegate.findByNgayGioCN(Double
					.parseDouble(request), "DtDmLoaiThucPham",
					"dtdmltpNgaygiocn");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		buf.append("<list>");
		if (listDtDmLoaiTp != null) {
			for (Object obj : listDtDmLoaiTp) {
				DtDmLoaiThucPham dmLoaiTp = (DtDmLoaiThucPham) obj;
				buf.append("<record " +
						"MaSo='" + dmLoaiTp.getDtdmltpMaso() + 
						"' Ma='" + dmLoaiTp.getDtdmltpMa() + 
						"' Ten='"	+ dmLoaiTp.getDtdmltpTen() + 
						"' NgayChinhSua='"	+ dmLoaiTp.getDtdmltpNgaygiocn() + 
						"' DT='1' />");												
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}


