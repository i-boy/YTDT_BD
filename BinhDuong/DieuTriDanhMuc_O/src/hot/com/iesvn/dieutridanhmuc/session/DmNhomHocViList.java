package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmNhomHocViList")
public class DmNhomHocViList extends EntityQuery<DmNhomHocVi> {

	private static final String EJBQL = "select dmNhomHocVi from DmNhomHocVi dmNhomHocVi";

	private static final String[] RESTRICTIONS = {
			"lower(dmNhomHocVi.dmnhomhocviMa) like concat('%',lower(#{dmNhomHocViList.dmNhomHocVi.dmnhomhocviMa}),'%')",
			"lower(dmNhomHocVi.dmnhomhocviTen) like concat('%',lower(#{dmNhomHocViList.dmNhomHocVi.dmnhomhocviTen}),'%')",
			"lower(dmNhomHocVi.dmnhomhocviTenbc) like concat('%',lower(#{dmNhomHocViList.dmNhomHocVi.dmnhomhocviTenbc}),'%')", };

	private DmNhomHocVi dmNhomHocVi = new DmNhomHocVi();

	public DmNhomHocViList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmNhomHocVi getDmNhomHocVi() {
		return dmNhomHocVi;
	}
}
