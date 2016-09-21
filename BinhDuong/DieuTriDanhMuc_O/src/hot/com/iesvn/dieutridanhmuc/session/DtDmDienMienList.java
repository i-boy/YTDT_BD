package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmDienMienList")
public class DtDmDienMienList extends EntityQuery<DtDmDienMien> {

	private static final String EJBQL = "select dtDmDienMien from DtDmDienMien dtDmDienMien";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmDienMien.dtdmdienmienMa) like concat('%',lower(#{dtDmDienMienList.dtDmDienMien.dtdmdienmienMa}),'%')",
			"lower(dtDmDienMien.dtdmdienmienTen) like concat('%',lower(#{dtDmDienMienList.dtDmDienMien.dtdmdienmienTen}),'%')", };

	private DtDmDienMien dtDmDienMien = new DtDmDienMien();

	public DtDmDienMienList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmDienMien getDtDmDienMien() {
		return dtDmDienMien;
	}
}
