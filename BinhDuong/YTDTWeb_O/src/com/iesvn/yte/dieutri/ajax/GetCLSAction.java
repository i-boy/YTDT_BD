package com.iesvn.yte.dieutri.ajax;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.utils.DieuTriUtilFacade;

public class GetCLSAction extends Action{
	
	@Override
    public String performAction(String request) {    	
		
		//System.out.println(request);
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
        	 TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();
        	 ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();
        	 ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
        	
             TiepDon td = tdDelegate.find(request);
             
             if (td == null){
             	buf.append("</list>");
                 return buf.toString();
             }
             
             System.out.println("td ma " + td.getTiepdonMa());
             
             mabaohiem = td.getTiepdonSothebh() == null ? "" : td.getTiepdonSothebh();
        	 noidangky = td.getKcbbhytMa() == null ? "" : td.getKcbbhytMa().getDmbenhvienTen();
        	 madangky = td.getKcbbhytMa() == null ? "" : td.getKcbbhytMa().getDmbenhvienMa();
        	 
        	 if (td.getTiepdonGiatri2() != null) {
                 SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                 Date cal = td.getTiepdonGiatri2();
                 ngayhethan = df.format(cal.getTime());
             }  
        	 
             BenhNhan bn = td.getBenhnhanMa();
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
             
             ThamKham tk = tkDelegate.findByMaTiepDon(td.getTiepdonMa());             
             String bankham = tk.getThamkhamBankham(true).getDtdmbankhamTen();
             String chuandoan = tk.getBenhicd10(true).getDmbenhicdTen();
             String chuandoanBD = td.getTiepdonMachdoanbd(true).getDmbenhicdTen();
             String bacsi = tk.getThamkhamBacsi() != null ? tk.getThamkhamBacsi().getDtdmnhanvienTen() : "";
             
             List<ClsKham> listClsKham = (List<ClsKham>)clsKhamDelegate.findByTiepdonmaAndKhoa(td.getTiepdonMa(), "30");
             soluongCLS = listClsKham.size() + "";
             
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
             for(int i = 0; i < listClsKham.size(); i++){             
	             result += "<CLS ";
	             ClsKham clsKham = listClsKham.get(i);
	             String loaiCLS = "";
	             String ngaykham = "";
	             String maCLS = "";
	             if (clsKham.getClskhamNgaygio() != null) {
	                 SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	                 Date cal = clsKham.getClskhamNgaygio();
	                 ngaykham = df.format(cal.getTime());
	             }
	             maCLS = clsKham.getClskhamMahang() == null ? "" : clsKham.getClskhamMahang().getDtdmclsbgMa();
	             loaiCLS = clsKham.getClskhamMahang() == null ? "" : clsKham.getClskhamMahang().getDtdmclsbgDiengiai();
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
	     //System.out.println(buf.toString()); //bao.ttc
	     return buf.toString();

 }

}
