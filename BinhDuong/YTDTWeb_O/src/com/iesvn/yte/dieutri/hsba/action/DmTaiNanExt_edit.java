package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import com.iesvn.yte.entity.DmTaiNan;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmTaiNan_edit")
@Scope(SESSION)
public class DmTaiNanExt_edit implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmTaiNan tainan;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmTaiNanExt_edit....");
		
		resetValue();
		setValueForForm();
	
		return "/B2_Dieutri/B2611_Tainan_edit";
	}

	public void save() {
		log.info("Save DmTaiNanExt_edit....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.tainan.getDmtainanMa();
		this.tainan.setDmtainanNgaygiocn((double)date.getTime());
		
		try {
			DieuTriUtilDelegate.getInstance().edit(this.tainan);
			FacesMessages.instance().add(IConstantsRes.tainan_up_su, ma);
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.tainan_up_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack DmTaiNanExt_edit....");
		
		return "/B2_Dieutri/B2611_Tainan";
	}
	
	private void setValueForForm() {
		log.info("Set value for form DmTaiNanExt_edit....");
		
		int rowIndex = -1;
		ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmTaiNan");
		rowIndex = lsDataModel.getRowIndex();
		
		if (rowIndex != -1) {
			this.tainan = ((List<DmTaiNan>)lsDataModel.getWrappedData()).get(rowIndex);
				
		}	
	}

	private void resetValue() {
		this.tainan = new DmTaiNan();
	}

	public DmTaiNan getTainan() {
		return tainan;
	}

	public void setTainan(DmTaiNan tainan) {
		this.tainan = tainan;
	}

	
	
}
