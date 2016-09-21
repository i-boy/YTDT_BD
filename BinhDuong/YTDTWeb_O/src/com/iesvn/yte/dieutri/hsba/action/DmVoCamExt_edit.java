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
import com.iesvn.yte.dieutri.entity.DtDmVoCam;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmVoCam_edit")
@Scope(SESSION)
public class DmVoCamExt_edit implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DtDmVoCam vocam;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmVoCamExt_edit....");
		
		resetValue();
		setValueForForm();
	
		return "/B2_Dieutri/B261_Vocam_edit";
	}

	public void save() {
		log.info("Save DmVoCamExt_edit....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.vocam.getDtdmvocamMa();
		this.vocam.setDtdmvocamNgaygiocn((double)date.getTime());
		
		try {
			DieuTriUtilDelegate.getInstance().edit(this.vocam);
			FacesMessages.instance().add(IConstantsRes.vocam_up_su, ma);
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.vocam_up_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack DmVoCamExt_edit....");
		
		return "/B2_Dieutri/B261_Vocam";
	}
	
	private void setValueForForm() {
		log.info("Set value for form DmVoCamExt_edit....");
		
		int rowIndex = -1;
		ListDataModel lsDataModel = (ListDataModel)Contexts.getSessionContext().get("listDmVoCam");
		rowIndex = lsDataModel.getRowIndex();
		
		if (rowIndex != -1) {
			this.vocam = ((List<DtDmVoCam>)lsDataModel.getWrappedData()).get(rowIndex);
				
		}	
	}

	private void resetValue() {
		this.vocam = new DtDmVoCam();
	}

	public DtDmVoCam getVocam() {
		return vocam;
	}

	public void setVocam(DtDmVoCam vocam) {
		this.vocam = vocam;
	}

	
}
