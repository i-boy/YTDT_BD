package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.util.Utils;

public class GetHuyenAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDmHuyen = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDmHuyen = dtutilDelegate.findByNgayGioCN(
					Double.parseDouble(request), "DmHuyen", "dmhuyenNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listDmHuyen != null) {
			for (Object obj : listDmHuyen) {
				DmHuyen huyen = (DmHuyen) obj;
				String ten = huyen.getDmhuyenTen();
				ten = Utils.findAndreplace(ten);
				String tinhMa = "";
				if (huyen.getDmtinhMaso() != null) {
					tinhMa = huyen.getDmtinhMaso().getDmtinhMaso().toString();
				}
				
				buf.append("<record MaSo='" + huyen.getDmhuyenMaso()
						+ "' Ma='" + huyen.getDmhuyenMa() 
						+ "' DMTINH_MASO='" + tinhMa 
						+ "' Ten='" + ten 
						+ "' DMHUYEN_DEFA='" + Utils.reFactorString(huyen.getDmhuyenDefa()) 
						+ "' DT='" + Utils.reFactorString(huyen.getDmhuyenChon())
						+ "' NgayChinhSua='" + huyen.getDmhuyenNgaygiocn() + "' />");
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}


