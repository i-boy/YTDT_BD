package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmLoiDanHome")
public class DtDmLoiDanHome extends EntityHome<DtDmLoiDan> {

	public void setDtDmLoiDanDtdmldMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmLoiDanDtdmldMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmLoiDan createInstance() {
		DtDmLoiDan dtDmLoiDan = new DtDmLoiDan();
		return dtDmLoiDan;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmLoiDan getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
