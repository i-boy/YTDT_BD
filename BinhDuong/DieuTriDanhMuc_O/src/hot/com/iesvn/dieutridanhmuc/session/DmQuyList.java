package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmQuyList")
public class DmQuyList extends EntityQuery<DmQuy> {

	private static final String EJBQL = "select dmQuy from DmQuy dmQuy";

	private static final String[] RESTRICTIONS = {
			"lower(dmQuy.dmquyMa) like concat('%',lower(#{dmQuyList.dmQuy.dmquyMa}),'%')",
			"lower(dmQuy.dmquyTen) like concat('%',lower(#{dmQuyList.dmQuy.dmquyTen}),'%')", };

	private DmQuy dmQuy = new DmQuy();

	public DmQuyList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmQuy getDmQuy() {
		return dmQuy;
	}
}
