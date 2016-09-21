package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmGioiTinhHome")
public class DmGioiTinhHome extends EntityHome<DmGioiTinh> {

	public void setDmGioiTinhDmgtMaso(Integer id) {
		setId(id);
	}

	public Integer getDmGioiTinhDmgtMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmGioiTinh createInstance() {
		DmGioiTinh dmGioiTinh = new DmGioiTinh();
		return dmGioiTinh;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmGioiTinh getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
