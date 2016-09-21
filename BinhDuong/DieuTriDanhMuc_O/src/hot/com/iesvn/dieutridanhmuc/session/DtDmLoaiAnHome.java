package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmLoaiAnHome")
public class DtDmLoaiAnHome extends EntityHome<DtDmLoaiAn> {

	public void setDtDmLoaiAnDtdmlaMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmLoaiAnDtdmlaMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmLoaiAn createInstance() {
		DtDmLoaiAn dtDmLoaiAn = new DtDmLoaiAn();
		return dtDmLoaiAn;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmLoaiAn getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmLoaiAn2> getDtDmLoaiAn2s() {
		return getInstance() == null ? null : new ArrayList<DtDmLoaiAn2>(
				getInstance().getDtDmLoaiAn2s());
	}

}
