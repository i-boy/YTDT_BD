package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmLoaiThuoc;
import com.iesvn.yte.util.Utils;

public class GetLoaiAction extends Action {
	public String performAction(String request) throws Exception{
		StringBuffer buf = new StringBuffer();
		List listLoai = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listLoai = dtutilDelegate.findByNgayGioCN(
					Double.parseDouble(request), "DmLoaiThuoc", "dmloaithuocNgaygiocn");
		}
		catch (Exception ex) {
			
		}
    	
        buf.append("<list>");
       // buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
        if (listLoai != null) {
	        for (Object obj : listLoai) {
	        	DmLoaiThuoc l = (DmLoaiThuoc) obj;
	        	buf.append("<record MaSo='" + l.getDmloaithuocMaso() + 
	        			"' Ma='" + l.getDmloaithuocMa() + 
	        			"' Ten='" + l.getDmloaithuocTen() + 
	        			"' DMLOAITHUOC_QUYEN='" + Utils.reFactorString(l.getDmloaithuocQuyen()) + 
	        			"' QL='" + Utils.reFactorString(l.getDmloaithuocQl()) + 
	        			"' DT='" + Utils.reFactorString(l.getDmloaithuocDt()) + 
	        			"' DP='" + Utils.reFactorString(l.getDmloaithuocDp()) + 
	        			"' NgayChinhSua='" + l.getDmloaithuocNgaygiocn() + "' />");
	        }
	        
        }
        buf.append("</list>");
        return buf.toString();
	}
}


