package com.iesvn.yte.dieutri.ajax;

import com.iesvn.yte.Action;

public class GetLoaiXoaHoSoBenhAnAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		
		
		if (request != null && request.equals("1")){
			buf.append("<list>");
			buf.append("</list>");
			return buf.toString();
		}
		
		
		String str1 = "Ng\u00E0y gi\u1EDD chuy\u1EC3n khoa";
		String str2= "Ng\u00E0y xu\u1EA5t vi\u1EC7n";
		String str3 = "H\u1ED3 s\u01A1 b\u1EC7nh \u00E1n";
		
		
		buf.append("<list>");
		
		buf.append("<record MaSo='1' Ma='1' Ten='"+ str1 +"' NgayChinhSua='1'  DT='1'/>");
		buf.append("<record MaSo='2' Ma='2' Ten='"+ str2 +"' NgayChinhSua='1'  DT='1'/>");
		buf.append("<record MaSo='3' Ma='3' Ten='"+ str3 +"' NgayChinhSua='1'  DT='1'/>");
		

		buf.append("</list>");
		return buf.toString();
	}
}


