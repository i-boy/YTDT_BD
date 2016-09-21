package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmKetQuaHome")
public class DtDmKetQuaHome extends EntityHome<DtDmKetQua> {

	public void setDtDmKetQuaDtdmketquaMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmKetQuaDtdmketquaMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmKetQua createInstance() {
		DtDmKetQua dtDmKetQua = new DtDmKetQua();
		return dtDmKetQua;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmKetQua getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
