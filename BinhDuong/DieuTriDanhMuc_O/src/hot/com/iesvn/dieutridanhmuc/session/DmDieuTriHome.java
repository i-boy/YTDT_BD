package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmDieuTriHome")
public class DmDieuTriHome extends EntityHome<DmDieuTri> {

	public void setDmDieuTriDmdieutriMaso(Integer id) {
		setId(id);
	}

	public Integer getDmDieuTriDmdieutriMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmDieuTri createInstance() {
		DmDieuTri dmDieuTri = new DmDieuTri();
		return dmDieuTri;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmDieuTri getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
