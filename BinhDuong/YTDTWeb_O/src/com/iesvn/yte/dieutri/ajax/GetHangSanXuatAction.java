package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmNhaSanXuat;
import com.iesvn.yte.util.Utils;

public class GetHangSanXuatAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listHangSX = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listHangSX = dtutilDelegate.findByNgayGioCN(
					Double.parseDouble(request), "DmNhaSanXuat", "dmnhasanxuatNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listHangSX != null) {
			for (Object obj : listHangSX) {
				DmNhaSanXuat hangSX = (DmNhaSanXuat) obj;
				buf.append("<record MaSo='" + hangSX.getDmnhasanxuatMaso()
						+ "' Ma='" + hangSX.getDmnhasanxuatMa() 
						+ "' Ten='" + hangSX.getDmnhasanxuatTen() 
						+ "' DMNHASANXUAT_PHANBIET='" + Utils.reFactorString(hangSX.getDmnhasanxuatPhanbiet())
						+ "' DMNHASANXUAT_PHANLOAI='" + Utils.reFactorString(hangSX.getDmnhasanxuatPhanloai()) 
						+ "' DMNHASANXUAT_TENVN='" + Utils.reFactorString(hangSX.getDmnhasanxuatTenvn())
						+ "' DP='" + Utils.reFactorString(hangSX.getDmnhasanxuatDp() )
						+ "' DT='" + Utils.reFactorString(hangSX.getDmnhasanxuatDt() )
						+ "' QL='" + Utils.reFactorString(hangSX.getDmnhasanxuatQl() )
						+ "' DMNHASANXUAT_HANG='" + Utils.reFactorString(hangSX.getDmnhasanxuatHang() )
						+ "' NgayChinhSua='" + Utils.reFactorString(hangSX.getDmnhasanxuatNgaygiocn()) + "' />");
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}


