package com.iesvn.yte.dieutri.ajax;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.Hsba;


public class GetDanhSachBenhNhanHSBAAction extends Action {
	private static Logger log = Logger.getLogger(GetDanhSachBenhNhanHSBAAction.class);
	private static String FORMAT_DATE = "dd/MM/yyyy";
	public String performAction(String request) throws Exception {
		log.info("----- Begin getDSBN() HSBA ----- Ma so Khoa: " + request);
		StringBuffer buf = new StringBuffer();
		String makhoa = request;
		
		SimpleDateFormat formatter;	    
        formatter = new SimpleDateFormat(FORMAT_DATE); 
		
		buf.append("<list>");
		buf.append("<record  matiepdon='' hoten='' tuoi='' namsinh='' cmnd='' />");
		HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
		
		try {
			List<Hsba> array_hsba = hsbaDelegate.findKhoaDangDieuTri(makhoa);
			
			if (array_hsba != null && array_hsba.size() > 0) {
				for (Hsba hsba : array_hsba) {
					
					BenhNhan benhNhan = hsba.getBenhnhanMa();
					
					if (benhNhan != null){
						
						Date ngaySinh = benhNhan.getBenhnhanNgaysinh();
						
						String sNgaySinh = "";
						if (ngaySinh != null){
							sNgaySinh = formatter.format(ngaySinh.getTime());
						} else if (benhNhan.getBenhnhanNamsinh() != null){
							sNgaySinh = benhNhan.getBenhnhanNamsinh();
						}
					
						buf.append("<record  matiepdon='" + hsba.getHsbaSovaovien() + "' hoten='"
								+ benhNhan.getBenhnhanHoten()  + "' tuoi='"
								+ "" + "' namsinh='"
								+ sNgaySinh  + "' cmnd='"
								+ "" + "' />");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(String.format("-----Error: %s", e.toString()));
		}
		
		buf.append("</list>");
		log.info("----- End getDSBN() HSBA -----");
		//System.out.println(" buf.toString():"+ buf.toString());
		
		return buf.toString();
	}
}


