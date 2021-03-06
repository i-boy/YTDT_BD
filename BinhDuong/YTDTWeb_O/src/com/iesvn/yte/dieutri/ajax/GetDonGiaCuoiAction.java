package com.iesvn.yte.dieutri.ajax;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;


public class GetDonGiaCuoiAction extends Action {
	
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		double donGiaCuoi = 0;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			donGiaCuoi = dtutilDelegate.getGiaCuoi(request);
		
		} catch (Exception ex) {
				ex.printStackTrace();
		}

		buf.append("<list>");
		
				buf.append("<record " +
						" DonGiaCuoi='" + donGiaCuoi + 						
						"' />");
				
		
		buf.append("</list>");
	
		return buf.toString();
	}
}


