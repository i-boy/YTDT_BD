package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmLoaiCanBoHome")
public class DmLoaiCanBoHome extends EntityHome<DmLoaiCanBo> {

	public void setDmLoaiCanBoDmloaicanboMaso(Integer id) {
		setId(id);
	}

	public Integer getDmLoaiCanBoDmloaicanboMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmLoaiCanBo createInstance() {
		DmLoaiCanBo dmLoaiCanBo = new DmLoaiCanBo();
		return dmLoaiCanBo;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmLoaiCanBo getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
