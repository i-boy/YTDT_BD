package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmNhomKhoaHome")
public class DmNhomKhoaHome extends EntityHome<DmNhomKhoa> {

	public void setDmNhomKhoaDmnhomkhoaMaso(Integer id) {
		setId(id);
	}

	public Integer getDmNhomKhoaDmnhomkhoaMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmNhomKhoa createInstance() {
		DmNhomKhoa dmNhomKhoa = new DmNhomKhoa();
		return dmNhomKhoa;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmNhomKhoa getDefinedInstance() {
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
