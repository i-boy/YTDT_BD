package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.util.IConstantsRes;
import com.iesvn.yte.util.Utils;

public class GetThuocAndPhongAction extends Action {

	@Override
    public String performAction(String request) {
    	
		System.out.println(request);
		
		String maThuoc = "";
		String pdt = "";
		String maKhoa = "";
		maThuoc = request.substring(0,request.indexOf("___"));
		request = request.substring(request.indexOf("___")+3);
		pdt = request.substring(0,request.indexOf("___"));
		
		maKhoa = request.substring(request.indexOf("___")+3);
		
		System.out.println("maThuoc:"+maThuoc);
		System.out.println("pdt:"+pdt);
		System.out.println("maKhoa:"+maKhoa);
		
		if ("0".equals(pdt)){
			maKhoa = IConstantsRes.KHOA_KC_MA;
		}else { //tu truc (kho cua khoa)
			
		}
		
		/*
		 * 
		 * 
		 */
		int pos = maThuoc.indexOf("_");
		if (pos  > 0){
			maThuoc = maThuoc.substring(0,pos) + "\\" + maThuoc.substring(pos); 
		}		
		
        StringBuffer buf = new StringBuffer();
        buf.append("<list>");
        List<TonKho> listTonKho = null;
        
        try {
            TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
            listTonKho = tkDelegate.findTonKhoByDtmMaAndKhoMa(maThuoc,maKhoa);
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
        						"' Malk='" + tonkho.getTonkhoMalienket() + "' />");
    				}
    				
    			}
    		}
            else {
            	//DtDmGiaPhong[] listPhong = null ;
//            	DtDmGiaPhongWSService phongService = new DtDmGiaPhongWSServiceLocator();
//            	try {
//                    DtDmGiaPhongWS phongWS = phongService.getDtDmGiaPhongWSPort();
//                    DtDmGiaPhong phong = phongWS.findByDtdmgiaphongMa(request);
//                    if (phong != null) {
//            				System.out.println("Phong ma " + phong.getDtdmgiaphongMa());
//            				String phongma = phong.getDtdmgiaphongMa() == null ? "" : phong.getDtdmgiaphongMa();
//            				String phongten = phong.getDtdmgiaphongTen() == null ? "" : phong.getDtdmgiaphongTen();
//            				String dongiap = phong.getDtdmgiaphongDongia() == null ? "" : phong.getDtdmgiaphongDongia().toString();
//            				buf.append("<MAT_HANG MAPHU='' MATHANG_MASO='"  +
//            	                    "' MATHANG_MA='" + phongma +
//            	                    "' MATHANG_TEN='" + phongten +
//            	                    "' MATHANG_DONVI='"  +
//            	                    "' MATHANG_DONGIA='" + dongiap +
//            	                    "' MATHANG_TON='"  +
//            	                    "' MATHANG_LOAI='P' />");
//            			
//            		}
//                } catch (ServiceException e) {
//                    System.out.println(e.toString());
//                } catch (RemoteException e) {
//                    System.out.println(e.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            	
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        buf.append("</list>");
        return buf.toString();

    }
}

