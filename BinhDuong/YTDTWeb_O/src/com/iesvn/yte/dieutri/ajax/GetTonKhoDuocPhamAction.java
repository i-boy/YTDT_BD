package com.iesvn.yte.dieutri.ajax;

import java.text.DecimalFormat;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.util.Utils;

public class GetTonKhoDuocPhamAction extends Action {
	private static Logger log = Logger.getLogger(GetTonKhoDuocPhamAction.class);
	public String performAction(String request) throws Exception {
		log.info("-----Begin getTonKho()-----" + request);
		StringBuffer buf = new StringBuffer();
		String maHang = "";
		String kinhphiMa = "";
		String nguonMa= "";
		String khoaXuat= "";
		StringTokenizer sToken = new StringTokenizer(request,";");
		if (sToken != null && sToken.countTokens() >= 4 ){
			
		//	while (sToken.hasMoreTokens()){
			  maHang  = sToken.nextToken().trim();
			  kinhphiMa = sToken.nextToken().trim();
			  nguonMa = sToken.nextToken().trim();
			  khoaXuat = sToken.nextToken().trim();
			
		//	}
		}
		
//		if ("".equals(maHang.trim()) ){
//			maHang = "%";
//		}
//		if ("".equals(kinhphiMa.trim()) ){
//			kinhphiMa = "%";
//		}
//		if ("".equals(nguonMa.trim()) ){
//			nguonMa = "%";
//		}
//		if ("".equals(khoaXuat.trim()) ){
//			khoaXuat = "%";
//		}
		
		/*
		 * 
		 * 
		 */
//		int pos = maHang.indexOf("_");
//		if (pos  > 0){
//			maHang = maHang.substring(0,pos) + "\\" + maHang.substring(pos); 
//		}		
		
       
		
		List<TonKho> listTk = null;
		
		try {
			TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
			listTk = tkDelegate.findDanhSachTonKho(maHang.toUpperCase(), kinhphiMa, nguonMa, khoaXuat);
			//listTk = tkWS.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(String.format("-----Error: %s", e.toString()));
		}
		
		

		buf.append("<list>");
		//buf.append("<record  MaHang='' TenHang=''  QuyCach=''  DonVi=''  TonKho=''  DonGia='' TonKhoMa='' />");
		if (listTk != null) {
			for (TonKho tonkho : listTk) {
				if (tonkho.getTonkhoTon() > 0) {
					
					String ten = tonkho.getDmthuocMaso().getDmthuocTen();
					ten = Utils.findAndreplace(ten);
					
					String donViTinhTen = "";
					if (tonkho.getDmthuocMaso().getDmdonvitinhMaso() != null){
						donViTinhTen = tonkho.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhTen();
					} else {
						donViTinhTen = " ";
					}
					
					//ten = formatString(ten, 30);
					//donViTinhTen = formatString(donViTinhTen, 10);
					DecimalFormat df = new DecimalFormat("###.##");
					String ton = df.format(tonkho.getTonkhoTon().doubleValue());
					String dg = df.format(tonkho.getTonkhoDongia());
					String mahang = tonkho.getDmthuocMaso().getDmthuocMa();
					String hamluong = tonkho.getDmthuocMaso().getDmthuocHamluong();
					if (hamluong == null) {
						hamluong = "";
					}
					String nuocSx = tonkho.getDmquocgiaMaso().getDmquocgiaTen();
					String hangSx = tonkho.getDmnhasanxuatMaso().getDmnhasanxuatTen();
					
					
					 String hd = "";
    				 if (tonkho.getTonkhoNgayhandung() != null && tonkho.getTonkhoThanghandung() != null && tonkho.getTonkhoNamhandung() != null){
    					 hd = tonkho.getTonkhoNgayhandung() + "/" + tonkho.getTonkhoThanghandung() + "/" + tonkho.getTonkhoNamhandung();
    				 }else if (tonkho.getTonkhoThanghandung() != null && tonkho.getTonkhoNamhandung() != null){
    					 hd =  tonkho.getTonkhoThanghandung() + "/" + tonkho.getTonkhoNamhandung();
    				 }
    				
					/*
					String tenHang = ten + " " + hamluong + " " + ton + " " + donViTinhTen + " " 
										+ dg + " " + hangSx + " " + nuocSx;
					
					buf.append("<record Ma='" + tonkho.getTonkhoMa() + "' Ten='" + tenHang + "' />");
					*/
					
					buf.append("<record  MaHang='" + mahang + 
							"' TenHang='" + ten +  
							"' DonVi='" + donViTinhTen + 
							"' TonKho='" + ton + 
							"' DonGia='" + dg + 
							"' TonKhoMa='" + tonkho.getTonkhoMa() + 
							"' HamLuong='" + hamluong + 
							"' NuocSx='" + nuocSx + 
							"' HangSx='" + hangSx + 
							"' HanDung='" + hd + 
							"' Malk='" + tonkho.getTonkhoMalienket() + "' />");
					
				}
			}
		}
		buf.append("</list>");
		log.info("-----End getTonKho()-----" + buf.toString());
		return buf.toString();
	}
	
}


