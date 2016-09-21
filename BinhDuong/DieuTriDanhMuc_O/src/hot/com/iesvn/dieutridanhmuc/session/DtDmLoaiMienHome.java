package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmLoaiMienHome")
public class DtDmLoaiMienHome extends EntityHome<DtDmLoaiMien> {

	public void setDtDmLoaiMienDtdmloaimienMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmLoaiMienDtdmloaimienMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmLoaiMien createInstance() {
		DtDmLoaiMien dtDmLoaiMien = new DtDmLoaiMien();
		return dtDmLoaiMien;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmLoaiMien getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
