package com.iesvn.yte.dieutri.ajax;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.TiepDon;

public class GetDanhSachBenhNhanTiepDonForThamKhamAction extends Action {
	private static Logger log = Logger.getLogger(GetDanhSachBenhNhanTiepDonForThamKhamAction.class);
	private static String FORMAT_DATE = "dd/MM/yyyy";
	public String performAction(String request) throws Exception {
		//log.debug("-----Begin getDSBN()-----" + request);
		StringBuffer buf = new StringBuffer();
		String banKhamMa = "";
		String ngayThamKham = "";
	
		StringTokenizer sToken = new StringTokenizer(request,";");
		if (sToken != null ){
			
		//	while (sToken.hasMoreTokens()){
			banKhamMa  = sToken.nextToken();
			ngayThamKham = sToken.nextToken();

			
		//	}
		}
		
		
		banKhamMa = banKhamMa.trim();
		ngayThamKham = ngayThamKham.trim();
		SimpleDateFormat formatter;	    
        formatter = new SimpleDateFormat(FORMAT_DATE); 
        
		Date dNgayThamKham = new Date();
		if (!ngayThamKham.equals("")){
			dNgayThamKham = formatter.parse(ngayThamKham);
		}
		
		ArrayList<String> lstBenhNhanMa = new ArrayList<String>();
		
//		ArrayList<TiepDon> listTiepDon = new ArrayList<TiepDon>();
		
		buf.append("<list>");
		buf.append("<record  matiepdon='' hoten='' tuoi='' namsinh='' cmnd='' />");
		
		try {
			TiepDonDelegate tdDelagate = TiepDonDelegate.getInstance();
			//int i = 0;
			List<TiepDon> listTd ;
			if ("".equals(banKhamMa)){
				listTd = tdDelagate.findTiepDonByNgay( dNgayThamKham );
			}else{
				listTd = tdDelagate.findTiepDonByBanKhamMaAndNgay(banKhamMa,  dNgayThamKham );
			}
			
			if (listTd != null && listTd.size() > 0) {
				for (TiepDon td : listTd) {
					
					BenhNhan benhNhan = td.getBenhnhanMa();
					if (benhNhan != null){
						Date ngaySinh = benhNhan.getBenhnhanNgaysinh();
						
						String sNgaySinh = "";
						
						if (ngaySinh != null){
							sNgaySinh = formatter.format(ngaySinh.getTime());
						}
						
						lstBenhNhanMa.add(benhNhan.getBenhnhanMa());
						
						buf.append("<record  matiepdon='" + td.getTiepdonMa() + "' hoten='"
								+ benhNhan.getBenhnhanHoten()  + "' tuoi='"
								+ "" + "' namsinh='"
								+ sNgaySinh  + "' cmnd='"
								+ "" + "' />");
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(String.format("-----Error: %s", e.toString()));
		}
		
		
		try {
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			//int i = 0;
			List<ThamKham> arrayThamKham;
			if ("".equals(banKhamMa)){
				arrayThamKham = tkDelegate.findThamKhamByNgay(  dNgayThamKham );
			}else {
				arrayThamKham = tkDelegate.findThamKhamByBanKhamMaAndNgay(banKhamMa,  dNgayThamKham );
				
			}
			
			if (arrayThamKham != null && arrayThamKham.size() > 0) {
				for (ThamKham thamKham : arrayThamKham) {
					
					BenhNhan benhNhan = thamKham.getTiepdonMa().getBenhnhanMa();
					if (benhNhan != null){
						Date ngaySinh = benhNhan.getBenhnhanNgaysinh();
						
						String sNgaySinh = "";
						if (ngaySinh != null){
							sNgaySinh = formatter.format(ngaySinh.getTime());
						}
						
						boolean bContain = false;
						for (String str : lstBenhNhanMa){
							if (str.equals(benhNhan.getBenhnhanMa())){
								bContain = true;
								break;
							}
						}
						
						if (bContain == false){
						buf.append("<record  matiepdon='" + thamKham.getTiepdonMa().getTiepdonMa() + "' hoten='"
								+ benhNhan.getBenhnhanHoten()  + "' tuoi='"
								+ "" + "' namsinh='"
								+ sNgaySinh  + "' cmnd='"
								+ "" + "' />");
						}
						
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(String.format("-----Error: %s", e.toString()));
		}

		buf.append("</list>");
		//log.debug("-----End getTonKho()-----" + buf.toString());
		
		//System.out.println(" buf.toString():"+ buf.toString());
		
		return buf.toString();
	}
}


