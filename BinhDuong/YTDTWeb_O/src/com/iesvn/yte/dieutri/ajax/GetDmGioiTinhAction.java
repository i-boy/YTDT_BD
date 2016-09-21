package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmGioiTinh;
import com.iesvn.yte.util.Utils;

public class GetDmGioiTinhAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listObj = null;
		
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listObj = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DmGioiTinh", "dmgtNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listObj != null) {
			for (Object obj : listObj) {
				DmGioiTinh dmgt = (DmGioiTinh) obj;
				buf.append("<record " 
						+ " MaSo='"	+ dmgt.getDmgtMaso() + "'"
						+ " Ma='"	+ dmgt.getDmgtMa() + "'"
						+ " Ten='"	+ dmgt.getDmgtTen() + "'" 
						+ " NgayChinhSua='"	+ Utils.reFactorString(dmgt.getDmgtNgaygiocn()) + "'"
						+ " DMGT_DEFA='"	+ Utils.reFactorString(dmgt.getDmgtDefa()) + "'"
						+ " DMGT_GHICHU='"	+ Utils.reFactorString(dmgt.getDmgtGhichu()) + "'"
						+ " DT='"	+ Utils.reFactorString(dmgt.getDmgtChon()) + "'"
						+ "/>");
			}
		}
		buf.append("</list>");
		return buf.toString();
	}

}
