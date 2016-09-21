package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmHuongDtHome")
public class DtDmHuongDtHome extends EntityHome<DtDmHuongDt> {

	public void setDtDmHuongDtDtdmhuongdtMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmHuongDtDtdmhuongdtMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmHuongDt createInstance() {
		DtDmHuongDt dtDmHuongDt = new DtDmHuongDt();
		return dtDmHuongDt;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmHuongDt getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
