package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmLoaiBenhVienHome")
public class DmLoaiBenhVienHome extends EntityHome<DmLoaiBenhVien> {

	public void setDmLoaiBenhVienDmloaibvMaso(Integer id) {
		setId(id);
	}

	public Integer getDmLoaiBenhVienDmloaibvMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmLoaiBenhVien createInstance() {
		DmLoaiBenhVien dmLoaiBenhVien = new DmLoaiBenhVien();
		return dmLoaiBenhVien;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmLoaiBenhVien getDefinedInstance() {
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
