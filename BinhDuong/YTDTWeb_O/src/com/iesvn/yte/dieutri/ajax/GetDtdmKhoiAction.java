package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
import com.iesvn.yte.util.Utils;


public class GetDtdmKhoiAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List list = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			list =  dtutilDelegate.findByNgayGioCN(Double
					.parseDouble(request), "DtDmKhoiBhyt",
					"dtdmkhoibhytNgaygiocn");
		} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (list != null) {
			for (Object obj: list) {
				DtDmKhoiBhyt dmkhoibhyt = (DtDmKhoiBhyt) obj;
				String loaiBHYT = "";
				if (dmkhoibhyt.getDtdmkhoibhytLoai()!=null){
					loaiBHYT = String.valueOf( dmkhoibhyt.getDtdmkhoibhytLoai().getDtdmloaibhytMaso());
				}
				String ploaiBHYT = "";
				if (dmkhoibhyt.getDtdmkhoibhytPhanloai()!=null){
					ploaiBHYT = String.valueOf( dmkhoibhyt.getDtdmkhoibhytPhanloai().getDtdmphloaibhytMaso());
				}
				
				buf.append("<record " +
						"MaSo='" + dmkhoibhyt.getDtdmkhoibhytMaso() + 
						"' Ma='" + dmkhoibhyt.getDtdmkhoibhytMa() + 
						"' Ten='"		+ dmkhoibhyt.getDtdmkhoibhytTen() + 
						"' NgayChinhSua='"	+ dmkhoibhyt.getDtdmkhoibhytNgaygiocn() +
						"' DTDMKHOIBHYT_TYLEBHYT1='"	+ Utils.reFactorString (dmkhoibhyt.getDtdmkhoibhytTylebhyt1()) +
						"' DTDMKHOIBHYT_TYLEBHYT2='"	+ Utils.reFactorString (dmkhoibhyt.getDtdmkhoibhytTylebhyt2()) +
						"' DTDMKHOIBHYT_TYLEKTC='"	+ Utils.reFactorString (dmkhoibhyt.getDtdmkhoibhytTylektc()) +
						
						"' DTDMKHOIBHYT_GHTYLE='"	+ Utils.reFactorString (dmkhoibhyt.getDtdmkhoibhytGioiHanTyLe()) +
						"' DTDMKHOIBHYT_MINKTC='"	+ Utils.reFactorString (dmkhoibhyt.getDtdmkhoibhytMinKTC()) +
						"' DTDMKHOIBHYT_TLMINKTC='"	+ Utils.reFactorString (dmkhoibhyt.getDtdmkhoibhytTLMinKTC()) +
						"' DTDMKHOIBHYT_MAXKTC='"	+ Utils.reFactorString (dmkhoibhyt.getDtdmkhoibhytMaxKTC()) +
						"' DTDMKHOIBHYT_TLMAXKTC='"	+ Utils.reFactorString (dmkhoibhyt.getDtdmkhoibhytTLMaxKTC()) +
						
						"' DTDMKHOIBHYT_GOMYC='"	+ Utils.reFactorString (dmkhoibhyt.getDtdmkhoibhytGomyc()) +
						"' DTDMKHOIBHYT_LOAI='"	+loaiBHYT +
						"' DTDMKHOIBHYT_GHICHU='"	+ Utils.reFactorString (dmkhoibhyt.getDtdmkhoibhytGhichu() )+
						"' DTDMKHOIBHYT_PHANLOAI='"	+ ploaiBHYT +
						
						"' DT='"	+ Utils.reFactorString (dmkhoibhyt.getDtdmkhoibhytChon()) +
						"' />");
				
			}
		}
		buf.append("</list>");
	
		return buf.toString();
	}

}


