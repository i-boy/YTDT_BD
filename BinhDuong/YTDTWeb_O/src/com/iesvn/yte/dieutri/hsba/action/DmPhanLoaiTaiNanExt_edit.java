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
import com.iesvn.yte.entity.DmPhanLoaiTaiNan;
import com.iesvn.yte.entity.DmTaiNan;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmPhanLoaiTaiNan_edit")
@Scope(SESSION)
public class DmPhanLoaiTaiNanExt_edit implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final int emptyValueCbo = -1;
	private static final String emptyLabelCbo = "...";

	@In(required = false)
	@Out(required = false)
	private DmPhanLoaiTaiNan phanloaitainan;
	
	private List<SelectItem> listPhanLoaiTaiNan;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmPhanLoaiTaiNanExt_edit....");
		
		resetValue();
		initComboxTaiNan();
		setValueForForm();
	
		return "/B2_Dieutri/B266_Phanloaitainan_edit";
	}

	public void save(int tainanId) {
		log.info("Save DmPhanLoaiTaiNanExt_edit....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.phanloaitainan.getDmpltainanMa();
		this.phanloaitainan.setDmpltainanNgaygiocn((double)date.getTime());
		
		if (tainanId == this.emptyValueCbo) {
			this.phanloaitainan.setDmtainanMaso(null);
		}
		
		try {
			DieuTriUtilDelegate.getInstance().edit(this.phanloaitainan);
			FacesMessages.instance().add(IConstantsRes.phanloaitainan_up_su, ma);
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.phanloaitainan_up_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack DmPhanLoaiTaiNanExt_edit....");
		
		return "/B2_Dieutri/B266_Phanloaitainan";
	}
	
	private void setValueForForm() {
		log.info("Set value for form DmPhanLoaiTaiNanExt_edit....");
		
		int rowIndex = -1;
		ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmPhanLoaiTaiNan");
		rowIndex = lsDataModel.getRowIndex();
		
		if (rowIndex != -1) {
			this.phanloaitainan = ((List<DmPhanLoaiTaiNan>)lsDataModel.getWrappedData()).get(rowIndex);
				
		}	
	}

	private void resetValue() {
		this.phanloaitainan = new DmPhanLoaiTaiNan();
		this.phanloaitainan.setDmtainanMaso(new DmTaiNan());
	}
	
	private void initComboxTaiNan() {	
		SelectItem item ;
		
		this.listPhanLoaiTaiNan = new ArrayList();
		
		item = new SelectItem(this.emptyValueCbo,this.emptyLabelCbo);
		this.listPhanLoaiTaiNan.add(item);
		
		List<DmTaiNan> lstempTaiNan = DieuTriUtilDelegate.getInstance().findAll("DmTaiNan", "dmtainanDt", true);
		
		for (DmTaiNan dmTaiNan : lstempTaiNan) {
			item = new SelectItem(dmTaiNan.getDmtainanMaso(),dmTaiNan.getDmtainanTen());
			this.listPhanLoaiTaiNan.add(item);
		}
		
	}

	public DmPhanLoaiTaiNan getPhanloaitainan() {
		return phanloaitainan;
	}

	public void setPhanloaitainan(DmPhanLoaiTaiNan phanloaitainan) {
		this.phanloaitainan = phanloaitainan;
	}

	public List<SelectItem> getListPhanLoaiTaiNan() {
		return listPhanLoaiTaiNan;
	}

	public void setListPhanLoaiTaiNan(List<SelectItem> listPhanLoaiTaiNan) {
		this.listPhanLoaiTaiNan = listPhanLoaiTaiNan;
	}

	
	
}
