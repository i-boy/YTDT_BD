package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmGioAnList")
public class DtDmGioAnList extends EntityQuery<DtDmGioAn> {

	private static final String EJBQL = "select dtDmGioAn from DtDmGioAn dtDmGioAn";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmGioAn.dtdmgaMa) like concat('%',lower(#{dtDmGioAnList.dtDmGioAn.dtdmgaMa}),'%')",
			"lower(dtDmGioAn.dtdmgaTen) like concat('%',lower(#{dtDmGioAnList.dtDmGioAn.dtdmgaTen}),'%')", };

	private DtDmGioAn dtDmGioAn = new DtDmGioAn();

	public DtDmGioAnList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmGioAn getDtDmGioAn() {
		return dtDmGioAn;
	}
}
