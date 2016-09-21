package com.iesvn.yte.dieutri.ajax;

import java.util.List;

import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmNhaCungCap;
import com.iesvn.yte.util.Utils;

public class GetNoiBanAction extends Action {
    public String performAction(String request) throws Exception{
    	StringBuffer buf = new StringBuffer();
    	List listNoiBan = null;
    	try {
    		DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
			listNoiBan =  dtutilDelegate.findByNgayGioCN(
					Double.parseDouble(request), "DmNhaCungCap", "dmnhacungcapNgaygiocn");
    	}
    	catch (Exception ex) {
    		
    	}
    	
        buf.append("<list>");
      //  buf.append("<record MaSo='' Ten='' NgayChinhSua='' />");
        if (listNoiBan != null) {
	        for (Object obj : listNoiBan) {
	        	DmNhaCungCap nb = (DmNhaCungCap) obj;
	        	buf.append("<record MaSo='" + nb.getDmnhacungcapMaso() + 
	        			"' Ma='" + nb.getDmnhacungcapMa() + 
	        			"' Ten='" + nb.getDmnhacungcapTen() + 
	        			"' QL='" + Utils.reFactorString(nb.getDmnhacungcapQl()) + 
	        			"' DT='" + Utils.reFactorString(nb.getDmnhacungcapDt()) + 
	        			"' DP='" + Utils.reFactorString(nb.getDmnhacungcapDp()) + 
	        			"' NgayChinhSua='" + nb.getDmnhacungcapNgaygiocn() + "' />");
	        }
	        /*
	        MaSo: new JStORM.Field({ type: "Integer" }),
			Ma: new JStORM.Field({ type: "String", maxLength: 10}),
			DMTINH_MASO: new JStORM.Field({ type: "String", maxLength: 10}),
			DMNCT_MASO: new JStORM.Field({ type: "String", maxLength: 10}),
			Ten: new JStORM.Field({ type: "String", maxLength: 45}),
			DMNHACUNGCAP_DIACHI: new JStORM.Field({ type: "String", maxLength: 30}),
			DMNHACUNGCAP_DIENTHOAI: new JStORM.Field({ type: "String", maxLength: 18}),
			DMNHACUNGCAP_MASOTHUE: new JStORM.Field({ type: "String", maxLength: 25}),
			DMNHACUNGCAP_FAX: new JStORM.Field({ type: "String", maxLength: 18}),
			DMNHACUNGCAP_PHANLOAI: new JStORM.Field({ type: "String", maxLength: 4}),
			DMNHACUNGCAP_PHANBIET: new JStORM.Field({ type: "String", maxLength: 4}),
			DMNHACUNGCAP_NGAYLV: new JStORM.Field({ type: "String"}),
			NgayChinhSua: new JStORM.Field({ type: "Float" }),
			QL: new JStORM.Field({ type: "Integer"}),
			DT: new JStORM.Field({ type: "Integer"}),
			DP: new JStORM.Field({ type: "Integer"})
			*/
        }
        buf.append("</list>");
        return buf.toString();
    }
    
    
}

