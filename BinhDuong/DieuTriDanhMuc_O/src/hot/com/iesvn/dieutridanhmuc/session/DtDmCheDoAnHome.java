package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmCheDoAnHome")
public class DtDmCheDoAnHome extends EntityHome<DtDmCheDoAn> {

	public void setDtDmCheDoAnDtdmcdaMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmCheDoAnDtdmcdaMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmCheDoAn createInstance() {
		DtDmCheDoAn dtDmCheDoAn = new DtDmCheDoAn();
		return dtDmCheDoAn;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmCheDoAn getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
