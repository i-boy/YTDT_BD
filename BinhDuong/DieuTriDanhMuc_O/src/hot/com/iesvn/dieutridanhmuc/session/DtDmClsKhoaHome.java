package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmClsKhoaHome")
public class DtDmClsKhoaHome extends EntityHome<DtDmClsKhoa> {

	@In(create = true)
	DmKhoaHome dmKhoaHome;
	@In(create = true)
	DtDmClsBangGiaHome dtDmClsBangGiaHome;

	public void setDtDmClsKhoaDtdmclskhoaMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmClsKhoaDtdmclskhoaMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmClsKhoa createInstance() {
		DtDmClsKhoa dtDmClsKhoa = new DtDmClsKhoa();
		return dtDmClsKhoa;
	}

	public void wire() {
		getInstance();
		DmKhoa dmKhoa = dmKhoaHome.getDefinedInstance();
		if (dmKhoa != null) {
			getInstance().setDmKhoa(dmKhoa);
		}
		DtDmClsBangGia dtDmClsBangGia = dtDmClsBangGiaHome.getDefinedInstance();
		if (dtDmClsBangGia != null) {
			getInstance().setDtDmClsBangGia(dtDmClsBangGia);
		}
	}

	public boolean isWired() {
		if (getInstance().getDmKhoa() == null)
			return false;
		if (getInstance().getDtDmClsBangGia() == null)
			return false;
		return true;
	}

	public DtDmClsKhoa getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
