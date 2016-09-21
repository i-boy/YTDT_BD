package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmClsKhoaList")
public class DtDmClsKhoaList extends EntityQuery<DtDmClsKhoa> {

	private static final String EJBQL = "select dtDmClsKhoa from DtDmClsKhoa dtDmClsKhoa";

	private static final String[] RESTRICTIONS = {};

	private DtDmClsKhoa dtDmClsKhoa = new DtDmClsKhoa();

	public DtDmClsKhoaList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmClsKhoa getDtDmClsKhoa() {
		return dtDmClsKhoa;
	}
}
