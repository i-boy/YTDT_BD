package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

public class GetTonKhoByPhanLoaiThuocAction extends Action {

	@Override
    public String performAction(String request) {
    	
		System.out.println("aaa:"+request);
		
		String maThuoc = "";
		String isTutruc = "";
		Integer masoKhoa = 0;
		maThuoc = request.substring(0,request.indexOf("___"));
		request = request.substring(request.indexOf("___")+3);
		isTutruc = request.substring(0,request.indexOf("___"));
		
		masoKhoa = Integer.valueOf(request.substring(request.indexOf("___")+3));
		
		if ("0".equals(isTutruc)){
			masoKhoa = 0;
		}
		System.out.println("maThuoc: "+maThuoc);	
		System.out.println("masoKhoa: "+masoKhoa);
		System.out.println("isTutruc: "+isTutruc);	
        StringBuffer buf = new StringBuffer();       
        buf.append("<list>");
        List<TonKho> listTonKho = null;
        Integer masoThuoc = null;
        try {
            TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
            if(maThuoc.length() >0){            	 
                 try{
                 	masoThuoc = Integer.parseInt(maThuoc);
                 }catch(NumberFormatException er){
                 	er.printStackTrace();
                 }
            	listTonKho = tkDelegate.findTonKhoByKhoaMasoThuocMaso(masoThuoc,masoKhoa);
            }
            if (listTonKho != null) {
    			for (TonKho tonkho : listTonKho) {
    				if (tonkho.getTonkhoTon() > 0) {
    					System.out.println("Ton kho ma " + tonkho.getTonkhoMa());
        				String tonkhoma = tonkho.getTonkhoMa() == null ? "" : tonkho.getTonkhoMa().toString();
        				String dmthuocma = tonkho.getDmthuocMaso() == null ? "" : tonkho.getDmthuocMaso().getDmthuocMa();
        				String dmthuocten = tonkho.getDmthuocMaso() == null ? "" : tonkho.getDmthuocMaso().getDmthuocTen();
        				String donvi = (tonkho.getDmthuocMaso() == null || tonkho.getDmthuocMaso().getDmdonvitinhMaso()==null )? "" :  tonkho.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhMa();
        				String dongia = tonkho.getTonkhoDongia() == null ? "" : Utils.formatNumber(tonkho.getTonkhoDongia(), "##.##") ;
        				String ton = tonkho.getTonkhoTon() == null ? "" :  Utils.formatNumber(tonkho.getTonkhoTon(), "##.##") ;
        				String loai = tonkho.getDmthuocMaso().getDmthuocYcu() == null ? "" : tonkho.getDmthuocMaso().getDmthuocYcu().toString();
        				String dmKhoaMaso = tonkho.getDmkhoaMaso().getDmkhoaMaso().toString(); 
        				String mathangloai="";
        				if(loai == "true"){
        					mathangloai = "Y";
        				}
        				else {
        					mathangloai = "T";
        				}
        				String hamLuong = "";
        				if (tonkho.getDmthuocMaso().getDmthuocHamluong() != null) {
        					hamLuong = tonkho.getDmthuocMaso().getDmthuocHamluong();
        				}
        				String nuocSx = "";
        				if (tonkho.getDmquocgiaMaso(true).getDmquocgiaTen() != null) {
        					nuocSx = tonkho.getDmquocgiaMaso(true).getDmquocgiaTen();
        				}
        				String hangSx = "";
        				if (tonkho.getDmnhasanxuatMaso(true).getDmnhasanxuatTen() != null) {
        					nuocSx = tonkho.getDmnhasanxuatMaso(true).getDmnhasanxuatTen();
        				}
        				
        				 String hanDung = "";
        				 if (tonkho.getTonkhoNgayhandung() != null && tonkho.getTonkhoThanghandung() != null && tonkho.getTonkhoNamhandung() != null){
        					 hanDung = tonkho.getTonkhoNgayhandung() + "/" + tonkho.getTonkhoThanghandung() + "/" + tonkho.getTonkhoNamhandung();
        				 }else if (tonkho.getTonkhoThanghandung() != null && tonkho.getTonkhoNamhandung() != null){
        					 hanDung =  tonkho.getTonkhoThanghandung() + "/" + tonkho.getTonkhoNamhandung();
        				 }
        				
        				 Boolean YC = tonkho.getDmthuocMaso().getDmthuocYeucau();
        				 if (YC == null){
        					 YC = new Boolean(false);
        				 }
        				 
        				 Boolean khongThu = tonkho.getDmthuocMaso().getDmthuocKhongthu();
        				 if (khongThu == null){
        					 khongThu = new Boolean(false);
        				 }
        				 
        				 Boolean mienPhi = tonkho.getDmthuocMaso().getDmthuocMien();
        				 if (mienPhi == null){
        					 mienPhi = new Boolean(false);
        				 }
        				 
        				 Boolean trongDm = tonkho.getDmthuocMaso().getDmthuocIndanhmuc();
        				 Boolean ndm = false;
        				 if (trongDm == null || trongDm.booleanValue()==false){
        					 ndm = new Boolean(true);
        				 }else{
        					 ndm = new Boolean(false);
        				 }
        				
        				buf.append("<record TonKhoMa='" + tonkhoma + 
        						"' MaHang='" + dmthuocma + 
        						"' TenHang='" + dmthuocten + 
        						"' HamLuong='" + hamLuong + 
        						"' TonKho='" + ton + 
        						"' HangSx='" + hangSx + 
        						"' DonVi='" + donvi + 
        						"' HanDung='" + hanDung + 
        						"' DonGia='" + dongia + 
        						"' Loai='" + mathangloai +
        						"' NuocSx='" + nuocSx +        						
        						"' MP='"	+ mienPhi + 
        						"' YC='"+ YC + 
        						"' NDM='" + ndm + 
        						"' KhongThu='" + khongThu +
        						"' DmKhoaMaso='" + dmKhoaMaso +
        						"' Malk='" + tonkho.getTonkhoMalienket() + "' />");
    				}
    				
    			}
    		}
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        buf.append("</list>");
        return buf.toString();

    }
}

