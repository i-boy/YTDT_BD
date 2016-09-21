package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmQuocGia;
import com.iesvn.yte.util.Utils;

public class GetQuocGiaAction extends Action {
	public String performAction(String request) throws Exception{
		StringBuffer buf = new StringBuffer();
		List listQuocGia = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listQuocGia =  dtutilDelegate.findByNgayGioCN(

					Double.parseDouble(request), "DmQuocGia", "dmquocgiaNgaygiocn");
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
    	
    	
        buf.append("<list>");
       // buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
        if (listQuocGia != null) {

	        for (Object obj : listQuocGia) {
	        	DmQuocGia qg = (DmQuocGia) obj;

	        	buf.append("<record MaSo='" + qg.getDmquocgiaMaso() + 
	        			"' Ma='" + qg.getDmquocgiaMa() + 
	        			"' Ten='" + qg.getDmquocgiaTen() + 
	        			"' DMQUOCGIA_TENVN='" + qg.getDmquocgiaTenvn() +
	        			"' DT='" + Utils.reFactorString(qg.getDmquocgiaChon()) +
	        			"' NgayChinhSua='" + qg.getDmquocgiaNgaygiocn() + "' />");
	        }
	        
        }
        buf.append("</list>");
        return buf.toString();
	}
}


