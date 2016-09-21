package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmTaiNanList")
public class DmTaiNanList extends EntityQuery<DmTaiNan> {

	private static final String EJBQL = "select dmTaiNan from DmTaiNan dmTaiNan";

	private static final String[] RESTRICTIONS = {
			"lower(dmTaiNan.dmtainanMa) like concat('%',lower(#{dmTaiNanList.dmTaiNan.dmtainanMa}),'%')",
			"lower(dmTaiNan.dmtainanTen) like concat('%',lower(#{dmTaiNanList.dmTaiNan.dmtainanTen}),'%')", };

	private DmTaiNan dmTaiNan = new DmTaiNan();

	public DmTaiNanList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmTaiNanList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DmTaiNan getDmTaiNan() {
		return dmTaiNan;
	}
}
