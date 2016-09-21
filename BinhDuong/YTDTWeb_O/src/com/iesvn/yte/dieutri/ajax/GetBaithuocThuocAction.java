package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.BaithuocThuoc;

public class GetBaithuocThuocAction extends Action{
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List list = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate
					.getInstance();
			list = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request),
					"BaithuocThuoc", "baithuocthuocNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		if (list != null) {
			for (Object obj : list) {
				BaithuocThuoc pt = (BaithuocThuoc) obj;
				buf
						.append("<record " + 
								" BAITHUOCTHUOC_MASO='" + pt.getBaithuocthuocMaso() + "' " + 
								" DMBAITHUOC_MASO='" + pt.getDmbaithuocMaso().getDmbaithuocMaso() + "' " + 
								" DMTHUOC_MASO='" + pt.getDmthuocMaso().getDmthuocMaso() + "' " + 
								" NgayChinhSua='" + pt.getBaithuocthuocNgaygiocn() + "' " + 								
								" BAITHUOCTHUOC_SOLUONG='"	+ pt.getBaithuocthuocSoluong() + "' " +
								"/>");

			}
		}
		buf.append("</list>");

		return buf.toString();
	}
}
