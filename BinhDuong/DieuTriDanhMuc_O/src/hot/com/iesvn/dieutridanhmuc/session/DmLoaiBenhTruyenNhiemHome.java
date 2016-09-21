package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmLoaiBenhTruyenNhiemHome")
public class DmLoaiBenhTruyenNhiemHome extends
		EntityHome<DmLoaiBenhTruyenNhiem> {

	public void setDmLoaiBenhTruyenNhiemDmlbtnMaso(Integer id) {
		setId(id);
	}

	public Integer getDmLoaiBenhTruyenNhiemDmlbtnMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmLoaiBenhTruyenNhiem createInstance() {
		DmLoaiBenhTruyenNhiem dmLoaiBenhTruyenNhiem = new DmLoaiBenhTruyenNhiem();
		return dmLoaiBenhTruyenNhiem;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmLoaiBenhTruyenNhiem getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmBenhTruyenNhiem> getDmBenhTruyenNhiems() {
		return getInstance() == null ? null : new ArrayList<DmBenhTruyenNhiem>(
				getInstance().getDmBenhTruyenNhiems());
	}

}
