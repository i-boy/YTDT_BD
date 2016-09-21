package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmLopBhytList")
public class DtDmLopBhytList extends EntityQuery<DtDmLopBhyt> {

	private static final String EJBQL = "select dtDmLopBhyt from DtDmLopBhyt dtDmLopBhyt";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmLopBhyt.dtdmlopbhytMa) like concat('%',lower(#{dtDmLopBhytList.dtDmLopBhyt.dtdmlopbhytMa}),'%')",
			"lower(dtDmLopBhyt.dtdmlopbhytTen) like concat('%',lower(#{dtDmLopBhytList.dtDmLopBhyt.dtdmlopbhytTen}),'%')", };

	private DtDmLopBhyt dtDmLopBhyt = new DtDmLopBhyt();

	public DtDmLopBhytList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmLopBhyt getDtDmLopBhyt() {
		return dtDmLopBhyt;
	}
}
