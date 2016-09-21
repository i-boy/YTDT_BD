package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmMucAnList")
public class DtDmMucAnList extends EntityQuery<DtDmMucAn> {

	private static final String EJBQL = "select dtDmMucAn from DtDmMucAn dtDmMucAn";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmMucAn.dtdmmaMa) like concat('%',lower(#{dtDmMucAnList.dtDmMucAn.dtdmmaMa}),'%')",
			"lower(dtDmMucAn.dtdmmaTen) like concat('%',lower(#{dtDmMucAnList.dtDmMucAn.dtdmmaTen}),'%')", };

	private DtDmMucAn dtDmMucAn = new DtDmMucAn();

	public DtDmMucAnList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmMucAn getDtDmMucAn() {
		return dtDmMucAn;
	}
}
