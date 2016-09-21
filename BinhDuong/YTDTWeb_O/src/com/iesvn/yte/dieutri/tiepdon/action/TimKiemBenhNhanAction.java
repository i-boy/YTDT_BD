package com.iesvn.yte.dieutri.tiepdon.action;

import static org.jboss.seam.ScopeType.CONVERSATION;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;

import com.iesvn.yte.dieutri.delegate.BenhNhanDelegate;
import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.setinfor.SetInforUtil;

@Scope(CONVERSATION)
@Name("B113_Timkiembenhnhan")
@Synchronized(timeout = 6000000)
public class TimKiemBenhNhanAction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2423255254960254349L;
	private static Logger log = Logger.getLogger(TimKiemBenhNhanAction.class);
	private BenhNhan benhNhan;
	private TiepDon tiepDon;
		
	
	@Create
	@Begin(join = true)
	public String init() {
		resetValue();
		return "TiepDon_TiepDonBenhNhan_TimKiemBenhNhan";
	}
	@End
	public void end(){
		
	}

	public void resetValue() {
		log.info("---resetValue");
		benhNhan = new BenhNhan();
		SetInforUtil.setInforIfNullForBN(benhNhan);
		tiepDon = new TiepDon();
		SetInforUtil.setInforIfNullForTiepDon(tiepDon);
	}
	
	public void refresh(){
		log.info("---refresh");
		TiepDon tdtmp = TiepDonDelegate.getInstance().find(tiepDon.getTiepdonMa());
		tiepDon = tdtmp==null?new TiepDon():tdtmp;		
		SetInforUtil.setInforIfNullForTiepDon(tiepDon);
		BenhNhan bntmp = BenhNhanDelegate.getInstance().find(benhNhan.getBenhnhanMa());
		benhNhan = bntmp==null? new BenhNhan():bntmp;
		SetInforUtil.setInforIfNullForBN(benhNhan);
	}
	
	public String formatDate(Date date){
		return date==null?"":new SimpleDateFormat("dd/MM/yyyy").format(date); 
	}
	
	public String formatGtBenhNhan(String gioitinh){
		if (gioitinh==null || gioitinh.trim().equals("")){
			return "";
		}
		return gioitinh.equals("1")?"nam":"nu";
	}
	
	public BenhNhan getBenhNhan() {
		return benhNhan;
	}

	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}

	public TiepDon getTiepDon() {
		return tiepDon;
	}

	public void setTiepDon(TiepDon tiepDon) {
		this.tiepDon = tiepDon;
	}

}
