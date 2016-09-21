package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanvienKhoa;
import com.iesvn.yte.util.Utils;

public class GetDtDmNhanVienKhoaAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listObj = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listObj =  dtutilDelegate.findByNgayGioCN(Double
					.parseDouble(request), "DtDmNhanvienKhoa",
					"dtdmnhanvienKhoaNgaygiocn");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		buf.append("<list>");
		
		if (listObj != null) {
			for (Object obj : listObj) {
				DtDmNhanvienKhoa nvKhoa = (DtDmNhanvienKhoa) obj;
				buf.append("<record " +
						"MaSo='" + nvKhoa.getDtdmnhanvienkhoaMaso() + 
						"' DTDMNHANVIEN_MASO='"+ nvKhoa.getDtdmnhanvienMaso().getDtdmnhanvienMaso() + 
						"' DMKHOA_MASO='"+ nvKhoa.getDmkhoaMaso().getDmkhoaMaso() + 
						"' NgayChinhSua='"+ Utils.reFactorString (nvKhoa.getDtdmnhanvienKhoaNgaygiocn()) + 
						"' DT='"+ Utils.reFactorString (nvKhoa.getDtdmnhanvienKhoaChon()) + 
						"' />");
			}
		}
		
		buf.append("</list>");
		return buf.toString();
	}
}


