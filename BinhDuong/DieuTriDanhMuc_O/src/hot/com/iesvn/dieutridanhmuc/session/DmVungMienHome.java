package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmVungMienHome")
public class DmVungMienHome extends EntityHome<DmVungMien> {

	public void setDmVungMienDmvungmienMaso(Integer id) {
		setId(id);
	}

	public Integer getDmVungMienDmvungmienMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmVungMien createInstance() {
		DmVungMien dmVungMien = new DmVungMien();
		return dmVungMien;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmVungMien getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmBenhVien> getDmBenhViens() {
		return getInstance() == null ? null : new ArrayList<DmBenhVien>(
				getInstance().getDmBenhViens());
	}

	public List<DmBenhVien> getDmBenhViens_1() {
		return getInstance() == null ? null : new ArrayList<DmBenhVien>(
				getInstance().getDmBenhViens_1());
	}

}
