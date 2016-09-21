package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmKetQuaList")
public class DtDmKetQuaList extends EntityQuery<DtDmKetQua> {

	private static final String EJBQL = "select dtDmKetQua from DtDmKetQua dtDmKetQua";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmKetQua.dtdmketquaDiengiai) like concat('%',lower(#{dtDmKetQuaList.dtDmKetQua.dtdmketquaDiengiai}),'%')",
			"lower(dtDmKetQua.dtdmketquaMa) like concat('%',lower(#{dtDmKetQuaList.dtDmKetQua.dtdmketquaMa}),'%')", };

	private DtDmKetQua dtDmKetQua = new DtDmKetQua();

	public DtDmKetQuaList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmKetQua getDtDmKetQua() {
		return dtDmKetQua;
	}
}
