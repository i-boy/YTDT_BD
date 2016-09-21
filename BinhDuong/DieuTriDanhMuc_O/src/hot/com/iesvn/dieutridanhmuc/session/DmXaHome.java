package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmXaHome")
public class DmXaHome extends EntityHome<DmXa> {

	@In(create = true)
	DmHuyenHome dmHuyenHome;

	public void setDmXaDmxaMaso(Integer id) {
		setId(id);
	}

	public Integer getDmXaDmxaMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmXa createInstance() {
		DmXa dmXa = new DmXa();
		return dmXa;
	}

	public void wire() {
		getInstance();
		DmHuyen dmHuyen = dmHuyenHome.getDefinedInstance();
		if (dmHuyen != null) {
			getInstance().setDmHuyen(dmHuyen);
		}
	}

	public boolean isWired() {
		if (getInstance().getDmHuyen() == null)
			return false;
		return true;
	}

	public DmXa getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmDonVi> getDmDonVis() {
		return getInstance() == null ? null : new ArrayList<DmDonVi>(
				getInstance().getDmDonVis());
	}

	public List<DmDonVi> getDmDonVis_1() {
		return getInstance() == null ? null : new ArrayList<DmDonVi>(
				getInstance().getDmDonVis_1());
	}

	public List<DmThon> getDmThons() {
		return getInstance() == null ? null : new ArrayList<DmThon>(
				getInstance().getDmThons());
	}

	public List<DmThon> getDmThons_1() {
		return getInstance() == null ? null : new ArrayList<DmThon>(
				getInstance().getDmThons_1());
	}

}
