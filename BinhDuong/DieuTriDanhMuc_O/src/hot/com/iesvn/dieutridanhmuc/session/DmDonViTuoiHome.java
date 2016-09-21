package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmDonViTuoiHome")
public class DmDonViTuoiHome extends EntityHome<DmDonViTuoi> {

	public void setDmDonViTuoiDmdonvituoiMaso(Integer id) {
		setId(id);
	}

	public Integer getDmDonViTuoiDmdonvituoiMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmDonViTuoi createInstance() {
		DmDonViTuoi dmDonViTuoi = new DmDonViTuoi();
		return dmDonViTuoi;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmDonViTuoi getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
