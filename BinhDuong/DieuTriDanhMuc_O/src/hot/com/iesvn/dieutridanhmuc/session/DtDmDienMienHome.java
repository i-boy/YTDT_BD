package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmDienMienHome")
public class DtDmDienMienHome extends EntityHome<DtDmDienMien> {

	public void setDtDmDienMienDtdmdienmienMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmDienMienDtdmdienmienMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmDienMien createInstance() {
		DtDmDienMien dtDmDienMien = new DtDmDienMien();
		return dtDmDienMien;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmDienMien getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
