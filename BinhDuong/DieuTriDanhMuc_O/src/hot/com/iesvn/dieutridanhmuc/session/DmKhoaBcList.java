package com.iesvn.dieutridanhmuc.session;

import com.iesvn.dieutridanhmuc.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("dmKhoaBcList")
public class DmKhoaBcList extends EntityQuery<DmKhoaBc> {

	private static final String EJBQL = "select dmKhoaBc from DmKhoaBc dmKhoaBc";

	private static final String[] RESTRICTIONS = {
			"lower(dmKhoaBc.dmkhoabcMa) like concat('%',lower(#{dmKhoaBcList.dmKhoaBc.dmkhoabcMa}),'%')",
			"lower(dmKhoaBc.dmkhoabcTen) like concat('%',lower(#{dmKhoaBcList.dmKhoaBc.dmkhoabcTen}),'%')", };

	private DmKhoaBc dmKhoaBc = new DmKhoaBc();

	public DmKhoaBcList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public DmKhoaBc getDmKhoaBc() {
		return dmKhoaBc;
	}
}
