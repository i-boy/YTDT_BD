package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.util.Utils;

public class GetDonViTinhAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDVT = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDVT =  dtutilDelegate.findByNgayGioCN(Double
					.parseDouble(request), "DmDonViTinh",
					"dmdonvitinhNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listDVT != null) {
			for (Object obj : listDVT) {
				DmDonViTinh dvt = (DmDonViTinh)obj;
				buf.append("<record " +
						"MaSo='" + dvt.getDmdonvitinhMaso() + 
						"' Ma='" + dvt.getDmdonvitinhMa() + 
						"' Ten='"	+ dvt.getDmdonvitinhTen() + 
						"' DMDONVITINH_DTICH='"	+ Utils.reFactorString (dvt.getDmdonvitinhDtich() )+ 
						"' DMDONVITINH_DACDIEM='"	+Utils.reFactorString ( dvt.getDmdonvitinhDacdiem()) + 
						"' NgayChinhSua='"	+ Utils.reFactorString (dvt.getDmdonvitinhNgaygiocn()) + 
						"' DT='" + Utils.reFactorString (dvt.getDmdonvitinhDt()) + 
						"' QL='" + Utils.reFactorString (dvt.getDmdonvitinhQl()) + 
						"' DP='" +Utils.reFactorString ( dvt.getDmdonvitinhDp()) + 
						"' />");
				

				
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}


