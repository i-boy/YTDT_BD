package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmPlBhytList")
public class DtDmPlBhytList extends EntityQuery<DtDmPlBhyt> {

	private static final String EJBQL = "select dtDmPlBhyt from DtDmPlBhyt dtDmPlBhyt";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmPlBhyt.dtdmphloaibhytGhichu) like concat('%',lower(#{dtDmPlBhytList.dtDmPlBhyt.dtdmphloaibhytGhichu}),'%')",
			"lower(dtDmPlBhyt.dtdmphloaibhytMa) like concat('%',lower(#{dtDmPlBhytList.dtDmPlBhyt.dtdmphloaibhytMa}),'%')",
			"lower(dtDmPlBhyt.dtdmphloaibhytTen) like concat('%',lower(#{dtDmPlBhytList.dtDmPlBhyt.dtdmphloaibhytTen}),'%')", };

	private DtDmPlBhyt dtDmPlBhyt = new DtDmPlBhyt();

	public DtDmPlBhytList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmPlBhytList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DtDmPlBhyt getDtDmPlBhyt() {
		return dtDmPlBhyt;
	}
}
