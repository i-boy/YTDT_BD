package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmTramYTeBhytHome")
public class DtDmTramYTeBhytHome extends EntityHome<DtDmTramYTeBhyt> {

	public void setDtDmTramYTeBhytDtdmtramytebhytMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmTramYTeBhytDtdmtramytebhytMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmTramYTeBhyt createInstance() {
		DtDmTramYTeBhyt dtDmTramYTeBhyt = new DtDmTramYTeBhyt();
		return dtDmTramYTeBhyt;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmTramYTeBhyt getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
