package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmHocHamHome")
public class DmHocHamHome extends EntityHome<DmHocHam> {

	public void setDmHocHamDmhochamMaso(Integer id) {
		setId(id);
	}

	public Integer getDmHocHamDmhochamMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmHocHam createInstance() {
		DmHocHam dmHocHam = new DmHocHam();
		return dmHocHam;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmHocHam getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
