package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmLoaiAn2Home")
public class DtDmLoaiAn2Home extends EntityHome<DtDmLoaiAn2> {

	@In(create = true)
	DtDmLoaiAnHome dtDmLoaiAnHome;

	public void setDtDmLoaiAn2Dtdmla2Maso(Integer id) {
		setId(id);
	}

	public Integer getDtDmLoaiAn2Dtdmla2Maso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmLoaiAn2 createInstance() {
		DtDmLoaiAn2 dtDmLoaiAn2 = new DtDmLoaiAn2();
		return dtDmLoaiAn2;
	}

	public void wire() {
		getInstance();
		DtDmLoaiAn dtDmLoaiAn = dtDmLoaiAnHome.getDefinedInstance();
		if (dtDmLoaiAn != null) {
			getInstance().setDtDmLoaiAn(dtDmLoaiAn);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DtDmLoaiAn2 getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
