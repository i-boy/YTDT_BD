package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat;
import com.iesvn.yte.util.Utils;

public class GetDtDmLoaiPhauThuatAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listLoaiPt = null;
		
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listLoaiPt = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DtDmLoaiPhauThuat", "dtdmloaiptNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listLoaiPt != null) {
			for (Object obj : listLoaiPt) {
				DtDmLoaiPhauThuat dvt = (DtDmLoaiPhauThuat)obj;
				buf.append("<record " +
						"MaSo='" + dvt.getDtdmloaiptMaso() + 
						"' Ma='" + dvt.getDtdmloaiptMa() + 
						"' Ten='"	+ dvt.getDtdmloaiptTen() + 
						"' DTDMLOAIPT_PHAUTHUAT='"	+ Utils.reFactorString (dvt.getDtdmloaiptPhauthuat() )+ 
						"' DTDMLOAIPT_MABYTP='"	+Utils.reFactorString ( dvt.getDtdmloaiptMabytp()) + 
						"' DTDMLOAIPT_THUTHUAT='"	+ Utils.reFactorString (dvt.getDtdmloaiptThuthuat()) + 
						"' DTDMLOAIPT_MABYTT='" + Utils.reFactorString (dvt.getDtdmloaiptMabytt()) + 
						"' NgayChinhSua='" + Utils.reFactorString (dvt.getDtdmloaiptNgaygiocn()) + 
						"' DT='" +Utils.reFactorString ( dvt.getDtdmloaiptChon()) + 
						"' />");
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}
