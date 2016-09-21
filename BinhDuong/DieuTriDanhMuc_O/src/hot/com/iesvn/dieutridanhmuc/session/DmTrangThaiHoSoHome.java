package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmTrangThaiHoSoHome")
public class DmTrangThaiHoSoHome extends EntityHome<DmTrangThaiHoSo> {

	public void setDmTrangThaiHoSoDmtthsMaso(Integer id) {
		setId(id);
	}

	public Integer getDmTrangThaiHoSoDmtthsMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmTrangThaiHoSo createInstance() {
		DmTrangThaiHoSo dmTrangThaiHoSo = new DmTrangThaiHoSo();
		return dmTrangThaiHoSo;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmTrangThaiHoSo getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
