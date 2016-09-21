package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmNhaSxSpddList")
public class DtDmNhaSxSpddList extends EntityQuery<DtDmNhaSxSpdd> {

	private static final String EJBQL = "select dtDmNhaSxSpdd from DtDmNhaSxSpdd dtDmNhaSxSpdd";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmNhaSxSpdd.dtdmnsxMa) like concat('%',lower(#{dtDmNhaSxSpddList.dtDmNhaSxSpdd.dtdmnsxMa}),'%')",
			"lower(dtDmNhaSxSpdd.dtdmnsxTen) like concat('%',lower(#{dtDmNhaSxSpddList.dtDmNhaSxSpdd.dtdmnsxTen}),'%')", };

	private DtDmNhaSxSpdd dtDmNhaSxSpdd = new DtDmNhaSxSpdd();

	public DtDmNhaSxSpddList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmNhaSxSpdd getDtDmNhaSxSpdd() {
		return dtDmNhaSxSpdd;
	}
}
