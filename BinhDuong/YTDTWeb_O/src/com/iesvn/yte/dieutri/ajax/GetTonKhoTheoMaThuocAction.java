package com.iesvn.yte.dieutri.ajax;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;


public class GetTonKhoTheoMaThuocAction extends Action {
	private static Logger log = Logger.getLogger(GetTonKhoTheoMaThuocAction.class);
	
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		double tonKhoCuoi = 0;
		try {
			String maThuoc;
			String maKho;
			StringTokenizer sToken = new StringTokenizer(request, ";");
			log.info("sToken"+sToken);
			if (sToken != null) {
				maThuoc = sToken.nextToken().trim();
				maKho = sToken.nextToken().trim();

				log.info("maThuoc: "+maThuoc);
				log.info("maKho: "+maKho);
				DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
				tonKhoCuoi = dtutilDelegate.getTonKhoByMaThuocAndMaKhoa(maThuoc, maKho);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		buf.append("<list>");
		
				buf.append("<record " +
						" TonKho='" + tonKhoCuoi + 						
						"' />");
				
			
		
		buf.append("</list>");
	
		return buf.toString();
	}
}


