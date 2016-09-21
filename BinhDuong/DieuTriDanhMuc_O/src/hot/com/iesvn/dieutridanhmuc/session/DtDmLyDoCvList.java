package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dtDmLyDoCvList")
public class DtDmLyDoCvList extends EntityQuery<DtDmLyDoCv> {

	private static final String EJBQL = "select dtDmLyDoCv from DtDmLyDoCv dtDmLyDoCv";

	private static final String[] RESTRICTIONS = {
			"lower(dtDmLyDoCv.dtdmlydocvMa) like concat('%',lower(#{dtDmLyDoCvList.dtDmLyDoCv.dtdmlydocvMa}),'%')",
			"lower(dtDmLyDoCv.dtdmlydocvTen) like concat('%',lower(#{dtDmLyDoCvList.dtDmLyDoCv.dtdmlydocvTen}),'%')", };

	private DtDmLyDoCv dtDmLyDoCv = new DtDmLyDoCv();

	public DtDmLyDoCvList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DtDmLyDoCv getDtDmLyDoCv() {
		return dtDmLyDoCv;
	}
}
