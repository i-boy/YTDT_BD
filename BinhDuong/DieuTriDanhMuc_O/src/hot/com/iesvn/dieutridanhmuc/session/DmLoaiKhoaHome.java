package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmLoaiKhoaHome")
public class DmLoaiKhoaHome extends EntityHome<DmLoaiKhoa> {

	public void setDmLoaiKhoaDmloaikhoaMaso(Integer id) {
		setId(id);
	}

	public Integer getDmLoaiKhoaDmloaikhoaMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmLoaiKhoa createInstance() {
		DmLoaiKhoa dmLoaiKhoa = new DmLoaiKhoa();
		return dmLoaiKhoa;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmLoaiKhoa getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmKhoa> getDmKhoas() {
		return getInstance() == null ? null : new ArrayList<DmKhoa>(
				getInstance().getDmKhoas());
	}

	public List<DmKhoa> getDmKhoas_1() {
		return getInstance() == null ? null : new ArrayList<DmKhoa>(
				getInstance().getDmKhoas_1());
	}

}
