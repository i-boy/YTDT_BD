package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmBaiThuocHome")
public class DmBaiThuocHome extends EntityHome<DmBaiThuoc> {

	public void setDmBaiThuocDmbaithuocMaso(Integer id) {
		setId(id);
	}

	public Integer getDmBaiThuocDmbaithuocMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmBaiThuoc createInstance() {
		DmBaiThuoc dmBaiThuoc = new DmBaiThuoc();
		return dmBaiThuoc;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmBaiThuoc getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
