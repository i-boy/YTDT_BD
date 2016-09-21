package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import org.apache.log4j.Logger;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmKho;
import com.iesvn.yte.util.Utils;

public class GetKhoAction extends Action {
	private static Logger logger = Logger.getLogger(GetKhoAction.class);
	
	public String performAction(String request) throws Exception{
		logger.debug("-----GetKhoAction.performAction()-----");
		logger.debug(String.format("-----request: %s", request));
		StringBuffer buf = new StringBuffer();
		List listKho = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listKho = dtutilDelegate.findByNgayGioCN(
					Double.parseDouble(request), "DtDmKho", "dtdmkhoaNgaygiocn");
		}
		catch (Exception ex) {
			
		}
		
		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listKho != null) {
			for (Object obj: listKho) {
				DtDmKho k = (DtDmKho) obj;
				String khocha = "";
				if (k.getDtdmkhoKhocha() != null) {
					khocha = k.getDtdmkhoKhocha().getDmkhoaMaso().toString();
				}
				String nv = "";
				if (k.getDtdmkhoThukho() != null) {
					nv = k.getDtdmkhoThukho().getDtdmnhanvienMaso().toString();
				}
				buf.append("<record MaSo='" + k.getDtdmkhoMaso() + 
						"' Ma='" + k.getDtdmkhoMa() + 
	        			"' Ten='" + k.getDtdmkhoTen() + 
	        			"' DTDMKHO_KHOCHA='" + khocha + 
	        			"' DTDMKHO_THUKHO='" + nv + 
	        			"' DTDMKHO_THUTUBC='" + Utils.reFactorString(k.getDtdmkhoThutubc()) + 
	        			"' DT='" + Utils.reFactorString(k.getDtdmkhoChon()) + 
	        			"' NgayChinhSua='" + k.getDtdmkhoaNgaygiocn() + "' />");
			}
			
		}
		buf.append("</list>");
		logger.debug(String.format("-----result: %s", buf.toString()));
		return buf.toString();
	}
}


