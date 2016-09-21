package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmBenhVien;
import com.iesvn.yte.entity.DmHuyen;
import com.iesvn.yte.entity.DmLoaiBenhVien;
import com.iesvn.yte.entity.DmTinh;
import com.iesvn.yte.entity.DmTuyen;
import com.iesvn.yte.entity.DmVungMien;
import com.iesvn.yte.util.Utils;

public class GetBenhVienAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDmBV = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDmBV = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DmBenhVien", "dmbenhvienNgaygiocn");
	
			
		} catch (Exception ex) {

		}

		buf.append("<list>");
		
		if (listDmBV != null) {
			for (Object obj : listDmBV) {
				DmBenhVien dmBV = (DmBenhVien)obj;
				String ten = dmBV.getDmbenhvienTen();
				
				if (ten == null || ten.equals("")){
					continue;
				}
				ten = Utils.findAndreplace(ten);
				
				DmTuyen dmTuyen = dmBV.getDmtuyenMaso();
				String maTuyen = "";
				if (dmTuyen != null ){
					maTuyen = String.valueOf(dmTuyen.getDmtuyenMaso());
				}
				
				DmLoaiBenhVien dmLoaiBV = dmBV.getDmloaibvMaso();
				String maLoaiBV = "";
				if (dmLoaiBV != null ){
					maLoaiBV = String.valueOf(dmLoaiBV.getDmloaibvMaso());
				}
				
				DmVungMien dmVungMien = dmBV.getDmvungmienMaso();
				String maVungMien = "";
				if (dmVungMien != null ){
					maVungMien = String.valueOf(dmVungMien.getDmvungmienMaso());
				}
				
				DmTinh dmTinh = dmBV.getDmtinhMaso();
				String maTinh = "";
				if (dmTinh != null ){
					maTinh = String.valueOf(dmTinh.getDmtinhMaso());
				}
				DmHuyen dmHuyen = dmBV.getDmhuyenMaso();
				String maHuyen = "";
				if (dmHuyen != null ){
					maHuyen = String.valueOf(dmHuyen.getDmhuyenMaso());
				}
				
				Boolean bChuyendi = dmBV.getDmbenhvienChuyendi();
				String chuyenDi = "0";
				if (bChuyendi!=null && bChuyendi.booleanValue()){
					chuyenDi = "1";
				}
				
				Boolean bChuyenden = dmBV.getDmbenhvienChuyenden();
				String chuyenDen = "0";
				if (bChuyenden!=null && bChuyenden.booleanValue()){
					chuyenDen = "1";
				}
				
				buf.append("<record " +
						" MaSo='" + dmBV.getDmbenhvienMaso() + 
						"' Ma='" + dmBV.getDmbenhvienMa() + 
						"' Ten='" + ten + 
						"' NgayChinhSua='"	+ dmBV.getDmbenhvienNgaygiocn() + 
						
						"' DMTUYEN_MASO='"	+ maTuyen + 
						"' DMLOAIBV_MASO='"	+ maLoaiBV + 
						"' DMVUNGMIEN_MASO='"	+ maVungMien + 
						"' DMTINH_MASO='"	+ maTinh + 
						"' DMHUYEN_MASO='"	+ maHuyen + 
						"' DMBENHVIEN_DIENTHOAI='"	+ Utils.reFactorString (dmBV.getDmbenhvienDienthoai()) + 
						"' DMBENHVIEN_DIACHI='"	+ Utils.reFactorString (dmBV.getDmbenhvienDiachi()) + 
						"' DMBENHVIEN_CHUYENDI='"	+ chuyenDi + 
						"' DMBENHVIEN_CHUYENDEN='"	+ chuyenDen + 
						"' DMBENHVIEN_DEFAULT='"	+  Utils.reFactorString (dmBV.getDmbenhvienDefault()) + 
						
						"' DT='" + Utils.reFactorString (dmBV.getDmbenhvienDt()) + 
						"' QL='" + Utils.reFactorString (dmBV.getDmbenhvienQl()) + 
						"' DP='" + Utils.reFactorString (dmBV.getDmbenhvienDp()) + 
				
						"' />");
				
			}
		}
		buf.append("</list>");
	
		return buf.toString();
	}
}


