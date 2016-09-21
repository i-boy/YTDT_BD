package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmXa;
import com.iesvn.yte.util.Utils;

public class GetXaAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDmXa = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDmXa =  dtutilDelegate.findByNgayGioCN(
					Double.parseDouble(request), "DmXa", "dmxaNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listDmXa != null) {
			for (Object obj : listDmXa) {
				DmXa xa = (DmXa) obj;
				//xa.getDmxaNgaygiocn()
				String ten = xa.getDmxaTen();
				ten = Utils.findAndreplace(ten);
				String huyenMaso = "";
				if (xa.getDmhuyenMaso() != null) {
					huyenMaso = xa.getDmhuyenMaso().getDmhuyenMaso().toString();
				}
				
				buf.append("<record MaSo='" + xa.getDmxaMaso() + 
						"' Ma='" + xa.getDmxaMa() + 
						"' Ten='" + ten + 
						"' DMHUYEN_MASO='" + huyenMaso + 
						"' DMXA_DEFA='" + Utils.reFactorString(xa.getDmxaDefa()) + 
						"' DT='" + Utils.reFactorString(xa.getDmxaChon()) + 
						"' NgayChinhSua='" + xa.getDmxaNgaygiocn() + "' />");
			}
		}
		
		buf.append("</list>");
		return buf.toString();
	}
}


