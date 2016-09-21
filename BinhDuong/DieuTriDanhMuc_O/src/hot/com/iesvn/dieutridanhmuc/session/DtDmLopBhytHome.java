package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmLopBhytHome")
public class DtDmLopBhytHome extends EntityHome<DtDmLopBhyt> {

	public void setDtDmLopBhytDtdmlopbhytMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmLopBhytDtdmlopbhytMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmLopBhyt createInstance() {
		DtDmLopBhyt dtDmLopBhyt = new DtDmLopBhyt();
		return dtDmLopBhyt;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmLopBhyt getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmNhomBhyt> getDtDmNhomBhyts() {
		return getInstance() == null ? null : new ArrayList<DtDmNhomBhyt>(
				getInstance().getDtDmNhomBhyts());
	}

}
