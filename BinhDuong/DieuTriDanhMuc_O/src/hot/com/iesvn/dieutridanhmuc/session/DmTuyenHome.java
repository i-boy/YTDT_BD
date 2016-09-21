package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmTuyenHome")
public class DmTuyenHome extends EntityHome<DmTuyen> {

	public void setDmTuyenDmtuyenMaso(Integer id) {
		setId(id);
	}

	public Integer getDmTuyenDmtuyenMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmTuyen createInstance() {
		DmTuyen dmTuyen = new DmTuyen();
		return dmTuyen;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmTuyen getDefinedInstance() {
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

	public List<DmDonVi> getDmDonVis() {
		return getInstance() == null ? null : new ArrayList<DmDonVi>(
				getInstance().getDmDonVis());
	}

	public List<DmDonVi> getDmDonVis_1() {
		return getInstance() == null ? null : new ArrayList<DmDonVi>(
				getInstance().getDmDonVis_1());
	}

	public List<DmTuyenDonVi> getDmTuyenDonVis() {
		return getInstance() == null ? null : new ArrayList<DmTuyenDonVi>(
				getInstance().getDmTuyenDonVis());
	}

	public List<DmTuyenDonVi> getDmTuyenDonVis_1() {
		return getInstance() == null ? null : new ArrayList<DmTuyenDonVi>(
				getInstance().getDmTuyenDonVis_1());
	}

}
