package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmCumList")
public class DtDmCumList extends EntityQuery<DtDmCum> {

	private static final String EJBQL = "select dtDmCum from DtDmCum dtDmCum";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmCum.dtdmcumGhichu) like concat('%',lower(#{dtDmCumList.dtDmCum.dtdmcumGhichu}),'%')",
			"lower(dtDmCum.dtdmcumMa) like concat('%',lower(#{dtDmCumList.dtDmCum.dtdmcumMa}),'%')",
			"lower(dtDmCum.dtdmcumTen) like concat('%',lower(#{dtDmCumList.dtDmCum.dtdmcumTen}),'%')", };

	private DtDmCum dtDmCum = new DtDmCum();

	public DtDmCumList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmCumList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DtDmCum getDtDmCum() {
		return dtDmCum;
	}
}
