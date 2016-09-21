package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmTinhBhytList")
public class DtDmTinhBhytList extends EntityQuery<DtDmTinhBhyt> {

	private static final String EJBQL = "select dtDmTinhBhyt from DtDmTinhBhyt dtDmTinhBhyt";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmTinhBhyt.dtdmtinhbhytMa) like lower(#{dtDmTinhBhytList.dtDmTinhBhyt.dtdmtinhbhytMa})",
			"lower(dtDmTinhBhyt.dtdmtinhbhytNoitinh) like concat('%',lower(#{dtDmTinhBhytList.dtDmTinhBhyt.dtdmtinhbhytNoitinh}),'%')",
			"lower(dtDmTinhBhyt.dtdmtinhbhytTen) like concat('%',lower(#{dtDmTinhBhytList.dtDmTinhBhyt.dtdmtinhbhytTen}),'%')",
			"lower(dtDmTinhBhyt.dtdmtinhbhytTen1) like concat('%',lower(#{dtDmTinhBhytList.dtDmTinhBhyt.dtdmtinhbhytTen1}),'%')",
			"lower(dtDmTinhBhyt.dtdmtinhbhytTen2) like concat('%',lower(#{dtDmTinhBhytList.dtDmTinhBhyt.dtdmtinhbhytTen2}),'%')",
			"lower(dtDmTinhBhyt.dtdmtinhbhytTen3) like concat('%',lower(#{dtDmTinhBhytList.dtDmTinhBhyt.dtdmtinhbhytTen3}),'%')", };

	private DtDmTinhBhyt dtDmTinhBhyt = new DtDmTinhBhyt();

	public DtDmTinhBhytList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmTinhBhyt getDtDmTinhBhyt() {
		return dtDmTinhBhyt;
	}
}
