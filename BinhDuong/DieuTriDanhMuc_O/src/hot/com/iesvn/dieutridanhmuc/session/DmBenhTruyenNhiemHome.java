package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmBenhTruyenNhiemHome")
public class DmBenhTruyenNhiemHome extends EntityHome<DmBenhTruyenNhiem> {

	@In(create = true)
	DmBenhVnHome dmBenhVnHome;
	@In(create = true)
	DmLoaiBenhTruyenNhiemHome dmLoaiBenhTruyenNhiemHome;

	public void setDmBenhTruyenNhiemDmbtnMaso(Integer id) {
		setId(id);
	}

	public Integer getDmBenhTruyenNhiemDmbtnMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmBenhTruyenNhiem createInstance() {
		DmBenhTruyenNhiem dmBenhTruyenNhiem = new DmBenhTruyenNhiem();
		return dmBenhTruyenNhiem;
	}

	public void wire() {
		getInstance();
		DmBenhVn dmBenhVn = dmBenhVnHome.getDefinedInstance();
		if (dmBenhVn != null) {
			getInstance().setDmBenhVn(dmBenhVn);
		}
		DmLoaiBenhTruyenNhiem dmLoaiBenhTruyenNhiem = dmLoaiBenhTruyenNhiemHome
				.getDefinedInstance();
		if (dmLoaiBenhTruyenNhiem != null) {
			getInstance().setDmLoaiBenhTruyenNhiem(dmLoaiBenhTruyenNhiem);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DmBenhTruyenNhiem getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
