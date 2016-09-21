package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmBacSiBanKhamList")
public class DtDmBacSiBanKhamList extends EntityQuery<DtDmBacSiBanKham> {

	private static final String EJBQL = "select dtDmBacSiBanKham from DtDmBacSiBanKham dtDmBacSiBanKham";

	private static final String[] RESTRICTIONS = {};

	private DtDmBacSiBanKham dtDmBacSiBanKham = new DtDmBacSiBanKham();

	public DtDmBacSiBanKhamList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmBacSiBanKham getDtDmBacSiBanKham() {
		return dtDmBacSiBanKham;
	}
}
