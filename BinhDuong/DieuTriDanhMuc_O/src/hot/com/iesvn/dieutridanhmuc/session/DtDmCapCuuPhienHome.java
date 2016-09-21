package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmCapCuuPhienHome")
public class DtDmCapCuuPhienHome extends EntityHome<DtDmCapCuuPhien> {

	public void setDtDmCapCuuPhienDtdmccpMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmCapCuuPhienDtdmccpMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmCapCuuPhien createInstance() {
		DtDmCapCuuPhien dtDmCapCuuPhien = new DtDmCapCuuPhien();
		return dtDmCapCuuPhien;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmCapCuuPhien getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
