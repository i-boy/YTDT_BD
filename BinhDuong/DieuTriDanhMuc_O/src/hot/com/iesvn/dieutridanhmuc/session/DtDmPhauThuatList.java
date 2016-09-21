package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmPhauThuatList")
public class DtDmPhauThuatList extends EntityQuery<DtDmPhauThuat> {

	private static final String EJBQL = "select dtDmPhauThuat from DtDmPhauThuat dtDmPhauThuat";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmPhauThuat.dtdmphauthuatLienhe) like concat('%',lower(#{dtDmPhauThuatList.dtDmPhauThuat.dtdmphauthuatLienhe}),'%')",
			"lower(dtDmPhauThuat.dtdmphauthuatMa) like concat('%',lower(#{dtDmPhauThuatList.dtDmPhauThuat.dtdmphauthuatMa}),'%')",
			"lower(dtDmPhauThuat.dtdmphauthuatMamo) like concat('%',lower(#{dtDmPhauThuatList.dtDmPhauThuat.dtdmphauthuatMamo}),'%')",
			"lower(dtDmPhauThuat.dtdmphauthuatPhanbiet) like concat('%',lower(#{dtDmPhauThuatList.dtDmPhauThuat.dtdmphauthuatPhanbiet}),'%')",
			"lower(dtDmPhauThuat.dtdmphauthuatTen) like concat('%',lower(#{dtDmPhauThuatList.dtDmPhauThuat.dtdmphauthuatTen}),'%')", };

	private DtDmPhauThuat dtDmPhauThuat = new DtDmPhauThuat();

	public DtDmPhauThuatList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmPhauThuatList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DtDmPhauThuat getDtDmPhauThuat() {
		return dtDmPhauThuat;
	}
}
