package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmTramYTeBhyt;
import com.iesvn.yte.util.Utils;

public class GetDtDmTramYTeBhytAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDtDmTramYTeBhyt = null;
		
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDtDmTramYTeBhyt = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DtDmTramYTeBhyt", "dtdmtramytebhytNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listDtDmTramYTeBhyt != null) {
			for (Object obj : listDtDmTramYTeBhyt) {
				DtDmTramYTeBhyt dtDmTramYTeBhyt = (DtDmTramYTeBhyt)obj; 
				
			
				
				buf.append("<record " 
						+ " MaSo='"	+ dtDmTramYTeBhyt.getDtdmtramytebhytMaso() + "'"
						+ " Ma='"	+ dtDmTramYTeBhyt.getDtdmtramytebhytMa() + "'"						
						+ " Ten='"	+ dtDmTramYTeBhyt.getDtdmtramytebhytTen() + "'" 
						+ " NgayChinhSua='"	+ dtDmTramYTeBhyt.getDtdmtramytebhytNgaygiocn() + "'"
						+ " DT='"	+ Utils.reFactorString (dtDmTramYTeBhyt.getDtdmtramytebhytChon()) + "'" 
						
						+ "/>");
				
			}
		}
		buf.append("</list>");
		return buf.toString();
	}
}


