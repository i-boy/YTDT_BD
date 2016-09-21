package com.iesvn.yte.dieutri.vienphi.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

import com.iesvn.yte.dieutri.delegate.HsThtoanDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.HsThtoan;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.util.Utils;

@Scope(SESSION)
@Name("B3119_Timbenhnhancohosothuphi")
@Synchronized(timeout = 6000000)
public class TimBenhNhanCoHSThuPhiAction implements Serializable{

	private static final long serialVersionUID = -4024399704895343834L;
	private static Logger log = Logger.getLogger(TimBenhNhanCoHSThuPhiAction.class);
	
	private HsThtoan hstt;
	private TiepDon td;

	public TiepDon getTd() {
		return td;
	}

	public void setTd(TiepDon td) {
		this.td = td;
	}

	@Create
	public void init() {	
		resetValue();			
	}
	
	public void resetValue() {
		log.info("---resetValue");
		hstt = new HsThtoan();
		td= new TiepDon();
	}
	
	public void refresh(){
		log.info("---refresh");
		TiepDon tdtmp = TiepDonDelegate.getInstance().find(td.getTiepdonMa());
		td = tdtmp==null?new TiepDon():tdtmp;
		HsThtoan hstttmp = HsThtoanDelegate.getInstance().find(hstt.getHsthtoanMa());
		hstt = hstttmp==null?new HsThtoan():hstttmp;
	}
	
	public String formatDate(Date date){
		return date==null?"":new SimpleDateFormat("dd/MM/yyyy").format(date);
	}
		
	public String formatDateTime(Date date){
		return date==null?"":Utils.getGioPhut(date);
	}
	
	public String formatGtBenhNhan(String gioitinh){
		if (gioitinh==null || gioitinh.trim().equals("")){
			return "";
		}
		return gioitinh.equals("1")?"true":"false";
	}

	public HsThtoan getHstt() {
		return hstt;
	}

	public void setHstt(HsThtoan hstt) {
		this.hstt = hstt;
	}

}
