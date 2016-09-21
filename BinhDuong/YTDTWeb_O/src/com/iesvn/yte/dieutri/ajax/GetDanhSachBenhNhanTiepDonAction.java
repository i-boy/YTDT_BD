package com.iesvn.yte.dieutri.ajax;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ThamKham;

public class GetDanhSachBenhNhanTiepDonAction extends Action {
	private static Logger log = Logger.getLogger(GetDanhSachBenhNhanTiepDonAction.class);
	private static String FORMAT_DATE = "dd/MM/yyyy";
	public String performAction(String request) throws Exception {
		//log.info("-----Begin getDSBN()-----" + request);
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
		
//		ArrayList<String> lstBenhNhanMa = new ArrayList<String>();
		
		buf.append("<list>");
		buf.append("<record  matiepdon='' hoten='' tuoi='' namsinh='' cmnd='' />");
		
//		try {
//			TiepDonDelegate tdDelagate = TiepDonDelegate.getInstance();
//			//int i = 0;
//			List<TiepDon> listTd ;
//			if ("".equals(banKhamMa)){
//				listTd = tdDelagate.findTiepDonByNgay( dNgayThamKham );
//			}else{
//				listTd = tdDelagate.findTiepDonByBanKhamMaAndNgay(banKhamMa,  dNgayThamKham );
//			}
//			
//			if (listTd != null && listTd.size() > 0) {
//				for (TiepDon td : listTd) {
//					
//					BenhNhan benhNhan = td.getBenhnhanMa();
//					if (benhNhan != null){
//						Date ngaySinh = benhNhan.getBenhnhanNgaysinh();
//						
//						String sNgaySinh = "";
//						
//						if (ngaySinh != null){
//							sNgaySinh = formatter.format(ngaySinh.getTime());
//						}
//						
//						lstBenhNhanMa.add(benhNhan.getBenhnhanMa());
//						
//						buf.append("<record  matiepdon='" + td.getTiepdonMa() + "' hoten='"
//								+ benhNhan.getBenhnhanHoten()  + "' tuoi='"
//								+ "" + "' namsinh='"
//								+ sNgaySinh  + "' cmnd='"
//								+ "" + "' />");
//					}
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			log.error(String.format("-----Error: %s", e.toString()));
//		}
		
// **********	20110623 bao.ttc : remove phan tren vi chi can search benhnhan theo thamkham (tim theo Ngay va Ban kham)
// **********					   Khong can search theo tiepdon 
		
		try {
			ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
			//int i = 0;
			List<ThamKham> arrayThamKham;
			List<String> listMaTD = new ArrayList<String>();
			
			if ("".equals(banKhamMa)){
				arrayThamKham = tkDelegate.findThamKhamByNgay(dNgayThamKham);
			} else {
				arrayThamKham = tkDelegate.findThamKhamByBanKhamMaAndNgay(banKhamMa, dNgayThamKham);
			}
			
			if (arrayThamKham != null && arrayThamKham.size() > 0) {
				for (ThamKham thamKham : arrayThamKham) {
					String maTD = thamKham.getTiepdonMa().getTiepdonMa();
					// 20111202 bao.ttc: check cac TH loi cung Tiep don va Ban kham nhung lai co 2 Tham kham
					if(!listMaTD.contains(maTD)){
						listMaTD.add(maTD);	
						
						BenhNhan benhNhan = thamKham.getTiepdonMa().getBenhnhanMa();
						if (benhNhan != null){
							Date ngaySinh = benhNhan.getBenhnhanNgaysinh();
							
							String sNgaySinh = "";
							if (ngaySinh != null){
								sNgaySinh = formatter.format(ngaySinh.getTime());
							} else if (benhNhan.getBenhnhanNamsinh() != null){
								sNgaySinh = benhNhan.getBenhnhanNamsinh();
							}
							
							buf.append("<record  matiepdon='" + thamKham.getTiepdonMa().getTiepdonMa() + "' hoten='"
									+ benhNhan.getBenhnhanHoten()  + "' tuoi='"
									+ "" + "' namsinh='"
									+ sNgaySinh  + "' cmnd='"
									+ "" + "' />");
	
						}
					}
				} // end FOR
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info(String.format("-----Error getDSBN: %s", e.toString()));
		}

		buf.append("</list>");
		//log.info("-----End getDSBN()-----");
		
		//System.out.println(" buf.toString():"+ buf.toString());
		
		return buf.toString();
	}
}


