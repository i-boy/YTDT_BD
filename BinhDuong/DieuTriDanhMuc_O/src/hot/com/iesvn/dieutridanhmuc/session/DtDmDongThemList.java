package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmDongThemList")
public class DtDmDongThemList extends EntityQuery<DtDmDongThem> {

	private static final String EJBQL = "select dtDmDongThem from DtDmDongThem dtDmDongThem";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmDongThem.dtdmdtMa) like concat('%',lower(#{dtDmDongThemList.dtDmDongThem.dtdmdtMa}),'%')",
			"lower(dtDmDongThem.dtdmdtTen) like concat('%',lower(#{dtDmDongThemList.dtDmDongThem.dtdmdtTen}),'%')", };

	private DtDmDongThem dtDmDongThem = new DtDmDongThem();

	public DtDmDongThemList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmDongThem getDtDmDongThem() {
		return dtDmDongThem;
	}
}
