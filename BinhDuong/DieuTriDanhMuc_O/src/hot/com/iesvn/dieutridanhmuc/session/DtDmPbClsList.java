package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmPbClsList")
public class DtDmPbClsList extends EntityQuery<DtDmPbCls> {

	private static final String EJBQL = "select dtDmPbCls from DtDmPbCls dtDmPbCls";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmPbCls.dtdmpbclsMa) like concat('%',lower(#{dtDmPbClsList.dtDmPbCls.dtdmpbclsMa}),'%')",
			"lower(dtDmPbCls.dtdmpbclsTen) like concat('%',lower(#{dtDmPbClsList.dtDmPbCls.dtdmpbclsTen}),'%')", };

	private DtDmPbCls dtDmPbCls = new DtDmPbCls();

	public DtDmPbClsList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmPbClsList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DtDmPbCls getDtDmPbCls() {
		return dtDmPbCls;
	}
}
