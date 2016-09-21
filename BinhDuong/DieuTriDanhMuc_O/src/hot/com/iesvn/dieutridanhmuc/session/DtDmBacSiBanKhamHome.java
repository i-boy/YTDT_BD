package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmBacSiBanKhamHome")
public class DtDmBacSiBanKhamHome extends EntityHome<DtDmBacSiBanKham> {

	@In(create = true)
	DtDmBanKhamHome dtDmBanKhamHome;
	@In(create = true)
	DtDmNhanVienHome dtDmNhanVienHome;

	public void setDtDmBacSiBanKhamDtdmbacsibankhamMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmBacSiBanKhamDtdmbacsibankhamMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmBacSiBanKham createInstance() {
		DtDmBacSiBanKham dtDmBacSiBanKham = new DtDmBacSiBanKham();
		return dtDmBacSiBanKham;
	}

	public void wire() {
		getInstance();
		DtDmBanKham dtDmBanKham = dtDmBanKhamHome.getDefinedInstance();
		if (dtDmBanKham != null) {
			getInstance().setDtDmBanKham(dtDmBanKham);
		}
		DtDmNhanVien dtDmNhanVien = dtDmNhanVienHome.getDefinedInstance();
		if (dtDmNhanVien != null) {
			getInstance().setDtDmNhanVien(dtDmNhanVien);
		}
	}

	public boolean isWired() {
		if (getInstance().getDtDmBanKham() == null)
			return false;
		if (getInstance().getDtDmNhanVien() == null)
			return false;
		return true;
	}

	public DtDmBacSiBanKham getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
