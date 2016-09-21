package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmChiDanHome")
public class DtDmChiDanHome extends EntityHome<DtDmChiDan> {

	public void setDtDmChiDanDtdmchidanMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmChiDanDtdmchidanMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmChiDan createInstance() {
		DtDmChiDan dtDmChiDan = new DtDmChiDan();
		return dtDmChiDan;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmChiDan getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmChiDanDvt> getDtDmChiDanDvts() {
		return getInstance() == null ? null : new ArrayList<DtDmChiDanDvt>(
				getInstance().getDtDmChiDanDvts());
	}

	public List<DtDmChiDanDvt> getDtDmChiDanDvts_1() {
		return getInstance() == null ? null : new ArrayList<DtDmChiDanDvt>(
				getInstance().getDtDmChiDanDvts_1());
	}

}
