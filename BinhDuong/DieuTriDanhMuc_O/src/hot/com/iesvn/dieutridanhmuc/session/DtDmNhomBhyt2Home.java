package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmNhomBhyt2Home")
public class DtDmNhomBhyt2Home extends EntityHome<DtDmNhomBhyt2> {

	public void setDtDmNhomBhyt2Dtdmnhombhyt2Maso(Integer id) {
		setId(id);
	}

	public Integer getDtDmNhomBhyt2Dtdmnhombhyt2Maso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmNhomBhyt2 createInstance() {
		DtDmNhomBhyt2 dtDmNhomBhyt2 = new DtDmNhomBhyt2();
		return dtDmNhomBhyt2;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmNhomBhyt2 getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmKhoiBhyt> getDtDmKhoiBhyts() {
		return getInstance() == null ? null : new ArrayList<DtDmKhoiBhyt>(
				getInstance().getDtDmKhoiBhyts());
	}

}
