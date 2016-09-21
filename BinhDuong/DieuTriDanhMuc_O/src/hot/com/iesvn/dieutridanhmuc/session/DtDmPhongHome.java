package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmPhongHome")
public class DtDmPhongHome extends EntityHome<DtDmPhong> {

	public void setDtDmPhongDtdmpMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmPhongDtdmpMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmPhong createInstance() {
		DtDmPhong dtDmPhong = new DtDmPhong();
		return dtDmPhong;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmPhong getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
