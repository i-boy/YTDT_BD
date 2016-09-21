package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmChiDanList")
public class DtDmChiDanList extends EntityQuery<DtDmChiDan> {

	private static final String EJBQL = "select dtDmChiDan from DtDmChiDan dtDmChiDan";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmChiDan.dtdmchidanMa) like concat('%',lower(#{dtDmChiDanList.dtDmChiDan.dtdmchidanMa}),'%')",
			"lower(dtDmChiDan.dtdmchidanMaphu) like concat('%',lower(#{dtDmChiDanList.dtDmChiDan.dtdmchidanMaphu}),'%')",
			"lower(dtDmChiDan.dtdmchidanTen) like concat('%',lower(#{dtDmChiDanList.dtDmChiDan.dtdmchidanTen}),'%')", };

	private DtDmChiDan dtDmChiDan = new DtDmChiDan();

	public DtDmChiDanList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmChiDan getDtDmChiDan() {
		return dtDmChiDan;
	}
}
