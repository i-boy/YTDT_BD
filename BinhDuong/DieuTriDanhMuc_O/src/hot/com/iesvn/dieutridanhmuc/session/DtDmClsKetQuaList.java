package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmClsKetQuaList")
public class DtDmClsKetQuaList extends EntityQuery<DtDmClsKetQua> {

	private static final String EJBQL = "select dtDmClsKetQua from DtDmClsKetQua dtDmClsKetQua";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmClsKetQua.dtdmclskqGhiChu) like concat('%',lower(#{dtDmClsKetQuaList.dtDmClsKetQua.dtdmclskqGhiChu}),'%')",
			"lower(dtDmClsKetQua.dtdmclskqMa) like concat('%',lower(#{dtDmClsKetQuaList.dtDmClsKetQua.dtdmclskqMa}),'%')",
			"lower(dtDmClsKetQua.dtdmclskqTen) like concat('%',lower(#{dtDmClsKetQuaList.dtDmClsKetQua.dtdmclskqTen}),'%')", };

	private DtDmClsKetQua dtDmClsKetQua = new DtDmClsKetQua();

	public DtDmClsKetQuaList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmClsKetQua getDtDmClsKetQua() {
		return dtDmClsKetQua;
	}
}
