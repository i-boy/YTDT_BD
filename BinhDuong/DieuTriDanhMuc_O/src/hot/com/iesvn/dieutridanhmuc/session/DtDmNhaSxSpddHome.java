package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmNhaSxSpddHome")
public class DtDmNhaSxSpddHome extends EntityHome<DtDmNhaSxSpdd> {

	public void setDtDmNhaSxSpddDtdmnsxMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmNhaSxSpddDtdmnsxMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmNhaSxSpdd createInstance() {
		DtDmNhaSxSpdd dtDmNhaSxSpdd = new DtDmNhaSxSpdd();
		return dtDmNhaSxSpdd;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmNhaSxSpdd getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
