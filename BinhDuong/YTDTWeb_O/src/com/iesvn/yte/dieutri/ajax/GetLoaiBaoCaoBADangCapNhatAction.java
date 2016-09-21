package com.iesvn.yte.dieutri.ajax;

import com.iesvn.yte.Action;

public class GetLoaiBaoCaoBADangCapNhatAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		
		
		if (request != null && request.equals("1")){
			buf.append("<list>");
			buf.append("</list>");
			return buf.toString();
		}
		
		
		String str1 = "\ufeffB\u1ec7nh \u00e1n ch\u01b0a \u0111\u01b0\u1ee3c khoa n\u00e0o c\u1eadp nh\u1eadt";
		String str2= "B\u1ec7nh \u00e1n \u0111ang c\u1eadp nh\u1eadt";
		String str3 = "B\u1ec7nh \u00e1n ch\u01b0a c\u1eadp nh\u1eadt ng\u00e0y ra vi\u1ec7n";
		String str4 = "B\u1ec7nh \u00e1n \u0111\u00e3 c\u1eadp nh\u1eadt ng\u00e0y ra vi\u1ec7n";
		
		buf.append("<list>");
		
		buf.append("<record MaSo='1' Ma='1' Ten='"+ str1 +"' NgayChinhSua='1'  DT='1'/>");
		buf.append("<record MaSo='2' Ma='2' Ten='"+ str2 +"' NgayChinhSua='1'  DT='1'/>");
		buf.append("<record MaSo='3' Ma='3' Ten='"+ str3 +"' NgayChinhSua='1'  DT='1'/>");
		buf.append("<record MaSo='4' Ma='4' Ten='"+ str4 +"' NgayChinhSua='1'  DT='1'/>");

		buf.append("</list>");
		return buf.toString();
	}
}


