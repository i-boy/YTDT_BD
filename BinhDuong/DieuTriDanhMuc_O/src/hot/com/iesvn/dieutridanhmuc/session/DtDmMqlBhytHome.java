package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmMqlBhytHome")
public class DtDmMqlBhytHome extends EntityHome<DtDmMqlBhyt> {

	public void setDtDmMqlBhytDtdmmqlbhytMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmMqlBhytDtdmmqlbhytMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmMqlBhyt createInstance() {
		DtDmMqlBhyt dtDmMqlBhyt = new DtDmMqlBhyt();
		return dtDmMqlBhyt;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmMqlBhyt getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
