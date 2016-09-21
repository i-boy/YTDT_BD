package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmTonGiaoList")
public class DmTonGiaoList extends EntityQuery<DmTonGiao> {

	private static final String EJBQL = "select dmTonGiao from DmTonGiao dmTonGiao";

	private static final String[] RESTRICTIONS = {
			"lower(dmTonGiao.tongiaoMa) like concat('%',lower(#{dmTonGiaoList.dmTonGiao.tongiaoMa}),'%')",
			"lower(dmTonGiao.tongiaoTen) like concat('%',lower(#{dmTonGiaoList.dmTonGiao.tongiaoTen}),'%')", };

	private DmTonGiao dmTonGiao = new DmTonGiao();

	public DmTonGiaoList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmTonGiao getDmTonGiao() {
		return dmTonGiao;
	}
}
