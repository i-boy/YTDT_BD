package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmQuyHome")
public class DmQuyHome extends EntityHome<DmQuy> {

	public void setDmQuyDmquyMaso(Byte id) {
		setId(id);
	}

	public Byte getDmQuyDmquyMaso() {
		return (Byte) getId();
	}

	@Override
	protected DmQuy createInstance() {
		DmQuy dmQuy = new DmQuy();
		return dmQuy;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmQuy getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
