package com.iesvn.yte.dieutri.ajax;



import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
import com.iesvn.yte.dieutri.entity.HsThtoank;


public class CheckHsThtoanKAction extends Action {

	@Override
    public String performAction(String request) {
    	
		//bao.ttc
		//System.out.println(request);
		System.out.println("##### CheckHsThtoanKAction #####");
		
        StringBuffer buf = new StringBuffer();
        buf.append("<list>");
        
        try {
        	HsThtoankDelegate hsttkDelegate = HsThtoankDelegate.getInstance();
			HsThtoank hsttk = hsttkDelegate.findBytiepdonMa(request);
            
            if (hsttk == null){
            	buf.append("</list>");
                return buf.toString();
            }
            System.out.println("hsttk.hsthtoankNgaygiott " + hsttk.getHsthtoankNgaygiott());

            
            
            buf.append("<HSTHTOANK NGAYTT='" + hsttk.getHsthtoankNgaygiott() + "'/>");
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        buf.append("</list>");
        //System.out.println(buf.toString()); //bao.ttc
        return buf.toString();

    }
}

