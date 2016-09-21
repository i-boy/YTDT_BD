package com.iesvn.yte.dieutri.ajax;

import java.util.List;


import com.iesvn.yte.Action;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmDongThem;

public class GetDtDmDongThemAction extends Action {
	/*public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		List<DtDmDongThem> list = DtDmDongThemDelegate.getInstance().findAll();
		JSONArray arr = new JSONArray();
		JSONObject i = new JSONObject();
		i.put("id", "");
		i.put("title", "");
		arr.put(i);
		for (DtDmDongThem o : list) {
			JSONObject item = new JSONObject();
			item.put("id", o.getDtdmdtMaso()+"");
			item.put("title", o.getDtdmdtTen());
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
			list = dtutilDelegate.findByNgayGioCN(Double.parseDouble(request), "DtDmDongThem", "dtdmdtNgaygiocn");
	} catch (Exception ex) {

		}

		buf.append("<list>");
		if (list != null) {
			for (Object obj : list) {
				DtDmDongThem pt = (DtDmDongThem)obj;
				buf.append("<record " 
						+ " MaSo='"	+ pt.getDtdmdtMaso() + "'"
						+ " Ma='"	+ pt.getDtdmdtMa() + "'"						
						+ " Ten='"	+ pt.getDtdmdtTen() + "'" 
						+ " NgayChinhSua='"	+ pt.getDtdmdtNgaygiocn() + "'"
						+ " DT='1'"
						+ "/>");
				
				
			}
		}
		buf.append("</list>");
	
		return buf.toString();
	}
}


