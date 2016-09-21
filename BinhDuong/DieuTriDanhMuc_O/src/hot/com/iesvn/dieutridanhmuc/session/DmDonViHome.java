package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmDonViHome")
public class DmDonViHome extends EntityHome<DmDonVi> {

	@In(create = true)
	DmHuyenHome dmHuyenHome;
	@In(create = true)
	DmTinhHome dmTinhHome;
	@In(create = true)
	DmTuyenHome dmTuyenHome;
	@In(create = true)
	DmXaHome dmXaHome;

	public void setDmDonViDmdonviMaso(Integer id) {
		setId(id);
	}

	public Integer getDmDonViDmdonviMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmDonVi createInstance() {
		DmDonVi dmDonVi = new DmDonVi();
		return dmDonVi;
	}

	public void wire() {
		getInstance();
		DmHuyen dmHuyen = dmHuyenHome.getDefinedInstance();
		if (dmHuyen != null) {
			getInstance().setDmHuyen(dmHuyen);
		}
		DmTinh dmTinh = dmTinhHome.getDefinedInstance();
		if (dmTinh != null) {
			getInstance().setDmTinh(dmTinh);
		}
		DmTuyen dmTuyen = dmTuyenHome.getDefinedInstance();
		if (dmTuyen != null) {
			getInstance().setDmTuyen(dmTuyen);
		}
		DmXa dmXa = dmXaHome.getDefinedInstance();
		if (dmXa != null) {
			getInstance().setDmXa(dmXa);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DmDonVi getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmTuyenDonVi> getDmTuyenDonVis() {
		return getInstance() == null ? null : new ArrayList<DmTuyenDonVi>(
				getInstance().getDmTuyenDonVis());
	}

	public List<DmTuyenDonVi> getDmTuyenDonVis_1() {
		return getInstance() == null ? null : new ArrayList<DmTuyenDonVi>(
				getInstance().getDmTuyenDonVis_1());
	}

}
