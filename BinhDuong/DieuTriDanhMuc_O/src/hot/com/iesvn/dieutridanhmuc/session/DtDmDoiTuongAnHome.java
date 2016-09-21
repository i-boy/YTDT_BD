package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmDoiTuongAnHome")
public class DtDmDoiTuongAnHome extends EntityHome<DtDmDoiTuongAn> {

	public void setDtDmDoiTuongAnDtdmdtaMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmDoiTuongAnDtdmdtaMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmDoiTuongAn createInstance() {
		DtDmDoiTuongAn dtDmDoiTuongAn = new DtDmDoiTuongAn();
		return dtDmDoiTuongAn;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmDoiTuongAn getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
