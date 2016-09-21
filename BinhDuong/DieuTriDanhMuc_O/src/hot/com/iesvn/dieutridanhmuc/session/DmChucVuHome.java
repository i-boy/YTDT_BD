package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmChucVuHome")
public class DmChucVuHome extends EntityHome<DmChucVu> {

	public void setDmChucVuDmchucvuMaso(Integer id) {
		setId(id);
	}

	public Integer getDmChucVuDmchucvuMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmChucVu createInstance() {
		DmChucVu dmChucVu = new DmChucVu();
		return dmChucVu;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmChucVu getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
