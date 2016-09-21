package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.util.Utils;

public class GetNhanVienAction extends Action {
	public String performAction(String request) throws Exception{
		StringBuffer buf = new StringBuffer();
		List listNV = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listNV = dtutilDelegate.findByNgayGioCN(
					Double.parseDouble(request), "DtDmNhanVien", "dtdmnhanvienNgaygiocn");
		}
		catch (Exception ex) {
			
		}
    	
        buf.append("<list>");
       // buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
        if (listNV != null) {
	        for (Object obj : listNV) {
	        	DtDmNhanVien nv = (DtDmNhanVien)obj;
	        	buf.append("<record MaSo='" + nv.getDtdmnhanvienMaso() + 
	        			"' Ma='" + nv.getDtdmnhanvienMa() + 
	        			"' HocVi='" + nv.getDmhocviMaso(true).getDmhocviMaso() + 
	        			"' Ten='" + nv.getDtdmnhanvienMa() + " " + nv.getDtdmnhanvienTen() + 
	        			"' DT='" + Utils.reFactorString(nv.getDtdmnhanvienChon()) + 
	        			"' NgayChinhSua='" + nv.getDtdmnhanvienNgaygiocn() + "' />");
	        }
	        
	        /*
			DMHOCVI_MASO: new JStORM.Field({ type: "String", maxLength: 10}),
			NHOM_MA: new JStORM.Field({ type: "String", maxLength: 8}),
			DTDMNHANVIEN_GIOITINH: new JStORM.Field({ type: "String", maxLength: 3}),
			DTDMNHANVIEN_NGAYSINH: new JStORM.Field({ type: "String"}),
			DTDMNHANVIEN_MAHH: new JStORM.Field({ type: "String", maxLength: 8}),
			DTDMNHANVIEN_BIENCHE: new JStORM.Field({ type: "String", maxLength: 1}),
			DTDMNHANVIEN_DIACHI: new JStORM.Field({ type: "String", maxLength: 50}),
			DTDMNHANVIEN_MOBILE: new JStORM.Field({ type: "String", maxLength: 12}),
			DTDMNHANVIEN_EMAIL: new JStORM.Field({ type: "String", maxLength: 50}),
			DTDMNHANVIEN_KYPHIEU: new JStORM.Field({ type: "Integer"}),
			DTDMNHANVIEN_DUYET: new JStORM.Field({ type: "Integer"}),
			DTDMNHANVIEN_MO: new JStORM.Field({ type: "Integer"}),
			DTDMNHANVIEN_NGHIVIEC: new JStORM.Field({ type: "Integer"}),
			DTDMNHANVIEN_MATMA: new JStORM.Field({ type: "String", maxLength: 20}),
			DTDMNHANVIEN_TKATM: new JStORM.Field({ type: "String", maxLength: 12}),
			DTDMNHANVIEN_SOBHXH: new JStORM.Field({ type: "String", maxLength: 12}),
			ND_TENDANGNHAP: new JStORM.Field({ type: "String", maxLength: 64}),
			*/
        }
        buf.append("</list>");
        return buf.toString();
	}
}


