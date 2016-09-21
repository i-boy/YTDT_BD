package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmNhomBaoCaoLoaiThuocHome")
public class DmNhomBaoCaoLoaiThuocHome extends
		EntityHome<DmNhomBaoCaoLoaiThuoc> {

	@In(create = true)
	DmLoaiThuocHome dmLoaiThuocHome;
	@In(create = true)
	DmNhomBaoCaoThuocHome dmNhomBaoCaoThuocHome;

	public void setDmNhomBaoCaoLoaiThuocDmnhombcthuocloaithuocMaso(Integer id) {
		setId(id);
	}

	public Integer getDmNhomBaoCaoLoaiThuocDmnhombcthuocloaithuocMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmNhomBaoCaoLoaiThuoc createInstance() {
		DmNhomBaoCaoLoaiThuoc dmNhomBaoCaoLoaiThuoc = new DmNhomBaoCaoLoaiThuoc();
		return dmNhomBaoCaoLoaiThuoc;
	}

	public void wire() {
		getInstance();
		DmLoaiThuoc dmLoaiThuoc = dmLoaiThuocHome.getDefinedInstance();
		if (dmLoaiThuoc != null) {
			getInstance().setDmLoaiThuoc(dmLoaiThuoc);
		}
		DmNhomBaoCaoThuoc dmNhomBaoCaoThuoc = dmNhomBaoCaoThuocHome
				.getDefinedInstance();
		if (dmNhomBaoCaoThuoc != null) {
			getInstance().setDmNhomBaoCaoThuoc(dmNhomBaoCaoThuoc);
		}
	}

	public boolean isWired() {
		if (getInstance().getDmLoaiThuoc() == null)
			return false;
		if (getInstance().getDmNhomBaoCaoThuoc() == null)
			return false;
		return true;
	}

	public DmNhomBaoCaoLoaiThuoc getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
