package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
import com.iesvn.yte.util.Utils;

public class GetDtDmClsKhoaAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listObj = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listObj =  dtutilDelegate.findByNgayGioCN(Double
					.parseDouble(request), "DtDmClsKhoa",
					"dtdmclsKhoaNgaygiocn");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.print("GetDtDmClsKhoaAction request:"+request);

		buf.append("<list>");
		
		if (listObj != null) {
			for (Object obj : listObj) {
				DtDmClsKhoa clsKhoa = (DtDmClsKhoa) obj;
				buf.append("<record " +
						"MaSo='" + clsKhoa.getDtdmclskhoaMaso() + 
						"' DTDMCLS_MASO='"+ clsKhoa.getDtdmclsMaso().getDtdmclsbgMaso() + 
						"' DMKHOA_MASO='"+ clsKhoa.getDmkhoaMaso().getDmkhoaMaso() + 
						"' NgayChinhSua='"+ Utils.reFactorString (clsKhoa.getDtdmclsKhoaNgaygiocn()) + 
						"' DT='"+ Utils.reFactorString (clsKhoa.getDtdmclsKhoaChon()) + 
						"' />");
			}
		}
		
		buf.append("</list>");
		return buf.toString();
	}
}


