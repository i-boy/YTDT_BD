package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmNgheNghiep;
import com.iesvn.yte.util.Utils;

public class GetDmNgheAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List listDmNghe = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listDmNghe = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DmNgheNghiep", "dmnghenghiepNgaygiocn");
	} catch (Exception ex) {

		}

		buf.append("<list>");
		//buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
		if (listDmNghe != null) {
			for (Object obj : listDmNghe) {
				DmNgheNghiep dmNghe = (DmNgheNghiep) obj;
				String ngheNghiepBC = "";
				if (dmNghe.getDmnghenghiepPhanloai() != null){
					ngheNghiepBC = String.valueOf(dmNghe.getDmnghenghiepPhanloai().getDmnghenghiepbcMaso());
				}
				buf.append("<record " +
						"  MaSo='" + dmNghe.getDmnghenghiepMaso() + 
						"' Ma='" + dmNghe.getDmnghenghiepMa() + 
						"' Ten='" + Utils.findAndreplace(dmNghe.getDmnghenghiepTen()) +
						"' DMNGHENGHIEP_PHANLOAI='" +ngheNghiepBC +
						"' DMNGHENGHIEP_PHANLOAI2='" + Utils.reFactorString(dmNghe.getDmnghenghiepPhanloai2()) +
						"' DMNGHENGHIEP_AGEMIN='" + Utils.reFactorString(dmNghe.getDmnghenghiepAgemin()) +
						"' DMNGHENGHIEP_AGEMAX='" + Utils.reFactorString(dmNghe.getDmnghenghiepAgemax()) +
						"' NgayChinhSua='" + dmNghe.getDmnghenghiepNgaygiocn() +
						"' DT='" + Utils.reFactorString (dmNghe.getDmnghenghiepDt()) + 
						"' QL='" + Utils.reFactorString (dmNghe.getDmnghenghiepQl()) + 
						"' DP='" +Utils.reFactorString ( dmNghe.getDmnghenghiepDp()) + 
						"' />");
			}
		}
		buf.append("</list>");
	
		return buf.toString();
	}

}


