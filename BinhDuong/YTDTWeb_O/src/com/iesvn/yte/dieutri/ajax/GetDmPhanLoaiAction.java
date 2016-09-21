package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import com.iesvn.yte.util.Utils;


public class GetDmPhanLoaiAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDmPhanLoai = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDmPhanLoai = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DmPhanLoaiThuoc", "dmphanloaithuocNgaygiocn");

		} catch (Exception ex) {
			System.out.println(ex);
		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listDmPhanLoai != null) {
			for (Object obj : listDmPhanLoai) {
				DmPhanLoaiThuoc phanLoai = (DmPhanLoaiThuoc)obj;
				String sLoai = "";
				if (phanLoai.getDmloaithuocMaso()!=null){
					sLoai = String.valueOf(phanLoai.getDmloaithuocMaso().getDmloaithuocMaso());
				}
				buf.append("<record " +
						"MaSo='" + phanLoai.getDmphanloaithuocMaso() +
						"' Ma='" + phanLoai.getDmphanloaithuocMa() +
						"' Ten='" + phanLoai.getDmphanloaithuocTen() + 
						"' DMLOAITHUOC_MASO='"	+ sLoai +
						"' DMPHANLOAITHUOC_NHOM2='"	+  Utils.reFactorString (phanLoai.getDmphanloaithuocNhom2()) +
						"' DMPHANLOAITHUOC_NHOM3='"	+  Utils.reFactorString (phanLoai.getDmphanloaithuocNhom3()) +
						"' DMPHANLOAITHUOC_DUNGTICH='"	+  Utils.reFactorString (phanLoai.getDmphanloaithuocDungtich()) +
						"' DMPHANLOAITHUOC_GHICHU='"	+  Utils.reFactorString (phanLoai.getDmphanloaithuocGhichu()) +
						"' DMPHANLOAITHUOC_THUTUBC='"	+  Utils.reFactorString (phanLoai.getDmphanloaithuocThutubc() )+
						"' NgayChinhSua='" + phanLoai.getDmphanloaithuocNgaygiocn() + 
						"' DMPHANLOAITHUOC_LOAI='" + Utils.reFactorString (phanLoai.getDmphanloaithuocLoai()) + 
						"' DT='" + Utils.reFactorString (phanLoai.getDmphanloaithuocDt()) + 
						"' QL='" + Utils.reFactorString (phanLoai.getDmphanloaithuocQl()) + 
						"' DP='" +Utils.reFactorString ( phanLoai.getDmphanloaithuocDp()) + 
						
						"' />");
				
			}
		}
		buf.append("</list>");
		return buf.toString();
	}

}


