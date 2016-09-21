package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmHuongDtList")
public class DtDmHuongDtList extends EntityQuery<DtDmHuongDt> {

	private static final String EJBQL = "select dtDmHuongDt from DtDmHuongDt dtDmHuongDt";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmHuongDt.dtdmhuongdtMa) like concat('%',lower(#{dtDmHuongDtList.dtDmHuongDt.dtdmhuongdtMa}),'%')",
			"lower(dtDmHuongDt.dtdmhuongdtTen) like concat('%',lower(#{dtDmHuongDtList.dtDmHuongDt.dtdmhuongdtTen}),'%')", };

	private DtDmHuongDt dtDmHuongDt = new DtDmHuongDt();

	public DtDmHuongDtList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmHuongDt getDtDmHuongDt() {
		return dtDmHuongDt;
	}
}
