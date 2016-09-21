package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmGioAnHome")
public class DtDmGioAnHome extends EntityHome<DtDmGioAn> {

	public void setDtDmGioAnDtdmgaMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmGioAnDtdmgaMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmGioAn createInstance() {
		DtDmGioAn dtDmGioAn = new DtDmGioAn();
		return dtDmGioAn;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmGioAn getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
