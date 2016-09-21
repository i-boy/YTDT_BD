package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmKetQuaDieuTri;

public class GetKQDieuTriAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDtDmKetQua = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDtDmKetQua = dtutilDelegate.findByNgayGioCN(
					Double.parseDouble(request), "DmKetQuaDieuTri", "dmkqdtNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listDtDmKetQua != null) {
			for (Object obj : listDtDmKetQua) {
				DmKetQuaDieuTri ketqua = (DmKetQuaDieuTri) obj;
				buf.append("<record MaSo='" + ketqua.getDmkqdtMaso() + 
						"' Ma='" + ketqua.getDmkqdtMa() + 
						"' Ten='" + ketqua.getDmkqdtTen() + 
						"' DMKQDT_GHICHU='" + ketqua.getDmkqdtGhichu() + 
						"' QL='" + ketqua.getDmkqdtQl() + 
						"' DT='" + ketqua.getDmkqdtDt() + 
						"' DP='" + ketqua.getDmkqdtDp() + 
						"' NgayChinhSua='" + ketqua.getDmkqdtNgaygiocn() + "' />");
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}


