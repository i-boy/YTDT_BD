package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmMqlBhytList")
public class DtDmMqlBhytList extends EntityQuery<DtDmMqlBhyt> {

	private static final String EJBQL = "select dtDmMqlBhyt from DtDmMqlBhyt dtDmMqlBhyt";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmMqlBhyt.dtdmmqlbhytMa) like concat('%',lower(#{dtDmMqlBhytList.dtDmMqlBhyt.dtdmmqlbhytMa}),'%')",
			"lower(dtDmMqlBhyt.dtdmmqlbhytTen) like concat('%',lower(#{dtDmMqlBhytList.dtDmMqlBhyt.dtdmmqlbhytTen}),'%')", };

	private DtDmMqlBhyt dtDmMqlBhyt = new DtDmMqlBhyt();

	public DtDmMqlBhytList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmMqlBhyt getDtDmMqlBhyt() {
		return dtDmMqlBhyt;
	}
}
