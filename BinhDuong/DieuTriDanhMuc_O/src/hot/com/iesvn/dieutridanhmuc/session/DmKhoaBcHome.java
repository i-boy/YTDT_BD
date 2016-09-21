package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmKhoaBcHome")
public class DmKhoaBcHome extends EntityHome<DmKhoaBc> {

	public void setDmKhoaBcDmkhoabcMaso(Integer id) {
		setId(id);
	}

	public Integer getDmKhoaBcDmkhoabcMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmKhoaBc createInstance() {
		DmKhoaBc dmKhoaBc = new DmKhoaBc();
		return dmKhoaBc;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmKhoaBc getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
