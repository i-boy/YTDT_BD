package com.iesvn.yte.dieutri.ajax;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.TiepDon;

public class GetCLSBangGiaCDHA extends Action{
	
	@Override
    public String performAction(String request) {    	
		
		//System.out.println(request);
		System.out.println("##### GetCLSBangGiaCDHA #####");
		
		
        StringBuffer buf = new StringBuffer();
        buf.append("<list>");
        try {
        	 ArrayList<DtDmClsBangGia> dtDmClsBangGiaList = (ArrayList<DtDmClsBangGia>) DtDmClsBangGiaDelegate.getInstance().getDtDmClsBangGiaByMaSoKhoa(30);
        	        
             String result = "";
             result += "<RECORD ";            
             result += " >";
             
             result += "<LISTCLS ";             
             result += " >";
             for(int i = 0; i < dtDmClsBangGiaList.size(); i++){             
	             result += "<CLS ";
	             DtDmClsBangGia dtDmClsBangGia = dtDmClsBangGiaList.get(i);
	             String diengiaiCLS = "";
	             String dongia = "";
	             String maCLS = "";
	             String khoaCDHA = "";	            
	             maCLS = dtDmClsBangGia.getDtdmclsbgMa();
	             diengiaiCLS = dtDmClsBangGia.getDtdmclsbgDiengiai();
	             dongia = dtDmClsBangGia.getDtdmclsbgDongia() + "";
	             khoaCDHA = dtDmClsBangGia.getDtdmclsbgCdha();
	             result += "DIENGIAICLS='" + diengiaiCLS + "' ";
	             result += "DONGIA='" + dongia + "' ";
	             result += "MACLS='" + maCLS + "' ";
	             result += "KHOACDHA='" + khoaCDHA + "' ";
	             result += " />";
             }
             
             result += " </LISTCLS>";
             
             result += " </RECORD>";
             buf.append(result);
		 } catch (Exception e) {
	         e.printStackTrace();
	     }
	     buf.append("</list>");
	     //System.out.println(buf.toString()); //bao.ttc
	     return buf.toString();

 }

}
