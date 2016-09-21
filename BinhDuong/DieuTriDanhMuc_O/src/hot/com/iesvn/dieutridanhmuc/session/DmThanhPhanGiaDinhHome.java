package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmThanhPhanGiaDinhHome")
public class DmThanhPhanGiaDinhHome extends EntityHome<DmThanhPhanGiaDinh> {

	public void setDmThanhPhanGiaDinhDmtpgdMaso(Integer id) {
		setId(id);
	}

	public Integer getDmThanhPhanGiaDinhDmtpgdMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmThanhPhanGiaDinh createInstance() {
		DmThanhPhanGiaDinh dmThanhPhanGiaDinh = new DmThanhPhanGiaDinh();
		return dmThanhPhanGiaDinh;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmThanhPhanGiaDinh getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
