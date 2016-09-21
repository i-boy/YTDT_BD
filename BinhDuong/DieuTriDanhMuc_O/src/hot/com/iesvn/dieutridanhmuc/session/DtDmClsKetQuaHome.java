package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmClsKetQuaHome")
public class DtDmClsKetQuaHome extends EntityHome<DtDmClsKetQua> {

	@In(create = true)
	DtDmClsBangGiaHome dtDmClsBangGiaHome;

	public void setDtDmClsKetQuaDtdtclskqMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmClsKetQuaDtdtclskqMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmClsKetQua createInstance() {
		DtDmClsKetQua dtDmClsKetQua = new DtDmClsKetQua();
		return dtDmClsKetQua;
	}

	public void wire() {
		getInstance();
		DtDmClsBangGia dtDmClsBangGia = dtDmClsBangGiaHome.getDefinedInstance();
		if (dtDmClsBangGia != null) {
			getInstance().setDtDmClsBangGia(dtDmClsBangGia);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DtDmClsKetQua getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
