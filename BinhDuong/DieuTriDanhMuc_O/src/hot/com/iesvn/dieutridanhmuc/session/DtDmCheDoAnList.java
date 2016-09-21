package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmCheDoAnList")
public class DtDmCheDoAnList extends EntityQuery<DtDmCheDoAn> {

	private static final String EJBQL = "select dtDmCheDoAn from DtDmCheDoAn dtDmCheDoAn";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmCheDoAn.dtdmcdaMa) like concat('%',lower(#{dtDmCheDoAnList.dtDmCheDoAn.dtdmcdaMa}),'%')",
			"lower(dtDmCheDoAn.dtdmcdaTen) like concat('%',lower(#{dtDmCheDoAnList.dtDmCheDoAn.dtdmcdaTen}),'%')", };

	private DtDmCheDoAn dtDmCheDoAn = new DtDmCheDoAn();

	public DtDmCheDoAnList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmCheDoAn getDtDmCheDoAn() {
		return dtDmCheDoAn;
	}
}
