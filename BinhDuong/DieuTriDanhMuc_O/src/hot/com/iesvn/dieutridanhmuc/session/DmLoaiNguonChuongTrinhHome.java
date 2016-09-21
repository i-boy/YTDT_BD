package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmLoaiNguonChuongTrinhHome")
public class DmLoaiNguonChuongTrinhHome extends
		EntityHome<DmLoaiNguonChuongTrinh> {

	public void setDmLoaiNguonChuongTrinhDmlnctMaso(Integer id) {
		setId(id);
	}

	public Integer getDmLoaiNguonChuongTrinhDmlnctMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmLoaiNguonChuongTrinh createInstance() {
		DmLoaiNguonChuongTrinh dmLoaiNguonChuongTrinh = new DmLoaiNguonChuongTrinh();
		return dmLoaiNguonChuongTrinh;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmLoaiNguonChuongTrinh getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmNguonChuongTrinh> getDmNguonChuongTrinhs() {
		return getInstance() == null ? null
				: new ArrayList<DmNguonChuongTrinh>(getInstance()
						.getDmNguonChuongTrinhs());
	}

	public List<DmNguonChuongTrinh> getDmNguonChuongTrinhs_1() {
		return getInstance() == null ? null
				: new ArrayList<DmNguonChuongTrinh>(getInstance()
						.getDmNguonChuongTrinhs_1());
	}

}
