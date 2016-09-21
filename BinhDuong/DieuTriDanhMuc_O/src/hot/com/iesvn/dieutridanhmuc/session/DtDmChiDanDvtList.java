package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmChiDanDvtList")
public class DtDmChiDanDvtList extends EntityQuery<DtDmChiDanDvt> {

	private static final String EJBQL = "select dtDmChiDanDvt from DtDmChiDanDvt dtDmChiDanDvt";

	private static final String[] RESTRICTIONS = {};

	private DtDmChiDanDvt dtDmChiDanDvt = new DtDmChiDanDvt();

	public DtDmChiDanDvtList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmChiDanDvt getDtDmChiDanDvt() {
		return dtDmChiDanDvt;
	}
}
