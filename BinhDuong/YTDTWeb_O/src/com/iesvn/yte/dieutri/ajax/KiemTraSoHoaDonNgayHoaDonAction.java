package com.iesvn.yte.dieutri.ajax;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.PhieuNhapKhoDelegate;
import com.iesvn.yte.util.Utils;


public class KiemTraSoHoaDonNgayHoaDonAction extends Action {
	
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		
		StringTokenizer sTokenizer  = new StringTokenizer(request,",");
		String soHoaDon = "";
		String ngayHoaDon = "";
		int i = 0;
		while (sTokenizer.hasMoreTokens()){
			if (i == 0){
				soHoaDon = sTokenizer.nextToken();
			}else if (i == 1){
				ngayHoaDon = sTokenizer.nextToken();
			}
			i++;
		}
		System.out.println("soHoaDon:"+soHoaDon);
		System.out.println("ngayHoaDon:"+ngayHoaDon);
		
		SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
		Date dNgayHoaDon = sdf.parse(ngayHoaDon);
		
		boolean kiemtra = false;
		try {
			PhieuNhapKhoDelegate pnkdele = PhieuNhapKhoDelegate.getInstance();
			kiemtra = pnkdele.checkExisted(soHoaDon, dNgayHoaDon);
		
		} catch (Exception ex) {
				ex.printStackTrace();
		}

		buf.append("<list>");
		
				buf.append("<record " +
						" TonTai='" + kiemtra + 						
						"' />");
				
		
		buf.append("</list>");
	
		return buf.toString();
	}
}


