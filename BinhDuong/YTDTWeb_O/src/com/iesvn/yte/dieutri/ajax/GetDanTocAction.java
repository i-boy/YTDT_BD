package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmDanToc;
import com.iesvn.yte.util.Utils;


public class GetDanTocAction extends Action {
	
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDmDanToc = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDmDanToc = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DmDanToc", "dmdantocNgaygiocn");
		
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listDmDanToc != null) {
			for (Object obj : listDmDanToc) {
				DmDanToc dantoc = (DmDanToc) obj;
				buf.append("<record " +
						" MaSo='" + dantoc.getDmdantocMaso() + 
						"' Ma='" + dantoc.getDmdantocMa() + 

						"' Ten='" + dantoc.getDmdantocTen() + 
						
						"' DMDANTOC_DEFA='" + Utils.reFactorString (dantoc.getDmdantocDefa()) + 
						"' DMDANTOC_MIENPHI='" + Utils.reFactorString (dantoc.getDmdantocMienphi()) + 
						
						"' NgayChinhSua='" + dantoc.getDmdantocNgaygiocn() +
						"' DT='" + Utils.reFactorString (dantoc.getDmdantocChon()) +
						"' />");
				
			}
		}
		buf.append("</list>");
	
		return buf.toString();
	}
}


