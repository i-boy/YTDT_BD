package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmKcbBhytList")
public class DtDmKcbBhytList extends EntityQuery<DtDmKcbBhyt> {

	private static final String EJBQL = "select dtDmKcbBhyt from DtDmKcbBhyt dtDmKcbBhyt";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmKcbBhyt.dtdmhuyenbhytMa) like concat('%',lower(#{dtDmKcbBhytList.dtDmKcbBhyt.dtdmhuyenbhytMa}),'%')",
			"lower(dtDmKcbBhyt.dtdmkcbbhytGhichu) like concat('%',lower(#{dtDmKcbBhytList.dtDmKcbBhyt.dtdmkcbbhytGhichu}),'%')",
			"lower(dtDmKcbBhyt.dtdmkcbbhytMa) like concat('%',lower(#{dtDmKcbBhytList.dtDmKcbBhyt.dtdmkcbbhytMa}),'%')",
			"lower(dtDmKcbBhyt.dtdmkcbbhytTen) like concat('%',lower(#{dtDmKcbBhytList.dtDmKcbBhyt.dtdmkcbbhytTen}),'%')", };

	private DtDmKcbBhyt dtDmKcbBhyt = new DtDmKcbBhyt();

	public DtDmKcbBhytList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmKcbBhytList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));		
	}

	public DtDmKcbBhyt getDtDmKcbBhyt() {
		return dtDmKcbBhyt;
	}
}
