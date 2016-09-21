package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmTuyenDonViHome")
public class DmTuyenDonViHome extends EntityHome<DmTuyenDonVi> {

	@In(create = true)
	DmDonViHome dmDonViHome;
	@In(create = true)
	DmTuyenHome dmTuyenHome;

	public void setDmTuyenDonViId(DmTuyenDonViId id) {
		setId(id);
	}

	public DmTuyenDonViId getDmTuyenDonViId() {
		return (DmTuyenDonViId) getId();
	}

	public DmTuyenDonViHome() {
		setDmTuyenDonViId(new DmTuyenDonViId());
	}

	@Override
	public boolean isIdDefined() {
		if (getDmTuyenDonViId().getDmdonviMaso() == 0)
			return false;
		if (getDmTuyenDonViId().getDmtuyenMaso() == 0)
			return false;
		return true;
	}

	@Override
	protected DmTuyenDonVi createInstance() {
		DmTuyenDonVi dmTuyenDonVi = new DmTuyenDonVi();
		dmTuyenDonVi.setId(new DmTuyenDonViId());
		return dmTuyenDonVi;
	}

	public void wire() {
		getInstance();
		DmDonVi dmDonVi = dmDonViHome.getDefinedInstance();
		if (dmDonVi != null) {
			getInstance().setDmDonVi(dmDonVi);
		}
		DmTuyen dmTuyen = dmTuyenHome.getDefinedInstance();
		if (dmTuyen != null) {
			getInstance().setDmTuyen(dmTuyen);
		}
	}

	public boolean isWired() {
		if (getInstance().getDmDonVi() == null)
			return false;
		if (getInstance().getDmTuyen() == null)
			return false;
		return true;
	}

	public DmTuyenDonVi getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
