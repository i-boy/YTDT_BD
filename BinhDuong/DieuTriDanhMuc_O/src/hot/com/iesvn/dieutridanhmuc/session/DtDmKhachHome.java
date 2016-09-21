package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmKhachHome")
public class DtDmKhachHome extends EntityHome<DtDmKhach> {

	@In(create = true)
	DmKhoaHome dmKhoaHome;
	@In(create = true)
	DmNguonChuongTrinhHome dmNguonChuongTrinhHome;
	@In(create = true)
	DmNguonKinhPhiHome dmNguonKinhPhiHome;
	@In(create = true)
	DmTinhHome dmTinhHome;

	public void setDtDmKhachDtdmkhachMa(String id) {
		setId(id);
	}

	public String getDtDmKhachDtdmkhachMa() {
		return (String) getId();
	}

	@Override
	protected DtDmKhach createInstance() {
		DtDmKhach dtDmKhach = new DtDmKhach();
		return dtDmKhach;
	}

	public void wire() {
		getInstance();
		DmKhoa dmKhoa = dmKhoaHome.getDefinedInstance();
		if (dmKhoa != null) {
			getInstance().setDmKhoa(dmKhoa);
		}
		DmNguonChuongTrinh dmNguonChuongTrinh = dmNguonChuongTrinhHome
				.getDefinedInstance();
		if (dmNguonChuongTrinh != null) {
			getInstance().setDmNguonChuongTrinh(dmNguonChuongTrinh);
		}
		DmNguonKinhPhi dmNguonKinhPhi = dmNguonKinhPhiHome.getDefinedInstance();
		if (dmNguonKinhPhi != null) {
			getInstance().setDmNguonKinhPhi(dmNguonKinhPhi);
		}
		DmTinh dmTinh = dmTinhHome.getDefinedInstance();
		if (dmTinh != null) {
			getInstance().setDmTinh(dmTinh);
		}
	}

	public boolean isWired() {
		if (getInstance().getDmNguonKinhPhi() == null)
			return false;
		if (getInstance().getDmTinh() == null)
			return false;
		return true;
	}

	public DtDmKhach getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
