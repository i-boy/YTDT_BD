package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmLoaiHoSoHome")
public class DmLoaiHoSoHome extends EntityHome<DmLoaiHoSo> {

	public void setDmLoaiHoSoDmloaihosoMaso(Integer id) {
		setId(id);
	}

	public Integer getDmLoaiHoSoDmloaihosoMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmLoaiHoSo createInstance() {
		DmLoaiHoSo dmLoaiHoSo = new DmLoaiHoSo();
		return dmLoaiHoSo;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmLoaiHoSo getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
