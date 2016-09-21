package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmTuyenDonViList")
public class DmTuyenDonViList extends EntityQuery<DmTuyenDonVi> {

	private static final String EJBQL = "select dmTuyenDonVi from DmTuyenDonVi dmTuyenDonVi";

	private static final String[] RESTRICTIONS = { "lower(dmTuyenDonVi.dmtuyenMa) like concat('%',lower(#{dmTuyenDonViList.dmTuyenDonVi.dmtuyenMa}),'%')", };

	private DmTuyenDonVi dmTuyenDonVi;

	public DmTuyenDonViList() {
		dmTuyenDonVi = new DmTuyenDonVi();
		dmTuyenDonVi.setId(new DmTuyenDonViId());
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmTuyenDonVi getDmTuyenDonVi() {
		return dmTuyenDonVi;
	}
}
