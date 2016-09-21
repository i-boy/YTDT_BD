package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmCapCuuPhienList")
public class DtDmCapCuuPhienList extends EntityQuery<DtDmCapCuuPhien> {

	private static final String EJBQL = "select dtDmCapCuuPhien from DtDmCapCuuPhien dtDmCapCuuPhien";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmCapCuuPhien.dtdmccpMa) like concat('%',lower(#{dtDmCapCuuPhienList.dtDmCapCuuPhien.dtdmccpMa}),'%')",
			"lower(dtDmCapCuuPhien.dtdmccpTen) like concat('%',lower(#{dtDmCapCuuPhienList.dtDmCapCuuPhien.dtdmccpTen}),'%')", };

	private DtDmCapCuuPhien dtDmCapCuuPhien = new DtDmCapCuuPhien();

	public DtDmCapCuuPhienList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmCapCuuPhien getDtDmCapCuuPhien() {
		return dtDmCapCuuPhien;
	}
}
