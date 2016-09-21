package com.iesvn.yte.dieutri.ajax;



import com.iesvn.yte.Action;
import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
import com.iesvn.yte.dieutri.entity.ThamKham;


public class GetThamKhamAction extends Action {

    @Override
    public String performAction(String request) {
    	
    	//phuc.lc
    	System.out.println("##### GetThamKhamAction, request = " + request);
    	//System.out.println(request);
        StringBuffer buf = new StringBuffer();
        buf.append("<list>");
        
        try {
        	ThamKhamDelegate tkDel = ThamKhamDelegate.getInstance();
            ThamKham tk = tkDel.findByMaTiepDon(request);
            
            if (tk != null) {
            	System.out.println("tk ma " + tk.getThamkhamMa());

                String tkBacsi = tk.getThamkhamBacsi() == null ? "" : "" + tk.getThamkhamBacsi().getDtdmnhanvienMaso();                
         
                buf.append("<THAM_KHAM THAMKHAM_BACSI='" + tkBacsi + "'/>");                        
            } else {
            	buf.append("<THAM_KHAM THAMKHAM_BACSI=''/>");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        buf.append("</list>");
        //System.out.println(buf.toString()); //phuc.lc
        return buf.toString();

    }
}

