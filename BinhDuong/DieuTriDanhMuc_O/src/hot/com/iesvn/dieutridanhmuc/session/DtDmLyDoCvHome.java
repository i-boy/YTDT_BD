package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmLyDoCvHome")
public class DtDmLyDoCvHome extends EntityHome<DtDmLyDoCv> {

	public void setDtDmLyDoCvDtdmlydocvMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmLyDoCvDtdmlydocvMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmLyDoCv createInstance() {
		DtDmLyDoCv dtDmLyDoCv = new DtDmLyDoCv();
		return dtDmLyDoCv;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmLyDoCv getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
