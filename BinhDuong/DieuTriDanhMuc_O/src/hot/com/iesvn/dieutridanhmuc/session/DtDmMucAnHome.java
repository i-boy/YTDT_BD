package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmMucAnHome")
public class DtDmMucAnHome extends EntityHome<DtDmMucAn> {

	public void setDtDmMucAnDtdmmaMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmMucAnDtdmmaMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmMucAn createInstance() {
		DtDmMucAn dtDmMucAn = new DtDmMucAn();
		return dtDmMucAn;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmMucAn getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
