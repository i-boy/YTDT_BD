package com.iesvn.yte.dieutri.duocpham.action;

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
import com.iesvn.yte.entity.DmDonViTinh;
import com.iesvn.yte.util.IConstantsRes;

@Name("DmDonViTinh_add")
@Scope(SESSION)
public class DmDonViTinhExt_add implements Serializable{

	private static final long serialVersionUID = 1L;

	@In(required = false)
	@Out(required = false)
	private DmDonViTinh donvitinh;

	@Logger
	private Log log;

	@Create
	public String init() {
		log.info("Init DmDonViTinhExt_add....");
		
		resetValue();
	
		return "/B4_Duocpham/KhoChinh/B4153_Donvitinh_add";
	}

	public void save() {
		log.info("Save DmDonViTinhExt_add....");
		
		String ma = "";
		Date date = new Date();
		
		ma = this.donvitinh.getDmdonvitinhMa();
		this.donvitinh.setDmdonvitinhNgaygiocn((double)date.getTime());
		this.donvitinh.setDmdonvitinhDt(true);
		
		try {
			DieuTriUtilDelegate.getInstance().create(this.donvitinh);
			FacesMessages.instance().add(IConstantsRes.donvitinh_cr_su, ma);
			reset();
			
		} catch (Exception e) {
			log.error(e.toString());
			FacesMessages.instance().add(IConstantsRes.donvitinh_cr_fa, ma);
		}
	}

	public String goBack() {
		log.info("GoBack DmDonViTinhExt_add....");
		
		return "/B4_Duocpham/KhoChinh/B4153_Donvitinh";
	}
	
	public void reset() {
		log.info("Reset DmDonViTinhExt_add....");

		resetValue();
	}

	private void resetValue() {
		this.donvitinh = new DmDonViTinh();
	}

	public DmDonViTinh getDonvitinh() {
		return donvitinh;
	}

	public void setDonvitinh(DmDonViTinh donvitinh) {
		this.donvitinh = donvitinh;
	}

	
}
