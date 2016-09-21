package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmNhomBhytList")
public class DtDmNhomBhytList extends EntityQuery<DtDmNhomBhyt> {

	private static final String EJBQL = "select dtDmNhomBhyt from DtDmNhomBhyt dtDmNhomBhyt";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmNhomBhyt.dtdmnhombhytMa) like concat('%',lower(#{dtDmNhomBhytList.dtDmNhomBhyt.dtdmnhombhytMa}),'%')",
			"lower(dtDmNhomBhyt.dtdmnhombhytTen) like concat('%',lower(#{dtDmNhomBhytList.dtDmNhomBhyt.dtdmnhombhytTen}),'%')", };

	private DtDmNhomBhyt dtDmNhomBhyt = new DtDmNhomBhyt();

	public DtDmNhomBhytList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmNhomBhyt getDtDmNhomBhyt() {
		return dtDmNhomBhyt;
	}
}
