package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dtDmLoaiBhytHome")
public class DtDmLoaiBhytHome extends EntityHome<DtDmLoaiBhyt> {

	public void setDtDmLoaiBhytDtdmloaibhytMaso(Integer id) {
		setId(id);
	}

	public Integer getDtDmLoaiBhytDtdmloaibhytMaso() {
		return (Integer) getId();
	}

	@Override
	protected DtDmLoaiBhyt createInstance() {
		DtDmLoaiBhyt dtDmLoaiBhyt = new DtDmLoaiBhyt();
		return dtDmLoaiBhyt;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DtDmLoaiBhyt getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<DtDmKhoiBhyt> getDtDmKhoiBhyts() {
		return getInstance() == null ? null : new ArrayList<DtDmKhoiBhyt>(
				getInstance().getDtDmKhoiBhyts());
	}

	public List<DtDmKhoiBhyt> getDtDmKhoiBhyts_1() {
		return getInstance() == null ? null : new ArrayList<DtDmKhoiBhyt>(
				getInstance().getDtDmKhoiBhyts_1());
	}

}
