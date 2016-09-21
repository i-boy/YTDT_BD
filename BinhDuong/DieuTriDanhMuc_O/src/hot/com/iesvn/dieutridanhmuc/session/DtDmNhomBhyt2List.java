package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmNhomBhyt2List")
public class DtDmNhomBhyt2List extends EntityQuery<DtDmNhomBhyt2> {

	private static final String EJBQL = "select dtDmNhomBhyt2 from DtDmNhomBhyt2 dtDmNhomBhyt2";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmNhomBhyt2.dtdmnhombhyt2Ma) like concat('%',lower(#{dtDmNhomBhyt2List.dtDmNhomBhyt2.dtdmnhombhyt2Ma}),'%')",
			"lower(dtDmNhomBhyt2.dtdmnhombhyt2Ten) like concat('%',lower(#{dtDmNhomBhyt2List.dtDmNhomBhyt2.dtdmnhombhyt2Ten}),'%')", };

	private DtDmNhomBhyt2 dtDmNhomBhyt2 = new DtDmNhomBhyt2();

	public DtDmNhomBhyt2List() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmNhomBhyt2 getDtDmNhomBhyt2() {
		return dtDmNhomBhyt2;
	}
}
