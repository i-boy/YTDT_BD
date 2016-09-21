package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmTienKhamHome")
public class DtDmTienKhamHome extends EntityHome<DtDmTienKham> {

	public void setDtDmTienKhamDtdmtienkhamMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmTienKhamDtdmtienkhamMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmTienKham createInstance() {
		DtDmTienKham dtDmTienKham = new DtDmTienKham();
		return dtDmTienKham;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmTienKham getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
