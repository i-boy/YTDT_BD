package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmDongThemHome")
public class DtDmDongThemHome extends EntityHome<DtDmDongThem> {

	public void setDtDmDongThemDtdmdtMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmDongThemDtdmdtMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmDongThem createInstance() {
		DtDmDongThem dtDmDongThem = new DtDmDongThem();
		return dtDmDongThem;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmDongThem getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
