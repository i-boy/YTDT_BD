package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNoiSinh;
import com.iesvn.yte.util.Utils;


public class GetDmNoiSinhAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDtDmNoiSinh = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDtDmNoiSinh = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DtDmNoiSinh", "dtdmnoisinhNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listDtDmNoiSinh != null) {
			for (Object obj : listDtDmNoiSinh) {
				DtDmNoiSinh noisinh = (DtDmNoiSinh) obj;
				buf.append("<record " +
						"MaSo='" + noisinh.getDtdmnoisinhMaso() +
						"' Ma='" + noisinh.getDtdmnoisinhMa() +
						"' Ten='"+ noisinh.getDtdmnoisinhTen() + 
						"' DTDMNOISINH_MAPHU='"+ Utils.reFactorString (noisinh.getDtdmnoisinhMaphu()) + 
						"' DTDMNOISINH_PHANLOAI='"+ Utils.reFactorString (noisinh.getDtdmnoisinhPhanloai() )+ 
						"' DTDMNOISINH_GHICHU='"+ Utils.reFactorString (noisinh.getDtdmnoisinhGhichu()) + 
						"' DT='"+ Utils.reFactorString (noisinh.getDtdmnoisinhChon()) +
						
						"' NgayChinhSua='"	+ noisinh.getDtdmnoisinhNgaygiocn() +
						"' />");
				
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}


