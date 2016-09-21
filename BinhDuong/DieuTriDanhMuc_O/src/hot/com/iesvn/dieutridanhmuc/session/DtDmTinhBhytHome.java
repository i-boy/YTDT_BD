package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmTinhBhytHome")
public class DtDmTinhBhytHome extends EntityHome<DtDmTinhBhyt> {

	public void setDtDmTinhBhytDtdmtinhbhytMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmTinhBhytDtdmtinhbhytMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmTinhBhyt createInstance() {
		DtDmTinhBhyt dtDmTinhBhyt = new DtDmTinhBhyt();
		return dtDmTinhBhyt;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmTinhBhyt getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmKcbBhyt> getDtDmKcbBhyts() {
		return getInstance() == null ? null : new ArrayList<DtDmKcbBhyt>(
				getInstance().getDtDmKcbBhyts());
	}

	public List<DtDmKcbBhyt> getDtDmKcbBhyts_1() {
		return getInstance() == null ? null : new ArrayList<DtDmKcbBhyt>(
				getInstance().getDtDmKcbBhyts_1());
	}

}
