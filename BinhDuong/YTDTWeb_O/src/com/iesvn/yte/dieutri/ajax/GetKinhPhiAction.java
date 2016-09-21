package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmNguonKinhPhi;
import com.iesvn.yte.util.Utils;

public class GetKinhPhiAction extends Action {
	public String performAction(String request) throws Exception{
		StringBuffer buf = new StringBuffer();
		List listKinhPhi = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listKinhPhi = dtutilDelegate.findByNgayGioCN(
					Double.parseDouble(request), "DmNguonKinhPhi", "dmnguonkinhphiNgaygiocn");
		}
		catch (Exception ex) {
			
		}
		
        buf.append("<list>");
       // buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
        if (listKinhPhi != null) {
	        for (Object obj : listKinhPhi) {
	        	DmNguonKinhPhi kp = (DmNguonKinhPhi) obj;
	        	buf.append("<record MaSo='" + kp.getDmnguonkinhphiMaso() + 
	        			"' Ma='" + kp.getDmnguonkinhphiMa() + 
	        			"' Ten='" + kp.getDmnguonkinhphiTen() + 
	        			"' QL='" + Utils.reFactorString(kp.getDmnguonkinhphiQl()) + 
	        			"' DP='" + Utils.reFactorString(kp.getDmnguonkinhphiDp()) + 
	        			"' DT='" + Utils.reFactorString(kp.getDmnguonkinhphiDt()) + 
	        			"' NgayChinhSua='" + kp.getDmnguonkinhphiNgaygiocn() + "' />");
	        }
        }
        buf.append("</list>");
        
        return buf.toString();
	}
}


