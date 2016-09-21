package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmDuAnDeTaiHome")
public class DmDuAnDeTaiHome extends EntityHome<DmDuAnDeTai> {

	public void setDmDuAnDeTaiDmduandetaiMaso(Integer id) {
		setId(id);
	}

	public Integer getDmDuAnDeTaiDmduandetaiMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmDuAnDeTai createInstance() {
		DmDuAnDeTai dmDuAnDeTai = new DmDuAnDeTai();
		return dmDuAnDeTai;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmDuAnDeTai getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
