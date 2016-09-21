package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DmBaiThuoc;
import com.iesvn.yte.dieutri.entity.DtDmLoiDan;
import com.iesvn.yte.util.Utils;

public class GetDmBaiThuocAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List list = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate
					.getInstance();
			list = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request),
					"DmBaiThuoc", "dmbaithuocNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		if (list != null) {
			for (Object obj : list) {
				DmBaiThuoc pt = (DmBaiThuoc) obj;
				buf
						.append("<record " + 
								" MaSo='" + pt.getDmbaithuocMaso() + "' " + 
								" Ma='" + pt.getDmbaithuocMa() + "' " + 
								" Ten='" + pt.getDmbaithuocTen() + "' " + 
								" NgayChinhSua='" + pt.getDmbaithuocNgaygiocn() + "' " + 
								" DT='"	+ Utils.reFactorString(pt.getDmbaithuocDt())+ "' " +
								" QL='"	+ Utils.reFactorString(pt.getDmbaithuocQl())+ "' " +
								" DP='"	+ Utils.reFactorString(pt.getDmbaithuocDp())+ "' " +
								" DMBAITHUOC_GHICHU='"	+ pt.getDmbaithuocGhichu()+ "' " +
								"/>");

			}
		}
		buf.append("</list>");

		return buf.toString();
	}
}
