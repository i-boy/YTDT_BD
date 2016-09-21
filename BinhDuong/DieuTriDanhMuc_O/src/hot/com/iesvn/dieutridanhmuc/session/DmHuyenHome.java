package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmHuyenHome")
public class DmHuyenHome extends EntityHome<DmHuyen> {

	@In(create = true)
	DmTinhHome dmTinhHome;

	public void setDmHuyenDmhuyenMaso(Integer id) {
		setId(id);
	}

	public Integer getDmHuyenDmhuyenMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmHuyen createInstance() {
		DmHuyen dmHuyen = new DmHuyen();
		return dmHuyen;
	}

	public void wire() {
		getInstance();
		DmTinh dmTinh = dmTinhHome.getDefinedInstance();
		if (dmTinh != null) {
			getInstance().setDmTinh(dmTinh);
		}
	}

	public boolean isWired() {
		if (getInstance().getDmTinh() == null)
			return false;
		return true;
	}

	public DmHuyen getDefinedInstance() {
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

	public List<DmXa> getDmXas() {
		return getInstance() == null ? null : new ArrayList<DmXa>(getInstance()
				.getDmXas());
	}

	public List<DmXa> getDmXas_1() {
		return getInstance() == null ? null : new ArrayList<DmXa>(getInstance()
				.getDmXas_1());
	}

}
