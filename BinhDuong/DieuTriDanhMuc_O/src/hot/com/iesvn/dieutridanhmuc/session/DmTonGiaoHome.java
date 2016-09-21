package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmTonGiaoHome")
public class DmTonGiaoHome extends EntityHome<DmTonGiao> {

	public void setDmTonGiaoTongiaoMaso(Integer id) {
		setId(id);
	}

	public Integer getDmTonGiaoTongiaoMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmTonGiao createInstance() {
		DmTonGiao dmTonGiao = new DmTonGiao();
		return dmTonGiao;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmTonGiao getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
