package com.iesvn.yte.dieutri.ajax;

import org.apache.log4j.Logger;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmThuoc;


public class GetGiaBQGiaCuoiTonAction extends Action {
	private static Logger logger = Logger.getLogger(GetGiaBQGiaCuoiTonAction.class);
	
	public String performAction(String request) {
		StringBuffer buf = new StringBuffer();
		
		buf.append("<list>");
		//TonKhoWSService tkService = new TonKhoWSServiceLocator();
		try {
			/*
			TonKhoWS tkWS = tkService.getTonKhoWSPort();
			String giaBQ = tkWS.getGiaBQ(request.substring(0, request.lastIndexOf("|")), 
					request.substring(request.lastIndexOf("|") + 1, request.length()));
			giaBQ = giaBQ.equals("") ? "0" : giaBQ;
			logger.debug("gia bq: " + giaBQ);
			
			buf.append("<result value='" + giaBQ + "'/>");
			String giaCuoi = tkWS.getGiaCuoi(request.substring(0, request.lastIndexOf("|")), 
					request.substring(request.lastIndexOf("|") + 1, request.length()));
			giaCuoi = giaCuoi.equals("") ? "0" : giaCuoi;
			logger.debug("gia cuoi: " + giaCuoi);
			
			buf.append("<result value='" + giaCuoi + "'/>");
			String ton = tkWS.getSoLuongTon(request.substring(0, request.lastIndexOf("|")), 
					request.substring(request.lastIndexOf("|") + 1, request.length()));
			ton = ton.equals("") ? "0" : ton;
			logger.debug("ton: " + ton);
			
			buf.append("<result value='" + ton + "'/>");
			*/
			
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			DmThuoc dmt = (DmThuoc) dtutilDelegate.findByMa(request.substring(0, request.lastIndexOf("|")), "DmThuoc",
					"dmthuocMa");
			
			for (int i = 0; i < 3; i++) {
				buf.append(String.format("<result value='%s' />", 0));
			}
			buf.append(String.format("<result value='%s' />", dmt.getDmdonvitinhMaso().getDmdonvitinhMa()));
			//buf.append("<result value='" + dmt.getDtdmdonvitinhMa().getDtdmdonvitinhMa() + "'/>");
		} catch (Exception e) {
			logger.error(String.format("-----Error: %s at line %s -----", 
					e, e.getStackTrace()[0].getLineNumber()));
		}
		buf.append("</list>");
		logger.debug("-----result: " + buf.toString());
		return buf.toString();
	}
}


