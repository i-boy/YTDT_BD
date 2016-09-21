package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmNhanvienKhoaHome")
public class DtDmNhanvienKhoaHome extends EntityHome<DtDmNhanvienKhoa> {

	@In(create = true)
	DmKhoaHome dmKhoaHome;
	@In(create = true)
	DtDmNhanVienHome dtDmNhanVienHome;

	public void setDtDmNhanvienKhoaDtdmnhanvienkhoaMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmNhanvienKhoaDtdmnhanvienkhoaMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmNhanvienKhoa createInstance() {
		DtDmNhanvienKhoa dtDmNhanvienKhoa = new DtDmNhanvienKhoa();
		return dtDmNhanvienKhoa;
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
		if (getInstance().getDmKhoa() == null)
			return false;
		if (getInstance().getDtDmNhanVien() == null)
			return false;
		return true;
	}

	public DtDmNhanvienKhoa getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
