package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmHocViList")
public class DmHocViList extends EntityQuery<DmHocVi> {

	private static final String EJBQL = "select dmHocVi from DmHocVi dmHocVi";

	private static final String[] RESTRICTIONS = {
			"lower(dmHocVi.dmhocviMa) like concat('%',lower(#{dmHocViList.dmHocVi.dmhocviMa}),'%')",
			"lower(dmHocVi.dmhocviTen) like concat('%',lower(#{dmHocViList.dmHocVi.dmhocviTen}),'%')",
			"lower(dmHocVi.dmhocviTenbc) like concat('%',lower(#{dmHocViList.dmHocVi.dmhocviTenbc}),'%')", };

	private DmHocVi dmHocVi = new DmHocVi();

	public DmHocViList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmHocVi getDmHocVi() {
		return dmHocVi;
	}
}
