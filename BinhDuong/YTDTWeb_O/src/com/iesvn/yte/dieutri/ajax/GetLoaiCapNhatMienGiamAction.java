package com.iesvn.yte.dieutri.ajax;

import com.iesvn.yte.Action;

public class GetLoaiCapNhatMienGiamAction extends Action {
	public String performAction(String request) throws Exception {
		StringBuffer buf = new StringBuffer();
		
		
		if (request != null && request.equals("1")){
			buf.append("<list>");
			buf.append("</list>");
			return buf.toString();
		}
		
		
		
		
		String capnhatmiengiam_ngaygiuongdieutri="Ng\u00E0y gi\u01B0\u1EDDng \u0111i\u1EC1u tr\u1ECB";
		String capnhatmiengiam_phantramchiphi="% chi ph\u00ED";
		String capnhatmiengiam_sotiencuthe="S\u1ED1 ti\u1EC1n c\u1EE5 th\u1EC3";
		String capnhatmiengiam_phantramtienmau="% ti\u1EC1n m\u00E1u";
		String capnhatmiengiam_tienkythuatcao="Ti\u1EC1n k\u1EF9 thu\u1EADt cao";
		
		
		buf.append("<list>");
		
		buf.append("<record MaSo='1' Ma='1' Ten='"+ capnhatmiengiam_ngaygiuongdieutri +"' NgayChinhSua='1'  DT='1'/>");
		buf.append("<record MaSo='2' Ma='2' Ten='"+ capnhatmiengiam_phantramchiphi +"' NgayChinhSua='1'  DT='1'/>");
		buf.append("<record MaSo='3' Ma='3' Ten='"+ capnhatmiengiam_sotiencuthe +"' NgayChinhSua='1'  DT='1'/>");
		buf.append("<record MaSo='4' Ma='4' Ten='"+ capnhatmiengiam_phantramtienmau +"' NgayChinhSua='1'  DT='1'/>");
		buf.append("<record MaSo='5' Ma='5' Ten='"+ capnhatmiengiam_tienkythuatcao +"' NgayChinhSua='1'  DT='1'/>");
		

		buf.append("</list>");
		return buf.toString();
	}
}


