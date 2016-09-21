package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmTuyenKcbList")
public class DtDmTuyenKcbList extends EntityQuery<DtDmTuyenKcb> {

	private static final String EJBQL = "select dtDmTuyenKcb from DtDmTuyenKcb dtDmTuyenKcb";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmTuyenKcb.dtdmtuyenkcbMa) like concat('%',lower(#{dtDmTuyenKcbList.dtDmTuyenKcb.dtdmtuyenkcbMa}),'%')",
			"lower(dtDmTuyenKcb.dtdmtuyenkcbTen) like concat('%',lower(#{dtDmTuyenKcbList.dtDmTuyenKcb.dtdmtuyenkcbTen}),'%')", };

	private DtDmTuyenKcb dtDmTuyenKcb = new DtDmTuyenKcb();

	public DtDmTuyenKcbList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmTuyenKcb getDtDmTuyenKcb() {
		return dtDmTuyenKcb;
	}
}
