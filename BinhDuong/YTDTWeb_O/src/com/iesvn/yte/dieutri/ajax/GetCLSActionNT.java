package com.iesvn.yte.dieutri.ajax;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaBhytDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsMo;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaBhyt;
import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;

public class GetCLSActionNT extends Action{
	
	@Override
    public String performAction(String request) {    	
		
		System.out.println("##### GetCLSAction #####");
		String hotenbenhnhan = "";
		String tuoi = "";
		String donvituoi = "";
		String namsinh = "";
		String gioitinh = "";
		String diachi = "";
		String mabaohiem = "";
		String noidangky = "";
		String madangky = "";
		String ngayhethan = "";
		String soluongCLS = "";
		
        StringBuffer buf = new StringBuffer();
        buf.append("<list>");
        try {
        	 HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
        	 ClsMoDelegate clsMoDelegate = ClsMoDelegate.getInstance();
        	 HsbaBhytDelegate hsbaBhytDelegate = HsbaBhytDelegate.getInstance();
        	 if(request.startsWith("NO")){
        		 request = request.replaceFirst("NO", "BD");
        	 }
             Hsba hsba = hsbaDelegate.find(request);
             
             if (hsba == null){
             	buf.append("</list>");
                 return buf.toString();
             }
             
             System.out.println("hsbasovaovien: " + hsba.getHsbaSovaovien());
             
             List<HsbaBhyt> listTmp = hsbaBhytDelegate.findBySoVaoVien(hsba.getHsbaSovaovien()); 
             if(listTmp != null && listTmp.size() > 0){
            	 HsbaBhyt hsbaBhyt = listTmp.get(listTmp.size()-1);
            	 mabaohiem = hsbaBhyt == null ? "" : hsbaBhyt.getHsbabhytSothebh();
            	 noidangky = hsbaBhyt.getHsbabhytMakcb() == null ? "" : hsbaBhyt.getHsbabhytMakcb().getDmbenhvienTen();            	 
            	 madangky = hsbaBhyt.getHsbabhytMakcb() == null ? "" : hsbaBhyt.getHsbabhytMakcb().getDmbenhvienMa();
            	 if(hsbaBhyt.getHsbabhytGiatri1() != null){
	            	 SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	                 Date cal = hsbaBhyt.getHsbabhytGiatri1();
	                 ngayhethan = df.format(cal.getTime());
            	 }
             }
        	 
             BenhNhan bn = hsba.getBenhnhanMa();
             if(bn != null){
            	 hotenbenhnhan = bn.getBenhnhanHoten();
            	 tuoi = bn.getBenhnhanTuoi()+"";
            	 if(bn.getBenhnhanDonvituoi() == 1){
            		 donvituoi = "n\u0103m";
            	 }else
            	 if(bn.getBenhnhanDonvituoi() == 2){
            		 donvituoi = "th\u00E1ng";
            	 }else
            	 if(bn.getBenhnhanDonvituoi() == 3){
            		 donvituoi = "ng\u00E0y";
            	 }            	
            	 namsinh = bn.getBenhnhanNamsinh();
            	 gioitinh = bn.getDmgtMaso() == null ? "" : bn.getDmgtMaso().getDmgtTen();
            	 diachi = bn.getBenhnhanDiachi();            	   	 
             }
             
             
             // 20120314 bao.ttc: KHOA-TANG : Ko can dung hsbaKhoa o day
             String bankham = "";
             if (hsba.getHsbaKhoadangdt() != null){
            	 bankham = hsba.getHsbaKhoadangdt().getDmkhoaTen();
             }
             
             HsbaChuyenMon hsbaChuyenMon = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_LastHSBACM(hsba.getHsbaSovaovien());
             String chuandoan = hsbaChuyenMon.getHsbacmBenhchinh(true).getDmbenhicdTen();
             String chuandoanBD = "";
             String bacsi = hsbaChuyenMon.getHsbacmBacsi(true).getDtdmnhanvienTen();
             
             List<ClsMo> listClsMoTemp = (List<ClsMo>)clsMoDelegate.findBySoVaoVien(hsba.getHsbaSovaovien());
             List<ClsMo> listClsMo = new ArrayList<ClsMo>();
             
             for(ClsMo c : listClsMoTemp){
            	 if(c.getClsmoKhoa() != null && c.getClsmoKhoa().getDmkhoaMaso() == 30){
            		 listClsMo.add(c);            		
            	 }
             }             
           
             soluongCLS = listClsMo.size() + "";
             
             String result = "";
             result += "<RECORD ";
             result += "HOTEN='"+ hotenbenhnhan + "' ";
             result += "TUOI='" + tuoi + "' ";
             result += "DONVITUOI='" + donvituoi + "' ";
             result += "NAMSINH='" + namsinh + "' ";
             result += "GIOITINH='" + gioitinh + "' ";
             result += "DIACHI='" + diachi + "' ";
             result += "MABAOHIEM='" + mabaohiem + "' ";
             result += "MADANGKY='" + madangky + "' ";
             result += "NOIDANGKY='" + noidangky + "' ";
             result += "NGAYHETHAN='" + ngayhethan + "' ";     
             result += "BANKHAM='" + bankham + "' ";     
             result += "CHUANDOAN='" + chuandoan + "' ";     
             result += "CHUANDOANBD='" + chuandoanBD + "' ";     
             result += "BACSI='" + bacsi + "' ";    
             result += " >";
             
             result += "<LISTCLS ";
             result += "SOLUONG='" + soluongCLS + "' ";
             result += " >";
             for(int i = 0; i < listClsMo.size(); i++){             
	             result += "<CLS ";
	             ClsMo clsMo = listClsMo.get(i);
	             String loaiCLS = "";
	             String ngaykham = "";
	             String maCLS = "";
	             if (clsMo.getClsmoNgay() != null) {
	                 SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	                 Date cal = clsMo.getClsmoNgay();
	                 ngaykham = df.format(cal.getTime());
	             }
	             maCLS = clsMo.getClsmoMahang() == null ? "" : clsMo.getClsmoMahang().getDtdmclsbgMa();
	             loaiCLS = clsMo.getClsmoMahang() == null ? "" : clsMo.getClsmoMahang().getDtdmclsbgDiengiai();
	             result += "LOAICLS='" + loaiCLS + "' ";
	             result += "NGAYKHAM='" + ngaykham + "' ";
	             result += "MACLS='" + maCLS + "' ";
	             result += " />";
             }
             
             result += " </LISTCLS>";
             
             result += " </RECORD>";
             buf.append(result);
//             buf.append("<RESULT " +
//             			"HOTEN='"+ hotenbenhnhan + "' " +
//             			"TUOI='" + tuoi + "' " +
//             			"DONVITUOI='" + donvituoi + "' " +
//             			"NAMSINH='" + namsinh + "' " +
//             			"DIACHI='" + diachi + "' " +
//             			"MABAOHIEM='" + mabaohiem + "' " +
//             			"NOIDANGKY='" + noidangky + "' " +
//             			"NGAYHETHAN='" + ngayhethan + "' " +             			
//            		    " />");
             
		 } catch (Exception e) {
	         e.printStackTrace();
	     }
	     buf.append("</list>");
	     return buf.toString();

 }
	

}
