package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmCachDungThuoc;
import com.iesvn.yte.util.Utils;

public class GetDmCachDungThuocAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDmCachDungThuoc = null;
		
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDmCachDungThuoc = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DmCachDungThuoc", "dmcachdungthuocNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listDmCachDungThuoc != null) {
			for (Object obj : listDmCachDungThuoc) {
				DmCachDungThuoc dmcdt = (DmCachDungThuoc)obj;
				buf.append("<record " +
						"MaSo='" + dmcdt.getDmcachdungthuocMaso() + 
						"' Ma='" + dmcdt.getDmcachdungthuocMa() + 
						"' Ten='"	+ dmcdt.getDmcachdungthuocTen() + 
						"' DMCACHDUNGTHUOC_MAPHU='"	+ Utils.reFactorString(dmcdt.getDmcachdungthuocMaphu()) + 
						"' NGAYCHINHSUA='"	+ Utils.reFactorString (dmcdt.getDmcachdungthuocNgaygiocn())+  						
						"' QL='"	+ Utils.reFactorString (dmcdt.getDmcachdungthuocQl()) + 
						"' DT='" + Utils.reFactorString (dmcdt.getDmcachdungthuocDt()) + 
						"' DP='" + Utils.reFactorString (dmcdt.getDmcachdungthuocDp()) + 					
						"' />");
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}
