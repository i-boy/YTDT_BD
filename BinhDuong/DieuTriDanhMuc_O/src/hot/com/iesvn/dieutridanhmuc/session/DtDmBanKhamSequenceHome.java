package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmBanKhamSequenceHome")
public class DtDmBanKhamSequenceHome extends EntityHome<DtDmBanKhamSequence> {

	public void setDtDmBanKhamSequenceId(Integer id) {
		setId(id);
	}

	public Integer getDtDmBanKhamSequenceId() {
		return (Integer) getId();
	}

	@Override
	protected DtDmBanKhamSequence createInstance() {
		DtDmBanKhamSequence dtDmBanKhamSequence = new DtDmBanKhamSequence();
		return dtDmBanKhamSequence;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmBanKhamSequence getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
