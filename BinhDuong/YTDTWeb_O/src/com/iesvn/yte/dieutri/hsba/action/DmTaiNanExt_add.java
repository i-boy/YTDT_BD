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
import com.iesvn.yte.entity.DmTaiNan;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmTaiNan_add")
@Scope(SESSION)
public class DmTaiNanExt_add implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmTaiNan tainan;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmTaiNanExt_add....");
		
		resetValue();
	
		return "/B2_Dieutri/B2611_Tainan_add";
	}

	public void save() {
		log.info("Save DmTaiNanExt_add....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.tainan.getDmtainanMa();
		this.tainan.setDmtainanNgaygiocn((double)date.getTime());
		this.tainan.setDmtainanDt(true);
		
		try {
			DieuTriUtilDelegate.getInstance().create(this.tainan);
			FacesMessages.instance().add(IConstantsRes.tainan_cr_su, ma);
			reset();
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.tainan_cr_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack DmTaiNanExt_add....");
		
		return "/B2_Dieutri/B2611_Tainan";
	}
	
	public void reset() {
		log.info("Reset DmTaiNanExt_add....");

		resetValue();
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
