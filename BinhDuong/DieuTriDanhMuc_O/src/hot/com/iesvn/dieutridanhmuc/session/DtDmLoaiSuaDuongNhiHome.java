package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmLoaiSuaDuongNhiHome")
public class DtDmLoaiSuaDuongNhiHome extends EntityHome<DtDmLoaiSuaDuongNhi> {

	public void setDtDmLoaiSuaDuongNhiDtdmlsdnMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmLoaiSuaDuongNhiDtdmlsdnMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmLoaiSuaDuongNhi createInstance() {
		DtDmLoaiSuaDuongNhi dtDmLoaiSuaDuongNhi = new DtDmLoaiSuaDuongNhi();
		return dtDmLoaiSuaDuongNhi;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmLoaiSuaDuongNhi getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
