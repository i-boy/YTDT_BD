package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmPhanNhomThuocHome")
public class DmPhanNhomThuocHome extends EntityHome<DmPhanNhomThuoc> {

	@In(create = true)
	DmNhomBaoCaoThuocHome dmNhomBaoCaoThuocHome;

	public void setDmPhanNhomThuocDmphannhomthuocMaso(Integer id) {
		setId(id);
	}

	public Integer getDmPhanNhomThuocDmphannhomthuocMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmPhanNhomThuoc createInstance() {
		DmPhanNhomThuoc dmPhanNhomThuoc = new DmPhanNhomThuoc();
		return dmPhanNhomThuoc;
	}

	public void wire() {
		getInstance();
		DmNhomBaoCaoThuoc dmNhomBaoCaoThuoc = dmNhomBaoCaoThuocHome
				.getDefinedInstance();
		if (dmNhomBaoCaoThuoc != null) {
			getInstance().setDmNhomBaoCaoThuoc(dmNhomBaoCaoThuoc);
		}
	}

	public boolean isWired() {
		return true;
	}

	public DmPhanNhomThuoc getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DmThuoc> getDmThuocsForDmphannhomthuocMaso() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocsForDmphannhomthuocMaso());
	}

	public List<DmThuoc> getDmThuocsForDmphannhomthuocMaso2() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocsForDmphannhomthuocMaso2());
	}

	public List<DmThuoc> getDmThuocsForDmphannhomthuocMaso2_1() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocsForDmphannhomthuocMaso2_1());
	}

	public List<DmThuoc> getDmThuocsForDmphannhomthuocMaso_1() {
		return getInstance() == null ? null : new ArrayList<DmThuoc>(
				getInstance().getDmThuocsForDmphannhomthuocMaso_1());
	}

}
