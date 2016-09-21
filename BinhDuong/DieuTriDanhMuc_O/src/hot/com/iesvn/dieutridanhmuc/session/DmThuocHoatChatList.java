package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmThuocHoatChatList")
public class DmThuocHoatChatList extends EntityQuery<DmThuocHoatChat> {

	private static final String EJBQL = "select dmThuocHoatChat from DmThuocHoatChat dmThuocHoatChat";

	private static final String[] RESTRICTIONS = {};

	private DmThuocHoatChat dmThuocHoatChat = new DmThuocHoatChat();

	public DmThuocHoatChatList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmThuocHoatChat getDmThuocHoatChat() {
		return dmThuocHoatChat;
	}
}
