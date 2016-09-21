package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmKcbBhyt;
import com.iesvn.yte.util.Utils;

public class GetKCBBHYTAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDtDmKcbBhyt = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDtDmKcbBhyt = dtutilDelegate.findByNgayGioCN(Double
					.parseDouble(request), "DtDmKcbBhyt",
					"dtdmkcbbhytNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		// buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listDtDmKcbBhyt != null) {
			for (Object obj : listDtDmKcbBhyt) {
				DtDmKcbBhyt kcb = (DtDmKcbBhyt) obj;
				String ten = Utils.findAndreplace(kcb.getDtdmkcbbhytTen());
				// String ten = URLEncoder.encode(kcb.getDtdmkcbbhytTen(),
				// "UTF-8");
				String tinhBhyt = "";
				if (kcb.getDtdmtinhbhytMaso() != null) {
					tinhBhyt = kcb.getDtdmtinhbhytMaso().getDtdmtinhbhytMaso()
							.toString();
				}
				String tuyenKcb = "";
				if (kcb.getDtdmtuyenkcbMaso() != null) {
					tuyenKcb = kcb.getDtdmtuyenkcbMaso().getDtdmtuyenkcbMaso()
							.toString();
				}
				
				

				buf.append("<record MaSo='" + kcb.getDtdmkcbbhytMaso()
						+ "' Ma='" + kcb.getDtdmkcbbhytMa() + "' Ten='" + ten
						+ "' DTDMKCBBHYT_GHICHU='" + kcb.getDtdmkcbbhytGhichu()
						+ "' DTDMKCBBHYT_MAUTHE='" + kcb.getDtdmkcbbhytMauthe()
						+ "' DT='" + Utils.reFactorString(kcb.getDtdmkcbbhytChon())
						+ "' DTDMTINHBHYT_MASO='" + tinhBhyt
						+ "' DTDMTUYENKCB_MASO='" + tuyenKcb
						
						+ "' DTDMKCBBHYT_DEFA='" +  Utils.reFactorString (kcb.getDtdmkcbbhytDefa())  
						
						
						
						+ "' NgayChinhSua='" + kcb.getDtdmkcbbhytNgaygiocn()
						+ "' />");
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}


