package com.iesvn.yte.dieutri.hsba.action;

import static org.jboss.seam.ScopeType.SESSION;

import java.io.Serializable;
import java.util.Date;

import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
import com.iesvn.yte.dieutri.entity.DtDmVoCam;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmVoCam_add")
@Scope(SESSION)
public class DmVoCamExt_add implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DtDmVoCam vocam;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmVoCamExt_add....");
		
		resetValue();
	
		return "/B2_Dieutri/B261_Vocam_add";
	}

	public void save() {
		log.info("Save DmVoCamExt_add....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.vocam.getDtdmvocamMa();
		this.vocam.setDtdmvocamNgaygiocn((double)date.getTime());
		this.vocam.setDtdmvocamChon(true);
		
		try {
			DieuTriUtilDelegate.getInstance().create(this.vocam);
			FacesMessages.instance().add(IConstantsRes.vocam_cr_su, ma);
			reset();
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.vocam_cr_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack DmVoCamExt_add....");
		
		return "/B2_Dieutri/B261_Vocam";
	}
	
	public void reset() {
		log.info("Reset DmVoCamExt_add....");

		resetValue();
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
