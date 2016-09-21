package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmNhanvienKhoaList")
public class DtDmNhanvienKhoaList extends EntityQuery<DtDmNhanvienKhoa> {

	private static final String EJBQL = "select dtDmNhanvienKhoa from DtDmNhanvienKhoa dtDmNhanvienKhoa";

	private static final String[] RESTRICTIONS = {};

	private DtDmNhanvienKhoa dtDmNhanvienKhoa = new DtDmNhanvienKhoa();

	public DtDmNhanvienKhoaList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmNhanvienKhoaList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}
	
	public DtDmNhanvienKhoa getDtDmNhanvienKhoa() {
		return dtDmNhanvienKhoa;
	}
}
