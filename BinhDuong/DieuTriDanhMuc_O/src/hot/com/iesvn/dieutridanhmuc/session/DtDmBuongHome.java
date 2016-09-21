package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmBuongHome")
public class DtDmBuongHome extends EntityHome<DtDmBuong> {

	@In(create = true)
	DmKhoaHome dmKhoaHome;

	public void setDtDmBuongDtdmbMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmBuongDtdmbMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmBuong createInstance() {
		DtDmBuong dtDmBuong = new DtDmBuong();
		return dtDmBuong;
	}

	public void wire() {
		getInstance();
		DmKhoa dmKhoa = dmKhoaHome.getDefinedInstance();
		if (dmKhoa != null) {
			getInstance().setDmKhoa(dmKhoa);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DtDmBuong getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
