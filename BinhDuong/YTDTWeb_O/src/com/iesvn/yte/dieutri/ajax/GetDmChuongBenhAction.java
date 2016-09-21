package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmChuongBenh;
import com.iesvn.yte.util.Utils;

public class GetDmChuongBenhAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDmChuongBenh = null;
		
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDmChuongBenh = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DmChuongBenh", "dmchuongbenhNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listDmChuongBenh != null) {
			for (Object obj : listDmChuongBenh) {
				DmChuongBenh dvt = (DmChuongBenh)obj;
				
				buf.append("<record " +
						"MaSo='" + dvt.getDmchuongbenhMaso() + 
						"' Ma='" + dvt.getDmchuongbenhMa() + 
						"' Ten='"	+dvt.getDmchuongbenhTen() + 
						"' DMBENHICD_MA='"	+ Utils.reFactorString(dvt.getDmbenhicdMa()) + 
						"' NgayChinhSua='"	+Utils.reFactorString ( dvt.getDmchuongbenhNgaygiocn()) + 
						"' QL='"	+ Utils.reFactorString (dvt.getDmchuongbenhQl()) + 
						"' DT='" + Utils.reFactorString (dvt.getDmchuongbenhDt()) + 
						"' DP='" + Utils.reFactorString (dvt.getDmchuongbenhDp()) +  
						"' />");
			}
		}
		buf.append("</list>");
		System.out.println(buf.toString());
		return buf.toString();
	}
}
