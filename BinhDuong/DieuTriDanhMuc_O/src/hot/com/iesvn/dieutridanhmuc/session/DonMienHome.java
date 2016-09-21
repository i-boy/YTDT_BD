package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("donMienHome")
public class DonMienHome extends EntityHome<DonMien> {

	@In(create = true)
	DmKhoaHome dmKhoaHome;
	@In(create = true)
	DtDmNhanVienHome dtDmNhanVienHome;

	public void setDonMienDonmienMa(String id) {
		setId(id);
	}

	public String getDonMienDonmienMa() {
		return (String) getId();
	}

	@Override
	protected DonMien createInstance() {
		DonMien donMien = new DonMien();
		return donMien;
	}

	public void wire() {
		getInstance();
		DmKhoa dmKhoa = dmKhoaHome.getDefinedInstance();
		if (dmKhoa != null) {
			getInstance().setDmKhoa(dmKhoa);
		}
		DtDmNhanVien dtDmNhanVien = dtDmNhanVienHome.getDefinedInstance();
		if (dtDmNhanVien != null) {
			getInstance().setDtDmNhanVien(dtDmNhanVien);
		}
	}

	public boolean isWired() {
		if (getInstance().getDtDmNhanVien() == null)
			return false;
		return true;
	}

	public DonMien getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
