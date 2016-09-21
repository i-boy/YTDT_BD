package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmTienKhamList")
public class DtDmTienKhamList extends EntityQuery<DtDmTienKham> {

	private static final String EJBQL = "select dtDmTienKham from DtDmTienKham dtDmTienKham";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmTienKham.dtdmtienkhamMa) like concat('%',lower(#{dtDmTienKhamList.dtDmTienKham.dtdmtienkhamMa}),'%')",
			"lower(dtDmTienKham.dtdmtienkhamMaloai) like concat('%',lower(#{dtDmTienKhamList.dtDmTienKham.dtdmtienkhamMaloai}),'%')",
			"lower(dtDmTienKham.dtdmtienkhamTen) like concat('%',lower(#{dtDmTienKhamList.dtDmTienKham.dtdmtienkhamTen}),'%')", };

	private DtDmTienKham dtDmTienKham = new DtDmTienKham();

	public DtDmTienKhamList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmTienKham getDtDmTienKham() {
		return dtDmTienKham;
	}
}
