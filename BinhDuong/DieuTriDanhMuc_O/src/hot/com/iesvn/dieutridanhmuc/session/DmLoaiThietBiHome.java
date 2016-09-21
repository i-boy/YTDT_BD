package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmLoaiThietBiHome")
public class DmLoaiThietBiHome extends EntityHome<DmLoaiThietBi> {

	public void setDmLoaiThietBiDmloaithietbiMaso(Integer id) {
		setId(id);
	}

	public Integer getDmLoaiThietBiDmloaithietbiMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmLoaiThietBi createInstance() {
		DmLoaiThietBi dmLoaiThietBi = new DmLoaiThietBi();
		return dmLoaiThietBi;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmLoaiThietBi getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
