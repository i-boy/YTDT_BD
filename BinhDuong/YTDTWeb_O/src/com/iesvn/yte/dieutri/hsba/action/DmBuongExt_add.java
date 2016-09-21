package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmBuong;
import com.iesvn.yte.entity.DmKhoa;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmBuong_add")
@Scope(SESSION)
public class DmBuongExt_add implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final int emptyValueCbo = -1;
	private static final String emptyLabelCbo = "...";

	@In(required = false)
	@Out(required = false)
	private DtDmBuong dmbuong;

	private List<SelectItem> listDmKhoa;
	
	
	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmBuongExt_add....");
		
		resetValue();
		initComboxKhoa();
		
		
		return "/B2_Dieutri/B273_DmBuong_add";
	}

	public void save(int khoaId) {
		log.info("Save  DmBuongExt_add....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.dmbuong.getDtdmbTen();
		this.dmbuong.setDtdmbNgaygiocn((double)date.getTime());
		this.dmbuong.setDtdmbChon(true);
		
		if (khoaId == this.emptyValueCbo) {
			this.dmbuong.setDmkhoaMaso(null);
		}
		
		
		try {
			DieuTriUtilDelegate.getInstance().create(this.dmbuong);
			FacesMessages.instance().add(IConstantsRes.buong_cr_su, ma);
			reset();
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.buong_cr_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack  DmBuongExt_add....");
		
		return "/B2_Dieutri/B273_DmBuong";
	}
	
	public void reset() {
		log.info("Reset  DmBuongExt_add....");

		resetValue();
	}

	private void resetValue() {
		this.dmbuong = new DtDmBuong();
		this.dmbuong.setDmkhoaMaso(new DmKhoa());		
		
	}
	
	private void initComboxKhoa() {	
		SelectItem item ;
		
		this.listDmKhoa = new ArrayList();
		
		item = new SelectItem(this.emptyValueCbo,this.emptyLabelCbo);
		this.listDmKhoa.add(item);
		
		List<DmKhoa> lstempKhoa = DieuTriUtilDelegate.getInstance().findKhoaNhapNuoc();
		
		for (DmKhoa dmKhoa : lstempKhoa) {
			item = new SelectItem(dmKhoa.getDmkhoaMaso(),dmKhoa.getDmkhoaTen());
			this.listDmKhoa.add(item);
		}
	
	}

	public DtDmBuong getDmbuong() {
		return dmbuong;
	}

	public void setDmbuong(DtDmBuong dmbuong) {
		this.dmbuong = dmbuong;
	}

	public List<SelectItem> getListDmKhoa() {
		return listDmKhoa;
	}

	public void setListDmKhoa(List<SelectItem> listDmKhoa) {
		this.listDmKhoa = listDmKhoa;
	}
	
	
}
