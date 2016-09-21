package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmDonViTinhHome")
public class DmDonViTinhHome extends EntityHome<DmDonViTinh> {

	public void setDmDonViTinhDmdonvitinhMaso(Integer id) {
		setId(id);
	}

	public Integer getDmDonViTinhDmdonvitinhMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmDonViTinh createInstance() {
		DmDonViTinh dmDonViTinh = new DmDonViTinh();
		return dmDonViTinh;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmDonViTinh getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmThuoc> getDmThuocs() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocs());
	}

	public List<DmThuoc> getDmThuocs_1() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocs_1());
	}

	public List<DtDmChiDanDvt> getDtDmChiDanDvts() {
		return getInstance() == null ? null : new ArrayList<DtDmChiDanDvt>(
				getInstance().getDtDmChiDanDvts());
	}

	public List<DtDmChiDanDvt> getDtDmChiDanDvts_1() {
		return getInstance() == null ? null : new ArrayList<DtDmChiDanDvt>(
				getInstance().getDtDmChiDanDvts_1());
	}

	public List<DtDmLoaiThucPham> getDtDmLoaiThucPhams() {
		return getInstance() == null ? null : new ArrayList<DtDmLoaiThucPham>(
				getInstance().getDtDmLoaiThucPhams());
	}

}
