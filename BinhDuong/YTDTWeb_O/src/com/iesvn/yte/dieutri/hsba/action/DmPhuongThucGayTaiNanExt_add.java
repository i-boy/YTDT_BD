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
import com.iesvn.yte.entity.DmPhuongThucGayTaiNan;
import com.iesvn.yte.entity.DmTaiNan;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmPhuongThucGayTaiNan_add")
@Scope(SESSION)
public class DmPhuongThucGayTaiNanExt_add implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final int emptyValueCbo = -1;
	private static final String emptyLabelCbo = "...";

	@In(required = false)
	@Out(required = false)
	private DmPhuongThucGayTaiNan phuongthucgaytainan;

	private List<SelectItem> listtainan;
	
	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmPhuongThucGayTaiNanExt_add....");
		
		resetValue();
		initComboxTaiNan();
		
		return "/B2_Dieutri/B267_Phuongthucgaytainan_add";
	}

	public void save(int tainanId) {
		log.info("Save  DmPhuongThucGayTaiNanExt_add....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.phuongthucgaytainan.getDmptgtnMa();
		this.phuongthucgaytainan.setDmptgtnNgaygiocn((double)date.getTime());
		this.phuongthucgaytainan.setDmptgtnDt(true);
		
		if (tainanId == this.emptyValueCbo) {
			this.phuongthucgaytainan.setDmtainanMaso(null);
		}
		
		try {
			DieuTriUtilDelegate.getInstance().create(this.phuongthucgaytainan);
			FacesMessages.instance().add(IConstantsRes.phuongthucgaytainan_cr_su, ma);
			reset();
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.phuongthucgaytainan_cr_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack  DmPhuongThucGayTaiNanExt_add....");
		
		return "/B2_Dieutri/B267_Phuongthucgaytainan";
	}
	
	public void reset() {
		log.info("Reset  DmPhuongThucGayTaiNanExt_add....");

		resetValue();
	}

	private void resetValue() {
		this.phuongthucgaytainan = new DmPhuongThucGayTaiNan();
		this.phuongthucgaytainan.setDmtainanMaso(new DmTaiNan());
		
	}
	
	private void initComboxTaiNan() {	
		SelectItem item ;
		
		this.listtainan = new ArrayList();
		
		item = new SelectItem(this.emptyValueCbo,this.emptyLabelCbo);
		this.listtainan.add(item);
		
		List<DmTaiNan> lstempTaiNan = DieuTriUtilDelegate.getInstance().findAll("DmTaiNan", "dmtainanDt", true);
		
		for (DmTaiNan dmTaiNan : lstempTaiNan) {
			item = new SelectItem(dmTaiNan.getDmtainanMaso(),dmTaiNan.getDmtainanTen());
			this.listtainan.add(item);
		}
		
	}

	public DmPhuongThucGayTaiNan getPhuongthucgaytainan() {
		return phuongthucgaytainan;
	}

	public void setPhuongthucgaytainan(DmPhuongThucGayTaiNan phuongthucgaytainan) {
		this.phuongthucgaytainan = phuongthucgaytainan;
	}

	public List<SelectItem> getListtainan() {
		return listtainan;
	}

	public void setListtainan(List<SelectItem> listtainan) {
		this.listtainan = listtainan;
	}

	

}
