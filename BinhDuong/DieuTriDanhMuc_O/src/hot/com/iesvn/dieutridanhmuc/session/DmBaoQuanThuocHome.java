package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmBaoQuanThuocHome")
public class DmBaoQuanThuocHome extends EntityHome<DmBaoQuanThuoc> {

	public void setDmBaoQuanThuocDmbaoquanthuocMaso(Integer id) {
		setId(id);
	}

	public Integer getDmBaoQuanThuocDmbaoquanthuocMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmBaoQuanThuoc createInstance() {
		DmBaoQuanThuoc dmBaoQuanThuoc = new DmBaoQuanThuoc();
		return dmBaoQuanThuoc;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmBaoQuanThuoc getDefinedInstance() {
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
