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
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.jsf.ListDataModel;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.entity.DmPhuongThucGayTaiNan;
import com.iesvn.yte.entity.DmTaiNan;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmPhuongThucGayTaiNan_edit")
@Scope(SESSION)
public class DmPhuongThucGayTaiNanExt_edit implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final int emptyValueCbo = -1;
	private static final String emptyLabelCbo = "...";

	@In(required = false)
	@Out(required = false)
	private DmPhuongThucGayTaiNan phuongthucgaytainan;
	
	private List<SelectItem> listTaiNan;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmPhuongThucGayTaiNanExt_edit....");
		
		resetValue();
		initComboxTaiNan();
		setValueForForm();
	
		return "/B2_Dieutri/B267_Phuongthucgaytainan_edit";
	}

	public void save(int tainanId) {
		log.info("Save DmPhuongThucGayTaiNanExt_edit....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.phuongthucgaytainan.getDmptgtnMa();
		this.phuongthucgaytainan.setDmptgtnNgaygiocn((double)date.getTime());
		
		if (tainanId == this.emptyValueCbo) {
			this.phuongthucgaytainan.setDmtainanMaso(null);
		}
		
		try {
			DieuTriUtilDelegate.getInstance().edit(this.phuongthucgaytainan);
			FacesMessages.instance().add(IConstantsRes.phuongthucgaytainan_up_su, ma);
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.phuongthucgaytainan_up_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack DmPhuongThucGayTaiNanExt_edit....");
		
		return "/B2_Dieutri/B267_Phuongthucgaytainan";
	}
	
	private void setValueForForm() {
		log.info("Set value for form DmPhuongThucGayTaiNanExt_edit....");
		
		int rowIndex = -1;
		ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmPhuongThucGayTaiNan");
		rowIndex = lsDataModel.getRowIndex();
		
		if (rowIndex != -1) {
			this.phuongthucgaytainan = ((List<DmPhuongThucGayTaiNan>)lsDataModel.getWrappedData()).get(rowIndex);
				
		}	
	}

	private void resetValue() {
		this.phuongthucgaytainan = new DmPhuongThucGayTaiNan();
		this.phuongthucgaytainan.setDmtainanMaso(new DmTaiNan());
	}
	
	private void initComboxTaiNan() {	
		SelectItem item ;
		
		this.listTaiNan = new ArrayList();
		
		item = new SelectItem(this.emptyValueCbo,this.emptyLabelCbo);
		this.listTaiNan.add(item);
		
		List<DmTaiNan> lstempTaiNan = DieuTriUtilDelegate.getInstance().findAll("DmTaiNan", "dmtainanDt", true);
		
		for (DmTaiNan dmTaiNan : lstempTaiNan) {
			item = new SelectItem(dmTaiNan.getDmtainanMaso(),dmTaiNan.getDmtainanTen());
			this.listTaiNan.add(item);
		}
		
	}

	public DmPhuongThucGayTaiNan getPhuongthucgaytainan() {
		return phuongthucgaytainan;
	}

	public void setPhuongthucgaytainan(DmPhuongThucGayTaiNan phuongthucgaytainan) {
		this.phuongthucgaytainan = phuongthucgaytainan;
	}

	public List<SelectItem> getListTaiNan() {
		return listTaiNan;
	}

	public void setListTaiNan(List<SelectItem> listTaiNan) {
		this.listTaiNan = listTaiNan;
	}

	
}
