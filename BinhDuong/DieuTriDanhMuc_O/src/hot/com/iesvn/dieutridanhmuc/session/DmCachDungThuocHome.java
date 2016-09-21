package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmCachDungThuocHome")
public class DmCachDungThuocHome extends EntityHome<DmCachDungThuoc> {

	public void setDmCachDungThuocDmcachdungthuocMaso(Integer id) {
		setId(id);
	}

	public Integer getDmCachDungThuocDmcachdungthuocMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmCachDungThuoc createInstance() {
		DmCachDungThuoc dmCachDungThuoc = new DmCachDungThuoc();
		return dmCachDungThuoc;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmCachDungThuoc getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmThuoc> getDmThuocs() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocs());
	}

	public List<DmThuoc> getDmThuocs_1() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocs_1());
	}

}
