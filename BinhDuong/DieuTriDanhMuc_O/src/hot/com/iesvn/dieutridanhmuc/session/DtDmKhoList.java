package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmKhoList")
public class DtDmKhoList extends EntityQuery<DtDmKho> {

	private static final String EJBQL = "select dtDmKho from DtDmKho dtDmKho";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmKho.dtdmkhoMa) like concat('%',lower(#{dtDmKhoList.dtDmKho.dtdmkhoMa}),'%')",
			"lower(dtDmKho.dtdmkhoTen) like concat('%',lower(#{dtDmKhoList.dtDmKho.dtdmkhoTen}),'%')", };

	private DtDmKho dtDmKho = new DtDmKho();

	public DtDmKhoList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmKhoList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DtDmKho getDtDmKho() {
		return dtDmKho;
	}
}
