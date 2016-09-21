package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmClsList")
public class DtDmClsList extends EntityQuery<DtDmCls> {

	private static final String EJBQL = "select dtDmCls from DtDmCls dtDmCls";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmCls.dtdmclsChudau) like concat('%',lower(#{dtDmClsList.dtDmCls.dtdmclsChudau}),'%')",
			"lower(dtDmCls.dtdmclsMa) like concat('%',lower(#{dtDmClsList.dtDmCls.dtdmclsMa}),'%')",
			"lower(dtDmCls.dtdmclsTen) like concat('%',lower(#{dtDmClsList.dtDmCls.dtdmclsTen}),'%')",
			"lower(dtDmCls.dtdmclsTendbf) like concat('%',lower(#{dtDmClsList.dtDmCls.dtdmclsTendbf}),'%')",
			"lower(dtDmCls.dtdmpbclsMa) like concat('%',lower(#{dtDmClsList.dtDmCls.dtdmpbclsMa}),'%')", };

	private DtDmCls dtDmCls = new DtDmCls();

	public DtDmClsList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmClsList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DtDmCls getDtDmCls() {
		return dtDmCls;
	}
}
