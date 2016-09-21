package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmNhomHocViHome")
public class DmNhomHocViHome extends EntityHome<DmNhomHocVi> {

	public void setDmNhomHocViDmnhomhocviMaso(Integer id) {
		setId(id);
	}

	public Integer getDmNhomHocViDmnhomhocviMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmNhomHocVi createInstance() {
		DmNhomHocVi dmNhomHocVi = new DmNhomHocVi();
		return dmNhomHocVi;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmNhomHocVi getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmHocVi> getDmHocVis() {
		return getInstance() == null ? null : new ArrayList<DmHocVi>(
				getInstance().getDmHocVis());
	}

	public List<DmHocVi> getDmHocVis_1() {
		return getInstance() == null ? null : new ArrayList<DmHocVi>(
				getInstance().getDmHocVis_1());
	}

}
