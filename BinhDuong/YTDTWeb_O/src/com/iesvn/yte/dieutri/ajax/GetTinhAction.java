package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.util.Utils;

public class GetTinhAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDmTinh = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDmTinh =  dtutilDelegate.findByNgayGioCN(
					Double.parseDouble(request), "DmTinh", "dmtinhNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		if (listDmTinh != null) {
			for (Object obj : listDmTinh) {
				DmTinh tinh = (DmTinh) obj;
				buf.append("<record MaSo='" + tinh.getDmtinhMaso() + 
						"' Ma='" + tinh.getDmtinhMa() + 
						"' Ten='" + tinh.getDmtinhTen() + 
						"' DMHUYEN_MA='" + Utils.reFactorString(tinh.getDmhuyenMa()) + 
						"' MaTinhBHYT='" + Utils.reFactorString(tinh.getDmtinhBHYT()) + 
						
						"' DMXA_MA='" + Utils.reFactorString(tinh.getDmxaMa()) + 
						"' DMTINH_DEFA='" + Utils.reFactorString(tinh.getDmtinhDefa()) + 
						"' DT='" + Utils.reFactorString(tinh.getDmtinhChon()) + 
						"' NgayChinhSua='" + tinh.getDmtinhNgaygiocn() + "' />");
			}
		}
		
		buf.append("</list>");
		return buf.toString();
	}
}


