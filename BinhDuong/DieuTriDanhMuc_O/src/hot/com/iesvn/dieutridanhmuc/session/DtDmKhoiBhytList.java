package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmKhoiBhytList")
public class DtDmKhoiBhytList extends EntityQuery<DtDmKhoiBhyt> {

	private static final String EJBQL = "select dtDmKhoiBhyt from DtDmKhoiBhyt dtDmKhoiBhyt";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmKhoiBhyt.dtdmkhoibhytGhichu) like concat('%',lower(#{dtDmKhoiBhytList.dtDmKhoiBhyt.dtdmkhoibhytGhichu}),'%')",
			"lower(dtDmKhoiBhyt.dtdmkhoibhytMa) like concat('%',lower(#{dtDmKhoiBhytList.dtDmKhoiBhyt.dtdmkhoibhytMa}),'%')",
			"lower(dtDmKhoiBhyt.dtdmkhoibhytTen) like concat('%',lower(#{dtDmKhoiBhytList.dtDmKhoiBhyt.dtdmkhoibhytTen}),'%')", };

	private DtDmKhoiBhyt dtDmKhoiBhyt = new DtDmKhoiBhyt();

	public DtDmKhoiBhytList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmKhoiBhytList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DtDmKhoiBhyt getDtDmKhoiBhyt() {
		return dtDmKhoiBhyt;
	}
}
