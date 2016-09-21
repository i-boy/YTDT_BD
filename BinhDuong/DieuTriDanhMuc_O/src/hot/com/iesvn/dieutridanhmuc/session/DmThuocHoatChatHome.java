package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dmThuocHoatChatHome")
public class DmThuocHoatChatHome extends EntityHome<DmThuocHoatChat> {

	public void setDmThuocHoatChatDmthuochoatchatMaso(Integer id) {
		setId(id);
	}

	public Integer getDmThuocHoatChatDmthuochoatchatMaso() {
		return (Integer) getId();
	}

	@Override
	protected DmThuocHoatChat createInstance() {
		DmThuocHoatChat dmThuocHoatChat = new DmThuocHoatChat();
		return dmThuocHoatChat;
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public DmThuocHoatChat getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
