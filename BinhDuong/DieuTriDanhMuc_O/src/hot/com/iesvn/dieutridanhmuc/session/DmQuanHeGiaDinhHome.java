package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmQuanHeGiaDinhHome")
public class DmQuanHeGiaDinhHome extends EntityHome<DmQuanHeGiaDinh> {

	public void setDmQuanHeGiaDinhDmqhgdMaso(Integer id) {
		setId(id);
	}

	public Integer getDmQuanHeGiaDinhDmqhgdMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmQuanHeGiaDinh createInstance() {
		DmQuanHeGiaDinh dmQuanHeGiaDinh = new DmQuanHeGiaDinh();
		return dmQuanHeGiaDinh;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmQuanHeGiaDinh getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
