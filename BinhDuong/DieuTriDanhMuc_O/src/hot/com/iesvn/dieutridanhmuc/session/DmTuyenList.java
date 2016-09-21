package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmTuyenList")
public class DmTuyenList extends EntityQuery<DmTuyen> {

	private static final String EJBQL = "select dmTuyen from DmTuyen dmTuyen";

	private static final String[] RESTRICTIONS = {
			"lower(dmTuyen.dmtuyenMa) like concat('%',lower(#{dmTuyenList.dmTuyen.dmtuyenMa}),'%')",
			"lower(dmTuyen.dmtuyenTen) like concat('%',lower(#{dmTuyenList.dmTuyen.dmtuyenTen}),'%')", };

	private DmTuyen dmTuyen = new DmTuyen();

	public DmTuyenList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmTuyen getDmTuyen() {
		return dmTuyen;
	}
}
