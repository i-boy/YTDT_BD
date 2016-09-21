package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmThonHome")
public class DmThonHome extends EntityHome<DmThon> {

	@In(create = true)
	DmXaHome dmXaHome;

	public void setDmThonDmthonMaso(Integer id) {
		setId(id);
	}

	public Integer getDmThonDmthonMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmThon createInstance() {
		DmThon dmThon = new DmThon();
		return dmThon;
	}

	public void wire() {
		getInstance();
		DmXa dmXa = dmXaHome.getDefinedInstance();
		if (dmXa != null) {
			getInstance().setDmXa(dmXa);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DmThon getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
