package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmDanTocList")
public class DmDanTocList extends EntityQuery<DmDanToc> {

	private static final String EJBQL = "select dmDanToc from DmDanToc dmDanToc";

	private static final String[] RESTRICTIONS = {
			"lower(dmDanToc.dmdantocMa) like concat('%',lower(#{dmDanTocList.dmDanToc.dmdantocMa}),'%')",
			"lower(dmDanToc.dmdantocTen) like concat('%',lower(#{dmDanTocList.dmDanToc.dmdantocTen}),'%')", };

	private DmDanToc dmDanToc = new DmDanToc();

	public DmDanTocList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmDanToc getDmDanToc() {
		return dmDanToc;
	}
}
