package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmTuyenKcbHome")
public class DtDmTuyenKcbHome extends EntityHome<DtDmTuyenKcb> {

	public void setDtDmTuyenKcbDtdmtuyenkcbMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmTuyenKcbDtdmtuyenkcbMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmTuyenKcb createInstance() {
		DtDmTuyenKcb dtDmTuyenKcb = new DtDmTuyenKcb();
		return dtDmTuyenKcb;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmTuyenKcb getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmKcbBhyt> getDtDmKcbBhyts() {
		return getInstance() == null ? null : new ArrayList<DtDmKcbBhyt>(
				getInstance().getDtDmKcbBhyts());
	}

	public List<DtDmKcbBhyt> getDtDmKcbBhyts_1() {
		return getInstance() == null ? null : new ArrayList<DtDmKcbBhyt>(
				getInstance().getDtDmKcbBhyts_1());
	}

}
