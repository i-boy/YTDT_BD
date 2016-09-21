package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmLoaiThucPhamHome")
public class DtDmLoaiThucPhamHome extends EntityHome<DtDmLoaiThucPham> {

	@In(create = true)
	DmDonViTinhHome dmDonViTinhHome;

	public void setDtDmLoaiThucPhamDtdmltpMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmLoaiThucPhamDtdmltpMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmLoaiThucPham createInstance() {
		DtDmLoaiThucPham dtDmLoaiThucPham = new DtDmLoaiThucPham();
		return dtDmLoaiThucPham;
	}

	public void wire() {
		getInstance();
		DmDonViTinh dmDonViTinh = dmDonViTinhHome.getDefinedInstance();
		if (dmDonViTinh != null) {
			getInstance().setDmDonViTinh(dmDonViTinh);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DtDmLoaiThucPham getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
