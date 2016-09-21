package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmNhomBhytHome")
public class DtDmNhomBhytHome extends EntityHome<DtDmNhomBhyt> {

	@In(create = true)
	DtDmLopBhytHome dtDmLopBhytHome;
	@In(create = true)
	DtDmPlBhytHome dtDmPlBhytHome;

	public void setDtDmNhomBhytDtdmnhombhytMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmNhomBhytDtdmnhombhytMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmNhomBhyt createInstance() {
		DtDmNhomBhyt dtDmNhomBhyt = new DtDmNhomBhyt();
		return dtDmNhomBhyt;
	}

	public void wire() {
		getInstance();
		DtDmLopBhyt dtDmLopBhyt = dtDmLopBhytHome.getDefinedInstance();
		if (dtDmLopBhyt != null) {
			getInstance().setDtDmLopBhyt(dtDmLopBhyt);
		}
		DtDmPlBhyt dtDmPlBhyt = dtDmPlBhytHome.getDefinedInstance();
		if (dtDmPlBhyt != null) {
			getInstance().setDtDmPlBhyt(dtDmPlBhyt);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DtDmNhomBhyt getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmKhoiBhyt> getDtDmKhoiBhyts() {
		return getInstance() == null ? null : new ArrayList<DtDmKhoiBhyt>(
				getInstance().getDtDmKhoiBhyts());
	}

}
