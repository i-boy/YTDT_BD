package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmDoiTuongAn;

public class GetDtDmDoiTuongAnAction extends Action {
	/*public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List<DtDmDoiTuongAn> list = DtDmDoiTuongAnDelegate.getInstance().findAll();
		JSONArray arr = new JSONArray();
		JSONObject i = new JSONObject();
		i.put("id", "");
		i.put("title", "");
		arr.put(i);
		for (DtDmDoiTuongAn o : list) {
			JSONObject item = new JSONObject();
			item.put("id", o.getDtdmdtaMaso()+"");
			item.put("title", o.getDtdmdtaTen());
			arr.put(item);
		}
		JSONObject result = new JSONObject();
		result.put("list", arr);
		buf.append(result);
		return buf.toString();
	}*/
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List list = null;
		try {
			DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			list = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DtDmDoiTuongAn", "dtdmdtaNgaygiocn");
	} catch (Exception ex) {

		}

		buf.append("<list>");
		if (list != null) {
			for (Object obj : list) {
				DtDmDoiTuongAn pt = (DtDmDoiTuongAn)obj;
				buf.append("<record " 
						+ " MaSo='"	+ pt.getDtdmdtaMaso() + "'"
						+ " Ma='"	+ pt.getDtdmdtaMa() + "'"						
						+ " Ten='"	+ pt.getDtdmdtaTen() + "'" 
						+ " NgayChinhSua='"	+ pt.getDtdmdtaNgaygiocn() + "'"
						+ " DT='1'" 
						+ "/>");
				
				
			}
		}
		buf.append("</list>");
	
		return buf.toString();
	}
}


