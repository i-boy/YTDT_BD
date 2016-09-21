package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmChiDanDvtHome")
public class DtDmChiDanDvtHome extends EntityHome<DtDmChiDanDvt> {

	@In(create = true)
	DmDonViTinhHome dmDonViTinhHome;
	@In(create = true)
	DtDmChiDanHome dtDmChiDanHome;

	public void setDtDmChiDanDvtDtdmchidandvtMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmChiDanDvtDtdmchidandvtMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmChiDanDvt createInstance() {
		DtDmChiDanDvt dtDmChiDanDvt = new DtDmChiDanDvt();
		return dtDmChiDanDvt;
	}

	public void wire() {
		getInstance();
		DmDonViTinh dmDonViTinh = dmDonViTinhHome.getDefinedInstance();
		if (dmDonViTinh != null) {
			getInstance().setDmDonViTinh(dmDonViTinh);
		}
		DtDmChiDan dtDmChiDan = dtDmChiDanHome.getDefinedInstance();
		if (dtDmChiDan != null) {
			getInstance().setDtDmChiDan(dtDmChiDan);
		}
	}

	public boolean isWired() {
		if (getInstance().getDmDonViTinh() == null)
			return false;
		if (getInstance().getDtDmChiDan() == null)
			return false;
		return true;
	}

	public DtDmChiDanDvt getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
