package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmHoatChatList")
public class DmHoatChatList extends EntityQuery<DmHoatChat> {

	private static final String EJBQL = "select dmHoatChat from DmHoatChat dmHoatChat";

	private static final String[] RESTRICTIONS = {
			"lower(dmHoatChat.dmhoatchatAtccode) like concat('%',lower(#{dmHoatChatList.dmHoatChat.dmhoatchatAtccode}),'%')",
			"lower(dmHoatChat.dmhoatchatGhichu) like concat('%',lower(#{dmHoatChatList.dmHoatChat.dmhoatchatGhichu}),'%')",
			"lower(dmHoatChat.dmhoatchatMa) like concat('%',lower(#{dmHoatChatList.dmHoatChat.dmhoatchatMa}),'%')",
			"lower(dmHoatChat.dmhoatchatMaloai) like concat('%',lower(#{dmHoatChatList.dmHoatChat.dmhoatchatMaloai}),'%')",
			"lower(dmHoatChat.dmhoatchatMaphu) like concat('%',lower(#{dmHoatChatList.dmHoatChat.dmhoatchatMaphu}),'%')",
			"lower(dmHoatChat.dmhoatchatNhom) like concat('%',lower(#{dmHoatChatList.dmHoatChat.dmhoatchatNhom}),'%')",
			"lower(dmHoatChat.dmhoatchatPhloai) like concat('%',lower(#{dmHoatChatList.dmHoatChat.dmhoatchatPhloai}),'%')",
			"lower(dmHoatChat.dmhoatchatTen) like concat('%',lower(#{dmHoatChatList.dmHoatChat.dmhoatchatTen}),'%')",
			"lower(dmHoatChat.nhanvienCn) like concat('%',lower(#{dmHoatChatList.dmHoatChat.nhanvienCn}),'%')", };

	private DmHoatChat dmHoatChat = new DmHoatChat();

	public DmHoatChatList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmHoatChatList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmHoatChat getDmHoatChat() {
		return dmHoatChat;
	}
}
