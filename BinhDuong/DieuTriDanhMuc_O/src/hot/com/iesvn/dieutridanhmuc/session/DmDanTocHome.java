package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmDanTocHome")
public class DmDanTocHome extends EntityHome<DmDanToc> {

	public void setDmDanTocDmdantocMaso(Integer id) {
		setId(id);
	}

	public Integer getDmDanTocDmdantocMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmDanToc createInstance() {
		DmDanToc dmDanToc = new DmDanToc();
		return dmDanToc;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmDanToc getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
