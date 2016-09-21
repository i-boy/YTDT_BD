package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmHocViHome")
public class DmHocViHome extends EntityHome<DmHocVi> {

	@In(create = true)
	DmNhomHocViHome dmNhomHocViHome;

	public void setDmHocViDmhocviMaso(Integer id) {
		setId(id);
	}

	public Integer getDmHocViDmhocviMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmHocVi createInstance() {
		DmHocVi dmHocVi = new DmHocVi();
		return dmHocVi;
	}

	public void wire() {
		getInstance();
		DmNhomHocVi dmNhomHocVi = dmNhomHocViHome.getDefinedInstance();
		if (dmNhomHocVi != null) {
			getInstance().setDmNhomHocVi(dmNhomHocVi);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DmHocVi getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmNhanVien> getDtDmNhanViens() {
		return getInstance() == null ? null : new ArrayList<DtDmNhanVien>(
				getInstance().getDtDmNhanViens());
	}

	public List<DtDmNhanVien> getDtDmNhanViens_1() {
		return getInstance() == null ? null : new ArrayList<DtDmNhanVien>(
				getInstance().getDtDmNhanViens_1());
	}

}
