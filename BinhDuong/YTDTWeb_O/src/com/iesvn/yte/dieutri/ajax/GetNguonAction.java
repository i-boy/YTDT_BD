package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmNguonChuongTrinh;
import com.iesvn.yte.util.Utils;

public class GetNguonAction extends Action {
	public String performAction(String request) throws Exception{
		StringBuffer buf = new StringBuffer();
		List listNguon = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listNguon = dtutilDelegate.findByNgayGioCN(
					Double.parseDouble(request), "DmNguonChuongTrinh", "dmnctNgaygiocn");
		}
		catch (Exception ex) {
			
		}
    	
        buf.append("<list>");
       // buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
        if (listNguon != null) {
	        for (Object obj : listNguon) {
	        	DmNguonChuongTrinh n = (DmNguonChuongTrinh) obj;
	        	String loaiNguon = "";
	        	if (n.getDmlnctMaso() != null) {
	        		loaiNguon = n.getDmlnctMaso().getDmlnctMaso().toString();
	        	}
	        	buf.append("<record MaSo='" + n.getDmnctMaso() + 
	        			"' Ma='" + n.getDmnctMa() + 
	        			"' Ten='" + n.getDmnctTen() + 
	        			"' DMLNCT_MASO='" + loaiNguon + 
	        			"' DMNCT_THUTUCBC='" + n.getDmnctThutucbc() + 
	        			"' DMNCT_DEFA='" + Utils.reFactorString(n.getDmnctDefa()) + 
	        			"' DT='" + Utils.reFactorString(n.getDmnctDt()) + 
	        			"' QL='" + Utils.reFactorString(n.getDmnctQl()) + 
	        			"' DP='" + Utils.reFactorString(n.getDmnctDp()) + 
	        			"' NgayChinhSua='" + n.getDmnctNgaygiocn() + "' />");
	        	
	        }
        }
        buf.append("</list>");
        return buf.toString();
	}
}


