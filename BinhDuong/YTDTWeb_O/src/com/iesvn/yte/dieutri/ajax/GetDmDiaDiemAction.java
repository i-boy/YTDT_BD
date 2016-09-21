package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmDiaDiem;
import com.iesvn.yte.util.Utils;


public class GetDmDiaDiemAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List list = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			list = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DmDiaDiem", "dmdiadiemNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (list != null) {
			for ( Object obj: list) {
				DmDiaDiem diadiem = (DmDiaDiem) obj;
				buf.append("<record " +
						"  MaSo='" + diadiem.getDmdiadiemMaso() +
						"' Ma='" + diadiem.getDmdiadiemMa() +
						"' Ten='"+ diadiem.getDmdiadiemTen() + 
						"' NgayChinhSua='"	+ diadiem.getDmdiadiemNgaygiocn() +
						
						"' QL='"	+ Utils.reFactorString (diadiem.getDmdiadiemQl()) +
						"' DT='"	+ Utils.reFactorString (diadiem.getDmdiadiemDt()) +
						"' DP='"	+ Utils.reFactorString (diadiem.getDmdiadiemDp())+
						"' />");
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}


