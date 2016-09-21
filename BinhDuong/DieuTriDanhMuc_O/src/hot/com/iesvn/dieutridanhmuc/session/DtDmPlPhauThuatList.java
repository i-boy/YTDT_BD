package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmPlPhauThuatList")
public class DtDmPlPhauThuatList extends EntityQuery<DtDmPlPhauThuat> {

	private static final String EJBQL = "select dtDmPlPhauThuat from DtDmPlPhauThuat dtDmPlPhauThuat";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmPlPhauThuat.dtdmplptMa) like concat('%',lower(#{dtDmPlPhauThuatList.dtDmPlPhauThuat.dtdmplptMa}),'%')",
			"lower(dtDmPlPhauThuat.dtdmplptMaphu) like concat('%',lower(#{dtDmPlPhauThuatList.dtDmPlPhauThuat.dtdmplptMaphu}),'%')",
			"lower(dtDmPlPhauThuat.dtdmplptTen) like concat('%',lower(#{dtDmPlPhauThuatList.dtDmPlPhauThuat.dtdmplptTen}),'%')",
			"lower(dtDmPlPhauThuat.dtdmplptThuockhoa) like concat('%',lower(#{dtDmPlPhauThuatList.dtDmPlPhauThuat.dtdmplptThuockhoa}),'%')", };

	private DtDmPlPhauThuat dtDmPlPhauThuat = new DtDmPlPhauThuat();

	public DtDmPlPhauThuatList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmPlPhauThuatList(String tempParam) {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}

	public DtDmPlPhauThuat getDtDmPlPhauThuat() {
		return dtDmPlPhauThuat;
	}
}
