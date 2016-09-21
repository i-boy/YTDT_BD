package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmKetQuaDieuTriHome")
public class DmKetQuaDieuTriHome extends EntityHome<DmKetQuaDieuTri> {

	public void setDmKetQuaDieuTriDmkqdtMaso(Integer id) {
		setId(id);
	}

	public Integer getDmKetQuaDieuTriDmkqdtMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmKetQuaDieuTri createInstance() {
		DmKetQuaDieuTri dmKetQuaDieuTri = new DmKetQuaDieuTri();
		return dmKetQuaDieuTri;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmKetQuaDieuTri getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
